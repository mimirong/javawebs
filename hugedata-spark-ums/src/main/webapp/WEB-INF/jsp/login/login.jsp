<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
	<meta name="decorator" content="simple">
	<title>登录</title>
	<script>
		if (window.top != window) {
			window.top.location.href = window.location.href;
		}
	</script>
</head>
<body>
<script>
	$("body").css({ "overflow":"hidden", "background-color":"#0e274d" });
</script>

<input type="hidden" name="redirectUrl" id="redirectUrl" value="${redirect}" />

<div class="logo-index">
    <a href="${govAffairsPortalUrl}/"><img src="${contextPath}/static/web2/public/images/logo-index.PNG" alt=""/></a>
    <div class="logo-index-right">
        <a class="" href="${contextPath}/signup/agreement">注册</a>
    </div>
</div>

<div class="slideImg">
    <div class="pageImg img2 z1"><img src="${contextPath}/static/web2/public/images/indexBg2-1.png" />
    </div>
</div>
<div class="login-container">
    <div class="login-top">
        <div class="login-logo">登录</div>
        <div class="login-cont">
        <div class="login-cont">
            <p><input type="text" value="" id="username" name="username" class="txt" placeholder="用户名"></p>
            <p><input type="password" value="" id="password" name="password" class="txt" placeholder="密码"></p>
            <p><input type="text" value="" id="imgCode" name="imgCode" class="txt" placeholder="验证码" style="width: 142px;"><a href="javascript:;" class="right_float code-img imgCodeUpdate" style="border:1px solid #b6b6b6;"><img src="${contextPath}/login/viewLoginCode" alt="" style="width:98px; height:38px;" /></a> </p>
					<span class="checkbox_area clearfix">
						<label id="chkSaveLoginName" class="checkbox" style="color:white">记住用户名</label>
						<a href="${contextPath}/passwordRecovery/">找回密码</a>
					</span>
            <a href="javascript:;" class="login-btn" id="submitLoginInProg" style="display:none;">正在登录...</a>
            <a href="javascript:;" class="login-btn" id="submitLogin">登&nbsp;录</a>
            <div class="tips">还没有账号？&nbsp;&nbsp;<a href="${contextPath}/signup/agreement" class="res">立即注册</a></div>
        </div>
    </div>
</div>

<div class="index-bottom" style="position:fixed;width:100%;bottom:0px;">
<!--footer-->
    <div class="index-footer">
        <div class="clearfix" style="min-width: 1220px;">
        <div class="footer-left">
            <h1 class="footer-tit">友情链接</h1>
            <ul class="clearfix">
                <li style="cursor:pointer;" onclick="window.open('http://www.hnxjxq.gov.cn/')">湘江新区</li>
                <li style="cursor:pointer;" onclick="window.open('http://www.yuelu.gov.cn/')">中国岳麓</li>
                <li style="cursor:pointer;" onclick="window.open('http://www.changsha.gov.cn/')">中国长沙</li>
                <li style="cursor:pointer;" onclick="window.open('http://www.hunan.gov.cn/')">湖南省政府网</li>
            </ul>
        </div>
        <div class="left_float" style="margin-top:10px;">
	        <img src="${contextPath}/static/web2/public/images/wechat.PNG" alt="" style="margin-right: 100px;"/>
	        <!--
	        <img src="${contextPath}/static/web2/public/images/weibo.PNG" alt=""/>
	        -->
        </div>
        <div class="footer-right">
            <ul>
                <li style="margin:30px 0 12px 0;">电话：0731-88532200&nbsp;&nbsp;&nbsp;&nbsp;版权所有：长沙市岳麓科技产业园&nbsp;&nbsp;&nbsp;&nbsp;备案号：湘ICP备10202297号-1</li>
                <li>建议使用IE8以上浏览器（不含IE8）浏览网站，技术支持：湖南网数科技有限公司</li>
            </ul>
        </div>
        </div>
    </div>
</div>
<!--footer结束-->

<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/js/login.js?v=${buildTimestamp}"></script>
</body>
</html>

