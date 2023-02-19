package com.litchi.petshop.foster.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.foster.entity.FosterEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterStandardDao;
import com.litchi.petshop.foster.entity.FosterStandardEntity;
import com.litchi.petshop.foster.service.FosterStandardService;


@Service("fosterStandardService")
public class FosterStandardServiceImpl extends ServiceImpl<FosterStandardDao, FosterStandardEntity> implements FosterStandardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<FosterStandardEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("foster_standard_id", key).or().like("foster_standard_name", key);
            });
        }

        List<FosterStandardEntity> entities = this.list(wrapper);
        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, entities);
        }
        Page<FosterStandardEntity> page = new Page<>();
        page.setRecords(entities);
        page.setTotal(entities.size());

        return new PageUtils(page);
    }

}