package com.litchi.petshop.pet.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录宠物种类的信息，方便操作录入
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@Data
@TableName("pet_breed")
public class PetBreedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 宠物品种编号
	 */
	@TableId
	private Integer breedId;
	/**
	 * 宠物品种名称
	 */
	private String breedName;

}
