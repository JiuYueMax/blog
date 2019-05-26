package com.itshidu.web.entity;

import lombok.Data;

@Data
public class ArticleVO extends Article{

	private long xihuan;		//多少人喜欢
	private long pinglun;		//多少人评论
	
}
