<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit">
<title>注册</title>
<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css" />
<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css" />
<style type="text/css">
a.btn:visited{color:#fff;}
a.btn:hover{color:#fff;text-decoration:none;}
</style>
</head>
<body>
<!--banner-start-->
<div class="banner-container">
  <a href="${govAffairsPortalUrl}/" class="logo1"><img src="${contextPath}/static/web2/public/images/logo1.png" alt="岳麓科技产业园"/></a>
    <div class="res-menu">
        <a href="${govAffairsPortalUrl}/">首页</a>
        <a href="${govAffairsPortalUrl}/serviceGuide1/list">办事服务</a>
        <a href="${govAffairsPortalUrl}/outsourcing/index">服务外包</a>
        <a href="${govAffairsPortalUrl}/pmProject/list">项目管理</a>
        <a href="${govAffairsPortalUrl}/notice/index">通知公告</a>
        <a href="${govAffairsPortalUrl}/handyService/index">便民服务</a>
        <a href="${govAffairsPortalUrl}/interactive/list">互动交流</a>
    </div>
  <div class="right_float"> <a href="${contextPath}/login">登录</a></div>
</div>

<!--banner-end-->
<!--content-start-->
<div class="res-content clearfix" style="margin-top:60px;margin-bottom: 60px;">
	<div class="signup-cont">
		<h2>注册</h2>
		<div class="row">
			<label class="title"><i>*</i>单位名称：</label>
			<input type="text" value="" id="companyName" class="txt" maxlength="100" />
			<em><i id="companyName_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>单位类型：</label>
			<span id="companyTypeSuper" class="select w262" style="float:left;">
				<select id="companyType">
                    <option value="">请选择</option>
                    <option value="1">行政单位</option>
                    <option value="2">事业单位</option>
                    <option value="3">企业单位</option>
                    <option value="4">军事单位</option>
                    <option value="5">其他单位</option>
                    <option value="6">个人</option>
                </select>
            </span>
			<em><i id="companyType_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>单位地址：</label>
			<input type="text" value="" id="companyAddress" class="txt" maxlength="100" />
			<em><i id="companyAddress_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>组织机构代码：</label>
			<input type="text" value="" id="organizationCode" class="txt" maxlength="100" />
			<em><i id="organizationCode_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>营业执照号码：</label>
			<input type="text" value="" id="licenceCode" class="txt" maxlength="100" />
			<em><i id="licenceCode_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>法人代表：</label>
			<input type="text" value="" id="representative" class="txt" maxlength="100" />
			<em><i id="representative_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>法人身份证号码：</label>
			<input type="text" value="" id="representativeId" class="txt" maxlength="100" />
			<em><i id="representativeId_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>联系人：</label>
			<input type="text" value="" id="contactName" class="txt" maxlength="100" />
			<em><i id="contactName_error"></i></em>
			</div>
		<div class="row">
			<label class="title"><i>*</i>联系人电话：</label>
			<input type="text" value="" id="contactMobile" class="txt" maxlength="100" />
			<em><i id="contactMobile_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>联系人邮箱：</label>
			<input type="text" value="" id="contactEmail" class="txt" maxlength="200" />
			<em><i id="contactEmail_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>用户名：</label>
			<input type="text" value="" id="username" class="txt" maxlength="200" />
			<em><i id="username_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>密码：</label>
			<input type="password" value="" id="password" class="txt" />
			<em><i id="password_error"></i></em>
		</div>
		<div class="row">
			<label class="title"><i>*</i>确认密码：</label>
			<input type="password" value="" id="password2" class="txt" />
			<em><i id="password2_error"></i></em>
		</div>
		<div class="btn-div res-div">
			<a class="btn btnSignUp" href="javascript:;">注册</a>
			<a class="btn btn-grey" href="${contextPath}/">取消</a>
		</div>
		<div class="res-login">已有账号？&nbsp;<a href="${contextPath}/login">立即登录</a></div>
	</div>
	<div style="float:left;padding:20px 0 0 20px;">
		<img src="${contextPath}/static/web2/public/images/register.png" alt="" />
	</div>
	<!--<div class="restip">建议使用IE8以上浏览器（不含IE8）浏览网站<a class="close-btn"></a></div>-->
</div>
<!--content-end-->

<div class="login_footer">
    <div class="cen">
        <div class="login_footer_top">
            <ul class="left_float">
                <li>
                    <h1>友情链接</h1>
                    <p><a href="http://www.hnxjxq.gov.cn/" target="_blank">湘江新区</a></p>
                    <p><a href="http://www.changsha.gov.cn/" target="_blank">中国长沙</a></p>
                    <p><a href="http://www.yuelu.gov.cn/" target="_blank">中国岳麓</a></p>
                    <p><a href="http://www.hunan.gov.cn/" target="_blank">湖南省政府网</a></p>
                </li>
            </ul>
            <img src="${contextPath}/static/web2/public/images/code1.png" style="margin-right: 105px;" />
            <%--
            <img src="${contextPath}/static/web2/public/images/code2.png" />
            --%>
            <ul class="right_float">
                <li>
                    <p>联系电话：0731-88532200</p>
                    <p>版权所有：长沙市岳麓科技产业园</p>
                    <p>备案号：湘ICP备10202297号-1</p>
                    <p>技术支持：湖南网数科技有限公司</a></p>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="foot">
    <div class="cen">
        建议使用IE8以上浏览器（不含IE8）浏览网站, 技术支持：湖南网数科技有限公司
    </div>
</div>
<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/web2/public/js/selectList.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/js/signup.js?v=${buildTimestamp}"></script>
</body>
</html>