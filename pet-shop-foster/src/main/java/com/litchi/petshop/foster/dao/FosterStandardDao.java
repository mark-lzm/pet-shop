package com.litchi.petshop.foster.dao;

import com.litchi.petshop.foster.entity.FosterStandardEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录不同大小的宠物寄养时不同的价格信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Mapper
public interface FosterStandardDao extends BaseMapper<FosterStandardEntity> {
	
}
