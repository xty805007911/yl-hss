package com.ctsi.controller;

import com.ctsi.config.Constant;
import com.ctsi.entity.TbUser;
import com.ctsi.service.TbUserService;
import com.ctsi.util.PageResult;
import com.ctsi.util.PhoneFormatCheckUtils;
import com.ctsi.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  16:02
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private TbUserService userService;

    //获取所有的护工
    @GetMapping("/care/list")
    public PageResult<TbUser> getAllCare() {

        return null;
    }

    //用户分页查询
    @GetMapping("/user/list")
    public PageResult<TbUser> getAllUser(Integer roleId,Integer page) {
        return userService.selectUserPageList(roleId,page, Constant.PAGE_SIZE);
    }

    //更新用户信息
    @PostMapping("/user/update")
    public Result updateUser(@RequestBody TbUser user) {
        try{
            userService.updateUser(user);
            return Result.ok();
        }catch (Exception e) {
            return Result.build(500,e.getMessage());
        }
    }

    //用户添加
    @PostMapping("/user/add")
    public Result addUser(@RequestBody TbUser user) {
        //手机号校验
        if(user.getMobile() == null || user.getMobile().trim().equals("")) {
            return Result.build(400,"手机号不能为空哦~");
        }
        if(!PhoneFormatCheckUtils.isChinaPhoneLegal(user.getMobile())) {
            return Result.build(400,"手机号不合法哦~");
        }
        if(userService.isMobileExist(user.getMobile())) {
            return Result.build(400,"手机号已存在咯~");
        }

        //密码校验
        if(user.getPassword() == null || user.getPassword().trim().equals("")) {
            return Result.build(400,"密码不能为空");
        }
        try{
            userService.insertUser(user);
            return Result.ok();
        }catch (Exception e) {
            return Result.build(500,e.getMessage());
        }
    }

    //根据id查询用户
    @GetMapping("/user/{id}")
    public TbUser getUserById(@PathVariable Integer id) {
        return userService.selectUserById(id);
    }
}
