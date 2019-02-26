package com.fsw.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbCourse;
import com.fsw.utils.FSWResult;

public interface CourseService {
	/**
	 * 获取热门课程
	 * @return
	 */
	List<TbCourse> getCourseListType();
	/**
	 * 获取兴趣生活
	 * @return
	 */
	List<TbCourse> getCourseListType3();
	/**
	 * 获取编程开发
	 * @return
	 */
	List<TbCourse> getCourseListType5();
	/**
	 * 根据兴趣获取课程
	 * @param interestingSplit
	 * @return
	 */
	List<TbCourse> getCourseWithInteresting(String[] interestingSplit);
	
	/**
	 * 查询课程 根据问题
	 * @param question
	 * @param type
	 * @param sort
	 * @param pageNow
	 * @param showNum
	 * @return
	 */
	SelectResult selectCourse(String question,String type,String sort,String pageNow,String showNum);
	
	/**
	 * 根据id  查询课程
	 * @param courseId
	 * @return
	 */
	TbCourse selectCourseById(String courseId);
	
	/**
	 * 根据收藏查询课程
	 * @return
	 */
	List<TbCourse> selectCourseByCollection(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 创建课程
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult createCourse(String name,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 查询老师创建的课程
	 * @param request
	 * @param response
	 * @return
	 */
	List<TbCourse> selectCourseByUserId(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 修改课程图片
	 * @param imgFile
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult updateCourseImage(TbCourse course,MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 修改课程信息
	 * @param courseId
	 * @param name
	 * @param type
	 * @param info
	 * @param teacher
	 * @param status
	 * @return
	 */
	FSWResult updateCourse(TbCourse course,String name,String type,String info,String teacher,String status);
	
	/**
	 * 改变课程状态
	 * @param id
	 * @param status
	 * @return
	 */
	FSWResult changeStatus(String id,String status);
	/**
	 * 删除课程
	 * @param id
	 * @return
	 */
	FSWResult removeCourse(String id);
	
	
}
