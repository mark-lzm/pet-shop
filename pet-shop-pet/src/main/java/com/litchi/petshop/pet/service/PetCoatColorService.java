package com.litchi.petshop.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.pet.entity.PetCoatColorEntity;

import java.util.Map;

/**
 * 用于记录宠物毛色分类，方便宠物登记时录入信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
public interface PetCoatColorService extends IService<PetCoatColorEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

