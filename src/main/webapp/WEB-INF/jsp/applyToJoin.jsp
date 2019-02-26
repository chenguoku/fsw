<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>申请入驻</title>
<link href="${pageContext.request.contextPath }/css/applyToJoin.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <div class="cover">
        <div class="cover-body">
            <h1 class="cover-title">申请成为入驻讲师</h1>
            <p class="cover-info">更新知识能够改变世界，传播知识也能改变世界。加入我们，将知识传递给最需要的人。</p>
            <p class="cover-email">请将您的姓名或机构名称、入驻说明、联系方式等相关材料整理好后发送至746515766@qq.com我们将在一周内回复您</p>
            <button class="cover-btn">申请入驻</button>
        </div>
    </div>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/applyToJoin.min.js"></script></body>

</html>