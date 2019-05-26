package com.itshidu.web.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.itshidu.web.dao.ForumDao;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.util.SpringContext;

public class MyListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = SpringContext.getApplicationContext();
		ForumDao groupDao = ctx.getBean(ForumDao.class);
		System.out.println("----------------"+groupDao);
		
		/*Group probe = new Group();
		probe.setStatus(true);
		ExampleMatcher matcher = ExampleMatcher.matching();
		matcher.withIgnoreCase("id");
		Example<Group> example  = Example.of(probe, matcher);
		List<Group> list = groupDao.findAll(example);*/
		List<Forum> list =groupDao.findByStatus(true);
		
		for(Forum g : list) {
			System.out.println(g.getName());
		}
		sce.getServletContext().setAttribute("FORUM_LIST", list);
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	

}
