package com.fsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.TbTestPageMapper;
import com.fsw.pojo.TbTestPage;
import com.fsw.pojo.TbTestPageExample;
import com.fsw.pojo.TbTestPageExample.Criteria;
import com.fsw.service.TestPageService;
import com.fsw.utils.FSWResult;

@Service
public class TestPageServiceImpl implements TestPageService {

	@Autowired
	private TbTestPageMapper testPageMapper;
	
	public FSWResult selectTestPageByCourseId(Integer courseId) {
		try {
			TbTestPageExample example = new TbTestPageExample();
			Criteria criteria = example.createCriteria();
			criteria.andCourseIdEqualTo(courseId);
			example.setOrderByClause("findex asc");
			List<TbTestPage> list = testPageMapper.selectByExample(example);
			if (list == null || list.size() == 0) {
				return FSWResult.build(404,"没有找到课程", null);
			}
			return FSWResult.build(200, "testpage", list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器异常", null);
		}
		
	}

}
