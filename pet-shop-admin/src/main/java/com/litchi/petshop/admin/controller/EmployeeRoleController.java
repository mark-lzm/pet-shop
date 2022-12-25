package com.litchi.petshop.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.admin.entity.EmployeeRoleEntity;
import com.litchi.petshop.admin.service.EmployeeRoleService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录员工的操作权限
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@RestController
@RequestMapping("admin/employeerole")
public class EmployeeRoleController {
    @Autowired
    private EmployeeRoleService employeeRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("admin:employeerole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = employeeRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    //@RequiresPermissions("admin:employeerole:info")
    public R info(@PathVariable("roleId") Integer roleId){
		EmployeeRoleEntity employeeRole = employeeRoleService.getById(roleId);

        return R.ok().put("employeeRole", employeeRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:employeerole:save")
    public R save(@RequestBody EmployeeRoleEntity employeeRole){
		employeeRoleService.save(employeeRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:employeerole:update")
    public R update(@RequestBody EmployeeRoleEntity employeeRole){
		employeeRoleService.updateById(employeeRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:employeerole:delete")
    public R delete(@RequestBody Integer[] roleIds){
		employeeRoleService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
