package com.litchi.pojo.member.dto;

import lombok.Data;

/**
 * @Author Mark
 * 2023/2/16 0:24
 */
@Data
public class MemberGradeDto {
    private Integer gradeId;
    private String gradeName;
    private Double discount;
    private Integer gradePointValue;
}
