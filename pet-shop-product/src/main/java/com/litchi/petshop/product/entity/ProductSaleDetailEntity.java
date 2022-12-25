package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录顾客一次购买几种商品的需要。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product_sale_detail")
public class ProductSaleDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品售卖详情编号
	 */
	@TableId
	private Integer saleDetailId;
	/**
	 * 商品售卖编号
	 */
	private Integer saleId;
	/**
	 * 商品编号
	 */
	private Integer productId;
	/**
	 * 售卖折后价
	 */
	private BigDecimal discountedPrice;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 备注
	 */
	private String remarks;

}
