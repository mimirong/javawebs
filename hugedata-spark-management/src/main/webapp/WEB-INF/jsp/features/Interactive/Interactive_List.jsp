<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>领导信箱</title>
<script>
	currentMenu = "it_service";
	currentSystem = "IT";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">领导信箱</h2>
		<div class="column_tab_con">
			<div class="search-box clearfix">
				<a class="btn op-btn right_float" title="批量忽略">批量忽略</a>
			</div>
			<table id="data" class="column_tab_table">
				<thead>
					<tr>
						<td style="width:30px;"><span class="checkbox js-all-selected"></span></td>
						<td>标题</td>
						<td style="width:120px;">写信人</td>
						<td style="width:70px;">类型</td>
						<td style="width:70px;">状态</td>
						<td style="width:90px;">提交时间</td>
						<td style="width:90px;">回复时间</td>
						<td style="width:85px">操作</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<%-- 回复操作 --%>
	<div class="alert" style="display: none; width: 800px" id="replyPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="replyPanelTitle"></span></div>
		<div class="pop-content" style="">
		    <ul class="form clearfix">
		        <li class="clearfix"><label class="tit w104">标题：</label><span  id="title" style="float:right;width:618px;margin-right:70px"></span></li>
		        <li class="clearfix"><label class="tit w104">内容：</label><textarea id="content" disabled="true" cols="30" rows="10" readonly="true"></textarea></li>
		        <li class="clearfix"><label class="tit w104"><em>*</em>回复：</label><textarea id="replyContent" name="" cols="30" rows="10"></textarea></li>
		        <li class="clearfix"><label class="tit w104"><em>*</em>回复单位：</label><input id="replierCompany" type="text" class="text w596" value="" maxlength="80"/></li>
		    </ul>
		    <div style="text-align: center;padding:10px 0;"><a id="btnDoModify" href="javascript:;" class="rp_submit btn">回复</a></div>
		</div>
	</div>


<script type="text/javascript" src="${contextPath}/static/js/spark/Interactive_List.js?v=${buildTimestamp}"></script>

</body>