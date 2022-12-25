package com.litchi.petshop.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.member.entity.MemberGradeEntity;
import com.litchi.petshop.member.service.MemberGradeService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;



/**
 * 
 *
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:51:15
 */
@RestController
@RequestMapping("member/membergrade")
public class MemberGradeController {
    @Autowired
    private MemberGradeService memberGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:membergrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberGradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{gradeId}")
    //@RequiresPermissions("member:membergrade:info")
    public R info(@PathVariable("gradeId") Integer gradeId){
		MemberGradeEntity memberGrade = memberGradeService.getById(gradeId);

        return R.ok().put("memberGrade", memberGrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:membergrade:save")
    public R save(@RequestBody MemberGradeEntity memberGrade){
		memberGradeService.save(memberGrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:membergrade:update")
    public R update(@RequestBody MemberGradeEntity memberGrade){
		memberGradeService.updateById(memberGrade);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:membergrade:delete")
    public R delete(@RequestBody Integer[] gradeIds){
		memberGradeService.removeByIds(Arrays.asList(gradeIds));

        return R.ok();
    }

}
