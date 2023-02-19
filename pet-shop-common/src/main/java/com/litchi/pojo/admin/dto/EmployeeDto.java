package com.litchi.pojo.admin.dto;

import lombok.Data;

/**
 * @Author Mark
 * 2023/2/14 14:55
 */
@Data
public class EmployeeDto {
    private Integer id;
    private String name;

    private String username;
    private String password;
    private String phone;
    private String idCard;
    private Integer status;
}
