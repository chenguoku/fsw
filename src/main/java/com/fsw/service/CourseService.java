package com.fsw.service;

import java.util.List;

import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbCourse;

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
	
}
