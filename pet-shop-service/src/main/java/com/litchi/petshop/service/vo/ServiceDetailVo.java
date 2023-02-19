package com.litchi.petshop.service.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Mark
 * 2023/2/14 15:56
 */
@Data
public class ServiceDetailVo {
    private Integer serviceDetailId;
    /**
     * 服务编号
     */
    private Integer serviceId;
    /**
     * 服务人
     */
    private String memberName;
    /**
     * 宠物名称
     */
    private String petName;
    /**
     * 服务项目子类名称
     */
    private String serviceItemSubName;
    /**
     * 服务折扣价
     */
    private BigDecimal serviceDiscountPrice;
    /**
     * 员工名称
     */
    private String employeeName;
    /**
     * 服务开始时间
     */
    private Date serviceStartTime;
    /**
     * 服务结束时间
     */
    private Date serviceEndTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 服务是否结束标记
     */
    private Integer serviceEndTag;
}
