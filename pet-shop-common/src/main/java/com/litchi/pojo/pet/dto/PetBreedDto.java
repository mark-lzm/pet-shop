package com.litchi.pojo.pet.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author Mark
 * 2023/2/7 16:22
 */
@Data
public class PetBreedDto {
    /**
     * 宠物品种编号
     */
    private Integer breedId;
    /**
     * 宠物品种名称
     */
    private String breedName;
}
