package com.fsw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsw.mapper.MyMapper;
import com.fsw.mapper.TbCommentsMapper;
import com.fsw.mapper.TbNextCommentsMapper;
import com.fsw.pojo.CommentPlus;
import com.fsw.pojo.SelectResult;
import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbCommentsExample;
import com.fsw.pojo.TbNextComments;
import com.fsw.pojo.TbNextCommentsExample;
import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbNextCommentsExample.Criteria;
import com.fsw.service.CommentsService;
import com.fsw.utils.FSWResult;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private MyMapper myMapper;
	@Autowired
	private TbCommentsMapper commentsMapper;
	@Autowired
	private TbNextCommentsMapper nextCommentsMapper;
	private Lock lock = new ReentrantLock();
	
	public SelectResult selectCommentsByCourseId(String courseId, String count, String pageNow) {
		SelectResult selectResult = new SelectResult();
		//查询所有评论
		TbCommentsExample commentsExample = new TbCommentsExample();
		com.fsw.pojo.TbCommentsExample.Criteria criteria2 = commentsExample.createCriteria();
		criteria2.andCourseIdEqualTo(Integer.parseInt(courseId));
		List<TbComments> selectByExample = commentsMapper.selectByExample(commentsExample);
		int commtentsSize = selectByExample.size();
		double d = (double)commtentsSize/(Integer.parseInt(count));
		//所有评论
		int ceil = (int)Math.ceil(d);
		try {
			
			
			//查询到主评论
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append(" course_id = "+courseId);
			sbBuilder.append(" ORDER BY create_time DESC ");
			sbBuilder.append(" LIMIT "+(Integer.parseInt(pageNow)*Integer.parseInt(count))+","+count+" ");
			String sql = sbBuilder+"";
			List<TbComments> list = myMapper.selectCommentsByCourseId(sql);
			
			if (list == null || list.size() == 0) {
				selectResult.setData(null);
				selectResult.setMsg("没有找打评论");
				selectResult.setPageCount(ceil);
				selectResult.setStatus(404);
				return selectResult;
			}
			//转成CommentPlus
			List<CommentPlus> listPlus = new ArrayList<CommentPlus>();
			for (TbComments tbComments : list) {
				CommentPlus commentPlus = new CommentPlus();
				
				commentPlus.setContent(tbComments.getContent());
				commentPlus.setCourseId(tbComments.getCourseId());
				commentPlus.setCreateTime(tbComments.getCreateTime());
				commentPlus.setId(tbComments.getId());
				commentPlus.setUserId(tbComments.getUserId());
				commentPlus.setUserImage(tbComments.getUserImage());
				commentPlus.setUserName(tbComments.getUserName());
				
				listPlus.add(commentPlus);
			}
			
			//查询副评论
			TbNextCommentsExample example = new TbNextCommentsExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andCourseIdEqualTo(Integer.parseInt(courseId));
			example.setOrderByClause("create_time asc");
			List<TbNextComments> nextList = nextCommentsMapper.selectByExampleWithBLOBs(example);
			for (CommentPlus commentPlus : listPlus) {
				List<TbNextComments> nextCommentsList = new ArrayList<TbNextComments>();
				for (TbNextComments tbNextComments : nextList) {
					if (commentPlus.getId() == tbNextComments.getCommentsId()) {
						nextCommentsList.add(tbNextComments);
					}
				}
				commentPlus.setList(nextCommentsList);
			}
			
			selectResult.setData(listPlus);
			selectResult.setMsg("comment");
			selectResult.setPageCount(ceil);
			selectResult.setStatus(200);
			
			return selectResult;
		} catch (Exception e) {
			e.printStackTrace();
			selectResult.setData(null);
			selectResult.setMsg("服务器错误");
			selectResult.setPageCount(ceil);
			selectResult.setStatus(500);
			return selectResult;
		}
	}

	
	
	public FSWResult insertCourseComment(String courseId, String content,HttpServletRequest request,HttpServletResponse response) {
		lock.lock();
		try {
			TbUser loginUser = (TbUser) request.getSession().getAttribute("loginUser");
			
			TbComments comments = new TbComments();
			comments.setContent(content);
			comments.setCourseId(Integer.parseInt(courseId));
			comments.setCreateTime(new Date());
			comments.setUserId(loginUser.getId());
			comments.setUserImage(loginUser.getImageurl());
			comments.setUserName(loginUser.getName());
			
			int insert = commentsMapper.insertSelective(comments);
			
			//查询主键id
			TbCommentsExample example = new TbCommentsExample();
			example.setOrderByClause("create_time DESC LIMIT 1");
			List<TbComments> list2 = commentsMapper.selectByExampleWithBLOBs(example);
			
			if (insert == 0) {
				return FSWResult.build(404, "没有找到课程", null);
			}
			
			return FSWResult.build(200, "评论成功", list2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "评论失败", null);
		}finally {
			lock.unlock();
		}
		
	}

	public FSWResult insertCourseCommentReply(TbNextComments nextComments, HttpServletRequest request,
			HttpServletResponse response) {
		
		/*
		 *	| courseId | 课程id | 必选 |
			| commentsId | 评论id | 必选 |
			| commentUserName | 被回复人name | 必选 |
			| commentUserId | 被回复人id | 必选 |
			| content | 评论内容 | 必选 | 
			
			
		 */
		lock.lock();
		try {
			TbUser loginUser = (TbUser) request.getSession().getAttribute("loginUser");
			nextComments.setUserId(loginUser.getId());
			nextComments.setUserImage(loginUser.getImageurl());
			nextComments.setUserName(loginUser.getName());
			nextComments.setCreateTime(new Date());
			
			//插入
			int insert = nextCommentsMapper.insert(nextComments);
			if (insert == 0) {
				return FSWResult.build(404, "没有找到课程、用户、评论、回复", null);
			}
			
			//查询最近添加的数据
			TbNextCommentsExample nextCommentsExample = new TbNextCommentsExample();
			nextCommentsExample.setOrderByClause("create_time DESC LIMIT 1");
			List<TbNextComments> selectByExampleWithBLOBs = nextCommentsMapper.selectByExampleWithBLOBs(nextCommentsExample);
			
			
			return FSWResult.build(200, "评论成功", selectByExampleWithBLOBs);
			
		} catch (Exception e) {
			e.printStackTrace();
			return FSWResult.build(500, "评论失败", null);
		}finally {
			lock.unlock();
		}
		
	}



	public List<TbComments> selectHotComments(String courseId, String count) {
		
		/*
		 * course_id = 1
			GROUP BY comments_id HAVING COUNT(*) ORDER BY COUNT(*) DESC
			LIMIT 0,5
		 */
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("  course_id = "+courseId);
		stringBuilder.append(" GROUP BY comments_id HAVING COUNT(*) ORDER BY COUNT(*) DESC ");
		stringBuilder.append(" LIMIT 0,"+count+" ");
		
		String sql = stringBuilder+"";
		//执行查询  得到热门课程的id
		List<Integer> selectHotComments = myMapper.selectHotComments(sql);
		
		
		
		//查询评论
		List<TbComments> list = new ArrayList<TbComments>();
		for (Integer integer : selectHotComments) {
			TbComments selectByPrimaryKey = commentsMapper.selectByPrimaryKey(integer);
			
			list.add(selectByPrimaryKey);
		}
		
		
		return list;
	}



	public FSWResult deleteComment(String id) {
		FSWResult result = new FSWResult();
		try {
			//删除主评论
			int deleteByPrimaryKey = commentsMapper.deleteByPrimaryKey(Integer.parseInt(id));
			//删除副评论
			TbNextCommentsExample example = new TbNextCommentsExample();
			Criteria criteria = example.createCriteria();
			criteria.andCommentsIdEqualTo(Integer.parseInt(id));
			int deleteByExample = nextCommentsMapper.deleteByExample(example);
			if (deleteByPrimaryKey == 0) {
				result.setStatus(404);
				result.setMsg("没有找到评论");
				return result;
			}
			result.setStatus(200);
			result.setMsg("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMsg("服务器错误");
			return result;
		}
		
	}



	public SelectResult selectAllComments(String count, String pageNow) {
		
/*		//查询所有评论
				TbCommentsExample commentsExample = new TbCommentsExample();
				List<TbComments> selectByExample = commentsMapper.selectByExample(commentsExample);
				int commtentsSize = selectByExample.size();
				double d = (double)commtentsSize/(double)(Integer.parseInt(count));
				//所有评论
				int ceil = (int)Math.ceil(d);
				try {
					
					
					//查询到主评论
					StringBuilder sbBuilder = new StringBuilder();
					sbBuilder.append(" course_id = "+courseId);
					sbBuilder.append(" ORDER BY create_time DESC ");
					sbBuilder.append(" LIMIT "+(Integer.parseInt(pageNow)*Integer.parseInt(count))+","+count+" ");
					String sql = sbBuilder+"";
					List<TbComments> list = myMapper.selectCommentsByCourseId(sql);*/
		
		SelectResult selectResult = new SelectResult();
		//查询所有评论
				TbCommentsExample commentsExample = new TbCommentsExample();
				List<TbComments> selectByExample = commentsMapper.selectByExample(commentsExample);
				int commtentsSize = selectByExample.size();
				double d = (double)commtentsSize/(double)(Integer.parseInt(count));
				//所有评论
				int ceil = (int)Math.ceil(d);
				try {
					
					
					//查询到主评论
					StringBuilder sbBuilder = new StringBuilder();
					sbBuilder.append(" 1=1 ");
					sbBuilder.append(" ORDER BY create_time DESC ");
					sbBuilder.append(" LIMIT "+((Integer.parseInt(pageNow)-1)*Integer.parseInt(count))+","+count+" ");
					String sql = sbBuilder+"";
					List<TbComments> list = myMapper.selectCommentsByCourseId(sql);
					
					
					if (list == null || list.size() == 0) {
						selectResult.setData(null);
						selectResult.setMsg("没有找打评论");
						selectResult.setPageCount(ceil);
						selectResult.setStatus(404);
						return selectResult;
					}
					//转成CommentPlus
					List<CommentPlus> listPlus = new ArrayList<CommentPlus>();
					for (TbComments tbComments : list) {
						CommentPlus commentPlus = new CommentPlus();
						
						commentPlus.setContent(tbComments.getContent());
						commentPlus.setCourseId(tbComments.getCourseId());
						commentPlus.setCreateTime(tbComments.getCreateTime());
						commentPlus.setId(tbComments.getId());
						commentPlus.setUserId(tbComments.getUserId());
						commentPlus.setUserImage(tbComments.getUserImage());
						commentPlus.setUserName(tbComments.getUserName());
						
						listPlus.add(commentPlus);
					}
					
					//查询副评论
					TbNextCommentsExample example2 = new TbNextCommentsExample();
					List<TbNextComments> nextList = nextCommentsMapper.selectByExampleWithBLOBs(example2);
					for (CommentPlus commentPlus : listPlus) {
						List<TbNextComments> nextCommentsList = new ArrayList<TbNextComments>();
						for (TbNextComments tbNextComments : nextList) {
							if (commentPlus.getId() == tbNextComments.getCommentsId()) {
								nextCommentsList.add(tbNextComments);
							}
						}
						commentPlus.setList(nextCommentsList);
					}
					
					selectResult.setData(listPlus);
					selectResult.setMsg("comment");
					selectResult.setPageCount(ceil);
					selectResult.setStatus(200);
					
					return selectResult;
				} catch (Exception e) {
					e.printStackTrace();
					selectResult.setData(null);
					selectResult.setMsg("服务器错误");
					selectResult.setPageCount(ceil);
					selectResult.setStatus(500);
					return selectResult;
				}
		
		
	}

}
