<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="decorator" content="main2low">
<title>办事事项</title>
</head>
<body>
    <h1 class="column_tit">您当前位置：
    	<a href="${contextPath}/">首页</a> > 
    	<a href="${contextPath}/serviceGuide/list">办事服务</a> > 
    	<a href="${contextPath}/serviceGuide/list">办事事项</a> > 
    	<a href="list?dept=${guide.deptCode}">${deptName}</a> > 
    	<span>${guide.guideName}}</span>
   	</h1>
  <!--content-start-->
  <div class="column_con clearfix" style="padding: 0 20px 35px; width: 960px;">
      <h2 class="title">${guide.guideName}}</h2>
      <div class="tab"><a href="details?guideId=${guideId}">办事指南</a><a href="details2?guideId=${guideId}">办事流程</a><a href="details3?guideId=${guideId}" class="on">表格下载</a></div>
      <div class="tab-content">
          <h3 class="title">表格下载</h3>
          <div class="content">
              <ul class="list">
              	  <c:forEach var="att" items="${attachments}">
	                  <li><a href="${att.previewFileId}">${att.fileName}</a></li>
                  </c:forEach>
              </ul>
          </div>
      </div>
  </div>
  <!--content-end-->

</body>
</html>