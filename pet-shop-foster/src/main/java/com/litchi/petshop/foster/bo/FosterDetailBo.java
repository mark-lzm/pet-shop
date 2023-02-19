package com.litchi.petshop.foster.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author Mark
 * 2023/2/7 22:05
 */
@Data
public class FosterDetailBo {

    private Integer id;

    private Integer payValue;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date fosterEndTime;

    /**
     * pay为0，take为1
     */
    private Integer payOrTake;
}
