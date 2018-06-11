package com.fsw.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsw.pojo.CommentPlus;
import com.fsw.pojo.TbNextComments;
import com.fsw.utils.FSWResult;


@Controller
public class TestConntroller {

	@RequestMapping(value="test")
	@ResponseBody
	public FSWResult test() {
		
		CommentPlus cp1  = new CommentPlus();
		CommentPlus cp2  = new CommentPlus();
		List<CommentPlus> cpList = new ArrayList<CommentPlus>();
		
		TbNextComments tnc1 = new TbNextComments();
		TbNextComments tnc2 = new TbNextComments();
		List<TbNextComments> tncList = new ArrayList<TbNextComments>();
		
		tnc1.setCommentsId(1);
		tnc1.setCommentUserId(1);
		tnc1.setCommentUserName("tnc1");
		tnc1.setContent("附表");
		tnc1.setCourseId(1);
		tnc1.setCreateTime(new Date());
		tnc1.setId(1);
		tnc1.setUserId(1);
		tnc1.setUserImage("aaaa");
		tnc1.setUserName("hh");
		
		tnc2.setCommentsId(1);
		tnc2.setCommentUserId(1);
		tnc2.setCommentUserName("tnc1");
		tnc2.setContent("附表");
		tnc2.setCourseId(1);
		tnc2.setCreateTime(new Date());
		tnc2.setId(1);
		tnc2.setUserId(1);
		tnc2.setUserImage("aaaa");
		tnc2.setUserName("hh");
		
		tncList.add(tnc1);
		tncList.add(tnc2);
		
		cp1.setContent("qqqq");
		cp1.setCourseId(1);
		cp1.setCreateTime(new Date());
		cp1.setId(1);
		cp1.setList(tncList);
		cp1.setUserId(1);
		cp1.setUserImage("wwww");
		cp1.setUserName("gg");
		
		cp2.setContent("qqqq");
		cp2.setCourseId(1);
		cp2.setCreateTime(new Date());
		cp2.setId(1);
		cp2.setList(tncList);
		cp2.setUserId(1);
		cp2.setUserImage("wwww");
		cp2.setUserName("gg");
		
		cpList.add(cp1);
		cpList.add(cp2);
		
		
		return FSWResult.build(200, "正常", cpList);
		
	}
	
}
