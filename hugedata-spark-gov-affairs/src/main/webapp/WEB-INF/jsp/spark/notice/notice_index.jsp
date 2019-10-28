<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
    <h1 class="column_tit txgg-tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>通知公告</span></h1>

    <!--content-start-->
    <div class="column_con txgg-con clearfix ng-cloak"  ng-app="noticeIndexModule" ng-controller="noticeIndexController" id="minH">
        <div id="play" ng-show="bannerImages.length > 0">
            <div id="play_text">
                <ul>
                    <li ng-repeat="img in bannerImages">{{$index + 1}}</li>
                </ul>
            </div>
            <div id="play_list">
            	<a ng-href="{{img.linkUrl}}" target="_blank" ng-repeat="img in bannerImages" style="background-color:white;">
            		<img alt="" title="" ng-src="{{img.previewUrl}}" style="width:960px; height:120px;" />
           		</a>
            </div>
        </div>
        <div class="notice-block clearfix">
            <div id="recommend">
                <div id="recommend_info"></div>
                <div id="recommend_text">
                    <ul>
                        <li ng-repeat="row in pictureNewsIndex">{{row.index}}</li>
                    </ul>
                </div>
                <div id="recommend_list">
                    <a ng-repeat="row in pictureNewsData" href="details?articleId={{row.articleId}}&categoryId=NOTICE_PICTURE_NEWS">
                        <img ng-if="row.coverFileId" alt="{{row.title}}" title="" ng-src="{{row.imgSrc}}"/>
                        <img ng-if="!row.coverFileId" alt="{{row.title}}" title="" ng-src=""/>
                    </a>
                </div>
            </div>
            <div class="notice-list">
                <h2 class="tit"><span>通知公告</span><a href="${contextPath}/notice/articleList" class="more">查看更多 >></a></h2>
                <ul class="reports_ul" ng-repeat="row in noticeData">
                    <li>
                        <span class="disc"></span>
                        <a ng-href="details?articleId={{row.articleId}}&categoryId=NOTICE_NOTICE" title="{{row.title}}">{{row.title}}</a>
                        <span>{{row.publishTime | date:'yyyy-MM-dd'}}</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--content-end-->

    <script type="text/javascript">

    </script>
    <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/notice/notice_index.js?v=${buildTimestamp}"></script>

</body>
</html>
