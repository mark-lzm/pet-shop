package com.litchi.petshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.product.entity.ProductCategoryEntity;

import java.util.Map;

/**
 * 用于记录商品的不同种类。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
public interface ProductCategoryService extends IService<ProductCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

