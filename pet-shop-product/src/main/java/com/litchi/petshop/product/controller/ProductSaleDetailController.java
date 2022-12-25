package com.litchi.petshop.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.litchi.petshop.product.service.ProductSaleDetailService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录顾客一次购买几种商品的需要。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/productsaledetail")
public class ProductSaleDetailController {
    @Autowired
    private ProductSaleDetailService productSaleDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productsaledetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productSaleDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{saleDetailId}")
    //@RequiresPermissions("product:productsaledetail:info")
    public R info(@PathVariable("saleDetailId") Integer saleDetailId){
		ProductSaleDetailEntity productSaleDetail = productSaleDetailService.getById(saleDetailId);

        return R.ok().put("productSaleDetail", productSaleDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productsaledetail:save")
    public R save(@RequestBody ProductSaleDetailEntity productSaleDetail){
		productSaleDetailService.save(productSaleDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productsaledetail:update")
    public R update(@RequestBody ProductSaleDetailEntity productSaleDetail){
		productSaleDetailService.updateById(productSaleDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productsaledetail:delete")
    public R delete(@RequestBody Integer[] saleDetailIds){
		productSaleDetailService.removeByIds(Arrays.asList(saleDetailIds));

        return R.ok();
    }

}
