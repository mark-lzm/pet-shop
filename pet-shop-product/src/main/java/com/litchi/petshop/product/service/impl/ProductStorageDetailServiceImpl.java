package com.litchi.petshop.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductStorageDetailDao;
import com.litchi.petshop.product.entity.ProductStorageDetailEntity;
import com.litchi.petshop.product.service.ProductStorageDetailService;


@Service("productStorageDetailService")
public class ProductStorageDetailServiceImpl extends ServiceImpl<ProductStorageDetailDao, ProductStorageDetailEntity> implements ProductStorageDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductStorageDetailEntity> page = this.page(
                new Query<ProductStorageDetailEntity>().getPage(params),
                new QueryWrapper<ProductStorageDetailEntity>()
        );

        return new PageUtils(page);
    }

}