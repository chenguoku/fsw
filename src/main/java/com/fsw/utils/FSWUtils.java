package com.fsw.utils;

import java.util.ArrayList;
import java.util.List;

public class FSWUtils {

	/**
	 * 将分类转换成 1-2-3-4-5-6
	 * @param string
	 * @return
	 */
	public static String ChangeInteresting(String string) {
		/*
		 * 	语言学习
			办公效率
			兴趣生活
			升学考研
			产品设计
			编程开发
			1.语言学习 2.办公效率 3.兴趣生活 4.升学考研 5.编程开发 6.产品设计
		 */
		if ("语言学习".equals(string)) {
			return 1+"";
		}
		if ("办公效率".equals(string)) {
			return 2+"";
		}
		if ("兴趣生活".equals(string)) {
			return 3+"";
		}
		if ("升学考研".equals(string)) {
			return 4+"";
		}
		if ("编程开发".equals(string)) {
			return 5+"";
		}
		if ("产品设计".equals(string)) {
			return 6+"";
		}
		
		return "";
	}
	
	/**
	 * 获得注册验证码
	 * @return
	 */
	public static String getRegisterCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append((int) (Math.random()*10)+"");
		}
		return sb+"";
	}
	
	/**
	 * 将排序转换成数字
	 * 1-人气
	 * 2-最新
	 * @param sort
	 * @return
	 */
	public static String ChangeSort(String sort) {
		if ("人气".equals(sort)) {
			return 1+"";
		}
		if ("最新".equals(sort)) {
			return 2+"";
		}
		return "";
	}
	/**
	 * 随机一个激励的话语
	 * @return
	 */
	public static String getInspiringWords() {
		
		List<String> list = new ArrayList<String>();
		
		list.add("只要路是对的，就不怕路远");
		list.add("天生我材必有用");
		list.add("天下谁人不识君");
		list.add("自我打败自我的远远多于被别人打败的");
		list.add("掌握坚持的人是成功的，是永不言弃的");
		list.add("人生没有彩排，只有现场直播，所以每一件事都要努力做得最好");
		list.add("接受我们不能改变的一切，改变我们能改变的一切");
		list.add("幸福不是因为你拥有得多，而是由于你计较得少");
		list.add("在前进的路上，主动搬开别人脚下的绊脚石，有时往往也是为自己铺路");
		list.add("眼睛可以近视，目光不能短浅");
		list.add("没有绝望的处境，只有对处境绝望的人");
		list.add("学会技能是小智慧，学会做人是大智慧");
		list.add("做事的能力往往只能给你一种机会，而做人的能力则会给你一百种机会");
		list.add("生活的最大悲剧不是失败，而是一个人已经习惯于失败");
		list.add("人创造奇迹常常是在瞬间，但没有一个创造奇迹的人是依靠瞬间的");
		
		int size = list.size();
		
		double random = Math.random()*size;
		int floor = (int) Math.floor(random);
		
		return list.get(floor);
	}
	
	
	
}
