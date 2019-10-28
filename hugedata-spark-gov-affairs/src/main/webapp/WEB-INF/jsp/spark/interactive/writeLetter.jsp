<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>领导信箱</title>
</head>
<body>
	<div class="ng-cloak" ng-app="writeLetterModule"
		ng-controller="writeLetterController">
		<h1 class="column_tit">
			您当前位置：<a href="${contextPath}/">首页</a> > <a
				href="${contextPath}/interactive/list">互动交流</a> > <a
				href="${contextPath}/interactive/list">领导信箱</a> > <span>我要写信</span>
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
					领导信箱-我要写信<a href="javascript:history.go(-1)" class="return-btn">返回 &gt;&gt;</a>
				</h2>
				<ul class="form clearfix" style="margin-top: 40px;">
					<li class="clearfix"><label class="tit"><em>*</em>编号：</label><input
						disabled="true" id="messageCode" type="text" class="text w322" value="" /></li>
					<li class="clearfix"><label class="tit"><em>*</em>主题：</label><input
						id = "title" type="text" class="text w322" value="" maxlength="200"/></li>
					<li class="clearfix"><label class="tit"><em>*</em>信件类别：</label>
						<span  class="select" style="width: 340px;"> <select id="messageType">
								<option value='0'>求助</option>
								<option value='1'>投诉</option>
								<option value='2'>咨询</option>
						</select>
					</span></li>
					<li class="clearfix"><label class="tit">内容：</label>
					<textarea  id="content"  name="" cols="30" rows="10" style="width: 322px;"></textarea></li>
					<li class="clearfix"><label class="tit">&nbsp;</label><a
						href="javascript:void(0)" class="btn" ng-click="submitLetter()">提交</a><a
						href="javascript:history.go(-1);" class="btn btn-grey">取消</a></li>
				</ul>
			</div>
		</div>
		<!--content-end-->
	</div>
	<script>
		var messageCode = "${messageCode}";
		$("#messageCode").val(messageCode) ;
	</script>
	<script
		src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script
		src="${contextPath}/static/js/interactive/writeLetter.js?v=${buildTimestamp}"></script>
</body>
</html>