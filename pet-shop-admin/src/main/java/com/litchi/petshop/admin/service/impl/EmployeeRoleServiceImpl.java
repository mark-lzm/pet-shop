package com.litchi.petshop.admin.service.impl;

import org.springframework.stereotype.Service;
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
        IPage<EmployeeRoleEntity> page = this.page(
                new Query<EmployeeRoleEntity>().getPage(params),
                new QueryWrapper<EmployeeRoleEntity>()
        );

        return new PageUtils(page);
    }

}