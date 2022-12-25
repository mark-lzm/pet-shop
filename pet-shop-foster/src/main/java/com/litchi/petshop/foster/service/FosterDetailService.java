package com.litchi.petshop.foster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.foster.entity.FosterDetailEntity;

import java.util.Map;

/**
 * 由于一个顾客可以寄养多只宠物，不同的宠物及不同的服务价格，必须通过详情表来体现。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
public interface FosterDetailService extends IService<FosterDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

