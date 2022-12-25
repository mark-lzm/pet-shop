package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录一张入库单有不同商品的情况。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product_storage_detail")
public class ProductStorageDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 入库详情编号
	 */
	@TableId
	private Integer storageDetailId;
	/**
	 * 入库编号
	 */
	private Integer storageId;
	/**
	 * 商品编号
	 */
	private Integer productId;
	/**
	 * 入库单价
	 */
	private BigDecimal storageUnitPrice;
	/**
	 * 入库数量
	 */
	private Integer storageAmount;
	/**
	 * 备注
	 */
	private String remarks;

}
