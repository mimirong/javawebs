<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办事事项</title>
</head>
<body>
	<div class="ng-cloak" ng-app="serviceGuideDetailsModule"
		ng-controller="serviceGuideDetailsController">
		<h1 class="column_tit">
			您当前位置： <a href="${contextPath}/">首页</a> > <a
				href="${contextPath}/serviceGuide1/list">办事服务</a> > <a
				href="${contextPath}/serviceGuide1/list">办事事项</a> > <a
				href="list?dept={{guide.deptCode}}">{{guide.deptName}}</a> > <span>{{guide.guideName}}</span>
		</h1>
		<!--content-start-->
		<div class="column_con clearfix"
			style="padding: 0 20px 35px; width: 960px;">
			<h2 class="title">{{guide.guideName}}</h2>
			 <div class="tab" style="width:347px">
				<a id="tabId_0" href="details?guideId=${guideId}" class="on">办事指南</a><a
					href="details2?guideId=${guideId}">办事流程</a><a
					href="details3?guideId=${guideId}">表格下载</a><a
					href="onlineTransact?guideId=${guideId}">在线办理</a>
			</div>
			<div class="tab-content">
				<h3 class="title" style="border-bottom: none; margin-bottom: 0;">办事指南</h3>
				<table class="table">
					<tr>
						<td class="tit">事项名称</td>
						<td colspan="3">{{guide.guideName}}</td>
					</tr>
					<tr>
						<td class="tit">事项类型</td>
						<td colspan="3">{{guide.guideType}}</td>
					</tr>
					<tr>
						<td class="tit">办理对象</td>
						<td colspan="3">{{guide.serviceSubject}}</td>
					</tr>
					<tr>
						<td class="tit">设立依据</td>
						<td colspan="3">{{guide.according}}</td>
					</tr>
					<tr>
						<td class="tit">前置条件</td>
						<td width="340">{{guide.precondition}}</td>
						<td class="tit">联合办理单位</td>
						<td>{{guide.jointDept}}</td>
					</tr>
					<tr>
						<td class="tit">法定期限</td>
						<td>{{guide.legalTimeLimit}}</td>
						<td class="tit">承诺期限</td>
						<td>{{guide.promisedTimeLimit}}</td>
					</tr>
					<tr>
						<td class="tit">办理条件</td>
						<td colspan="3" ng-bind-html="guide.conditions"></td>
					</tr>
					<tr>
						<td class="tit">申请材料</td>
						<td colspan="3" ng-bind-html="guide.material"></td>
					</tr>
					<tr>
						<td class="tit">办事程序</td>
						<td colspan="3" ng-bind-html="guide.process"></td>
					</tr>
					<tr>
						<td class="tit">是否收费</td>
						<td>{{guide.isCharge ? "是" : "否"}}</td>
						<td class="tit">收费依据</td>
						<td>{{guide.chargeAccording}}</td>
					</tr>
					<tr>
						<td class="tit">收费标准</td>
						<td colspan="3">{{guide.chargeStandard}}</td>
					</tr>
					<tr>
						<td class="tit">办理地点</td>
						<td colspan="3">{{guide.address}}</td>
					</tr>
					<tr>
						<td class="tit">办理时间</td>
						<td colspan="3">{{guide.workTime}}</td>
					</tr>
					<tr>
						<td class="tit">咨询电话</td>
						<td>{{guide.telephone}}</td>
						<td class="tit">投诉电话</td>
						<td>{{guide.complaintTelephone}}</td>
					</tr>
				</table>
			</div>
		</div>
		<!--content-end-->
	</div>

	<script>
		window.guideData = ${data};
	</script>
	<script
		src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script
		src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
	<script
		src="${contextPath}/static/js/serviceGuide/serviceGuide_details.js?v=${buildTimestamp}"></script>

</body>
</html>