package com.itshidu.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itshidu.web.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	/** 通过用户名查询 */
	User findByUsername(String username);
	/** 通过邮箱查询 */
	User findByEmail(String email);
	
	
	
}
