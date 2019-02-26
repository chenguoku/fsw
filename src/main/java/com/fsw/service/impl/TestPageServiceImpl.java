package com.fsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.MyMapper;
import com.fsw.mapper.TbTestPageMapper;
import com.fsw.mapper.TbTestQuestionMapper;
import com.fsw.pojo.TbTestPage;
import com.fsw.pojo.TbTestPageExample;
import com.fsw.pojo.TbTestPageExample.Criteria;
import com.fsw.pojo.TbTestQuestionExample;
import com.fsw.pojo.TbTestQuestionWithBLOBs;
import com.fsw.service.TestPageService;
import com.fsw.utils.FSWResult;

@Service
public class TestPageServiceImpl implements TestPageService {

	@Autowired
	private TbTestPageMapper testPageMapper;
	@Autowired 
	private TbTestQuestionMapper questionMapper;
	@Autowired
	private MyMapper myMapper;
	
	public FSWResult selectTestPageByCourseId(Integer courseId) {
		try {
			TbTestPageExample example = new TbTestPageExample();
			Criteria criteria = example.createCriteria();
			criteria.andCourseIdEqualTo(courseId);
			example.setOrderByClause("findex asc");
			List<TbTestPage> list = testPageMapper.selectByExample(example);
			if (list == null || list.size() == 0) {
				return FSWResult.build(404,"没有找到试卷", null);
			}
			return FSWResult.build(200, "testpage", list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器异常", null);
		}
		
	}

	public FSWResult getTestPageTestQuestion(String testPageId) {
		
		try {
			
			TbTestQuestionExample example = new TbTestQuestionExample();
			com.fsw.pojo.TbTestQuestionExample.Criteria criteria = example.createCriteria();
			criteria.andTestPageIdEqualTo(Integer.parseInt(testPageId));
			example.setOrderByClause("findex ASC");
			List<TbTestQuestionWithBLOBs> list = questionMapper.selectByExampleWithBLOBs(example);
			
			if (list == null || list.size() == 0) {
				return FSWResult.build(404, "没有找到试卷",null);
			}
			
			return FSWResult.build(200, "成功", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器错误",null);
		}
		
	}

	public FSWResult deleteTestPage(String id) {
		try {
			//先删除 试卷 
			testPageMapper.deleteByPrimaryKey(Integer.parseInt(id));
			//在删除 试题
			TbTestQuestionExample example = new TbTestQuestionExample();
			com.fsw.pojo.TbTestQuestionExample.Criteria criteria = example.createCriteria();
			criteria.andTestPageIdEqualTo(Integer.parseInt(id));
			questionMapper.deleteByExample(example);
			
			return FSWResult.build(200, "成功");
		} catch (Exception e) {
			return FSWResult.build(400, "没有找到试卷");
		}
	}

	public Integer insertTestPage(TbTestPage testPage) {
		
		testPageMapper.insert(testPage);
		String sql = " ORDER BY id DESC LIMIT 0,1 ";
		Integer selectMaxTestPage = myMapper.selectMaxTestPage(sql);
		
		
		return selectMaxTestPage;
	}

	public Integer insertTestQuestion(TbTestQuestionWithBLOBs tbTestQuestion) {
		int insert = questionMapper.insert(tbTestQuestion);
		
		return insert;
	}

}
