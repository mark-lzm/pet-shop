package com.litchi.petshop.service.dao;

import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录服务的大类。如：美容类、洗澡类等。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Mapper
public interface ServiceItemCategoryDao extends BaseMapper<ServiceItemCategoryEntity> {
	
}
