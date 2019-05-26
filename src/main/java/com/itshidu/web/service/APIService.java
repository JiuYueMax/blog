package com.itshidu.web.service;

import java.util.List;

import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;

public interface APIService {

	/** 最热用户 */
	List<User> hotusers(int maxSize);
	/** 最新文章 */
	List<Article> latests(int maxSize);
	/** 最高点击量文章 */
	List<Article> hots(int maxSize);
}
