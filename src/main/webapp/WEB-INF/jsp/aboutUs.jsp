<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>关于我们</title>
<link href="${pageContext.request.contextPath }/css/aboutUs.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="main_pic">
            <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_pic.gif" alt="" />
        </div>
        <div class="main_text">
            <div class="main_text_top"></div>
            <div class="main_text_1">
                <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_logo1.gif" alt="" />
                <span>&nbsp;如果您在学习时，有关整体平台的建议，反馈，技术问题，请和我们产品人员联系，我们希望在平台形态、学习体验和教学支持都做到更加完善</span>
            </div>
            <div class="main_text_1">
                <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_logo4.gif" alt="" />
                <span>&nbsp;如果您所在的大学或公司，希望使用发生网，或者你们想要提供课程，请和我们联系。</span>
            </div>
            <div class="main_text_1">
                <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_logo3.gif" alt="" />
                <span>&nbsp;如果您参加课程学习的学生有疑问，反馈，错误报告或者关于特定问题的课程。我们的工作人员会查看收集改正及解决技术问题。</span>
            </div>
            <div class="main_text_1">
                <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_logo5.gif" alt="" />
                <span>&nbsp;客服提醒：为了更好更快的帮您解决问题，请在邮件中留下电话，发生网账号，截图等相关信息。</span>
            </div>
            <div class="main_text_1">
                <img src="http://www.fashengwang.com/image/aboutUs/aboutOur_logo2.gif" alt="" />
                <span>&nbsp;Email： 746515766@qq.com</span>
                <br/>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tel： 18245895215</span>
            </div>
        </div>
    </main>
	<!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/aboutUs.min.js"></script></body>

</html>