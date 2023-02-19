package com.litchi.petshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.litchi.pojo.product.dto.ProductSaleDetailDto;

import java.util.List;
import java.util.Map;

/**
 * 用于记录顾客一次购买几种商品的需要。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
public interface ProductSaleDetailService extends IService<ProductSaleDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductSaleDetailDto> listAllProductSaleDetail();
}

