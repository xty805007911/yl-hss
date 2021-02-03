package com.ctsi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctsi.entity.TbOrderRating;
import com.ctsi.mapper.TbOrderRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/1  22:23
 */
@Service
public class TbOrderRatingService {
    @Autowired
    private TbOrderRatingMapper orderRatingMapper;

    //添加
    public void addOrderRating(TbOrderRating orderRating) {
        orderRating.setCreateTime(new Date());
        orderRatingMapper.insert(orderRating);
    }

    //根据id查询
    public TbOrderRating getOrderRatingById(Integer id) {
        return orderRatingMapper.selectById(id);
    }

    //修改
    public void updateOrderRating(TbOrderRating orderRating) {
        orderRatingMapper.updateById(orderRating);
    }

    //根据订单id查询评价
    public TbOrderRating getOrderRatingByOrderId(Integer orderId) {
        QueryWrapper<TbOrderRating> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<TbOrderRating> list = orderRatingMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }else {
            return list.get(0);
        }
    }
}
