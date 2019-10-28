<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

  <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/expertsIndex">成果转化</a> &gt; <span>专家详情</span></h1>
  <!--content-start-->
  <div class="cen ng-cloak" id="minServiceH" ng-app="expertDetailModule" ng-controller="expertDetailController">
      <h2 class="se-title se-title-noborder"><span>专家详情</span><a onclick="history.go(-1);" class="more">返回</a></h2>
      <div class="se-block se-expert-block clearfix">
          <img ng-src="{{expertDetail.imgSrc}}" width="140" height="170" alt=""/>
          <ul class="se-con" style="width:400px">
              <li class="title">{{expertDetail.name}}&nbsp;</li>
              <li><label class="tit">职称：</label>{{expertDetail.jobTitle}}</li>
              <li><label class="tit">部门：</label>{{expertDetail.dept}}</li>
              <li><label class="tit">职务：</label>{{expertDetail.position}}</li>
          </ul>
          <ul class="se-con" style="margin: 40px 0 0 100px; width:300px">
              <li><label class="tit">专业领域：</label>{{expertDetail.professionField}}</li>
              <li><label class="tit">擅长领域：</label>{{expertDetail.expertField}}</li>
              <li><label class="tit">专家称号：</label>{{expertDetail.expertTitle}}</li>
          </ul>
      </div>
      <div class="se-block se-intro-block">
          <h2 class="tit">个人简介</h2>
          <div class="con">
              <p>{{expertDetail.brief}}</p>
          </div>
      </div>
  </div>
  <!--content-end-->

  <script>
      window.expertId = "${expertId}";
  </script>

  <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/outsourcing_expertDetail.js?v=${buildTimestamp}"></script>
</body>
</html>