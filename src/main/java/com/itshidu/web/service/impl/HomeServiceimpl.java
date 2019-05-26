package com.itshidu.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.CommentDao;
import com.itshidu.web.dao.FavorDao;
import com.itshidu.web.dao.FollowsDao;
import com.itshidu.web.dao.NotifyDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.ArticleVO;
import com.itshidu.web.entity.Favor;
import com.itshidu.web.entity.Follows;
import com.itshidu.web.entity.Notify;
import com.itshidu.web.entity.User;
import com.itshidu.web.entity.UserVO;
import com.itshidu.web.service.HomeService;
import com.itshidu.web.util.EhcacheUtil;
import com.itshidu.web.util.LoginUtil;

@Service
public class HomeServiceimpl implements HomeService {
	
	@Autowired FollowsDao _followsDao;
	@Autowired ArticleDao _articleDao;
	@Autowired CommentDao _commentDao;
	@Autowired NotifyDao _notifyDao;
	@Autowired FavorDao _favorDao;

	@Override
	public void follows(Integer page, ModelAndView mv) {
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = PageRequest.of(page, 20);
		Page<Follows> data = _followsDao.findFollows(loginUser.getId(), pageable);
		List<UserVO> list = new ArrayList<>();
		for(Follows fo : data.getContent()) {
			User user = fo.getTarget();
			UserVO vo = new UserVO();
			BeanUtils.copyProperties(user, vo);
			//该用户发表的文章数
			int articleCount = _articleDao.countByUser(user.getId());
			int commentCount = _commentDao.countByUser(user.getId());
			vo.setArticleCount(articleCount);
			vo.setCommentCount(commentCount);
			list.add(vo);
		}
		mv.addObject("data", data);
		mv.addObject("voList",list);

	}

	@Override
	public void fans(Integer page, ModelAndView mv) {
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = PageRequest.of(page-1, 20);
		Page<Follows> data = _followsDao.findFans(loginUser.getId(), pageable);
		List<UserVO> list = new ArrayList<>();
		for(Follows fo : data.getContent()) {
			User user =fo.getSource();
			UserVO vo = new UserVO();
			BeanUtils.copyProperties(user, vo);
			//该用户发表的文章数
			int articleCount = _articleDao.countByUser(user.getId());
			int commentCount = _commentDao.countByUser(user.getId());
			vo.setArticleCount(articleCount);
			vo.setCommentCount(commentCount);
			list.add(vo);
		}
		mv.addObject("data",data);//分页数据
		mv.addObject("voList",list);//userVo列表
		
		
	}

	@Override
	public void notifies(Integer page, ModelAndView mv) {
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = PageRequest.of(page-1, 20);
		Page<Notify> data = _notifyDao.findByUser(loginUser.getId(), pageable);
		mv.addObject("data",data);
		
		
	}

	@Override
	public void favors(Integer page, ModelAndView mv) {
/*		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = PageRequest.of(page-1, 20);
		Page<Favor> data = _favorDao.findByArticle(loginUser.getId(), pageable);
		mv.addObject("data",data);*/
		
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = PageRequest.of(page, 20);
		Page<Favor> data = _favorDao.findByArticle(loginUser.getId(), pageable);
		List<UserVO> list = new ArrayList<>();
		for(Favor fo : data.getContent()) {
			User user = fo.getUser();
			UserVO vo = new UserVO();
			BeanUtils.copyProperties(user, vo);
			//该用户发表的文章数
			int articleCount = _articleDao.countByUser(user.getId());
			int commentCount = _commentDao.countByUser(user.getId());
			vo.setArticleCount(articleCount);
			vo.setCommentCount(commentCount);
			list.add(vo);
		}
		mv.addObject("data", data);
		mv.addObject("voList",list);
		
	}

}
