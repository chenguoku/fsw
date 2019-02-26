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
<link href="${pageContext.request.contextPath}/css/setCourse.css" rel="stylesheet"></head>

<body>
    <header class="header">
        <h1 class="hd-title">${course.name }</h1>
    </header>
    <div class="body-title">课程资料</div>
    <main class="main">
        <aside class="aside">
            <ul class="aside-list">
                <li>
                    <a href="javascript:void(0)" class="active">课程资料</a>
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
                    <a href="${pageContext.request.contextPath}/goTestPageManage.html?courseId=${course.id }">试卷管理</a>
                </li>
            </ul>
        </aside>
        <div class="body">
            <form action="updateCourseInfo.html" class="body-form" method="post">
           		<input type="hidden" name="courseId" value="${course.id }">
                <!-- 名称 -->
                <div>
                    <label for="name">课程名称：</label>
                    <input type="text" id="name" name="name" value="${course.name }">
                </div>
                <!-- 分类 -->
                <div>
                    <label for="type">课程分类：</label>
                    <select name="type" id="type">
                    <!-- 1.语言学习 2.办公效率 3.兴趣生活 4.升学考研 5.编程开发 6.产品设计 -->
                        <option value="1" <c:if test="${course.type == '1'}">selected</c:if> >${course.status }语言学习</option>
                        <option value="2" <c:if test="${course.type == '2'}">selected</c:if> >办公效率</option>
                        <option value="3" <c:if test="${course.type == '3'}">selected</c:if> >兴趣生活</option>
                        <option value="4" <c:if test="${course.type == '4'}">selected</c:if> >升学考研</option>
                        <option value="5" <c:if test="${course.type == '5'}">selected</c:if> >编程开发</option>
                        <option value="6" <c:if test="${course.type == '6'}">selected</c:if> >产品设计</option>
                    </select>
                </div>
                <!-- logo -->
                <div>
                    <span class="logo-text">课程logo：</span>
                    <img src="${pageContext.request.contextPath}${course.image}" alt="" class="course-img">
                    <span class="up-img">上传图片</span>
                </div>
                <!-- 详情 -->
                <div>
                    <label for="info">课程详情：</label>
                    <textarea name="info" id="info">${course.details }</textarea>
                </div>
                <!-- 教师 -->
                <div>
                    <label for="teacher">授课教师：</label>
                    <input type="text" name="teacher" id="teacher" value="${course.teacher }">
                </div>
                <div>
                    <label for="on-off">是否上线：</label>
                    <select name="status" id="on-off">
                    
                        <option value="1" <c:if test="${course.status == 1}">selected</c:if> >上线</option>
                        <option value="0" <c:if test="${course.status == 0}">selected</c:if>>下线</option>
                    </select>
                </div>
                <input type="submit" value="保存">
            </form>
        </div>
        <!-- 修改头像弹窗 -->
        <form action="updateCourseImage.html" class="alert"enctype="multipart/form-data" method="post">
            <div class="alert-body-wrap">
                <h1>修改logo</h1>
                <img id="showImg" src="${pageContext.request.contextPath}${course.image}" alt="">
                <div class="alert-btn">
                    <span>选择图片</span>
                    <input type="file" id="imgFile" accept="image/*" name="imgFile">
                    <input type="hidden" name="courseId" value="${course.id }">
                    <input class="imgFile-btn" type="submit" value="保存">
                </div>
            </div>
        </form>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/setCourse.min.js"></script></body>

</html>