package com.litchi.petshop.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/16 21:31
 */
@Data
public class ProductSaleDetailVo {

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
    private String productName;
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
