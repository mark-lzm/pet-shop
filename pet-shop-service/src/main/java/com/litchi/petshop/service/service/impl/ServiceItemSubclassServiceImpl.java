package com.litchi.petshop.service.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceItemSubclassDao;
import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.service.ServiceItemSubclassService;


@Service("serviceItemSubclassService")
public class ServiceItemSubclassServiceImpl extends ServiceImpl<ServiceItemSubclassDao, ServiceItemSubclassEntity> implements ServiceItemSubclassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceItemSubclassEntity> page = this.page(
                new Query<ServiceItemSubclassEntity>().getPage(params),
                new QueryWrapper<ServiceItemSubclassEntity>()
        );

        return new PageUtils(page);
    }

}