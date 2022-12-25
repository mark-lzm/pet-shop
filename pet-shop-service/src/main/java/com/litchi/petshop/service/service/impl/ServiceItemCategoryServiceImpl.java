package com.litchi.petshop.service.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceItemCategoryDao;
import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;
import com.litchi.petshop.service.service.ServiceItemCategoryService;


@Service("serviceItemCategoryService")
public class ServiceItemCategoryServiceImpl extends ServiceImpl<ServiceItemCategoryDao, ServiceItemCategoryEntity> implements ServiceItemCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceItemCategoryEntity> page = this.page(
                new Query<ServiceItemCategoryEntity>().getPage(params),
                new QueryWrapper<ServiceItemCategoryEntity>()
        );

        return new PageUtils(page);
    }

}