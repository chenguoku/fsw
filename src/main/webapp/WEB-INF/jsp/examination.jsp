<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>这里是试卷名称</title>
<link href="${pageContext.request.contextPath }/css/examination.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="testPaper">
            <div class="testPaper-during active">
                <div class="tp-head">
                    <div class="tp-hd-bar-lower">
                        <span class="tp-hd-bar"></span>
                    </div>
                    <div class="tp-hd-index">0/0</div>
                    <div class="tp-hd-time">00:00:00</div>
                </div>
                <div class="tp-body" id="options">
                    <div class="loading active">
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                        <span class="pillar"></span>
                    </div>
                </div>
                <div class="correct-answer"></div>
                <div class="tp-body-btn"></div>
                <ul class="tp-body-page" id="pageBtn"></ul>
            </div>
            <div class="testPaper-result">
                <h1 class="tp-r-hd">
                    <span>得分情况</span>
                </h1>
                <div class="tp-r-body">
                    <div class="tp-r-left">
                        <p class="tp-score-ion">得分：0</p>
                        <p class="tp-correct">正确题数：0/10</p>
                        <p class="tp-score">得分：0</p>
                        <p class="tp-time">用时：00:00:00</p>
                        <p class="tp-answer">习题答案：</p>
                    </div>
                    <div class="tp-r-right">
                        <img src="${pageContext.request.contextPath }/image/examination/daydayup.jpg" alt="">
                    </div>
                </div>
                <div class="tp-back">
                    <span>查看试卷</span>
                </div>
            </div>
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/examination.min.js"></script></body>

</html>