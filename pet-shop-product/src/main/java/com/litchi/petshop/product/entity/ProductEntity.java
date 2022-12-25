package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录商品的信息及库存数量，多次进价后的成本价等。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
	@TableId
	private Integer id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品类别编号
	 */
	private Integer catId;
	/**
	 * 商品单位
	 */
	private String unit;
	/**
	 * 商品规格
	 */
	private String specifications;
	/**
	 * 最后一次进价
	 */
	private BigDecimal lastPurchasePrice;
	/**
	 * 成本价格
	 */
	private BigDecimal costPrice;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 库存数量
	 */
	private Integer stock;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 商品信息标记，1为还在售卖，0为不再售卖
	 */
	private Integer productInfoMark;

}
