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

import com.litchi.petshop.pet.dao.PetCoatColorDao;
import com.litchi.petshop.pet.entity.PetCoatColorEntity;
import com.litchi.petshop.pet.service.PetCoatColorService;


@Service("petCoatColorService")
public class PetCoatColorServiceImpl extends ServiceImpl<PetCoatColorDao, PetCoatColorEntity> implements PetCoatColorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<PetCoatColorEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("coat_color_id", key).or().like("coat_color_name", key);
            });
        }

        List<PetCoatColorEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}