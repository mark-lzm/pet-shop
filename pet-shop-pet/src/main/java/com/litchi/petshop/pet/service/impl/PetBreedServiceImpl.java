package com.litchi.petshop.pet.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.pet.dao.PetBreedDao;
import com.litchi.petshop.pet.entity.PetBreedEntity;
import com.litchi.petshop.pet.service.PetBreedService;


@Service("petBreedService")
public class PetBreedServiceImpl extends ServiceImpl<PetBreedDao, PetBreedEntity> implements PetBreedService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PetBreedEntity> page = this.page(
                new Query<PetBreedEntity>().getPage(params),
                new QueryWrapper<PetBreedEntity>()
        );

        return new PageUtils(page);
    }

}