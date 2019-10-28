<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
</head>
<body>

<h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>个人中心</span></h1>
<!--content-start-->
<div class="column_con clearfix" id="minH">
    <h2 class="title">个人中心</h2>
    <div class="tab"><a href="${contextPath}/user/userCenter" class="on">注册信息</a><a href="${contextPath}/messages/list">消息通知<span id="unreadMessageIndicator" class="red-dot" style="display:none"></span></a></div>
    <div class="signup-cont signup-cont1">
        <div class="row"><label class="title">单位名称：</label><span>${company.companyName}</span></div>
        <div class="row"><label class="title">单位类型：</label><span>${companyTypeName}</span></div>
        <div class="row"><label class="title">单位地址：</label><span>${company.companyAddress}</span></div>
        <div class="row"><label class="title">组织机构代码：</label><span>${company.organizationCode}</span></div>
        <div class="row"><label class="title">营业执照号码：</label><span>${company.licenceCode}</span></div>
        <div class="row"><label class="title">法人代表：</label><span>${company.representative}</span></div>
        <div class="row"><label class="title">法人身份证号码：</label><span>${company.representativeId}</span></div>
        <div class="row"><label class="title">联系人：</label><span>${company.contactName}</span></div>
        <div class="row"><label class="title">联系人电话：</label><span>${company.contactMobile}</span></div>
        <div class="row"><label class="title">联系人邮箱：</label><span>${company.contactEmail}</span></div>
        <%--
        <div class="row"><label class="title">密码：</label><span>sdf****22</span></div>
        --%>
        <div class="btn-div res-div">
        	<a class="btn" href="${contextPath}/user/modifyUserInfo">修改</a>
       	</div>
    </div>
</div>

<script src="${contextPath}/static/js/user/user_center.js?v=${buildTimestamp}"></script>
</body>
</html>