<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="decorator" content="simple"> 
	<title>登录</title>
	<style>
		.mainPanel { width:300px; height:290px; position:absolute; left:50%; top:50%; margin-left:-150px; margin-top:-160px; }
	</style>
	<script type="text/javascript">
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
</head>
<body>

	<div class="panel panel-primary mainPanel">
		<div class="panel-heading">登录系统管理</div>
		<div class="panel-body text-center">
			<div class="form-group" style="margin-top:12px;">
				<label class="sr-only" for="username">用户名</label>
				<div class="input-group">
					<div class="input-group-addon"><span class="fa fa-user"></span></div>
					<input type="text" class="form-control" id="username" placeholder="用户名">
				</div>
			</div>
			<div class="form-group" style="margin-top:24px;">
				<label class="sr-only" for=""password"">密码</label>
				<div class="input-group">
					<div class="input-group-addon"><span class="fa fa-lock"></span></div>
					<input type="password" class="form-control" id="password" placeholder="密码">
				</div>
			</div>
			<div id="errorMessage" class="text-danger text-left" style="margin-top:15px; padding-left:4px;">&nbsp;</div>
			<div id="loginButtonPanel" style="margin-top:24px;">
				<button type="button" class="btn btn-primary btn-md btnLogin" style="width:120px;">登录</button>
			</div>
			<div id="loginLoadingPanel" style="margin-top:24px; display:none;">
				<img src="${contextPath}/static/images/loading-round.gif" />
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		(function() {
			$(function() {
				$("#username").focus();
			});
			
			// 用户名
			$("#username").on("keydown", function(e) {
				if (e.keyCode == 13) {
					$("#password").focus();
				}
			});
			
			// 密码
			$("#password").on("keydown", function(e) {
				if (e.keyCode == 13) {
					doLogin();
				}
			});

			// 登录
			function doLogin() {
				$("#errorMessage").html("&nbsp;");
				
				// username
				var username = $("#username").val();
				if (username == "") {
					$("#errorMessage").html("请输入用户名");
					return;
				}
				
				// password
				var password = $("#password").val();
				if (password == "") {
					$("#errorMessage").html("请输入密码");
					return;
				}
				
				// 请求服务器
				$("#loginButtonPanel").hide();
				$("#loginLoadingPanel").show();
				$.ajax({
					url: "doLogin.aspx?_t=" + new Date().getTime(),
					type: "post",
					dataType: "json",
					data: {
						username: username,
						password: password
					},
					success: function(resp) {
						if (resp && resp.result == 0) {
							location.href = contextPath + "/";
						} else {
							$("#errorMessage").html(resp.message);
							$("#loginButtonPanel").show();
							$("#loginLoadingPanel").hide();
						}
					},
					error: function() {
						$("#errorMessage").html("登录失败，请稍后重试");
						$("#loginButtonPanel").show();
						$("#loginLoadingPanel").hide();
					}
				});
		}
			$(".btnLogin").on("click", doLogin);

		})();
	</script>

</body>