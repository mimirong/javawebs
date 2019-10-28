<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/meetingTrainingIndex">会议培训</a> &gt; <span>会议培训详情</span></h1>
  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="meetingTrainingDetailModule" ng-controller="meetingTrainingDetailController">
      <h2 class="se-title se-title-noborder"><span>会议培训详情</span><a onclick="history.go(-1);" class="more">返回</a></h2>
      <div class="se-block se-expert-block clearfix" id="kcjj">
          <img ng-src="{{meetingTrainingDetail.imgSrc}}" width="360" height="270" alt=""/>
          <ul class="se-con">
              <li class="title">{{meetingTrainingDetail.name}}</li>
              <li class="fl w345"><label class="tit">培训类型：</label>{{meetingTrainingDetail.trainingType}}</li>
              <li class="fl w350"><label class="tit">限制人数：</label>{{meetingTrainingDetail.maxPlayers}}</li>
              <li class="fl w345"><label class="tit">报名时间：</label>{{meetingTrainingDetail.registrationTimeStr}} - {{meetingTrainingDetail.registrationDeadlineStr}}</li>
              <li class="fl w350"><label class="tit">讲课形式：</label>{{meetingTrainingDetail.trainingWay}}</li>
              <li class="fl w345"><label class="tit">培训时间：</label>{{meetingTrainingDetail.trainingStartTimeStr}} - {{meetingTrainingDetail.trainingEndTimeStr}}</li>
              <li class="fl w350"><label class="tit">课程价格：</label>
                  <span class="blue" ng-show="meetingTrainingDetail.price < 0 || meetingTrainingDetail.price == 0">免费</span>
                  <span class="blue" ng-show="meetingTrainingDetail.price > 0">{{meetingTrainingDetail.price}}元</span>
              </li>
              <li><label class="tit">报名方式：</label>{{meetingTrainingDetail.registrationWay}}</li>
              <li><label class="tit">适应对象：</label>{{meetingTrainingDetail.adaptObject}}</li>
              <li><label class="tit">培训地点：</label>{{meetingTrainingDetail.address}}</li>
              <li><label class="tit tit1">联系人：{{meetingTrainingDetail.contact}}&nbsp;&nbsp;|&nbsp;&nbsp;联系电话：{{meetingTrainingDetail.phone }}&nbsp;&nbsp;|&nbsp;&nbsp;联系手机：{{meetingTrainingDetail.mobile && meetingTrainingDetail.mobile != '' ? meetingTrainingDetail.mobile : '暂无'}}</label></li>
              <li><label class="tit tit1"> 邮箱：{{meetingTrainingDetail.email}}</label></li>
          </ul>
      </div>
      <div class="se-block se-intro-block" id="kcnr">
          <h2 class="tit">课程内容</h2>
          <div class="con">
              <dl>
                  <dd>{{meetingTrainingDetail.brief}}</dd>
                  <dd ng-show="meetingTrainingDetail.briefFileName != '' && meetingTrainingDetail.briefFileName != null">附件下载：</dd>
                  <a ng-show="meetingTrainingDetail.briefFileName != '' && meetingTrainingDetail.briefFileName != null" ng-href="{{meetingTrainingDetail.briefFileDownloadUrl}}" style="color:#107aee">{{meetingTrainingDetail.briefFileName}}</a>
              </dl>
          </div>
      </div>
      <div class="se-block se-intro-block" id="zysx">
          <h2 class="tit">注意事项</h2>
          <div class="con">
              <dl>
                  <dd>{{meetingTrainingDetail.announcements}}</dd>
                  <dd ng-show="meetingTrainingDetail.annoFileName != '' && meetingTrainingDetail.annoFileName != null">附件下载：</dd>
                  <a ng-show="meetingTrainingDetail.annoFileName != ''  && meetingTrainingDetail.annoFileName != null" ng-href="{{meetingTrainingDetail.announcementsFileDownloadUrl}}" style="color:#107aee">{{meetingTrainingDetail.annoFileName}}</a>
              </dl>
          </div>
      </div>
  </div>
  <!--content-end-->

  <script>
      window.trainingId = "${trainingId}";
  </script>

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_meetingTrainingDetail.js?v=${buildTimestamp}"></script>
</body>
</html>