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
        <a href="${contextPath}/parkJoin/listGuide">入园服务</a> >
        <span>入园申请</span>
    </h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH" style="padding: 0; width: 1000px;">
    <div class="column_menu">
      <ul class="leftsidebar">
        <li class="menu_tit">政务服务</li>
        <li class="active">
        	<a href="javascript:;" class="drop">入园服务</a>
            <dl style="display: block">
                <dd><a href="${contextPath}/parkJoin/listGuide">入园指南</a></dd>
                <dd class="on"><a href="${contextPath}/parkJoin/apply">入园申请</a></dd>
            </dl>
        </li>
        <li>
        	<a href="javascript:;" class="drop">退园服务</a>
            <dl>
                <dd><a href="${contextPath}/parkQuit/listGuide">退园指南</a></dd>
                <dd><a href="${contextPath}/parkQuit/apply"> 退园申请</a></dd>
            </dl>
        </li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_SITE">场地服务</a></li>
        <li><a href="${contextPath}/outsourcing/articleList?category=GA_FEES">费用服务</a></li>
      </ul>
    </div>
    <div class="column_list">
        <h2 class="title">入园申请<span class="tips"></span></h2>
        <h3 class="tab-tit" style="text-align: center;">长沙市岳麓科技产业园企业入园备案登记表</h3>
        <div class="signup-cont apply-count">
            <div class="row">
                <label class="title w160"><em>*</em>企业名称：</label>
                <input type="text" value="" class="txt w190" id="companyName" maxlength="100" />
                <label class="title w100"><em>*</em>法人代表：</label>
                <input type="text" value="" class="txt w190" id="representative" maxlength="100" />
            </div>
            <div class="row">
                <label class="title w160"><em>*</em>联系方式：</label>
                <input type="text" value="" class="txt w190" id="contact" maxlength="100" />
                <label class="title w100"><em>*</em>手机：</label>
                <input type="text" value="" class="txt w190" id="contactMobile" maxlength="100" />
            </div>
            <div class="row">
                <label class="title w160"><em>*</em>固定电话：</label>
                <input type="text" value="" class="txt w190" id="telephone" maxlength="100" />
            </div>
            <div class="row">
                <label class="title w160"><em>*</em>注册资金：</label>
                <div class="con-span">
	                <span>内资</span>
	                <input type="text" value="" class="txt w80 left_float" id="regCapDomestic" maxlength="100" />
	                <span>（万元），</span>
                </div>
                <div class="con-span">
	                <span>外资</span>
	                <input type="text" value="" class="txt w80" id="regCapForeign" maxlength="100" />
	                <span>（万元）</span>
                </div>
            </div>
            <div class="row">
            	<label class="title w160"><em>*</em>注册地址：</label>
            	<input type="text" value=""  class="txt w520" id="regAddress" />
            </div>
            <div class="row">
            	<label class="title w160"><em>*</em>经营范围：</label>
            	<input type="text" value=""  class="txt w520" id="businessScope" />
           	</div>
            <!--<div class="row row-new">
                <label class="title w160">创业平台：</label>
                <span class="checkbox_area">
                    <label class="checkbox">广发隆平创业服务中心</label>
                </span>
                <span class="checkbox_area">
                    <label class="checkbox">金丹科技创业大厦</label>
                </span>
                <span class="checkbox_area">
                    <label class="checkbox">豪丹生物科技创业园</label>
                </span>

                 <span class="checkbox_area">
                    <label class="checkbox">美地思创业园</label>
                </span>
                 <span class="checkbox_area">
                    <label class="checkbox">行知教学创业园</label>
                </span>
                <span class="checkbox_area">
                    <label class="checkbox">其他</label>
                </span>
            </div>-->
            <div class="row">
                <label class="title w160"><em>*</em>入驻企业联系人：</label>
                <input type="text" value="" class="txt w100" id="companyContactName" maxlength="100" />
                <label class="title w94"><em>*</em>联系电话：</label>
                <input type="text" value="" class="txt w100" id="companyContactTel" maxlength="100" />
                <label class="title w66"><em>*</em>邮箱：</label>
                <input type="text" value="" class="txt w90" id="companyContactEmail" maxlength="90" />
            </div>
            <div class="row">
                <label class="title w160"><em>*</em>创业平台联系人：</label>
                <input type="text" value="" class="txt w100" id="platformContactName" maxlength="100" />
                <label class="title w94"><em>*</em>联系电话：</label>
                <input type="text" value="" class="txt w100" id="platformContactTel" maxlength="100" />
                <label class="title w66"><em>*</em>邮箱：</label>
                <input type="text" value="" class="txt w90" id="platformContactEmail" maxlength="90" />
            </div>
            <div class="row">
                <label class="title w180"><em>*</em>招商合作局项目联系人：</label>
                <input type="text" value="" class="txt w80_1" id="investContactName" maxlength="80" />
                <label class="title w94"><em>*</em>联系电话：</label>
                <input type="text" value="" class="txt w100" id="investContactTel" maxlength="100" />
                <label class="title w66"><em>*</em>邮箱：</label>
                <input type="text" value="" class="txt w90" id="investContactEmail" maxlength="90" />
            </div>
            <div class="row">
	            <label class="title w160"><em>*</em>入驻方意见：</label>
	            <input type="text" value="" class="txt w520" id="companyRemark" maxlength="100" />
            </div>
            <div class="row">
            	<label class="title w160"><em>*</em>招商合作局意见：</label>
            	<input type="text" value="" class="txt w520" id="investRemark" maxlength="100" />
            </div>
            <div class="btn-div res-div" style="text-align:center; margin-left:0;">
            	<a href="javascript:;" class="btn small-btn" id="btnSubmit">提交</a>
            	<a href="javascript:;" class="btn btn-grey small-btn" id="btnReset">重置</a>
            </div>
        </div>

    </div>
  </div>
  <!--content-end-->

	<script src="${contextPath}/static/js/parkJoin/parkJoin_apply.js?v=${buildTimestamp}"></script>

</body>
</html>