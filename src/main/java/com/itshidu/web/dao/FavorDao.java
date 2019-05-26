package com.itshidu.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itshidu.web.entity.Favor;

public interface FavorDao extends JpaRepository<Favor, Long>{

	@Query("from Favor f where f.user.id=?1 and f.article.id=?2")
	Favor find(long user,long article);
	
	@Query("select count(id) from Favor f where f.article.id=?1")
	int countByArticle(long articleId);
	
	//@Query("SELECT count(id) from Favor f where f.article.id=?1")
	@Query("from Favor f where f.user.id=?1")
	Page<Favor> findByArticle(long id,Pageable pageable);
	
}
