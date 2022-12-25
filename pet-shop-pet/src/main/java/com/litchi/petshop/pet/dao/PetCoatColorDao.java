package com.litchi.petshop.pet.dao;

import com.litchi.petshop.pet.entity.PetCoatColorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录宠物毛色分类，方便宠物登记时录入信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Mapper
public interface PetCoatColorDao extends BaseMapper<PetCoatColorEntity> {
	
}
