package com.litchi.petshop.foster.dao;

import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 由于一个顾客可以寄养多只宠物，不同的宠物及不同的服务价格，必须通过详情表来体现。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Mapper
public interface FosterDetailDao extends BaseMapper<FosterDetailEntity> {
	
}
