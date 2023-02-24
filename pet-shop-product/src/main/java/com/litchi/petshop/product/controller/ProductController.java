package com.litchi.petshop.product.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.product.bo.ProductBo;
import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.litchi.petshop.product.service.ProductSaleDetailService;
import com.litchi.petshop.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.product.entity.ProductEntity;
import com.litchi.petshop.product.service.ProductService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * 记录商品的信息及库存数量，多次进价后的成本价等。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:31:17
 */
@RestController
@RequestMapping("product/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSaleDetailService productSaleDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:product:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = productService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/listAndCategory")
    public R listForCategory(@RequestParam Map<String, Object> params) {
        PageUtils page = productService.queryPageAndCategory(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listSelectProduct")
    public List<ProductEntity> listSelectProduct() {
        return productService.listSelectProduct();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:product:info")
    public R info(@PathVariable("id") Integer id) {
        ProductEntity product = productService.getById(id);

        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:product:save")
    public R save(@RequestBody ProductEntity product) {
        productService.save(product);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:product:update")
    public R update(@RequestBody ProductEntity product) {
        productService.updateById(product);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:product:delete")
    public R delete(@RequestBody Integer[] ids) {
        List<ProductSaleDetailEntity> productSaleDetailEntities = productSaleDetailService.list();

        Set<Integer> relatedAllIds = productSaleDetailEntities.stream().map(ProductSaleDetailEntity::getProductId).collect(Collectors.toSet());

        List<Integer> relatedIds = new ArrayList<>();

        for (Integer id : ids) {
            if (relatedAllIds.contains(id)) {
                //要删除的ids中，被关联到的id
                relatedIds.add(id);
            }
        }

        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被productSaleDetail表关联，无法删除");
        }

        productService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/setStock")
    public R setStock(@RequestBody ProductBo bo) {
        boolean flag = productService.setStock(bo);
        if (flag) {
            return R.ok();
        } else {
            ProductEntity product = productService.getById(bo.getProductId());
            return R.error(product.getName() + "商品库存不足，库存仅剩" + product.getStock() + "份");
        }
    }

}
