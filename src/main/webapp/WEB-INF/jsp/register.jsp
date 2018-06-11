<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网——注册</title>
<link href="${pageContext.request.contextPath }/css/register.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="register">
            <form action="register.html" class="register-left">
                <h1 class="register-tt">发生网用户注册</h1>
                <input type="text" name="name" class="register-input" value="<c:if test="${not empty name }">${name }</c:if>" placeholder='<c:if test="${empty name }">请输入用户名</c:if>' maxlength="12">
                <div class="register-email-wrap">
                    <input type="text" name="email" class="register-input" value="<c:if test="${not empty email }">${email }</c:if>" placeholder="<c:if test="${empty email }">请输入邮箱</c:if>" maxlength="30">
                    <span class="send-code">发送验证码</span>
                </div>
                <input type="text" name="code" class="register-input" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入验证码" maxlength="6">
                <input type="password" name="passwd" class="register-input" value="<c:if test="${not empty passwd }">${passwd }</c:if>" placeholder="<c:if test="${empty passwd }">请输入登录密码</c:if>" maxlength="16">
                <p class="register-msg">用户名已存在</p>
                <p style="color:red" class="<c:if test=" ${empty codeErr }">none</c:if>" >${codeErr }</p>
                <div class="agreement">
                    <input type="submit" class="register-btn" value="注册">
                    <input type="checkbox" id="agreement" checked>
                    <label for="agreement">同意《发生网用户注册协议》</label>
                </div>
            </form>
            <div class="register-bar"></div>
            <div class="register-right">
                <h1 class="right-tt">海量视频&nbsp;种类繁多</h1>
                <h1 class="right-tt">随时随地&nbsp;无障观看</h1>
                <h1 class="right-tt">评论提问&nbsp;互动学习</h1>
                <h1 class="right-tt">云端储存&nbsp;资源共享</h1>
            </div>
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.min.js"></script></body>

</html>