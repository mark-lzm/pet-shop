package com.litchi.petshop.pet.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.member.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mark
 * 2023/2/5 11:08
 */
@FeignClient("petshop-member")
public interface MemberFeignService {
    @RequestMapping("/member/member/info/{id}")
    public R info(@PathVariable("id") Integer id);


    @RequestMapping("/member/member/memberdto/{id}")
    public MemberDto memberById(@PathVariable("id") Integer id);
}
