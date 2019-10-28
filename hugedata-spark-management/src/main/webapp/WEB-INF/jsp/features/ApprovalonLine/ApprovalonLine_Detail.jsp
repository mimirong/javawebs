<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>在线办理</title>
<script>
	currentMenu = "ol_service";
	currentSystem = "APPROVAL";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">
			在线办理<a class="return-back" href="javascript:;"
				onclick="history.go(-1)">返回 >></a>
		</h2>
		<table class="table">
			<tr>
				<td colspan="4" class="tit">办件信息</td>
			</tr>
			<tr>
				<td class="tit">受理编号</td>
				<td colspan="3" id="businessNo"></td>
			</tr>
			<tr>
				<td class="tit">申办事项</td>
				<td colspan="3" id="guidename"></td>
			</tr>
			<tr>
				<td class="tit">办理方式</td>
				<td>在线预审</td>
				<td class="tit">办理期限</td>
				<td id="timeLimit"></td>
			</tr>
			<tr>
				<td colspan="4" class="tit">申办单位（人）信息</td>
			</tr>
			<tr>
				<td class="tit">申办单位(人)</td>
				<td colspan="3" id="businessDeptPerson"></td>
			</tr>
			<tr>
				<td colspan="4" class="tit">受理信息</td>
			</tr>
			<tr>
				<td class="tit">受理窗口</td>
				<td id="acceptWindowName"></td>
				<td class="tit">受理人</td>
				<td id="acceptUserName"></td>
			</tr>
			<tr>
				<td class="tit">受理时间</td>
				<td id="createTime"></td>
				<td class="tit">联系电话</td>
				<td id="acceptCellphone"></td>
			</tr>
			<tr>
				<td class="tit">办结时间</td>
				<td id="acceptTime"></td>
				<td class="tit">当前状态</td>
				<td  id="status"></td>
			</tr>
			<tr>
				<td class="tit">处理结果</td>
				<td colspan="3" id="opinion"></td>
			</tr>
			<tr>
				<td class="tit">反馈意见</td>
				<td colspan="3" id="remark"></td>
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
				$("#businessNo").text(resp.businessNo);
				$("#guidename").text(resp.guidename);
				$("#timeLimit").text(resp.timeLimit);
				$("#businessDeptPerson").text(resp.businessDeptPerson);
				$("#acceptWindowName").text(resp.acceptWindowName==null?'':resp.acceptWindowName);
				$("#acceptUserName").text(resp.acceptUserName==null?'':resp.acceptUserName);
				$("#createTime").text(DateFormat.format.date(new Date(resp.createTime),'yyyy-MM-dd'));
				$("#acceptCellphone").text(resp.acceptCellphone==null?'':resp.acceptCellphone);
				if(resp.acceptTime == null){
					$("#acceptTime").text('');
				}
				else{
					$("#acceptTime").text(DateFormat.format.date(new Date(resp.acceptTime),'yyyy-MM-dd'));
				}
				$("#status").text(resp.status=='0'?'待审核':'办结');
				$("#opinion").text(resp.status=='0'?'':(resp.status=='1'?'通过':'不通过'));
				$("#remark").text(resp.remark==null?'':resp.remark);
			},
			error : function() {
				MU.msgTips("error", "添加失败，请稍后重试", 1200);
			}
		});
		
	</script>

</body>