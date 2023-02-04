package com.litchi.petshop.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.core.injector.methods.Insert;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
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
    @NotBlank(message = "会员姓名不能为空")
    private String name;
    /**
     * 会员电话
     */
    @NotBlank(message = "手机号码不能为空", groups = {Insert.class})
    @NotNull(message = "手机号不能为空", groups = {Insert.class})
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;
    /**
     * 会员地址
     */
    @NotBlank(message = "会员地址不能为空")
    private String address;
    /**
     * 会员性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;
    /**
     * 会员可用积分
     */
    @NotNull(message = "会员可用积分不能为空")
    private Integer availablePoints;
    /**
     * 会员积分
     */
    @NotNull(message = "会员积分不能为空")
    private Integer points;
    /**
     * 会员等级编号
     */
    @NotNull(message = "会员等级编号不能为空")
    private Integer gradeId;
    /**
     * 会员余额
     */
    @NotNull(message = "会员余额不能为空")
    private Double balance;
    /**
     * 会员状态标记，用于标记会员与否，1为是，0为否
     */
    @NotNull(message = "会员状态标记不能为空")
    private Integer status;

}
