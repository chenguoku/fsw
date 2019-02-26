package com.fsw.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("remove/user")
	@ResponseBody
	public FSWResult removeUser(String id) {
		
		FSWResult removeUser = userService.removeUser(id);
		
		return removeUser;
	}
	
	@RequestMapping("managerStudentInfo")
	public String managerStudentInfo(String userId,String username,String sex,String school) {
		
		//修改数据
		if (username != null) {
			userService.updateName(userId,username);
		}
		if (sex != null) {
			userService.updateSex(userId,sex);
		}
		if (school != null) {
			userService.updateSchool(userId,school);
		}
		
		
		return "redirect:goBackstageManagement.html";
	}
	@RequestMapping("managerTeacherInfo")
	public String managerTeacherInfo(String userId,String username,String sex,String school) {
		
		//修改数据
		if (username != null) {
			userService.updateName(userId,username);
		}
		if (sex != null) {
			userService.updateSex(userId,sex);
		}
		if (school != null) {
			userService.updateSchool(userId,school);
		}
		
		
		return "redirect:goTeacherManagement.html";
	}
	
	@RequestMapping("select/user")
	@ResponseBody
	public FSWResult selectUser(@RequestParam(defaultValue="")String name ,@RequestParam(defaultValue="")String type) {
		
		FSWResult selectUser = userService.selectUser(name,type);
		
		return selectUser;
	}
	
	/**
	 * 取申请讲课页面
	 * @return
	 */
	@RequestMapping("goApplyToJoin")
	public String goApplyToJoin() {
		return "applyToJoin";
	}
	
	/**
	 * 修改用户头像
	 * @return
	 */
	@RequestMapping("updateImg")
	public String updateImg(Model model,MultipartFile imgFile,HttpServletRequest request,HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问个人信息！");
			return "login";
		}
		
		if (imgFile.isEmpty()) {
			return "userInfo";
		}
		
		userService.updateImg(imgFile, request, response);
		
		return "userInfo";
	}
	
	/**
	 * 修改个人信息
	 * 包括：姓名，密码，性别，学校
	 * @param name
	 * @param newPasswd
	 * @param sex  男  女
	 * @param school
	 * @return
	 */
	@RequestMapping("updateUserInfo")
	public String updateUserInfo(Model model,String name,String oldPasswd,String newPasswd,String sex,String school,HttpServletRequest request, HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问个人信息！");
			return "login";
		}
		
		//修改数据
		if (name != null) {
			String updateName = userService.updateName(name, request, response);
			if (updateName.equals("1")) {
				model.addAttribute("accountInfo", "用户名修改成功");
			}else {
				model.addAttribute("accountInfo", "用户名修改失败");
			}
		}
		if (newPasswd != null) {
			String updatePasswd = userService.updatePasswd(oldPasswd,newPasswd, request, response);
			if (updatePasswd.equals("1")) {
				model.addAttribute("accountInfo", "密码修改成功");
			}else {
				model.addAttribute("accountInfo", "密码修改失败");
			}
		}
		if (sex != null) {
			String updateSex = userService.updateSex(sex, request, response);
			if (updateSex.equals("1")) {
				model.addAttribute("personalInfo", "修改成功");
			}else {
				model.addAttribute("personalInfo", "修改失败");
			}
		}
		if (school != null) {
			String updateSchool = userService.updateSchool(school, request, response);
			if (updateSchool.equals("1")) {
				model.addAttribute("personalInfo", "修改成功");
			}else {
				model.addAttribute("personalInfo", "修改失败");
			}
		}
		
		
		return "userInfo";
	}
	
	
	/**
	 * 去个人信息页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="goUserInfo")
	public String goUserInfo(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问个人信息！");
			return "login";
		}
		
		
				
		return "userInfo";
		
	}
	
	
	@RequestMapping(value="goLoginPage")
	public String userLogin() {
		return "login";
	}
	@RequestMapping(value="login")
	public String login(Model model,String name,String password,HttpServletRequest request,HttpServletResponse response) {
		
		FSWResult result = userService.userLoginByEmail(request, response, name, password);
		if (result.getStatus() == 404) {
			model.addAttribute("err", result.getMsg());
			return "login";
		}
		
		if("3".equals(((TbUserWithBLOBs)request.getSession().getAttribute("loginUser")).getType())) {
			return "backstageManagement";
		}
		
		
		return "redirect:index.html";
	}
	@RequestMapping(value="goRegisterPage")
	public String goRegisterPage() {
		return "register";
	}
	@RequestMapping(value="register")
	public String register(Model model,String code,TbUserWithBLOBs user,HttpServletRequest request,HttpServletResponse response) {
		
		
		request.getSession().setAttribute("name", user.getName());
		request.getSession().setAttribute("email", user.getEmail());
		request.getSession().setAttribute("passwd", user.getPasswd());
		
		if (user.getEmail().equals(request.getSession().getAttribute("email"))) {
			if (code.equals(request.getSession().getAttribute("registerCode"))) {
				//注册用户
				FSWResult result = userService.register(user,request,response);
				if ((Integer)result.getData() != 0) {
					return "redirect:index.html";
				}else {
					request.getSession().setAttribute("codeErr", "注册发生错误，请重新注册！！");
					return "redirect:goRegisterPage.html";
				}
				
			}else {
				request.getSession().setAttribute("codeErr", "验证码错误！");
				return "redirect:goRegisterPage.html";
			}
		}else {
			request.getSession().setAttribute("codeErr", "验证码错误！");
			return "redirect:goRegisterPage.html";
		}
	}
	@RequestMapping(value="register/code")
	@ResponseBody
	public FSWResult registerCode(String email,HttpServletRequest request,HttpServletResponse response) {
		FSWResult result = new FSWResult();
		TbUser user = userService.findUserByEmail(email);
		if (user.getEmail().equals("404")) {
			result = userService.registerCode(email, request, response);			
		}else {
			result = FSWResult.build(500, "邮箱已注册，请直接去登录！");
		}
		
		return result;
	}
	
	
}
