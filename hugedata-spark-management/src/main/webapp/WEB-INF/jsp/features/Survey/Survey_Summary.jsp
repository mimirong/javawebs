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
		<h2 class="title">
			调查征集结果-${title}<a href="javascript:history.go(-1)" class="return-btn">返回
				>></a>
		</h2>
		<div class="column_tab_con">
		</div>
		<div class="alert" style="display: none; width: 850px" id="detailPanel"
			name="wh_alert">
			<div class="title">
				<a href="javascript:;" onclick="MU.hide(this)"></a><span
					class="detailPanelTitle"></span>
			</div>
			<div class="pop-content">
			    <div style="max-height: 500px; overflow-y: auto;">
			        <table id="data" class="column_tab_table">
			            <thead>
			                <tr><td width="500">提交文本</td><td width="200">提交日期</td></tr>
			            </thead>
			        </table>
			    </div>
			</div>
		</div>	
		<script>
			var surveyId = ${surveyId};
		</script>
		<script type="text/javascript"
			src="${contextPath}/static/js/spark/Survey_Summary.js?v=${buildTimestamp}"></script>
</body>