package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/3  20:18
 */
@Controller
public class WorkRouter {

    //去工作列表
    @RequestMapping("/work/list")
    public String toWork() {
        return "manager/work-list";
    }
    //去添加页面
    @RequestMapping("/work/add")
    public String toAdd() {

        return "manager/work-add";
    }
    //去修改页面
    @RequestMapping("/work/update/{id}")
    public String toEdit(@PathVariable Integer id, HttpServletRequest request) {
        request.setAttribute("workId",id);
        return "manager/work-edit";
    }

}
