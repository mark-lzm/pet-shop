package com.litchi.petshop.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.service.bo.ServiceBo;
import com.litchi.petshop.service.feign.AdminFeignService;
import com.litchi.petshop.service.feign.MemberFeignService;
import com.litchi.petshop.service.vo.ServiceVo;
import com.litchi.pojo.admin.dto.EmployeeDto;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.service.dto.ServiceDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceDao;
import com.litchi.petshop.service.entity.ServiceEntity;
import com.litchi.petshop.service.service.ServiceService;
import org.springframework.transaction.annotation.Transactional;


@Service("serviceService")
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    AdminFeignService adminFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<ServiceVo> voList = new ArrayList<>();

        String key = (String) params.get("key");

        QueryWrapper<ServiceEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().eq("member_id", key);
            });
        }

        List<ServiceEntity> entities = this.list(wrapper);
        for (ServiceEntity entity : entities) {
            ServiceVo vo = new ServiceVo();
            BeanUtils.copyProperties(entity, vo);

            vo.setMemberId(entity.getMemberId());
            MemberDto memberDto = memberFeignService.memberById(entity.getMemberId());
            vo.setMemberName(memberDto.getName());

            EmployeeDto employeedto = adminFeignService.employeedto(entity.getOperatorId());
            vo.setOperatorName(employeedto.getName());

            voList.add(vo);
        }

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, voList);
        }
        Page<ServiceVo> page = new Page<>();
        page.setRecords(voList);
        page.setTotal(voList.size());
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public Map<String, String> pay(ServiceBo bo) {
        Map<String, String> map = new HashMap<>();

        MemberDto memberDto = memberFeignService.memberById(bo.getMemberId());
        // 修改支付金额
        if ("会员卡支付".equals(bo.getPayMode())) {
            // 修改会员信息，采用会员卡支付
            // 要修改的金额
            if (memberDto.getBalance() >= bo.getServiceTotalPrice().doubleValue()) {
                memberDto.setBalance(memberDto.getBalance() - bo.getServiceTotalPrice().doubleValue());
                memberFeignService.updateMemberDto(memberDto);
            } else {
                // 会员金额不足，无法支付
                map.put("message", "会员余额不足，无法支付");
                return map;
            }
        } else {
            // 其他则为微信支付和支付宝支付
            // 会员积分增加
            memberDto.setAvailablePoints(bo.getServiceTotalPrice().intValue() + memberDto.getAvailablePoints());
            memberDto.setPoints(bo.getServiceTotalPrice().intValue() + memberDto.getPoints());
            memberFeignService.updateMemberDto(memberDto);

            //检查积分是否达到门槛，更新会员等级
            memberFeignService.updateGradeIdByPoints(memberDto);
        }
        ServiceEntity service = this.getById(bo.getId());
        service.setPayMode(bo.getPayMode());
        service.setPaidMark(1);
        service.setPayTime(new Date());
        this.updateById(service);

        return map;
    }


    @Override
    public List<ServiceVo> listSelect() {
        List<ServiceVo> result = new ArrayList<>();
        List<ServiceEntity> list = this.list();
        for (ServiceEntity service : list) {
            ServiceVo vo = new ServiceVo();
            BeanUtils.copyProperties(service, vo);

            MemberDto memberDto = memberFeignService.memberById(service.getMemberId());
            vo.setMemberName(memberDto.getName());
            // 未付款的数据进行返回
            if (vo.getPaidMark() == 0) {
                result.add(vo);
            }
        }
        return result;
    }

    @Transactional
    @Override
    public void updatePrice(ServiceBo bo) {
        ServiceEntity service = this.getById(bo.getId());
        service.setServiceTotalPrice(service.getServiceTotalPrice().add(bo.getServiceDiscountPrice()));
        this.updateById(service);
    }

    @Override
    public List<ServiceDto> listAllService() {
        List<ServiceDto> result = new ArrayList<>();
        List<ServiceEntity> list = this.list();
        for (ServiceEntity service : list) {
            ServiceDto dto = new ServiceDto();
            BeanUtils.copyProperties(service, dto);
            result.add(dto);
        }
        return result;
    }


}