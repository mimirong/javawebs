<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form action="${data.webUrl}/login/VerifyLogin.jsp" id="form1" name="form1" method="post">
		<input type="hidden" name="loginfile" value="/wui/theme/ecology8/page/login.jsp?templateId=3&logintype=1&gopage=" />
		<input type="hidden" name="logintype" value="1" />
		<input type="hidden" name="formmethod" value="post" />
		<input type="hidden" name="islanguid" value="7" />
		<input type="hidden" name="loginid" value="${data.loginName}" />
		<input type="hidden" name="userpassword" value="${data.password}" />
		<input type="hidden" name="rnd" value="" />
		<input type="hidden" name="serial" value="" />
		<input type="hidden" name="username" value="" />
	</form>
	<script>
		document.getElementById("form1").submit();
	</script>
</body>
</html>