package com.fsw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.fsw.utils.FSWResult;

public interface VideoService {

	/**
	 * 根据课程查询视频
	 * @param courseId
	 * @return
	 */
	FSWResult selectVideoByCourse(Integer courseId);
	
	/**
	 * 新建视频
	 * @param name
	 * @param index
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult newVideo(String courseId,String name,String index,MultipartFile file,
			HttpServletRequest request ,HttpServletResponse response);
	
	/**
	 * 修改视频
	 * @param courseId
	 * @param name
	 * @param index
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult updateVideo(String id,String name,String index,MultipartFile file,
			HttpServletRequest request ,HttpServletResponse response);
	
	/**
	 * 删除视频
	 * @param videoId
	 * @return
	 */
	FSWResult deleteVideo(String videoId,HttpServletRequest request , HttpServletResponse response);
	
	
}
