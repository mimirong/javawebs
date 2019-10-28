<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办事事项</title>
 <script type="text/javascript" src="${contextPath}/static/web2/public/js/jq_scroll.js"></script>
</head>
<body>
<div class="ng-cloak" ng-app="serviceShowModule" ng-controller="serviceShowController">
    <h1 class="column_tit">您当前位置：
    	<a href="${contextPath}/">首页</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事服务</a> > 
    	<a href="${contextPath}/serviceGuide1/serviceShow">办事公示</a> 
   	</h1>
  <!--content-start-->
  <div class="column_con clearfix" style="padding: 0 20px 35px; width: 960px;">
      <h2 class="title">办事公示<a class="blue fr" href="serviceListMy">我的办件</a></h2>
      <div class="column_tab_con">
          <div class="filter-box">
              <label class="tit">办件编号：</label><input class="txt mr20" ng-model="businessNo">
              <label class="tit">申办事项：</label><input class="txt mr20" ng-model="guidename">
              <label class="tit">申办单位（人）：</label><input class="txt mr20" ng-model="businessDetpOrPerson">
          </div>
          <table class="column_tab_table">
              <thead>
              <tr>
                  <td width="142px">办件编号</td>
                  <td width="90px">受理窗口</td>
                  <td width="170px">申办事项</td>
                  <td width="160px">申办单位(人)</td>
                  <td width="85px">受理时间</td>
                  <td width="85px">办结时间</td>
                  <td width="60px">当前状态</td>
              </tr>
              </thead>
          </table>
           <div id="tableOpList" style="border-left:none;">
              <div class="tabnew" style="border-left:1px solid #ddd;">
                  <ul ng-repeat="row in businessList track by $index" class="clearfix">
                      <li class="acol" style="text-align:center;"><a href="serviceDetail?serviceId={{row.serviceId}}" style="color:#107aee;">{{row.businessNo}}</a></li>
                      <li class="bcol" style="text-align:center;" title="{{row.deptname}}">{{row.deptname}}</li>
                      <li class="ccol" title="{{row.guidename}}">{{row.guidename}}</li>
                      <li class="dcol" title="{{row.businessDeptPerson}}">{{row.businessDeptPerson}}</li>
                      <li class="ecol" style="text-align:center;">{{row.createTime | date : 'yyyy-MM-dd'}}</li>
                      <li class="fcol" style="text-align:center;">{{row.finishTime | date : 'yyyy-MM-dd'}}</li>
                      <li class="gcol" style="text-align:center;">
                      	  <span ng-if="row.status == '110'">待预审</span>
                      	  <span ng-if="row.status != '110' && row.status != '211' && row.status != '221' && row.status != '231'" style="color:#107aee">已受理</span>
                      	  <span ng-if="row.status == '211'">已办结</span>
                      	  <span ng-if="row.status == '221' || row.status == '231'">废弃件</span>
                      </li>
                  </ul>
              </div>
          </div>
          
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
$(function(){
    $("#tableOpList").Scroll({line:1,speed:500,timer:2000}); // 办件公示列表滚动
});
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/serviceGuide/serviceShow_list.js?v=${buildTimestamp}"></script>

</body>
</html>