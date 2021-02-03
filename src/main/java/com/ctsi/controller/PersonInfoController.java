package com.ctsi.controller;

import com.ctsi.entity.TbUser;
import com.ctsi.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 个人信息
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  16:09
 */
@RestController
@RequestMapping("/api/person/info")
public class PersonInfoController {

    @Autowired
    private TbUserService userService;

    //获取当前用户
    @GetMapping("/current/user")
    public TbUser getCurrentUser(HttpServletRequest request) {
        TbUser currentUser = (TbUser) request.getSession().getAttribute("sessionUser");
        return currentUser;
    }

    //修改用户信息
    @PostMapping("/update")
    public void updateUser(@RequestBody TbUser formUser, HttpServletRequest request) {
        if(formUser == null || formUser.getId() == null) {
            return ;
        }
        userService.updateUser(formUser);
        //重新加载用户信息
        request.getSession().setAttribute("sessionUser",formUser);
    }

}
