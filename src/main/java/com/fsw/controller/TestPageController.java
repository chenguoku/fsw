package com.fsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.service.TestPageService;
import com.fsw.utils.FSWResult;

@Controller
public class TestPageController {

	@Autowired
	private TestPageService testPageService;
	
	@RequestMapping(value="select/course/testpage")
	@ResponseBody
	public FSWResult selectCourseTestpage(String courseId) {
		
		FSWResult result = testPageService.selectTestPageByCourseId(Integer.parseInt(courseId));
		
		return result;
		
	}
	
}
