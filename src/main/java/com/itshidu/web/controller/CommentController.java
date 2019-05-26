package com.itshidu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.CommentService;

@Controller
public class CommentController {

	
	@Autowired
	CommentService _commentService;
	
	@ResponseBody
	@RequestMapping("/comment/submit")
	public Object submit(Long toId,Long pid,String text) {
		//toId=评论的文章,  pid=针对的评论,   text=内容
		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}*/
		return _commentService.save(toId,pid,text);
	}
	
	@ResponseBody
	@RequestMapping("/comment/list/{articleId}")
	public Object list(@PathVariable Long articleId,int pageSize,int pn) {
		
		//return null;
		///_commentService.list(articleId,pageSize,pn).get("data");
		
		//System.out.println("----------");
		
		return _commentService.list(articleId,pageSize,pn).get("data");
	}
	
	
	
}
