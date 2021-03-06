<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网后台管理系统</title>
<link href="${pageContext.request.contextPath }/css/teacherManagement.css" rel="stylesheet"></head>

<body>
    <header class="header">
        <h1 class="hd-title">发生网后台管理系统</h1>
    </header>
    <div class="body-title">教师管理</div>
    <main class="main">
        <aside class="aside">
            <ul class="aside-list">
                <li>
                    <a href="${pageContext.request.contextPath}/goBackstageManagement.html">学生管理</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="active">教师管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goBackstageCommentManage.html">评论管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/backstageCourseManage.html">课程管理</a>
                </li>
            </ul>
        </aside>
        <div class="body">
            <div class="body-head">
                <input type="text" class="select-username" placeholder="用户名">
                <span class="videoAdd">搜索</span>
            </div>
            <!-- 十条数据 -->
            <table></table>
            <ul class="page" id="page"></ul>
        </div>
        <!-- 新增试卷弹窗 -->
        <form action="managerTeacherInfo.html" method="POST" class="alert">
            <!-- userId -->
            <input type="hidden" value="1" name="userId" class="courseId">
            <div class="alert-body-wrap">
                <h1>编辑用户</h1>
                <div class="alert-body">
                    <div class="test-tt-wrap">
                        <div>
                            <label for="videoName">用户名称：</label>
                            <input type="text" id="videoName" name="userName" maxlength="12">
                        </div>
                        <div>
                            <label>性别：</label>
                            <input type="radio" name="sex" id="sex-nan" value="男"><label for="sex-nan">男</label>
                            <input type="radio" name="sex" id="sex-nv" value="女"><label for="sex-nv">女</label>
                        </div>
                        <div>
                            <label for="school">学校：</label>
                            <input type="text" id="school" name="school" maxlength="20">
                        </div>
                    </div>
                    <input type="submit" value="保存">
                </div>
            </div>
        </form>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/teacherManagement.min.js"></script></body>

</html>