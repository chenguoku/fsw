package com.fsw.utils;

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
	
}
