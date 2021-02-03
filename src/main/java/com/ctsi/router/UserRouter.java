package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用户路由
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  16:28
 */
@Controller
public class UserRouter {

    // 用户首页
    @RequestMapping("/user/index")
    public String toUserIndex() {
        return "user/user-index";
    }
}
