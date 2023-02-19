package com.litchi.petshop.service.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Mark
 * 2023/2/13 16:43
 */
@Data
public class ServiceItemSubclassVo {
    /**
     * 服务项目子类编号
     */
    @TableId
    private Integer serviceItemSubId;
    /**
     * 宠物服务项目大类
     */
    private String serviceItemName;
    /**
     * 服务项目子类名称
     */
    private String serviceItemSubName;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 服务项目标记
     */
    private Integer serviceItemTag;
}
