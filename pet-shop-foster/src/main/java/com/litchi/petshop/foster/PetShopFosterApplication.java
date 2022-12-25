package com.litchi.petshop.foster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.foster.dao")
@SpringBootApplication
public class PetShopFosterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopFosterApplication.class, args);
    }

}
