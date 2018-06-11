package com.fsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.service.MaterialService;
import com.fsw.utils.FSWResult;

@Controller
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value="select/course/material")
	@ResponseBody
	public FSWResult selectCourseMaterial(String courseId) {
		
		FSWResult result = materialService.selectMaterialByCourse(Integer.parseInt(courseId));
		
		return result;
	}
	
	
}
