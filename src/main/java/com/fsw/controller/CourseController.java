package com.fsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbUser;
import com.fsw.service.CommentsService;
import com.fsw.service.CourseService;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping("updateCourseInfo")
	public String updateCourseInfo(Model model,String courseId,String name,String type,
			String info,String teacher,String status) {
//		name=测试&type=分类名称&info=1111&teacher=2222&on-off=on
		
		TbCourse course = courseService.selectCourseById(courseId);
		
		courseService.updateCourse(course, name, type, info, teacher, status);
		
		model.addAttribute("courseId", courseId);
		return "redirect:goSetCourse.html";
	}
	
	@RequestMapping("updateCourseImage")
	public String updateCourseImage(Model model,String courseId,MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
		
		TbCourse selectCourseById = courseService.selectCourseById(courseId);
		
		courseService.updateCourseImage(selectCourseById,imgFile, request, response);
		
		model.addAttribute("courseId", courseId);
		return "redirect:goSetCourse.html";
	}
	
	/**
	 * 老师 去课程设置页面
	 * @return
	 */
	@RequestMapping("goSetCourse")
	public String goSetCourse(@RequestParam(defaultValue="1")String courseId,Model model,HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		TbCourse managerCourse = courseService.selectCourseById(courseId);
		model.addAttribute("course", managerCourse);
		
		return "setCourse";
	}
	
	/**
	 * 创建课程
	 * @return
	 */
	@RequestMapping("createCourse")
	public String createCourse(String name,Model model , HttpServletRequest request , HttpServletResponse response) {
		
		FSWResult result = courseService.createCourse(name,request,response);
		
		return "redirect:goCourseManager.html";
	}
	
	
	/**
	 * 去课程管理页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("goCourseManager")
	public String goCourseManager(Model model , HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		//查询励志语句
		String inspiringWords = FSWUtils.getInspiringWords();
		model.addAttribute("inspiringWords", inspiringWords);
		
		//查询创建课程
		List<TbCourse> list = courseService.selectCourseByUserId(request, response);
		
		model.addAttribute("collectionCourse", list);
		
		
		return "courseManager";
	}
	
	@RequestMapping("goMyStudy")
	public String goMyStudy(Model model , HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		//查询励志语句
		String inspiringWords = FSWUtils.getInspiringWords();
		model.addAttribute("inspiringWords", inspiringWords);
		
		//查询收藏课程
		List<TbCourse> list = courseService.selectCourseByCollection(request, response);
		
		model.addAttribute("collectionCourse", list);
		
		return "myStudy";
	}
	
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
