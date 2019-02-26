package com.fsw.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.mapper.TbVideoMapper;
import com.fsw.pojo.TbVideo;
import com.fsw.pojo.TbVideoExample;
import com.fsw.pojo.TbVideoExample.Criteria;
import com.fsw.service.VideoService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.IDUtils;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private TbVideoMapper videoMapper;
	@Value("${videoURL}")
	private String videoURL;
	
	public FSWResult selectVideoByCourse(Integer courseId) {

		try {
			TbVideoExample example = new TbVideoExample();
			Criteria criteria = example.createCriteria();
			criteria.andCourseIdEqualTo(courseId);
			example.setOrderByClause("findex asc");
			List<TbVideo> list = videoMapper.selectByExampleWithBLOBs(example);
			if (list == null || list.size() == 0) {
				return FSWResult.build(404, "没有找到视频", null);
			}
			return FSWResult.build(200, "video", list);
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "服务器异常", null);
		}
	}

	public FSWResult newVideo(String courseId,String name, String index, MultipartFile videoFile, HttpServletRequest request,
			HttpServletResponse response) {
		
		TbVideo video = new TbVideo();
		video.setName(name);
		video.setFindex(Integer.parseInt(index));
		video.setCourseId(Integer.parseInt(courseId));
		video.setCreateTime(new Date());
		video.setUpdateTime(new Date());
		
		
		FSWResult result = new FSWResult();
		
		if (videoFile.getSize() != 0) {
			InputStream in = null;
			FileOutputStream fos = null;
			//获取原文件名
			String oldName = videoFile.getOriginalFilename();
			String filePath = "";
			
			try {
				//生成新文件名
				String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
				//获得文件路径
				filePath = request.getSession().getServletContext().getRealPath(videoURL);
				
				//获取流
				byte[] bytes = videoFile.getBytes();
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
				
				//将视频路径传到数据库
				String imageUrl = videoURL+newName;
				
				video.setUrl(imageUrl);
				//新建视频
				int whether = videoMapper.insert(video);
				result.setMsg(whether+"");
				
				//返回
				return result;
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
		}
		
		video.setUrl(" ");
		//新建视频
		int whether = videoMapper.insert(video);
		result.setMsg(whether+"");
		
		return result;
	}

	public FSWResult updateVideo(String id, String name, String index, MultipartFile videoFile,
			HttpServletRequest request, HttpServletResponse response) {
		
		TbVideo video = videoMapper.selectByPrimaryKey(Integer.parseInt(id));
		video.setName(name);
		video.setFindex(Integer.parseInt(index));
		video.setUpdateTime(new Date());
		
		
		
		FSWResult result = new FSWResult();
		
		if (videoFile.getSize() != 0) {
			InputStream in = null;
			FileOutputStream fos = null;
			//获取原文件名
			String oldName = videoFile.getOriginalFilename();
			String filePath = "";
			
			String oldImageUrl = video.getUrl();
			
			try {
				//生成新文件名
				String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
				//获得文件路径
				filePath = request.getSession().getServletContext().getRealPath(videoURL);
				
				//获取流
				byte[] bytes = videoFile.getBytes();
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
				
				//将视频路径传到数据库
				String imageUrl = videoURL+newName;
				
				video.setUrl(imageUrl);
				//修改视频
				int whether = videoMapper.updateByPrimaryKeyWithBLOBs(video);
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
					
				String realPath = request.getSession().getServletContext().getRealPath(videoURL);
				String[] split = oldImageUrl.split("/");
				int length = split.length;
				String fileName = realPath+split[length-1] ;
				
				File file = new File(fileName);
				file.delete();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("删除视频错误");
			}
			
		}else {
			//修改视频
			int whether = videoMapper.updateByPrimaryKeyWithBLOBs(video);
			result.setMsg(whether+"");
			
		}
		
		
		
		return result;
	}

	public FSWResult deleteVideo(String videoId,HttpServletRequest request , HttpServletResponse response) {
		FSWResult result = new FSWResult();
		
		try {
			
			//获得视频对象
			TbVideo video = videoMapper.selectByPrimaryKey(Integer.parseInt(videoId));
			//删除视频
			try {
				String oldImageUrl = video.getUrl();
				String realPath = request.getSession().getServletContext().getRealPath(videoURL);
				String[] split = oldImageUrl.split("/");
				int length = split.length;
				String fileName = realPath+split[length-1] ;
				
				File file = new File(fileName);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("删除视频出现错误");
			}
			
			
			//删除数据库中数据
			int deleteByPrimaryKey = videoMapper.deleteByPrimaryKey(Integer.parseInt(videoId));
			if (deleteByPrimaryKey == 1) {
				result.setStatus(200);
				result.setMsg("成功");
				return result;
			}else {
				result.setStatus(404);
				result.setMsg("没有找到视频");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMsg("服务器错误");
			return result;
		}
		
	}

}
