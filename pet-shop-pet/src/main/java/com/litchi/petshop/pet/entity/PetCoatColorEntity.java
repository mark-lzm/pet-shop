package com.litchi.petshop.pet.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录宠物毛色分类，方便宠物登记时录入信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Data
@TableName("pet_coat_color")
public class PetCoatColorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 宠物毛色编号
	 */
	@TableId
	private Integer coatColorId;
	/**
	 * 宠物毛色名称
	 */
	private String coatColorName;

}
