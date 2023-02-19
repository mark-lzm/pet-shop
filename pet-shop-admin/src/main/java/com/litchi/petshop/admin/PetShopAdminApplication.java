package com.litchi.petshop.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.litchi.petshop.admin.feign")
@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.admin.dao")
@SpringBootApplication
public class PetShopAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopAdminApplication.class, args);
    }

}
