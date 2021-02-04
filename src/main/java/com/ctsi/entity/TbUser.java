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

	@Override
	public boolean equals(Object o) {
		TbUser user = (TbUser)o;
		return id == user.id;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
		result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
		result = 31 * result + (realname != null ? realname.hashCode() : 0);
		return result;
	}
}