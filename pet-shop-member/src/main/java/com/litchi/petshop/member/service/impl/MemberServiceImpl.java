package com.litchi.petshop.member.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.member.bo.MemberBalanceBo;
import com.litchi.petshop.member.dao.MemberGradeDao;
import com.litchi.petshop.member.entity.MemberGradeEntity;
import com.litchi.petshop.member.service.MemberGradeService;
import com.litchi.petshop.member.vo.MemberAndGradeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.member.dao.MemberDao;
import com.litchi.petshop.member.entity.MemberEntity;
import com.litchi.petshop.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    MemberGradeDao memberGradeDao;

    @Autowired
    MemberGradeService memberGradeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> {
                obj.eq("id", key).or().like("name", key);
            });
        }

        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public PageUtils queryPageAndGrade(Map<String, Object> params) {

        List<MemberAndGradeVo> memberAndGradeVoList = new ArrayList<>();

        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> {
                obj.eq("id", key).or().like("name", key);
            });
        }

        List<MemberEntity> entities = this.list(wrapper);
        for (MemberEntity entity : entities) {
            MemberAndGradeVo vo = new MemberAndGradeVo();
            BeanUtils.copyProperties(entity, vo);

            Integer gradeId = entity.getGradeId();
            MemberGradeEntity memberGradeEntity = memberGradeDao.selectById(gradeId);
            vo.setGradeName(memberGradeEntity.getGradeName());

            memberAndGradeVoList.add(vo);
        }

        return PetPageUtils.getPageUtils(pageIndex, limit, memberAndGradeVoList);
    }

    /**
     * 保存并且设置会员等级
     * @param member
     */
    @Transactional
    @Override
    public void saveAndGrade(MemberEntity member) {
        int balance = member.getBalance().intValue();
        member.setPoints(balance);
        member.setAvailablePoints(balance);

        //根据积分设置会员等级
        updateGradeIdByPoints(member);

        this.save(member);
    }

    /**
     * 根据积分设置会员等级
     * @param member
     */
    @Transactional
    @Override
    public void updateGradeIdByPoints(MemberEntity member) {
        //根据积分设置会员等级
        List<MemberGradeEntity> memberGradeEntities = memberGradeService.list();

        //根据gradePointValue进行排序，获取小于member的point值的MemberGradeEntity对象集合
        List<MemberGradeEntity> collect = memberGradeEntities.stream()
                .sorted(Comparator.comparing(MemberGradeEntity::getGradePointValue))
                .filter(memberGradeEntity ->
                        memberGradeEntity.getGradePointValue() <= member.getPoints()
                ).collect(Collectors.toList());
        //利用skip获取集合中最后一个
        Optional<MemberGradeEntity> memberGradeEntity = collect.stream().skip(collect.size() - 1).collect(Collectors.toList()).stream().findFirst();
        member.setGradeId(memberGradeEntity.get().getGradeId());
    }


    /**
     * 充值
     * @param bo
     */
    @Transactional
    @Override
    public void charge(MemberBalanceBo bo) {
        //根据id获取member
        MemberEntity member = this.getById(bo.getId());
        //充值
        double balance = bo.getChargeValue() + member.getBalance();
        member.setBalance(balance);

        //积分更改
        member.setPoints(bo.getChargeValue() + member.getPoints());
        member.setAvailablePoints(bo.getChargeValue() + member.getAvailablePoints());

        //根据积分设置会员等级
        updateGradeIdByPoints(member);

        this.updateById(member);
    }

    /**
     * 修改会员等级所需积分值，需要重新将会员信息的等级重新设置
     */
    @Transactional
    @Override
    public void updateMemberGradeId() {
        List<MemberEntity> list = this.list();
        for (MemberEntity member : list) {
            this.updateGradeIdByPoints(member);
            this.updateById(member);
        }
    }

}