package com.litchi.petshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录商品的不同种类。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@Data
@TableName("pms_product_category")
public class ProductCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品类别编号
	 */
	@TableId
	private Integer catId;
	/**
	 * 商品类别名称
	 */
	private String name;

}
