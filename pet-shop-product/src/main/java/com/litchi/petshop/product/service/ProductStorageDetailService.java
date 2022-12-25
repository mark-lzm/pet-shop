package com.litchi.petshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.product.entity.ProductStorageDetailEntity;

import java.util.Map;

/**
 * 用于记录一张入库单有不同商品的情况。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
public interface ProductStorageDetailService extends IService<ProductStorageDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

