package com.litchi.petshop.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.entity.ProductEntity;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.service.ProductService;
import com.litchi.petshop.product.vo.ProductSaleDetailVo;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.product.dto.ProductSaleDetailDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductSaleDetailDao;
import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.litchi.petshop.product.service.ProductSaleDetailService;


@Service("productSaleDetailService")
public class ProductSaleDetailServiceImpl extends ServiceImpl<ProductSaleDetailDao, ProductSaleDetailEntity> implements ProductSaleDetailService {

    @Autowired
    ProductService productService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<ProductSaleDetailVo> voList = new ArrayList<>();

        String key = (String) params.get("key");

        QueryWrapper<ProductSaleDetailEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("sale_detail_id", key).or().eq("sale_id", key);
            });
        }

        List<ProductSaleDetailEntity> entities = this.list(wrapper);
        for (ProductSaleDetailEntity entity : entities) {
            ProductSaleDetailVo vo = new ProductSaleDetailVo();
            BeanUtils.copyProperties(entity, vo);
            ProductEntity product = productService.getById(entity.getProductId());
            vo.setProductName(product.getName());
            voList.add(vo);
        }

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, voList);
        }

        Page<ProductSaleDetailVo> page = new Page<>();
        page.setRecords(voList);
        page.setTotal(voList.size());
        return new PageUtils(page);
    }

    @Override
    public List<ProductSaleDetailDto> listAllProductSaleDetail() {
        List<ProductSaleDetailDto> result = new ArrayList<>();
        List<ProductSaleDetailEntity> list = this.list();
        for (ProductSaleDetailEntity productSaleDetail : list) {
            ProductSaleDetailDto dto = new ProductSaleDetailDto();
            BeanUtils.copyProperties(productSaleDetail, dto);
            result.add(dto);
        }
        return result;
    }

}