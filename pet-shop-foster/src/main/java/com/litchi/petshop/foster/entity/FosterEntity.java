package com.litchi.petshop.foster.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录寄养服务时会员及非会员的基本。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Data
@TableName("fms_foster")
public class FosterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 寄养编号
	 */
	@TableId
	private Integer id;
	/**
	 * 会员编号
	 */
	private Integer memberId;
	/**
	 * 姓名
	 */
	private String memberName;
	/**
	 * 电话
	 */
	private String memberPhone;
	/**
	 * 备注
	 */
	private String remarks;

}
