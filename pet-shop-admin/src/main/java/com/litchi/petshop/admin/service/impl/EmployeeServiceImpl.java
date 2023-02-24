package com.litchi.petshop.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.admin.dao.EmployeeDao;
import com.litchi.petshop.admin.entity.EmployeeEntity;
import com.litchi.petshop.admin.service.EmployeeService;


@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, EmployeeEntity> implements EmployeeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<EmployeeEntity> employeeEntityList = new ArrayList<>();

        String key = (String) params.get("key");

        QueryWrapper<EmployeeEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> {
                obj.like("username", key);
            });
        }
        List<EmployeeEntity> entities = this.list(wrapper);

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, entities);
        }
        Page<EmployeeEntity> page = new Page<>();
        page.setRecords(entities);
        page.setTotal(entities.size());
        return new PageUtils(page);
    }

    @Override
    public EmployeeEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

}