package com.itshidu.web.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.RegisterService;

@Controller
public class PublicController {
	
	@Autowired RegisterService _registerService;

	/**
	 * 这里的话映射路径写BBB也没事因为底下他有映射了，映射到了register.ftl里面
	 * @param mv
	 * @return
	 */
	@RequestMapping("/register.html")
	public Object aaregister(ModelAndView mv) {
		mv.setViewName("register");
		return mv;
	}
	
	/** 注册 */
	@ResponseBody
	@RequestMapping("/public/register")
	public Object register(User user){
		Map<String, Object> result = _registerService.register(user);
		return result;
	}
	/** 登录 */
	@ResponseBody
	@RequestMapping("/public/login")
	public Object login(String username,String password){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  _registerService.login(username,password);
	}
	
	/*@RequestMapping("/g/{code}")
	public Object group(@PathVariable String code,HttpServletRequest request){
		//板块的code
		
		ModelAndView mv = new ModelAndView("g");
		return mv;
	}
	*/
	
	
	
}
