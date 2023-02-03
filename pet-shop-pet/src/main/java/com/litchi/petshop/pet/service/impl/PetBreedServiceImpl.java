package com.litchi.petshop.pet.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.pet.entity.PetEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<PetBreedEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("breed_id", key).or().like("breed_name", key);
            });
        }

        List<PetBreedEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}