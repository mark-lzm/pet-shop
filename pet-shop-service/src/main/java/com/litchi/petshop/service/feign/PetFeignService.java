package com.litchi.petshop.service.feign;

import com.litchi.common.utils.R;
import com.litchi.pojo.pet.dto.PetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mark
 * 2023/2/14 23:55
 */
@FeignClient("petshop-pet")
public interface PetFeignService {
    @RequestMapping("/pet/pet/selectByPetId/{id}")
    public PetDto selectByPetId(@PathVariable("id") Integer id);
}
