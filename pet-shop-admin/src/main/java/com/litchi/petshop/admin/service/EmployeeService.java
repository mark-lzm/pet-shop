package com.litchi.petshop.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.admin.entity.EmployeeEntity;

import java.util.Map;

/**
 * 用于记载员工的信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
public interface EmployeeService extends IService<EmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    EmployeeEntity queryByUserName(String username);
}

