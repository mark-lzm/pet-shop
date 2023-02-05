package com.litchi.petshop.pet.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author Mark
 * 2023/2/5 11:00
 */
@Data
public class PetVo {
    /**
     * 宠物编号
     */
    @TableId
    private Integer id;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 宠物名称
     */
    private String name;
    /**
     * 宠物品种
     */
    private String breedName;
    /**
     * 宠物性别
     */
    private String sex;
    /**
     * 宠物重量
     */
    private Double weight;
    /**
     * 宠物毛色编号
     */
    private String coatColorName;
    /**
     * 宠物照片
     */
    private String photo;
    /**
     * 宠物状态标记，1为在店，0为不在店
     */
    private Integer status;
}
