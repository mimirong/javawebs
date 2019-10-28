<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="decorator" content="main2">
</head>
<body>

<div class="ng-cloak" ng-app="articleListModule" ng-controller="articleListController">
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
    
  	<span>{{categoryName}}</span>
  </h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
		  <c:if test="${menuId == '201'}">
			  <li class="menu_tit">检验检测服务</li>
			  <li class="{{categoryId == 'OS_DETEC_ACHIEVE_EXHIBIT' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('OS_DETEC_ACHIEVE_EXHIBIT')">成果展示</a></li>
			  <li class="{{categoryId == 'OS_DETEC_RESOURCE_PLATFORM' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('OS_DETEC_RESOURCE_PLATFORM')">资源平台</a></li>
			  <li class="{{categoryId == 'OS_DETEC_TECH_TRANSFER' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('OS_DETEC_TECH_TRANSFER')">政策法规</a></li>
			  <li class="{{categoryId == 'OS_DETEC_TECH_TRAIN' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('OS_DETEC_TECH_TRAIN')">技术培训</a></li>
		  </c:if>
      	<c:if test="${menuId == '202'}">
	        <li class="menu_tit">公共技术服务</li>
	        <li class="categoryItem categoryItem_HUMAN_RESOURCE">
	            <a class="drop">人力资源服务</a>
	            <dl>
	                <dd class="{{categoryId == 'HUMAN_RESOURCE_1' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('HUMAN_RESOURCE_1')">人才培训</a></dd>
	                <dd class="{{categoryId == 'HUMAN_RESOURCE_2' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('HUMAN_RESOURCE_2')">技术职称招考</a></dd>
	                <dd class="{{categoryId == 'HUMAN_RESOURCE_3' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('HUMAN_RESOURCE_3')">企业招聘</a></dd>
	            </dl>
	        </li>
	        <li class="categoryItem categoryItem_INFORMATION">
	            <a class="drop">企业信息服务</a>
	            <dl>
		            <dd class="{{categoryId == 'INFORMATION_COMPANIES' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('INFORMATION_COMPANIES')">企业信息库</a></dd>
		            <dd class="{{categoryId == 'INFORMATION_PRODUCTS' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('INFORMATION_PRODUCTS')">产品信息库</a></dd>
	            </dl>
	        </li>
	        <li class="categoryItem categoryItem_OSINVEST">
	            <a class="drop">项目招商服务</a>
	            <dl>
		            <dd class="{{categoryId == 'OSINVEST_1' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('OSINVEST_1')">招商信息</a></dd>
		            <dd class="{{categoryId == 'OSINVEST_2' ? 'on' : ''}}"><a href="javascript:;" ng-click="changeCategory('OSINVEST_2')">资源展示</a></dd>
	            </dl>
	        </li>
        </c:if>
      	<c:if test="${menuId == '203'}">
	        <li class="menu_tit">电子商务服务</li>
	        <li class="{{categoryId == 'ECOMM_BIDDINGS' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('ECOMM_BIDDINGS')">招标公告</a></li>
	        <li class="{{categoryId == 'ECOMM_PROJECTS' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('ECOMM_PROJECTS')">项目对接</a></li>
	        <li class="{{categoryId == 'ECOMM_OUTSOURCING' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('ECOMM_OUTSOURCING')">服务外包</a></li>
	        <li class="{{categoryId == 'ECOMM_FINANCING' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('ECOMM_FINANCING')">金融服务</a></li>
      	</c:if>
      	<c:if test="${menuId == '901'}">
	        <li class="menu_tit">政务服务</li>
	        <li>
	        	<a href="javascript:;" class="drop">入园服务</a>
	            <dl>
	                <dd><a href="${contextPath}/parkJoin/listGuide">入园指南</a></dd>
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
	        <li class="{{categoryId == 'GA_SITE' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('GA_SITE')">场地服务</a></li>
	        <li class="{{categoryId == 'GA_FEES' ? 'active' : ''}}"><a href="javascript:;" ng-click="changeCategory('GA_FEES')">费用服务</a></li>
      	</c:if>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">{{categoryName}}</h2>
        <ul class="reports_ul">
            <li ng-repeat="row in page.data">
            	<span class="disc"></span>
            	<a class="w630" ng-href="details?articleId={{row.articleId}}&categoryId={{row.categoryId}}" title="{{row.title}}">{{row.title | limit:42}}</a>
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
	window.categoryId = "${category}";
	
	// 显示左侧菜单
	$(".categoryItem").removeClass("active");
	$(".categoryItem a").removeClass("down");
	$(".categoryItem dl").hide();
	
	if (window.categoryId.indexOf("HUMAN_RESOURCE_") == 0) {
		$(".categoryItem_HUMAN_RESOURCE").addClass("active");
		$(".categoryItem_HUMAN_RESOURCE a").addClass("down");
		$(".categoryItem_HUMAN_RESOURCE dl").show();
		
	} else if (window.categoryId.indexOf("INFORMATION_") == 0) {
		$(".categoryItem_INFORMATION").addClass("active");
		$(".categoryItem_INFORMATION a").addClass("down");
		$(".categoryItem_INFORMATION dl").show();
		
	} else if (window.categoryId.indexOf("OSINVEST_") == 0) {
		$(".categoryItem_OSINVEST").addClass("active");
		$(".categoryItem_OSINVEST a").addClass("down");
		$(".categoryItem_OSINVEST dl").show();
	}
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/articleList.js?v=${buildTimestamp}"></script>

</body>
</html>