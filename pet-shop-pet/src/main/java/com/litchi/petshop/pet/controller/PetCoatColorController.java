package com.litchi.petshop.pet.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.pet.entity.PetBreedEntity;
import com.litchi.petshop.pet.entity.PetEntity;
import com.litchi.petshop.pet.service.PetService;
import com.litchi.pojo.pet.dto.PetCoatColorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.pet.entity.PetCoatColorEntity;
import com.litchi.petshop.pet.service.PetCoatColorService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * 用于记录宠物毛色分类，方便宠物登记时录入信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@RestController
@RequestMapping("pet/petcoatcolor")
public class PetCoatColorController {
    @Autowired
    private PetCoatColorService petCoatColorService;

    @Autowired
    PetService petService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("pet:petcoatcolor:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = petCoatColorService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listColor")
    public R listColor(@RequestParam Map<String, Object> params) {
        PageUtils page = petCoatColorService.queryColorPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{coatColorId}")
    //@RequiresPermissions("pet:petcoatcolor:info")
    public R info(@PathVariable("coatColorId") Integer coatColorId) {
        PetCoatColorEntity petCoatColor = petCoatColorService.getById(coatColorId);

        return R.ok().put("petCoatColor", petCoatColor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("pet:petcoatcolor:save")
    public R save(@RequestBody PetCoatColorEntity petCoatColor) {
        petCoatColorService.save(petCoatColor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("pet:petcoatcolor:update")
    public R update(@RequestBody PetCoatColorEntity petCoatColor) {
        petCoatColorService.updateById(petCoatColor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("pet:petcoatcolor:delete")
    public R delete(@RequestBody Integer[] coatColorIds) {

        // 查看毛色是否被pet关联到，被关联到则无法删除
        List<PetEntity> petEntityList = petService.list();
        //所有被关联到的colorId
        Set<Integer> relatedAllIds = petEntityList.stream().map(PetEntity::getCoatColorId).collect(Collectors.toSet());
//        Set<Integer> relatedAllIds = new HashSet<>();
//        for (PetEntity petEntity : petEntityList) {
//            relatedAllIds.add(petEntity.getCoatColorId());
//        }

        // 要删除的ids中，被关联的colorId
        List<Integer> relatedIds = new ArrayList<>();

        for (Integer coatColorId : coatColorIds) {
            if (relatedAllIds.contains(coatColorId)) {
                //被关联到的colorId
                relatedIds.add(coatColorId);
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被pet关联，无法删除");
        }
        petCoatColorService.removeByIds(Arrays.asList(coatColorIds));
        return R.ok();
    }


    /**
     * 根据coatColorId获取对象
     */
    @RequestMapping("/selectByColorId/{coatColorId}")
    public PetCoatColorDto selectByColorId(@PathVariable("coatColorId") Integer coatColorId) {
        PetCoatColorEntity petCoatColor = petCoatColorService.getById(coatColorId);
        PetCoatColorDto petCoatColorDto = new PetCoatColorDto();
        BeanUtils.copyProperties(petCoatColor, petCoatColorDto);
        return petCoatColorDto;
    }
}
