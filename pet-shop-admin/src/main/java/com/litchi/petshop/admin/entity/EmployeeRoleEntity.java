package com.litchi.petshop.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用于记录员工的操作权限
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
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
	 * 商品信息设置
	 */
	private String productInfoSetting;
	/**
	 * 员工信息设置
	 */
	private String memberInfoSetting;
	/**
	 * 宠物服务项目设置
	 */
	private String petServiceItemSetting;
	/**
	 * 寄养标准设置
	 */
	private String fosterStandardSetting;
	/**
	 * 统计分析
	 */
	private String statisticalAnalysis;

}
