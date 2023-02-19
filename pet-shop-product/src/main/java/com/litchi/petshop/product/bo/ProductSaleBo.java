package com.litchi.petshop.product.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/16 22:32
 */
@Data
public class ProductSaleBo {
    private Integer saleId;
    private BigDecimal discountedPrice;

    private String payMode;
    private BigDecimal salePrice;
    private Integer memberId;
}
