<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div class="ng-cloak" ng-app="outsourcingEcommIndexModule" ng-controller="outsourcingEcommIndexController">
  <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <a href="${contextPath}/outsourcing/index">服务外包</a> >  <span>电子商务服务</span></h1>
  
  <!--content-start-->
  <div class="column_con clearfix" id="minH">
      <h2 class="title">电子商务服务 </h2>
      <div class="">
          <div class="block left_float mr30">
              <h3 class="title"> 招标公告<a href="${contextPath}/outsourcing/articleList?category=ECOMM_BIDDINGS" class="more">查看更多 &gt;&gt;</a></h3>
              <ul class="list clearfix">
                  <li ng-repeat="row in biddingsList">
                  	  <span class="dot"></span>
                  	  <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                  	  <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                  </li>
              </ul>
          </div>
          <div class="block left_float">
              <h3 class="title">项目对接<a href="${contextPath}/outsourcing/articleList?category=ECOMM_PROJECTS" class="more">查看更多 &gt;&gt;</a></h3>
              <ul class="list clearfix">
                  <li ng-repeat="row in projectsList">
                  	  <span class="dot"></span>
                  	  <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                  	  <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                  </li>
              </ul>
          </div>
          <div class="block left_float mr30">
              <h3 class="title">服务外包 <a href="${contextPath}/outsourcing/articleList?category=ECOMM_OUTSOURCING" class="more">查看更多 &gt;&gt;</a></h3>
              <ul class="list clearfix">
                  <li ng-repeat="row in outsourcingList">
                  	  <span class="dot"></span>
                  	  <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                  	  <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                  </li>
              </ul>
          </div>
          <div class="block left_float">
              <h3 class="title">金融服务 <a href="${contextPath}/outsourcing/articleList?category=ECOMM_FINANCING" class="more">查看更多 &gt;&gt;</a></h3>
              <ul class="list clearfix">
                  <li ng-repeat="row in financingList">
                  	  <span class="dot"></span>
                  	  <a href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title}}</a>
                  	  <em class="date">{{row.publishTime | date:'MM.dd'}}</em>
                  </li>
              </ul>
          </div>
      </div>
  </div>
  <!--content-end-->
</div>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_ecommIndex.js?v=${buildTimestamp}"></script>

</body>
</html>