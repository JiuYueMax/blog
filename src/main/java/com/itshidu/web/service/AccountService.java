package com.itshidu.web.service;

import javax.servlet.http.HttpServletRequest;

import com.itshidu.web.vo.Result;

public interface AccountService {

	Result updatePassword(String oldPassword,String newPassword);

	/** 修改昵称,个性签名 */
	Result updateProfile(String nickname, String sign);
	
	/** 完成头像上传功能 */
	Result updateAvatar(int x,int y,int width,int height,String path,HttpServletRequest request);
	
	/** 修改邮箱 */
	Result updateEamil(String email,HttpServletRequest request);
	
	/** 喜欢的数量 */
	Result saveFavor(long articleId,HttpServletRequest request);
	
	/** 关注的人 */
	Result saveFollows(long userId);
	
	/** 当前登录的用户检查是否关注了某个人 */
	Result followCheck(long userId);
	
	/** 取消关注某个人 */
	Result unfollow(long id);
	
}
