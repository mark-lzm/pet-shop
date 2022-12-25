package com.litchi.petshop.service.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.service.entity.ServiceEntity;
import com.litchi.petshop.service.service.ServiceService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录宠物服务的主要信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@RestController
@RequestMapping("service/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("service:service:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("service:service:info")
    public R info(@PathVariable("id") Integer id){
		ServiceEntity service = serviceService.getById(id);

        return R.ok().put("service", service);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("service:service:save")
    public R save(@RequestBody ServiceEntity service){
		serviceService.save(service);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("service:service:update")
    public R update(@RequestBody ServiceEntity service){
		serviceService.updateById(service);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("service:service:delete")
    public R delete(@RequestBody Integer[] ids){
		serviceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
