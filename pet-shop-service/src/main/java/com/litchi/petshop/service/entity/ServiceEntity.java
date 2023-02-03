package com.litchi.petshop.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 用于记录宠物服务的主要信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Data
@TableName("sms_service")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 操作员编号
	 */
	private Integer operatorId;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 已付标记，1为是，0为否
	 */
	private Integer paidMark;

}
