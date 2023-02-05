package com.litchi.petshop.foster.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.litchi.pojo.dto.MemberDto;
import org.springframework.beans.BeanUtils;
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
        fosterService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
