package com.itshidu.web.service;


import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import com.itshidu.web.entity.Article;
import com.itshidu.web.vo.Result;
/**
 * 板块业务
 * @author 16419
 *
 */
public interface ForumService {

	void findForumArticles(String forumCode,String sortType,int page,ModelAndView mv);

}
