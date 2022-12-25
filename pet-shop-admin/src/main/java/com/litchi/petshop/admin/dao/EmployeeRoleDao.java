package com.litchi.petshop.admin.dao;

import com.litchi.petshop.admin.entity.EmployeeRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录员工的操作权限
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@Mapper
public interface EmployeeRoleDao extends BaseMapper<EmployeeRoleEntity> {
	
}
