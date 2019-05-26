package com.itshidu.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itshidu.web.entity.Follows;

public interface FollowsDao extends JpaRepository<Follows, Long>{

	/**我关注了谁*/
	@Query("from Follows f where f.source.id=?1")
	Page<Follows> findFollows(long sourceUserId,Pageable pageable);
	
	/**谁关注了我,我的粉丝*/
	@Query("from Follows f where f.target.id=?1")
	Page<Follows> findFans(long targetUserId,Pageable pageable);
	
	/** 一个人关注了另外一个人的记录 */
	@Query("from Follows f where f.source.id=?1 and f.target.id=?2")
	Follows find(long source,long target);
	
	
}
