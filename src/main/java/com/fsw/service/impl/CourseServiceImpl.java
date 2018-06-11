package com.fsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.MyMapper;
import com.fsw.mapper.TbCourseMapper;
import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbCourse;
import com.fsw.service.CourseService;
import com.fsw.utils.FSWResult;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private MyMapper myMapper;
	@Autowired
	private TbCourseMapper courseMapper;
	
	public List<TbCourse> getCourseListType3() {
		List<TbCourse> list = myMapper.getCourseListType3();
		return list;
	}

	public List<TbCourse> getCourseListType5() {
		List<TbCourse> list = myMapper.getCourseListType5();
		return list;
	}

	public List<TbCourse> getCourseListType() {
		List<TbCourse> list = myMapper.getCourseListType();
		return list;
	}

	public List<TbCourse> getCourseWithInteresting(String[] interestingSplit) {
		StringBuffer interestingSql = new StringBuffer(); 
		interestingSql.append("2=1 ");
		for (String string : interestingSplit) {
			interestingSql.append("OR c.type = '"+string+"' " );
		}
		List<TbCourse> list = myMapper.getCourseByInteresting(interestingSql+"");
		return list;
	}

	public SelectResult selectCourse(String question, String type,String sort, String pageNow, String showNum) {
		
		SelectResult sResult = new SelectResult();
		
		try {
			
			StringBuilder sBuilder = new StringBuilder();
			
			//判断类型是不是空
			if (type != "") {
				sBuilder.append(" c.`type` = '"+type+"' ");
			}else {
				sBuilder.append(" 1=1 ");
			}
			//判断查询是不是空
			if (question != null) {
				sBuilder.append(" AND (c.name LIKE '%"+question+"%' OR c.`details` LIKE '%"+question+"%')");
				
			}else {
				sBuilder.append(" AND 1=1");
			}
			
			//判断排序是不是空
			if (sort != "") {
				if (sort.equals("1")) {
					sBuilder.append(" ORDER BY collection DESC ");
				}else if (sort.equals("2")) {
					sBuilder.append(" ORDER BY create_time DESC ");
				} 
			}
			
			String selectCount = sBuilder+"";
			sBuilder.append(" LIMIT "+((Integer.parseInt(pageNow)-1)*(Integer.parseInt(showNum)))+","+showNum+"");
			String sql = sBuilder+"";
			//查询课程
			List<TbCourse> result = myMapper.selectCourse(sql);
			//查询课程总数
			Integer courseCount = myMapper.selectCourseCount(selectCount);
			
			//将总课程转换成页数
			
			
			double d = (double)courseCount/(double)(Integer.parseInt(showNum));
			int ceil = (int)Math.ceil(d);
			
			sResult.setData(result);
			sResult.setPageCont(ceil);
			sResult.setMsg("正常");
			sResult.setStatus(200);
			
			if (result == null || result.size() == 0) {
				sResult.setMsg("没有搜索到课程");
				sResult.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sResult.setStatus(500);
			sResult.setMsg("服务器异常");
		}
		
		
		
		return sResult;
	}

	public TbCourse selectCourseById(String courseId) {
		
		TbCourse result = courseMapper.selectByPrimaryKey(Integer.parseInt(courseId));
		
		return result;
	}

	
}
