package com.ctsi.service;

import com.ctsi.entity.TbCare;
import com.ctsi.mapper.TbCareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  15:03
 */
@Service
public class TbCareService {
    @Autowired
    private TbCareMapper careMapper;

    //查询
    public TbCare selectById(Integer id) {
        return careMapper.selectById(id);
    }

    //添加
    public void add(TbCare care) {
        care.setCreateTime(new Date());
        careMapper.insert(care);
    }

    //修改
    public void edit(TbCare care) {
        if(selectById(care.getId()) == null) {
            add(care);
        }else {
            careMapper.updateById(care);
        }
    }
}
