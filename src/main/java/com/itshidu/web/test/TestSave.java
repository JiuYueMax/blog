package com.itshidu.web.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.ForumDao;
import com.itshidu.web.dao.StudentDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.entity.Student;
import com.itshidu.web.entity.User;

public class TestSave {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
		/*StudentDao dao = ctx.getBean(StudentDao.class);
		Date dt = new Date();
		for(int i=1;i<=200;i++) {
			Student s = new Student();
			s.setName("悟空"+i);
			s.setCode(String.format("XH%04d", i));
			s.setCreateTime(dt);
			s.setUpdateTime(dt);
			dao.save(s);
		}*/

		UserDao userDao = ctx.getBean(UserDao.class);
		ForumDao forumDao = ctx.getBean(ForumDao.class);
		ArticleDao articleDao = ctx.getBean(ArticleDao.class);
		Date dt = new Date();
		long time = dt.getTime();
		User user = userDao.getOne((long) 1);
		Forum forum = forumDao.getOne((long) 1);
		for(int i = 1; i<=100; i++) {
			Article a = new Article();
			a.setCreateTime(new Date(time));
			a.setForum(forum);
			a.setUser(user);
			a.setTitle("标题:  "+i);
			a.setContent(i+"《Java从入门到精通》是人民邮电出版社于 2010年出版的图书，由国家863中部软件孵化器主编。以零基础讲解为宗旨，深入浅出地讲解Java的各项技术及实战技能。本书从初学者角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用Java语言进行程序开发应该掌握的各方面技术。");
			a.setHits((long) (Math.random()*1000));
			articleDao.save(a);
			time+=2000;
		}
		
		
	}

}
