package com.ctsi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctsi.entity.TbAvatar;
import com.ctsi.entity.TbCare;
import com.ctsi.entity.TbUser;
import com.ctsi.mapper.TbAvatarMapper;
import com.ctsi.mapper.TbCareMapper;
import com.ctsi.mapper.TbUserMapper;
import com.ctsi.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  15:14
 */
@Service
public class TbUserService {

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbAvatarMapper avatarMapper;
    @Autowired
    private TbCareMapper careMapper;

    // 根据手机号和密码查询（用户登录）
    public TbUser selectUserByMobileAndPassword(String mobile,String password) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        queryWrapper.eq("password",password);
        TbUser dbUser = userMapper.selectOne(queryWrapper);
        return dbUser;
    }

    // 根据id查询
    public TbUser selectUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    // 修改用户信息
    public void updateUser(TbUser user) {
        userMapper.updateById(user);
    }

    // 保存用户信息
    public void insertUser(TbUser formUser) {
        formUser.setCreateTime(new Date());
        //选择一个随机的头像
        List<TbAvatar> avatarList = avatarMapper.selectList(new QueryWrapper<>());
        Random random = new Random();
        TbAvatar randomAvatar = avatarList.get(random.nextInt(avatarList.size()));
        formUser.setAvatar(randomAvatar.getUrl());
        userMapper.insert(formUser);
    }

    // 检查手机号是否存在
    public boolean isMobileExist(String mobile) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        List<TbUser> userList = userMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(userList)) {
            return false;
        }else {
            return true;
        }
    }

    // 用户分页查询
    public PageResult<TbUser> selectUserPageList(Integer roleId,Integer page,Integer size) {
        if(page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if(roleId != null) {
            queryWrapper.eq("role_id",roleId);
        }
        queryWrapper.orderByDesc("create_time");

        List<TbUser> userList = userMapper.selectList(queryWrapper);
        PageInfo<TbUser> pageInfo = new PageInfo<>(userList);
        PageResult<TbUser> pageResult = new PageResult<>(pageInfo);
        return pageResult;
    }

    //根据角色查询所有用户
    public List<TbUser> selectAllUserByRole(Integer roleId) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if(roleId != null) {
            queryWrapper.eq("role_id",roleId);
        }
        return userMapper.selectList(queryWrapper);
    }

    //分页查询id不在某个数组的用户
    public PageResult<TbUser> selectUserNotInId(Integer roleId,List<Integer> ids,Integer page,Integer size) {
        if(page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if(roleId != null) {
            queryWrapper.eq("role_id",roleId);
        }
        if(!CollectionUtils.isEmpty(ids)) {
            queryWrapper.notIn("id",ids);
        }

        queryWrapper.orderByDesc("create_time");

        List<TbUser> userList = userMapper.selectList(queryWrapper);
        PageInfo<TbUser> pageInfo = new PageInfo<>(userList);
        PageResult<TbUser> pageResult = new PageResult<>(pageInfo);
        return pageResult;
    }
}
