package com.litchi.petshop.product.dao;

import com.litchi.petshop.product.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录商品的信息及库存数量，多次进价后的成本价等。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductEntity> {
	
}
