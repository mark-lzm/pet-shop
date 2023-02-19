package com.litchi.petshop.product.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.petshop.product.bo.ProductSaleBo;
import com.litchi.petshop.product.entity.ProductSaleDetailEntity;
import com.litchi.petshop.product.service.ProductSaleDetailService;
import com.litchi.petshop.product.vo.ProductSaleVo;
import com.litchi.pojo.product.dto.ProductSaleDto;
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

    @Autowired
    private ProductSaleDetailService productSaleDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productsale:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = productSaleService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listSelectProductSale")
    public List<ProductSaleVo> listSelectProductSale() {
        return productSaleService.listSelectProductSale();
    }

    @RequestMapping("/listAllProductSale")
    public List<ProductSaleDto> listAllProductSale() {
        return productSaleService.listAllProductSale();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:productsale:info")
    public R info(@PathVariable("id") Integer id) {
        ProductSaleEntity productSale = productSaleService.getById(id);

        return R.ok().put("productSale", productSale);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productsale:save")
    public R save(@RequestBody ProductSaleEntity productSale) {
        productSaleService.save(productSale);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productsale:update")
    public R update(@RequestBody ProductSaleEntity productSale) {
        productSaleService.updateById(productSale);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productsale:delete")
    public R delete(@RequestBody Integer[] ids) {

        List<ProductSaleDetailEntity> productSaleDetailEntities = productSaleDetailService.list();

        //所有关联到的saleId
        Set<Integer> relatedAllIds = productSaleDetailEntities.stream().map(ProductSaleDetailEntity::getSaleId).collect(Collectors.toSet());

        // 要删除的ids中，被关联的saleId
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

        productSaleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/updateSalePrice")
    public R updateSalePrice(@RequestBody ProductSaleBo bo) {
        ProductSaleEntity productSale = productSaleService.getById(bo.getSaleId());
        productSale.setSalePrice(productSale.getSalePrice().add(bo.getDiscountedPrice()));

        productSaleService.updateById(productSale);

        return R.ok();
    }

    @RequestMapping("/pay")
    public R pay(@RequestBody ProductSaleBo bo) {
        Map<String, String> map = productSaleService.pay(bo);
        String message = map.get("message");
        if (message != null) {
            return R.error(message);
        }
        return R.ok();
    }
}
