<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<title><decorator:title /></title>
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/spark-global.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/spark-sidemenu.css" />
	
	<script type="text/javascript" src="${contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/spark-side-menu-data.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/spark-side-menu.js"></script>
	
	<script type="text/javascript">
		var contextPath = "${contextPath}";
		var currentMenu = null;

		// 窗口大小改变时触发
		function onWindowResize() {
			var windowWidth = $(window).width();
			var menuWidth = $("#masterPageSideMenu").width();
			$("#masterPageContent").width((windowWidth - menuWidth - 22) + "px").css("display", "block");
		}
		$(window).resize(onWindowResize);
		$(onWindowResize);
		
	</script>
	<decorator:head />
</head>
<body class="no-skin" style="background:white;">

	<!-- =================================================================== -->
	<!--  TOP                                                                -->
	<!-- =================================================================== -->

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="height:48px; overflow:hidden; margin:0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">岳麓科技产业园-系统管理</a>
			</div>
			<p class="navbar-text">&nbsp;</p>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">${LOGIN_USER.username}</a></li>
				<li>
					<a href="${contextPath}/user/logout.aspx">退出</a>
				</li>
			</ul>
		</div><!-- container-fluid --> 
	</div><!-- navbar -->
	

	<!-- =================================================================== -->
	<!--  LEFT                                                               -->
	<!-- =================================================================== -->
	
	<div id="masterPageSideMenu" class="masterPageSideMenu" data-spy="affix" data-offset-top="0">
		<div id="sideMenuNoMenu" style="padding:12px; color:#ff5533; display:none;">
			<div class="text-center">该用户没有配置菜单权限</div>
			<div class="text-center">请联系系统管理员</div>
		</div>
		<div class="side-menu">
			<ul id="sideMenuList" class="nav navbar-nav" style="display:none;">
			</ul>
		</div>
	</div><!-- masterPageSideMenu -->
	
	<script>
		initSideMenu(SideMenuData.data(), currentMenu);
	</script>
	
	<!-- =================================================================== -->
	<!--  CONTENT                                                            -->
	<!-- =================================================================== -->
	
	<div id="masterPageContent" class="masterPageContent" style="display:none;">
		<decorator:body />
	</div><!-- masterPageContent -->

		
	<!-- =================================================================== -->
	<!--  FOOTER                                                             -->
	<!-- =================================================================== -->
	

</body>
</html>
