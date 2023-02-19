package com.litchi.petshop.service.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litchi.petshop.service.bo.ServiceBo;
import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.litchi.petshop.service.service.ServiceDetailService;
import com.litchi.petshop.service.vo.ServiceVo;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.service.dto.ServiceDto;
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

    @Autowired
    private ServiceDetailService serviceDetailService;

    @RequestMapping("/member/{memberId}")
    public R memberService(@PathVariable Integer memberId) {
        List<ServiceEntity> services = serviceService.list(new QueryWrapper<ServiceEntity>().eq("member_id", memberId));
//        ServiceEntity service = new ServiceEntity();
//        service.setMemberId(66);
        return R.ok().put("services", services);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("service:service:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = serviceService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listSelect")
    public List<ServiceVo> listSelect() {
        return serviceService.listSelect();
    }

    @RequestMapping("/listAllService")
    public List<ServiceDto> listAllService(){
        return serviceService.listAllService();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("service:service:info")
    public R info(@PathVariable("id") Integer id) {
        ServiceEntity service = serviceService.getById(id);

        return R.ok().put("service", service);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("service:service:save")
    public R save(@RequestBody ServiceEntity service) {
        serviceService.save(service);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("service:service:update")
    public R update(@RequestBody ServiceEntity service) {
        serviceService.updateById(service);

        return R.ok();
    }

    @RequestMapping("/updatePrice")
    public R updatePrice(@RequestBody ServiceBo bo) {
        serviceService.updatePrice(bo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("service:service:delete")
    public R delete(@RequestBody Integer[] ids) {

        // 查看服务详情表所有被service表关联到，被关联到则无法删除
        List<ServiceDetailEntity> serviceDetailEntities = serviceDetailService.list();

        //所有关联到的serviceId
        Set<Integer> relatedAllIds = serviceDetailEntities.stream().map(ServiceDetailEntity::getServiceId).collect(Collectors.toSet());

        // 要删除的ids中，被关联的serviceId
        List<Integer> relatedIds = new ArrayList<>();

        for (Integer id : ids) {
            if (relatedAllIds.contains(id)) {
                //要删除的ids中，被关联到的id
                relatedIds.add(id);
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被serviceDetail表关联，无法删除");
        }

        serviceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/pay")
    public R pay(@RequestBody ServiceBo bo) {
        Map<String, String> map = serviceService.pay(bo);
        String message = map.get("message");
        if (message != null) {
            return R.error(message);
        }
        return R.ok();
    }

}
