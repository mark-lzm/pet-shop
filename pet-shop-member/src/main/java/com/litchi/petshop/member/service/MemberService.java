package com.litchi.petshop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.member.bo.MemberBalanceBo;
import com.litchi.petshop.member.entity.MemberEntity;

import java.util.Map;

/**
 * 
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:51:15
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageAndGrade(Map<String, Object> params);

    void saveAndGrade(MemberEntity member);

    void charge(MemberBalanceBo bo);

    public void updateGradeIdByPoints(MemberEntity member);
    /**
     * 修改会员等级所需积分值，需要重新将会员信息的等级重新设置
     */
    void updateMemberGradeId();
}

