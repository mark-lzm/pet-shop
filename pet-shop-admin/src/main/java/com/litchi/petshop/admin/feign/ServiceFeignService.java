package com.litchi.petshop.admin.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.service.dto.ServiceDetailDto;
import com.litchi.pojo.service.dto.ServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/18 19:45
 */
@FeignClient("petshop-service")
public interface ServiceFeignService {
    @RequestMapping("/service/service/member/{memberId}")
    public R memberService(@PathVariable Integer memberId);

    @RequestMapping("/service/service/listAllService")
    public List<ServiceDto> listAllService();

    @RequestMapping("/service/servicedetail/listAllServiceDetail")
    public List<ServiceDetailDto> listAllServiceDetail();
}
