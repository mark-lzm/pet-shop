package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于商品卖出的相关信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product_sale")
public class ProductSaleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品售卖编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员编号
	 */
	private Integer memberId;
	/**
	 * 售卖总价
	 */
	private BigDecimal salePrice;
	/**
	 * 售卖时间
	 */
	private Date saleTime;
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
	 * 是否已付标记，1为是，0为否
	 */
	private Integer paidMark;

}
