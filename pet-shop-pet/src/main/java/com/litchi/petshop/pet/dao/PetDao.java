package com.litchi.petshop.pet.dao;

import com.litchi.petshop.pet.entity.PetEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记载会员的宠物信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Mapper
public interface PetDao extends BaseMapper<PetEntity> {
	
}
