package com.litchi.petshop.service.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.litchi.petshop.service.service.ServiceDetailService;
import com.litchi.pojo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.service.ServiceItemSubclassService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录各个类别的宠物服务价格。宠物服务项目的分类设置，可以方便用户操作。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@RestController
@RequestMapping("service/serviceitemsubclass")
public class ServiceItemSubclassController {
    @Autowired
    private ServiceItemSubclassService serviceItemSubclassService;

    @Autowired
    private ServiceDetailService serviceDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("service:serviceitemsubclass:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceItemSubclassService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{serviceItemSubId}")
    //@RequiresPermissions("service:serviceitemsubclass:info")
    public R info(@PathVariable("serviceItemSubId") Integer serviceItemSubId){
		ServiceItemSubclassEntity serviceItemSubclass = serviceItemSubclassService.getById(serviceItemSubId);

        return R.ok().put("serviceItemSubclass", serviceItemSubclass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("service:serviceitemsubclass:save")
    public R save(@RequestBody ServiceItemSubclassEntity serviceItemSubclass){
		serviceItemSubclassService.save(serviceItemSubclass);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("service:serviceitemsubclass:update")
    public R update(@RequestBody ServiceItemSubclassEntity serviceItemSubclass){
		serviceItemSubclassService.updateById(serviceItemSubclass);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("service:serviceitemsubclass:delete")
    public R delete(@RequestBody Integer[] serviceItemSubIds){
        List<ServiceDetailEntity> serviceDetailEntities = serviceDetailService.list();
        //所有被serviceDetail关联到的memberId
        Set<Integer> relatedAllIds = serviceDetailEntities.stream().map(ServiceDetailEntity::getServiceItemSubId).collect(Collectors.toSet());
        // 被关联的id
        List<Integer> relatedIds = new ArrayList<>();
        if (relatedAllIds.size() != 0) {
            for (Integer id : serviceItemSubIds) {
                if (relatedAllIds.contains(id)) {
                    //被关联到的id
                    relatedIds.add(id);
                }
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被serviceDetail表关联，无法删除");
        }

		serviceItemSubclassService.removeByIds(Arrays.asList(serviceItemSubIds));

        return R.ok();
    }

}
