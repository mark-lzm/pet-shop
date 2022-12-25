package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录商品入库时的主要信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product_storage")
public class ProductStorageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 入库编号
	 */
	@TableId
	private Integer id;
	/**
	 * 入库时间
	 */
	private Date storageTime;
	/**
	 * 入库总价
	 */
	private BigDecimal storageTotalPrice;
	/**
	 * 操作员编号
	 */
	private Integer operatorId;
	/**
	 * 备注
	 */
	private String remarks;

}
