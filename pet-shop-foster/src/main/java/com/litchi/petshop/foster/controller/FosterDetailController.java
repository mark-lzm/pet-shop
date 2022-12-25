package com.litchi.petshop.foster.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 由于一个顾客可以寄养多只宠物，不同的宠物及不同的服务价格，必须通过详情表来体现。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@RestController
@RequestMapping("foster/fosterdetail")
public class FosterDetailController {
    @Autowired
    private FosterDetailService fosterDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("foster:fosterdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fosterDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fosterDetailId}")
    //@RequiresPermissions("foster:fosterdetail:info")
    public R info(@PathVariable("fosterDetailId") Integer fosterDetailId){
		FosterDetailEntity fosterDetail = fosterDetailService.getById(fosterDetailId);

        return R.ok().put("fosterDetail", fosterDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("foster:fosterdetail:save")
    public R save(@RequestBody FosterDetailEntity fosterDetail){
		fosterDetailService.save(fosterDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("foster:fosterdetail:update")
    public R update(@RequestBody FosterDetailEntity fosterDetail){
		fosterDetailService.updateById(fosterDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("foster:fosterdetail:delete")
    public R delete(@RequestBody Integer[] fosterDetailIds){
		fosterDetailService.removeByIds(Arrays.asList(fosterDetailIds));

        return R.ok();
    }

}
