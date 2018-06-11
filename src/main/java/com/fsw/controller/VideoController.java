package com.fsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.TbVideo;
import com.fsw.service.VideoService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.IDUtils;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@RequestMapping("goVideoPlayer")
	public String goVideoPlayer(Model model,String courseId,@RequestParam(defaultValue="0")String index) {
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
