<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>办事事项</title>
</head>
<body>
<div class="ng-cloak" ng-app="serviceGuideListModule" ng-controller="serviceGuideListController">
  <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <a href="${contextPath}/serviceGuide1/list">办事服务</a> > <a href="${contextPath}/serviceGuide1/list">办事事项</a> > <span>{{deptCode|deptName}}</span></h1>
  <!--content-start-->
  <div class="column_con clearfix">
    <div style="margin-left:-29px;width:214px"class="column_menu">
      <ul>
        <li style=" width: 198px;margin-left:8px"class="menu_tit">办事事项</li>
        <li class="{{deptCode == '7' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('7',$event)">办公室</a></li>
        <li class="{{deptCode == '8' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('8',$event)">党群纪检绩效办</a></li>
        <li class="{{deptCode == '1' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('1',$event)">经济发展局</a></li>
        <li class="{{deptCode == '5' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('5',$event)">招商合作局</a></li>
        <li class="{{deptCode == '3' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('3',$event)">工程建设局</a></li>
        <li class="{{deptCode == '4' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('4',$event)">社会事务局</a></li>
        <li class="{{deptCode == '6' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('6',$event)">财政分局</a></li>
        <li class="{{deptCode == '2' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeDept('2',$event)">国土规划局</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">{{deptCode | deptName}}-办事事项</h2>
        <ul class="reports_ul">
            <li ng-repeat="row in page.data">
            	<span class="disc"></span><a href="details?guideId={{row.guideId}}" title="{{row.guideName}}">{{row.guideName | limit:42}}</a><span>{{row.createTime | date:'yyyy-MM-dd'}}</span>
           	</li>
        </ul>
        
      <div class="page_v1" style="text-align: center;">
        <span class="total">共<em>{{page.pageCount}}</em>页</span>
        <span class="ell" ng-if="pageButtons[0] != 1">...</span>
        <ng-repeat ng-repeat="p in pageButtons">
        	<a target="_self" href="javascript:;" ng-if="p!=page.page" ng-click="gotoPage(p)">{{p}}</a>
        	<span class="cur" ng-if="p==page.page">{{p}}</span>
       	</ng-repeat>
        <span class="ell" ng-if="pageButtons[pageButtons.length - 1] != page.pageCount">...</span>
        <a target="_self" href="javascript:;" ng-click="gotoPage(page.hasNext ? page.page+1 : page.page)">下一页</a><span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span><a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
      </div>
      
    </div>
  </div>
  <!--content-end-->
</div>
<script>
		var deptCode = "${deptCode}";
	</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/serviceGuide/serviceGuide_list.js?v=${buildTimestamp}"></script>
</body>
</html>