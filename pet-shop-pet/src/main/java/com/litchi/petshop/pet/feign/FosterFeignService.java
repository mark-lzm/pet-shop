package com.litchi.petshop.pet.feign;

import com.litchi.pojo.foster.dto.FosterDto;
import com.litchi.pojo.member.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/21 16:44
 */
@FeignClient("petshop-foster")
public interface FosterFeignService {
    @RequestMapping("/foster/foster/updateByMemberId")
    public void updateByMemberId(@RequestBody MemberDto dto);

    @RequestMapping("/foster/foster/listAllMember")
    public List<MemberDto> listAllMember();

    @RequestMapping("/foster/foster/listAllFoster")
    public List<FosterDto> listAllFoster();
}
