package com.fsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbUser;
import com.fsw.service.CommentsService;
import com.fsw.service.CourseService;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("goCourseInfo")
	public String goCourseInfo(Model model,String courseId,HttpServletRequest request, HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问课程！");
			return "login";
		}
		
		
		//查询是否收藏课程
		Boolean whetherCollection = userService.whetherCollection(courseId,request,response);
		if (whetherCollection) {
			model.addAttribute("whetherCollection", 1);
			
		}else {
			model.addAttribute("whetherCollection", 2);
			
		}
		
		//查询热门评论 5条
		
		List<TbComments> selectHotComments = commentsService.selectHotComments(courseId,"5");
		model.addAttribute("hotComments", selectHotComments);
		
		//根据id 查询课程
		TbCourse course = courseService.selectCourseById(courseId);
		model.addAttribute("course", course);
		
		model.addAttribute("courseId", courseId);
		return "courseInfo";
	}
	
	@RequestMapping("course/collection")
	@ResponseBody
	public FSWResult courseCollection(String courseId,Boolean bool,HttpServletRequest request, HttpServletResponse response) {
		
		FSWResult result = userService.courseCollection(courseId,bool,request,response);
		
		return result;
	}
	
}
