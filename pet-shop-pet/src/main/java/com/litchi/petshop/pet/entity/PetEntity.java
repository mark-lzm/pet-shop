package com.litchi.petshop.pet.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记载会员的宠物信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Data
@TableName("pet")
public class PetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 宠物编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员编号
	 */
	private Integer memberId;
	/**
	 * 宠物名称
	 */
	private String name;
	/**
	 * 宠物品种编号
	 */
	private Integer breedId;
	/**
	 * 宠物性别 
	 */
	private String sex;
	/**
	 * 宠物重量
	 */
	private Double weight;
	/**
	 * 宠物毛色编号
	 */
	private Integer coatColorId;
	/**
	 * 宠物照片
	 */
	private String photo;
	/**
	 * 宠物状态标记，1为在店，0为不在店
	 */
	private Integer status;

}
