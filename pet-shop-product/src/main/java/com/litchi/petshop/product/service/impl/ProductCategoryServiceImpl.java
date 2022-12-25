package com.litchi.petshop.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductCategoryDao;
import com.litchi.petshop.product.entity.ProductCategoryEntity;
import com.litchi.petshop.product.service.ProductCategoryService;


@Service("productCategoryService")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao, ProductCategoryEntity> implements ProductCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductCategoryEntity> page = this.page(
                new Query<ProductCategoryEntity>().getPage(params),
                new QueryWrapper<ProductCategoryEntity>()
        );

        return new PageUtils(page);
    }

}