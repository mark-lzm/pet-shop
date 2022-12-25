package com.litchi.petshop.service.dao;

import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录各个类别的宠物服务价格。宠物服务项目的分类设置，可以方便用户操作。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Mapper
public interface ServiceItemSubclassDao extends BaseMapper<ServiceItemSubclassEntity> {
	
}
