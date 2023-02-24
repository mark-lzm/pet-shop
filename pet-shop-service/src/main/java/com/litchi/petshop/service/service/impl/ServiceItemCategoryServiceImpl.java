package com.litchi.petshop.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

        QueryWrapper<ServiceItemCategoryEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
//                obj.eq("service_item_id", key).or().like("service_item_name", key);
                obj.like("service_item_name", key);
            });
        }

        List<ServiceItemCategoryEntity> entities = this.list(wrapper);

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, entities);
        }
        Page<ServiceItemCategoryEntity> page = new Page<>();
        page.setRecords(entities);
        page.setTotal(entities.size());
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryServiceItemPage(Map<String, Object> params) {
        IPage<ServiceItemCategoryEntity> page = this.page(
                new Query<ServiceItemCategoryEntity>().getPage(params),
                new QueryWrapper<ServiceItemCategoryEntity>()
        );
        return new PageUtils(page);
    }

}