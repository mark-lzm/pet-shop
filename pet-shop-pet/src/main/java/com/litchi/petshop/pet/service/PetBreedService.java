package com.litchi.petshop.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.pet.entity.PetBreedEntity;

import java.util.Map;

/**
 * 用于记录宠物种类的信息，方便操作录入
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
public interface PetBreedService extends IService<PetBreedEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBreedPage(Map<String, Object> params);
}

