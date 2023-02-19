package com.litchi.petshop.member.feign;

import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.pet.dto.PetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/16 17:58
 */
@FeignClient("petshop-pet")
public interface PetFeignService {
    @RequestMapping("/pet/pet/listAllPet")
    public List<PetDto> listAlPet();
}
