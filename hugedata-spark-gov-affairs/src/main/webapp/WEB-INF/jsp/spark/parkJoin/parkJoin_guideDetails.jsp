<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>
		#content p { line-height:2em; margin-top:1.5em; text-indent:2em; }
		#content b, #content strong { font-weight:bold; }
		#content table { border-top:solid 1px #888; border-left:solid 1px #888; }
		#content table td { border-bottom:solid 1px #888; border-right:solid 1px #888; padding:4px 8px; }
		#content ul { list-style:disc; padding-left:24px; margin-top:12px; } 
		#content ol { list-style:decimal; padding-left:24px; margin-top:12px; } 
		#content a { color:#0e78ee; } 
		#content em { font-style:italic; }
	</style>
</head>
<body>

    <h1 class="column_tit">您当前位置：
        <a href="${contextPath}/">首页</a> >
        <a href="${contextPath}/integrated/index">综合服务</a> >
		<a href="${contextPath}/parkJoin/listGuide">政务服务</a> >
        <a href="${contextPath}/parkJoin/listGuide">入园服务</a> >
        <span>入园指南</span>
    </h1>
  
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
        <li class="menu_tit">政务服务</li>
        <li class="active">
        	<a href="javascript:;" class="drop">入园服务</a>
            <dl style="display: block">
                <dd class="on"><a href="${contextPath}/parkJoin/listGuide">入园指南</a></dd>
                <dd><a href="${contextPath}/parkJoin/apply">入园申请</a></dd>
            </dl>
        </li>
        <li>
        	<a href="javascript:;" class="drop">退园服务</a>
            <dl>
                <dd><a href="${contextPath}/parkQuit/listGuide">退园指南</a></dd>
                <dd><a href="${contextPath}/parkQuit/apply"> 退园申请</a></dd>
            </dl>
        </li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_SITE">场地服务</a></li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_FEES">费用服务</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">入园指南<span class="tips"></span></h2>
        <h3 class="tab-tit" style="text-align:center;">${guide.title}</h3>
        <span style="text-align:center;display:block;font-size:12px;color:#999;line-height:25px;">${publishTimeStr}</span>
        <div id="content">
        	${guide.content}
        </div>
        <c:if test="${guide.attachmentFileId != null && guide.attachmentFileId != ''}">
        	<p style="margin-top:20px; margin-bottom:12px;">附件：</p>
        	<p style="padding-left:12px;"><a href="${attachmentUrl}" target="_blank" style="color:#107aee">${guide.attachmentFileName}</a></p>
        </c:if>
       	<div style="height:20px;"></div>
    </div>
  </div>
  <!--content-end-->

</body>
</html>