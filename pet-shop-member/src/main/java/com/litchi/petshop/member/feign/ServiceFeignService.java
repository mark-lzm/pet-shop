package com.litchi.petshop.member.feign;

import com.litchi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mark
 * 2022/12/25 14:41
 */
@FeignClient("petshop-service")
public interface ServiceFeignService {
    @RequestMapping("/service/service/member/{memberId}")
    public R memberService(@PathVariable Integer memberId);
}
