<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="main2">
<title>外包服务</title>
</head>
<body>

    <div class="column_list ng-cloak" ng-app="techStorageSpecsModule" ng-controller="techStorageSpecsController">
	<h1 class="column_tit" style="clear:both;">您当前位置：
		<a href="${contextPath}/">首页</a> &gt;  
		<a href="${contextPath}/integrated/index">综合服务</a> &gt;  
		<a href="${contextPath}/outsourcing/tech/computing/applyList">IT基础设施服务</a> &gt; 
		<a href="${contextPath}/outsourcing/tech/storage/applyList">云存储</a> &gt; 
		<span style="width:100px;">云存储申请</span>
	</h1>
	
	  <!--content-start-->
	  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
	    <div class="column_menu">
	      <ul class="leftsidebar">
	        <li class="menu_tit">IT基础设施服务</li>
	        <li>
	            <a class="drop">云主机</a>
	            <dl>
	                <dd><a href="${contextPath}/outsourcing/tech/computing/applyList">申请记录</a></dd>
	                <dd><a href="${contextPath}/outsourcing/tech/computing/specs">云主机申请</a></dd>
	            </dl>
	        </li>
	        <li class="active">
	            <a class="drop down">云存储</a>
	            <dl style="display: block;">
	                <dd><a href="${contextPath}/outsourcing/tech/storage/applyList">申请记录 </a></dd>
	                <dd class="on"><a href="${contextPath}/outsourcing/tech/storage/specs">云存储申请</a></dd>
	            </dl>
	        </li>
	      </ul>
	    </div>
	    <div class="column_list">
	        <h2 class="title">云存储申请</h2>
           	<ul class="form clearfix" style="margin-top: 40px;">
           		<li class="clearfix">
		            <label class="tit w110">储存大小:</label>
		            <input type="text" class="txt mr10" style="width:82px; height:30px;" ng-model="applyStorageSize" />
		            <span>GB</span>
		            <span style="font-size: 12px;color: #fb5044; margin-left: 22px;">20-20000GB</span>
           		</li>
           		<li class="clearfix">
		            <label class="tit w110 mt10" style="clear: both;">购买时长 :</label>
		           	<span class="select fl" style="width: 88px;">
		                <select id="serviceDuration" style="display: none;">
		                    <option value="1">1月</option>
		                    <option value="2">2月</option>
		                    <option value="3">3月</option>
		                    <option value="4">4月</option>
		                    <option value="5">5月</option>
		                    <option value="6">6月</option>
		                    <option value="7">7月</option>
		                    <option value="8">8月</option>
		                    <option value="9">9月</option>
		                    <option value="12">1年</option>
		                    <option value="24">2年</option>
		                    <option value="36">3年</option>
		                </select>
		            </span>
           		</li>
           		<li class="clearfix">
		            <label class="tit w110" style="clear: both;">购买数量 :</label>
		           	<span class="select fl" style="width: 88px;">
		                <select id="amount" style="display: none;">
		                    <option value="1">1</option>
		                    <option value="2">2</option>
		                    <option value="3">3</option>
		                    <option value="4">4</option>
		                    <option value="5">5</option>
		                </select>
		            </span>
           		</li>
           		<li class="clearfix">
           			<label class="tit w110">计算费用 :</label>
           			<span class="price">￥{{totalPrice}} </span>
           		</li>
           		<li class="clearfix">
           			<label class="tit w110">&nbsp;</label>
					<a class="btn" href="javascript:;" ng-click="submitApply()">立即申请</a><a class="btn btn-grey" href="javascript:;">取消</a>
           		</li>
           	</ul>
	    </div>
	  </div>
	  <!--content-end-->
        
    </div>

	<script type="text/javascript" src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
 	<script type="text/javascript" src="${contextPath}/static/js/tech/storage_specs.js?v=${buildTimestamp}"></script>
 	
</body>
</html>