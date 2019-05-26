package com.itshidu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itshidu.web.service.APIService;

@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	APIService _apiService;
	
	//粉丝最多的用户(目前未做粉丝功能,先用最新用户代替)
	@RequestMapping("/hotusers.json")
	public Object f1(int maxResults) {
		//5分钟
		return _apiService.hotusers(maxResults);
	}
	//最新发布的文章
	@RequestMapping("/latests.json")
	public Object f2(int maxResults) {
		//10秒之内
		return _apiService.latests(maxResults);
	}
	//最热门的文章
	@RequestMapping("/hots.json")
	public Object f3(int maxResults) {
		//1分钟
		return _apiService.hots(maxResults);
	}
	
	
}
