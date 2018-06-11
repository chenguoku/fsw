package com.fsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.SelectResult;
import com.fsw.service.CourseService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;

@Controller
public class SelectCourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="goSelect")
	public String goSelect() {
		
		return "select";
	}
	
	@RequestMapping(value="select/course")
	@ResponseBody
	public SelectResult selectCourse(String question,String type,String sort,
			@RequestParam(defaultValue="1")String pageNow,@RequestParam(defaultValue="12")String showNum) {
		
		type = FSWUtils.ChangeInteresting(type);
		sort = FSWUtils.ChangeSort(sort);
		
		SelectResult result = courseService.selectCourse(question, type,sort, pageNow, showNum);
		
		return result;
	}
}
