package com.litchi.petshop.service.service.impl;

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

import com.litchi.petshop.service.dao.ServiceItemCategoryDao;
import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;
import com.litchi.petshop.service.service.ServiceItemCategoryService;


@Service("serviceItemCategoryService")
public class ServiceItemCategoryServiceImpl extends ServiceImpl<ServiceItemCategoryDao, ServiceItemCategoryEntity> implements ServiceItemCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<ServiceItemCategoryEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("service_item_id", key).or().like("service_item_name", key);
            });
        }

        List<ServiceItemCategoryEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}