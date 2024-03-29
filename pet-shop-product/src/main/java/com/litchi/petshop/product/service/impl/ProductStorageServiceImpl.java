package com.litchi.petshop.product.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<ProductStorageEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key);
            });
        }

        List<ProductStorageEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}