package com.litchi.petshop.member.service.impl;

import com.litchi.petshop.member.entity.MemberEntity;
import com.litchi.petshop.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.member.dao.MemberGradeDao;
import com.litchi.petshop.member.entity.MemberGradeEntity;
import com.litchi.petshop.member.service.MemberGradeService;
import org.springframework.transaction.annotation.Transactional;


@Service("memberGradeService")
public class MemberGradeServiceImpl extends ServiceImpl<MemberGradeDao, MemberGradeEntity> implements MemberGradeService {

    @Autowired
    MemberService memberService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberGradeEntity> page = this.page(
                new Query<MemberGradeEntity>().getPage(params),
                new QueryWrapper<MemberGradeEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateMemberGradeAndSetMemberGradeId() {
        //修改会员等级所需积分值，需要重新将会员信息的等级重新设置
        List<MemberEntity> list = memberService.list();
        for (MemberEntity member : list) {
            memberService.updateGradeIdByPoints(member);
            memberService.updateById(member);
        }
    }

}