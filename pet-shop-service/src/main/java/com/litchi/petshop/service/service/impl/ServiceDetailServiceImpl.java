package com.litchi.petshop.service.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceDetailDao;
import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.litchi.petshop.service.service.ServiceDetailService;


@Service("serviceDetailService")
public class ServiceDetailServiceImpl extends ServiceImpl<ServiceDetailDao, ServiceDetailEntity> implements ServiceDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceDetailEntity> page = this.page(
                new Query<ServiceDetailEntity>().getPage(params),
                new QueryWrapper<ServiceDetailEntity>()
        );

        return new PageUtils(page);
    }

}