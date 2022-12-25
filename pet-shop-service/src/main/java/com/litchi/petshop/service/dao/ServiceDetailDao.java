package com.litchi.petshop.service.dao;

import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录宠物服务的详细情况
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Mapper
public interface ServiceDetailDao extends BaseMapper<ServiceDetailEntity> {
	
}
