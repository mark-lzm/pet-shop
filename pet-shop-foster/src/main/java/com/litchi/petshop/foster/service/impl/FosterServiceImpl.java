package com.litchi.petshop.foster.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.pojo.foster.dto.FosterDto;
import com.litchi.pojo.member.dto.MemberDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterDao;
import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.service.FosterService;
import org.springframework.transaction.annotation.Transactional;


@Service("fosterService")
public class FosterServiceImpl extends ServiceImpl<FosterDao, FosterEntity> implements FosterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<FosterEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
//                obj.eq("id", key).or().eq("member_id", key).or().like("member_name", key);
                obj.like("member_name", key);
            });
        }

        List<FosterEntity> entities = this.list(wrapper);

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, entities);
        }

        IPage<FosterEntity> page = this.page(
                new Query<FosterEntity>().getPage(params),
                new QueryWrapper<FosterEntity>()
        );
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateByMemberId(MemberDto dto) {
        Integer memberId = dto.getId();
        QueryWrapper<FosterEntity> wrapper = new QueryWrapper<FosterEntity>().eq("member_id", memberId);
        FosterEntity foster = this.baseMapper.selectOne(wrapper);
        if (foster != null) {
            foster.setMemberName(dto.getName());
            foster.setMemberPhone(dto.getPhone());
            this.updateById(foster);
        }
    }

    @Override
    public List<MemberDto> listAllMember() {
        List<MemberDto> result = new ArrayList<>();
        List<FosterEntity> list = this.list();
        for (FosterEntity foster : list) {
            MemberDto dto = new MemberDto();
            dto.setId(foster.getMemberId());
            dto.setName(foster.getMemberName());
            dto.setPhone(foster.getMemberPhone());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<FosterDto> listAllFoster() {
        List<FosterDto> result = new ArrayList<>();
        List<FosterEntity> list = this.list();
        for (FosterEntity foster : list) {
            FosterDto dto = new FosterDto();
            BeanUtils.copyProperties(foster, dto);
            result.add(dto);
        }
        return result;
    }

}