package com.litchi.petshop.member.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/5 22:38
 */
@FeignClient("petshop-foster")
public interface FosterFeignService {
    @RequestMapping("/foster/foster/updateByMemberId")
    public void updateByMemberId(@RequestBody MemberDto dto);

    @RequestMapping("/foster/foster/listAllMember")
    public List<MemberDto> listAllMember();
}
