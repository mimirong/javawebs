<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
    <title>政务服务</title>
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
	
	<script type="text/javascript" src="${contextPath}/static/web/public/js/jquery.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/public.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/common.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/selectList.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/dateFormat.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/date.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/datepicker.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web/public/js/datepicker-zh-CN.js?v=${buildTimestamp}"></script>
	
	<script>
		var contextPath = "${contextPath}";
		var downloadServiceUrl = "${downloadServiceUrl}";
		var umsUrl = "${umsUrl}";
		var managementUrl = "${managementUrl}";
		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var outsourcingPortalUrl = "${outsourcingPortalUrl}";
	</script>
	
	<style>
		.ng-cloak { display:none; }
	</style>
	
	<decorator:head />
</head>
<body>
	<!--banner-start-->
    <div class="banner_bg" style="height:180px;">
        <div class="banner">
          <a href="index.html" class="logo"><img src="${contextPath}/static/web2/public/images/logo.png" alt="岳麓科技产业园"/></a>
          <div class="right_float">
          </div>
        </div>
        <%--
        <div class="menu">
          <div class="cen">
              <ul class="clearfix">
                  <li><a class="tit" href="${contextPath}/">首页</a></li>
                  <li class="on">
                      <a class="tit" href="${contextPath}/serviceGuide/list">办事服务</a>
                      <div class="menu_down">
                          <a class="on" href="${contextPath}/serviceGuide/list">办事事项</a>
                          <a href="#">办事公示</a>
                          <a href="#">在线办理</a>
                      </div>
                  </li>
                  <li><a class="tit" href="#">平台服务</a></li>
                  <li><a class="tit" href="#">项目公示</a></li>
                  <li><a class="tit" href="#">通知公告</a></li>
                  <li><a class="tit" href="#">便民服务</a></li>
                  <li><a class="tit" href="#">互动交流</a></li>
              </ul>
          </div>
        </div>
        --%>
    </div>
	<!--banner-end-->
	
	<script>
		$(".loginLink").attr("href", "${umsUrl}/login?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
		$(".logoutLink").attr("href", "${umsUrl}/logout?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
	</script>
	
	<!--content-start-->
	<decorator:body />
	<!--content-end-->

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
                <img src="${contextPath}/static/web2/public/images/code1.png" style="margin-right: 105px;">
                <img src="${contextPath}/static/web2/public/images/code2.png">
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


</body>
</html>