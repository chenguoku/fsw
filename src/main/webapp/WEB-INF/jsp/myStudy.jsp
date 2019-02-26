<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网——我的学习</title>
<link href="${pageContext.request.contextPath}/css/myStudy.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="study-cover">
            <img class="study-cover-img" src="${pageContext.request.contextPath}/image/myStudy/studyCover.jpg" width="100%" alt="">
            <ul class="user-info">
                <img class="user-img" src="${pageContext.request.contextPath}${loginUser.imageurl }" alt="">
                <div class="user-info-wrap">
                    <h1 class="userName">${loginUser.name }</h1>
                    <p class="randomText">${inspiringWords }</p>
                </div>
            </ul>
        </div>
        <div class="study-nav">
            <ul class="tab-head">
                <li class="active">收藏夹</li>
            </ul>
        </div>
        <div class="tab-panel course-item-wrap active">
            <!-- 每个course-item都是一个课程 -->
            
            <c:forEach items="${collectionCourse }" var="course">
	            <a href="${pageContext.request.contextPath}/goCourseInfo.html?courseId=${course.id }" class="course-item">
	                <div class="cur-item-img">
	                    <img src="${pageContext.request.contextPath}${course.image}" alt="">
	                </div>
	                <h2 class="cur-item-name">${course.name}</h2>
	                <p class="cur-item-details">${course.details}</p>
	                <p class="cur-item-num">${course.collection}人收藏过</p>
	            </a>
            </c:forEach>
            
            
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myStudy.min.js"></script></body>

</html>