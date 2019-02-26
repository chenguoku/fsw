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
<link href="${pageContext.request.contextPath }/css/commentManage.css" rel="stylesheet"></head>

<body>
    <!-- 课程id给我 -->
    <input type="hidden" value="${course.id }" class="courseId">
    <header class="header">
        <h1 class="hd-title">${course.name }</h1>
    </header>
    <div class="body-title">评论管理</div>
    <main class="main">
        <aside class="aside">
            <ul class="aside-list">
                <li>
                    <a href="${pageContext.request.contextPath }/goSetCourse.html?courseId=${course.id }">课程资料</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goVideoManage.html?courseId=${course.id }">视频管理</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="active">评论管理</a>
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
                <span class="videoAdd">打印</span>
            </div>
            <!-- 十条数据 -->
            <!--startprint-->
            <table></table>
            <!--endprint-->
            <ul class="page" id="page"></ul>
        </div>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/commentManage.min.js"></script></body>

</html>