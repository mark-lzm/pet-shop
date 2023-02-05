package com.litchi.petshop.pet.controller;

import java.util.*;

import com.litchi.petshop.pet.entity.PetEntity;
import com.litchi.petshop.pet.service.PetService;
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

    @Autowired
    PetService petService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("pet:petbreed:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = petBreedService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listBreed")
    public R listBreed(@RequestParam Map<String, Object> params){
        PageUtils page = petBreedService.queryBreedPage(params);

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

        // 查看毛色是否被pet关联到，被关联到则无法删除
        List<PetEntity> petEntityList = petService.list();
        //所有被关联到的breedId
        Set<Integer> relatedAllIds = new HashSet<>();
        for (PetEntity petEntity : petEntityList) {
            relatedAllIds.add(petEntity.getBreedId());
        }

        // 没被关联的colorId
        List<Integer> resultIds = new ArrayList<>();
        // 被关联的colorId
        List<Integer> relatedIds = new ArrayList<>();

        for (Integer breedId : breedIds) {
            if (!relatedAllIds.contains(breedId)) {
                resultIds.add(breedId);
            } else {
                //被关联到的breedId
                relatedIds.add(breedId);
            }
        }

        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被pet关联，无法删除");
        }
        if (resultIds.size() != 0) {
            petBreedService.removeByIds(Arrays.asList(breedIds));
        }
        return R.ok();
    }

}
