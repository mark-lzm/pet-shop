package com.litchi.petshop.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记载员工的信息
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@Data
@TableName("sys_employee")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 员工编号
	 */
	@TableId
	private Integer id;
	/**
	 * 员工登录名
	 */
	private String username;
	/**
	 * 员工密码
	 */
	private String password;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 * 员工电话号
	 */
	private String phone;
	/**
	 * 员工身份证号
	 */
	private String idCard;
	/**
	 * 员工状态，标记在职与否
	 */
	private Integer status;

}
