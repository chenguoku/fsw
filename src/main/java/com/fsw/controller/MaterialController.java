package com.fsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbUser;
import com.fsw.service.CourseService;
import com.fsw.service.MaterialService;
import com.fsw.utils.FSWResult;

@Controller
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("materialDown")
	public void materialDown(String id,HttpServletRequest request , HttpServletResponse response) {
		materialService.materialDown(id,request,response);
		
	}
	
	@RequestMapping("course/remove/material")
	@ResponseBody
	public FSWResult deleteMaterial(String id,HttpServletRequest request , HttpServletResponse response) {
		
		FSWResult result = materialService.deleteMaterial(id,request,response);
		return result;
	}
	
	@RequestMapping("newAndUpdateMaterial")
	public String newAndUpdateMaterial(Model model,String courseId,String name,String index,MultipartFile file,
			@RequestParam(defaultValue="0")String id,HttpServletRequest request,HttpServletResponse response) {
		if (id.equals("0")) {
			//新建
			materialService.newMaterial(courseId, name, index, file, request, response);
		}else {
			//修改
			materialService.updateMaterial(id, name, index, file, request, response);
		}
		model.addAttribute("courseId", courseId);
		return "redirect:goMaterialManage.html";
	}
	
	@RequestMapping("goMaterialManage")
	public String goMaterialManage(@RequestParam(defaultValue="1")String courseId,Model model,HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		
		TbCourse selectCourseById = courseService.selectCourseById(courseId);
		model.addAttribute("course", selectCourseById);
		
		return "materialManage";
	}
	
	@RequestMapping(value="select/course/material")
	@ResponseBody
	public FSWResult selectCourseMaterial(String courseId) {
		
		FSWResult result = materialService.selectMaterialByCourse(Integer.parseInt(courseId));
		
		return result;
	}
	
	
}
