package com.ctsi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TbUser {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String mobile;
	private String address;
	private Date createTime;
	private Integer enabled;
	private String avatar;
	private Integer roleId;
	private String realname;


}