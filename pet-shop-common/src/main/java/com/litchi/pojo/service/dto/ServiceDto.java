package com.litchi.pojo.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Mark
 * 2023/2/16 17:46
 */
@Data
public class ServiceDto {
    private Integer id;

    private Integer memberId;

    private BigDecimal serviceTotalPrice;

    private Date payTime;

    private String payMode;

    private Integer operatorId;

    private String remarks;

    private Integer paidMark;
}
