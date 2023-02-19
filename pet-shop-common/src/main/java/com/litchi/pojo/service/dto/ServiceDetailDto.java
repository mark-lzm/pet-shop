package com.litchi.pojo.service.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Mark
 * 2023/2/18 20:09
 */
@Data
public class ServiceDetailDto {
    /**
     * 服务详情编号
     */
    private Integer serviceDetailId;
    /**
     * 服务编号
     */
    private Integer serviceId;
    /**
     * 宠物编号
     */
    private Integer petId;
    /**
     * 服务项目子类编号
     */
    private Integer serviceItemSubId;
    /**
     * 服务折扣价
     */
    private BigDecimal serviceDiscountPrice;
    /**
     * 员工编号
     */
    private Integer employeeId;
    /**
     * 服务开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date serviceStartTime;
    /**
     * 服务结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
