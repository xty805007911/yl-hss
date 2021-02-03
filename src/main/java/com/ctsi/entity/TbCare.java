package com.ctsi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  21:22
 */
@Data
public class TbCare {
    private Integer id;//用户id
    private Double temperature;
    private Double height;
    private Double weight;
    private String healthy;
    private Date createTime;
}
