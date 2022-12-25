package com.litchi.petshop.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;

import java.util.Map;

/**
 * 用于记录各个类别的宠物服务价格。宠物服务项目的分类设置，可以方便用户操作。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
public interface ServiceItemSubclassService extends IService<ServiceItemSubclassEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

