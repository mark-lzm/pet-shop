package com.litchi.petshop.pet.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.pet.feign.FosterFeignService;
import com.litchi.petshop.pet.feign.ServiceFeignService;
import com.litchi.pojo.foster.dto.FosterDto;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.pet.dto.PetDto;
import com.litchi.pojo.service.dto.ServiceDetailDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.pet.entity.PetEntity;
import com.litchi.petshop.pet.service.PetService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * 用于记载会员的宠物信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:22:25
 */
@RestController
@RequestMapping("pet/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private ServiceFeignService serviceFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("pet:pet:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = petService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listAllPet")
    public List<PetDto> listAlPet(){
        return petService.listAlPet();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("pet:pet:info")
    public R info(@PathVariable("id") Integer id) {
        PetEntity pet = petService.getById(id);

        return R.ok().put("pet", pet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("pet:pet:save")
    public R save(@RequestBody PetEntity pet) {
        petService.save(pet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("pet:pet:update")
    public R update(@RequestBody PetEntity pet) {
        petService.updateById(pet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("pet:pet:delete")
    public R delete(@RequestBody Integer[] ids) {

        List<ServiceDetailDto> serviceDetailDtos = serviceFeignService.listAllServiceDetail();

        Set<Integer> relatedAllIds = serviceDetailDtos.stream().map(ServiceDetailDto::getPetId).collect(Collectors.toSet());

        List<Integer> relatedIds = new ArrayList<>();

        for (Integer petId : ids) {
            if (relatedAllIds.contains(petId)) {
                relatedIds.add(petId);
            }
        }

        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被servicedetail表关联，无法删除");
        }
        petService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/petByMember")
    public R petByMember(Integer memberId) {
        List<PetEntity> pets = petService.petByMember(memberId);
        return R.ok().put("pets", pets);
    }

    @RequestMapping("/selectByPetId/{id}")
    public PetDto selectByPetId(@PathVariable("id") Integer id) {
        PetEntity pet = petService.getById(id);
        PetDto petDto = new PetDto();
        BeanUtils.copyProperties(pet, petDto);
        return petDto;
    }
}
