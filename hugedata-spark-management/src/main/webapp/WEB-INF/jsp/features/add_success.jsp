<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="renderer" content="webkit">
	<meta name="decorator" content="simple"> 
	<title>新增成功</title>
</head>
<body>

	<div style="text-align:center; margin-top: 30px;">新增成功</div>
	<input type="hidden" id="serviceType" name="serviceType" value="${serviceType}"/>
	
	<script type="text/javascript">
		window.parent.Dialog.hide("addDialog");
		window.parent.reloadList();
	</script>

</body>