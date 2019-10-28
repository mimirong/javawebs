<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>领导信箱</title>
</head>
<body>
	<div class="ng-cloak" ng-app="letterDetailModule"
		ng-controller="letterDetailController">
		<h1 class="column_tit">
			您当前位置：<a href="${contextPath}/">首页</a> > <a
				href="${contextPath}/interactive/list">互动交流</a> > <a
				href="${contextPath}/interactive/list">领导信箱</a> > <span> 领导信箱详情</span>
		</h1>
		<!--content-start-->
		<div class="column_con clearfix" id="minH"
			style="padding: 0; width: 1000px;">
			<div class="column_menu">
				<ul>
					<li class="menu_tit">互动交流</li>
					<li class="active"><a href="${contextPath}/interactive/list">领导信箱</a></li>
					<li><a href="${contextPath}/survey/list">调查征集</a></li>
				</ul>
			</div>
			<div class="column_list">
				<h2 class="title">
					领导信箱-我要写信<a href="javascript:history.go(-1)" class="return-btn">返回
						&gt;&gt;</a>
				</h2>
				<div class="show">
					<ul class="clearfix">
						<li><span class="wh" title="{{messageDetail.title}}"><label>主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>{{messageDetail.title}}</span></li>
						<li><label>信件编号：</label>{{messageDetail.messageCode}}</li>
						<li><label>来&nbsp;信&nbsp;人：</label>{{messageDetail.name}}</li>
						<li><label>来信时间：</label>{{messageDetail.submitTime | date : 'yyyy-MM-dd'}}</li>
					</ul>
					<div class="row">
						<h4>来信内容：</h4>
						<p>{{messageDetail.content}}</p>
					</div>
					<div class="row">
						<h4>{{messageDetail.replierCompany}}回复：</h4>
						<p>{{messageDetail.replyContent}}</p>
						<div class="time">{{messageDetail.replyTime | date : 'yyyy-MM-dd'}}</div>
					</div>
				</div>
			</div>
		</div>
		<!--content-end-->
	</div>
	<script>
		var messageId = ${messageId}
	</script>
	<script
		src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script
		src="${contextPath}/static/js/interactive/letter_detail.js?v=${buildTimestamp}"></script>
</body>
</html>