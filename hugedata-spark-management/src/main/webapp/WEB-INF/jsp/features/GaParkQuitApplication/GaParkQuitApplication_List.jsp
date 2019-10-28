<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>退园申请</title>
	<script>
		currentMenu = "quitapply";
		currentSystem = "INTEGRATED";
	</script>
</head>
<body>

	<h2 class="title">退园申请</h2>
	<%--
    <div class="tab"><a href="${contextPath}/features/GaParkQuitApplication/list" class="on">申请审批</a><a href="${contextPath}/features/GaParkQuitGuide/list">退园指南</a></div>
    --%>
		
	<div class="column_tab_con">
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
                    <td style="width:100px">申请ID</td>
                    <td>企业名称</td>
                    <td style="width:160px">申请日期</td>
                    <td style="width:80px">
                        <div class="selectContainer" id="queryStatusWrapper">
                        	<input type="hidden" class="queryItem" value="" name="status" />
                        	<span class="selectOption">当前状态</span><em class="icon"></em>
                            <ul class="selectMenu">
                                <li class="cur" value="">全部</li>
                                <li value="CREATED">待审批</li>
                                <li value="APPROVED">已审批</li>
                            </ul>
                        </div>
                    </td>
                    <td style="width:100px">操作</td>
				</tr>
			</thead>
		</table>
	</div>

    <%-- 查看详情 --%>
    <div class="alert" style="display:none; width:781px;" id="detailsPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span id="detailsTitle"></span></div>
		<div class="column_con panelSizeWrapper" style="border:none; overflow:auto; width:770px; min-width:0">
			<h3 class="tab-tit" style="text-align: center;">长沙市岳麓科技产业园企业退园备案登记表</h3>
			<div class="signup-cont apply-count" id="viewFormWrapper">
			    <div class="row">
			        <label class="title w154">企业名称：</label>
			        <input type="text" value="" class="txt w190" id="viewCompanyName" />
			        <label class="title w100">法人代表：</label>
			        <input type="text" value="" class="txt w190" id="viewRepresentative" />
			    </div>
			    <div class="row">
			        <label class="title w154">联系方式：</label>
			        <input type="text" value="" class="txt w190" id="viewContact" />
			        <label class="title w100">退园日期：</label>
			        <input type="text" value="" class="txt w190" id="viewQuitDate" />
			    </div>
			    <div class="row" style="height:66px;">
			    	<label class="title w154">审批意见：</label>
			    	<textarea id="approveRemark" class="txt w520" style="height:80px"></textarea>
		    	</div>
			    <div class="btn-div">
			    	<a href="javascript:;" class="btn" id="btnSubmitApprove">提交</a>
			    	<a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey">关闭</a>
			    </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/GaParkQuitApplication_List.js?v=${buildTimestamp}"></script>

</body>