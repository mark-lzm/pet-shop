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

import com.litchi.petshop.service.dao.ServiceItemSubclassDao;
import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.service.ServiceItemSubclassService;


@Service("serviceItemSubclassService")
public class ServiceItemSubclassServiceImpl extends ServiceImpl<ServiceItemSubclassDao, ServiceItemSubclassEntity> implements ServiceItemSubclassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<ServiceItemSubclassEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("service_item_sub_id", key).or().eq("service_item_id", key).or().like("service_item_sub_name", key);
            });
        }

        List<ServiceItemSubclassEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}