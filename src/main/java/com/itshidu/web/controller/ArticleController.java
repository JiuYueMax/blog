package com.itshidu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	ArticleService _articleService;
	
	@RequestMapping(value="/article/{name}",method=RequestMethod.GET)//article/create
	public Object create(@PathVariable String name,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("article/"+name);
		return mv;
		
	}
	
	@RequestMapping(value="/article/create",method=RequestMethod.POST)
	public Object create(String title,long group,String content,HttpServletRequest request) {
		//需要放置XSS攻击
		_articleService.save(title, group, content, request);
		return "redirect:/home";
		
	}
	@RequestMapping("/view/{articleId}.html")
	public Object view(@PathVariable long articleId,HttpServletRequest request,ModelAndView mv) {
		mv.setViewName("article/view");
		_articleService.view(articleId,mv);
		//mv.addObject("articleInfo", _articleService.getArticleById(articleId));
		return mv;
	}
	
}
