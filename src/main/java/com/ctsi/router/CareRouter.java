package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  15:15
 */
@Controller
public class CareRouter {

    //去当前用户详情
    @RequestMapping("/care/info/{id}")
    public String toCareInfo(@PathVariable Integer id, HttpServletRequest request) {
        request.setAttribute("id",id);
        return "person-info/person-info-care";
    }
}
