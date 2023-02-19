package com.litchi.petshop.foster.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.foster.bo.FosterDetailBo;
import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.entity.FosterStandardEntity;
import com.litchi.petshop.foster.feign.MemberFeignService;
import com.litchi.petshop.foster.feign.PetFeignService;
import com.litchi.petshop.foster.service.FosterService;
import com.litchi.petshop.foster.service.FosterStandardService;
import com.litchi.petshop.foster.vo.FosterDetailVo;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.member.dto.MemberGradeDto;
import com.litchi.pojo.pet.dto.PetBreedDto;
import com.litchi.pojo.pet.dto.PetCoatColorDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;

import com.litchi.petshop.foster.dao.FosterDetailDao;
import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;
import org.springframework.transaction.annotation.Transactional;


@Service("fosterDetailService")
public class FosterDetailServiceImpl extends ServiceImpl<FosterDetailDao, FosterDetailEntity> implements FosterDetailService {

    @Autowired
    FosterService fosterService;

    @Autowired
    FosterStandardService fosterStandardService;

    @Autowired
    PetFeignService petFeignService;

    @Autowired
    MemberFeignService memberFeignService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<FosterDetailEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("foster_detail_id", key).or().eq("foster_id", key).or().like("pet_name", key);
            });
        }
        List<FosterDetailEntity> entities = this.list(wrapper);
        if (params.get("page") != null && params.get("limit") != null) {
            List<FosterDetailVo> vos = new ArrayList();
            for (FosterDetailEntity entity : entities) {
                FosterDetailVo vo = new FosterDetailVo();
                BeanUtils.copyProperties(entity, vo);
                FosterEntity foster = fosterService.getById(entity.getFosterId());
                vo.setFosterName(foster.getMemberName());
                FosterStandardEntity fosterStandard = fosterStandardService.getById(entity.getFosterStandardId());
                vo.setFosterStandardName(fosterStandard.getFosterStandardName());
                PetBreedDto petBreedDto = petFeignService.selectByBreedId(entity.getPetBreedId());
                vo.setPetBreedName(petBreedDto.getBreedName());
                PetCoatColorDto petCoatColorDto = petFeignService.selectByColorId(entity.getPetCoatColorId());
                vo.setPetCoatColorName(petCoatColorDto.getCoatColorName());
                vos.add(vo);
            }

            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, vos);
        }

        Page<FosterDetailEntity> page = new Page<>();
        page.setRecords(entities);
        page.setTotal(entities.size());

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public Map<String, String> updateFosterDetailBo(FosterDetailBo bo) {
        Map<String, String> map = new HashMap<>();

        FosterDetailEntity fosterDetailEntity = this.getById(bo.getId());
        Integer fosterId = fosterDetailEntity.getFosterId();
        FosterEntity foster = fosterService.getById(fosterId);
        Integer memberId = foster.getMemberId();
        MemberDto memberDto = memberFeignService.memberById(memberId);


        // 设置领取时间  并且  设置实收金额  并且  进行支付
        if (bo.getFosterEndTime() != null) {
            fosterDetailEntity.setFosterEndTime(bo.getFosterEndTime());

            // (计划领走时间 - 寄养开始时间) * 寄养标准 = 实收金额
            //设置实收金额
            Date createTime = fosterDetailEntity.getFosterCreateTime();
            Date endTime = fosterDetailEntity.getFosterEndTime();
            long diff = endTime.getTime() - createTime.getTime();
            TimeUnit time = TimeUnit.DAYS;
            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
            // 默认小于24小时为1天，超过24小时为2天
            diffrence += 1;

            FosterStandardEntity fosterStandard = fosterStandardService.getById(fosterDetailEntity.getFosterStandardId());
            BigDecimal amountReceived = fosterStandard.getFosterStandardPrice().multiply(BigDecimal.valueOf(diffrence));
            // 实收金额，根据会员等级去优惠
            Integer gradeId = memberDto.getGradeId();
            MemberGradeDto memberGradeDto = memberFeignService.memberGradeDtoById(gradeId);
            BigDecimal amountReceivedDiscount = amountReceived.multiply(BigDecimal.valueOf(memberGradeDto.getDiscount()));
            fosterDetailEntity.setAmountReceived(amountReceivedDiscount.doubleValue());


            // 进行支付
            // 支付的钱将由宠物的会员去扣

            // 判断金额是否足够
            if (memberDto.getBalance() >= fosterDetailEntity.getAmountReceived()) {
                memberDto.setBalance(memberDto.getBalance() - fosterDetailEntity.getAmountReceived());
                memberFeignService.updateMemberDto(memberDto);
            } else {
                // 会员金额不足，无法支付
                map.put("message", "会员余额不足，无法支付");
                return map;
            }

            // 设置标志为0
            fosterDetailEntity.setFosterStatus(0);
        }

        this.updateById(fosterDetailEntity);
        return map;
    }

}