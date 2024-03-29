package com.litchi.petshop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.member.entity.MemberGradeEntity;

import java.util.Map;

/**
 * 
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:51:15
 */
public interface MemberGradeService extends IService<MemberGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateMemberGradeAndSetMemberGradeId();
}

