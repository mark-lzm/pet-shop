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
@TableName("ums_member_grade")
public class MemberGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员等级编号
	 */
	@TableId
	private Integer gradeId;
	/**
	 * 会员等级名称
	 */
	private String gradeName;
	/**
	 * 会员折扣
	 */
	private Double discount;
	/**
	 * 等级所需积分值
	 */
	private Integer gradePointValue;

}
