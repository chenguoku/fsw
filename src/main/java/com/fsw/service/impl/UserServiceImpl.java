package com.fsw.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.mapper.TbUserMapper;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserExample;
import com.fsw.pojo.TbUserExample.Criteria;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.FSWUtils;
import com.fsw.utils.IDUtils;
import com.fsw.utils.SendEmail;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper userMapper;
	@Value("${headerImg}")
	private String headerImg;
	

	public FSWResult checkLogin(HttpServletRequest request, HttpServletResponse response) {
		
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		//创作查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(user.getEmail());
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return FSWResult.ok(false);
		}
		
		return FSWResult.ok(true);
		
	}


	public FSWResult userLoginByEmail(HttpServletRequest request, HttpServletResponse response, String email,
			String password) {
		TbUserExample example = new TbUserExample();
		Criteria citeria = example.createCriteria();
		citeria.andEmailEqualTo(email);
		
		List<TbUserWithBLOBs> list = userMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0 ) {
			return FSWResult.build(404, "邮箱或密码错误！");
		}
		TbUserWithBLOBs user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPasswd()) ) {
			return FSWResult.build(404, "邮箱或密码错误！");
		}
		
		request.getSession().setAttribute("loginUser", user);
		
		return FSWResult.ok();
	}


	public int updateUser(TbUserWithBLOBs user) {
		int num = userMapper.updateByPrimaryKeyWithBLOBs(user);
		return num;
	}


	public FSWResult registerCode(String email,HttpServletRequest request,HttpServletResponse response) {
		String code = FSWUtils.getRegisterCode();
		try {
			new SendEmail(email, "发生网", "您好", "您的验证码为："+code);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("registerCode", code);
		} catch (Exception e) {
			return FSWResult.build(500, "发送错误");
		}
		return FSWResult.build(200, "");
	}


	public TbUser findUserByEmail(String email) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		List<TbUser> result = userMapper.selectByExample(example);
		if (result == null || result.size() == 0) {
			TbUser user = new TbUser();
			user.setEmail("404");
			return user;
		}
		return result.get(0);
	}


	public FSWResult register(TbUserWithBLOBs user,HttpServletRequest request,HttpServletResponse response) {
		
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPasswd(DigestUtils.md5DigestAsHex(user.getPasswd().getBytes()));
		user.setType(1+"");
		user.setImageurl("/image/user/0.jpg");
		user.setCollection("0");
		
		int result = userMapper.insert(user);
		request.getSession().setAttribute("loginUser", user);
		return FSWResult.ok(result);
	}


	public FSWResult courseCollection(String courseId, Boolean bool,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			String[] split = user.getCollection().split(",");
			
			if (bool) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("0");
				//收藏
				for (String string : split) {
					//判断是否有该课程
					if (string.equals(courseId)) {
						return FSWResult.build(404, "已经收藏过该课程");
					}
					if (string.equalsIgnoreCase("0")) {
						continue;
					}
					stringBuilder.append(","+string);
				}
				stringBuilder.append(","+courseId);
				user.setCollection(stringBuilder+"");
				userMapper.updateByPrimaryKeyWithBLOBs(user);
			}else {
				//取消收藏
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("0");
				for (String string : split) {
					//判断是否有该课程
					if (string.equals(courseId)) {
						continue;
					}
					if (string.equalsIgnoreCase("0")) {
						continue;
					}
					stringBuilder.append(","+string);
				}
				user.setCollection(stringBuilder+"");
				userMapper.updateByPrimaryKeyWithBLOBs(user);
			}
			
			return FSWResult.build(200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "收藏失败");
		}
		
		
		
	}


	public Boolean whetherCollection(String courseId, HttpServletRequest request, HttpServletResponse response) {
		
		TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		String[] split = user.getCollection().split(",");
		boolean flag = false ;
		for (String string : split) {
			if (courseId.equals(string)) {
				flag = true;
			}
		}
		
		return flag;
	}


	public String updateName(String name,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			user.setName(name);
			//修改姓名
			int whether = userMapper.updateByPrimaryKeyWithBLOBs(user);
			
			return whether+"";
			
		} catch (Exception e) {
			e.printStackTrace();
			return 2+"";
		}
		
	}
	public String updateName(String id,String name) {
		//修改姓名
		TbUserWithBLOBs record = new TbUserWithBLOBs();
		record.setId(Integer.parseInt(id));
		record.setName(name);
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective+"";
		
	}


	public String updateSex(String sex,HttpServletRequest request, HttpServletResponse response) {
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			user.setSex(sex);
			//修改性别
			int whether = userMapper.updateByPrimaryKeyWithBLOBs(user);
			
			return whether+"";
			
		} catch (Exception e) {
			e.printStackTrace();
			return 2+"";
		}
	}
	public String updateSex(String id,String sex) {
		TbUserWithBLOBs record = new TbUserWithBLOBs();
		record.setId(Integer.parseInt(id));
		record.setSex(sex);
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective+"";
	}


	public String updatePasswd(String oldPasswd,String newPasswd,HttpServletRequest request, HttpServletResponse response) {
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			
			if (!DigestUtils.md5DigestAsHex(oldPasswd.getBytes()).equals(user.getPasswd()))  {
				return 2+"";
			}
			
			user.setPasswd(DigestUtils.md5DigestAsHex(newPasswd.getBytes()));
			//修改密码
			int whether = userMapper.updateByPrimaryKeyWithBLOBs(user);
			
			return whether+"";
			
		} catch (Exception e) {
			e.printStackTrace();
			return 2+"";
		}
	}


	public String updateSchool(String school,HttpServletRequest request, HttpServletResponse response) {
		try {
			TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
			user.setSchool(school);
			//修改学校
			int whether = userMapper.updateByPrimaryKeyWithBLOBs(user);
			
			return whether+"";
			
		} catch (Exception e) {
			e.printStackTrace();
			return 2+"";
		}
	}
	public String updateSchool(String id,String school) {
		TbUserWithBLOBs record = new TbUserWithBLOBs();
		record.setId(Integer.parseInt(id));
		record.setSchool(school);
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective+"";
	}


	public FSWResult updateImg(MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
		FSWResult result = new FSWResult();
		
		InputStream in = null;
		FileOutputStream fos = null;
		//获取原文件名
		String oldName = imgFile.getOriginalFilename();
		String filePath = "";
		TbUserWithBLOBs user = (TbUserWithBLOBs) request.getSession().getAttribute("loginUser");
		String oldImageUrl = user.getImageurl();
		try {
			//生成新文件名
			String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
			
			//获得文件路径
			filePath = request.getSession().getServletContext().getRealPath(headerImg);
			
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
			String imageUrl = headerImg+newName;
			
			user.setImageurl(imageUrl);
			//修改头像路径
			int whether = userMapper.updateByPrimaryKeyWithBLOBs(user);
			
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
		
		//删除老头像
		if (!oldName.equals("0.jpg")) {
			
			String realPath = request.getSession().getServletContext().getRealPath(headerImg);
			String[] split = oldImageUrl.split("/");
			int length = split.length;
			String fileName = realPath+split[length-1] ;
			
			File file = new File(fileName);
			file.delete();
		}
	
		return result;
	}


	public FSWResult selectUser(String name, String type) {
		
//		userMapper
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (! "".equals(name)) {
			criteria.andNameEqualTo(name);
		}
		if (!"".equals(type)) {
			criteria.andTypeEqualTo(type);
		}
		List<TbUserWithBLOBs> selectByExampleWithBLOBs = userMapper.selectByExampleWithBLOBs(example);
		if (selectByExampleWithBLOBs == null || selectByExampleWithBLOBs.size() == 0) {
			return FSWResult.build(400, "没有找到", selectByExampleWithBLOBs);
		}
		return FSWResult.build(200, "消息", selectByExampleWithBLOBs);
	}


	public FSWResult removeUser(String id) {
		
		int deleteByPrimaryKey = userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		
		if ("".equals(deleteByPrimaryKey+"") || deleteByPrimaryKey == 0) {
			return FSWResult.build(400, "没有找到用户");
		}
		
		return FSWResult.build(200, "成功", null);
	}



}
