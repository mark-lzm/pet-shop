package com.litchi.petshop.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录各个类别的宠物服务价格。宠物服务项目的分类设置，可以方便用户操作。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@Data
@TableName("sms_service_item_subclass")
public class ServiceItemSubclassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务项目子类编号
	 */
	@TableId
	private Integer serviceItemSubId;
	/**
	 * 宠物服务项目大类编号
	 */
	private Integer serviceItemId;
	/**
	 * 服务项目子类名称
	 */
	private String serviceItemSubName;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 服务项目标记
	 */
	private Integer serviceItemTag;

}
