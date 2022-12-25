package com.litchi.petshop.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductStorageEntity;
import com.litchi.petshop.product.service.ProductStorageService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录商品入库时的主要信息。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/productstorage")
public class ProductStorageController {
    @Autowired
    private ProductStorageService productStorageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productstorage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productStorageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:productstorage:info")
    public R info(@PathVariable("id") Integer id){
		ProductStorageEntity productStorage = productStorageService.getById(id);

        return R.ok().put("productStorage", productStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productstorage:save")
    public R save(@RequestBody ProductStorageEntity productStorage){
		productStorageService.save(productStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productstorage:update")
    public R update(@RequestBody ProductStorageEntity productStorage){
		productStorageService.updateById(productStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productstorage:delete")
    public R delete(@RequestBody Integer[] ids){
		productStorageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
