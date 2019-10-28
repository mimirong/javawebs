<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit">
	<title>岳麓科技产业园-内部办公系统</title>
	
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
	<decorator:body />
</body>
</html>
