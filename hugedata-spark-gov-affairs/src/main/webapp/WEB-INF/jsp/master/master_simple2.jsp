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
	<decorator:body />
</body>
</html>