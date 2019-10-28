<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="p" uri="http://spark.hugedata.com.cn/tags/portal-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办事事项</title>
</head>
<body>
    <h1 class="column_tit">您当前位置：
    	<a href="${contextPath}/">首页</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事服务</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事事项</a> > 
    	<a href="list?dept={{guide.deptCode}}">${guide.deptName}</a> > 
    	<span>${guide.guideName}</span>
   	</h1>
   	
	<!--content-start-->
	<div class="column_con clearfix" style="padding: 0 20px 35px; width: 960px;">
		<h2 class="title">${guide.guideName}</h2>
			<div class="tab" style="width: 347px">
				<a href="details?guideId=${guideId}">办事指南</a><a href="details2?guideId=${guideId}">办事流程</a><a href="details3?guideId=${guideId}">表格下载</a><a href="onlineTransact?guideId=${guideId}" class="on">在线办理</a>
			</div>

			<div class="tab-content clearfix">
				<h3 class="title">在线办理</h3>
				<div class="pro-cont">
				
				<div class="row">
					<label class="title"><i>*</i>申办单位（人）：</label>
					<input type="text" class="txt" id="businessDeptPerson" maxlength="100" />
				</div>
				<div class="row">
					<label class="title"><i>*</i>联系方式：</label>
					<input type="text" class="txt" id="cellphone" maxlength="100" />
				</div>
				<div class="row">
					<label class="title"><i>*</i>申请业务材料上传：</label>
				</div>
				
				<table class="column_tab_table" style="text-align: center;">
					<thead>
						<tr>
							<td width="100px">操作</td>
							<td width="80px">附件数</td>
							<td width="">材料名称</td>
							<td width="280px">材料说明</td>
						</tr>
					</thead>
					<tbody id="attListWrapper">
						<!-- 办理材料列表 -->
					</tbody>
				</table>
				<div class="btn-div" style="margin:20px 0; text-align:center;">
					<a class="btn" id="btnSubmit" href="javascript:;" style="margin:0;">提交审核</a>
				</div>
			</div>
		</div>

	</div>
	<!--content-end-->

	<script>
		window.data = ${data};
		window.fromServiceId = "${param.fromServiceId}";
	</script>
	<script src="${contextPath}/static/js/serviceGuide/onlineTransact.js?v=${buildTimestamp}"></script>

</body>
</html>