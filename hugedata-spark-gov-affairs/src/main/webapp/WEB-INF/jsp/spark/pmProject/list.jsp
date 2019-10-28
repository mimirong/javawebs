<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目管理</title>
</head>
<body>
<div class="ng-cloak" ng-app="pmProjectListModule" ng-controller="pmProjectListController">
  <!--banner-end-->
    <h1 class="column_tit">您当前位置：<a href="${contextPath}">首页</a> > <span>项目管理</span></h1>
<!--content-start-->
  <div class="column_con clearfix" id="minH">
      <h2 class="title">项目列表</h2>
      <div class="column_tab_con">
          <div class="filter-box">
              <label class="tit">关键词查询：</label><input type="text" id="keyword" ng-keydown="reload()" class="txt"><a href="" class="btn btnSearch">查询</a>
          </div>
          <table class="column_tab_table">
              <thead>
              <tr>
                  <td>序号</td>
                  <td>项目名称</td>
                  <td>项目情况</td>
                  <td>创建时间</td>
                  <td>操作</td>
              </tr>
              </thead>
              <tbody  ng-if="dataList.length == 0 ">
             	 <tr class="odd"><td valign="top" colspan="5" class="dataTables_empty">没有数据</td></tr>
              </tbody>
              <tbody ng-if="dataList.length > 0 ">
              
              	  <tr ng-repeat="item in dataList">
			      	 <td>{{(page-1)*10+$index+1}}</td>
			         <td>{{item.projectName}}</td>
			          <td ng-if="item.projectStatus == '0'"><span class="red-font">异常</span></td>
			          <td ng-if="item.projectStatus == '1'">正常</td>
			          <td>{{item.createTime | date:'yyyy-MM-dd HH:mm'}}</td>
			          <td><a href="detail?projectId={{item.projectId}}" title="查看详情" class="blue-font">修改</a></td>
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
<script>
		var deptCode = "${deptCode}";
	</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/pmProject/list.js?v=${buildTimestamp}"></script>
</body>
</html>