package com.litchi.petshop.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litchi.petshop.admin.entity.AdminEntity;
import com.litchi.petshop.admin.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetShopAdminApplicationTests {

    @Autowired
    AdminService adminService;

    @Test
    public void contextLoads() {
        AdminEntity adminEntity = new AdminEntity();
//        adminEntity.setId(1);
//        adminEntity.setAvailableTag("1");
//        adminEntity.setAdminName("litchi");
//        adminEntity.setAdminPassword("123456");
//        adminService.save(adminEntity);
//        System.out.println("保存成功");

//        adminService.updateById(adminEntity);

        List<AdminEntity> list = adminService.list(new QueryWrapper<AdminEntity>().eq("admin_id", 1));
        list.forEach(item -> System.out.println(item));
    }

}
