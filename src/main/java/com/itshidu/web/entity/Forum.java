package com.itshidu.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**论坛
 * 板块、栏目、分组
 * @author 16419
 *
 */
@Data
@Entity
@Table(name = "blog_group")
public class Forum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;	//板块名称
	private String code;	//板块标记,使用字母和数字,访问的时候回座位URL的一部分
	private Boolean status;	//状态(true显示,false隐藏)
	
	
	
}
