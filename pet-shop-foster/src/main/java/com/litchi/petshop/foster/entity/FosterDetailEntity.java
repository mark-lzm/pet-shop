package com.litchi.petshop.foster.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 由于一个顾客可以寄养多只宠物，不同的宠物及不同的服务价格，必须通过详情表来体现。
 * 
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@Data
@TableName("fms_foster_detail")
public class FosterDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 寄养详情编号
	 */
	@TableId
	private Integer fosterDetailId;
	/**
	 * 寄养编号
	 */
	private Integer fosterId;
	/**
	 * 宠物名称
	 */
	private String petName;
	/**
	 * 宠物品种编号
	 */
	private Integer petBreedId;
	/**
	 * 宠物性别
	 */
	private String petSex;
	/**
	 * 宠物重量
	 */
	private Double petWeight;
	/**
	 * 宠物毛色编号
	 */
	private Integer petCoatColorId;
	/**
	 * 寄养标准编号
	 */
	private Integer fosterStandardId;
	/**
	 * 寄养开始时间
	 */
	private Date fosterCreateTime;
	/**
	 * 计划领走时间
	 */
	private Date fosterPlanTime;
	/**
	 * 实际领走时间
	 */
	private Date fosterEndTime;
	/**
	 * 预交押金
	 */
	private Double cashPledge;
	/**
	 * 实收金额
	 */
	private Double amountReceived;
	/**
	 * 寄养状态标记，1为寄养中，0为已取走
	 */
	private Integer fosterStatus;

}
