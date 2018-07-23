package com.fsw.controller;

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

import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbVideo;
import com.fsw.service.CourseService;
import com.fsw.service.VideoService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.IDUtils;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping("course/remove/video")
	@ResponseBody
	public FSWResult deleteVideo(String id,HttpServletRequest request , HttpServletResponse response) {
		FSWResult deleteVideo = videoService.deleteVideo(id,request,response);
		
		return deleteVideo;
	}
	
	@RequestMapping("newAndUpdateVideo")
	public String newAndUpdateVideo(Model model,String courseId,String name,String index,MultipartFile file,
			@RequestParam(defaultValue="0")String id,HttpServletRequest request,HttpServletResponse response) {
		if (id.equals("0")) {
			//新建
			videoService.newVideo(courseId, name, index, file, request, response);
		}else {
			//修改
			videoService.updateVideo(id, name, index, file, request, response);
		}
		model.addAttribute("courseId", courseId);
		return "redirect:goVideoManage.html";
	}
	
	@RequestMapping("goVideoManage")
	public String goVideoManage(@RequestParam(defaultValue="1")String courseId,Model model,HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		TbCourse selectCourseById = courseService.selectCourseById(courseId);
		model.addAttribute("course", selectCourseById);
		model.addAttribute("courseId", courseId);
		return "videoManage";
	}
	
	@RequestMapping("goVideoPlayer")
	public String goVideoPlayer(HttpServletRequest request , HttpServletResponse response,Model model,String courseId,@RequestParam(defaultValue="0")String index) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问课程！");
			return "login";
		}
		
		//查询课程名称
		TbCourse showCourse = courseService.selectCourseById(courseId);
		model.addAttribute("showCourse", showCourse);
		
		FSWResult result = videoService.selectVideoByCourse(Integer.parseInt(courseId));
		List<TbVideo> list = (List<TbVideo>) result.getData();
		//存入查询的课程列表
		model.addAttribute("videoList", list);
		//存入一共多少视频
		model.addAttribute("videoSize", (list.size()-1));
		//存入这是第几个视频的  序号
		model.addAttribute("videoIndex", index);
		for (int i = 0; i < list.size(); i++) {
			TbVideo tbVideo = list.get(i);
			if (i == Integer.parseInt(index)) {
				//存入正在显示的视频
				model.addAttribute("showVideo", tbVideo);
			}
		}
		return "videoPlayer";
	}
	
	@RequestMapping("select/course/video")
	@ResponseBody
	public FSWResult selectCourseVideo(String courseId) {
		
		FSWResult result = videoService.selectVideoByCourse(Integer.parseInt(courseId));
		
		return result;
		
	}
	
}
