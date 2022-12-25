package com.litchi.petshop.pet.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.pet.dao.PetDao;
import com.litchi.petshop.pet.entity.PetEntity;
import com.litchi.petshop.pet.service.PetService;


@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetDao, PetEntity> implements PetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PetEntity> page = this.page(
                new Query<PetEntity>().getPage(params),
                new QueryWrapper<PetEntity>()
        );

        return new PageUtils(page);
    }

}