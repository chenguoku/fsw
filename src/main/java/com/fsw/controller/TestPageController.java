package com.fsw.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.TbCourse;
import com.fsw.pojo.TbTestPage;
import com.fsw.pojo.TbTestQuestionWithBLOBs;
import com.fsw.pojo.TbUser;
import com.fsw.service.CourseService;
import com.fsw.service.TestPageService;
import com.fsw.utils.FSWResult;

@Controller
public class TestPageController {

	@Autowired
	private TestPageService testPageService;
	@Autowired
	private CourseService courseService;
	
	
	/**
	 * 新增试卷
	 * @return
	 */
	@RequestMapping("insertTestPage")
	public String insertTestPage(String testPageId,String questionCount,String courseId,
			String testPageName,String testPageIndex,HttpServletRequest request,HttpServletResponse reHttpServletResponse) {
		int questionNumber = Integer.parseInt(questionCount);
//		questionName
//		questionType_0
//		questionId  0 是新建
//		questionIndex
//		ABCDE
//		answer_0
		
		String[] questionName = request.getParameterValues("questionName");
		String[] questionId = request.getParameterValues("questionId");
		String[] questionIndex = request.getParameterValues("questionIndex");
		String[] A = request.getParameterValues("A");
		String[] B = request.getParameterValues("B");
		String[] C = request.getParameterValues("C");
		String[] D = request.getParameterValues("D");
		String[] E = request.getParameterValues("E");
		
		
		
		if (testPageId.equals("0")) {
			//新建
			TbTestPage testPage = new TbTestPage();
			testPage.setTitle(testPageName);
			testPage.setCourseId(Integer.parseInt(courseId));
			testPage.setFindex(Integer.parseInt(testPageIndex));
			testPage.setCreateTime(new Date());
			testPage.setUpdateTime(new Date());
			//先插入试卷 返回试卷id
			Integer insertTestPage = testPageService.insertTestPage(testPage);
			for (int i = 0; i < questionNumber; i++) {
				String[] questionType_0 = request.getParameterValues("questionType_"+i);
				String[] answer_0 = request.getParameterValues("answer_"+i);
				
				
				TbTestQuestionWithBLOBs tbTestQuestion = new TbTestQuestionWithBLOBs();
				tbTestQuestion.setCreateTime(new Date());
				tbTestQuestion.setUpdateTime(new Date());
				tbTestQuestion.setTestPageId(insertTestPage);
				tbTestQuestion.setType(Integer.parseInt(questionType_0[0]));
				tbTestQuestion.setFindex(Integer.parseInt(questionIndex[i]));
				tbTestQuestion.setQuestion(questionName[i]);
				String options = A[i].trim().replace(" ", "")+" "+
								 B[i].trim().replace(" ", "")+" "+
								 C[i].trim().replace(" ", "")+" "+
								 D[i].trim().replace(" ", "")+" "+
								 E[i].trim().replace(" ", "");
				tbTestQuestion.setOptions(options);
				tbTestQuestion.setAnswer(answer_0[0]);
				testPageService.insertTestQuestion(tbTestQuestion);
			}
			return "redirect:goTestPageManage.html?courseId="+courseId;
			
		}else {
			//修改
			//先删除之前的试卷和题
			testPageService.deleteTestPage(testPageId);
			
			TbTestPage testPage = new TbTestPage();
			testPage.setTitle(testPageName);
			testPage.setCourseId(Integer.parseInt(courseId));
			testPage.setFindex(Integer.parseInt(testPageIndex));
			testPage.setCreateTime(new Date());
			testPage.setUpdateTime(new Date());
			
			
			
			//先插入试卷 返回试卷id
			Integer insertTestPage = testPageService.insertTestPage(testPage);
			for (int i = 0; i < questionNumber; i++) {
				String[] questionType_0 = request.getParameterValues("questionType_"+i);
				String[] answer_0 = request.getParameterValues("answer_"+i);
				
				
				TbTestQuestionWithBLOBs tbTestQuestion = new TbTestQuestionWithBLOBs();
				tbTestQuestion.setCreateTime(new Date());
				tbTestQuestion.setUpdateTime(new Date());
				tbTestQuestion.setTestPageId(insertTestPage);
				tbTestQuestion.setType(Integer.parseInt(questionType_0[0]));
				tbTestQuestion.setFindex(Integer.parseInt(questionIndex[i]));
				tbTestQuestion.setQuestion(questionName[i]);
				String options = A[i].trim().replace(" ", "")+" "+
								 B[i].trim().replace(" ", "")+" "+
								 C[i].trim().replace(" ", "")+" "+
								 D[i].trim().replace(" ", "")+" "+
								 E[i].trim().replace(" ", "");
				tbTestQuestion.setOptions(options);
				tbTestQuestion.setAnswer(answer_0[0]);
				testPageService.insertTestQuestion(tbTestQuestion);
			}
			return "redirect:goTestPageManage.html?courseId="+courseId;
			
		}
	}
	
	@RequestMapping("course/remove/testPage")
	@ResponseBody
	public FSWResult deleteTestPage(String id) {
		FSWResult deleteTestPage = testPageService.deleteTestPage(id);
		return deleteTestPage;
	}
	
	@RequestMapping("goTestPageManage")
	public String goTestPageManage(String courseId,Model model) {
		
		TbCourse course = courseService.selectCourseById(courseId);
		model.addAttribute("course", course);
		return "testPageManage";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("examination")
	public String goExamination(HttpServletRequest request,HttpServletResponse response,Model model) {
		//查看是否登录了
		TbUser user = (TbUser) request.getSession().getAttribute("loginUser");
		if (user == null) {
			model.addAttribute("err", "请登录，在访问！");
			return "login";
		}
		
		model.addAttribute("testPageId", "1");
		return "examination";
	}
	
	/**
	 * 根据试卷id获得试卷的题
	 * @param testPageId
	 * @return
	 */
	@RequestMapping("testPage/testQuestion")
	@ResponseBody
	public FSWResult getTestPageTestQuestion(String testPageId){
		
		
		FSWResult result = testPageService.getTestPageTestQuestion(testPageId);
		
		
		return result;
		
	}
	
	/**
	 * 根据课程id获取有多少试卷及名称
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="select/course/testpage")
	@ResponseBody
	public FSWResult selectCourseTestpage(String courseId) {
		
		FSWResult result = testPageService.selectTestPageByCourseId(Integer.parseInt(courseId));
		
		return result;
		
	}
	
}
