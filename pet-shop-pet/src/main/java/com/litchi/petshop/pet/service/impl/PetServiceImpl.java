package com.litchi.petshop.pet.service.impl;

import com.litchi.common.utils.PetPageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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

        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<PetEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().eq("member_id", key).or().like("name", key);
            });
        }

        List<PetEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}