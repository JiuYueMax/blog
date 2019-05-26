package com.itshidu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	HomeService _homeService;

	@RequestMapping(value= {"","/","/index"})
	public Object index(HttpServletRequest request) {
		//如果未登录,跳转到登录页面
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("home/index");
		//mv.addObject("servletPath",request.getServletPath());
		return mv;
	}
	
	@RequestMapping("/public/logout")
	public Object logout(HttpServletRequest request) {
		//request.getSession().removeAttribute("loginInfo");//这个是移除，换一个别的session
		//request.getSession().setMaxInactiveInterval(1);//超时时间1秒钟移除掉
		request.getSession().invalidate();//废弃现有session  
		return "redirect:/login.html";
	}
	
	@RequestMapping(value= {"/follows"})
	public Object follows(@RequestParam(defaultValue="0")Integer page,HttpServletRequest request) {
		//如果未登录,跳转到登录页面
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("home/follows");
		_homeService.follows(page, mv);
		return mv;
	}
	
	@RequestMapping(value= {"/fans"})
	public Object fans(@RequestParam(defaultValue="1")Integer page,HttpServletRequest request) {
		//如果未登录,跳转到登录页面
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("home/fans");
		_homeService.fans(page, mv);
		return mv;
	}
	@RequestMapping(value= {"/notifies"})
	public Object notifies(@RequestParam(defaultValue="1")Integer page,HttpServletRequest request) {
		//如果未登录,跳转到登录页面
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("home/notifies");
		_homeService.notifies(page, mv);
		return mv;
	}
	@RequestMapping(value= {"/favors"})
	public Object favors(@RequestParam(defaultValue="1")Integer page,HttpServletRequest request) {
		//如果未登录,跳转到登录页面
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("home/favors");
		_homeService.favors(page, mv);
		return mv;
	}

	
	
}
