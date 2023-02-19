package com.litchi.petshop.service.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.member.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mark
 * 2023/2/14 14:50
 */
@FeignClient("petshop-member")
public interface MemberFeignService {
    @RequestMapping("/member/member/memberdto/{id}")
    public MemberDto memberById(@PathVariable("id") Integer id);

    @RequestMapping("/member/member/updateMemberDto")
    public void updateMemberDto(@RequestBody MemberDto memberDto);

    @RequestMapping("/member/member/updateGradeIdByPoints")
    public void updateGradeIdByPoints(@RequestBody MemberDto memberDto);
}
