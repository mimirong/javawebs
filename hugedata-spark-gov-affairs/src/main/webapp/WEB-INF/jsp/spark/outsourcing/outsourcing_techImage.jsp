<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

	<h1 class="column_tit">您当前位置：
		<a href="${contextPath}/">首页</a> > 
		<a href="${contextPath}/outsourcing/index">服务外包</a> >  
		<a href="${contextPath}/outsourcing/techIndex">公共技术服务</a> >
		<span>园区影像</span>
	</h1>
	
	<!--content-start-->
	<div class="column_con clearfix" id="minH">
	    <h2 class="title">园区影像 </h2>
	    
	    <c:if test="${image != null}">
		    <div style="text-align:center;">
		    	<img src="${fileUrl}" style="max-width:950px" alt="" />
		    	<p style="margin-top:12px; font-size:16px;">${image.title}</p>
		    	<p style="margin-top:12px;">${image.brief}</p>
		    </div>
	    </c:if>
	    
	    <c:if test="${image == null}">
		    <div style="text-align:center;">
	    		<p style="margin-top:12px; font-size:16px;">图片不存在</p>
	    	</div>
	    </c:if>
	</div>
	<!--content-end-->

</body>
</html>