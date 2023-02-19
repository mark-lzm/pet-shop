package com.litchi.petshop.admin.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.admin.feign.ProductFeignService;
import com.litchi.petshop.admin.feign.ServiceFeignService;
import com.litchi.pojo.admin.dto.EmployeeDto;
import com.litchi.pojo.product.dto.ProductSaleDetailDto;
import com.litchi.pojo.product.dto.ProductSaleDto;
import com.litchi.pojo.service.dto.ServiceDetailDto;
import com.litchi.pojo.service.dto.ServiceDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ServiceFeignService serviceFeignService;

    @Autowired
    private ProductFeignService productFeignService;

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
    public R deleteEmployee(@RequestBody Integer[] ids){
        // 被service要删除，需要判断联表数据

        /**
         * service
         */
        List<ServiceDto> serviceDtos = serviceFeignService.listAllService();
        //所有被service关联到的operatorId
        Set<Integer> relatedAllIdsOfService = serviceDtos.stream().map(ServiceDto::getOperatorId).collect(Collectors.toSet());
        // 被关联的id
        List<Integer> relatedIdsOfService = new ArrayList<>();
        if (relatedAllIdsOfService.size() != 0) {
            for (Integer id : ids) {
                if (relatedAllIdsOfService.contains(id)) {
                    //被关联到的id
                    relatedIdsOfService.add(id);
                }
            }
        }
        if (relatedIdsOfService.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIdsOfService.toArray()) + "被service表关联，无法删除");
        }

        /**
         * productsale
         */
        List<ProductSaleDto> productSaleDtos = productFeignService.listAllProductSale();
        //所有被productsale关联到的operatorId
        Set<Integer> relatedAllIdsOfProductSale = productSaleDtos.stream().map(ProductSaleDto::getOperatorId).collect(Collectors.toSet());
        // 被关联的id
        List<Integer> relatedIdsOfProductSale = new ArrayList<>();
        if (relatedAllIdsOfProductSale.size() != 0) {
            for (Integer id : ids) {
                if (relatedAllIdsOfProductSale.contains(id)) {
                    //被关联到的id
                    relatedIdsOfProductSale.add(id);
                }
            }
        }
        if (relatedIdsOfProductSale.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIdsOfProductSale.toArray()) + "被productsale表关联，无法删除");
        }


        /**
         * pet-service-detail
         */
        List<ServiceDetailDto> serviceDetailDtos = serviceFeignService.listAllServiceDetail();
        Set<Integer> relatedAllIdsOfServiceDetail = serviceDetailDtos.stream().map(ServiceDetailDto::getEmployeeId).collect(Collectors.toSet());
        // 被关联的id
        List<Integer> relatedIdsOfServiceDetail = new ArrayList<>();
        if (relatedAllIdsOfProductSale.size() != 0) {
            for (Integer id : ids) {
                if (relatedAllIdsOfServiceDetail.contains(id)) {
                    //被关联到的id
                    relatedIdsOfServiceDetail.add(id);
                }
            }
        }
        if (relatedIdsOfServiceDetail.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIdsOfServiceDetail.toArray()) + "被servicedetail表关联，无法删除");
        }


        employeeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/employeedto/{id}")
    public EmployeeDto employeedto(@PathVariable("id") Integer id){
        EmployeeEntity employee = employeeService.getById(id);
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }

    @RequestMapping("/queryByUserName/{username}")
    public EmployeeDto queryByUserName(@PathVariable("username") String username){
        EmployeeEntity employee = employeeService.queryByUserName(username);
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }
}
