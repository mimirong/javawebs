<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>会议培训</span></h1>
  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="meetingTrainingIndexModule" ng-controller="meetingTrainingIndexController">
      <div class="se-filter clearfix">
          <label class="tit">热门分类：</label>
          <div class="conbox">
              <a class="on" href="javascript:;" ng-click="trainingTypeSelected($event, '')">全部</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'SPECIALTY_TECH')">专业技术</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'TECH_POLICY')">科技政策</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'AUTHENTICATION')">认证认定</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'OCCUPATIONAL_SKILLS')">职业技能</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'OCCUPATIONAL_QUALIFICATIONS')">职业资格</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'TECH_MANAGEMENT')">科技管理</a>
              <a href="javascript:;" ng-click="trainingTypeSelected($event, 'OTHERS')">其他</a>
          </div>
      </div>
      <div class="se-filter clearfix" style="margin-top: -11px;">
          <label class="tit">课程状态：</label>
          <div class="conbox">
              <a class="on" href="javascript:;"  ng-click="outOfDateSelected($event, '')">全部</a>
              <a href="javascript:;" ng-click="outOfDateSelected($event, '1')">可报名</a>
              <a href="javascript:;" ng-click="outOfDateSelected($event, '2')">已结束</a>
          </div>
      </div>
      <ul class="se-list">
      	 <li class="nothing" ng-if="page.data.length == 0 ">
               <p class="tips"><em></em>找不到您查询的相关信息</p>
            </li>
          <li class="h150" ng-repeat="row in page.data">
              <table class="se-table se-meeting-table">
                  <tr>
                      <td width="220"><a href="${contextPath}/outsourcing/meetingTrainingDetail?trainingId={{row.trainingId}}"><img  ng-src={{row.imgSrc}} alt=""/></a></td>
                      <td width="" valign="top">
                          <div class="info">
                              <h2><a href="${contextPath}/outsourcing/meetingTrainingDetail?trainingId={{row.trainingId}}" class="wh" style="width: 700px;" title="{{row.name}}">{{row.name}}</a></h2>
                              <span class="tag" ng-show="row.price < 0 || row.price == 0">免费</span>
                              <span class="tag" ng-show="row.price > 0">{{row.price}}元</span>
                              <p><em class="time"></em>培训时间：{{row.trainingStartTimeStr}} - {{row.trainingEndTimeStr}}</p>
                              <p><em class="address"></em>培训地点：{{row.address}}</p>
                          </div>
                      </td>
                      <td width="130">
                          <a class="btn-green" style= "cursor:default"  ng-show="row.announcements == '1'">可报名</a>
                          <a class="btn-finish" style= "cursor:default"  ng-show="row.announcements == '2'">已结束</a>
                      </td>
                  </tr>
              </table>
          </li>
      </ul>
      <div class="page_v1">
          <span class="total">共<em>{{page.pageCount}}</em>页</span>
          <span class="ell" ng-if="pageButtons[0] != 1">...</span>
          <ng-repeat ng-repeat="p in pageButtons">
              <a target="_self" href="javascript:;" ng-if="p!=page.page" ng-click="gotoPage(p)">{{p}}</a>
              <span class="cur" ng-if="p==page.page">{{p}}</span>
          </ng-repeat>
          <span class="ell" ng-if="pageButtons[pageButtons.length - 1] != page.pageCount">...</span>
          <a target="_self" href="javascript:;" ng-click="gotoPage(page.hasNext ? page.page+1 : page.page)">下一页</a>
          <span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span>
          <a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
      </div>
  </div>

  <script>
      <!-- 保持从上个页面(会议培训详情页)带过来的搜索关键字 -->
      var searchWord = "${searchWord}";
      $("#searchWord").val("${searchWord}");
  </script>
  <!--content-end-->

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_meetingTrainingIndex.js?v=${buildTimestamp}"></script>
</body>
</html>