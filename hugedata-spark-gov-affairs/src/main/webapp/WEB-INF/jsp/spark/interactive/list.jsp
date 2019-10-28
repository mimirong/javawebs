<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>领导信箱</title>
</head>
<body>
	<div class="ng-cloak" ng-app="interactiveListModule"
		ng-controller="interactiveListController">
		<h1 class="column_tit">
			您当前位置：<a href="${contextPath}/">首页</a> > <a
				href="${contextPath}/interactive/list">互动交流</a> > <span>领导信箱</span>
		</h1>
		<!--content-start-->
		<div class="column_con clearfix" id="minH">
			<div style="margin-left: -29px; width: 214px" class="column_menu">
				<ul class="leftsidebar">
					<li class="menu_tit" style="width: 208px; margin-left: 8px">互动交流管理</li>
					<li class="active" style="width: 208px;"><a
						href="${contextPath}/interactive/list">领导信箱</a></li>
					<li style="width: 208px;"><a href="${contextPath}/survey/list">调查征集</a></li>
				</ul>
			</div>
			<div class="column_list">
				<h2 class="title">
					领导信箱-互动答复查询<a href="writeLetter" class="letter">我要写信</a>
				</h2>
				<div class="column_tab_con">
					<div class="filter-box">
							<label class="tit">查询密码：</label><input
							ng-model="password" type="password" class="txt"  ng-keydown="onPasswordKeydown($event)"><a href="" class="btn" ng-click="loadData()">查询</a>
					</div>
					<table class="column_tab_table">
						<thead>
							<tr>
								<td>信件标题</td>
								<td>提交时间</td>
								<td>回复时间</td>
								<td>回复单位</td>
							</tr>
						</thead>
						<tbody>
							<tr  ng-repeat="row in page.data">
							<!-- 带密码的查询是可以点击查看详细的-->
								<td ng-if="row.isDeleted==true" ><span class="wh" style="width:170px;"title="{{row.title}}"><a class="blue-font" href="letterDetail?messageId={{row.messageId}}">{{row.title}}</a></span>
								</td>
								<td ng-if="row.isDeleted==false" >
									<span class="wh"style="width:170px;"title="{{row.title}}">{{row.title}}</span>
								</td>
								<td>{{row.submitTime | date : 'yyyy-MM-dd hh:mm:ss'}}</td>
								<td>{{row.replyTime  | date : 'yyyy-MM-dd hh:mm:ss'}}</td>
								<td>{{row.replierCompany}}</td>
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
		src="${contextPath}/static/js/interactive/interactive_list.js?v=${buildTimestamp}"></script>
</body>
</html>