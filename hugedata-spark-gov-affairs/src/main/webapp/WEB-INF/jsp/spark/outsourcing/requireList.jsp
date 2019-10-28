<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>需求大厅</span></h1>

    <div class="cen ng-cloak" id="minServiceH" ng-app="requireModule" ng-controller="requireController">
        <div class="se-title-tab clearfix">
            <div class="fr"><a href="${contextPath}/outsourcing/requireMy" class="btn">我的发布</a><span>信息共 <b>{{count}}</b> 条</span></div>
        </div>
        <div class="se-filter clearfix">
            <label class="tit">服务领域：</label>
            <div class="conbox">
                <a>其他</a><a>化学化工</a><a>建筑建材</a><a>无损检测</a><a>消费品</a><a>环境监测与职业卫生</a><a>生物医药及器械</a><a>电子电气及机械</a><a>电磁兼容（EMC）</a>
                 <a value="纺织及纤维">纺织及纤维</a>
                                    <a value="软件信息">软件信息</a>
                                    <a value="金属材料">金属材料</a>
                                    <a value="食品及农产品">食品及农产品</a>
            </div>
            <a class="show_more" data-id="0">更多</a>
        </div>
        <div class="se-sort">
            <label>排序：</label>
            <a class="on">按时间</a><a>按浏览量</a>
            <span class="fr"><b class="blue">{{page}}</b> / <b class="fb">{{pageTotal}}</b> 页</span>
        </div>
        <ul class="se-list">
        	 <li class="nothing" ng-if="dataList.length == 0 ">
               <p class="tips"><em></em>找不到您查询的相关信息</p>
            </li>
        
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
                        <td width="190" align="center">
                            <p>发布日期：{{row.publishTime | date:'yyyy-MM-dd'}}</p>
                            <p>截止日期：{{row.deadDate | date:'yyyy-MM-dd'}}</p>
                        </td>
                        <td width="85" align="center">
                            <div class="price">
                                价格<span class="red" ng-if="row.isChat == '1'">面议</span>
              <span class="red" ng-if="row.isChat != '1'">{{row.hopePrice}}{{row.priceUnit}}</span>
                            </div>
                        </td>
                        <td width="120" align="center">
                            <div class="price">
                                数量<span class="red">{{row.requireNum}}{{row.numUnit}}</span>
                            </div>
                        </td>
                        <td width="130"><a href="${contextPath}/outsourcing/requireDetail?requireId={{row.requireId}}" class="btn btn-grey">查看详情</a></td>
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
    
    <script>
      <!-- 保持从上个页面带过来的搜索关键字 -->
      var searchWord = "${searchWord}";
      $("#searchWord").val("${searchWord}");
  </script>
  
  
      <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  	<script src="${contextPath}/static/js/outsourcing/requireList.js?v=${buildTimestamp}"></script> 
</body>
</html>