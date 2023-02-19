package com.litchi.pojo.product.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/18 19:59
 */
@Data
public class ProductSaleDetailDto {
    /**
     * 商品售卖详情编号
     */
    private Integer saleDetailId;
    /**
     * 商品售卖编号
     */
    private Integer saleId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 售卖折后价
     */
    private BigDecimal discountedPrice;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 备注
     */
    private String remarks;
}
