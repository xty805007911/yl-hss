package com.ctsi.controller;

import com.ctsi.entity.TbUser;
import com.ctsi.service.TbUserService;
import com.ctsi.util.PhoneFormatCheckUtils;
import com.ctsi.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 公共的controller api
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  15:13
 */
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private TbUserService userService;

    //用户登录
    @PostMapping("/login")
    public TbUser login(@RequestBody TbUser formUser, HttpServletRequest request) {
        TbUser dbUser = userService.selectUserByMobileAndPassword(formUser.getMobile(), formUser.getPassword());
        if(dbUser == null || dbUser.getEnabled() == null || dbUser.getEnabled() == 0) {
            dbUser = null;
        }
        if(dbUser != null) {
            request.getSession().setAttribute("sessionUser",dbUser);
        }
        return dbUser;
    }

    @PostMapping("/register")
    public Result register(@RequestBody TbUser formUser,String selectCare,HttpServletRequest request) {
        //手机号校验
        if(formUser.getMobile() == null || formUser.getMobile().trim().equals("")) {
            return Result.build(400,"手机号不能为空哦~");
        }
        if(!PhoneFormatCheckUtils.isChinaPhoneLegal(formUser.getMobile())) {
            return Result.build(400,"手机号不合法哦~");
        }
        if(userService.isMobileExist(formUser.getMobile())) {
            return Result.build(400,"手机号已存在咯~");
        }

        //密码校验
        if(formUser.getPassword() == null || formUser.getPassword().trim().equals("")) {
            return Result.build(400,"密码不能为空");
        }

        if(selectCare.equals("true")) {//注册了护工
            formUser.setEnabled(0);
            formUser.setRoleId(2);
        }else {//未注册护工
            formUser.setEnabled(1);
            formUser.setRoleId(1);
        }

        //保存用户到数据库
        userService.insertUser(formUser) ;
        if(formUser.getEnabled() == 1) {
            //自动登录
            request.getSession().setAttribute("sessionUser",formUser);
        }


        return Result.ok();

    }





}
