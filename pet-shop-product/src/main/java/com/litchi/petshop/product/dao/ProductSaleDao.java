package com.litchi.petshop.product.dao;

import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于商品卖出的相关信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Mapper
public interface ProductSaleDao extends BaseMapper<ProductSaleEntity> {
	
}
