package com.itshidu.web.entity;

import lombok.Data;

@Data
public class UserVO extends User {

	private int articleCount;	//该用户发表的文章数
	private int commentCount;	//该用户发表的评论数
}
