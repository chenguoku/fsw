<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网——课程查询</title>
<link href="${pageContext.request.contextPath }/css/select.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="select-cont">
            <!-- 搜索条 -->
            <div class="select-head">
                <input type="text" class="select-input" name="question" placeholder="课程名称" autofocus>
                <input type="submit" class="select-btn" value="搜索">
            </div>
            <!-- 导航 -->
            <h3 class="select-tt">
                <a href="http://www.fashengwang.com">首页</a>&gt;
                <a href="#">课程查询</a>
            </h3>
            <!-- 查询条件 -->
            <div class="select-condition">
                <ul class="select-type">
                    <li class="select-type-tt">课程类别</li>
                    <li class="select-type-item active">全部</li>
                    <li class="select-type-item">编程开发</li>
                    <li class="select-type-item">产品设计</li>
                    <li class="select-type-item">升学考研</li>
                    <li class="select-type-item">兴趣生活</li>
                    <li class="select-type-item">办公效率</li>
                    <li class="select-type-item">语言学习</li>
                </ul>
                <ul class="select-sort">
                    <li class="select-sort-tt">排序方式</li>
                    <li class="select-sort-item active">综合</li>
                    <li class="select-sort-item">人气</li>
                    <li class="select-sort-item">最新</li>
                </ul>
            </div>
            <!-- 加载动画 -->
            <div class="loading">
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
                <span class="pillar"></span>
            </div>
            <!-- 课程列表 -->
            <div class="select-course-list"></div>
            <ul class="select-page"></ul>
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/select.min.js"></script></body>

</html>