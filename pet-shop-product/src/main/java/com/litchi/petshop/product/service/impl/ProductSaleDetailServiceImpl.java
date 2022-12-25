package com.litchi.petshop.product.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductSaleDetailEntity> page = this.page(
                new Query<ProductSaleDetailEntity>().getPage(params),
                new QueryWrapper<ProductSaleDetailEntity>()
        );

        return new PageUtils(page);
    }

}