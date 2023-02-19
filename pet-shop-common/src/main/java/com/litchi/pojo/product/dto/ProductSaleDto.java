package com.litchi.pojo.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Mark
 * 2023/2/18 19:53
 */
@Data
public class ProductSaleDto {
    /**
     * 商品售卖编号
     */
    private Integer id;
    /**
     * 会员编号
     */
    private Integer memberId;
    /**
     * 售卖总价
     */
    private BigDecimal salePrice;
    /**
     * 售卖时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date saleTime;
    /**
     * 支付方式
     */
    private String payMode;
    /**
     * 操作员编号
     */
    private Integer operatorId;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否已付标记，1为是，0为否
     */
    private Integer paidMark;
}
