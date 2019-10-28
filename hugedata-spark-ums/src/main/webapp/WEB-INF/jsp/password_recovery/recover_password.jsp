<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
	<meta name="decorator" content="simple">
	<title>忘记密码</title>
</head>
<body>
<!--banner-start-->
<div class="banner-container">
  <a href="${govAffairsPortalUrl}/" class="logo1"><img src="${contextPath}/static/web2/public/images/logo1.png" alt="岳麓科技产业园"/></a>
    <div class="res-menu">
        <a href="${govAffairsPortalUrl}/">首页</a>
        <a href="${govAffairsPortalUrl}/serviceGuide1/list">办事服务</a>
        <a href="${govAffairsPortalUrl}/outsourcing/index">服务外包</a>
        <a href="${govAffairsPortalUrl}/pmProject/list">项目管理</a>
        <a href="${govAffairsPortalUrl}/notice/index">通知公告</a>
        <a href="${govAffairsPortalUrl}/handyService/index">便民服务</a>
        <a href="${govAffairsPortalUrl}/interactive/list">互动交流</a>
    </div>
  <div class="right_float"> <a href="${contextPath}/login">登录</a></div>
</div>

<!--banner-end-->
  <!--content-start-->
<div class="res-content clearfix password-content" style="margin-top:60px;margin-bottom: 60px;">
    <div class="signup-cont" style="border-right: none;width: auto;margin-left: 220px;">
        <h2>找回密码</h2>
        <div class="row">
        	<label class="title"><i>*</i>联系邮箱：</label>
        	<input type="text" value="" id="email" class="txt w270" placeholder="请输入注册时的邮箱" id="email"/>
        	<em id="emailError"></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>验证码：</label>
	        <input type="text" value="" id="code" class="txt w168" placeholder="请输入邮箱收到的验证码" maxlength="6"/>
	        <a class="send-code" href="javascript:;" id="btnSendCode">发送验证码</a>
	        <a class="send-code" href="javascript:;" id="btnSendCodeDone" style="display:none;">已发送</a>
	        <em id="codeError"></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>新密码：</label>
	        <input type="password" value="" id="password" class="txt w270" placeholder="请输入新密码" />
	        <em id="passwordError"></em>
        </div>
        <div class="row">
        	<label class="title"><i>*</i>确认新密码：</label>
	        <input type="password" value="" id="password2" class="txt w270" placeholder="请再次输入新密码" />
	        <em id="password2Error"></em>
        </div>
        <div class="row">
	        <label class="title">&nbsp;</label>
	        <input type="text" value="" id="imgCode" class="txt w168" placeholder="请输入右侧验证码"/>
	        <img src="viewCode" class="code" alt="" style="width:81px;height:31px;" id="codeImage"/>
	        <em id="imgCodeError"></em>
        </div>
       <div class="btn-div res-div">
	       <a class="btn" href="javascript:;" id="btnSubmit">提交</a>
	       <a class="btn btn-grey" href="${contextPath}/login">取消</a>
       </div>
    </div>
<!--<div class="restip">建议使用IE8以上浏览器（不含IE8）浏览网站<a class="close-btn"></a></div>-->
</div>
  <!--content-end-->

<div class="login_footer">
    <div class="cen">
        <div class="login_footer_top">
            <ul class="left_float">
                <li>
                    <h1>友情链接</h1>
                    <p><a href="http://www.hnxjxq.gov.cn/">湘江新区</a></p>
                    <p><a href="http://www.changsha.gov.cn/">中国长沙</a></p>
                    <p><a href="http://www.yuelu.gov.cn/">中国岳麓</a></p>
                    <p><a href="http://www.hunan.gov.cn/">湖南省政府网</a></p>
                </li>
            </ul>
            <img src="${contextPath}/static/web2/public/images/code1.png" style="margin-right: 105px;" />
            <%--
            <img src="${contextPath}/static/web2/public/images/code2.png" />
            --%>
            <ul class="right_float">
                <li>
                    <p>联系电话：0731-88532200</p>
                    <p>版权所有：长沙市岳麓科技产业园</p>
                    <p>备案号：湘ICP备10202297号-1</p>
                    <p>技术支持：湖南网数科技有限公司</a></p>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="foot">
    <div class="cen">
        建议使用IE8以上浏览器（不含IE8）浏览网站, 技术支持：湖南网数科技有限公司
    </div>
</div>
	
<script type="text/javascript" src="${contextPath}/static/js/passwordRecovery.js?v=${buildTimestamp}"></script>

</body>
</html>