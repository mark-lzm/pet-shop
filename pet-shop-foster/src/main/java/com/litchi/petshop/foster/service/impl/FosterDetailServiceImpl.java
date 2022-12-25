package com.litchi.petshop.foster.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterDetailDao;
import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;


@Service("fosterDetailService")
public class FosterDetailServiceImpl extends ServiceImpl<FosterDetailDao, FosterDetailEntity> implements FosterDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FosterDetailEntity> page = this.page(
                new Query<FosterDetailEntity>().getPage(params),
                new QueryWrapper<FosterDetailEntity>()
        );

        return new PageUtils(page);
    }

}