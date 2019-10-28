<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>

<title>个人中心</title>
</head>
<body>
<div class="column_list ng-cloak" ng-app="messageListModule" ng-controller="messageListController">
	<h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>个人中心</span></h1>
	
	<!--content-start-->
	<div class="column_con clearfix" id="minH">
	    <h2 class="title">个人中心</h2>
	    <div class="tab"><a href="${contextPath}/user/userCenter">注册信息</a><a class="on" href="${contextPath}/messages/list">消息通知<span id="unreadMessageIndicator" class="red-dot" style="display:none"></span></a></div>
		<div class="news_list column_tab_con">
		    <table class="column_tab_table">
		        <thead>
		        <tr>
		            <td>序号</td>
		            <td>通知时间</td>
		            <td>通知事项</td>
		            <td>通知内容</td>
		            <td>状态</td>
		            <td>操作</td>
		        </tr>
		        </thead>
		        <tbody>
		            <tr ng-repeat="row in page.data">
		                <td>{{row.serialNum}}</td>
		                <td>{{row.sendTime | date:'yyyy-MM-dd'}}</td>
		                <td>{{row.messageType | messageTypeText}}</td>
		                <td>
			                <span  ng-if=" row.messageType == 'PARK_JOIN_APPLY' || row.messageType == 'PARK_QUIT_APPLY' " class="blue-font">{{row.title}}</span>
			                <a ng-if=" row.messageType == 'PROJECT_MANAGE' || row.messageType == 'AP_SERVICE' " class="" href="javascript:;" ng-click="viewMessage(row,$event)"><span class="blue-font">{{row.title}}</span></a>
		                </td>
		                <td>
		                	<span ng-if=" row.messageType == 'PARK_JOIN_APPLY' || row.messageType == 'PARK_QUIT_APPLY' " class="grey" >--</span>
		                	<span ng-if=" row.messageType != 'PARK_JOIN_APPLY' && row.messageType != 'PARK_QUIT_APPLY'  && !row.isRead" class="red-font" >待查看</span>
		                	<span ng-if=" row.messageType != 'PARK_JOIN_APPLY' && row.messageType != 'PARK_QUIT_APPLY' &&  row.isRead " class="grey"  >已查看</span>
		                </td>
		                <td><a class="grey op-del" ng-click="deleteMessage(row.messageId)" href="javascript:;">删除</a></td>
		            </tr>
		        </tbody>
		    </table>
	        
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

</div>

<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/messageList.js?v=${buildTimestamp}"></script>

</body>
</html>