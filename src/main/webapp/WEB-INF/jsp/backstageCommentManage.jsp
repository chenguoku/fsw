<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网后台管理系统</title>
<link href="${pageContext.request.contextPath }/css/backstageCommentManage.css" rel="stylesheet"></head>

<body>
    <!-- 课程id给我 -->
    <input type="hidden" value="1" class="courseId">
    <header class="header">
        <h1 class="hd-title">发生网后台管理系统</h1>
    </header>
    <div class="body-title">评论管理</div>
    <main class="main">
        <aside class="aside">
            <ul class="aside-list">
                <li>
                    <a href="${pageContext.request.contextPath}/goBackstageManagement.html">学生管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goTeacherManagement.html">教师管理</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="active">评论管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/backstageCourseManage.html">课程管理</a>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/backstageCommentManage.min.js"></script></body>

</html>