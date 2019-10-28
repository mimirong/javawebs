<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="decorator" content="main2low">
	<title>办事事项</title>
	<script type="text/javascript">
		function jumpPage() {
			var p = document.getElementById("inputPage").value;
			p = parseInt(p);
			if (isNaN(p)) {
				MU.msgTips("warn", "请输入需要跳转的页码");
				return;
			}
			var start = (p - 1) * ${length};
			location.href = "list?dept=${deptCode}&start=" + start;
		}
	</script>
</head>
<body>
  <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <a href="${contextPath}/serviceGuide/list">办事服务</a> > <a href="${contextPath}/serviceGuide/list">办事事项</a> > <span>经济发展局</span></h1>
  <!--content-start-->
  <div class="column_con clearfix">
    <div style="margin-left:-29px;width:214px"class="column_menu">
      <ul>
        <li class="menu_tit">办事事项</li>
        <li class="${deptCode == '7' ? 'active' : ''}"><a href="list?dept=7">办公室</a></li>
        <li class="${deptCode == '8' ? 'active' : ''}"><a href="list?dept=8">党群纪检绩效办</a></li>
        <li class="${deptCode == '1' ? 'active' : ''}"><a href="list?dept=1">经济发展局</a></li>
        <li class="${deptCode == '5' ? 'active' : ''}"><a href="list?dept=5">招商合作局</a></li>
        <li class="${deptCode == '3' ? 'active' : ''}"><a href="list?dept=3">工程建设局</a></li>
        <li class="${deptCode == '4' ? 'active' : ''}"><a href="list?dept=4">社会事务局</a></li>
        <li class="${deptCode == '6' ? 'active' : ''}"><a href="list?dept=6">财政分局</a></li>
        <li class="${deptCode == '2' ? 'active' : ''}"><a href="list?dept=2">国土规划局</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">${deptName}-办事事项</h2>
        <ul class="reports_ul">
        	<c:forEach items="${data.data}" var="row">
	            <li>
	            	<span class="disc"></span>
	            	<c:if test="${fn:length(row.guideName) > 42}">
            			<a href="details?guideId=${row.guideId}" title="${row.guideName}">${fn:substring(row.guideName,0,42)}...</a>
	            	</c:if>
	            	<c:if test="${fn:length(row.guideName) <= 42}">
	            		<a href="details?guideId=${row.guideId}">${row.guideName}</a>
	            	</c:if>
	            	
	            	<span><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd" /></span>
	           	</li>
           	</c:forEach>
        </ul>
        
		<div class="page_v1" style="text-align:center;">
			<span class="total">共<em>${data.pageCount}</em>页</span>
			
			<c:if test="${data.page-2 > 1}">
        		<span class="ell">...</span>
			</c:if>
			
			<c:forEach var="p" begin="${data.page-2 > 1 ? data.page-2 : 1}" 
					end="${data.page+2 < data.pageCount ? data.page+2 : data.pageCount}">
        		<c:if test="${p!=data.page}"><a target="_self" href="list?dept=${deptCode}&start=${(p-1)*length}">${p}</a></c:if>
				<c:if test="${p==data.page}"><span class="cur">${p}</span></c:if>
			</c:forEach>
			
			<c:if test="${data.page+2 < data.pageCount}">
        		<span class="ell">...</span>
			</c:if>
			
			<c:if test="${start+length<data.recordsTotal}">
	        	<a target="_self" href="list?dept=${deptCode}&start=${start+length}">下一页</a>
	        </c:if>
			<c:if test="${start+length>=data.recordsTotal}">
	        	<a target="_self" href="javascript:;">下一页</a>
	        </c:if>
	        
	        <span>到<input type="text" name="jump_url" id="inputPage"  />页</span>
	        <a href="javascript:;" class="pages-goto" onclick="jumpPage()">跳转</a>
		</div>
        
    </div>
  </div>
  <!--content-end-->
</body>
</html>