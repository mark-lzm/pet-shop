package com.litchi.pojo.foster.dto;

import lombok.Data;

/**
 * @Author Mark
 * 2023/2/21 16:52
 */
@Data
public class FosterDto {
    private Integer id;
    /**
     * 会员编号
     */
    private Integer memberId;
    /**
     * 姓名
     */
    private String memberName;
    /**
     * 电话
     */
    private String memberPhone;
    /**
     * 备注
     */
    private String remarks;

}
