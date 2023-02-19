package com.litchi.pojo.pet.dto;

import lombok.Data;

/**
 * @Author Mark
 * 2023/2/14 23:57
 */
@Data
public class PetDto {

    private Integer id;
    /**
     * 会员编号
     */
    private Integer memberId;
    /**
     * 宠物名称
     */
    private String name;
    /**
     * 宠物品种编号
     */
    private Integer breedId;
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
    private Integer coatColorId;
    /**
     * 宠物照片
     */
    private String photo;
    /**
     * 宠物状态标记，1为在店，0为不在店
     */
    private Integer status;
}
