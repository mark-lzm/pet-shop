package com.litchi.petshop.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.litchi.petshop.member.feign")
@EnableDiscoveryClient
@MapperScan("com.litchi.petshop.member.dao")
@SpringBootApplication
public class PetShopMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopMemberApplication.class, args);
    }

}
