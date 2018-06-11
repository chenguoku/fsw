<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>发生网——登录</title>
<link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet"></head>

<body>
    <!--头部导航开始-->
    <header class="header">
        <div class="hd-content">
        
         	 <a href="#" class="hd-logo">
                <img src="${pageContext.request.contextPath }/image/index/logo.png" alt="">
            </a>
            <form action="" class="hd-select">
                <input type="text" class="hd-input" name="question" placeholder="搜索">
                <input type="submit" class="hd-btn">
            </form>
            <nav class="hd-nav">
                <a href="${pageContext.request.contextPath }" class="hd-nav-item">首页</a>
                <a href="${pageContext.request.contextPath }/goRegisterPage.html" class="hd-nav-item">注册</a>
                <a href="#" class="hd-nav-item">曲智超曲智超曲智超</a>
                <a href="javascript:void(0)" class="hd-nav-item">
                    <img src="http://www.fashengwang.com/HeadPortrait/1/1.jpg" alt="">
                </a>
            </nav>
            <div class="hd-more-wrap">
                <div class="hd-more">
                    <a href="#" class="hd-more-itme">我的学习</a>
                    <a href="#" class="hd-more-itme">个人资料</a>
                    <a href="#" class="hd-more-itme">我要讲课</a>
                    <a href="#" class="hd-more-itme">退出</a>
                </div>
            </div>
            
            
        </div>
    </header>
    <!--头部导航结束-->
    <!-- 中间内容 -->
    <main class="main">
        <!-- 轮播图 -->
        <div class="lunbo">
            <div class="lunbo-cont zero">
                <div class="lunbo-img-wrap">
                    <img src="${pageContext.request.contextPath }/image/login/login1_bg.jpg" alt="" class="lunbo-img active">
                    <img src="${pageContext.request.contextPath }/image/login/login1_animate_1.png" alt="" class="lunbo-tt active">
                    <!-- 登录框 -->
                    
                    <div class="login-wrap">
                        <h1 class="login-tt">登录</h1>
                        <form action="login.html" class="login-form" method="post">
                            <input type="text" class="login-name" name="name" placeholder="用户名/邮箱" autofocus>
                            <input type="password" class="login-passwd" name="password" placeholder="密码">
                            
                            <div class="login-case">
                                <a href="#" class="lg-case-item">忘记密码</a>
                            </div>
                            <span style="color:red">${requestScope.err }</span>
                            <input type="submit" class="login-btn" value="登录">
                        </form>
                        
                    </div>
                </div>
                <div class="lunbo-img-wrap">
                    <img src="${pageContext.request.contextPath }/image/login/login2_bg.jpg" alt="" class="lunbo-img">
                    <img src="${pageContext.request.contextPath }/image/login/login2_animate_1.png" alt="" class="lunbo-tt">
                </div>
                <div class="lunbo-img-wrap">
                    <img src="${pageContext.request.contextPath }/image/login/login3_bg.jpg" alt="" class="lunbo-img">
                    <img src="${pageContext.request.contextPath }/image/login/login3_animate_1.png" alt="" class="lunbo-tt">
                </div>
                <div class="lunbo-img-wrap">
                    <img src="${pageContext.request.contextPath }/image/login/login4_bg.jpg" alt="" class="lunbo-img">
                    <img src="${pageContext.request.contextPath }/image/login/login4_animate_1.png" alt="" class="lunbo-tt">
                </div>
                <div class="lunbo-img-wrap">
                    <img src="${pageContext.request.contextPath }/image/login/login5_bg.jpg" alt="" class="lunbo-img">
                    <img src="" alt="" class="lunbo-tt">
                    <!-- 版尾 -->
                    <div class="footer">
                        <div class="ft-wrap">
                            <div class="ft-left">
                                <p class="ft-tt">发生网，基于听障生打造的智能在线学习网站。</p>
                                <div class="ft-list">
                                    <a href="#" class="ft-item">意见反馈</a>
                                    <a href="#" class="ft-item">广告服务</a>
                                    <a href="#" class="ft-item">关于我们</a>
                                    <a href="#" class="ft-item">合作入口</a>
                                    <a href="#" class="ft-item">服务条款</a>
                                </div>
                                <p class="ft-copy">&copy;2017-2018 发生网 www.fashengwang.com 版权所有</p>
                            </div>
                            <div class="ft-right">
                                <p class="contact">联系我们：</p>
                                <a href="javascript:void(0)" class="ct-qq"></a>
                                <img class="ct-qq-img" src="${pageContext.request.contextPath }/image/login/weixin_01.png">
                                <a href="javascript:void(0)" class="ct-weixin"></a>
                                <img class="ct-weixin-img" src="${pageContext.request.contextPath }/image/login/weixin_01.png">
                                <a href="javascript:void(0)" class="ct-email"></a>
                                <img class="ct-email-img" src="${pageContext.request.contextPath }/image/login/weixin_01.png">
                            </div>
                        </div>
                    </div>
                    <!-- 版尾结束 -->
                </div>
            </div>
            <!-- 向下箭头 -->
            <div class="lunbo-down-wrap">
                <i class="lunbo-down"></i>
            </div>
            <!-- 右侧圆点 -->
            <div class="lunbo-btns">
                <span class="btns-item active"></span>
                <span class="btns-item"></span>
                <span class="btns-item"></span>
                <span class="btns-item"></span>
                <span class="btns-item"></span>
            </div>
        </div>
    </main>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.min.js"></script></body>

</html>