package com.fsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.TbVideoMapper;
import com.fsw.pojo.TbVideo;
import com.fsw.pojo.TbVideoExample;
import com.fsw.pojo.TbVideoExample.Criteria;
import com.fsw.service.VideoService;
import com.fsw.utils.FSWResult;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private TbVideoMapper videoMapper;
	
	public FSWResult selectVideoByCourse(Integer courseId) {

		try {
			TbVideoExample example = new TbVideoExample();
			Criteria criteria = example.createCriteria();
			criteria.andCourseIdEqualTo(courseId);
			example.setOrderByClause("findex asc");
			List<TbVideo> list = videoMapper.selectByExampleWithBLOBs(example);
			if (list == null || list.size() == 0) {
				return FSWResult.build(404, "没有找到课程", null);
			}
			return FSWResult.build(200, "video", list);
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器异常", null);
		}
	}

}
