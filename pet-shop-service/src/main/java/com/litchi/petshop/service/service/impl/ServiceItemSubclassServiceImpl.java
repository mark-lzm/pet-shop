package com.litchi.petshop.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;
import com.litchi.petshop.service.service.ServiceItemCategoryService;
import com.litchi.petshop.service.vo.ServiceItemSubclassVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceItemSubclassDao;
import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.service.ServiceItemSubclassService;


@Service("serviceItemSubclassService")
public class ServiceItemSubclassServiceImpl extends ServiceImpl<ServiceItemSubclassDao, ServiceItemSubclassEntity> implements ServiceItemSubclassService {

    @Autowired
    ServiceItemCategoryService serviceItemCategoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<ServiceItemSubclassVo> voList = new ArrayList<>();

        QueryWrapper<ServiceItemSubclassEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("service_item_sub_id", key).or().eq("service_item_id", key).or().like("service_item_sub_name", key);
            });
        }

        List<ServiceItemSubclassEntity> entities = this.list(wrapper);
        for (ServiceItemSubclassEntity entity : entities) {
            ServiceItemSubclassVo vo = new ServiceItemSubclassVo();
            BeanUtils.copyProperties(entity, vo);

            ServiceItemCategoryEntity serviceItemCategory = serviceItemCategoryService.getById(entity.getServiceItemId());
            vo.setServiceItemName(serviceItemCategory.getServiceItemName());

            voList.add(vo);
        }

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, voList);
        }

        Page<ServiceItemSubclassVo> page = new Page<>();
        page.setRecords(voList);
        page.setTotal(voList.size());
        return new PageUtils(page);
    }

}