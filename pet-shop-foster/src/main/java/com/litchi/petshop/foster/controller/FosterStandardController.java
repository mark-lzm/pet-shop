package com.litchi.petshop.foster.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.foster.entity.FosterStandardEntity;
import com.litchi.petshop.foster.service.FosterStandardService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录不同大小的宠物寄养时不同的价格信息。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@RestController
@RequestMapping("foster/fosterstandard")
public class FosterStandardController {
    @Autowired
    private FosterStandardService fosterStandardService;

    @Autowired
    private FosterDetailService fosterDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("foster:fosterstandard:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fosterStandardService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fosterStandardId}")
    //@RequiresPermissions("foster:fosterstandard:info")
    public R info(@PathVariable("fosterStandardId") Integer fosterStandardId){
		FosterStandardEntity fosterStandard = fosterStandardService.getById(fosterStandardId);

        return R.ok().put("fosterStandard", fosterStandard);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("foster:fosterstandard:save")
    public R save(@RequestBody FosterStandardEntity fosterStandard){
		fosterStandardService.save(fosterStandard);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("foster:fosterstandard:update")
    public R update(@RequestBody FosterStandardEntity fosterStandard){
		fosterStandardService.updateById(fosterStandard);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("foster:fosterstandard:delete")
    public R delete(@RequestBody Integer[] fosterStandardIds){
        List<FosterDetailEntity> fosterDetailEntities = fosterDetailService.list();
        Set<Integer> relatedAllIds = fosterDetailEntities.stream().map(FosterDetailEntity::getFosterStandardId).collect(Collectors.toSet());
        List<Integer> relatedIds = new ArrayList<>();
        for (Integer id : fosterStandardIds) {
            if (relatedAllIds.contains(id)) {
                //要删除的ids中，被关联到的id
                relatedIds.add(id);
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被fosterDetail表关联，无法删除");
        }
        fosterStandardService.removeByIds(Arrays.asList(fosterStandardIds));

        return R.ok();
    }

}
