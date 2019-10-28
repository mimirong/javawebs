<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>申请记录</title>
	<script>
		currentMenu = "techserv/service_apply";
		currentSystem = "INTEGRATED";
	</script>
</head>
<body>

	<h2 class="title">申请记录</h2>
		
	<div class="column_tab_con">
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
                    <td style="">申请内容</td>
                    <td style="width:50px;">时长</td>
                    <td style="width:50px;">数量</td>
                    <td style="width:80px;">费用总计</td>
                    <td style="width:90px;">申请日期</td>
                    <td style="width:80px;">设施类别</td>
                    <td style="width:90px;">类型</td>
                    <td style="width:70px;">审批进度</td>
                    <td style="width:90px;">操作</td>
				</tr>
			</thead>
		</table>
	</div>
	
    <%-- 新增和修改操作 --%>
    <div class="alert" style="display:none; width:360px;" id="approvePanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a>审批</div>
		<div class="pop-content" style="padding: 30px 0;">
		    <ul class="form clearfix" style="width: 360px;text-align: center;">
		        <li class="clearfix">未审批</li>
		        <li class="clearfix">设置为已审批？</li>
		        <li class="clearfix">
		        	<a href="javascript:;" class="btn" id="btnSetApprove">确定</a>
	        		<a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey">取消</a>
        		</li>
		    </ul>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/OsTechServiceApply_List.js?v=${buildTimestamp}"></script>

</body>