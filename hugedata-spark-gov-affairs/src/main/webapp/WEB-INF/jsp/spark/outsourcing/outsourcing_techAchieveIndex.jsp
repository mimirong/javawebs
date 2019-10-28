<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>成果转化</span></h1>
  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="techAchieveIndexModule" ng-controller="techAchieveIndexController">
      <div class="se-title-tab clearfix">
          <div class="fl"><a href="${contextPath}/outsourcing/expertsIndex">专家名录</a><a class="on" href="${contextPath}/outsourcing/techAchieveIndex">技术成果</a></div>
          <div class="fr"><span>信息共 <b>{{page.recordsTotal}}</b> 条</span></div>
      </div>
      <div class="se-sidebar">
          <h2 class="tit"><em></em>成果分类</h2>
          <ul class="clearfix">
              <li class="on"><a class="tit" href="javascript:;" ng-click="professionFieldSelected($event, '')"><em class="icons"></em>全部</a></li>
              <li class="subnav"><a class="tit"><em class="icons"></em>专业领域</a>
                  <dl>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT')">生物医疗及器械</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CHEMISTRY')">化学化工</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_METAL_MATERIAL')">金属材料</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL')">电子电气及机械</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_EMC')">电磁兼容（EMC）</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_NDT')">无损检测</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SOFTWARE_INFO')">软件信息</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_ENVIRON_HEALTH')">环境检测与职业卫生</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS')">食品及农产品</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSTRUCTION')">建筑材料</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_SPIN_FIBER')">纺织及纤维</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_CONSUMER_GOODS')">消费品</a></dd>
                      <dd><a href="javascript:;" ng-click="professionFieldSelected($event, 'SP_SERVICE_FIELD_OTHER')">其他</a></dd>
                  </dl>
              </li>
              <li class="subnav"><a class="tit"><em class="icons"></em>成果形式</a>
                  <dl>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_PRODUCT')">新产品</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_TECH')">新技术</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_CRAFT')">新工艺</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'NEW_MATERIAL')">新材料</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'INVENTION_PATENT')">发明专利</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'UTILITY_MODEL')">实用新型</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'APPEARANCE_DESIGN')">外观设计</a></dd>
                      <dd><a href="javascript:;" ng-click="achieveTypeSelected($event, 'TECH_SECRET')">技术秘密</a></dd>
                  </dl>
              </li>
              <li class="subnav"><a class="tit"><em class="icons"></em>投资金额</a>
                  <dl>
                      <dd><a href="javascript:;" ng-click="investmentValumeScopeSelected($event, 'lt_10')">10万元以下</a></dd>
                      <dd><a href="javascript:;" ng-click="investmentValumeScopeSelected($event, '10_50')">10~50万元</a></dd>
                      <dd><a href="javascript:;" ng-click="investmentValumeScopeSelected($event, '50_100')">50~100万元</a></dd>
                      <dd><a href="javascript:;" ng-click="investmentValumeScopeSelected($event, 'gt_100')">100万元以上</a></dd>
                  </dl>
              </li>
          </ul>
      </div>
      <div class="se-col-auto">
          <ul class="se-result-list clearfix">
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
      <br class="clear"/>
  </div>
  <!--content-end-->

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_techAchieveIndex.js?v=${buildTimestamp}"></script>
</body>
</html>