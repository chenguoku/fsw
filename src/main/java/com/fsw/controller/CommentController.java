package com.fsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbNextComments;
import com.fsw.service.CommentsService;
import com.fsw.utils.FSWResult;


@Controller
public class CommentController {

	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value="select/course/comment")
	@ResponseBody
	public SelectResult selectCourseComment(String courseId,
			@RequestParam(defaultValue="5")String count ,@RequestParam(defaultValue="1")String pageNow) {
		
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
