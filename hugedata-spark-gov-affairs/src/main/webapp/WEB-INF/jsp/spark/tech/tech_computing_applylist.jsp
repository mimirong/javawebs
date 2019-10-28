<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="main2">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外包服务</title>
</head>
<body>

<div class="ng-cloak" ng-app="techComputingApplyListModule" ng-controller="techComputingApplyListController">
	<h1 class="column_tit" style="clear:both;">您当前位置：
		<a href="${contextPath}/">首页</a> &gt;  
		<a href="${contextPath}/integrated/index">综合服务</a> &gt;  
		<a href="${contextPath}/outsourcing/tech/computing/applyList">IT基础设施服务</a> &gt; 
		<a href="${contextPath}/outsourcing/tech/computing/applyList">云主机</a> &gt; 
		<span style="width:100px;">申请记录</span>
	</h1>
	
	<!--content-start-->
	<div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
	  <div class="column_menu">
	    <ul class="leftsidebar">
	      <li class="menu_tit">IT基础设施服务</li>
	      <li class="active">
	          <a class="drop down">云主机</a>
	          <dl style="display: block;">
	              <dd class="on"><a href="${contextPath}/outsourcing/tech/computing/applyList">申请记录</a></dd>
	              <dd><a href="${contextPath}/outsourcing/tech/computing/specs">云主机申请</a></dd>
	          </dl>
	      </li>
	      <li>
	          <a class="drop">云存储</a>
	          <dl>
	              <dd><a href="${contextPath}/outsourcing/tech/storage/applyList">申请记录 </a></dd>
	              <dd><a href="${contextPath}/outsourcing/tech/storage/specs">云存储记录</a></dd>
	          </dl>
	      </li>
	    </ul>
	  </div>
	  <div class="column_list">
	      <h2 class="title">云主机申请记录</h2>
	      <table class="column_tab_table">
	          <thead>
		          <tr>
		              <td width="">申请内容</td>
		              <td width="">时长</td>
		              <td width="">数量</td>
		              <td width="">费用总计</td>
		              <td width="">申请日期</td>
		              <td width="">类型</td>
		              <td width="">审批进度</td>
		              <td width="">操作</td>
		          </tr>
	          </thead>
	          <tbody>
		          <tr ng-repeat="row in dataList">
		              <td>CPU：{{row.specs.cpu}}，内存：{{row.specs.memory}}，<br/>硬盘：{{row.specs.disk}}，带宽：{{row.specs.bandwidth}}</td>
		              <td>{{row.serviceDuration}}个月</td>
		              <td>{{row.amount}}</td>
		              <td><span ng-show="row.price > 0">{{row.price}}元</span></td>
		              <td>{{row.createTime | date:'yyyy-MM-dd'}}</td>
		              <td>{{row.specName}}</td>
		              <td>
                          <span class="red-font" ng-if="row.status == 'CREATED'">待审批</span>
                          <span class="grey-font" ng-if="row.status == 'APPROVED'">已审批</span>
                          <span class="red-font" ng-if="row.status == 'REJECTED'">审批不通过</span>
		              </td>
		              <td>
                         <a class="grey" href="javascript:;" ng-click="deleteApply(row)" ng-if="row.status == 'CREATED'">删除</a>
                         <%--
                         <a class="blue-font" href="javascript:;" ng-click="showDetails(row)">详情</a>
                         --%>
		              </td>
		          </tr>
	          </tbody>
	      </table>
		  <div class="page_v1" style="text-align: center;">
			  <span class="total">共<em>{{pageTotal}}</em>页</span>
			  <span class="ell" ng-if="pageButtons[0] != 1">...</span>
			  <ng-repeat ng-repeat="p in pageButtons"><a target="_self" href="javascript:;" ng-if="p!=page" ng-click="gotoPage(p)">{{p}}</a><span class="cur" ng-if="p==page">{{p}}</span></ng-repeat>
			  <span class="ell" ng-if="pageButtons[pageButtons.length - 1] != pageTotal">...</span>
			  <a target="_self" href="javascript:;" ng-click="gotoPage(hasNextPage ? page+1 : page)">下一页</a><span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span><a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
		  </div>
	    </div>
	  </div>
	<!--content-end-->

    
    <%-- 申请详情 --%>
    <div class="alert" style="display:none; width:600px;" id="viewApplyPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span>云主机申请详情</span></div>
		<div class="pop-content" style="padding: 30px 0;">
		    <ul class="form clearfix">
		        <li class="clearfix"><label class="tit">申请内容：</label><span class="">CPU:{{viewApply.specs.cpu}}，内存：{{viewApply.specs.memory}}，硬盘：{{viewApply.specs.disk}}，带宽：{{viewApply.specs.bandwidth}}</span></li>
		        <li class="clearfix"><label class="tit">时长：</label><span class="">{{viewApply.serviceDuration}}个月</span></li>
		        <li class="clearfix"><label class="tit">费用总计：</label><span class="" ng-show="viewApply.price > 0">{{viewApply.price}}元</span></li>
		        <li class="clearfix"><label class="tit">申请日期：</label><span class="">{{viewApply.createTime | date:'yyyy-MM-dd'}}</span></li>
		        <li class="clearfix">
			        <label class="tit">类型：</label>
		        	<span class="">{{viewApply.specName}}</span>
		        </li>
		        <li class="clearfix">
		        	<label class="tit">审批进度：</label>
		        	<span class="green-font" ng-if="viewApply.status == 'CREATED'">待审批</span>
		        	<span class="grey-font" ng-if="viewApply.status == 'APPROVED'">已审批</span>
		        	<span class="red-font" ng-if="viewApply.status == 'REJECTED'">审批不通过</span>
	        	</li>
		    </ul>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/js/tech/computing_applylist.js?v=${buildTimestamp}"></script>

</body>
</html>