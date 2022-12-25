package com.litchi.petshop.pet.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.pet.dao.PetCoatColorDao;
import com.litchi.petshop.pet.entity.PetCoatColorEntity;
import com.litchi.petshop.pet.service.PetCoatColorService;


@Service("petCoatColorService")
public class PetCoatColorServiceImpl extends ServiceImpl<PetCoatColorDao, PetCoatColorEntity> implements PetCoatColorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PetCoatColorEntity> page = this.page(
                new Query<PetCoatColorEntity>().getPage(params),
                new QueryWrapper<PetCoatColorEntity>()
        );

        return new PageUtils(page);
    }

}