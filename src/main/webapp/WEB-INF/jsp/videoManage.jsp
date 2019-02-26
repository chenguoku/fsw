<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>这里是课程名称</title>
<link href="${pageContext.request.contextPath }/css/videoManage.css" rel="stylesheet"></head>

<body>
    
    <header class="header">
        <h1 class="hd-title">${course.name }</h1>
    </header>
    <div class="body-title">视频管理</div>
    <main class="main">
        <aside class="aside">
            <ul class="aside-list">
                <li>
                    <a href="${pageContext.request.contextPath }/goSetCourse.html?courseId=${course.id }">课程资料</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="active">视频管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goCommentManage.html?courseId=${course.id }">评论管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goMaterialManage.html?courseId=${course.id }">相关素材</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goTestPageManage.html?courseId=${course.id }">试卷管理</a>
                </li>
            </ul>
        </aside>
        <div class="body">
            <div class="body-head">
                <span class="videoAdd">新增</span>
            </div>
            <!-- 十条数据 -->
            <table></table>
            <ul class="page" id="page"></ul>
        </div>
        <!-- 新增视频弹窗 -->
        <form action="newAndUpdateVideo.html" class="alert" enctype="multipart/form-data" method="post">
       		<!-- 课程id给我 -->
    		<input type="hidden"  value="${courseId }" name="courseId" class="courseId" />		
            <div class="alert-body-wrap">
                <h1>新增视频</h1>
                <div class="alert-body">
                    <div>
                        <label for="videoName">视频名称：</label>
                        <input type="text" id="videoName" name="name" maxlength="30">
                    </div>
                    <div>
                        <label for="videoIndex">视频排序：</label>
                        <input type="text" id="videoIndex" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="4" name="index">
                        <span>数字越小排名越靠前</span>
                    </div>
                    <div>
                        <label for="videoFile">上传视频：</label>
                        <input type="text" id="videoFile">
                        <span class="selectVideo">选择视频</span>
                        <input type="file" name="file" accept="video/*" id="upVideo">
                    </div>
                    <!-- 视频id value数字是修改，没有值是新增 -->
                    <input type="hidden" name="id" value="1">
                    <input type="submit" value="保存">
                </div>
            </div>
        </form>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/videoManage.min.js"></script></body>

</html>