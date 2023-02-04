package com.litchi.petshop.product.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.entity.ProductStorageEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<ProductStorageDetailEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("storage_detail_id", key).or().eq("storage_id", key);
            });
        }

        List<ProductStorageDetailEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}