package com.litchi.petshop.service.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/14 11:29
 */
@Data
public class ServiceBo {
    private Integer id;
    private String payMode;
    private BigDecimal serviceTotalPrice;
    private BigDecimal serviceDiscountPrice;
    private Integer memberId;
}
