package com.litchi.petshop.admin.feign;

import com.litchi.pojo.product.dto.ProductSaleDetailDto;
import com.litchi.pojo.product.dto.ProductSaleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/18 19:50
 */
@FeignClient("petshop-product")
public interface ProductFeignService {
    @RequestMapping("/product/productsale/listAllProductSale")
    public List<ProductSaleDto> listAllProductSale();

    @RequestMapping("/product/productsaledetail/listAllProductSaleDetail")
    public List<ProductSaleDetailDto> listAllProductSaleDetail();
}
