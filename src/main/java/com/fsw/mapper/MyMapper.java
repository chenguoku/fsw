package com.fsw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbTestPage;

public interface MyMapper {
	List<TbCourse> getCourseListType3();
	List<TbCourse> getCourseListType5();
	List<TbCourse> getCourseListType();
	/**
	 * 根据兴趣查询课程
	 * @param interestingSql
	 * @return
	 */
	List<TbCourse> getCourseByInteresting(@Param(value = "interestingSql") String interestingSql);
	
	/**
	 * 查询课程
	 * @param sql
	 * @return
	 */
	List<TbCourse> selectCourse(@Param(value = "sql") String sql);
	/**
	 * 查询课程总数
	 * @param selectCount
	 * @return
	 */
	Integer selectCourseCount(@Param(value="selectCount")String selectCount);
	
	/**
	 * 查询评论根据课程id
	 * @param sql
	 * @return
	 */
	List<TbComments> selectCommentsByCourseId(@Param(value="sql")String sql);

	/**
	 * 查询热门评论
	 * @param sql
	 * @return
	 */
	List<Integer> selectHotComments(@Param(value="sql")String sql);
	
	/**
	 * 查询试卷最大id
	 * @param sql
	 * @return
	 */
	Integer selectMaxTestPage(@Param(value="sql")String sql);
}
