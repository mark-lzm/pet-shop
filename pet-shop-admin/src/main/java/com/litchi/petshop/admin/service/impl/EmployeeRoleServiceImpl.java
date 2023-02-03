package com.litchi.petshop.admin.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.admin.entity.AdminEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.admin.dao.EmployeeRoleDao;
import com.litchi.petshop.admin.entity.EmployeeRoleEntity;
import com.litchi.petshop.admin.service.EmployeeRoleService;


@Service("employeeRoleService")
public class EmployeeRoleServiceImpl extends ServiceImpl<EmployeeRoleDao, EmployeeRoleEntity> implements EmployeeRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<EmployeeRoleEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> {
                obj.eq("role_id", key).or().eq("employee_id", key);
            });
        }
        List<EmployeeRoleEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}