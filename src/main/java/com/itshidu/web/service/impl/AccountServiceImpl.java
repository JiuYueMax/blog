package com.itshidu.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.FavorDao;
import com.itshidu.web.dao.FollowsDao;
import com.itshidu.web.dao.NotifyDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Favor;
import com.itshidu.web.entity.Follows;
import com.itshidu.web.entity.Notify;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.AccountService;
import com.itshidu.web.util.DigestHelper;
import com.itshidu.web.vo.Result;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	UserDao userDao;
	@Autowired
	FavorDao _favorDao;
	@Autowired
	FollowsDao _followsDao;
	@Autowired
	NotifyDao _notifyDao;
	@Autowired
	ArticleDao _articleDao;

	@Value("${SORT_ROOT_PATH}")
	String StoreRootPath;// 存储根目录

	@Override
	public Result updatePassword(String oldPassword, String newPassword) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		User user = userDao.getOne(loginUser.getId());
		String m = oldPassword; // 明文密码
		String s = user.getSalt(); // 密码盐值
		String r = sha512(sha512(m) + md5(s) + sha512(m + s)).substring(1, 50);

		if (r.equals(user.getPassword())) {
			System.out.println("111111111111");
			user.setSalt(UUID.randomUUID().toString());// 改个新的盐,更加安全,不改也可以
			String m1 = newPassword;
			String s1 = user.getSalt();
			String r1 = sha512(sha512(m1) + md5(s1) + sha512(m1 + s1)).substring(1, 50);
			user.setPassword(r1);
			userDao.save(user);
			return Result.of(200);
		} else {
			System.out.println("22222222222222");
			return Result.of(300);
		}
	}

	private String md5(String text) {
		return DigestHelper.md5(text);
	}

	private String sha512(String text) {
		return DigestHelper.sha512(text);
	}

	@Override
	public Result updateProfile(String nickname, String sign) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		User user = userDao.getOne(loginUser.getId());
		user.setNickname(nickname);
		user.setSign(sign);
		userDao.save(user);
		session.setAttribute("loginInfo", user);
		return null;
	}

	@Override
	public Result updateAvatar(int x, int y, int width, int height, String path, HttpServletRequest request) {
		Date date = new Date();
		String a = "/store/avatar/" + new SimpleDateFormat("yyyy").format(date) + File.separator// 分割器
				+ new SimpleDateFormat("MM").format(date) + "/" + new SimpleDateFormat("dd").format(date) + "/"
				+ UUID.randomUUID().toString() + ".jpg";
		File local = new File(StoreRootPath, a);
		File dir = local.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(StoreRootPath, path);
		try {// 拿过上传的图片进行裁剪,裁剪好之后存放到另一个目录下
			Thumbnails.of(file).sourceRegion(x, y, width, height).size(width, height).outputFormat("jpg").toFile(local);
			User loginUser = (User) request.getSession().getAttribute("loginInfo");
			if (loginUser == null) {
				return Result.of(1);// 1代表未登录
			}
			User user = userDao.getOne(loginUser.getId());
			user.setAvatar(a);
			userDao.save(user);
			request.getSession().setAttribute("loginInfo", user);
			return Result.of(2);// 2代表修改成功

		} catch (IOException e) {
			e.printStackTrace();
			return Result.of(3, e.toString());
		}

	}

	@Override
	public Result updateEamil(String email, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("loginInfo");
		if (loginUser == null) {
			return Result.of(1);// 1代表未登录
		}
		User user = userDao.getOne(loginUser.getId());
		if(email!=user.getEmail()) {
			
			user.setEmail(email);
			System.out.println(email);
			userDao.save(user);
			System.out.println("修改之后的邮箱:"+email+UUID.randomUUID().toString());
			request.getSession().setAttribute("loginInfo", user);
			return Result.of(100);// 2代表修改成功
		}else {
			return Result.of(300);
		}
		
		

	}

	@Override
	public Result saveFavor(long articleId, HttpServletRequest request) {
		User loginUser=getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		Article article =_articleDao.getOne(articleId);
		//article.setId(articleId);
		if(_favorDao.find(loginUser.getId(), articleId)!=null) {
			return Result.of(1,"不能重复喜欢");
		}
		Favor favor = new Favor();
		favor.setArticle(article);
		favor.setCreated(new Date());
		favor.setUser(loginUser);
		_favorDao.save(favor);
		//给文章作者发送通知
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setDate(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(article.getUser());
		notify.setUrl("/ta/"+loginUser.getId());
		String s2 = String.format("喜欢了你的文章 - <a href=\"/view/%s\">%s</a>", article.getId(),article.getTitle());
		notify.setContent(s2);
		_notifyDao.save(notify);
		
		
		return Result.of(2, "点赞成功");
	}

	private User getLoginUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
									.getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		return loginUser;
	}

	@Override
	public Result saveFollows(long userId) {
		User loginUser = getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		if(_followsDao.find(loginUser.getId(), userId)!=null) {
			return Result.of(3,"已经关注了");
		}
		Follows follow = new Follows();
		follow.setCreated(new Date());
		follow.setSource(loginUser);
		User target = new User();
		target.setId(userId);
		follow.setTarget(target);
		_followsDao.save(follow);
		//给被关注的人发送通知
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setDate(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(target);
		notify.setUrl("/ta/"+loginUser.getId());
		notify.setContent("关注了你,你的粉丝+1");
		_notifyDao.save(notify);
		
		return Result.of(1,"关注成功");
	}

	@Override
	public Result followCheck(long userId) {
		User loginUser = getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录！！");
		}
		Follows follow  = _followsDao.find(loginUser.getId(), userId);
		if(follow==null) {
			return Result.of(1,"未关注！！！");
		}
		return Result.of(2,"已关注！！");
	}

	@Override
	@Transactional
	public Result unfollow(long id) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginInfo");
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		Follows fo = _followsDao.find(loginUser.getId(), id);
		_followsDao.delete(fo);
		
		return Result.of(1,"取消关注成功!");
	}
}
