package com.litchi.petshop.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.service.entity.ServiceEntity;

import java.util.Map;

/**
 * 用于记录宠物服务的主要信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
public interface ServiceService extends IService<ServiceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

