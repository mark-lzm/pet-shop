package com.litchi.petshop.foster.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录不同大小的宠物寄养时不同的价格信息。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Data
@TableName("fms_foster_standard")
public class FosterStandardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 寄养标准编号
	 */
	@TableId
	private Integer fosterStandardId;
	/**
	 * 寄养标准名称
	 */
	private String fosterStandardName;
	/**
	 * 寄养价格
	 */
	private BigDecimal fosterStandardPrice;

}
