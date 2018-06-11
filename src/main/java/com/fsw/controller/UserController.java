package com.fsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserWithBLOBs;
import com.fsw.service.UserService;
import com.fsw.utils.FSWResult;
import com.fsw.utils.SendEmail;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
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
