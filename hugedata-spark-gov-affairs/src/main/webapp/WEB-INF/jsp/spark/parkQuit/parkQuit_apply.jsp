<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>
		.signup-cont .row em { background-image:none; padding-left:0; margin:0 5px 0 0; }
	</style>
</head>
<body>

  <h1 class="column_tit">您当前位置：
    <a href="${contextPath}/">首页</a> > 
    <a href="${contextPath}/integrated/index">综合服务</a> >
	<a href="${contextPath}/parkJoin/listGuide">政务服务</a> >
    <a href="${contextPath}/parkQuit/listGuide">退园服务</a> >
  	<span>退园申请</span>
  </h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
        <li class="menu_tit">政务服务</li>
        <li>
        	<a href="javascript:;" class="drop">入园服务</a>
            <dl>
                <dd><a href="${contextPath}/parkJoin/listGuide">入园指南</a></dd>
                <dd><a href="${contextPath}/parkJoin/apply">入园申请</a></dd>
            </dl>
        </li>
        <li class="active">
        	<a href="javascript:;" class="drop">退园服务</a>
            <dl style="display: block">
                <dd><a href="${contextPath}/parkQuit/listGuide">退园指南</a></dd>
                <dd class="on"><a href="${contextPath}/parkQuit/apply">退园申请</a></dd>
            </dl>
        </li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_SITE">场地服务</a></li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_FEES">费用服务</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">退园申请<span class="tips"></span></h2>
        <h3 class="tab-tit" style="text-align: center;">长沙市岳麓科技产业园企业退园申请</h3>
        <div class="signup-cont apply-count">
            <div class="row">
                <label class="title w160"><em>*</em>企业名称：</label>
                <input type="text" value="" class="txt w190" maxlength="100" id="companyName" />
                <label class="title w100"><em>*</em>法人代表：</label>
                <input type="text" value="" class="txt w190" maxlength="100" id="representative" />
            </div>
            <div class="row">
                <label class="title w160"><em>*</em>联系方式：</label>
                <input type="text" value="" class="txt w190" maxlength="100" id="contact" />
                <label class="title w100"><em>*</em>退园日期：</label>
                <input type="text" value="" class="txt w190" maxlength="100" id="quitDate" />
            </div>
            <div class="btn-div res-div" style="text-align:center; margin-left:0;">
            	<a href="javascript:;" class="btn small-btn" id="btnSubmit">提交</a>
            	<a href="javascript:;" class="btn btn-grey small-btn" id="btnReset">重置</a>
            </div>
        </div>

    </div>
  </div>
  <!--content-end-->

	<script src="${contextPath}/static/js/parkQuit/parkQuit_apply.js?v=${buildTimestamp}"></script>

</body>
</html>