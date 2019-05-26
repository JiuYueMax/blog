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
/**
 * 关注
 * @author 16419
 *
 */
@Data
@Entity
@Table(name="blog_follows")
public class Follows {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="source_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private User source;	//发起关注的人
	
	@ManyToOne
	@JoinColumn(name="target_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private User target;	//被关注的人
	
	private Date created;	//关注的时间
	
}
