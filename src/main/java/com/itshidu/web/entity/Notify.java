package com.itshidu.web.entity;

import java.util.Date;

import javax.persistence.Column;
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

@Data
@Entity
@Table(name="blog_notify")
public class Notify {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String avatar;
	
	private String title;

	private Date date;

	@Column(columnDefinition="TEXT")
	private String content;
	@ManyToOne
	@JoinColumn(name="user_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private User user;
	
	private String url;

}
