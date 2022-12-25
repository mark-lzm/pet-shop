package com.litchi.petshop.foster.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterDao;
import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.service.FosterService;


@Service("fosterService")
public class FosterServiceImpl extends ServiceImpl<FosterDao, FosterEntity> implements FosterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FosterEntity> page = this.page(
                new Query<FosterEntity>().getPage(params),
                new QueryWrapper<FosterEntity>()
        );

        return new PageUtils(page);
    }

}