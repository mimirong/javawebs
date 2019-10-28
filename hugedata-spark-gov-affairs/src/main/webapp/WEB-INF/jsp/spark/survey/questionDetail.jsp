<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>调查征集</title>
</head>
<body>
	<div class="ng-cloak" ng-app="questionDetailModule"
		ng-controller="questionDetailController">
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
					<li class="active"  style="width: 208px;"><a href="${contextPath}/survey/list">调查征集</a></li>
				</ul>
			</div>
			<div class="column_list">
				<h2 class="title">
					调查征集-详情<a href="javascript:history.go(-1);" class="return-btn">返回 &gt;&gt;</a>
				</h2>
				<div class="question-block">
					<div class="con_time">
						<span>进行中...</span>{{itSurvey.startTime | date:'MM.dd'}}-{{itSurvey.endTime | date:'MM.dd'}}
					</div>
					<p class="con_title">{{itSurvey.title}}</p>
					<p class="con_publishtxt">{{itSurvey.startTime | date:'yyyy-MM-dd'}}</p>
					<p class="top">
					</p>
					 <dl class="list">
		            	<div ng-repeat="q in questionList" class="questionItem" data-id="{{q.questionId}}">
		            		<div ng-if="q.questionType == '0'">
				                <dt><label class="tit"><em ng-if="q.isRequired">*</em><em ng-if="!q.isRequired"></em>{{$index + 1}}、</label>{{q.questionText}} <span style="color:red;" class="errorMessage">{{q.errorMessage}}</span></dt>
				                <dd>
				                <span class="checkbox_area">
				                    <label data-id="{{op.optionCode}}" class="checkbox radio questionOption" ng-repeat="op in q.itSurveyOptionList"
				                    		ng-click="clearError(q.questionId)">{{op.optionText}}</label>
				                </span>
				                </dd>
			                </div>
			                <div ng-if="q.questionType == '1'">
								<dt><label class="tit"><em ng-if="q.isRequired">*</em><em ng-if="!q.isRequired"></em>{{$index + 1}}、</label>{{q.questionText}} <span style="color:red;" class="errorMessage">{{q.errorMessage}}</span></dt>
								<dd><textarea name="" id="" cols="30" rows="10" ng-click="clearError(q.questionId)"></textarea></dd>
							</div>
							<div ng-if="q.questionType == '2'">
				                <dt><label class="tit"><em ng-if="q.isRequired">*</em><em ng-if="!q.isRequired"></em>{{$index + 1}}、</label>{{q.questionText}} <span style="color:red;" class="errorMessage">{{q.errorMessage}}</span></dt>
				                <dd>
					                <span class="checkbox_area">
					                    <label data-id="{{op.optionCode}}" class="checkbox questionOptionMulti"
					                    		ng-repeat="op in q.itSurveyOptionList"
					                    		ng-click="clearError(q.questionId)">{{op.optionText}}</label>
					                </span>
				                </dd>
			                </div>
		                </div>
           			</dl>
					<div class="btn-box">
						<a href="" ng-click="submit()"class="btn">投票</a><a href="javascript:history.go(-1);" class="btn btn-grey">返回</a>
					</div>
			</div>
		</div>
		<!--content-end-->
	</div>
	<script>
	var surveyId = ${surveyId}
	</script>
	<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
	<script src="${contextPath}/static/js/survey/question_detail.js?v=${buildTimestamp}"></script>
</body>
</html>