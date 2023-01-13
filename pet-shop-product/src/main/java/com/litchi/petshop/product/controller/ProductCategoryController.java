package com.litchi.petshop.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductCategoryEntity;
import com.litchi.petshop.product.service.ProductCategoryService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录商品的不同种类。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/productcategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:productcategory:info")
    public R info(@PathVariable("catId") Integer catId){
		ProductCategoryEntity productCategory = productCategoryService.getById(catId);

        return R.ok().put("productCategory", productCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productcategory:save")
    public R save(@RequestBody ProductCategoryEntity productCategory){
		productCategoryService.save(productCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productcategory:update")
    public R update(@RequestBody ProductCategoryEntity productCategory){
		productCategoryService.updateById(productCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productcategory:delete")
    public R delete(@RequestBody Integer[] catIds){
        //1.检查当前要删除的菜单，是否被其他地方引用
//		productCategoryService.removeByIds(Arrays.asList(catIds));
		productCategoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
