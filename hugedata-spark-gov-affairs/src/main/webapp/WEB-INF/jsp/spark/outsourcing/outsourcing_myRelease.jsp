<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<body class="service-page">

<div class="cen ng-cloak" id="minServiceH" ng-app="outsourcingMyReleaseModule" ng-controller="outsourcingMyReleaseController">
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/serviceProject">检验检测</a> &gt; <span>我的发布</span></h1>
    <h2 class="se-title se-title-noborder"><span>我的发布</span><a href="${contextPath}/outsourcing/serviceProject" class="more">返回</a></h2>
    <ul class="se-list">
        <li ng-repeat="row in page.data" data-id="{{row.projectId}}">
            <table class="se-table">
                <tr>
                    <td width="140" ng-if="!row.coverFileId"><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
                        <img ng-src="${contextPath}/static/web2/public/images/service/img01.png" alt=""/></a></td>
                    <td width="140" ng-if="row.coverFileId"><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
                        <img ng-src="{{row.imgSrc}}" alt=""/></a></td>
                    <td width="" valign="top">
                        <div class="info">
                            <h2><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">{{row.supplyTheme}}</a></h2>
                            <p>{{row.supplyBrief}}</p>
                            <p>{{row.companyName}}</p>
                            <em ng-if="row.supplyType == 'SP_SUPPLY_TYPE_INSPECT_DETEC'" class="icons"></em>
                            <em ng-if="row.supplyType == 'SP_SUPPLY_TYPE_TECH_SERVICE'" class="icons skill"></em>
                        </div>
                    </td>
                    <td width="130" align="center">
                        <div class="price">
                            价格<span class="red">{{row.price}}</span>
                        </div>
                    </td>
                    <td width="250" align="center">{{row.visitorCount}}人浏览</td>
                    <td width="180">
                        <a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}" class="btn btn-grey">查看详情</a>
                        <a class="se-del ml10" href="${contextPath}/outsourcing/publishSupply?isModify=true&projectId={{row.projectId}}">修改</a>
                        <a class="se-del ml10" href="javascript:;" data-value="{{row.projectId}}"  ng-click="deleteMyRelease($event);">删除</a>
                    </td>
                </tr>
            </table>
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

<div id="goTopBtn" class="go-top-btn"></div>

<script>

</script>
<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_myRelease.js?v=${buildTimestamp}"></script>
</body>
</html>