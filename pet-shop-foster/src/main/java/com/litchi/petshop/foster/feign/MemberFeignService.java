package com.litchi.petshop.foster.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.member.dto.MemberGradeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author Mark
 * 2023/2/11 15:23
 */
@FeignClient("petshop-member")
public interface MemberFeignService {
    @RequestMapping("/member/member/memberdto/{id}")
    public MemberDto memberById(@PathVariable("id") Integer id);

    @RequestMapping("/member/member/updateMemberDto")
    public void updateMemberDto(@RequestBody MemberDto memberDto);

    @RequestMapping("/member/membergrade/memberGradeDto/{id}")
    public MemberGradeDto memberGradeDtoById(@PathVariable("id") Integer id);
}
