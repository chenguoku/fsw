<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--头部导航开始-->
    <header class="header">
        <div class="hd-content">
            <a href="${pageContext.request.contextPath }" class="hd-logo">
                <img src="${pageContext.request.contextPath }/image/index/logo.png" alt="">
            </a>
            <form action="goSelect.html" class="hd-select">
                <input type="text" class="hd-input" name="question" placeholder="搜索">
                <input type="submit" class="hd-btn">
            </form>
            <nav class="hd-nav">
                <a href="${pageContext.request.contextPath }" class="hd-nav-item">首页</a>
                <a href="#" class="hd-nav-item">我的学习</a>
                
				<c:if test="${not empty loginUser.name}">
					<a href="#" class="hd-nav-item">${loginUser.name }</a>
				</c:if>
				<c:if test="${empty loginUser.name }">
					<a href="${pageContext.request.contextPath }/goLoginPage.html" class="hd-nav-item">登录/注册</a>
				</c:if>
				
                <a href="javascript:void(0)" class="hd-nav-item">
                	<c:if test="${not empty loginUser.imageurl}">
                    	<img src="${pageContext.request.contextPath }${loginUser.imageurl}" alt="">
                	</c:if>
                	<c:if test="${empty loginUser.imageurl}">
                    	<img class="none" src="" alt="">
                	</c:if>
                </a>
            </nav>
            <div class="hd-more-wrap">
                <div class="hd-more">
                    <a href="#" class="hd-more-itme">我的学习</a>
                    <a href="#" class="hd-more-itme">个人资料</a>
                    <a href="#" class="hd-more-itme">我要讲课</a>
                    <a href="${pageContext.request.contextPath }/exitLogin.html" class="hd-more-itme">退出</a>
                </div>
            </div>
        </div>
    </header>
<!--头部导航结束-->