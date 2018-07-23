package com.fsw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.CourseService;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;
import com.fsw.utils.JsonUtils;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	@RequestMapping(value="index")
	public String findIndex(Model model, HttpServletRequest request,HttpServletResponse response) {

		
		
		
		
		//热门课程查询
		List<TbCourse> hotTypeList = courseService.getCourseListType();
		//兴趣生活
		List<TbCourse> type3List = courseService.getCourseListType3();
		//编程开发
		List<TbCourse> type5List = courseService.getCourseListType5();
		//判断用户是否登录
		TbUserWithBLOBs loginUser = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		if (loginUser != null) {
			if (loginUser.getInteresting() != null) {
				//获得用户兴趣
				String[] interestingSplit = loginUser.getInteresting().split(",");
				//获得兴趣课程
				List<TbCourse> interestingCourse = courseService.getCourseWithInteresting(interestingSplit);
				//将兴趣课程放到model中
				model.addAttribute("interestingCourse", interestingCourse);
			}
		}
		
		//将数据传给页面
		//热门课程
		model.addAttribute("hotTypeList", hotTypeList);
		//兴趣生活
		model.addAttribute("type3List", type3List);
		//编程开发
		model.addAttribute("type5List", type5List);
		
		return "index";
	}
	@RequestMapping(value="select/interest",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FSWResult getCourseByInteresting(HttpServletRequest request,HttpServletResponse response,String interest,@RequestParam(defaultValue="8")String count) {
		try {
			//判断用户是否登录
			TbUserWithBLOBs loginUser = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			if (loginUser != null) {
				//获取兴趣数组
				StringBuilder string = new StringBuilder();
				String[] split = interest.split(",");
				for (int i = 0; i < split.length; i++) {
					split[i] = FSWUtils.ChangeInteresting(split[i]);
					string.append(","+split[i]);
				}
				String str = string.substring(1)+"";
				loginUser.setInteresting(str);
				userService.updateUser(loginUser);
			}
		} catch (Exception e) {
			LOGGER.debug("更改用户兴趣出错！");
		}
		
		try {
			//获取兴趣数组
			String[] split = interest.split(",");
			
			for (int i = 0; i < split.length; i++) {
				split[i] = FSWUtils.ChangeInteresting(split[i]);
			}
			//根据兴趣获取课程
			List<TbCourse> list = courseService.getCourseWithInteresting(split);
			if (list == null || list.size() == 0) {
				
				return FSWResult.build(404, "not find", null);
			}
			return FSWResult.build(200, "success", list);
		} catch (Exception e) {
			return FSWResult.build(500, "err", null);
		}
		
	}
	
	@RequestMapping("exitLogin")
	public String exitLogin(HttpServletRequest request , HttpServletResponse response) {
		
		request.getSession().removeAttribute("loginUser");
		
		
		return "redirect:index.html";
		
	}
}
