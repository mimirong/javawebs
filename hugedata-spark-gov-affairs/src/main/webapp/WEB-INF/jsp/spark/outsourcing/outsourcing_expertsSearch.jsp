<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>搜索</span></h1>

  <!--content-start-->
  <div class="cen" id="minServiceH" ng-app="expertSearchModule" ng-controller="expertSearchController">
      <div class="se-filter clearfix">
          <label class="tit">专家技术分类：</label>
          <div class="conbox">
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT')"><em class="icons"></em>生物医疗及器械</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CHEMISTRY')"><em class="icons"></em>化学化工</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_METAL_MATERIAL')"><em class="icons"></em>金属材料</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL')"><em class="icons"></em>电子电气及机械</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_EMC')"><em class="icons"></em>电磁兼容（EMC）</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_NDT')"><em class="icons"></em>无损检测</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SOFTWARE_INFO')"><em class="icons"></em>软件信息</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ENVIRON_HEALTH')"><em class="icons"></em>环境检测与职业卫生</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS')"><em class="icons"></em>食品及农产品</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSTRUCTION')"><em class="icons"></em>建筑材料</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SPIN_FIBER')"><em class="icons"></em>纺织及纤维</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSUMER_GOODS')"><em class="icons"></em>消费品</a>
              <a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_OTHER')"><em class="icons"></em>其他</a>
          </div>
          <a class="show_more" data-id="0">更多</a>
      </div>
      <ul class="se-expert-list se-search-expert clearfix">
          <li ng-repeat="row in page.data">
              <a href="${contextPath}/outsourcing/expertDetail?expertId={{row.expertId}}"><img ng-src={{row.imgSrc}} alt=""/></a>
              <h2 class="tit"><a href="${contextPath}/outsourcing/expertDetail?expertId={{row.expertId}}"><em></em>{{row.name}}</a></h2>
              <dl>
                  <dd class="wh">职称：{{row.jobTitle}}</dd>
                  <dd class="wh">部门：{{row.dept}}</dd>
                  <dd class="wh">职务：{{row.position}}</dd>
                  <dd class="wh">擅长领域：{{row.expertField}}</dd>
              </dl>
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
      <!-- 保持从上个页面带过来的搜索关键字 -->
      var searchWord = "${searchWord}";
      $("#searchWord").val("${searchWord}");
  </script>
  <!--content-end-->

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_expertSearch.js?v=${buildTimestamp}"></script>
</body>
</html>