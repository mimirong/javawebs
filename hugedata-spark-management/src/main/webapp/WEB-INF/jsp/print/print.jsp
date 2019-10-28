<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="decorator" content="simple">
	<style>
		.noprintshow { display:none; }
	
		@media print {
			.noprint { display:none; }
			body { background-color:white; }
			.column_con { border:none; }
			
			@page {
				size: ${param.pagesize};
			}
		}
	</style>
</head>
<body>
	<div class="column_con clearfix" style="width:95%; margin:0 10px;">
		<h2 class="title noprint">
			<a class="op-btn op-btn1" id="btnPrint">打印</a>
			<a class="op-btn op-btn1" style="left:70px" onclick="window.close()">关闭</a>
		</h2>
		<h3 class="title3">${title}</h3>
		<div class="table-div">${data}</div>
	</div>
	</div>
	
	<script>
		function doPrint() {
			window.print();
		}
	
		$("#btnPrint").on("click", function() {
			doPrint();
		});
		
		setTimeout(function() {
			doPrint();
		}, 100);
	</script>
</body>
</html>