<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit">
	<meta name="decorator" content="simple">
	<title>登录</title>
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
	<script>
		if (window.top != window) {
			window.top.location.href = window.location.href;
		}

		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var managementUrl = "${managementUrl}";
	</script>
</head>
<body style="overflow: hidden">

<input type="hidden" name="redirectUrl" id="redirectUrl" value="${redirectUrl}" />

<div class="page login-page">
	<div class="login-header wrapper clearfix">
		<a href="javascript:;"><img src="${contextPath}/static/web2/public/images/login-logo.png" width="294" height="50" title="岳麓科技产业园" /></a>
	</div>
	<div class="container clearfix">
		<div class="wrapper">
			<div class="login-top">
				<div class="login-logo"><a class="on" id="userTypePark">普通账号登录</a><a id="userTypeAdmin">管理员登录</a></div>
				<div class="login-cont">
					<p><input type="text" value="" id="username" name="username" class="txt" placeholder="用户名" /></p>
					<p><input type="password" value="" id="password" name="password" class="txt" placeholder="密码" /></p>
					<p><input type="text" value="" id="imgCode" name="imgCode" class="txt" placeholder="验证码" style="width: 146px;"/><a href="javascript:;" class="fr code imgCodeUpdate"><img src="${contextPath}/login/viewLoginCode" alt=""/></a> </p>
					<span class="checkbox_area clearfix">
						<label id="chkSaveLoginName" class="checkbox" style="color: #fff;">记住用户名</label>
						<%-- 
						<a href="#">忘记密码？</a>
						--%>
					</span>
					<a href="javascript:;" class="login-btn" id="submitLogin">登录</a>
					<a href="javascript:;" class="login-btn" id="submitLoginInProg" style="display:none">正在登录...</a>
				</div>
			</div>
		</div>
	</div>
    <div class="login_footer">
        <div class="cen">
            <div class="login_footer_top">
                <ul class="left_float">
                    <li>
                        <h1>友情链接</h1>
                        <p><a href="http://www.hnxjxq.gov.cn/" target="_blank">湘江新区</a></p>
                        <p><a href="http://www.changsha.gov.cn/" target="_blank">中国长沙</a></p>
                        <p><a href="http://www.yuelu.gov.cn/" target="_blank">中国岳麓</a></p>
                        <p><a href="http://www.hunan.gov.cn/" target="_blank">湖南省政府网</a></p>
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
                        <p>技术支持：湖南网数科技有限公司</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/js/spark/login.js?v=${buildTimestamp}"></script>
<script>
    $(function(){
        $(".login-page").css("padding-top",($(window).height()-$(".login-page").height())/2);
    });
</script>
</body>
</html>

