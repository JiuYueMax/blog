package com.itshidu.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

/**
 * 帖子、文章
 * @author 16419
 *
 */
@Data
@Entity
@Table(name="blog_article")
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch=FetchType.LAZY)	//懒加载
	@JoinColumn(name="user_id")
	@Cascade(CascadeType.SAVE_UPDATE)	//级联保存,级联更新,但不级联删除
	private User user;					//文章作者,发帖人
	private String title;				//文章标题
	private Date createTime;			//创建时间
	private long hits;					//点击量
	@Column(columnDefinition="TEXT")
	private String content;				//文章正文内容
	@ManyToOne
	@JoinColumn(name="forum_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Forum forum;				//所属板块
	
	
	
	
	

}











