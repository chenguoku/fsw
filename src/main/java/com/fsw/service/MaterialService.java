package com.fsw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.utils.FSWResult;

public interface MaterialService {

	/**
	 * 下载素材 
	 * @param id
	 * @param request
	 * @param response
	 */
	void materialDown(String id,HttpServletRequest request , HttpServletResponse response);
	/**
	 * 根据课程查询素材
	 * @param courseId
	 * @return
	 */
	FSWResult selectMaterialByCourse(Integer courseId);
	
	/**
	 * 新建素材
	 * @param courseId
	 * @param name
	 * @param index
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult newMaterial(String courseId,String name,String index,MultipartFile file,
			HttpServletRequest request,HttpServletResponse response);
	/**
	 * 修改素材
	 * @param id
	 * @param name
	 * @param index
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult updateMaterial(String id,String name,String index,MultipartFile file,
			HttpServletRequest request,HttpServletResponse response);
	/**
	 * 删除素材
	 * @param id
	 * @return
	 */
	FSWResult deleteMaterial(String id,HttpServletRequest request , HttpServletResponse response);
}
