<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作统计</title>
<script>
	currentMenu = "approval_stat";
	currentSystem = "APPROVAL";
</script>
<style>
	.text { position:absolute; text-align:center; }
	.text .a { font-size:18px; color:#59676B; margin-top:120px; }
	.text .b { font-size:44px; color:#59676B; margin-top:12px; }
	.nodata p { font-size:12px; color:#59676B; }
</style>
</head>
<body>

	<div class="">
		<h2 class="title">工作统计</h2>
		<div class="column_tab_con" id="chartWrapper">
			<div class="filter-box">
				<label class="tit">时段选择：</label>
				<input type="text" class="txt date dateInput" id="beginDate" value="">
				<span style="margin: 0 10px;">-</span>
				<input type="text" class="txt date dateInput" id="endDate" value="">
				<a href="javascript:;" class="btn btnReload">确认</a>
			</div>
			
		</div>
	</div>


	<script type="text/javascript">
	</script>
	<script type="text/javascript" src="${contextPath}/static/js/echarts.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/spark/ApprovalStat_Index.js?v=${buildTimestamp}"></script>

</body>