package com.litchi.petshop.member.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.petshop.member.dao.MemberGradeDao;
import com.litchi.petshop.member.entity.MemberGradeEntity;
import com.litchi.petshop.member.vo.MemberAndGradeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.member.dao.MemberDao;
import com.litchi.petshop.member.entity.MemberEntity;
import com.litchi.petshop.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    MemberGradeDao memberGradeDao;

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

        Page<MemberAndGradeVo> page = new Page<>(pageIndex, limit);
        int totalSize = memberAndGradeVoList.size();
        // 若找不到数据，直接返回page
        if (totalSize == 0) {
            return new PageUtils(page);
        }
        int startIndex = (pageIndex - 1) * limit;
        int endIndex = pageIndex * limit;
        //总页数
        int pageCount = 0;
        //通过 % 判断是否给总页数加1
        int num = totalSize % limit;
        pageCount = num == 0 ? totalSize / limit : totalSize / limit + 1;
        //如果当前页是最后一页的话 要包含集合的最后一条数据，因为sublist方法本身结束的下标是不包含当前元素的
        if (pageIndex == pageCount) {
            endIndex = totalSize;
        }

        List<MemberAndGradeVo> memberAndGradeVoSubList = memberAndGradeVoList.subList(startIndex, endIndex);
        page.setRecords(memberAndGradeVoSubList);
        page.setTotal(totalSize);

        return new PageUtils(page);
    }

}