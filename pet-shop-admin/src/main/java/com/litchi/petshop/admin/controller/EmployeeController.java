package com.litchi.petshop.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.admin.entity.EmployeeEntity;
import com.litchi.petshop.admin.service.EmployeeService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记载员工的信息
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:49:36
 */
@RestController
@RequestMapping("admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("admin:employee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = employeeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("admin:employee:info")
    public R info(@PathVariable("id") Integer id){
		EmployeeEntity employee = employeeService.getById(id);

        return R.ok().put("employee", employee);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:employee:save")
    public R save(@RequestBody EmployeeEntity employee){
		employeeService.save(employee);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:employee:update")
    public R update(@RequestBody EmployeeEntity employee){
		employeeService.updateById(employee);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:employee:delete")
    public R delete(@RequestBody Integer[] ids){
		employeeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
