<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目管理</title>
</head>
<body>

<div class="ng-cloak" ng-app="yearPlanModule" ng-controller="yearPlanController">   
   <!--banner-end-->
  <h1 class="column_tit">您当前位置：<a href="${contextPath}">首页</a> > <a href="list">项目管理</a> > <span>项目信息</span></h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH">
   <input type="hidden" id="typeCodeBegin" value="200"/>
      <h2 class="title">项目信息<a href="detail?projectId=${projectId }" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?projectId=${projectId }">基本信息</a><a  href="doc1?projectId=${projectId }">手续文件</a><a  href="doc2?projectId=${projectId }">文档资料</a><a href="monthReport?projectId=${projectId }">项目月报</a><a class="on" href="yearPlan?projectId=${projectId }">年度计划</a></div>
       <div class="tab-content" style="padding: 0;border: none;">
          <h3 class="title" style="border-bottom: none;margin-bottom: 0;margin-top: -16px;">${projectName} <a href="javascript:;" class="btn op-add addPlan" title="添加/修改年度计划">添加</a></h3>
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
			         
			         <td ng-if="item.planStatus"><span class="gray">已审核</span></td>
	         		 <td ng-if="!item.planStatus"><a  ng-click="modifyPlan(item)" class="blue-font op-del">修改</a></td>
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
 
  <div id="addPlan"  style="display:none">
  <form name="form" action="${contextPath}/pmProject/addPlan" enctype="multipart/form-data" method="post">
		<div class="pop-content" style="padding: 30px 0;">
		  <ul class="form clearfix">
		  <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
		  <input type="hidden"  name="content" value=""/>	
		  <input type="hidden"  name="planYear" value=""/>	
		  <li class="clearfix planYear"><label class="tit"><em>*</em>年份：</label>
            <span class="select planYear" style="width: 302px;">
                <select>
                    <option value="1">明年</option>
                    <option value="0">今年</option>
                </select>
            </span>
        </li>
        <li class="clearfix"><label class="tit"><em>*</em>年计划投资（万元）：</label><input type="text" name="amount" class="text w300" value=""/></li>
        <li class="clearfix"><label class="tit"><em>*</em>计划投资主体：</label><input type="text" name="mainActor" class="text w300" value=""/></li>
        <li class="clearfix"><label class="tit"><em>*</em>年主要建设内容：</label>
            <textarea name="contentT" cols="30" rows="10" style="width: 284px; height: 78px;"></textarea>
        </li>			 
        <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn submitAddPlan">提交</a><a href="javascript:;" onclick="MU.close(this)" class="btn btn-grey">取消</a></li>
	    </ul>
    </div>
	</form>
  </div>
  
  
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/pmProject/yearPlan.js?v=${buildTimestamp}"></script>
</body>
</html>