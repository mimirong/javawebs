<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>操作日志查询</title>
	<script>
		currentMenu = "logs/log_operation";
	</script>
</head>
<body>
	<div class="content">
		<div class="tit_lev1 clearfix">
			<span>操作日志查询</span>
		</div>

		<%-- RESULT --%>
		<table id="data" class="table" cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<td style="width:80px;">ID</td>
					<td>操作内容</td>
					<td>操作</td>
					<td>数据</td>
					<td>操作用户</td>
					<td style="width:170px;">操作时间</td>
				</tr>
			</thead>
		</table>
		
	</div>
	
	<script type="text/javascript">
		// Columns
		var columns =[
			{
				data: "logId"
			}, {
				data: "target",
				render: function(target) {
					if (CommonsSymbols.LogOperation_Target[target]) {
						return CommonsSymbols.LogOperation_Target[target];
					} else {
						return target;
					}
				}
			}, {
				data: "operation",
				render: function(op) {
					if (CommonsSymbols.LogOperation_Operation[op]) {
						return CommonsSymbols.LogOperation_Operation[op];
					} else {
						return op;
					}
				}
			}, {
				data: "targetInfo"
			}, {
				data: "username"
			}, {
				data: "createTime",
				render: function(val) {
					return DateFormat.format.date(new Date(val), 'yyyy/MM/dd HH:mm:ss');
				}
			}
		];
			
		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
  			ajax: {
  				url: "listData",
  				data: function(data) {
				}
  			},
			lengthChange: false,
			serverSide : true,
			searching : false,
			processing: true,
			order: [[0, "desc"]],
			columns : columns
		}));
		
		// 绑定事件
		ListFeaturePage.autobind(dt);
		
	</script>

</body>