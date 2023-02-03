package com.litchi.petshop.admin.entity;

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
 * @date 2023-02-03 09:49:19
 */
@Data
@TableName("sys_employee_role")
public class EmployeeRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 员工权限编号
	 */
	@TableId
	private Integer roleId;
	/**
	 * 员工编号
	 */
	private Integer employeeId;
	/**
	 * 商品信息设置，1为有权限，0为无权限
	 */
	private Integer productInfoSetting;
	/**
	 * 会员信息设置，1为有权限，0为无权限
	 */
	private Integer memberInfoSetting;
	/**
	 * 宠物服务项目设置，1为有权限，0为无权限
	 */
	private Integer petServiceItemSetting;
	/**
	 * 寄养标准设置，1为有权限，0为无权限
	 */
	private Integer fosterStandardSetting;
	/**
	 * 统计分析，1为有权限，0为无权限
	 */
	private Integer statisticalAnalysis;

}