package com.ctsi.controller;

import com.ctsi.entity.TbOrderRating;
import com.ctsi.service.TbOrderRatingService;
import com.ctsi.service.TbOrderService;
import com.ctsi.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/1  22:24
 */
@RestController
@RequestMapping("/api/order/rating")
public class OrderRatingController {

    @Autowired
    private TbOrderRatingService orderRatingService;

    //添加
    @PostMapping("/add")
    public void addOrderRating(@RequestBody TbOrderRating orderRating) {
        orderRatingService.addOrderRating(orderRating);
    }

    //根据id查询
    @GetMapping("/{id}")
    public TbOrderRating getOrderRatingById(@PathVariable Integer id) {
        return orderRatingService.getOrderRatingById(id);
    }

    //修改
    @PostMapping("/update")
    public Result updateOrderRating(@RequestBody TbOrderRating orderRating) {
        orderRatingService.updateOrderRating(orderRating);
        return Result.ok();
    }

    //根据订单id查询
    @GetMapping("/order/{orderId}")
    public TbOrderRating getOrderRatingByOrderId(@PathVariable Integer orderId) {
        return orderRatingService.getOrderRatingByOrderId(orderId);
    }
}
