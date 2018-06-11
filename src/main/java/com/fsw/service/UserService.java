package com.fsw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.utils.FSWResult;

public interface UserService {

	FSWResult checkLogin(HttpServletRequest request, HttpServletResponse response);

	FSWResult userLoginByEmail(HttpServletRequest request, HttpServletResponse response, String email, String password);

	int updateUser(TbUserWithBLOBs user);
	/**
	 * 注册验证码
	 * @param email
	 * @return
	 */
	FSWResult registerCode(String email,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 根据邮箱查询用户
	 * @param email
	 * @return
	 */
	TbUser findUserByEmail(String email);
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	FSWResult register(TbUserWithBLOBs user,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 课程收藏
	 * @param courseId
	 * @param bool
	 * @return
	 */
	FSWResult courseCollection(String courseId,Boolean bool,HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 判断是否 收藏
	 * @param courseId
	 * @param request
	 * @param response
	 * @return
	 */
	Boolean whetherCollection(String courseId,HttpServletRequest request, HttpServletResponse response);
}
