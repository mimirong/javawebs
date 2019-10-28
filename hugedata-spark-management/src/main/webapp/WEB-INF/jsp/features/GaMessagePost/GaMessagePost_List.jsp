<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>信息送报</title>
<script>
	currentMenu = "message_post";
	currentSystem = "IT";
</script>
<script>
	
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">信息送报</h2>
		<div class="column_tab_con">
			<div class="filter-box">
				<label class="tit">送报地点：</label><input type="text"
					class="txt mr20 queryItem" name="like_postAddr"><label
					class="tit">送报人姓名：</label><input type="text"
					class="txt mr20 queryItem" name="like_posterName"><label
						class="tit">送报人电话：</label><input type="text"
						class="txt queryItem" name="like_posterMobile"><a
							href="javascript:;" class="btn" id="btnSearch">查询</a>
			</div>
			<table id="data" class="column_tab_table" width="100%">
				<thead>
					<tr>
						<td width="33%">事件描述</td>
						<td width="10%">重要级别</td>
						<td width="12%">发生时间</td>
						<td width="20%">送报地点</td>
						<td width="10%">送报人姓名</td>
						<td width="15%">送报人电话</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	 


	<script type="text/javascript" src="${contextPath}/static/js/spark/GaMessagePost_List.js?v=${buildTimestamp}"></script>

</body>