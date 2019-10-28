<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="webkit">
	<meta name="decorator" content="simple"> 
</head>
<body>
	<script>
		$("body").css("overflow", "hidden");
	</script>

	<div class="logo-index">
	    <img src="${contextPath}/static/web2/public/images/logo_home.png" alt="" class="fl"/><span class="fl">内部办公系统</span>
   	    <c:if test="${SPARK_LOGIN.userType == 'SYSTEM'}">
    		<a href="${contextPath}/switchSystem?systemType=ACCOUNTS" class="blue op-acc">账号管理</a>
    	</c:if>
   	    <ul class="clearfix fr" id="userChoice">
	        <li class="item">欢迎您，
	            <a href="#">${SPARK_LOGIN.name}</a>
	            <div class="user-info">
	                <a href="${contextPath}/modifyPwd">修改密码</a>
	                <a href="javascript:;" class="last logoutLink">退出</a>
	            </div>
	        </li>
	    </ul>
	</div>
	<script>
		$(".logoutLink").attr("href", "${contextPath}/login/logout?redirect=" + encodeURIComponent("${managementUrl}"));
	</script>
	
	<div class="slideImg">
	    <div class="pageImg img2 z1"><img src="${contextPath}/static/web2/public/images/bg.png" />
	    </div>
	</div>
	
	<div style="clear:both;"></div>
	<div class="index-block" style="clear:both;">
	    <h2>请选择您要进入的办公系统</h2>
	    <ul class="clearfix">
	        <li><a href="${oaWebUrl}"><img src="${contextPath}/static/web2/public/images/home1.png" alt=""/>OA办公</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=APPROVAL"><img src="${contextPath}/static/web2/public/images/home2.png" alt=""/>行政审批</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=PM"><img src="${contextPath}/static/web2/public/images/home3.png" alt=""/>项目管理</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=OUTSOURCING"><img src="${contextPath}/static/web2/public/images/home4.png" alt=""/>服务外包</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=INTEGRATED"><img src="${contextPath}/static/web2/public/images/home7.png" alt=""/>综合服务</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=IT"><img src="${contextPath}/static/web2/public/images/home5.png" alt=""/>互动交流</a></li>
	        <li><a href="${contextPath}/switchSystem?systemType=INFORMATION"><img src="${contextPath}/static/web2/public/images/home6.png" alt=""/>信息发布</a></li>
	    </ul>
	</div>
	<div class="index-bottom" style="position:absolute;width:100%;bottom:0px;">
	<!--footer-->
	    <div class="index-footer">
	        <div class="clearfix" style="min-width: 1220px;">
	        <div class="footer-left">
	            <h1 class="footer-tit">友情链接</h1>
	            <ul class="clearfix">
	                <li><a href="http://www.hnxjxq.gov.cn/" target="_blank" style="color: #cdcdcd;">湘江新区</a></li>
	                <li><a href="http://www.changsha.gov.cn/" target="_blank" style="color: #cdcdcd;">中国长沙</a></li>
	                <li><a href="http://www.yuelu.gov.cn/" target="_blank" style="color: #cdcdcd;">中国岳麓</a></li>
	                <li><a href="http://www.hunan.gov.cn/" target="_blank" style="color: #cdcdcd;">湖南省政府网</a></li>
	            </ul>
	        </div>
	        <div class="left_float" style="width: 320px;height: 95px;margin-top:20px;">
		        <img src="${contextPath}/static/web2/public/images/wechat.PNG" alt="" style="margin-right: 100px;"/>
		        <%--
		        <img src="${contextPath}/static/web2/public/images/weibo.PNG" alt=""/>
		        --%>
	        </div>
	        <div class="footer-right">
	            <ul>
	                <li style="margin: 28px 0 16px 0;">电话：0731-88532200&nbsp;&nbsp;&nbsp;&nbsp;版权所有：长沙市岳麓科技产业园&nbsp;&nbsp;&nbsp;&nbsp;备案号：湘ICP备10202297号-1</li>
	                <li>建议使用IE8以上浏览器（不含IE8）浏览网站，技术支持：湖南网数科技有限公司</li>
	            </ul>
	        </div>
	        </div>
	    </div>
	</div>
	<!--footer结束-->

</body>