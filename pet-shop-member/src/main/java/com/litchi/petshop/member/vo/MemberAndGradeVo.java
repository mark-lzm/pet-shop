package com.litchi.petshop.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author Mark
 * 2023/1/13 23:37
 */
@Data
public class MemberAndGradeVo {
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
     * 会员等级
     */
    private String gradeName;
    /**
     * 会员余额
     */
    private Double balance;
    /**
     * 会员状态标记，用于标记会员与否，1为是，0为否
     */
    private Integer status;
}
