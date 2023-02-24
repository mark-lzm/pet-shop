package com.litchi.petshop.foster.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litchi.petshop.foster.entity.FosterDetailEntity;
import com.litchi.petshop.foster.service.FosterDetailService;
import com.litchi.pojo.foster.dto.FosterDto;
import com.litchi.pojo.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.foster.entity.FosterEntity;
import com.litchi.petshop.foster.service.FosterService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * 记录寄养服务时会员及非会员的基本。
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 11:11:25
 */
@RestController
@RequestMapping("foster/foster")
public class FosterController {
    @Autowired
    private FosterService fosterService;

    @Autowired
    private FosterDetailService fosterDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("foster:foster:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fosterService.queryPage(params);

        return R.ok().put("page", page);
    }

     /**
     * 列表所有会员
     */
    @RequestMapping("/listAllMember")
    public List<MemberDto> listAllMember() {
        return fosterService.listAllMember();
    }

    /**
     * 列表所有foster
     */
    @RequestMapping("/listAllFoster")
    public List<FosterDto> listAllFoster() {
        return fosterService.listAllFoster();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("foster:foster:info")
    public R info(@PathVariable("id") Integer id) {
        FosterEntity foster = fosterService.getById(id);

        return R.ok().put("foster", foster);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("foster:foster:save")
    public R save(@RequestBody FosterEntity foster) {
        if (isExistMember(foster)) {
            return R.error().put("msg", "会员\"" + foster.getMemberName() + "\"已登记，无需重复登记");
        }
        fosterService.save(foster);
        return R.ok();
    }

    /**
     * 判断添加的foster中，选择的member是否已经存在在Foster表中，存在返回true，反之false
     *
     * @param foster
     * @return
     */
    private boolean isExistMember(FosterEntity foster) {
        List<FosterEntity> fosterEntities = fosterService.list();
        Set<Integer> ids = fosterEntities.stream().map(FosterEntity::getMemberId).collect(Collectors.toSet());
        return ids.contains(foster.getMemberId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("foster:foster:update")
    public R update(@RequestBody FosterEntity foster) {
        if (isExistMember(foster)) {
            return R.error().put("msg", "会员\"" + foster.getMemberName() + "\"已登记，不可重复修改");
        }
        fosterService.updateById(foster);

        return R.ok();
    }

    /**
     * 根据Member进行修改
     *
     * @return
     */
    @RequestMapping("/updateByMemberId")
    public void updateByMemberId(@RequestBody MemberDto dto) {
        fosterService.updateByMemberId(dto);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("foster:foster:delete")
    public R delete(@RequestBody Integer[] ids) {
        // 检查当前要删除的寄养人，是否被其他地方引用

        // 查看寄养详情表所有被foster关联到，被关联到则无法删除
        List<FosterDetailEntity> fosterDetailEntities = fosterDetailService.list();

        //所有关联到的fosterId
        Set<Integer> relatedAllIds = fosterDetailEntities.stream().map(FosterDetailEntity::getFosterId).collect(Collectors.toSet());

        // 要删除的ids中，被关联的fosterId
        List<Integer> relatedIds = new ArrayList<>();

        for (Integer id : ids) {
            if (relatedAllIds.contains(id)) {
                //要删除的ids中，被关联到的id
                relatedIds.add(id);
            }
        }
        if (relatedIds.size() != 0) {
            return R.error().put("msg", "编号为：" + Arrays.toString(relatedIds.toArray()) + "被fosterDetail表关联，无法删除");
        }
        fosterService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
