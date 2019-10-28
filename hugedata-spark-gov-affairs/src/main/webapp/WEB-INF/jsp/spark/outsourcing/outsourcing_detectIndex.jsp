<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <a href="${contextPath}/outsourcing/index">服务外包</a> >  <span>检验检测服务</span></h1>

<!--content-start-->
<div class="column_con clearfix ng-cloak" id="minH" ng-app="outsourcingDetectIndexModule" ng-controller="outsourcingDetectIndexController">
    <h2 class="title">检验检测服务 </h2>
    <div class="">
        <div class="block left_float mr30">
            <h3 class="title">检验检测成果展示<a href="${contextPath}/outsourcing/articleList?category=OS_DETEC_ACHIEVE_EXHIBIT" class="more">查看更多 &gt;&gt;</a></h3>
            <ul class="list clearfix">
                <li ng-repeat="row in achieveExhibitList">
                    <span class="dot"></span>
                    <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                    <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                </li>
            </ul>
        </div>
        <div class="block left_float">
            <h3 class="title">检验检测资源平台<a href="${contextPath}/outsourcing/articleList?category=OS_DETEC_RESOURCE_PLATFORM" class="more">查看更多 &gt;&gt;</a></h3>
            <ul class="list clearfix">
                <li ng-repeat="row in resourcePlatformList">
                    <span class="dot"></span>
                    <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                    <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                </li>
            </ul>
        </div>
        <div class="block left_float mr30">
            <h3 class="title">检验检测政策法规<a href="${contextPath}/outsourcing/articleList?category=OS_DETEC_TECH_TRANSFER" class="more">查看更多 &gt;&gt;</a></h3>
            <ul class="list clearfix">
                <li ng-repeat="row in techTransferList">
                    <span class="dot"></span>
                    <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                    <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                </li>
            </ul>
        </div>
        <div class="block left_float">
            <h3 class="title">检验检测技术培训<a href="${contextPath}/outsourcing/articleList?category=OS_DETEC_TECH_TRAIN" class="more">查看更多 &gt;&gt;</a></h3>
            <ul class="list clearfix">
                <li ng-repeat="row in techTrainList">
                    <span class="dot"></span>
                    <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                    <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--content-end-->

<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_detectIndex.js?v=${buildTimestamp}"></script>

</body>
</html>
