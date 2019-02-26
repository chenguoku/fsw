<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>意见反馈</title>
<link href="${pageContext.request.contextPath }/css/adviceOur.css" rel="stylesheet"></head>

<body>
    <main class="main">
        <div class="main_con">
            <div class="main_con_title">
                <img src="http://www.fashengwang.com/image/adciveOur/adciveOur_smile.gif" alt="" />
                <span>请您对我们提出宝贵的意见和建议，您留下的每个意见都是我们产品不断前进的动力！</span>
            </div>
            <div class="main_con_form">
                <form class="main_form" name="main_form" action="#" method="post">
                    <div class="ge question_response">
                        <span>&nbsp;&nbsp;问题反馈：</span>
                        <textarea name="context" id="advice" rows="5"></textarea>
                        <br/>
                    </div>
                    <div class="ge question_type">
                        <span>&nbsp;&nbsp;问题类型：</span>
                        <input type="radio" name="question_type" value="网站改进" checked="checked">
                        <span class="radio_font">网站改进</span>
                        <input type="radio" name="question_type" value="课程学习">
                        <span class="radio_font">课程学习</span>
                        <input type="radio" name="question_type" value="课件播放">
                        <span class="radio_font">课件播放</span>
                        <input type="radio" name="question_type" value="其他意见">
                        <span class="radio_font">其他意见</span>
                        <br/>
                    </div>
                    <div class="ge">
                        <span>&nbsp;&nbsp;您联系方式（可不填）：</span>
                    </div>
                    <div class="ge">
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;QQ&nbsp;号：</span>
                        <input type="text" name="qq" />
                        <br/>
                    </div>
                    <div class="ge">
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;手机号：</span>
                        <input type="text" name="phone" maxlength="11" />
                        <br/>
                    </div>
                    <div class="ge">
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;箱：</span>
                        <input type="text" name="email">
                        <br/>
                    </div>
                    <div class="ge">
                        <input type="submit" id="submit" value="提交">
                        <input id="reset" type="reset" value="重置" />
                    </div>
                    <div class="ge">
                        <span>&nbsp;&nbsp;</span>
                    </div>
                </form>
            </div>
        </div>
    </main>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/adviceOur.min.js"></script></body>

</html>