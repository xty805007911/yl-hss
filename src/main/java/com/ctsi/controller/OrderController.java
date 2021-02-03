package com.ctsi.controller;

import com.ctsi.config.Constant;
import com.ctsi.entity.TbOrder;
import com.ctsi.service.TbOrderService;
import com.ctsi.util.PageResult;
import com.ctsi.util.Result;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:订单
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  18:09
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private TbOrderService orderService;
    @Autowired
    private PersonInfoController personInfoController;

    //添加订单
    @PostMapping("/add")
    public Result addOrder(@RequestBody TbOrder order) {
        boolean isWaitOrder = orderService.isUserHaveOrderByStatus(Constant.ORDER_STATUS_WAIT,order.getUserId());
        boolean isOrdered = orderService.isUserHaveOrderByStatus(Constant.ORDER_STATUS_ORDERED,order.getUserId());
        if(isWaitOrder || isOrdered) {
            return Result.build(201,"您有已下单的订单啦~");
        }
        if(order.getUserId() == null || order.getCareId() == null) {
            return Result.build(500,"请登录哦~");
        }
        orderService.addOrder(order);
        return Result.ok(order);
    }

    //分页查询
    @GetMapping("/page/list")
    public PageResult<TbOrder> selectOrderPage(Integer page, TbOrder order, HttpServletRequest request) {
        //
        if(personInfoController.getCurrentUser(request).getRoleId() == 3) {
            order.setUserId(null);
            order.setCareId(null);
        }
        return orderService.selectOrderPageList(page,Constant.PAGE_SIZE,order.getUserId(),order.getCareId(),order.getStatus());
    }

    //根据id查询订单
    @GetMapping("/{orderId}")
    public TbOrder getById(@PathVariable Integer orderId) {
        return orderService.selectById(orderId);
    }

    //更新订单
    @PostMapping("/update")
    public Result updateOrder(@RequestBody TbOrder order) {
        orderService.updateOrder(order);
        return Result.ok();
    }

    // 根据用户和状态查询
    @GetMapping("/list/user/{userId}")
    public List<TbOrder> getOrderByUser(@PathVariable Integer userId,
                                        @RequestParam(required = false) Integer status) {
        return orderService.selectOrderByCareId(userId,status);
    }
}
