package com.litchi.petshop.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductStorageDetailEntity;
import com.litchi.petshop.product.service.ProductStorageDetailService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 用于记录一张入库单有不同商品的情况。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/productstoragedetail")
public class ProductStorageDetailController {
    @Autowired
    private ProductStorageDetailService productStorageDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productstoragedetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productStorageDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{storageDetailId}")
    //@RequiresPermissions("product:productstoragedetail:info")
    public R info(@PathVariable("storageDetailId") Integer storageDetailId){
		ProductStorageDetailEntity productStorageDetail = productStorageDetailService.getById(storageDetailId);

        return R.ok().put("productStorageDetail", productStorageDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productstoragedetail:save")
    public R save(@RequestBody ProductStorageDetailEntity productStorageDetail){
		productStorageDetailService.save(productStorageDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productstoragedetail:update")
    public R update(@RequestBody ProductStorageDetailEntity productStorageDetail){
		productStorageDetailService.updateById(productStorageDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productstoragedetail:delete")
    public R delete(@RequestBody Integer[] storageDetailIds){
		productStorageDetailService.removeByIds(Arrays.asList(storageDetailIds));

        return R.ok();
    }

}
