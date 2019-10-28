<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="renderer" content="webkit">
	<meta name="decorator" content="simple"> 
</head>
<body>

	<div style="margin-top:200px; text-align:center; font-size:28px;">
		没有权限使用这个功能
	</div>
	<div style="margin-top:12px; text-align:center;">
		<button type="button" class="btn btn-primary btn-md btnReLogin">重新登录</button>
		<button type="button" class="btn btn-primary btn-md btnGoHome">返回首页</button>
	</div>
	
	<script>
		$(".btnGoHome").on("click", function() {
			location.href = "${govAffairsPortalUrl}";
		});
		$(".btnReLogin").on("click", function() {
			location.href = "${contextPath}/login/?forceLogin=true&redirect=" + encodeURIComponent("${managementUrl}");
		});
	</script>

</body>