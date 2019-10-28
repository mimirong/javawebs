<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>搜索</span></h1>

  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="techAchieveSearchModule" ng-controller="techAchieveSearchController">
      <div class="se-filter clearfix">
          <label class="tit">专业领域：</label>
          <div class="conbox">
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT')">生物医疗及器械</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CHEMISTRY')">化学化工</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_METAL_MATERIAL')">金属材料</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL')">电子电气及机械</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_EMC')">电磁兼容（EMC）</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_NDT')">无损检测</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SOFTWARE_INFO')">软件信息</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ENVIRON_HEALTH')">环境检测与职业卫生</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS')">食品及农产品</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSTRUCTION')">建筑材料</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SPIN_FIBER')">纺织及纤维</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSUMER_GOODS')">消费品</a>
              <a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_OTHER')">其他</a>
          </div>
          <a class="show_more" data-id="0">更多</a>
      </div>
      <div class="se-filter clearfix" style="margin-top: -11px;">
          <label class="tit">成果形式：</label>
          <div class="conbox">
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_PRODUCT')">新产品</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_TECH')">新技术</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_CRAFT')">新工艺</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_MATERIAL')">新材料</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'INVENTION_PATENT')">发明专利</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'UTILITY_MODEL')">实用新型</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'APPEARANCE_DESIGN')">外观设计</a>
              <a href="javascript:;" ng-click="achieveTypeSelected($event, 'TECH_SECRET')">技术秘密</a>
          </div>
      </div>
      <div class="se-filter clearfix" style="margin-top: -11px;">
          <label class="tit">投资金额：</label>
          <div class="conbox">
              <a href="javascript:;" ng-click="investmentValumeScopeSelected($event, 'lt_10')">10万元以下</a>
              <a href="javascript:;" ng-click="investmentValumeScopeSelected($event, '10_50')">10~50万元</a>
              <a href="javascript:;" ng-click="investmentValumeScopeSelected($event, '50_100')">50~100万元</a>
              <a href="javascript:;" ng-click="investmentValumeScopeSelected($event, 'gt_100')">100万元以上</a>
          </div>
      </div>
      <ul class="se-result-list se-search-result clearfix">
          <li ng-repeat="row in page.data">
              <a href="${contextPath}/outsourcing/techAchieveDetail?achieveId={{row.achieveId}}">
                  <img ng-src={{row.imgSrc}} alt=""/>
                  <span class="wh" title="{{row.name}}">{{row.name}}</span>
                  <p>投资额：<em class="red">{{row.investmentVolume}}</em>{{row.monetaryUnit}}</p>
              </a>
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
  <script src="${contextPath}/static/js/outsourcing/outsourcing_techAchieveSearch.js?v=${buildTimestamp}"></script>
</body>
</html>