package com.litchi.pojo.pet.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author Mark
 * 2023/2/7 16:21
 */
@Data
public class PetCoatColorDto {
    /**
     * 宠物毛色编号
     */
    private Integer coatColorId;
    /**
     * 宠物毛色名称
     */
    private String coatColorName;
}
