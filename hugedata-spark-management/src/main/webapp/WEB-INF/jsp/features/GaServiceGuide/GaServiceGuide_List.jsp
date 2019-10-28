<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>办事指南</title>
	<script>
		currentMenu = "service_guide";
        currentSystem = "INFORMATION";
	</script>
	<style>
		ul.form li label.tit { width:100px; }
		ul.form li input[type="text"] { width:780px; }
		ul.form li textarea { width:780px; }
		ul.form li { clear:both; min-height:32px; }
		.w250 { width:250px }
	</style>
</head>
<body>

	<h2 class="title">办事指南</h2>
		
	<div class="column_tab_con">
        <div class="search-box clearfix">
            <a href="javascript:;" class="btn op-btn right_float btnAdd" title="添加指南">添加</a>
        </div>
		<div class="column_tab_con">
			<table id="data" class="column_tab_table" width="100%">
				<thead>
				<tr>
					<td width="50px">ID</td>
					<td width="100px">所属部门</td>
					<td width="">事项名称</td>
					<td width="85" style="border-top-right-radius: 5px;">操作</td>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	
    <%-- 新增和修改操作 --%>
    <div class="alert" style="display:none;" id="addPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
		<div class="pop-content" style="padding-top:8px;">
			<div class="tab w434"><a href="javascript:;" class="on switchTab1">基本信息</a><a href="javascript:;" class="switchTab2">申请材料</a></div>
			<ul class="form clearfix" style="padding:10px 0; overflow:auto; width:1000px;">
				<li id="first" class="tab1">
					<label class="tit w70"><em>*</em>所属部门：</label>
					<span class="select w250">
						<select id="deptCode">
							<option value="">请选择</option>
							<option value="1">经济发展局</option>
							<option value="2">国土规划局</option>
							<option value="3">工程建设局</option>
							<option value="4">社会事务局</option>
							<option value="5">招商合作局</option>
							<option value="6">财政分局</option>
							<option value="7">办公室</option>
							<option value="8">党群纪检绩效办</option>
						</select>
					</span>
				</li>
				<li class="tab1"><label class="tit w70"><em>*</em>事项名称：</label><input type="text" class="text w800" maxlength="100" id="guideName" /></li>
				<li class="tab1"><label class="tit w70"><em>*</em>事项类型：</label><input type="text" class="text w800" maxlength="100" id="guideType" /></li>
				<li class="tab1"><label class="tit w70">办理对象：</label><input type="text" class="text w800" maxlength="100" id="serviceSubject" /></li>
				<li class="tab1"><label class="tit w70">设立依据：</label><input type="text" class="text w800" maxlength="600" id="according" /></li>
				<li class="tab1" style="height:32px;">
					<label class="tit w70">前置条件：</label><input type="text" class="text" style="float:left; margin-right:16px; width:320px;" maxlength="100" id="precondition" />
					<label class="tit" style="width:100px;">联合办理单位：</label><input type="text" class="text" style="float:left; width:320px;" maxlength="100" id="jointDept" />
				</li>
				<li class="tab1" style="height:32px;">
					<label class="tit w70">法定期限：</label><input type="text" class="text" style="float:left; margin-right:16px; width:320px;" maxlength="100" id="legalTimeLimit" />
					<label class="tit" style="width:100px;">承诺期限：</label><input type="text" class="text" style="float:left; width:320px;" maxlength="100" id="promisedTimeLimit" />
				</li>
				<li class="tab1" style="height:142px;"><label class="tit w70">办理条件：</label><textarea class="text" id="conditions"></textarea></li>
				<%--
				<li class="tab1" style="height:142px;"><label class="tit w70">申请材料：</label><textarea class="text" id="material"></textarea></li>
				--%>
				<li class="tab1" style="height:142px;"><label class="tit w70">办理程序：</label><textarea class="text" id="process"></textarea></li>
				<li class="tab1">
					<label class="tit w70"><em>*</em>是否收费：</label>
					<label class="checkbox" id="isCharge_true" style="float:left; width:40px; line-height:20px; height:20px; margin-top:8px;">是</label>
					<label class="checkbox" id="isCharge_false" style="float:left; width:40px; line-height:20px; height:20px; margin-top:8px;">否</label>
					<label style="float:left; width:182px;">&nbsp;</label>
					<label class="tit w100">收费依据：</label><input type="text" class="text" style="float:left; width:320px;" maxlength="100" id="chargeAccording" />
				</li>
				<li class="tab1"><label class="tit w70">收费标准：</label><input type="text" class="text w800" maxlength="100" id="chargeStandard" /></li>
				<li class="tab1"><label class="tit w70">办理地点：</label><input type="text" class="text w800" maxlength="100" id="address" /></li>
				<li class="tab1"><label class="tit w70">办理时间：</label><input type="text" class="text w800" maxlength="200" id="workTime" /></li>
				<li class="tab1">
					<label class="tit w70">咨询电话：</label><input type="text" class="text" style="float:left; margin-right:16px; width:320px;" maxlength="100" id="telephone" />
					<label class="tit" style="width:100px;">投诉电话：</label><input type="text" class="text" style="float:left; width:320px;" maxlength="100" id="complaintTelephone" />
				</li>
				<li class="tab1">
					<label class="tit w100">办理流程：</label>
					<div id="flowImageWrapper">
						<m:fileUpload inputIdForFileId="flowImageFileId" inputIdForFileName="flowImageFileName" />
					</div>
				</li>
				<li class="tab1">
					<label class="tit w100">上传表格：</label>
					<div id="attachmentWrapper" style="float:left; width:800px;">
						<div id="curAttachments">
						</div>
						<div id="upAttachments">
							<m:fileUpload inputIdForFileId="attachmentFileId" inputIdForFileName="attachmentFileName" 
									callback="onAttachmentUploaded" autoReset="true" />
						</div>
					</div>
				</li class="tab1">
				
				<li class="tab2" style="display:none;">
					<label class="tit w100">申请材料：</label>
					<div style="float:left; width:830px;">
						<div id="attConfigWrapper">
						</div>
						<div style="margin-bottom:12px;">
							<a href="javascript:;" id="btnAddAttConfig" style="color:#107aee;">新增材料</a>
						</div>
					</div>
				</li>
				
				<li style="margin-top:10px;">
					<label class="tit w70">&nbsp;</label>
					<a class="btn" id="btnDoAdd" href="javascript:;">确定</a>
					<a class="btn" id="btnDoModify" href="javascript:;">确定</a>
					<a class="btn btn-gray" onclick="MU.hide(this)" href="javascript:;">取消</a>
				</li>
			</ul>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/GaServiceGuide_List.js?v=${buildTimestamp}"></script>

</body>