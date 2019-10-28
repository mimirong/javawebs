<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>手续文件</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>

	<div  ng-app="docModule" ng-controller="docController" class="ng-cloak" >
     <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
     <input type="hidden" id="typeCodeBegin" value="100"/>
      <h2 class="title">项目信息<a href="list" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?serviceId=${projectId}" >基本信息</a><a class="on" href="doc1?projectId=${projectId}">手续文件</a><a href="doc2?projectId=${ projectId}">文档资料</a><a href="monthReport?projectId=${ projectId}">项目月报</a><a href="yearPlan?projectId=${ projectId}">年度计划</a></div>
       <div class="tab-content" style="padding: 0;border: none;">
          <h3 class="title" style="border-bottom: none;margin-bottom: 0;margin-top: -16px;">${projectName}<!--<a href="ajax/add-reply-file.html" class="btn op-add" title="添加批复文件">添加</a>--></h3>
          <table class="column_tab_table">
              <thead>
              <tr>
                  <td width="5%">序号</td>
                  <td width="15%">文件类型</td>
                  <td width="15%">文件名称</td>
                  <td width="15%">批复文号</td>
                  <td width="10%">上传人</td>
                  <td width="15%">上传时间</td>
                   <td width="10%">是否通过审核</td>
                   <td width="15%">操作</td>
              </tr>
              </thead>
              <tbody>
              <tr ng-repeat="item in dataList">
			      	 <td>{{(page-1)*10+$index+1}}</td>
			      	 <td>{{item.typeName}}</td>
			         <td ng-if="item.fileId != null"><a href="{{item.fileId}}" class="blue-font">{{item.fileName}}</a></td>
			         <td ng-if="item.fileId == null"></td>
			         <td>{{item.approveCode}}</td>
			         <td>{{item.uploadUserName}}</td>
			         <td>{{item.uploadTime | date:'yyyy-MM-dd HH:mm'}}</td>
			          <td ng-if="item.fileId == null"></td>
			         <td ng-if="item.fileId != null && item.status != '1' && item.status != '0'">待审核</td>
			         <td class="green-font" ng-if="item.fileId != null && item.status == '1'"><span style="color:#00FF00">已通过</span></td>
		             <td class="red-font" ng-if="item.fileId != null && item.status == '0'"><span style="color:#FF0000">未通过</span></td>
			         <td ng-if="item.fileId == null"></td>
			         <td  ng-if="item.fileId != null && item.status != '1' && item.status != '0'"> <a data-value="{{item.docId}}"    ng-click="chgDocStatus(item.docId)" class="blue-font">通过</a>&nbsp; <a data-value="{{item.docId}}"    ng-click="chgDocStatus0(item.docId)" class="blue-font">不通过</a> &nbsp;<a data-value="{{item.docId}}" ng-click="deleteDoc(item.docId)" class="blue-font op-del">删除</a></td>
			         <td  ng-if="item.status == '1'"><span class="grey">通过</span>&nbsp; <a data-value="{{item.docId}}"    ng-click="chgDocStatus0(item.docId)" class="blue-font">不通过</a> &nbsp;<a data-value="{{item.docId}}" ng-click="deleteDoc(item.docId)" class="blue-font op-del">删除</a></td>
		       		 <td  ng-if="item.status == '0'"> <a data-value="{{item.docId}}"    ng-click="chgDocStatus(item.docId)" class="blue-font">通过</a>&nbsp;<span class="grey">不通过</span> &nbsp;<a data-value="{{item.docId}}" ng-click="deleteDoc(item.docId)" class="blue-font op-del">删除</a></td>
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
   <script type="text/javascript" >
    $('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
  </script>
	<script type="text/javascript" src="${contextPath}/static/js/spark/PmProject_Doc.js"></script>
	
</body>