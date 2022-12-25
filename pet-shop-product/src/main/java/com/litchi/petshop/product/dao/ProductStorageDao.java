package com.litchi.petshop.product.dao;

import com.litchi.petshop.product.entity.ProductStorageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录商品入库时的主要信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Mapper
public interface ProductStorageDao extends BaseMapper<ProductStorageEntity> {
	
}
