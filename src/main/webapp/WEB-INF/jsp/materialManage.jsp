<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${course.name }</title>
<link href="${pageContext.request.contextPath }/css/materialManage.css" rel="stylesheet"></head>

<body>
    <header class="header">
        <h1 class="hd-title">${course.name }</h1>
    </header>
    <div class="body-title">相关素材</div>
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
                    <a href="javascript:void(0)" class="active">相关素材</a>
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
        <!-- 新增素材弹窗 -->
        <form action="newAndUpdateMaterial.html" class="alert" enctype="multipart/form-data" method="post">
            <!-- 课程id给我 -->
            <input type="hidden" value="${course.id }" class="courseId" name="courseId" />
            <div class="alert-body-wrap">
                <h1>新增素材</h1>
                <div class="alert-body">
                    <div>
                        <label for="videoName">素材名称：</label>
                        <input type="text" id="videoName" name="name" maxlength="30">
                    </div>
                    <div>
                        <label for="videoIndex">素材排序：</label>
                        <input type="text" id="videoIndex" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="4" name="index">
                        <span>数字越小排名越靠前</span>
                    </div>
                    <div>
                        <label for="videoFile">上传素材：</label>
                        <input type="text" id="videoFile">
                        <span class="selectVideo">选择素材</span>
                        <input type="file" name="file" id="upVideo">
                    </div>
                    <!-- 素材id value数字是修改，没有值是新增 -->
                    <input type="hidden" name="id" value="1">
                    <input type="submit" value="保存">
                </div>
            </div>
        </form>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/materialManage.min.js"></script></body>

</html>