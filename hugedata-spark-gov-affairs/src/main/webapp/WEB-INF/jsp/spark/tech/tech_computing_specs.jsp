<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="main2">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外包服务</title>
</head>
<body>
<div class="ng-cloak" ng-app="techComputingSpecsModule" ng-controller="techComputingSpecsController">
	<h1 class="column_tit" style="clear:both;">您当前位置：
		<a href="${contextPath}/">首页</a> &gt;  
		<a href="${contextPath}/integrated/index">综合服务</a> &gt;  
		<a href="${contextPath}/outsourcing/tech/computing/applyList">IT基础设施服务</a> &gt; 
		<a href="${contextPath}/outsourcing/tech/computing/applyList">云主机</a> &gt; 
		<span style="width:100px;">云主机申请</span>
	</h1>
	
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
        <li class="menu_tit">IT基础设施服务</li>
        <li class="active">
            <a class="drop down">云主机</a>
            <dl style="display: block;">
                <dd><a href="${contextPath}/outsourcing/tech/computing/applyList">申请记录</a></dd>
                <dd class="on"><a href="${contextPath}/outsourcing/tech/computing/specs">云主机申请</a></dd>
            </dl>
        </li>
        <li>
            <a class="drop">云存储</a>
            <dl>
                <dd><a href="${contextPath}/outsourcing/tech/storage/applyList">申请记录 </a></dd>
                <dd><a href="${contextPath}/outsourcing/tech/storage/specs">云存储申请</a></dd>
            </dl>
        </li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">云主机申请</h2>
        <div class="tab-btn"><a class="{{curTab==1 ? 'on' : ''}}" ng-click="showSpecs()">套餐申请</a><a class="{{curTab==2 ? 'on' : ''}}" ng-click="showApplyDialog(null)">自定义申请</a></div>
        <div class="tab-detail">
            <div class="tab-details" style="display:block" ng-show="curTab==1">
                <ul class="cloud-block clearfix">
                   <li class="block-con" ng-show="specs.length >= 1">
                       <div class="block-tit">
                           <h1 class="title">{{specs[0].specName}}</h1>
                           <p class="tit-detail"><span class="num">¥{{specs[0].price}}</span>/月</p>
                       </div>
                       <p class="cloud-con"><label class="tit">CPU：</label><span>{{specs[0].cpu}}</span></p>
                       <p class="cloud-con"><label class="tit">内存：</label><span>{{specs[0].memory}}</span></p>
                       <p class="cloud-con"><label class="tit">硬盘：</label><span>{{specs[0].disk}}</span></p>
                       <p class="cloud-con"><label class="tit">带宽：</label><span>{{specs[0].bandwidth}}</span></p>
                       <p class="cloud-con"><label class="tit">时长:</label>
							<span class="select mr10" style="width:60px;">
								<select class="directDurationSelect" data-index="0">
									<option value="1">1</option><option value="2">2</option><option value="3">3</option>
									<option value="4">4</option><option value="5">5</option><option value="6">6</option>
                                    <option value="7">7</option><option value="8">8</option><option value="9">9</option>
                                    <option value="10">10</option><option value="11">11</option><option value="12">12</option>
								</select>
							</span>个月
                       </p>
                       <a class="cloud-btn" ng-click="directApplySpec(specs[0], directApplyDuration[0])">立即申请</a>
                   </li>
                    <li class="block-con second" ng-show="specs.length >= 2">
                        <div class="block-tit">
                            <h1 class="title">{{specs[1].specName}}</h1>
                            <p class="tit-detail"><span class="num">¥{{specs[1].price}}</span>/月</p>
                        </div>
                        <p class="cloud-con"><label class="tit">CPU：</label><span>{{specs[1].cpu}}</span></p>
                        <p class="cloud-con"><label class="tit">内存：</label><span>{{specs[1].memory}}</span></p>
                        <p class="cloud-con"><label class="tit">硬盘：</label><span>{{specs[1].disk}}</span></p>
                        <p class="cloud-con"><label class="tit">带宽：</label><span>{{specs[1].bandwidth}}</span></p>
                        <p class="cloud-con"><label class="tit">时长:</label>
							<span class="select mr10" style="width:60px;">
								<select class="directDurationSelect" data-index="1">
									<option value="1">1</option><option value="2">2</option><option value="3">3</option>
									<option value="4">4</option><option value="5">5</option><option value="6">6</option>
                                    <option value="7">7</option><option value="8">8</option><option value="9">9</option>
                                    <option value="10">10</option><option value="11">11</option><option value="12">12</option>
								</select>
							</span>个月
                       </p>
                        <a class="cloud-btn" ng-click="directApplySpec(specs[1], directApplyDuration[1])">立即申请</a>
                    </li>
                    <li class="block-con last" ng-show="specs.length >= 3">
                        <div class="block-tit">
                            <h1 class="title">{{specs[2].specName}}</h1>
                            <p class="tit-detail"><span class="num">¥{{specs[2].price}}</span>/月</p>
                        </div>
                        <p class="cloud-con"><label class="tit">CPU：</label><span>{{specs[2].cpu}}</span></p>
                        <p class="cloud-con"><label class="tit">内存：</label><span>{{specs[2].memory}}</span></p>
                        <p class="cloud-con"><label class="tit">硬盘：</label><span>{{specs[2].disk}}</span></p>
                        <p class="cloud-con"><label class="tit">带宽：</label><span>{{specs[2].bandwidth}}</span></p>
                        <p class="cloud-con"><label class="tit">时长:</label>
							<span class="select mr10" style="width:60px;">
								<select class="directDurationSelect" data-index="2">
									<option value="1">1</option><option value="2">2</option><option value="3">3</option>
									<option value="4">4</option><option value="5">5</option><option value="6">6</option>
                                    <option value="7">7</option><option value="8">8</option><option value="9">9</option>
                                    <option value="10">10</option><option value="11">11</option><option value="12">12</option>
								</select>
							</span>个月
                       </p>
                        <a class="cloud-btn" ng-click="directApplySpec(specs[2], directApplyDuration[2])">立即申请</a>
                    </li>
                </ul>
            </div>
            <div class="tab-details" style="display:block" ng-show="curTab==2">
            	<ul class="form clearfix" style="margin-top: 40px;">
	           		<li class="clearfix">
			            <label class="tit w110 mt10" style="clear: both;">CPU：</label>
			           	<span class="select fl" style="width:170px;">
			                <select id="specCpu" style="display: none;">
			                </select>
			            </span>
	           		</li>
	           		<li class="clearfix">
			            <label class="tit w110 mt10" style="clear: both;">内存：</label>
			           	<span class="select fl" style="width:170px;">
			                <select id="specMemory" style="display: none;">
			                </select>
			            </span>
	           		</li>
	           		<li class="clearfix">
			            <label class="tit w110 mt10" style="clear: both;">硬盘：</label>
			           	<span class="select fl" style="width:170px;">
			                <select id="specDisk" style="display: none;">
			                </select>
			            </span>
	           		</li>
	           		<li class="clearfix">
			            <label class="tit w110 mt10" style="clear: both;">带宽：</label>
			           	<span class="select fl" style="width:170px;">
			                <select id="specBandwidth" style="display: none;">
			                </select>
			            </span>
	           		</li>
            		<li class="clearfix">
	                    <label class="tit w110 mt10" style="clear: both;">购买时长 :</label>
			           	<span class="select fl" style="width:100px;">
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
						<span class="select fl" style="width:100px;">
							<select id="amount" style="display: none;">
	                            <option value="1">1</option>
	                            <option value="2">2</option>
	                            <option value="3">3</option>
	                            <option value="4">4</option>
	                            <option value="5">5</option>
	                        </select>
						</span>
            		</li>
            		<%--
            		<li class="clearfix">
	                    <label class="tit w110" style="clear: both;">&nbsp;</label>
	                    <span>CPU:{{applyingSpec.cpu}} MEMORY:{{applyingSpec.memory}} DISK:{{applyingSpec.disk}} BAND:{{applyingSpec.bandwidth}} DURATION:{{serviceDuration}} AMOUNT:{{applyAmount}}</span>
            		</li>
            		--%>
            		<li class="clearfix">
	                    <label class="tit w110" style="clear: both;">&nbsp;</label>
	                    <a class="btn" style="margin-left:0;" href="javascript:;" ng-click="submitApply()">立即申请</a>
            		</li>
            	</ul>
            </div>
        </div>

    </div>
  </div>
  <!--content-end-->
        
</div>

<script type="text/javascript" src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/js/tech/computing_specs.js?v=${buildTimestamp}"></script>
    
</body>
</html>