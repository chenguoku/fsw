<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<base href="<%=basePath%>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网——一切尽在发生</title>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
</head>

<body>
    
    
    <jsp:include page="commons/header.jsp"></jsp:include>
    
    <!-- 主体开始 -->
    <main class="main">
        <!-- 轮播图开始 -->
        <div class="lunbo-wrap pink">
            <div class="lunbo-img-wrap">
                <img src="${pageContext.request.contextPath}/image/index/main_advertise_1.png" alt="" class="lunbo-img active">
                <img src="${pageContext.request.contextPath}/image/index/main_advertise_2.png" alt="" class="lunbo-img">
                <img src="${pageContext.request.contextPath}/image/index/main_advertise_3.png" alt="" class="lunbo-img">
            </div>
            <div class="lunbo-btn-wrap">
                <span class="lunbo-btn active"></span>
                <span class="lunbo-btn"></span>
                <span class="lunbo-btn"></span>
            </div>
        </div>
        <!-- 轮播图结束 -->
        <!-- 课程列表 -->
        <div class="course-list">
            <img src="${pageContext.request.contextPath}/image/index/interest.jpg " alt="" class="course-interest <c:if test="${empty interestingCourse }">active</c:if>">
            <div class="loading">
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
            </div>
            <div class="alert-wrap">
                <div class="alert">
                    <h1 class="alert-tt">选择您的兴趣</h1>
                    <span class="alert-item">语言学习</span>
                    <span class="alert-item">办公效率</span>
                    <span class="alert-item">兴趣生活</span>
                    <span class="alert-item">升学考研</span>
                    <span class="alert-item">编程开发</span>
                    <span class="alert-item">产品设计</span>
                    <p class="alert-btn">保存</p>
                </div>
            </div>
            <!-- 兴趣课 none隐藏 -->
            <div class="course-wrap <c:if test="${empty interestingCourse }">none</c:if>">
                <div class="course-tt">
                    <h1 class="course-type">兴趣推荐</h1>
                    <a href="#" class="course-more">更改兴趣&gt;</a>
                </div>
                <div class="course-item-wrap">
                    
                    <c:forEach items="${interestingCourse }" var="course">
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
            </div>
            <!-- 热门课程 -->
            <div class="course-wrap">
                <div class="course-tt">
                    <h1 class="course-type">热门课程</h1>
                    <a href="${pageContext.request.contextPath}/goSelect.html?sort=1" class="course-more">更多&gt;</a>
                </div>
                <div class="course-item-wrap">
                    <c:forEach items="${hotTypeList }" var="hotCourse">
                    	<a href="${pageContext.request.contextPath}/goCourseInfo.html?courseId=${hotCourse.id }" class="course-item">
	                        <div class="cur-item-img">
	                            <img src="${pageContext.request.contextPath}${hotCourse.image}" alt="">
	                        </div>
	                        <div>
	                            <h2 class="cur-item-name">${hotCourse.name}</h2>
	                            <p class="cur-item-details">${hotCourse.details }</p>
	                            <p class="cur-item-num">${hotCourse.collection }人收藏过</p>
	                        </div>
                    	</a>
                    </c:forEach>
                </div>
            </div>
            <!-- 兴趣生活 -->
            <div class="course-wrap">
                <div class="course-tt">
                    <h1 class="course-type">兴趣生活</h1>
                    <a href="${pageContext.request.contextPath}/goSelect.html?type=4" class="course-more">更多&gt;</a>
                </div>
                <div class="course-item-wrap">
                    <c:forEach items="${type3List }" var="type3Course">
                    	<a href="${pageContext.request.contextPath}/goCourseInfo.html?courseId=${type3Course.id }" class="course-item">
	                        <div class="cur-item-img">
	                            <img src="${pageContext.request.contextPath}${type3Course.image}" alt="">
	                        </div>
	                        <div>
	                            <h2 class="cur-item-name">${type3Course.name}</h2>
	                            <p class="cur-item-details">${type3Course.details}</p>
	                            <p class="cur-item-num">${type3Course.collection}人收藏过</p>
	                        </div>
                    	</a>
                    </c:forEach>
                    
                    
                </div>
            </div>
            <!-- 编程开发 -->
            <div class="course-wrap">
                <div class="course-tt">
                    <h1 class="course-type">编程开发</h1>
                    <a href="${pageContext.request.contextPath}/goSelect.html?type=1" class="course-more">更多&gt;</a>
                </div>
                <div class="course-item-wrap">
                    <c:forEach items="${type5List }" var="type5Course">
                    	<a href="${pageContext.request.contextPath}/goCourseInfo.html?courseId=${type5Course.id }" class="course-item">
	                        <div class="cur-item-img">
	                            <img src="${pageContext.request.contextPath}${type5Course.image}" alt="">
	                        </div>
	                        <div>
	                            <h2 class="cur-item-name">${type5Course.name}</h2>
	                            <p class="cur-item-details">${type5Course.details}</p>
	                            <p class="cur-item-num">${type5Course.collection}人收藏过</p>
	                        </div>
                    	</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- 课程列表结束 -->
    </main>
    <!-- 主体结束 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.min.js"></script>
</body>

</html>