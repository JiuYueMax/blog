package com.itshidu.web.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.FavorDao;
import com.itshidu.web.dao.ForumDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.ArticleVO;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.ArticleService;
import com.itshidu.web.util.EhcacheUtil;
import com.itshidu.web.vo.Result;
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired UserDao _userDao;
	@Autowired ForumDao _forumDao;
	@Autowired ArticleDao _articleDao;
	@Autowired FavorDao _favorDao;

	@Override
	public Result save(String title, long group, String content, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		User user = _userDao.getOne(loginUser.getId());
		Forum forum = _forumDao.getOne(group);
		Article article = new Article();
		article.setContent(content);
		article.setCreateTime(new Date());
		article.setForum(forum);
		article.setHits(0L);
		article.setTitle(title);
		article.setUser(user);
		_articleDao.save(article);
		
		return null;
	}
	

	@Override
	public Article getArticleById(long articleId) {
		
		return _articleDao.getOne(articleId);
	}


	@Override
	public void view(long articleId, ModelAndView modelAndview) {
		Article a = _articleDao.getOne(articleId);
		ArticleVO vo = new ArticleVO();
		BeanUtils.copyProperties(a, vo);
		//System.out.println("查询喜欢的数量："+vo.getXihuan());
		
		//查询喜欢数,设置10s缓存
		String key2 = "wzxh_"+a.getId();
		Integer favorCount = EhcacheUtil.get("mytest", key2);
		if(favorCount==null) {
			favorCount = _favorDao.countByArticle(a.getId());
			EhcacheUtil.put("mytest", key2, favorCount,10,10);
		}
		vo.setXihuan(favorCount);
		modelAndview.addObject("articleInfo",vo);
		
	}

	

}
