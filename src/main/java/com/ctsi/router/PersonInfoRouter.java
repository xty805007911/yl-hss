package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  16:23
 */
@Controller
public class PersonInfoRouter {

    //去修改密码
    @RequestMapping("/person/info/password")
    public String toChangePassword() {
        return "person-info/person-change-pass";
    }

    //去当前用户详情
    @RequestMapping("/person/info")
    public String toPersonInfo() {
        return "person-info/person-info";
    }
}
