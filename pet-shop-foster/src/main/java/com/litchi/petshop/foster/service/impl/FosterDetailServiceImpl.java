package com.litchi.petshop.foster.service.impl;

import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.foster.entity.FosterStandardEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.foster.dao.FosterDetailDao;
import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;


@Service("fosterDetailService")
public class FosterDetailServiceImpl extends ServiceImpl<FosterDetailDao, FosterDetailEntity> implements FosterDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<FosterDetailEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("foster_detail_id", key).or().eq("foster_id", key).or().like("pet_name", key);
            });
        }

        List<FosterDetailEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}