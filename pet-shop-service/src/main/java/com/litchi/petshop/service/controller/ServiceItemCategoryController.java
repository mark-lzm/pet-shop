package com.litchi.petshop.service.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.service.ServiceItemSubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.service.entity.ServiceItemCategoryEntity;
import com.litchi.petshop.service.service.ServiceItemCategoryService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录服务的大类。如：美容类、洗澡类等。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@RestController
@RequestMapping("service/serviceitemcategory")
public class ServiceItemCategoryController {
    @Autowired
    private ServiceItemCategoryService serviceItemCategoryService;

    @Autowired
    private ServiceItemSubclassService serviceItemSubclassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("service:serviceitemcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceItemCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{serviceItemId}")
    //@RequiresPermissions("service:serviceitemcategory:info")
    public R info(@PathVariable("serviceItemId") Integer serviceItemId){
		ServiceItemCategoryEntity serviceItemCategory = serviceItemCategoryService.getById(serviceItemId);

        return R.ok().put("serviceItemCategory", serviceItemCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("service:serviceitemcategory:save")
    public R save(@RequestBody ServiceItemCategoryEntity serviceItemCategory){
		serviceItemCategoryService.save(serviceItemCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("service:serviceitemcategory:update")
    public R update(@RequestBody ServiceItemCategoryEntity serviceItemCategory){
		serviceItemCategoryService.updateById(serviceItemCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("service:serviceitemcategory:delete")
    public R delete(@RequestBody Integer[] serviceItemIds){
        // 判断是否被服务子类关联
        List<ServiceItemSubclassEntity> serviceItemSubclassEntities = serviceItemSubclassService.list();
        Set<Integer> relatedAllIds = serviceItemSubclassEntities.stream().map(ServiceItemSubclassEntity::getServiceItemId).collect(Collectors.toSet());
        List<Integer> relatedIds = new ArrayList<>();
        if (relatedAllIds.size() != 0) {
            for (Integer id : serviceItemIds) {
                if (relatedAllIds.contains(id)) {
                    //被关联到的id
                    relatedIds.add(id);
                }
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被serviceItemSubclass表关联，无法删除");
        }

        serviceItemCategoryService.removeByIds(Arrays.asList(serviceItemIds));

        return R.ok();
    }

    @RequestMapping("/listServiceItem")
    public R listServiceItem(@RequestParam Map<String, Object> params) {
        PageUtils page = serviceItemCategoryService.queryServiceItemPage(params);

        return R.ok().put("page", page);
    }
}
