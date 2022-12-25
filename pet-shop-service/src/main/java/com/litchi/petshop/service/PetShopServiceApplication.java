package com.litchi.petshop.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.service.dao")
@SpringBootApplication
public class PetShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopServiceApplication.class, args);
    }

}
