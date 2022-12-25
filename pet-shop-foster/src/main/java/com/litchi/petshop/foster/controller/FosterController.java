package com.litchi.petshop.foster.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.service.FosterService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 记录寄养服务时会员及非会员的基本。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@RestController
@RequestMapping("foster/foster")
public class FosterController {
    @Autowired
    private FosterService fosterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("foster:foster:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fosterService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("foster:foster:info")
    public R info(@PathVariable("id") Integer id){
		FosterEntity foster = fosterService.getById(id);

        return R.ok().put("foster", foster);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("foster:foster:save")
    public R save(@RequestBody FosterEntity foster){
		fosterService.save(foster);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("foster:foster:update")
    public R update(@RequestBody FosterEntity foster){
		fosterService.updateById(foster);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("foster:foster:delete")
    public R delete(@RequestBody Integer[] ids){
		fosterService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
