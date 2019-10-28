<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办事事项</title>
</head>

<body>
<div class="ng-cloak" ng-app="serviceGuideDetailsModule" ng-controller="serviceGuideDetailsController">
    <h1 class="column_tit">您当前位置：
    	<a href="${contextPath}/">首页</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事服务</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事事项</a> > 
    	<a href="list?dept={{guide.deptCode}}">{{guide.deptName}}</a> > 
    	<span>{{guide.guideName}}</span>
   	</h1>
  <!--content-start-->
  <div class="column_con clearfix" style="padding: 0 20px 35px; width: 960px;">
      <h2 class="title">{{guide.guideName}}</h2>
     <div class="tab" style="width:347px">
				<a href="details?guideId=${guideId}">办事指南</a><a
					href="details2?guideId=${guideId}" class="on">办事流程</a><a
					href="details3?guideId=${guideId}">表格下载</a><a
					href="onlineTransact?guideId=${guideId}">在线办理</a>
			</div>
      <div class="tab-content">
          <h3 class="title">办事流程</h3>
          <div class="content">
          	
          	  <div ng-if="!guide.flowImageFileId || guide.flowImageFileId == ''" style="height:200px; text-align:center;">
          	  	  <p>没有数据</p>
          	  </div>
          	  
              <img ng-src="{{guide.flowImageFileId | makeUrl:guide.flowImageFileName}}" alt=""
              		ng-if="guide.flowImageFileId != null && guide.flowImageFileId != ''" style="max-width:850px" />
          </div>
      </div>
  </div>
  <!--content-end-->

</div>

<script>
window.guideData = ${data};
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/serviceGuide/serviceGuide_details.js?v=${buildTimestamp}"></script>

</body>
</html>