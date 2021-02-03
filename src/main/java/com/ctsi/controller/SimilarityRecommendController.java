package com.ctsi.controller;

import com.ctsi.entity.TbCare;
import com.ctsi.entity.TbOrder;
import com.ctsi.entity.TbUser;
import com.ctsi.service.SimilarityService;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/3  11:05
 */
@RestController
@RequestMapping("/api/recommend")
public class SimilarityRecommendController {
    @Autowired
    private SimilarityService similarityService;

    // 推荐用户
    @GetMapping("/order/user/{userId}")
    public List<TbUser> getRecommendOrder(@PathVariable Integer userId) {
        return similarityService.getRecommendCare(userId);
    }
}
