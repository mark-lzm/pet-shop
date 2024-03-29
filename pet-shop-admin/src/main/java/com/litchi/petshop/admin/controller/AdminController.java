package com.litchi.petshop.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.admin.entity.AdminEntity;
import com.litchi.petshop.admin.service.AdminService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记载管理员的相关信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@RestController
@RequestMapping("admin/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("admin:admin:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{adminId}")
    //@RequiresPermissions("admin:admin:info")
    public R info(@PathVariable("adminId") Integer adminId){
		AdminEntity admin = adminService.getById(adminId);

        return R.ok().put("admin", admin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:admin:save")
    public R save(@RequestBody AdminEntity admin){
		adminService.save(admin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:admin:update")
    public R update(@RequestBody AdminEntity admin){
		adminService.updateById(admin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:admin:delete")
    public R delete(@RequestBody Integer[] adminIds){
		adminService.removeByIds(Arrays.asList(adminIds));

        return R.ok();
    }

}
