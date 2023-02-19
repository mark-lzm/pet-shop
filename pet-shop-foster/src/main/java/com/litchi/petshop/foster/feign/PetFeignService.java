package com.litchi.petshop.foster.feign;

import com.litchi.pojo.pet.dto.PetBreedDto;
import com.litchi.pojo.pet.dto.PetCoatColorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mark
 * 2023/2/7 16:17
 */
@FeignClient("petshop-pet")
public interface PetFeignService {
    @RequestMapping("/pet/petcoatcolor/selectByColorId/{coatColorId}")
    public PetCoatColorDto selectByColorId(@PathVariable("coatColorId") Integer coatColorId);

    @RequestMapping("/pet/petbreed/selectByBreedId/{breedId}")
    public PetBreedDto selectByBreedId(@PathVariable("breedId") Integer breedId);
}
