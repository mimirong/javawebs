<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>调查征集</title>
</head>
<body>
	<div class="ng-cloak" ng-app="surveyListModule"
		ng-controller="surveyListController">
		<h1 class="column_tit">
			您当前位置：<a href="${contextPath}/">首页</a> > <a
				href="${contextPath}/interactive/list">互动交流</a> > <span>调查征集</span>
		</h1>
		<!--content-start-->
		<div class="column_con clearfix" id="minH">
			<div style="margin-left: -29px; width: 214px" class="column_menu">
				<ul class="leftsidebar">
					<li class="menu_tit" style="width: 208px; margin-left: 8px">互动交流管理</li>
					<li style="width: 208px;"><a
						href="${contextPath}/interactive/list">领导信箱</a></li>
					<li class="active" style="width: 208px;"><a
						href="${contextPath}/survey/list">调查征集</a></li>
				</ul>
			</div>
			<div class="column_list">
				<h2 class="title">调查征集</h2>
				<div class="column_tab_con">
					<div class="check-tips">
						<span>欢迎来到调查征集!</span>
						为进一步了解广大用户的实际需求，提供更好的服务，我们特别推出调查征集栏目，欢迎您的积极参与！
					</div>
					<div class="filter-box"></div>
					<table class="column_tab_table">
						<thead>
							<tr>
								<td width="60%">调查主题</td>
								<td width="">调查时间</td>
								<td width="">当前状态</td>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="row in page.data">
								<td ng-if="timeIsBetween(row.itSurvey.startTime,row.itSurvey.endTime) && row.isExpired==false"><a
									class="blue-font" href="toQuestion?surveyId={{row.itSurvey.surveyId}}">{{row.itSurvey.title}}</a>
								</td>
								<td ng-if="!timeIsBetween(row.itSurvey.startTime,row.itSurvey.endTime) || row.isExpired ==true"><span>{{row.itSurvey.title}}</span>
								</td>
								<td>{{row.itSurvey.startTime | date : 'yyyy-MM-dd'}}至{{row.itSurvey.endTime |
									date : 'yyyy-MM-dd'}}</td>
								<td ng-if="row.isExpired==true"><span
									class="grey">已调查</span>
								</td>
								<td ng-if="timeIsBetween(row.itSurvey.startTime,row.itSurvey.endTime) && row.isExpired==false"><a
									class="blue-font" href="toQuestion?surveyId={{row.itSurvey.surveyId}}">调查中...</a>
								</td>
								<td ng-if="!timeIsBetween(row.itSurvey.startTime,row.itSurvey.endTime) && row.isExpired==false"><span
									class="red">已过期</span></td>
							</tr>
						</tbody>
					</table>
					<div class="page_v1" style="text-align: center;">
						<span class="total">共<em>{{page.pageCount}}</em>页
						</span> <span class="ell" ng-if="pageButtons[0] != 1">...</span>
						<ng-repeat ng-repeat="p in pageButtons"> <a
							target="_self" href="javascript:;" ng-if="p!=page.page"
							ng-click="gotoPage(p)">{{p}}</a> <span class="cur"
							ng-if="p==page.page">{{p}}</span> </ng-repeat>
						<span class="ell"
							ng-if="pageButtons[pageButtons.length - 1] != page.pageCount">...</span>
						<a target="_self" href="javascript:;"
							ng-click="gotoPage(page.hasNext ? page.page+1 : page.page)">下一页</a><span>到<input
							type="text" name="jump_url" ng-model="directGoto"
							ng-keydown="onPageKeydown($event)" />页
						</span><a href="javascript:;" class="pages-goto"
							ng-click="directGotoPage()">跳转</a>
					</div>
				</div>
			</div>
		</div>
		<!--content-end-->
	</div>
	<script>
		
	</script>
	<script
		src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script
		src="${contextPath}/static/js/survey/survey_list.js?v=${buildTimestamp}"></script>
</body>
</html>