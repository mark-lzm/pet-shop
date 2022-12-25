package com.litchi.petshop.foster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.foster.entity.FosterStandardEntity;

import java.util.Map;

/**
 * 用于记录不同大小的宠物寄养时不同的价格信息。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
public interface FosterStandardService extends IService<FosterStandardEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

