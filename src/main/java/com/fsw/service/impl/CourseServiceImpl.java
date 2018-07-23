package com.fsw.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.mapper.MyMapper;
import com.fsw.mapper.TbCourseMapper;
import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbCourseExample;
import com.fsw.pojo.TbCourseExample.Criteria;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.CourseService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.IDUtils;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private MyMapper myMapper;
	@Autowired
	private TbCourseMapper courseMapper;
	@Value("${courseImg}")
	private String courseImg;
	
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
				sBuilder.append(" AND 1=1 AND c.status = 1");
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

	public List<TbCourse> selectCourseByCollection(HttpServletRequest request, HttpServletResponse response) {
		
		TbUserWithBLOBs loginUser = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		
		String[] split = loginUser.getCollection().split(",");
		List<TbCourse> list = new ArrayList<TbCourse>();
		for (String string : split) {
			
			if (string.equals("0")) {
				continue;
			}
			TbCourse selectByPrimaryKey = courseMapper.selectByPrimaryKey(Integer.parseInt(string));
			list.add(selectByPrimaryKey);
		}
		
		
		return list;
	}

	public FSWResult createCourse(String name,HttpServletRequest request,HttpServletResponse response) {
		TbUserWithBLOBs loginUser = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		TbCourse course = new TbCourse();
		course.setName(name);
		course.setUserId(loginUser.getId());
		course.setCreateTime(new Date());
		course.setImage("/image/course/0.jpg");
		course.setUpdateTime(new Date());
		course.setStatus(0);
		
		//插入课程
		int insert = courseMapper.insert(course);
		
		
		return FSWResult.build(200, "", insert);
	}

	public List<TbCourse> selectCourseByUserId(HttpServletRequest request, HttpServletResponse response) {
		TbUserWithBLOBs loginUser = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		TbCourseExample example = new TbCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(loginUser.getId());
		List<TbCourse> selectByExampleWithBLOBs = courseMapper.selectByExampleWithBLOBs(example);
		
		
		return selectByExampleWithBLOBs;
	}

	public FSWResult updateCourseImage(TbCourse course,MultipartFile imgFile, HttpServletRequest request,
			HttpServletResponse response) {
		
		FSWResult result = new FSWResult();
		
		InputStream in = null;
		FileOutputStream fos = null;
		//获取原文件名
		String oldName = imgFile.getOriginalFilename();
		String filePath = "";
		
		String oldImageUrl = course.getImage();
		try {
			//生成新文件名
			String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
			//获得文件路径
			filePath = request.getSession().getServletContext().getRealPath(courseImg);
			
			//获取流
			byte[] bytes = imgFile.getBytes();
			in = new ByteArrayInputStream(bytes);
			
			//获取file
			File file = new File(filePath, newName);
			
			fos = new FileOutputStream(file);
			
			byte[] b = new byte[1024];
			int nRead = 0;
			while ((nRead = in.read(b)) != -1) {
			 fos.write(b, 0, nRead);
			}
			fos.flush();
			
			//将头像路径传到数据库
			String imageUrl = courseImg+newName;
			
			course.setImage(imageUrl);
			//修改头像路径
			int whether = courseMapper.updateByPrimaryKeyWithBLOBs(course);
			result.setMsg(whether+"");
		} catch (IOException e) {
			e.printStackTrace();
			result.setMsg(2+"");
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			//删除老头像
			if (!oldName.equals("0.jpg")) {
				
				String realPath = request.getSession().getServletContext().getRealPath(courseImg);
				String[] split = oldImageUrl.split("/");
				int length = split.length;
				String fileName = realPath+split[length-1] ;
				
				File file = new File(fileName);
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("删除课程之前图片错误");
		}
		
	
		return result;
	}

	public FSWResult updateCourse(TbCourse course, String name, String type, String info, String teacher,
			String status) {
		
		course.setName(name);
		course.setType(type);
		course.setDetails(info);
		course.setTeacher(teacher);
		course.setStatus(Integer.parseInt(status));
		
		//修改课程信息
		int updateByPrimaryKeyWithBLOBs = courseMapper.updateByPrimaryKeyWithBLOBs(course);
		
		return FSWResult.ok(updateByPrimaryKeyWithBLOBs);
	}

	
}
