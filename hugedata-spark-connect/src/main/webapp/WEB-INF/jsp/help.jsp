<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cn.com.hugedata.spark.connect.HandlerMapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setAttribute("methodList", HandlerMapper.listAllHandlerMethods());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Help</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js?v=${buildTimestamp}"></script>
	<style>
		h1 { font:normal 20px Arial; }
		.form-row { clear:both; padding:10px; }
		.form-row label { display:block; float:left; width:80px; }
		.form-row input { float:left; width:200px; padding:4px 8px; }
		.form-row textarea { float:left; width:300px; height:300px; padding:4px 8px; }
		.form-row select { float:left; width:300px; padding:4px 8px; }
		.form-row button { float:left; padding:4px 16px; }
	</style>
</head>
<body>

	<h1>SPARK CONNECT HELPER</h1>
	
	<div>
		<div class="form-row">
			<label>Method:</label>
			<select id="method">
				<c:forEach var="m" items="${methodList}">
					<c:if test="${m == 'user/login2'}">
						<option selected="selected" value="${m}">${m}</option>
					</c:if>
					<c:if test="${m != 'user/login2'}">
						<option value="${m}">${m}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="form-row">
			<label>Login:</label>
			<input type="text" id="login" />
		</div>
		<div class="form-row">
			<label>Request:</label>
			<textarea id="request" style="width:400px;">{
    "loginName":"admin",
    "password":"e6210be8e3bf597749dc2734d4f2889c"
}</textarea>
		</div>
		<div class="form-row">
			<label>&nbsp;</label>
			<button class="btnCall">CALL</button>
		</div>
	</div>
	
	<script>
		(function() {
			
			$(".btnCall").on("click", function() {
				var url = "${pageContext.request.contextPath}/connect?method=";
				url += encodeURIComponent($("#method").val());
				url += "&data=";
				url += encodeURIComponent($("#request").val());
				url += "&session=";
				url += encodeURIComponent($("#login").val());
				window.open(url);
			});
			
		})();
	</script>

</body>
</html>
