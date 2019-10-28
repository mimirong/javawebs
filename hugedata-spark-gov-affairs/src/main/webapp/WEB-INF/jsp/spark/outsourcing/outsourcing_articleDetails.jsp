<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="decorator" content="main2">
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
    <h1 class="column_tit">您当前位置：
	  	<a href="${contextPath}/">首页</a> > 
	  	
	    <c:if test="${menuId != '901'}">
		  	<a href="${contextPath}/outsourcing/index">服务外包</a> >
			<c:if test="${menuId == '201'}">
				<a href="${contextPath}/outsourcing/detectIndex">检验检测服务</a> >
			</c:if>
			<c:if test="${menuId == '202'}">
				<a href="${contextPath}/outsourcing/techIndex">公共技术服务</a> >
			</c:if>
			<c:if test="${menuId == '203'}">
				<a href="${contextPath}/outsourcing/ecommIndex">电子商务服务</a> >
			</c:if>
		</c:if>
		  
	    <c:if test="${menuId == '901'}">
			<a href="${contextPath}/integrated/index">综合服务</a> >
			<a href="${contextPath}/parkJoin/listGuide">政务服务</a> >
	    </c:if>
	  	
	  	<a href="${contextPath}/outsourcing/articleList?category={{categoryId}}">{{categoryName}}</a> >
	  	<span style="width:100px;">文章详情</span>
    </h1>
    
    <div class="column_con clearfix" id="minH">
        <h2 class="title">文章详情 <a href="javascript:history.go(-1);" class="return-btn">返回 >></a>
            <c:if test="${categoryId == 'OS_DETEC_RESOURCE_PLATFORM' || categoryId == 'OS_DETEC_TECH_TRANSFER'}">
                <a href="javascript:;" class="btn op-btn right_float op-apply btnAdd" title="信息填写" style="height: 30px;line-height: 30px;margin-right: 20px;padding: 0 12px;">我要申请</a>
            </c:if>
        </h2>
        <div class="article">
            <h2 class="tit" style="line-height: normal; max-width:850px; word-wrap: break-word">{{article.title}}<span class="time">{{article.publishTime | date:"yyyy-MM-dd"}}</span></h2>
            <p ng-repeat="img in images">
            	<img ng-src="{{img.url}}" alt="" style="max-width:850px; max-height:350px;" />
            </p>
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

<%--  填写申请信息  --%>
<div class="alert" style="display:none; width:625px;" id="addPanel" name="wh_alert">
    <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
    <div class="pop-content" style="padding: 30px 0;">
        <ul class="form clearfix">
            <li class="clearfix"><label class="tit">公司名称：</label><input id="companyName" name="companyName" type="text" class="text w300" value=""/></li>
            <li class="clearfix"><label class="tit">联系人：</label><input id="contactName" name="contactName" type="text" class="text w300" value=""/></li>
            <li class="clearfix"><label class="tit">手机号码：</label><input id="contact" name="contact" type="text" class="text w300" value=""/></li>
            <li class="clearfix"><label class="tit">其他说明：</label><textarea id="comment" name="comment"  type="text" class="text w300" value="" style=" width: 284px;">限字200</textarea></li>
            <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn" id="btnDoAdd">提交申请</a><a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey">取消</a></li>
        </ul>
    </div>
</div>

<script>
	window.categoryMap = {
        "OS_DETEC_ACHIEVE_EXHIBIT":	"成果展示",
        "OS_DETEC_RESOURCE_PLATFORM":"资源平台",
        "OS_DETEC_TECH_TRANSFER":	"政策法规",
        "OS_DETEC_TECH_TRAIN":		"技术培训",
		"HUMAN_RESOURCE_1": 		"人才培训",
		"HUMAN_RESOURCE_2": 		"技术职称招考",
		"HUMAN_RESOURCE_3": 		"企业招聘",
		"INFORMATION_COMPANIES":	"企业信息库",
		"INFORMATION_PRODUCTS": 	"产品信息库",
		"OSINVEST_1": 				"招商信息",
		"OSINVEST_2": 				"资源展示",
		"ECOMM_BIDDINGS": 			"招标公告",
		"ECOMM_PROJECTS": 			"项目对接",
		"ECOMM_OUTSOURCING": 		"服务外包",
		"ECOMM_FINANCING": 			"金融服务",
        "GA_SITE": 					"场地服务",
        "GA_FEES": 					"费用服务"
	};
	window.articleId = "${articleId}";
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/articleDetails.js?v=${buildTimestamp}"></script>
</body>
</html>