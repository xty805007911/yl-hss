package com.ctsi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/1  22:21
 */
@Data
public class TbOrderRating {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer rating;
    private String content;
    private String contentReply;
    private Date createTime;
}
