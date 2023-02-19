package com.litchi.petshop.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 用于记录宠物服务的详细情况
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Data
@TableName("sms_service_detail")
public class ServiceDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务详情编号
	 */
	@TableId
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
