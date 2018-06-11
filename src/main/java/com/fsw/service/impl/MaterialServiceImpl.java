package com.fsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.TbMaterialMapper;
import com.fsw.pojo.TbMaterial;
import com.fsw.pojo.TbMaterialExample;
import com.fsw.pojo.TbMaterialExample.Criteria;
import com.fsw.service.MaterialService;
import com.fsw.utils.FSWResult;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private TbMaterialMapper materialMapper;
	
	public FSWResult selectMaterialByCourse(Integer courseId) {
		try {
			TbMaterialExample example = new TbMaterialExample();
			Criteria criteria = example.createCriteria();
			criteria.andCourseIdEqualTo(courseId);
			example.setOrderByClause("findex asc");
			List<TbMaterial> list = materialMapper.selectByExample(example);
			if (list == null || list.size() == 0) {
				return FSWResult.build(404, "没有找到素材", null);
			}
			
			return FSWResult.build(200, "material", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器异常", null);
		}
	}

	
	
}
