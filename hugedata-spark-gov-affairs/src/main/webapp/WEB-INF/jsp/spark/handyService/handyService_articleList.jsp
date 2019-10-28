<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div class="ng-cloak" ng-app="articleListModule" ng-controller="articleListController">
    <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> >
        <a href="${contextPath}/handyService/index">便民服务</a> >
        <c:choose>
            <c:when test="${category == 'HANDY_SERVICE_RESIDENCE'}">
                <span>园区小区</span>
            </c:when>
            <c:when test="${category == 'HANDY_SERVICE_SCHOOL'}">
                <span>园区学校</span>
            </c:when>
            <c:when test="${category == 'HANDY_SERVICE_HOSPITAL'}">
                <span>园区医院</span>
            </c:when>
            <c:when test="${category == 'HANDY_SERVICE_BUS'}">
                <span>园区公交</span>
            </c:when>
            <c:when test="${category == 'HANDY_SERVICE_BANK'}">
                <span>园区银行</span>
            </c:when>
            <c:otherwise>
                <span>园区小区</span>
            </c:otherwise>
        </c:choose>
    </h1>
    <!--content-start-->
    <div class="column_con clearfix" id="minH">
        <h2 class="title">{{categoryName}}</h2>
        <ul class="news-list">
            <li ng-repeat="row in page.data">
                <a ng-if="row.coverFileId" ng-href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" class="fl">
                    <img ng-src="{{row.imgSrc}}" alt=""/>
                </a>
                <div ng-if="row.coverFileId" class="fr">
                    <h2><a ng-href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" class="wh" title="{{row.title}}">{{row.title | limit:40}}</a></h2>
                    <p>{{categoryName}}介绍：<br/>{{row.content | limit:180}}</p>
                </div>

                <div  ng-if="!row.coverFileId" class="fr" style="width: 100%">
                    <h2><a ng-href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" class="wh" title="{{row.title}}">{{row.title | limit:53}}</a></h2>
                    <p>{{categoryName}}介绍：<br/>{{row.content | limit:200}}</p>
                </div>
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
    <!--content-end-->
</div>

    <script>
        window.categoryMap = {
            "HANDY_SERVICE_RESIDENCE"   :   "园区小区",
            "HANDY_SERVICE_SCHOOL"      :   "园区学校",
            "HANDY_SERVICE_HOSPITAL"    :   "园区医院",
            "HANDY_SERVICE_BUS"         :   "园区公交"
        };
        window.categoryId = "${category}";
        window.isNeedContent = true;
    </script>
    <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/articleList.js?v=${buildTimestamp}"></script>

</body>
</html>
