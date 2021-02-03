package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 管理路由
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  21:06
 */
@Controller
public class ManagerRouter {

    // 用户分页查询
    @RequestMapping("/manager/user/list")
    public String toUserList() {
        return "manager/user-list.html";
    }

    // 用户添加
    @RequestMapping("/manager/user/add")
    public String toAddUser() {
        return "manager/user-add.html";
    }

    // 用户编辑
    @RequestMapping("/manager/user/{userId}")
    public String toEdit(@PathVariable Integer userId, HttpServletRequest request) {
        request.setAttribute("userId",userId);
        return "manager/user-edit.html";
    }

    // 护工分页查询
    @RequestMapping("/manager/care/list")
    public String toCareList() {
        return "manager/care-list.html";
    }
}
