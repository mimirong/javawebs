<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
   
    
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/requireList">需求大厅</a> &gt; <span>我的发布</span></h1>

    <div class="cen ng-cloak" id="minServiceH" ng-app="requireModule" ng-controller="requireController">
        <h2 class="se-title se-title-noborder"><span>我的发布</span><a href="javascript:history.go(-1);" class="more">返回</a></h2>
        <ul class="se-list">
        
            <li class="h80" ng-repeat="row  in dataList">
                <table class="se-table">
                    <tr>
                        <td width="" valign="top">
                            <div class="info">
                                <h2><a href="${contextPath}/outsourcing/requireDetail?requireId={{row.requireId}}" class="wh" style="width: 250px;" title="{{row.requireTitle}}">{{row.requireTitle}}</a></h2>
                                <p>{{row.companyName}}</p>
                            </div>
                        </td>
                        <td width="270"><span class="wh" style="width: 250px;">{{row.requireDesc}}</span></td>
                        <td width="180" align="center">
                           <p>发布日期：{{row.publishTime | date:'yyyy-MM-dd'}}</p>
                            <p>截止日期：{{row.deadDate | date:'yyyy-MM-dd'}}</p>
                        </td>
                        <td width="90" align="center">
                            <div class="price">
                                价格<span class="red" ng-if="row.isChat == '1'">面议</span>
              <span class="red" ng-if="row.isChat != '1'">{{row.hopePrice}}{{row.priceUnit}}</span>
                            </div>
                        </td>
                        <td width="90" align="center">
                            <div class="price">
                                数量<span class="red">{{row.requireNum}}{{row.numUnit}}</span>
                            </div>
                        </td>
                        <td width="160">
                        <a href="${contextPath}/outsourcing/requireDetail?requireId={{row.requireId}}" class="btn btn-grey">查看详情</a>
                        <a class="se-del" ng-click="deleteRequire(row.requireId)">删除</a>
                        <a class="se-del" href="${contextPath}/outsourcing/requireModify?requireId={{row.requireId}}">修改</a>
                        </td>
                    </tr>
                </table>
            </li>
            
        </ul>
       <div class="page_v1">
          <span class="total">共<em>{{pageTotal}}</em>页</span>
          <span class="ell" ng-if="pageButtons[0] != 1">...</span>
          <ng-repeat ng-repeat="p in pageButtons">
              <a target="_self" href="javascript:;" ng-if="p!=page" ng-click="gotoPage(p)">{{p}}</a>
              <span class="cur" ng-if="p==page">{{p}}</span>
          </ng-repeat>
          <span class="ell" ng-if="pageButtons[pageButtons.length - 1] != pageTotal">...</span>
          <a target="_self" href="javascript:;" ng-click="gotoPage(hasNextPage ? page+1 : page)">下一页</a>
          <span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span>
          <a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
      </div>
    </div>
      <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  	<script src="${contextPath}/static/js/outsourcing/requireMy.js?v=${buildTimestamp}"></script> 
    
    
</body>
</html>