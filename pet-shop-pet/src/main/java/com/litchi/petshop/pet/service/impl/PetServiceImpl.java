package com.litchi.petshop.pet.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.common.utils.R;
import com.litchi.petshop.pet.entity.PetBreedEntity;
import com.litchi.petshop.pet.entity.PetCoatColorEntity;
import com.litchi.petshop.pet.feign.MemberFeignService;
import com.litchi.petshop.pet.service.PetBreedService;
import com.litchi.petshop.pet.service.PetCoatColorService;
import com.litchi.petshop.pet.vo.PetVo;
import com.litchi.pojo.dto.MemberDto;
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

import com.litchi.petshop.pet.dao.PetDao;
import com.litchi.petshop.pet.entity.PetEntity;
import com.litchi.petshop.pet.service.PetService;
import org.springframework.transaction.annotation.Transactional;


@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetDao, PetEntity> implements PetService {

    @Autowired
    PetBreedService petBreedService;

    @Autowired
    PetCoatColorService petCoatColorService;

    @Autowired
    MemberFeignService memberFeignService;

    @Transactional
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<PetVo> voList = new ArrayList<>();

        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<PetEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().eq("member_id", key).or().like("name", key);
            });
        }

        List<PetEntity> entities = this.list(wrapper);
        for (PetEntity entity : entities) {
            PetVo petVo = new PetVo();
            BeanUtils.copyProperties(entity,petVo);

            MemberDto dto = memberFeignService.MemberById(entity.getMemberId());
            petVo.setMemberName(dto.getName());


            PetBreedEntity petBreed = petBreedService.getById(entity.getBreedId());
            petVo.setBreedName(petBreed.getBreedName());

            PetCoatColorEntity petCoatColor = petCoatColorService.getById(entity.getCoatColorId());
            petVo.setCoatColorName(petCoatColor.getCoatColorName());

            voList.add(petVo);
        }

        return PetPageUtils.getPageUtils(pageIndex, limit, voList);
    }

}