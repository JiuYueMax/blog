package com.itshidu.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.APIService;
import com.itshidu.web.util.EhcacheUtil;

import net.sf.ehcache.Ehcache;

@Service
public class APIServiceImpl implements APIService {

	@Autowired
	ArticleDao _articleDao;
	@Autowired
	UserDao _userDao;

	@Override
	public List<User> hotusers(int maxSize) {
		List<User> data = EhcacheUtil.get("Cache1", "user_hotusers");
		if (data == null || data.isEmpty()) {
			Sort sort = new Sort(Direction.DESC, "createTime");
			Pageable pageable = PageRequest.of(0, maxSize, sort);
			Page<User> page = _userDao.findAll(pageable);
			data = page.getContent();
			EhcacheUtil.put("Cache1", "user_hotusers", data, 30, 30);// 第一个10是最多活10秒,第二个是多久不访问就消失
		}
		return data;
	}

	@Override
	public List<Article> latests(int maxSize) {
		// 先查询缓存中是否存在对应的数据
		// 如果存在则直接使用,不存在则发起SQL查询
		// 缓存设置超时时间
		List<Article> data = EhcacheUtil.get("Cache1", "article_latests");
		if (data == null || data.isEmpty()) {
			//System.out.println("未发现缓存,发起SQL");
			Sort sort = new Sort(Direction.DESC, "createTime");
			Pageable pageable = PageRequest.of(0, maxSize, sort);
			Page<Article> page = _articleDao.findAll(pageable);
			data = page.getContent();
			EhcacheUtil.put("Cache1", "article_latests", data, 10, 10);// 第一个10是最多活10秒,第二个是多久不访问就消失
		} else {
			//System.out.println("缓存命中,直接使用");
		}
		return data;
	}

	@Override
	public List<Article> hots(int maxSize) {
		List<Article> data = EhcacheUtil.get("Cache1", "hotArticles");
		if (data == null || data.isEmpty()) {
			Sort sort = new Sort(Direction.DESC, "hits");
			Pageable pageable = PageRequest.of(0, maxSize, sort);
			Page<Article> page = _articleDao.findAll(pageable);
			data = page.getContent();
			EhcacheUtil.put("Cache1", "hotArticles", data, 60, 60);// 第一个10是最多活10秒,第二个是多久不访问就消失
		}
		return data;
	}

}
