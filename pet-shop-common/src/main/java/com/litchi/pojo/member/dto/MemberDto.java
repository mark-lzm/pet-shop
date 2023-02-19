package com.litchi.pojo.member.dto;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @Author Mark
 * 2023/2/5 11:24
 */
@Data
public class MemberDto {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String sex;
    private Integer availablePoints;
    private Integer points;
    private Integer gradeId;
    private Double balance;
    private Integer status;
}
