package com.ctsi.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  18:39
 */
@Controller
public class OrderRouter {

    //用户添加订单
    @RequestMapping("/user/order/add")
    public String userToAddOrder(Integer careId, HttpServletRequest request) {
        request.setAttribute("careId",careId);
        return "user/user-order-add";
    }

    //用户查询订单详情
    @RequestMapping("/user/order/{orderId}")
    public String userToOrderDetail(@PathVariable Integer orderId) {
        return "user/user-order-detail";
    }

    //查询订单列表
    @RequestMapping("/order/list")
    public String toOrderList() {
        return "user/user-order-list";
    }
}
