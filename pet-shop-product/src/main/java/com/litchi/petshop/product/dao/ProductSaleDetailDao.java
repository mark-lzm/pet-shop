package com.litchi.petshop.product.dao;

import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于记录顾客一次购买几种商品的需要。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Mapper
public interface ProductSaleDetailDao extends BaseMapper<ProductSaleDetailEntity> {
	
}
