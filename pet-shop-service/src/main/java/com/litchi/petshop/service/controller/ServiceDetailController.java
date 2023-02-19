package com.litchi.petshop.service.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.service.dto.ServiceDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.litchi.petshop.service.service.ServiceDetailService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * 用于记录宠物服务的详细情况
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:02:35
 */
@RestController
@RequestMapping("service/servicedetail")
public class ServiceDetailController {
    @Autowired
    private ServiceDetailService serviceDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = serviceDetailService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listAllServiceDetail")
    public List<ServiceDetailDto> listAllServiceDetail() {
        return serviceDetailService.listAllServiceDetail();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{serviceDetailId}")
    //@RequiresPermissions("service:servicedetail:info")
    public R info(@PathVariable("serviceDetailId") Integer serviceDetailId) {
        ServiceDetailEntity serviceDetail = serviceDetailService.getById(serviceDetailId);

        return R.ok().put("serviceDetail", serviceDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("service:servicedetail:save")
    public R save(@RequestBody ServiceDetailEntity serviceDetail) {
        serviceDetailService.save(serviceDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("service:servicedetail:update")
    public R update(@RequestBody ServiceDetailEntity serviceDetail) {
        serviceDetailService.updateById(serviceDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("service:servicedetail:delete")
    public R delete(@RequestBody Integer[] serviceDetailIds) {
        serviceDetailService.removeByIds(Arrays.asList(serviceDetailIds));

        return R.ok();
    }


    /**
     * 判断服务详情中，与服务id相关联的所有服务详情，是否已经服务结束
     */
    @RequestMapping("/isServiceEnd/{serviceId}")
    public R isServiceEnd(@PathVariable("serviceId") Integer serviceId) {
        boolean flag = serviceDetailService.isServiceEnd(serviceId);
        if (flag) {
            return R.ok();
        }
        return R.error( "还有一些服务未结束，暂时无法付款");
    }

    /**
     * 是否存在服务详情
     * @param
     * @return
     */
    @RequestMapping("/existServiceDetail")
    public R existServiceDetail(@RequestBody Integer[] serviceIds) {
        boolean flag = false;
        for (Integer serviceId : serviceIds) {
            flag = serviceDetailService.existServiceDetail(serviceId);
        }
        if (flag) {
            return R.error( "存在一些服务详情与之相关联，不能删除");
        }
        return R.ok();
    }

}
