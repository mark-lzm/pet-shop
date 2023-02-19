package com.litchi.petshop.product.feign;

/**
 * @Author Mark
 * 2023/2/16 20:18
 */

import com.litchi.pojo.admin.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("petshop-admin")
public interface AdminFeignService {
    @RequestMapping("/admin/employee/employeedto/{id}")
    public EmployeeDto employeedto(@PathVariable("id") Integer id);
}
