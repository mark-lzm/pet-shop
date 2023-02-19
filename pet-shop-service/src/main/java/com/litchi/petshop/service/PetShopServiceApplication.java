package com.litchi.petshop.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.litchi.petshop.service.feign")
@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.service.dao")
@SpringBootApplication
public class PetShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopServiceApplication.class, args);
    }

}
