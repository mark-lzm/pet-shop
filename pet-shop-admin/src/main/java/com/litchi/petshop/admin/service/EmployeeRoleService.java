package com.litchi.petshop.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.admin.entity.EmployeeRoleEntity;

import java.util.Map;

/**
 * 用于记录员工的操作权限
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
public interface EmployeeRoleService extends IService<EmployeeRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

