<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	
	<title><decorator:title /></title>
	
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/public.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/selectList.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/jquery.cookie.js?v=${buildTimestamp}"></script>
	<script type="text/javascript">
		var contextPath = "${contextPath}";
		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var outsourcingPortalUrl = "${outsourcingPortalUrl}";
		var detectionPortalUrl = "${detectionPortalUrl}";
		var ecommercePortalUrl = "${ecommercePortalUrl}";
		var managementUrl = "${managementUrl}";
	</script>
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
	
	<decorator:head />
</head>
<body>
	<decorator:body />
</body>
</html>
