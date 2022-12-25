package com.litchi.petshop.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.member.dao.MemberGradeDao;
import com.litchi.petshop.member.entity.MemberGradeEntity;
import com.litchi.petshop.member.service.MemberGradeService;


@Service("memberGradeService")
public class MemberGradeServiceImpl extends ServiceImpl<MemberGradeDao, MemberGradeEntity> implements MemberGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberGradeEntity> page = this.page(
                new Query<MemberGradeEntity>().getPage(params),
                new QueryWrapper<MemberGradeEntity>()
        );

        return new PageUtils(page);
    }

}