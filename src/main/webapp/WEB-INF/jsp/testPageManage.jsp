<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${course.name }</title>
<link href="${pageContext.request.contextPath }/css/testPageManage.css" rel="stylesheet"></head>

<body>
    <header class="header">
        <h1 class="hd-title">${course.name }</h1>
    </header>
    <div class="body-title">试卷管理</div>
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
                    <a href="${pageContext.request.contextPath}/goCommentManage.html?courseId=${course.id }">评论管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goMaterialManage.html?courseId=${course.id }">相关素材</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="active">试卷管理</a>
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
        <!-- 新增试卷弹窗 -->
        <form action="insertTestPage.html" method="POST" class="alert">
            <!-- 课程id给我 -->
            <input type="hidden" value="${course.id }" name="courseId" class="courseId">
            <div class="alert-body-wrap">
                <h1>新增试卷</h1>
                <div class="alert-body">
                    <div class="test-tt-wrap">
                        <div>
                            <label for="videoName">试卷名称：</label>
                            <input type="text" id="videoName" name="testPageName" maxlength="30">
                        </div>
                        <div>
                            <label for="videoIndex">试卷排序：</label>
                            <input type="text" name="testPageIndex" id="videoIndex" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="4">
                            <span>数字越小排名越靠前</span>
                        </div>
                        <!-- value为试卷id的值，0是添加，其余为修改 -->
                        <input type="hidden" name="testPageId" value="0">
                        <!-- 问题数量 -->
                        <input type="hidden" name="questionCount" value="3">
                    </div>
                    <div id="alert-list"></div>
                    <div class="addQuestion" title="添加一个问题"></div>
                    <input type="submit" value="保存">
                </div>
            </div>
        </form>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/testPageManage.min.js"></script></body>

</html>