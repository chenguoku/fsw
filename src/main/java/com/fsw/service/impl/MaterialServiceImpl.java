package com.fsw.service.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.mapper.TbMaterialMapper;
import com.fsw.pojo.TbMaterial;
import com.fsw.pojo.TbMaterialExample;
import com.fsw.pojo.TbMaterialExample.Criteria;
import com.fsw.service.MaterialService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;
import com.fsw.utils.IDUtils;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private TbMaterialMapper materialMapper;
	@Value("${materialURL}")
	private String materialURL;
	
	
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

	public FSWResult newMaterial(String courseId, String name, String index, MultipartFile materialFile,
			HttpServletRequest request, HttpServletResponse response) {
		
		TbMaterial material = new TbMaterial();
		material.setCourseId(Integer.parseInt(courseId));
		material.setFindex(Integer.parseInt(index));
		material.setName(name);
		material.setCreateTime(new Date());
		material.setUpdateTime(new Date());
		
		FSWResult result = new FSWResult();
		
		if (materialFile.getSize() != 0) {
			InputStream in = null;
			FileOutputStream fos = null;
			//获取原文件名
			String oldName = materialFile.getOriginalFilename();
			String filePath = "";
			
			try {
				//生成新文件名
				String newName = IDUtils.genImageName();
				//获得文件路径
				filePath = request.getSession().getServletContext().getRealPath(materialURL);
				
				//获取流
				byte[] bytes = materialFile.getBytes();
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
				String imageUrl = newName;
				
				material.setUrl(imageUrl);
				//新建素材
				int whether = materialMapper.insert(material);
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
		
		material.setUrl(" ");
		//新建视频
		int whether = materialMapper.insert(material);
		result.setMsg(whether+"");
		
		return result;
	}

	public FSWResult updateMaterial(String id, String name, String index, MultipartFile materialFile, HttpServletRequest request,
			HttpServletResponse response) {
		
		TbMaterial material = materialMapper.selectByPrimaryKey(Integer.parseInt(id));
		material.setName(name);
		material.setFindex(Integer.parseInt(index));
		material.setUpdateTime(new Date());
		
		
		FSWResult result = new FSWResult();
		
		if (materialFile.getSize() != 0) {
			InputStream in = null;
			FileOutputStream fos = null;
			//获取原文件名
			String oldName = materialFile.getOriginalFilename();
			String filePath = "";
			
			String oldImageUrl = material.getUrl();
			
			try {
				//生成新文件名
				String newName = IDUtils.genImageName();
				//获得文件路径
				filePath = request.getSession().getServletContext().getRealPath(materialURL);
				
				//获取流
				byte[] bytes = materialFile.getBytes();
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
				String imageUrl = newName;
				material.setUrl(imageUrl);
				//修改素材
				int whether = materialMapper.updateByPrimaryKey(material);
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
				//删除老素材
					
				String realPath = request.getSession().getServletContext().getRealPath(materialURL);
				String[] split = oldImageUrl.split("/");
				int length = split.length;
				String fileName = realPath+split[length-1] ;
				File file = new File(fileName);
				file.delete();
				
			} catch (Exception e) {
				System.out.println("删除素材错误");
			}
			
		}else {
			//修改素材
			int whether = materialMapper.updateByPrimaryKey(material);
			result.setMsg(whether+"");
			
		}
		
		return result;
	}

	public FSWResult deleteMaterial(String id,HttpServletRequest request , HttpServletResponse response) {
		
		FSWResult result = new FSWResult();
		try {
			
			//获得素材对象
			TbMaterial material = materialMapper.selectByPrimaryKey(Integer.parseInt(id));
			try {
				//删除老素材
				String oldImageUrl = material.getUrl();
				String realPath = request.getSession().getServletContext().getRealPath(materialURL);
				String[] split = oldImageUrl.split("/");
				int length = split.length;
				String fileName = realPath+split[length-1] ;
				File file = new File(fileName);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("删除素材失败");
			}
			
			
			//删除数据库中数据
			int deleteByPrimaryKey = materialMapper.deleteByPrimaryKey(Integer.parseInt(id));
			if (deleteByPrimaryKey == 1) {
				result.setStatus(200);
				result.setMsg("成功");
			}else {
				result.setStatus(404);
				result.setMsg("没有找到素材");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMsg("服务器错误");	
		}
		
		
		return result;
	}

	public void materialDown(String id, HttpServletRequest request, HttpServletResponse response) {
		TbMaterial material = materialMapper.selectByPrimaryKey(Integer.parseInt(id));
		             System.out.println(material.getName());
		try {
			//模拟文件，myfile.txt为需要下载的文件  
	        String fileName1 = request.getSession().getServletContext().getRealPath(materialURL);
	        //获取输入流    
			//String fileName = request.getSession().getServletContext().getRealPath("/"); 
	        String fileName = fileName1+material.getUrl();
	        System.out.println(fileName);
	        
	        //获取file
			File file = new File(fileName);
	        FileInputStream fis = new FileInputStream(file);
	        
            String filename = material.getName();
            // 取得文件的后缀名。
            String[] split = (material.getUrl()).split("\\.");
            String ext = split[split.length-1];
	        filename = filename+"."+ext;  
	        //转码，免得文件名中文乱码  
			filename = URLEncoder.encode(filename,"UTF-8");
	        //设置文件下载头  
	        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
	        response.setContentType("multipart/form-data");   
	        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
	        int len = 0;  
	        while((len = fis.read()) != -1){  
	            out.write(len);  
	            out.flush();  
	        }  
	        out.close();  
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
	
}
