package com.litchi.petshop.product.dao;

import com.litchi.petshop.product.entity.ProductStorageDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录一张入库单有不同商品的情况。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Mapper
public interface ProductStorageDetailDao extends BaseMapper<ProductStorageDetailEntity> {
	
}
