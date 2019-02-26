package com.fsw.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbNextComments;
import com.fsw.pojo.TbUser;
import com.fsw.service.CommentsService;
import com.fsw.service.CourseService;
import com.fsw.utils.FSWResult;


@Controller
public class CommentController {

	@Autowired
	private CommentsService commentsService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("course/remove/comment")
	@ResponseBody
	public FSWResult deleteComment(String id) {
		
		FSWResult deleteComment = commentsService.deleteComment(id);
		
		return deleteComment;
	}
	
	
	@RequestMapping("goCommentManage")
	public String goCommentManage(@RequestParam(defaultValue="1")String courseId,Model model,HttpServletRequest request , HttpServletResponse response) {
		
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		TbCourse managerCourse = courseService.selectCourseById(courseId);
		model.addAttribute("course", managerCourse);
		
		return "commentManage";
	}
	
	@RequestMapping(value="select/course/comment")
	@ResponseBody
	public SelectResult selectCourseComment(@RequestParam(value="courseId")String courseId,
			@RequestParam(value="count",defaultValue="5")String count ,@RequestParam(value="pageNow",defaultValue="1")String pageNow) {
		
		if (courseId == null || courseId =="") {
			return null;
		}
		if("0".equals(courseId)) {
			
			SelectResult selectAllComments = commentsService.selectAllComments(10+"",pageNow);
			return selectAllComments;
		}
		
		pageNow = (Integer.parseInt(pageNow)-1)+"";
		
		SelectResult result = commentsService.selectCommentsByCourseId(courseId, count, pageNow);
		
		return result;
	}
	@RequestMapping("course/comment")
	@ResponseBody
	public FSWResult insertCourseComment(String courseId,String content,
			HttpServletRequest request,HttpServletResponse response) {
		FSWResult result = commentsService.insertCourseComment(courseId,content,request,response);
		return result;
	}
	
	@RequestMapping("course/comment/reply")
	@ResponseBody
	public FSWResult insertCourseCommentReply(TbNextComments nextComments,HttpServletRequest request,HttpServletResponse response) {
		
		FSWResult result = commentsService.insertCourseCommentReply(nextComments, request, response);
		
		return result;
		
	}
	
	
	
}
