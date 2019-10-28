<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/expertsIndex">成果转化</a> &gt; <span>技术成果详情</span></h1>
  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="techAchieveDetailModule" ng-controller="techAchieveDetailController">
      <h2 class="se-title se-title-noborder"><span>技术成果展示详情</span><a onclick="history.go(-1);" class="more">返回</a></h2>
      <div class="se-block se-expert-block clearfix">
          <img ng-src="{{achieveDetail.imgSrc}}" width="270" height="240" alt=""/>
          <div class="result-con fl">
              <ul class="se-con">
                  <li class="title">{{achieveDetail.name}}</li>
                  <li class="fl w345"><label class="tit">所属领域：</label>{{achieveDetail.professionField}}</li>
                  <li class="fl w350"><label class="tit">技术成熟度：</label><span class="blue ora">{{achieveDetail.maturityStage}}</span></li>
                  <li><label class="tit">成果形式：</label>{{achieveDetail.achieveType}}</li>
              </ul>
          </div>
          <div class="res-con">
              <div class="price">
                  <span>投资金额: <em>{{achieveDetail.investmentVolume}}<i>{{achieveDetail.monetaryUnit}}</i></em></span>
              </div>
              <h3><span>联系人：{{achieveDetail.contact}}</span><span>电话：{{achieveDetail.phone}}</span></h3>
          </div>
      </div>
      <div class="se-block se-intro-block">
          <h2 class="tit">成果详述</h2>
          <div class="con">
              <dl>
                  <dt>方案详述</dt>
                  <dd>{{achieveDetail.brief}}</dd>
                  <dt>预期效果</dt>
                  <dd>{{achieveDetail.desiredEffect}}</dd>
                  <dt>适应对象</dt>
                  <dd>{{achieveDetail.adaptObject}}</dd>
                  <dt>合作方式</dt>
                  <dd>{{achieveDetail.cooperationWays}}</dd>
              </dl>
          </div>
      </div>
  </div>
  <!--content-end-->

  <script>
      window.achieveId = "${achieveId}";
  </script>

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_techAchieveDetail.js?v=${buildTimestamp}"></script>
</body>
</html>