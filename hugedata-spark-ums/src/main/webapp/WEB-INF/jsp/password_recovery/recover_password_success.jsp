<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
<meta name="decorator" content="simple">
<title>找回密码成功</title>
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
      <div class="success-block">
          <h2 class="title">恭喜您，密码修改成功，您可以用新密码进行登录！</h2>
          <a href="${contextPath}/login" class="btn">马上登录</a>
      </div>
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
</body>
</html>