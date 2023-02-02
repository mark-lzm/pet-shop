package com.litchi.petshop.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.litchi.petshop.member.feign.ServiceFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.petshop.member.entity.MemberEntity;
import com.litchi.petshop.member.service.MemberService;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.R;


/**
 * @author mark
 * @email lizhiming596@126.com
 * @date 2022-12-25 10:51:15
 */
@RefreshScope
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    ServiceFeignService serviceFeignService;

    @Value("${member.user.name}")
    private String name;
    @Value("${member.user.age}")
    private String age;

    @RequestMapping("/test")
    public R test1() {
        return R.ok().put("name", name).put("age", age);
    }

    @RequestMapping("/service")
    public R test() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(1);
        memberEntity.setName("zhangsan");
        R memberServices = serviceFeignService.memberService(memberEntity.getId());
        return R.ok().put("member", memberEntity).put("service", memberServices.get("services"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/listAndGrade")
    //@RequiresPermissions("member:member:list")
    public R listAndGrade(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPageAndGrade(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Integer id) {
        MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Integer[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
