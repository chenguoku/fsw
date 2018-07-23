package com.fsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.TbUser;
import com.fsw.service.TestPageService;
import com.fsw.utils.FSWResult;

@Controller
public class TestPageController {

	@Autowired
	private TestPageService testPageService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("examination")
	public String goExamination(HttpServletRequest request,HttpServletResponse response,Model model) {
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		model.addAttribute("testPageId", "1");
		return "examination";
	}
	
	/**
	 * 根据试卷id获得试卷的题
	 * @param testPageId
	 * @return
	 */
	@RequestMapping("testPage/testQuestion")
	@ResponseBody
	public FSWResult getTestPageTestQuestion(String testPageId){
		
		FSWResult result = testPageService.getTestPageTestQuestion(testPageId);
		
		
		return result;
		
	}
	
	/**
	 * 根据课程id获取有多少试卷及名称
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="select/course/testpage")
	@ResponseBody
	public FSWResult selectCourseTestpage(String courseId) {
		
		FSWResult result = testPageService.selectTestPageByCourseId(Integer.parseInt(courseId));
		
		return result;
		
	}
	
}
