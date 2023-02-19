package com.litchi.petshop.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/16 23:06
 */
@Data
public class ProductVo {
    /**
     * 商品编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类别编号
     */
    private Integer catId;
    /**
     * 商品单位
     */
    private String unit;
    /**
     * 商品规格
     */
    private String specifications;
    /**
     * 最后一次进价
     */
    private BigDecimal lastPurchasePrice;
    /**
     * 成本价格
     */
    private BigDecimal costPrice;
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    /**
     * 库存数量
     */
    private Integer stock;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 商品信息标记，1为还在售卖，0为不再售卖
     */
    private Integer productInfoMark;
}
