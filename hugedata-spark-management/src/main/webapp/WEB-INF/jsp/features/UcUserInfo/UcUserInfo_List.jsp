<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>内部帐号管理</title>
	<script>
		currentMenu = "park_account";
		currentSystem = "ACCOUNTS";
	</script>
</head>
<body>

	<h2 class="title">内部账号管理
		<a href="javascript:;" class="btn op-btn right_float op-add btnAdd" title="添加用户">添加用户</a>
	</h2>

	<div class="column_tab_con">
        <div class="filter-box">
            <label class="tit">用户名：</label>
            <input type="text" class="txt mr20 queryItem" name="like_loginName" style="width:160px;" />
            <label class="tit">部门：</label>
			<span class="select mr20" style="width:160px;">
				<select class="queryItem" name="deptCode" id="deptQuerySelect"></select>
			</span>
            <label class="tit">职位：</label>
			<span class="select" style="width:160px;">
				<select class="queryItem" name="serviceRole">
					<option value="">--请选择--</option>
					<option value="WINDOW">窗口</option>
					<option value="DEPUTY_DIRECTOR">副局长</option>
					<option value="DIRECTOR">局长</option>
					<option value="DEPUTY_CHIEF">副主任</option>
					<option value="CHIEF">主任</option>
					<option value="SECRETARY_GENERAL">书记</option>
				</select>
			</span>
            <a href="javascript:;" class="btn btnQuery">查询</a>
        </div>
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
					<td width="80" style="border-top-left-radius: 5px;">序号</th>
					<td width="130">用户名</th>
					<td width="130">真实姓名</th>
					<td width="90">所属部门</th>
					<td width="90">职位</th>
					<td width="">联系电话</th>
					<td width="120" style="border-top-right-radius: 5px;">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <%-- 新增和修改操作 --%>
    <div class="alert" style="display:none; width:560px;" id="addPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
		<div class="pop-content" style="padding: 30px 0;">
			<form>
				<ul class="form clearfix">
					<li>
						<label class="tit w70">用户名：</label>
						<input type="text" class="text w300" maxlength="100" id="loginName" autocomplete="off" />
					</li>
					<li>
						<label class="tit w70">密码：</label>
						<input type="password" style="display:none;" id="password_00" name="password" />
						<input type="password" class="text w300" maxlength="100" id="password" style="height:30px;" autocomplete="off" />
					</li>
					<li>
						<label class="tit w70">真实姓名：</label>
						<input type="text" class="text w300" maxlength="100" id="name" />
					</li>
					<li>
						<label class="tit w70">所属部门：</label>
						<span class="select" style="width:302px;" id="deptSelectWrapper">
							<div class="select-input" title="请选择">请选择</div>
							<ul class="select-list" style="display:none; height:200px; padding-top:10px;">
							</ul>
						</span>
					</li>
					<li>
						<label class="tit w70">职位：</label>
						<span class="select" style="width:302px;">
							<select id="serviceRole">
								<option value="">--请选择--</option>
								<option value="WINDOW">窗口</option>
								<option value="DEPUTY_DIRECTOR">副局长</option>
								<option value="DIRECTOR">局长</option>
								<option value="DEPUTY_CHIEF">副主任</option>
								<option value="CHIEF">主任</option>
								<option value="SECRETARY_GENERAL">书记</option>
							</select>
						</span>
					</li>
					<li>
						<label class="tit w70">联系电话：</label>
						<input type="text" class="text w300" maxlength="100" id="mobile" />
					</li>
					<li>
						<label class="tit w70">&nbsp;</label>
						<a class="btn" id="btnDoAdd" href="javascript:;">确定</a><a class="btn" id="btnDoModify" href="javascript:;">确定</a><a class="btn btn-gray" onclick="MU.hide(this)" href="javascript:;">取消</a>
					</li>
				</ul>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/UcUserInfo_List.js?v=${buildTimestamp}"></script>

</body>