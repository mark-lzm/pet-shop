package com.litchi.petshop.service.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Mark
 * 2023/2/14 14:47
 */
@Data
public class ServiceVo {
    /**
     * 服务编号
     */
    @TableId
    private Integer id;
    /**
     * 会员编号
     */
    private Integer memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 服务总价
     */
    private BigDecimal serviceTotalPrice;
    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date payTime;
    /**
     * 支付方式
     */
    private String payMode;
    /**
     * 操作员名称
     */
    private String operatorName;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 已付标记，1为是，0为否
     */
    private Integer paidMark;
}
