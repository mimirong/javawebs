<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit">
	<title><decorator:title /></title>
	
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/css/spark-global.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/css/jquery-ui.min.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/css/jquery-ui.theme.min.css?v=${buildTimestamp}" />
	
	<script type="text/javascript" src="${contextPath}/static/js/jquery.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/bootstrap.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/jquery.dataTables.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/dateFormat.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/jquery.validate.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/jquery.cookie.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/jquery-ui.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/sp.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/public.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/selectList.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/date.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/datepicker-zh-CN.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/moment.js?v=${buildTimestamp}"></script>
	
	<script>
		var contextPath = "${contextPath}";
		var umsUrl = "${umsUrl}";
		var managementUrl = "${managementUrl}";
		var portalUrl = "${govAffairsPortalUrl}";
		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var downloadServiceUrl = "${downloadServiceUrl}";
	</script>
	<script type="text/javascript" src="${contextPath}/static/js/spark.min.js?v=${buildTimestamp}"></script>
	
	<script type="text/javascript">
		var currentMenu = null;
	</script>
	<c:if test="${PrivilegeInfo.privilegeListJson != null}">
		<script type="text/javascript">
			var masterPrivilegeList = ${PrivilegeInfo.privilegeListJson};
			var masterRightList = ${PrivilegeInfo.rightListJson};
			var masterSystemType = "${PrivilegeInfo.systemType}";
			CommonsPrivileges.init(masterPrivilegeList, masterRightList);
		</script>
	</c:if>
	<decorator:head />
</head>
<body>

	<!--banner-start-->
    <div class="banner_bg">
        <div class="banner">
            <a href="${contextPath}/" class="logo"><img src="${contextPath}/static/web2/public/images/logo.png" alt="岳麓科技产业园"/></a>
            <ul class="clearfix fr" id="userChoice">
                <li class="item">
                    <a href="#">${SPARK_LOGIN.name}</a>
                    <div class="user-info">
						<a href="${contextPath}/modifyPwd">修改密码</a>
                        <a href="javascript:;" class="last logoutLink">退出</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="menu">
          <div class="cen">
              <ul class="clearfix">
                  <li class="${PrivilegeInfo.systemType == 'APPROVAL' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=APPROVAL">行政审批</a></li>
                  <li class="${PrivilegeInfo.systemType == 'PM' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=PM">项目管理</a></li>
                  <li class="${PrivilegeInfo.systemType == 'OUTSOURCING' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=OUTSOURCING">服务外包</a></li>
                  <li class="${PrivilegeInfo.systemType == 'INTEGRATED' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=INTEGRATED">综合服务</a></li>
                  <li class="${PrivilegeInfo.systemType == 'IT' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=IT">互动交流</a></li>
                  <li class="${PrivilegeInfo.systemType == 'INFORMATION' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=INFORMATION">信息发布</a></li>
                  <c:if test="${SPARK_LOGIN.userType == 'SYSTEM'}">
                  	  <li class="${PrivilegeInfo.systemType == 'ACCOUNTS' ? 'on' : ''}"><a class="tit" href="${contextPath}/switchSystem?systemType=ACCOUNTS">账号管理</a></li>
                  </c:if>
              </ul>
          </div>
        </div>
    </div>
	<script>
		$(".logoutLink").attr("href", "${contextPath}/login/logout?redirect=" + encodeURIComponent("${managementUrl}"));
	</script>
	<!--banner-end-->


	<!-- location start-->
    <h1 class="column_tit" id="currentLocationWrapper">您当前位置：
		<a href="${context}/">...</a> > <span>...</span>
   	</h1>
	<script>
		$(function() {
			var link = {
				"APPROVAL": { title:"行政审批", url:"${contextPath}/switchSystem?systemType=APPROVAL" },
				"ACCOUNTS": { title:"账号管理", url:"${contextPath}/switchSystem?systemType=ACCOUNTS" },
				"OUTSOURCING": { title: "服务外包", url:"${contextPath}/switchSystem?systemType=OUTSOURCING" },
				"INTEGRATED": { title: "综合服务", url:"${contextPath}/switchSystem?systemType=INTEGRATED" },
                "INFORMATION": {title: "信息发布", url: "${contextPath}/switchSystem?systemType=INFORMATION"},
                "PM": {title: "项目管理", url: "${contextPath}/switchSystem?systemType=PM"},
                "IT": {title: "互动交流", url: "${contextPath}/switchSystem?systemType=IT"}
			}[currentSystem];
			
			if (link) {
				$("#currentLocationWrapper a").html(link.title).attr("href", link.url);
				$("#currentLocationWrapper span").html(document.title);
			}
		});
	</script>
   	<!-- location end -->
    
    
	<!--content-start-->
	<div class="column_con clearfix" id="minH">
		<div class="column_menu leftsidebar">
			<div id="sideMenuNoMenu" style="color:#ff5533; display:none; margin-top:16px; text-align:center; line-height:20px;">
				<div class="text-center">该用户没有配置菜单权限</div>
				<div class="text-center">请联系系统管理员</div>
			</div>
			<ul id="sideMenuList" style="display:none;">
			</ul>
		</div>
		<script>
			$(function() {
				var menuData = ${PrivilegeInfo.menuJson};
				initSideMenu(menuData, currentMenu);
			});
		</script>
		
		<div class="column_list">
			<decorator:body />
		</div>
	</div>
	
	<!-- footer -->
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
						<p>技术支持：湖南网数科技有限公司</a></p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
</body>
</html>
