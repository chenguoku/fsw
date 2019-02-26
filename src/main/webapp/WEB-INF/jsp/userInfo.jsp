<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户名——个人资料</title>
<link href="${pageContext.request.contextPath}/css/userInfo.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <jsp:include page="commons/header.jsp"></jsp:include>
    <!--头部导航结束-->
    <main class="main">
        <div class="user-info-wrap">
            <!-- 展示账户信息 -->
            <h1 class="user-info">账户信息
                <!-- userInfo-msg没有内容自动隐藏 -->
                <span class="userInfo-msg"><c:if test="${not empty accountInfo }">${accountInfo }</c:if></span>
            </h1>
            <div class="user-name">
                <p>用户名：</p>
                <span>${loginUser.name}</span>
                <a href="javascript:void(0)" class="setName">修改</a>
            </div>
            <div class="user-email">
                <p>邮箱：</p>
                <span>${loginUser.email}</span>
                <!-- <a href="javascript:void(0)" class="setEmali">修改</a> -->
            </div>
            <div class="user-passwd">
                <p>密码：</p>
                <span>******</span>
                <a href="javascript:void(0)" class="setPasswd">修改密码</a>
            </div>
            <!-- 展示头像 -->
            <div class="user-headImg">
                <img src="${pageContext.request.contextPath }${loginUser.imageurl}" alt="">
                <span class="headImg-btn">修改头像</span>
            </div>
            <!-- 展示个人信息 -->
            <div class="twoInfo-wrap active">
                <h1 class="user-info twoInfo">个人信息
                    <a href="javascript:void(0)" class="setInfo">修改</a>
                    <span class="userInfo-msg"><c:if test="${not empty personalInfo }">${personalInfo }</c:if></span>
                </h1>
                <div class="user-type">
                    <p>身份：</p>
                    <span>
                    	<c:if test="${loginUser.type == 1 }">学生</c:if>
                    	<c:if test="${loginUser.type == 2 }">老师</c:if>
                    	<c:if test="${loginUser.type == 3 }">管理员</c:if>
                    </span>
                </div>
                <div class="user-sex">
                    <p>性别：</p>
                    <span>${loginUser.sex}</span>
                </div>
                <div class="user-school">
                    <p>学校：</p>
                    <span>${loginUser.school}</span>
                </div>
            </div>
            <!-- 修改个人信息 -->
            <form action="updateUserInfo.html" class="twoInfo-form">
                <h1 class="user-info twoInfo">个人信息</h1>
                <div class="user-type">
                    <p>身份：</p>
                    <span>
						<c:if test="${loginUser.type == 1 }">学生</c:if>
                    	<c:if test="${loginUser.type == 2 }">老师</c:if>
                    	<c:if test="${loginUser.type == 3 }">管理员</c:if>
					</span>
                </div>
                <div class="user-sex">
                    <p>性别：</p>
                    <select name="sex">
                    	
                        <option value="男" <c:if test="${loginUser.sex == '男'}">selected</c:if>>男</option>
                        <option value="女" <c:if test="${loginUser.sex == '女'}">selected</c:if>>女</option>
                    </select>
                </div>
                <div class="user-school">
                    <p>学校：</p>
                    <input type="text" name="school" value="绥化学院" maxlength="20" />
                </div>
                <p><input type="submit" value="完成"></p>
            </form>
            <!-- 修改用户名弹窗 -->
            <form action="updateUserInfo.html" class="alert alert1">
                <div class="alert-body-wrap">
                    <h1>修改用户名</h1>
                    <div class="alert-body">
                        新用户名：
                        <input type="text" name="name" maxlength="12" autofocus="autofocus">
                    </div>
                    <div class="alert-btn">
                        <input type="submit" value="保存">
                    </div>
                </div>
            </form>
            <!-- 修改密码弹窗 -->
            <form action="updateUserInfo.html" class="alert alert2" method="post">
                <div class="alert-body-wrap">
                    <h1>修改密码</h1>
                    <div class="alert-body">
                        旧密码：
                        <input type="password" name="oldPasswd" maxlength="16" autofocus="autofocus">
                    </div>
                    <div class="new-passwd">
                        新密码：
                        <input type="password" name="newPasswd" maxlength="16">
                    </div>
                    <div class="re-passwd">
                        确&nbsp;&nbsp;&nbsp;认：
                        <input type="password" name="rePasswd" maxlength="16">
                    </div>
                    <div class="alert-btn">
                        <input type="submit" value="保存">
                    </div>
                </div>
            </form>
            <!-- 修改头像弹窗 -->
            <form action="updateImg.html" class="alert alert3" enctype="multipart/form-data" method="post">
                <div class="alert-body-wrap">
                    <h1>修改头像</h1>
                    <img id="showImg" src="${pageContext.request.contextPath }${loginUser.imageurl}" alt="">
                    <div class="alert-btn">
                        <span>选择头像</span>
                        <input type="file" id="imgFile" accept="image/*" name="imgFile">
                        <input class="imgFile-btn" type="submit" value="保存">
                    </div>
                </div>
            </form>
        </div>
    </main>
    <!-- 版尾 -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- 版尾结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/userInfo.min.js"></script>
</body>

</html>