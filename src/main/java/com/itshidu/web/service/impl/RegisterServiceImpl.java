package com.itshidu.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.RegisterService;
import com.itshidu.web.util.DigestHelper;
import com.itshidu.web.vo.Result;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired UserDao _userDao;

	@Override
	public Map<String, Object> register(User user) {
		User t = _userDao.findByUsername(user.getUsername());
		if(t!=null) {
			return Result.of(100,"用户名已经存在");
		}
		if(_userDao.findByEmail(user.getEmail())!=null) {
			return Result.of(101,"邮箱已经存在,请更换");
		}
		
		user.setAvatar("default.jpg");			//	头像
		user.setCreateTime(new Date());
		user.setStatus(0);						//设置默认状态为未激活
		
		//对明文秘密进行加密
		user.setSalt(UUID.randomUUID().toString());
		String m = user.getPassword();
		String s = user.getSalt();
		String r = sha512(sha512(m)+md5(s)+sha512(m+s)).substring(1, 50);
		//String r1 = sha512(sha512(m)+md5(s)+sha512(m+s)).substring(1, 20);
		user.setPassword(r);
		
		_userDao.save(user);
		return Result.of(200).put("msg", "注册成功");
	}
	
	private String md5(String text) {
		return DigestHelper.md5(text);
	}
	private String sha512(String text) {
		return DigestHelper.sha512(text);
	}
	
	public static void main(String[] args) {
		String a = DigestHelper.md5("123456");
		String b = DigestHelper.sha512("123456");
		System.out.println(a);
		System.out.println(b);
	
	}

	@Override
	public Result login(String username, String password) {
		User user = _userDao.findByUsername(username);
		if(user==null) {
			return Result.of(100,"用户名不存在");
		}
		String m = password;
		String s = user.getSalt();
		String r = sha512(sha512(m)+md5(s)+sha512(m+s)).substring(1, 50);
		if(r.equals(user.getPassword())) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", user);
			return Result.of(200,"登录成功");
		}
		return Result.of(100,"密码不正确");
	}
	

}
