package com.litchi.petshop.foster.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author Mark
 * 2023/2/7 15:56
 */
@Data
public class FosterDetailVo {
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
     * 寄养人
     */
    private String fosterName;
    /**
     * 宠物名称
     */
    private String petName;
    /**
     * 宠物品种编号
     */
    private Integer petBreedId;
    /**
     * 宠物品种
     */
    private String petBreedName;
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
     * 宠物毛色
     */
    private String petCoatColorName;
    /**
     * 寄养标准编号
     */
    private Integer fosterStandardId;
    /**
     * 寄养标准
     */
    private String fosterStandardName;
    /**
     * 寄养开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date fosterCreateTime;
    /**
     * 计划领走时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date fosterPlanTime;
    /**
     * 实际领走时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
