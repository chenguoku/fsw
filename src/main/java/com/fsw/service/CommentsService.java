package com.fsw.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbNextComments;
import com.fsw.utils.FSWResult;

public interface CommentsService {

	/**
	 * 查询评论和副评论
	 * @param courseId
	 * @param count
	 * @param pageNow
	 * @return
	 */
	SelectResult selectCommentsByCourseId(String courseId,String count,String pageNow);
	
	/**
	 * 插入主评论
	 * @param courseId
	 * @param content
	 * @return
	 */
	FSWResult insertCourseComment(String courseId,String content,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 添加评论回复
	 * @param nextComments
	 * @param request
	 * @param response
	 * @return
	 */
	FSWResult insertCourseCommentReply(TbNextComments nextComments , HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 查询热门评论 
	 * @param courseId
	 * @param count
	 * @return
	 */
	List<TbComments> selectHotComments(String courseId,String count);
	
	/**
	 * 删除评论及副评论
	 * @param id
	 * @return
	 */
	FSWResult deleteComment(String id);
}
