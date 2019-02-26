package com.fsw.service;

import com.fsw.pojo.TbTestPage;
import com.fsw.pojo.TbTestQuestionWithBLOBs;
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
	/**
	 * 删除测试试卷
	 * @param id
	 * @return
	 */
	FSWResult deleteTestPage(String id);
	
	/**
	 * 插入试卷
	 * @param testPage
	 * @return
	 */
	Integer insertTestPage(TbTestPage testPage);
	
	/**
	 * 插入问题
	 * @param tbTestQuestion
	 * @return
	 */
	Integer insertTestQuestion(TbTestQuestionWithBLOBs tbTestQuestion);
	
}
