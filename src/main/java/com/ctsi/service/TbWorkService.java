package com.ctsi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctsi.entity.TbWork;
import com.ctsi.mapper.TbWorkMapper;
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
 * @CreateDate: 2021/2/3  17:53
 */
@Service
public class TbWorkService {
    @Autowired
    private TbWorkMapper workMapper;

    // 添加工作记录
    public void add(TbWork work) {
        Date currentDate = new Date();
        work.setCreateTime(currentDate);
        work.setUpdateTime(currentDate);
        workMapper.insert(work);
    }

    // 修改工作记录
    public void update(TbWork work) {
        work.setUpdateTime(new Date());
        workMapper.updateById(work);
    }

    // 根据id查询
    public TbWork selectById(Integer id) {
        return workMapper.selectById(id);
    }

    // 分页查询
    public PageResult<TbWork> selectPageList(Integer page,Integer size,Integer userId) {
        if(page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        QueryWrapper<TbWork> queryWrapper = new QueryWrapper<>();
        if(userId != null) {
            queryWrapper.eq("user_id",userId);
        }
        List<TbWork> list = workMapper.selectList(queryWrapper);
        PageInfo<TbWork> pageInfo = new PageInfo<>(list);
        PageResult<TbWork> pageResult = new PageResult<>(pageInfo);
        return pageResult;
    }

    // 删除
    public void delete(Integer id) {
        workMapper.deleteById(id);
    }
}
