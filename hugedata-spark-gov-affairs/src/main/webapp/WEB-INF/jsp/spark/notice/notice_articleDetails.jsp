<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>
		#content p { line-height:2em; margin-top:1.5em; text-indent:2em; }
		#content b, #content strong { font-weight:bold; }
		#content table { border-top:solid 1px #888; border-left:solid 1px #888; }
		#content table td { border-bottom:solid 1px #888; border-right:solid 1px #888; padding:4px 8px; }
		#content ul { list-style:disc; padding-left:24px; margin-top:12px; } 
		#content ol { list-style:decimal; padding-left:24px; margin-top:12px; } 
		#content a { color:#0e78ee; } 
		#content em { font-style:italic; }
	</style>
</head>
<body>
<div class="ng-cloak" ng-app="articleDetailsModule" ng-controller="articleDetailsController">
    <h1 class="column_tit txgg-tit">您当前位置：
	  	<a href="${contextPath}/">首页</a> >
        <a href="${contextPath}/notice/index">通知公告</a> >
	  	<a href="${contextPath}/notice/articleList?category={{categoryId}}">{{categoryName}}</a> >
	  	<span style="width:100px;">文章详情</span>
    </h1>
    
    <div class="column_con txgg-con clearfix" id="minH">
        <h2 class="title">文章详情 <a href="javascript:history.go(-1);" class="return-btn">返回 >></a></h2>
        <div class="article">
            <h2 class="tit" style="line-height: normal; max-width:850px; word-wrap: break-word">{{article.title}}<span class="time">{{article.publishTime | date:"yyyy-MM-dd"}}</span></h2>
            <%--<p ng-repeat="img in images">
            	<img ng-src="{{img.url}}" alt="" style="max-width:850px; max-height:350px;" />
            </p>--%>
            <div id="content" ng-bind-html="article.content" style="line-height: normal; max-width:850px; word-wrap: break-word">
            	<!-- content -->
            </div>
            
            <p style="margin-top:12px;" ng-if="{{attachments && attachments.length > 0}}">附件：</p>
            <p ng-repeat="att in attachments" style="padding-left:12px;">
            	<a ng-href="{{att.url}}" style="color:#107aee">{{att.fileName}}</a>
            </p>
        </div>
    </div>
    
</div>
<script>
    window.categoryMap = {
        "NOTICE_PICTURE_NEWS"   :   "图片新闻",
        "NOTICE_NOTICE"         :   "通知公告"
    };
	window.articleId = "${articleId}";
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/articleDetails.js?v=${buildTimestamp}"></script>
</body>
</html>