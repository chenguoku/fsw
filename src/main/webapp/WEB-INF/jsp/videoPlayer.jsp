<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${pageContext.request.contextPath }/css/video-js.css" rel="stylesheet">
    <title>${showVideo.name }</title>
<link href="${pageContext.request.contextPath }/css/videoPlayer.css" rel="stylesheet"></head>

<body>
    <ul class="left">
        <h1 class="left-tt">${video.name }</h1>
        <c:forEach items="${videoList }" var="video" begin="0" end="${videoSize}" varStatus="status">
        
	        <a href="${pageContext.request.contextPath }/goVideoPlayer.html?courseId=${video.courseId}&index=${status.index }"
	         class="left-item <c:if test="${videoIndex == status.index}" >active</c:if>">${video.name } </a>
        </c:forEach>
    </ul>
    <div class="right">
        <div class="right-header">
            <h1 class="listBtn">目录</h1>
            <h1 class="right-hd-tt">${showVideo.name }</h1>
        </div>
        <video id="my-video" class="video-js vjs-big-play-centered" controls preload="auto" data-setup="{}">
            <source src="${pageContext.request.contextPath }${showVideo.url }" type="video/mp4">
            <p class="vjs-no-js">
              	  您的浏览器不支持  HTML5 video
                <a href="http://videojs.com/html5-video-support/" target="_blank">您的浏览器不支持  HTML5 video</a>
            </p>
        </video>
    </div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/videoPlayer.min.js"></script></body>
<script src="${pageContext.request.contextPath }/js/video.min.js"></script>
<script type="text/javascript">
    var myPlayer = videojs('my-video');
    videojs("my-video").ready(function () {
        var myPlayer = this;
        myPlayer.play();
    });
</script>
</html>