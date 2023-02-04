package com.litchi.petshop.product.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.product.dao.ProductCategoryDao;
import com.litchi.petshop.product.entity.ProductCategoryEntity;
import com.litchi.petshop.product.vo.ProductForCategoryVo;
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
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

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
        return PetPageUtils.getPageUtils(pageIndex, limit, productForCategoryVoList);
    }

}