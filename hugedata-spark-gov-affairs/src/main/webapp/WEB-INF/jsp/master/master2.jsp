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
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/css/jquery-ui.structure.min.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/css/jquery-ui.theme.min.css?v=${buildTimestamp}" />
	
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery-ui.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/public.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/common.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/selectList.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/dateFormat.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/date.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/commons.js?v=${buildTimestamp}"></script>

	<script>
		var contextPath = "${contextPath}";
		var downloadServiceUrl = "${downloadServiceUrl}";
		var umsUrl = "${umsUrl}";
		var managementUrl = "${managementUrl}";
		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var oaWebUrl = "${oaWebUrl}";
	</script>
	
	<style>
		.ng-cloak { display:none; }
	</style>
	
	<decorator:head />
</head>
<body>
	<!--banner-start-->
    <div class="banner_bg">
        <div class="banner">
          <a href="${contextPath}/" class="logo"><img src="${contextPath}/static/web2/public/images/logo.png" alt="岳麓科技产业园"/></a>
          <div class="right_float">
          	<c:if test="${SPARK_LOGIN == null}">
          		<a href="${umsUrl}/login" class="mr10 loginLink">登录</a>|<a href="${umsUrl}/signup/agreement" class="ml10">注册</a>
          	</c:if>
          	<c:if test="${SPARK_LOGIN != null}">
          		欢迎您， ${SPARK_LOGIN.companyName} &nbsp;| 
          		<a href="${contextPath}/user/userCenter" class="ml10">个人中心</a>
          		<a href="javascript:;" class="ml10 logoutLink">退出</a>
        	</c:if>
          </div>
        </div>
        <div class="menu">
          <div class="cen">
          
              <ul class="clearfix">
                  <li><a class="tit" href="${contextPath}/">首页</a></li>
                  <li><a class="tit" href="http://www.cshrss.gov.cn/xxgk/ztzl/csrcxz22/" target="_blank">人才政策</a></li>
                  <li>
                      <a class="tit" href="${contextPath}/serviceGuide1/list">办事服务</a>
                      <div class="menu_down">
                          <a id="menuId_2" href="${contextPath}/serviceGuide1/list">办事事项</a>
                          <a id="menuId_1" href="${contextPath}/serviceGuide1/serviceShow">办事公示</a>
                      </div>
                  </li>
                  <li id="menuId_200">
                  	  <a class="tit" href="${contextPath}/outsourcing/index">服务外包</a>
                  </li>
                  <li><a class="tit" id="menuId_300"  href="${contextPath}/pmProject/list">项目管理</a></li>
                  <li class="on"><a class="tit" id="menuId_900" href="${contextPath}/integrated/index">综合服务</a>
                      <div class="menu_down" style="">
                          <a id="menuId_901" href="${contextPath}/parkJoin/listGuide">政务服务</a>
                          <a id="menuId_905" href="${contextPath}/outsourcing/tech/computing/applyList">IT基础设施服务</a>
                      </div>
                  </li>
                  <li><a id="menuId_400" class="tit" href="${contextPath}/notice/index">通知公告</a></li>
                  <li id="menuId_500">
                      <a class="tit" href="${contextPath}/handyService/index">便民服务</a>
                      <div class="menu_down w460" style="margin-left:-182px">
                          <a id="menuId_501" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_RESIDENCE">园区小区</a>
                          <a id="menuId_502" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_SCHOOL">园区学校</a>
                          <a id="menuId_503" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_HOSPITAL">园区医院</a>
                          <a id="menuId_504" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_BUS">园区公交</a>
                          <a id="menuId_505" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_BANK">园区银行</a>
                      </div>
                  </li>
                  <li><a class="tit" href="${contextPath}/interactive/list">互动交流</a>
                      <div class="menu_down w250">
                          <a id="menuId_701" href="${contextPath}/interactive/list">领导信箱</a>
                          <a id="menuId_702" href="${contextPath}/survey/list">调查征集</a>
                      </div>
                  </li>
              </ul>
          </div>
        </div>
    </div>
	<!--banner-end-->
	
	<script>
		$(".loginLink").attr("href", "${umsUrl}/login?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
		$(".logoutLink").attr("href", "${umsUrl}/logout?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
		var menuId = "${menuId}";
		if (menuId != "") {
			$(".menu_down a").removeClass("on");
			$(".menu ul li").removeClass("on");
			if ($("#menuId_" + menuId).is("li")) {
				$("#menuId_" + menuId).addClass("on");
			} else {
				$("#menuId_" + menuId).addClass("on").parents("li").addClass("on");
			}
		}
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
                <%--
                <img src="${contextPath}/static/web2/public/images/code2.png">
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

</body>
</html>