package com.litchi.petshop.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductSaleDao;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.service.ProductSaleService;


@Service("productSaleService")
public class ProductSaleServiceImpl extends ServiceImpl<ProductSaleDao, ProductSaleEntity> implements ProductSaleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductSaleEntity> page = this.page(
                new Query<ProductSaleEntity>().getPage(params),
                new QueryWrapper<ProductSaleEntity>()
        );

        return new PageUtils(page);
    }

}