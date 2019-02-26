<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>课程管理</title>
<link href="${pageContext.request.contextPath }/css/courseManage.css" rel="stylesheet"></head>

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
                <li class="active">我创建的</li>
                <li><a href="#">新建</a></li>
            </ul>
        </div>
        <div class="tab-panel course-item-wrap active">
            <!-- 每个course-item都是一个课程 -->
            <c:forEach items="${collectionCourse }" var="course">
	            <a href="${pageContext.request.contextPath}/goSetCourse.html?courseId=${course.id}" target="_blank" class="course-item">
	                <div class="cur-item-img">
	                    <img src="${pageContext.request.contextPath}${course.image}" alt="">
	                </div>
	                <h2 class="cur-item-name">${course.name}</h2>
	                <p class="cur-item-details">${course.details}</p>
	                <p class="cur-item-num">${course.collection}人收藏过</p>
	            </a>
            </c:forEach>
        </div>
        <!-- 新建课程遮罩 -->
        <form action="createCourse.html" class="newCourse-alert">
            <div class="alert-body">
                <h1 class="alert-title">新建课程</h1>
                <div class="name-input">
                    <label for="course-name">课程名称：</label>
                    <input type="text" id="course-name" name="name" onfocus>
                    <div class="alert-btn"><input type="submit" value="创建"></div>
                </div>
            </div>
        </form>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/courseManage.min.js"></script></body>

</html>