package com.litchi.petshop.foster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.common.utils.PageUtils;
import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.pojo.foster.dto.FosterDto;
import com.litchi.pojo.member.dto.MemberDto;

import java.util.List;
import java.util.Map;

/**
 * 记录寄养服务时会员及非会员的基本。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
public interface FosterService extends IService<FosterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateByMemberId(MemberDto dto);

    List<MemberDto> listAllMember();

    List<FosterDto> listAllFoster();
}

