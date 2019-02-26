package com.fsw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.utils.FSWResult;

public interface UserService {

	/**
	 * 检验是否登录
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult checkLogin(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 用户登录通过邮箱
	 * @param request
	 * @param response
	 * @param email
	 * @param password
	 * @return
	 */
	FSWResult userLoginByEmail(HttpServletRequest request, HttpServletResponse response, String email, String password);

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
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
	
	/**
	 * 修改用户名
	 * @param name
	 * @return
	 */
	String updateName(String name,HttpServletRequest request, HttpServletResponse response);
	String updateName(String id,String name);
	
	/**
	 * 修改性别
	 * @param sex
	 * @return
	 */
	String updateSex(String sex,HttpServletRequest request, HttpServletResponse response);
	String updateSex(String id,String sex);
	/**
	 * 修改密码
	 * @param newPasswd
	 * @return
	 */
	String updatePasswd(String oldPasswd,String newPasswd,HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 修改学校
	 * @param school
	 * @return
	 */
	String updateSchool(String school,HttpServletRequest request, HttpServletResponse response);
	String updateSchool(String id,String school);
	/**
	 * 更改头像
	 * @param imgFile
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult updateImg(MultipartFile imgFile,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 查询用户
	 * @param name
	 * @param type
	 * @return
	 */
	FSWResult selectUser(String name,String type);
	
	/**
	 * 删除user
	 * @param id
	 * @return
	 */
	FSWResult removeUser(String id);
	
}
