<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>企业帐号管理</title>
	<script>
		currentMenu = "company_account";
		currentSystem = "ACCOUNTS";
	</script>
</head>
<body>

	<h2 class="title">企业帐号管理</h2>
	
	<div class="column_tab_con">
        <div class="filter-box">
            <label class="tit">用户名：</label>
            <input type="text" class="txt mr20 w118 queryItem" name="like_loginName" />
            <label class="tit">企业名称：</label>
            <input type="text" class="txt mr20 w118 queryItem" name="like_companyName" />
            <label class="tit">联系人电话：</label>
            <input type="text" class="txt mr20 w118 queryItem" name="like_mobile" />
            <label class="tit">邮箱：</label>
            <input type="text" class="txt w118 queryItem" name="like_email" />
            <a href="javascript:;" class="btn btnQuery">查询</a>
        </div>
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
					<td width="70" style="border-top-left-radius: 5px;">用户名</th>
					<td width="100">企业名称</th>
					<td width="">邮箱</th>
					<td width="100">联系人电话</th>
					<td width="60">是否可用</th>
					<td width="80">创建日期</th>
					<td width="210" style="border-top-right-radius: 5px;">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <%-- 详情 --%>
    <div class="alert" style="display:none; width:560px;" id="detailsPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a>注册账号信息</div>
		<div class="pop-content" style="padding: 30px 0;">
		    <ul class="form clearfix">
		        <li class="clearfix"><label class="tit"><em>*</em>单位名称：</label><span class="showCompanyName"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>单位类型：</label><span class="showCompanyType"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>单位地址：</label><span class="showCompanyAddress"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>组织机构代码：</label><span class="showOrganizationCode"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>营业执照号码：</label><span class="showLicenceCode"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>法人代表：</label><span class="showRepresentative"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>法人身份证号码：</label><span class="showRepresentativeId"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>联系人：</label><span class="showContactName"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>联系人电话：</label><span class="showContactMobile"></span></li>
		        <li class="clearfix"><label class="tit"><em>*</em>联系人邮箱：</label> <span class="showContactEmail"></span></li>
		    </ul>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/UcCompanyUser_List.js?v=${buildTimestamp}"></script>
</body>