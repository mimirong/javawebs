<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", -10);
	%>
	<title>项目管理</title>
</head>
<body>

<div class="ng-cloak" ng-app="docModule" ng-controller="docController">   
   <!--banner-end-->
  <h1 class="column_tit">您当前位置：<a href="${contextPath}">首页</a> > <a href="list">项目管理</a> > <span>项目信息</span></h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH">
   <input type="hidden" id="typeCodeBegin" value="200"/>
      <h2 class="title">项目信息<a href="detail?projectId=${projectId }" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?projectId=${projectId }">基本信息</a><a  href="doc1?projectId=${projectId }">手续文件</a><a  class="on" href="doc2?projectId=${projectId }">文档资料</a><a href="monthReport?projectId=${projectId }">项目月报</a><a href="yearPlan?projectId=${projectId }">年度计划</a></div>
       <div class="tab-content" style="padding: 0;border: none;">
          <h3 class="title" style="border-bottom: none;margin-bottom: 0;margin-top: -16px;">${projectName}<a href="javascript:;" class="btn op-add addDoc" title="添加批复文件">添加</a></h3>
          <table class="column_tab_table">
              <thead>
              <tr>
                  <td width="8%">序号</td>
                  <td width="15%">资料类型</td>
                  <td width="30%">资料名称</td>
                  <td width="10%">上传人</td>
                  <td width="15%">最近更新时间</td>
                  <td width="12%">是否通过审核</td>
                  <td width="10%">操作</td>
              </tr>
              </thead>
              <tbody>
              
               <tr ng-repeat="item in dataList">
			      	 <td>{{(page-1)*10+$index+1}}</td>
			      	 <td>{{item.typeName}}</td>
			         <td><a href="{{item.fileId}}" class="blue-font">{{item.fileName}}</a></td>
			         <td>{{item.uploadUserName}}</td>
			         <td>{{item.uploadTime | date:'yyyy-MM-dd HH:mm'}}</td>
			         <td ng-if="item.fileId == null"></td>
			         <td ng-if="item.fileId != null && item.status != '1' && item.status != '0'">待审核</td>
			         <td class="green-font" ng-if="item.status == '1'"><span style="color:#00FF00">已通过</span></td>
		             <td class="red-font" ng-if="item.status == '0'"><span style="color:#FF0000">未通过</span></td>
		             <td ng-if="item.fileId == null"><a data-value="{{item.docId}}" ng-click="modifyDoc(item)" class="blue-font op-del">修改</a></td>
			         <td ng-if="item.fileId != null"><a data-value="{{item.docId}}" ng-click="modifyDoc(item)" class="blue-font op-del">修改</a>&nbsp;&nbsp;<a data-value="{{item.docId}}" ng-click="deleteDoc(item.docId)" class="blue-font op-del">删除</a></td>
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
 </div>  
 
  <div id="addDoc"  style="display:none">
  <form name="form" action="${contextPath}/pmProject/addDoc" enctype="multipart/form-data" method="post">
		<div class="pop-content" style="padding: 30px 0;">
		  <ul class="form clearfix">
		  <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
		  <input type="hidden" name="typeName" value=""/>
		  <input type="hidden" name="typeCode" value=""/>
		   <input type="hidden" name="docType" value="2"/>
		  <li class="clearfix"><label class="tit"><em>*</em>资料类型：</label>
	            <span class="select docType" style="width: 302px;">
	                 <select>
	                	 <option value="-1">--请选择--</option>
	                	<c:forEach var="m" items="${docTypes}">
							 <option value="${m.typeId}">${m.typeName}</option>
						</c:forEach>
	                </select>
	            </span>
	        </li>
	        <li class="clearfix"><label class="tit"><em>*</em>资料名称：</label><input type="text" name="fileName" class="text w300" value=""/></li>
	        <li class="clearfix"><label class="tit"><em>*</em>选择上传文件：</label><input type="file" name="fileContent" value="上传申请材料"/></li>
	        <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn submitAddDoc">提交</a><a href="javascript:;" onclick="MU.close(this)" class="btn btn-grey">取消</a></li>
	    </ul>
    </div>
	</form>
  </div>
  
  
  <div id="modifyDoc"  style="display:none">
  <form name="form" action="${contextPath}/pmProject/modifyDoc" enctype="multipart/form-data" method="post">
		<div class="pop-content" style="padding: 30px 0;">
		  <ul class="form clearfix">
		  <input type="hidden" id="projectId" name="projectId" value="${projectId}"/>
		  <input type="hidden" name="typeName" value=""/>
		  <input type="hidden" name="typeCode" value=""/>
		  <input type="hidden" name="docType" value="2"/>
		   <input type="hidden" name="docId" value=""/>
		  <li class="clearfix"><label class="tit"><em>*</em>资料类型：</label>
	            <span class="select docType" style="width: 302px;">
	                 <select>
	                	 <option value="-1">--请选择--</option>
	                	<c:forEach var="m" items="${docTypes}">
							 <option value="${m.typeId}">${m.typeName}</option>
						</c:forEach>
	                 </select>
	            </span>
	        </li>
	        <li class="clearfix fileName"><label class="tit"><em>*</em>资料名称：</label><input type="text" name="fileName" class="text w300" value=""/></li>
	        <li class="clearfix"><label class="tit"><em>*</em>选择上传文件：</label><input type="file" name="fileContent" value="上传申请材料"/></li>
	        <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn submitModifyDoc">提交</a><a href="javascript:;" onclick="MU.close(this)" class="btn btn-grey">取消</a></li>
	    </ul>
    </div>
	</form>
  </div>
  
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/pmProject/doc.js?v=${buildTimestamp}"></script>
</body>
</html>