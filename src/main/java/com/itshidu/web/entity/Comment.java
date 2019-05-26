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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="blog_comment")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="article_id")//有一个列
	private Article article;		//文章 

	private Date created;			//评论的时间
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="author_id")//有一个列
	private User author;			//评论者
	
	@Column(columnDefinition="TEXT")
	private String content;			//评论的内容
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="target_id")//有一个列
	private Comment target;			//针对的目标,回复的目标
	
	
	
	
}
