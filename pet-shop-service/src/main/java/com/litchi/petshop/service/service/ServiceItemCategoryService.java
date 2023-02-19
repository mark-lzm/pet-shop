package com.litchi.petshop.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;

import java.util.Map;

/**
 * 用于记录服务的大类。如：美容类、洗澡类等。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
public interface ServiceItemCategoryService extends IService<ServiceItemCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryServiceItemPage(Map<String, Object> params);
}

