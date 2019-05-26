package com.itshidu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.service.TaService;
import com.itshidu.web.vo.Result;


@Controller
public class TaController {
	
	@Autowired TaService _taService;

	@RequestMapping("/ta/{userId}")
	public Object f1(@PathVariable long userId,@RequestParam(defaultValue="1")Integer pn) {
		ModelAndView mv = new ModelAndView("ta/index");
		//user基本信息,user的文章分页
		Result result = _taService.findData(userId, pn,mv);
		//mv.addObject("user", result.get("user"));
		return mv;
	}
	
	@RequestMapping("/a/pictures")
	public Object aaa() {
		return "redirect:default.jpg";
	}
	
	
}
