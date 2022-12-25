package com.litchi.petshop.foster.dao;

import com.litchi.petshop.foster.entity.FosterEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录寄养服务时会员及非会员的基本。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Mapper
public interface FosterDao extends BaseMapper<FosterEntity> {
	
}
