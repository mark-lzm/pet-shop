package com.litchi.petshop.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:51:15
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员姓名
	 */
	private String name;
	/**
	 * 会员电话
	 */
	private String phone;
	/**
	 * 会员地址
	 */
	private String address;
	/**
	 * 会员性别
	 */
	private String sex;
	/**
	 * 会员可用积分
	 */
	private Integer availablePoints;
	/**
	 * 会员积分
	 */
	private Integer points;
	/**
	 * 会员等级编号
	 */
	private Integer gradeId;
	/**
	 * 会员余额
	 */
	private Double balance;
	/**
	 * 会员状态标记，用于标记会员与否，1为是，0为否
	 */
	private Integer status;

}
