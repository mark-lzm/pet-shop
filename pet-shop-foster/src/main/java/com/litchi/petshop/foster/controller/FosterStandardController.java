package com.litchi.petshop.foster.controller;

import java.util.Arrays;
import java.util.Map;

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
		fosterStandardService.removeByIds(Arrays.asList(fosterStandardIds));

        return R.ok();
    }

}
