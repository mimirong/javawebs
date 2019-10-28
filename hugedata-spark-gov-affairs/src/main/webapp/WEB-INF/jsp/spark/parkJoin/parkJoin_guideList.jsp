<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<div class="ng-cloak" ng-app="parkJoinGuideListModule" ng-controller="parkJoinGuideListController">
  <h1 class="column_tit">您当前位置：
  	<a href="${contextPath}/">首页</a> > 
        <a href="${contextPath}/integrated/index">综合服务</a> >
		<a href="${contextPath}/parkJoin/listGuide">政务服务</a> >
        <a href="${contextPath}/parkJoin/listGuide">入园服务</a> >
  	<span>入园指南</span>
  </h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
        <li class="menu_tit">政务服务</li>
        <li class="active">
        	<a href="javascript:;" class="drop">入园服务</a>
            <dl style="display: block">
                <dd class="on"><a href="${contextPath}/parkJoin/listGuide">入园指南</a></dd>
                <dd><a href="${contextPath}/parkJoin/apply">入园申请</a></dd>
            </dl>
        </li>
        <li>
        	<a href="javascript:;" class="drop">退园服务</a>
            <dl>
                <dd><a href="${contextPath}/parkQuit/listGuide">退园指南</a></dd>
                <dd><a href="${contextPath}/parkQuit/apply"> 退园申请</a></dd>
            </dl>
        </li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_SITE">场地服务</a></li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_FEES">费用服务</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">入园指南</h2>
        <ul class="reports_ul">
            <li ng-repeat="row in page.data">
            	<span class="disc"></span>
            	<a class="w630" ng-href="guideDetails?guideId={{row.guideId}}&categoryId={{row.guideId}}">{{row.title | limit:42}}</a>
            	<span>{{row.publishTime | date:'yyyy-MM-dd'}}</span>
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

<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/parkJoin/parkJoin_guideList.js?v=${buildTimestamp}"></script>

</body>
</html>