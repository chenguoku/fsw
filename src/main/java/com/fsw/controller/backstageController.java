package com.fsw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class backstageController {

	
	@RequestMapping("goBackstageCommentManage")
	public String goBackstageCommentManage() {
		return "backstageCommentManage";
	}
	
	@RequestMapping("goBackstageManagement")
	public String goBackstageManagement() {
		return "backstageManagement";
	}
	
	@RequestMapping("goTeacherManagement")
	public String goTeacherManagement() {
		return "teacherManagement";
		
	}
	@RequestMapping("backstageCourseManage")
	public String goBackstageCourseManage() {
		return "backstageCourseManage";
	}
	
}
