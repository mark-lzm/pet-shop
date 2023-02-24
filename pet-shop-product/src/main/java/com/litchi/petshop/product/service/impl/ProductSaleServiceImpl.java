package com.litchi.petshop.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.bo.ProductSaleBo;
import com.litchi.petshop.product.feign.AdminFeignService;
import com.litchi.petshop.product.feign.MemberFeignService;
import com.litchi.petshop.product.vo.ProductSaleVo;
import com.litchi.pojo.admin.dto.EmployeeDto;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.product.dto.ProductSaleDto;
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

import com.litchi.petshop.product.dao.ProductSaleDao;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.service.ProductSaleService;
import org.springframework.transaction.annotation.Transactional;


@Service("productSaleService")
public class ProductSaleServiceImpl extends ServiceImpl<ProductSaleDao, ProductSaleEntity> implements ProductSaleService {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    AdminFeignService adminFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<ProductSaleVo> voList = new ArrayList<>();

        String key = (String) params.get("key");

        QueryWrapper<ProductSaleEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
//                obj.eq("id", key).or().eq("member_id", key);
                obj.eq("id", key);
            });
        }
        List<ProductSaleEntity> entities = this.list(wrapper);
        for (ProductSaleEntity entity : entities) {
            ProductSaleVo vo = new ProductSaleVo();
            BeanUtils.copyProperties(entity, vo);

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

        Page<ProductSaleVo> page = new Page<>();
        page.setRecords(voList);
        page.setTotal(voList.size());
        return new PageUtils(page);
    }

    @Override
    public List<ProductSaleVo> listSelectProductSale() {
        List<ProductSaleVo> result = new ArrayList<>();
        List<ProductSaleEntity> list = this.list();
        for (ProductSaleEntity productSale : list) {
            ProductSaleVo vo = new ProductSaleVo();
            BeanUtils.copyProperties(productSale, vo);

            MemberDto memberDto = memberFeignService.memberById(productSale.getMemberId());
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
    public Map<String, String> pay(ProductSaleBo bo) {
        Map<String, String> map = new HashMap<>();

        MemberDto memberDto = memberFeignService.memberById(bo.getMemberId());
        // 修改支付金额
        if ("会员卡支付".equals(bo.getPayMode())) {
            // 修改会员信息，采用会员卡支付
            // 要修改的金额
            if (memberDto.getBalance() >= bo.getSalePrice().doubleValue()) {
                memberDto.setBalance(memberDto.getBalance() - bo.getSalePrice().doubleValue());
                memberFeignService.updateMemberDto(memberDto);
            } else {
                // 会员金额不足，无法支付
                map.put("message", "会员余额不足，无法支付");
                return map;
            }
        } else {
            // 其他则为微信支付和支付宝支付
            // 会员积分增加
            memberDto.setAvailablePoints(bo.getSalePrice().intValue() + memberDto.getAvailablePoints());
            memberDto.setPoints(bo.getSalePrice().intValue() + memberDto.getPoints());
            memberFeignService.updateMemberDto(memberDto);

            //检查积分是否达到门槛，更新会员等级
            memberFeignService.updateGradeIdByPoints(memberDto);
        }

        ProductSaleEntity productSale = this.getById(bo.getSaleId());
        productSale.setPayMode(bo.getPayMode());
        productSale.setPaidMark(1);
        productSale.setSaleTime(new Date());
        this.updateById(productSale);

        return map;
    }

    @Override
    public List<ProductSaleDto> listAllProductSale() {
        List<ProductSaleDto> result = new ArrayList<>();
        List<ProductSaleEntity> list = this.list();
        for (ProductSaleEntity productSale : list) {
            ProductSaleDto dto = new ProductSaleDto();
            BeanUtils.copyProperties(productSale, dto);
            result.add(dto);
        }
        return result;
    }

}