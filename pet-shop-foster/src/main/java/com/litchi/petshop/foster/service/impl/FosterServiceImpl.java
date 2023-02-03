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

import com.litchi.petshop.foster.dao.FosterDao;
import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.service.FosterService;


@Service("fosterService")
public class FosterServiceImpl extends ServiceImpl<FosterDao, FosterEntity> implements FosterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer pageIndex = Integer.parseInt((String) params.get("page"));
        Integer limit = Integer.parseInt((String) params.get("limit"));

        QueryWrapper<FosterEntity> wrapper = new QueryWrapper<>();
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().eq("member_id", key).or().like("member_name", key);
            });
        }

        List<FosterEntity> entities = this.list(wrapper);

        return PetPageUtils.getPageUtils(pageIndex, limit, entities);
    }

}