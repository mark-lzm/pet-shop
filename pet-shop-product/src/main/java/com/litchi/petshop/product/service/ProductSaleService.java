package com.litchi.petshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.product.bo.ProductSaleBo;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.vo.ProductSaleVo;
import com.litchi.pojo.product.dto.ProductSaleDto;

import java.util.List;
import java.util.Map;

/**
 * 用于商品卖出的相关信息。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
public interface ProductSaleService extends IService<ProductSaleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductSaleVo> listSelectProductSale();

    Map<String, String> pay(ProductSaleBo bo);

    List<ProductSaleDto> listAllProductSale();
}

