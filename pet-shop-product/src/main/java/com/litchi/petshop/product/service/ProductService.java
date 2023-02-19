package com.litchi.petshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.product.bo.ProductBo;
import com.litchi.petshop.product.entity.ProductEntity;
import com.litchi.petshop.product.vo.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * 记录商品的信息及库存数量，多次进价后的成本价等。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageAndCategory(Map<String, Object> params);

    List<ProductEntity> listSelectProduct();

    boolean setStock(ProductBo bo);
}

