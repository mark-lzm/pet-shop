package com.litchi.petshop.pet.dao;

import com.litchi.petshop.pet.entity.PetBreedEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录宠物种类的信息，方便操作录入
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Mapper
public interface PetBreedDao extends BaseMapper<PetBreedEntity> {
	
}
