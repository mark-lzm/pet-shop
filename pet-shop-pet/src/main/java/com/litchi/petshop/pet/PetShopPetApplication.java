package com.litchi.petshop.pet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.pet.dao")
@SpringBootApplication
public class PetShopPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopPetApplication.class, args);
    }

}
