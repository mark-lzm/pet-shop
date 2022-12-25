package com.litchi.petshop.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductStorageDao;
import com.litchi.petshop.product.entity.ProductStorageEntity;
import com.litchi.petshop.product.service.ProductStorageService;


@Service("productStorageService")
public class ProductStorageServiceImpl extends ServiceImpl<ProductStorageDao, ProductStorageEntity> implements ProductStorageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductStorageEntity> page = this.page(
                new Query<ProductStorageEntity>().getPage(params),
                new QueryWrapper<ProductStorageEntity>()
        );

        return new PageUtils(page);
    }

}