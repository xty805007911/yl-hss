package com.ctsi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  18:26
 */
@Data
public class TbOrder {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer careId;
    private Integer status;
    private Date orderTime;
    private Date createTime;
    private Date endTime;
    private String userDemo;
    private String careDemo;

    @TableField(exist = false)
    private TbUser user;//用户
    @TableField(exist = false)
    private TbUser careUser;//护工
    @TableField(exist = false)
    private TbCare care;//护工健康信息

    @TableField(exist = false)
    private double w;

    public double getW() {
        if(this.status == 0) {
            return  0;
        }
        if(this.status == -1) {
            return  -1;
        }
        if(this.status == -2) {
            return  -0.5;
        }
        if(this.status == 1) {
            return  0.5;
        }
        if(this.status == 2) {
            return  1;
        }
        return 0;
    }

}
