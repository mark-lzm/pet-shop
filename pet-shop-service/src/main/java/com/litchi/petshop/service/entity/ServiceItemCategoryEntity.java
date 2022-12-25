package com.litchi.petshop.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录服务的大类。如：美容类、洗澡类等。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Data
@TableName("sms_service_item_category")
public class ServiceItemCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务项目大类编号
	 */
	@TableId
	private Integer serviceItemId;
	/**
	 * 服务项目大类名称
	 */
	private String serviceItemName;

}
