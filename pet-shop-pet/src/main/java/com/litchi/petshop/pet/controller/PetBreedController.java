package com.litchi.petshop.pet.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.pet.entity.PetBreedEntity;
import com.litchi.petshop.pet.service.PetBreedService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录宠物种类的信息，方便操作录入
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@RestController
@RequestMapping("pet/petbreed")
public class PetBreedController {
    @Autowired
    private PetBreedService petBreedService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("pet:petbreed:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = petBreedService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{breedId}")
    //@RequiresPermissions("pet:petbreed:info")
    public R info(@PathVariable("breedId") Integer breedId){
		PetBreedEntity petBreed = petBreedService.getById(breedId);

        return R.ok().put("petBreed", petBreed);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("pet:petbreed:save")
    public R save(@RequestBody PetBreedEntity petBreed){
		petBreedService.save(petBreed);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("pet:petbreed:update")
    public R update(@RequestBody PetBreedEntity petBreed){
		petBreedService.updateById(petBreed);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("pet:petbreed:delete")
    public R delete(@RequestBody Integer[] breedIds){
		petBreedService.removeByIds(Arrays.asList(breedIds));

        return R.ok();
    }

}
