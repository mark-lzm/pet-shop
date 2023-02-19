package com.litchi.petshop.product.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.bo.ProductBo;
import com.litchi.petshop.product.dao.ProductCategoryDao;
import com.litchi.petshop.product.entity.ProductCategoryEntity;
import com.litchi.petshop.product.entity.ProductSaleEntity;
import com.litchi.petshop.product.vo.ProductForCategoryVo;
import com.litchi.petshop.product.vo.ProductSaleVo;
import com.litchi.petshop.product.vo.ProductVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.product.dao.ProductDao;
import com.litchi.petshop.product.entity.ProductEntity;
import com.litchi.petshop.product.service.ProductService;
import org.springframework.transaction.annotation.Transactional;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductEntity> page = this.page(
                new Query<ProductEntity>().getPage(params),
                new QueryWrapper<ProductEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageAndCategory(Map<String, Object> params) {
        List<ProductForCategoryVo> productForCategoryVoList = new ArrayList<>();

        QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");

        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().like("name", key);
            });
        }
        List<ProductEntity> productEntities = this.list(wrapper);

        // 将entity赋值给vo
        for (ProductEntity entity : productEntities) {
            ProductForCategoryVo productForCategoryVo = new ProductForCategoryVo();
            BeanUtils.copyProperties(entity, productForCategoryVo);

            //查询catName
            Integer catId = entity.getCatId();
            ProductCategoryEntity productCategoryEntity = productCategoryDao.selectOne(new QueryWrapper<ProductCategoryEntity>().eq("cat_id", catId));
            productForCategoryVo.setCatName(productCategoryEntity.getName());

            productForCategoryVoList.add(productForCategoryVo);
        }

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, productForCategoryVoList);
        }

        Page<ProductForCategoryVo> page = new Page<>();
        page.setRecords(productForCategoryVoList);
        page.setTotal(productForCategoryVoList.size());
        return new PageUtils(page);

    }

    @Override
    public List<ProductEntity> listSelectProduct() {
        List<ProductEntity> result = new ArrayList<>();

        List<ProductEntity> list = this.list();
        for (ProductEntity product : list) {
            // 商品进行展示才能被其他表选中
            if (product.getProductInfoMark() == 1) {
                result.add(product);
            }
        }
        return result;
    }

    @Transactional
    @Override
    public boolean setStock(ProductBo bo) {
        ProductEntity product = this.getById(bo.getProductId());
        if (bo.getProductAmount() > product.getStock()) {
            return false;
        } else {
            product.setStock(product.getStock() - bo.getProductAmount());
            this.updateById(product);
            return true;
        }
    }

}