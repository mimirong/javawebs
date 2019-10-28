<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>调查征集</title>
<script>
	currentMenu = "interactive";
	currentSystem = "IT";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">调查征集管理</h2>
		<div class="column_tab_con">
			<div class="search-box clearfix">
				<a class="btn op-btn right_float btnDeleteSelected" title="删除调查">删除调查</a> <a
					href="javascript:;"
					class="btn op-btn right_float add-survey" title="新增调查">新增调查</a>
			</div>
			<table id="data" class="column_tab_table">
				<thead>
					<tr>
						<td style="width:24px;"><label class="checkbox btnSelectAll"></label></td>
						<td style="width:60px;">调查ID</td>
						<td>标题</td>
						<td style="width:100px;">开始时间</td>
						<td style="width:100px;">结束时间</td>
						<td style="width:80px;">提交数</td>
						<td style="width:80px;">是否发布</td>
						<td style="width:240px; text-align:center;">操作</td>
					</tr>
				</thead>
			</table>
		</div>
		<div class="alert" style="display: none; width: 800px" id="addPanel"
			name="wh_alert">
			<div class="title">
				<a href="javascript:;" onclick="MU.hide(this)"></a><span
					class="addPanelTitle"></span>
			</div>
			<div class="pop-content" style="">
				<ul class="form clearfix">
					<li class="clearfix"><label class="tit w104"><em>*</em>调查标题：</label><input
						id="title" type="text" class="text w596" placeholder="输入标题（限字200）"maxlength="200" value="" /></li>
					<li class="clearfix"><label class="tit w104">摘要：</label>
					<textarea id="brief" name="" cols="30" rows="10"></textarea></li>
					<li class="clearfix"><label class="tit w104"><em>*</em>来源：</label><input
						id="source" type="text" class="text w596" placeholder="输入调查来源（限字100）" maxlength="100" value="" /></li>
					<li class="clearfix"><label class="tit w104"><em>*</em>开始时间：</label><input
						id="startTime" type="text" class="text w596 dateInput"
						placeholder="选择调查开始时间" value="" /></li>
					<li class="clearfix"><label class="tit w104"><em>*</em>结束时间：</label><input
						id="endTime" type="text" class="text w596 dateInput"
						placeholder="选择调查结束时间" value="" /></li>
				</ul>
				<div style="text-align: center; padding: 10px 0;">
					<a href="javascript:;" id="btnDoAdd" class="btn"></a>
				</div>
			</div>
		</div>
    <script type="text/javascript" src="${contextPath}/static/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/spark/Survey_List.js?v=${buildTimestamp}"></script>
</body>