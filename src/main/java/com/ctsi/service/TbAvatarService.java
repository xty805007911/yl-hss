package com.ctsi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctsi.entity.TbAvatar;
import com.ctsi.mapper.TbAvatarMapper;
import com.ctsi.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  20:15
 */
@Service
public class TbAvatarService {

    @Autowired
    private TbAvatarMapper avatarMapper;

    //用户头像分页列表
    public PageResult<TbAvatar> getAvatarPageList(Integer page, Integer size) {
        if(page == null || page <= 0) {
            page = 1;
        }

        PageHelper.startPage(page,size);

        QueryWrapper<TbAvatar> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");

        List<TbAvatar> avatarList = avatarMapper.selectList(queryWrapper);

        PageInfo<TbAvatar> pageInfo = new PageInfo<>(avatarList);
        PageResult<TbAvatar> pageResult = new PageResult<>(pageInfo);
        return pageResult;
    }

    //查询所有头像
    public List<TbAvatar> getAvatarListAll() {
        return avatarMapper.selectList(null);
    }


    //用户头像添加
    public void saveAvatar(TbAvatar avatar) {
        avatar.setEnabled(1);
        avatar.setCreateTime(new Date());
        avatarMapper.insert(avatar);
    }
}
