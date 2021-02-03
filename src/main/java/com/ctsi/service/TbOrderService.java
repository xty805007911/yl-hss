package com.ctsi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctsi.config.Constant;
import com.ctsi.entity.TbOrder;
import com.ctsi.mapper.TbCareMapper;
import com.ctsi.mapper.TbOrderMapper;
import com.ctsi.mapper.TbUserMapper;
import com.ctsi.util.PageResult;
import com.ctsi.vo.GroupCountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  18:28
 */
@Service
public class TbOrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbCareMapper careMapper;

    //添加一个订单
    public void addOrder(TbOrder order) {
        Date currentDate = new Date();
        order.setCreateTime(currentDate);
        order.setStatus(Constant.ORDER_STATUS_WAIT);//等待接单中..
        if(order.getOrderTime() == null) {
            order.setOrderTime(currentDate);
        }
        orderMapper.insert(order);
    }

    //查询用户是否有某个状态的订单
    public boolean isUserHaveOrderByStatus(Integer status,Integer userId) {
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",status);
        queryWrapper.eq("user_id",userId);
        List<TbOrder> list = orderMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(list)) {
            return false;
        }else {
            return true;
        }
    }

    //分页查询订单
    public PageResult<TbOrder> selectOrderPageList(Integer page,Integer size,Integer userId,Integer careId,Integer status) {
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        if(page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        if(userId != null) {
            queryWrapper.eq("user_id",userId);
        }
        if(careId != null) {
            queryWrapper.eq("care_Id",careId);
        }
        if(status != null) {
            queryWrapper.eq("status",status);
        }
        queryWrapper.orderByDesc("create_time");
        List<TbOrder> list = orderMapper.selectList(queryWrapper);

        for(TbOrder order : list) {
            order.setUser(userMapper.selectById(order.getUserId()));
            order.setCareUser(userMapper.selectById(order.getCareId()));
            order.setCare(careMapper.selectById(order.getCareId()));
        }

        PageInfo<TbOrder> pageInfo = new PageInfo<>(list);
        PageResult<TbOrder> pageResult = new PageResult<>(pageInfo);
        return pageResult;
    }

    //根据id查询订单
    public TbOrder selectById(Integer orderId) {
        TbOrder order = orderMapper.selectById(orderId);
        order.setUser(userMapper.selectById(order.getUserId()));
        order.setCareUser(userMapper.selectById(order.getCareId()));
        order.setCare(careMapper.selectById(order.getCareId()));
        return order;
    }

    //修改订单
    public void updateOrder(TbOrder order) {
        orderMapper.updateById(order);
    }

    //根据userId查询订单
    public List<TbOrder> selectOrderByUserId(Integer userId) {
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return orderMapper.selectList(queryWrapper);
    }

    //根据careId查询订单
    public List<TbOrder> selectOrderByCareId(Integer careId,Integer status) {
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        if(careId != null) {
            queryWrapper.eq("care_id",careId);
        }
        if(status != null) {
            queryWrapper.eq("status",status);
        }
        return orderMapper.selectList(queryWrapper);
    }

    //查询所有
    public List<TbOrder> selectAll() {
        return orderMapper.selectList(null);
    }

    //查询接单最多的用户
    public List<GroupCountVO> selectTopOrderCare() {
        return orderMapper.selectTopOrderCare();
    }

}
