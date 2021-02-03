package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 公共的地址跳转
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  15:06
 */
@Controller
public class CommonRouter {

    //去首页
    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    //去模板页面
    @RequestMapping("/tmp")
    public String toTmp() {
        return "tmp";
    }

    //去登录页面
    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request) {
        return "login";
    }

    //安全退出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionUser");
        return "redirect:/";
    }

    //去注册页面
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }
}
