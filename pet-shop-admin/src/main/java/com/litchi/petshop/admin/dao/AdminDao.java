package com.litchi.petshop.admin.dao;

import com.litchi.petshop.admin.entity.AdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记载管理员的相关信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {
	
}
