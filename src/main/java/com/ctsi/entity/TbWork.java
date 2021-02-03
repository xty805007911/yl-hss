package com.ctsi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/3  17:51
 */
@Data
public class TbWork {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    private String content;
    private Integer orderId;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
}
