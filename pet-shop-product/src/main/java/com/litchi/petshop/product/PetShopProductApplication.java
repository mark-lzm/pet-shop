package com.litchi.petshop.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.litchi.petshop.product.feign")
@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.product.dao")
@SpringBootApplication
public class PetShopProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopProductApplication.class, args);
    }

}
