package com.fsw.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.fsw.mapper.TbUserMapper;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserExample;
import com.fsw.pojo.TbUserExample.Criteria;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;
import com.fsw.utils.SendEmail;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper userMapper;
	

	public FSWResult checkLogin(HttpServletRequest request, HttpServletResponse response) {
		
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		//创作查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(user.getEmail());
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return FSWResult.ok(false);
		}
		
		return FSWResult.ok(true);
		
	}


	public FSWResult userLoginByEmail(HttpServletRequest request, HttpServletResponse response, String email,
			String password) {
		TbUserExample example = new TbUserExample();
		Criteria citeria = example.createCriteria();
		citeria.andEmailEqualTo(email);
		
		List<TbUserWithBLOBs> list = userMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0 ) {
			return FSWResult.build(404, "邮箱或密码错误！");
		}
		TbUserWithBLOBs user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPasswd()) ) {
			return FSWResult.build(404, "邮箱或密码错误！");
		}
		
		request.getSession().setAttribute("loginUser", user);
		
		return FSWResult.ok();
	}


	public int updateUser(TbUserWithBLOBs user) {
		int num = userMapper.updateByPrimaryKeyWithBLOBs(user);
		return num;
	}


	public FSWResult registerCode(String email,HttpServletRequest request,HttpServletResponse response) {
		String code = FSWUtils.getRegisterCode();
		try {
			new SendEmail(email, "发生网", "您好", "您的验证码为："+code);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("registerCode", code);
		} catch (Exception e) {
			return FSWResult.build(500, "发送错误");
		}
		return FSWResult.build(200, "");
	}


	public TbUser findUserByEmail(String email) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		List<TbUser> result = userMapper.selectByExample(example);
		if (result == null || result.size() == 0) {
			TbUser user = new TbUser();
			user.setEmail("404");
			return user;
		}
		return result.get(0);
	}


	public FSWResult register(TbUserWithBLOBs user,HttpServletRequest request,HttpServletResponse response) {
		
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPasswd(DigestUtils.md5DigestAsHex(user.getPasswd().getBytes()));
		user.setType(1+"");
		user.setImageurl("/image/user/0.jpg");
		int result = userMapper.insert(user);
		request.getSession().setAttribute("loginUser", user);
		return FSWResult.ok(result);
	}


	public FSWResult courseCollection(String courseId, Boolean bool,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			String[] split = user.getCollection().split(",");
			
			if (bool) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("0");
				//收藏
				for (String string : split) {
					//判断是否有该课程
					if (string.equals(courseId)) {
						return FSWResult.build(404, "已经收藏过该课程");
					}
					if (string.equalsIgnoreCase("0")) {
						continue;
					}
					stringBuilder.append(","+string);
				}
				stringBuilder.append(","+courseId);
				user.setCollection(stringBuilder+"");
				userMapper.updateByPrimaryKeyWithBLOBs(user);
			}else {
				//取消收藏
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("0");
				for (String string : split) {
					//判断是否有该课程
					if (string.equals(courseId)) {
						continue;
					}
					if (string.equalsIgnoreCase("0")) {
						continue;
					}
					stringBuilder.append(","+string);
				}
				user.setCollection(stringBuilder+"");
				userMapper.updateByPrimaryKeyWithBLOBs(user);
			}
			
			return FSWResult.build(200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "收藏失败");
		}
		
		
		
	}


	public Boolean whetherCollection(String courseId, HttpServletRequest request, HttpServletResponse response) {
		
		TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		String[] split = user.getCollection().split(",");
		boolean flag = false ;
		for (String string : split) {
			if (courseId.equals(string)) {
				flag = true;
			}
		}
		
		return flag;
	}



}
