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
	
	<script type="text/javascript" src="${contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		var contextPath = "${contextPath}";
	</script>
	<decorator:head />
</head>
<body class="no-skin" style="background:white;">

	<!-- =================================================================== -->
	<!--  CONTENT                                                            -->
	<!-- =================================================================== -->
	
	<div id="masterPageContent">
		<decorator:body />
	</div><!-- masterPageContent -->


	<script src="${contextPath}/static/js/bootstrap.min.js"></script>
		
</body>
</html>
