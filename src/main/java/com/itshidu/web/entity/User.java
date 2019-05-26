package com.itshidu.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="blog_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;	//数据库中村密文密码
	private String salt;		//密码盐值，作料
	private String email;
	private String phone;
	private String sex;
	private String nickname;	//昵称
	private String sign;		//个性签名
	private String avatar="/defaullt.png";		//头像
	private Date createTime;	//时间
	private int status;			//状态(0未激活,1正常,2禁用)
	
	
	
}
