package com.litchi.petshop.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.pet.entity.PetEntity;

import java.util.Map;

/**
 * 用于记载会员的宠物信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
public interface PetService extends IService<PetEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

