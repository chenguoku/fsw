<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${course.name }</title>
	<link href="${pageContext.request.contextPath }/css/courseInfo.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <!-- 课程信息 -->
        <div class="courseInfo-wrap">
            <!-- 导航 -->
            <h3 class="courseInfo-nav">
                <a href="http://www.fashengwang.com">首页</a>&nbsp;&gt;&nbsp;
                <a href="http://www.fashengwang.com/goSelect.html?type=${course.type }">兴趣生活</a>&nbsp;&gt;&nbsp;
                <span>${course.name }</span>
            </h3>
            <!-- 上方信息部分 -->
            <div class="courseInfo">
                <div class="courseInfo-img">
                    <img src="${pageContext.request.contextPath}${course.image}" alt="">
                </div>
                <!-- 信息右侧部分 -->
                <div class="courseInfo-right">
                    <h1 class="courseInfo-tt">${course.name }</h1>
                    <!-- whetherCollection -->
                    
                    <c:if test="${whetherCollection == 1 }" >
                    	<span class="courseInfo-star fill-star">取消</span>
                    </c:if>
                    <c:if test="${whetherCollection == 2 }">
                    	<span class="courseInfo-star void-star">收藏</span>
                    </c:if>
                    
                    <!-- <span class="courseInfo-star void-star">收藏</span> -->
                    
                    <p class="courseInfo-teacher">授课教师：${course.teacher }</p>
                    <p class="courseInfo-details">${course.details }</p>
                </div>
            </div>
            <!-- 目录、评论 -->
            <div class="courseInfo-list">
                <!-- 选项卡 -->
                <div class="courseInfo-tab">
                    <ul class="tab-head">
                        <li class="active">目录</li>
                        <li>相关素材</li>
                        <li>互动答疑</li>
                        <li>课程练习</li>
                    </ul>
                    <!-- 加载动画 -->
                    <div class="loading">
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                    </div>
                    <div class="tab-panel-wrap">
                        <ul class="tab-panel active"></ul>
                        <ul class="tab-panel"></ul>
                        <ul class="tab-panel">
                            <div>
                                <p class="comment-msg">不能发表辱骂、政治等相关词汇</p>
                                <textarea class="comment-textarea" rows="5" maxlength="200" placeholder="欢迎分享课程有关内容，100字以内。"></textarea>
                                <div class="comment-btn">
                                    <span>发布</span>
                                    <p>0</p>/200
                                </div>
                            </div>
                        </ul>
                        <div class="loading more">
                            <span class="pillar"></span>
                            <span class="pillar"></span>
                            <span class="pillar"></span>
                            <span class="pillar"></span>
                            <span class="pillar"></span>
                        </div>
                        <p class="comment-more">已没有更多</p>
                        <ul class="tab-panel"></ul>
                    </div>
                </div>
                <!-- 五条最新评论 -->
                <div class="courseInfo-comment">
                    <h2 class="comment-tt">课程评价</h2>
                    
                    
                    <!-- 每个comment-item为一条评论 -->
                    <c:forEach items="${hotComments }" var="hotComment">
	                    <div class="comment-item">
	                        <div class="comment-head">
	                            <img src="${pageContext.request.contextPath}${hotComment.userImage}" alt="" class="comment-img">
	                            <div class="comment-info">
	                                <p class="comment-name">${hotComment.userName}</p>
	                                <p class="comment-time">${hotComment.createTime}</p>
	                            </div>
	                        </div>
	                        <div class="comment-cont">${hotComment.content}</div>
	                    </div>
                    </c:forEach>
                    
                    
                    
                    
                    
                </div>
            </div>
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/courseInfo.min.js"></script></body>

</html>