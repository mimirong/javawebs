<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="decorator" content="simple">
	<title>打印</title>
	<style>
		@media print {
			.noprint { display:none; }
			body { background-color:white; }

			@page {
				size: "A4 portrait";
			}
		}
			
		p.print-title { font:normal 16pt '黑体'; text-align:center; }
		p.print-att { font:normal 10.5pt '宋体'; text-indent:0; line-height:1.7em; }
		p { font:normal 14pt '仿宋'; text-indent:2em; line-height:1.7em; }
		.ul { text-decoration:underline; }
	</style>
</head>
<body>
		
	<div class="content" style="padding:12px; width:800px; margin:0 auto;">
		<h2 class="noprint">
			<a href="javascript:;" class="btn blue-font" id="btnPrint" style="float:left;">打印</a>
		</h2>
		<div style="clear:both; height:12px;"></div>
		
		<p class="print-title">长沙岳麓科技产业园企业服务中心</p>
		<p class="print-title">行政许可申请受理通知书</p>
		
		<p>&nbsp;</p>
		<p style="text-indent:0"><span class="ul">&nbsp;${data.businessDeptPerson}&nbsp;</span>：</p>
		<p>您企业于<span class="ul"><fmt:formatDate value="${data.createTime}" pattern=" yyyy " /></span>年<span class="ul"><fmt:formatDate value="${data.createTime}" pattern=" M " /></span>月<span class="ul"><fmt:formatDate value="${data.createTime}" pattern=" d " /></span>日向我中心提出<span class="ul">&nbsp;${data.guidename}&nbsp;</span>的行政许可申请，根据《中华人民共和国行政许可法》第三十二条第一款第（五）项之规定，我中心决定予以受理。</p>
		<p>特此通知。</p>
		<p>联系人（经办人）：	<span class="ul">&nbsp;${data.acceptUserName}&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;联系电话：<span class="ul">&nbsp;${data.acceptCellphone}&nbsp;</span>
		<p style="text-align:right;"><span class="ul"><fmt:formatDate value="${data.acceptTime}" pattern=" yyyy " /></span>年<span class="ul"><fmt:formatDate value="${data.acceptTime}" pattern=" M " /></span>月<span class="ul"><fmt:formatDate value="${data.acceptTime}" pattern=" d " /></span>日</p>
		
		<p class="print-att">&nbsp;</p>
		<p class="print-att">受理材料清单：</p>
		<div id="attachments">
		</div>
	</div>

	<script type="text/javascript">
		var serviceId = ${param.serviceId};
		var attachments = ${data.attachmentsJson};

		var attIndex = 1;
		$.each(attachments, function(i, att) {
			if (att.fileId == "NOFILE") {
				return;
			}
			$("<p>").addClass("print-att").append(attIndex++).append("、").append(att.fileName).appendTo("#attachments");
		});
		
		$("#btnPrint").on("click",function(){
			window.print();
		});
	</script>

</body>