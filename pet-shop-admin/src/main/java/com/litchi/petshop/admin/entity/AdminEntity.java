package com.litchi.petshop.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记载管理员的相关信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@Data
@TableName("sys_admin")
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 管理员编号
	 */
	@TableId
	private Integer adminId;
	/**
	 * 管理姓名
	 */
	private String adminName;
	/**
	 * 管理密码
	 */
	private String adminPassword;
	/**
	 * 管理员是否可用标记，1为可用，0为不可用
	 */
	private Integer availableTag;

}
