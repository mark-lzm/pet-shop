package com.litchi.petshop.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.service.ProductSaleService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于商品卖出的相关信息。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/productsale")
public class ProductSaleController {
    @Autowired
    private ProductSaleService productSaleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productsale:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productSaleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:productsale:info")
    public R info(@PathVariable("id") Integer id){
		ProductSaleEntity productSale = productSaleService.getById(id);

        return R.ok().put("productSale", productSale);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productsale:save")
    public R save(@RequestBody ProductSaleEntity productSale){
		productSaleService.save(productSale);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productsale:update")
    public R update(@RequestBody ProductSaleEntity productSale){
		productSaleService.updateById(productSale);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productsale:delete")
    public R delete(@RequestBody Integer[] ids){
		productSaleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
