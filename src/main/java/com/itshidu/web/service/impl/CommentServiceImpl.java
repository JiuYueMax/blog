package com.itshidu.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.CommentDao;
import com.itshidu.web.dao.NotifyDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Comment;
import com.itshidu.web.entity.Notify;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.CommentService;
import com.itshidu.web.vo.Result;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao _commentDao;
	@Autowired
	ArticleDao _articleDao;
	@Autowired
	NotifyDao _notifyDao;

	@Override
	public Result save(Long articleId, Long targetCommentId, String text) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		
		Comment c = new Comment(); // 创建一个新对象,打算保存
		Article article =_articleDao.getOne(articleId);
		
		Comment target = null; // 针对的目标
		if (targetCommentId != 0) {
			target = new Comment();
			target.setId(targetCommentId);
		}

		c.setArticle(article);
		c.setAuthor(loginUser);
		c.setContent(text);
		c.setCreated(new Date());
		c.setTarget(target);
		_commentDao.save(c);
		//给文章作者发通知
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setDate(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(article.getUser());
		notify.setUrl("/ta/"+loginUser.getId());
		String s2 = String.format("评论了你的文章 - <a href=\"/view/%s.html\">点击查看详情</a>", article.getId());
		notify.setContent(s2);
		_notifyDao.save(notify);
		return Result.of(1, "评论成功");
	}

	@Override
	public Result list(Long articleId, int pageSize, int pn) {
		Sort sort = new Sort(Direction.DESC,"created");			//排序规则:通过,sort降序排序
		Pageable pageable = PageRequest.of(pn-1, pageSize,sort);//pn是第几页,pageSize是多少数据
		Page<Comment>data = _commentDao.findByArticleId(articleId, pageable);
		
		
		return Result.of(1,"OK",data);
	}

}
