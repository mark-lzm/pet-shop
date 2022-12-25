package com.litchi.petshop.foster.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterStandardDao;
import com.litchi.petshop.foster.entity.FosterStandardEntity;
import com.litchi.petshop.foster.service.FosterStandardService;


@Service("fosterStandardService")
public class FosterStandardServiceImpl extends ServiceImpl<FosterStandardDao, FosterStandardEntity> implements FosterStandardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FosterStandardEntity> page = this.page(
                new Query<FosterStandardEntity>().getPage(params),
                new QueryWrapper<FosterStandardEntity>()
        );

        return new PageUtils(page);
    }

}