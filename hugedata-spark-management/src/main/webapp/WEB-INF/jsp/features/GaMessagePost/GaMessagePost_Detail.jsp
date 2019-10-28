<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>在线办理</title>
<script>
	currentMenu = "message_post";
	currentSystem = "IT";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">
			信息送报<a class="return-back" href="javascript:;"
				onclick="history.go(-1)">返回 >></a>
		</h2>
		<table class="table">
			<tr>
				<td colspan="4" class="tit">送报详情</td>
			</tr>
			<tr>
				<td class="tit">重要程度</td>
				<td colspan="3" id="postLevel"></td>
			</tr>
			<tr>
				<td class="tit">送报时间</td>
				<td colspan="3" id="postTime"></td>
			</tr>
			<tr>
				<td class="tit">送报地点</td>
				<td colspan="3" id="postAddr"></td>
			</tr>
			<tr>
				<td class="tit">事件描述</td>
				<td colspan="3" id="eventDesc"></td>
			</tr>
			<tr>
				<td class="tit">发生时间</td>
				<td colspan="3" id="happenTime"></td>
			</tr>
			<tr>
				<td class="tit">送报人姓名</td>
				<td colspan="3" id="posterName"></td>
			</tr>
			<tr>
				<td class="tit">送报人电话</td>
				<td colspan="3" id="posterMobile"></td>
			</tr>
			 
		</table>
	</div>





	<script type="text/javascript">
		var serviceId = ${serviceId};
		$.ajax({
			url : "detailData",
			type : 'POST',
			dataType : "json",
			data : {serviceId:serviceId},
			success : function(resp) {
				$("#postLevel").text(resp.postLevel && resp.postLevel == '1' ? '紧急':'普通');
				$("#postTime").text(DateFormat.format.date(new Date(resp.postTime),'yyyy-MM-dd HH:mm:ss'));
				$("#postAddr").text(resp.postAddr);
				$("#eventDesc").text(resp.eventDesc);
				$("#happenTime").text(DateFormat.format.date(new Date(resp.happenTime),'yyyy-MM-dd HH:mm:ss'));
				$("#posterName").text(resp.posterName);
				$("#posterMobile").text(resp.posterMobile);
				if(resp.file1Id && resp.file1Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit">附件下载链接</td></tr>');
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file1Id+'" class="download">附件1下载</a></td></tr>')
				}
				
				if(resp.file2Id && resp.file2Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file2Id+'" class="download">附件2下载</a></td></tr>')
				}
				
				if(resp.file3Id && resp.file3Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file3Id+'" class="download">附件3下载</a></td></tr>')
				}
				
				if(resp.file4Id && resp.file4Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file4Id+'" class="download">附件4下载</a></td></tr>')
				}
				
				if(resp.file5Id && resp.file5Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file5Id+'" class="download">附件5下载</a></td></tr>')
				}
				
				if(resp.file6Id && resp.file6Id != ''){
					$('tbody').append('<tr><td colspan="4" class="tit"><a href="'+resp.file6Id+'" class="download">附件6下载</a></td></tr>')
				}
				
				 
			},
			error : function() {
				MU.msgTips("error", "获取详情失败，请稍后重试", 1200);
			}
		});
		
	</script>

</body>