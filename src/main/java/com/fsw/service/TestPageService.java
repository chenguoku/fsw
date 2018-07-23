package com.fsw.service;

import com.fsw.utils.FSWResult;

public interface TestPageService {

	/**
	 * 根据试卷id获得试卷的题
	 * @param testPageId
	 * @return
	 */
	FSWResult getTestPageTestQuestion(String testPageId);
	
	/**
	 * 根据课程查询试卷
	 * @param courseId
	 * @return
	 */
	FSWResult selectTestPageByCourseId(Integer courseId);
	
}
