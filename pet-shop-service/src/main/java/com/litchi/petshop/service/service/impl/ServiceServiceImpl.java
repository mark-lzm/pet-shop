package com.litchi.petshop.service.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceDao;
import com.litchi.petshop.service.entity.ServiceEntity;
import com.litchi.petshop.service.service.ServiceService;


@Service("serviceService")
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceEntity> page = this.page(
                new Query<ServiceEntity>().getPage(params),
                new QueryWrapper<ServiceEntity>()
        );

        return new PageUtils(page);
    }

}