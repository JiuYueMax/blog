package com.itshidu.web.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Entity
@Data
@Table(name="blog_favor")
public class Favor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private User user;
	@ManyToOne
	@JoinColumn(name="article_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Article article;
	
	private Date created;
	
}
