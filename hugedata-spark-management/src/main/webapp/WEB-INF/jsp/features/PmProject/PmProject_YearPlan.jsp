<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>年度计划</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>

	<div  ng-app="yearPlanModule" ng-controller="yearPlanController" class="ng-cloak">
     <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
      <h2 class="title">项目信息<a href="list" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?serviceId=${projectId}" >基本信息</a><a  href="doc1?projectId=${projectId}">手续文件</a><a  href="doc2?projectId=${ projectId}">文档资料</a><a href="monthReport?projectId=${ projectId}">项目月报</a><a class="on" href="yearPlan?projectId=${ projectId}">年度计划</a></div>
       <div class="tab-content" style="padding: 0;border: none;">
          <h3 class="title" style="border-bottom: none;margin-bottom: 0;margin-top: -16px;">${projectName}<!--<a href="ajax/add-reply-file.html" class="btn op-add" title="添加批复文件">添加</a>--></h3>
          <table class="column_tab_table">
              <thead>
               <tr>
                  <td width="5%">序号</td>
                  <td width="10%">年份</td>
                  <td width="10%">年计划投资（万元）</td>
                  <td width="10%">计划投资主体</td>
                  <td width="20%">年主要建设内容</td>
                  <td width="15%">创建时间</td>
                  <td width="20%">审核结论</td>
                  <td width="10%">操作</td>
              </tr>
              </thead>
              <tbody>
              
               <tr ng-repeat="item in dataList">
			      	 <td>{{(page-1)*10+$index+1}}</td>
			      	 <td>{{item.planYear}}</td>
			         <td>{{item.amount.toFixed(2)}}</td>
			         <td>{{item.mainActor}}</td>
			         <td>{{item.content}}</td>
			         <td>{{item.createTime | date:'yyyy-MM-dd HH:mm'}}</td>
			         <td>{{item.approveResult}}</td>
			         
			         <td ng-if="item.planStatus"><span class="gray">通过</span></td>
	         		 <td ng-if="!item.planStatus" ><span class="red" style="cursor:pointer" ng-click="approvePlan(item.planId)" >审核</span>&nbsp;<span class="blue" style="cursor:pointer" ng-click="deletePlan(item.planId)" >删除</span></td>
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
  
   <div id="approvePlan"  style="display:none">
  <form name="form" action="approvePlan" enctype="multipart/form-data" method="post">
		<div class="pop-content" style="padding: 30px 0;">
		<input type="hidden"  name="projectId" value="${projectId }"/>
		 <input type="hidden"  name="approveResult" value=""/>
		 <input type="hidden"  name="planId" value=""/>
		 <input type="hidden"  name="approveStatus" value=""/>
		 <ul class="form clearfix">
        	<li class="clearfix"><textarea name="result" cols="30" rows="10" placeholder="限字200" style="width: 582px;"></textarea></li>
    	</ul>
     <div style="text-align: center;padding:10px 0;"><a href="javascript:;" class="btn mr10 approvePlan">通过</a><a href="javascript:;" class="btn mr10 disApprovePlan">不通过</a><a href="javascript:;" onclick="MU.close(this)" class="btn btn-grey">取消</a></div>
    </div>
	</form>
  </div>
  <script type="text/javascript" >
  $('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
  </script>
  
  
	<script type="text/javascript" src="${contextPath}/static/js/spark/PmProject_YearPlan.js"></script>
	
</body>