<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>窗口办理</title>
<script>
	currentMenu = "wd_service";
	currentSystem = "APPROVAL";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">
			窗口办理
			<c:if test="${SPARK_LOGIN.serviceRole == 'WINDOW'}">
				<a href="javascript:;" class="btn fr add-check btnAdd" title="添加办件">添加办件</a>
			</c:if>
		</h2>
		<div class="filter-box">
			<label class="tit">办件编号：</label>
			<input type="text" class="txt mr20 queryItem" name="like_businessNo">
			<label class="tit">申办事项：</label>
			<input type="text" class="txt mr20 queryItem" name="like_guidename">
			<label class="tit">申办单位（人）：</label>
			<input type="text" class="txt queryItem" name="like_businessDeptPerson">
			<a href="javascript:;" class="btn" id="btnSearch">查询</a>
		</div>
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
					<td style="width:180px;">办件编号</td>
					<td>申办事项</td>
					<td style="width:110px;">受理时间</td>
					<td style="width:180px;">申办单位（人）</td>
					<td style="width:150px;">联系方式</td>
					<td style="width:90px;">
						<div class="selectContainer" id="statusSelect">
							<span class="selectOption">状态</span><em class="icon"></em>
							<input type="hidden" name="status" value="" id="queryStatus" class="queryItem" />
							<ul class="selectMenu" style="margin-left:-12px;">
								<li style="padding:0 12px;" data-value="" class="cur">全部</li>
								<%--
								<li style="padding:0 12px;" data-value="11">待预审</li>
								--%>
								<li style="padding:0 12px;" data-value="12">已受理</li>
								<li style="padding:0 12px;" data-value="13">已办结</li>
								<li style="padding:0 12px;" data-value="14">废弃件</li>
							</ul>
						</div>
					</td>
				</tr>
			</thead>
		</table>
	</div>

	<%-- ========================================================================================================== --%>
	
	<%-- 添加操作 --%>
	<div class="alert" style="display:none; width:1000px" id="addPanel" name="wh_alert">
		<div style="text-align: center" class="title">
			<a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle">办件处理</span>
		</div>
		<div class="popupWrapper">
			<table class="table" style="width:98%;">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tr>
					<td class="tit">受理编号</td>
					<td id="businessNo" colspan="3"></td>
				</tr>
				<tr>
					<td class="tit">申办事项</td>
					<td colspan="3">
						<span class="select" style="width: 250px;">
							<select id="deptSelect"></select>
						</span> 
						<span class="select" style="width: 250px;">
							<select id="guideSelect"></select>
						</span>
					</td>
				</tr>
				<tr>
					<td class="tit">办理方式</td>
					<td>承诺件</td>
					<td class="tit">办理期限</td>
					<td id="timeLimit"></td>
				</tr>
				<tr>
					<td colspan="4" class="tit">申办单位（人）信息</td>
				</tr>
				<tr>
					<td class="tit">申办单位(人)</td>
					<td>
						<input id="businessDeptPerson" type="text" style="height:30px;" class="text" placeholder="在此输入单位（人）名称（限字20）" maxlength="20"/>
					</td>
					<td class="tit">联系方式</td>
					<td>
						<input id="cellphone" type="text" style="height:30px;" class="text" placeholder="在此输入联系电话" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="tit">受理信息</td>
				</tr>
				<tr>
					<td class="tit">受理部门</td>
					<td id="acceptDeptName"></td>
					<td class="tit">受理人</td>
					<td id="acceptUserName"></td>
				</tr>
				<tr>
					<td class="tit">受理时间</td>
					<td id="acceptTime"></td>
					<td class="tit">联系电话</td>
					<td id="acceptCellphone"></td>
				</tr>
			</table>
			
	        <h3 class="detail-title">申请业务材料上传</h3>
	        <table class="column_tab_table" style="text-align:center; width:98%;">
	            <thead>
	            <tr>
	                <td width="100px">操作</td>
	                <td width="80px">附件数</td>
	                <td width="">材料名称<a class="blue ml20" id="btnCreateAttConfig">手动输入</a></td>
	                <td width="280px">材料说明</td>
	            </tr>
	            </thead>
	            <tbody id="attListWrapper">
	            </tbody>
	        </table>
			
	        <h3 class="detail-title">预审结果<a class="status">待审</a></h3>
	        <div style="margin: 20px 0 16px 0;"><label class="checkbox radio checked">通过</label></div>
	        <table class="table" style="width:98%">
	            <tr>
	                <td class="tit" style="vertical-align: middle;">反馈意见</td>
	                <td colspan="3"><textarea id="remark" placeholder="此处输入反馈意见内容，限字200"></textarea></td>
	            </tr>
	        </table>
	        <h3 class="detail-title">提交初审</h3>
	        <div class="filter-box">
	            <label class="tit">接收部门：</label>
                <span class="select mr30 drop-up" style="width:200px;" id="acceptDeptSelector">
                    <select></select>
                </span>
	            <label class="tit">接收人：</label>
                <span class="select drop-up" style="width: 200px;" id="acceptUserSelector">
                    <select></select>
                </span>
	        </div>
			
			<div class="btn-box" style="margin: 20px 0px 20px; text-align: center;">
				<a href="javascript:;" id="btnDoAdd" class="btn">提交</a>
				<a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey">取消</a>
			</div>
		</div>
	</div>
	
	<%-- ========================================================================================================== --%>
	
	<%-- 详情 --%>
	<div class="alert" style="display:none; width:1000px;" id="approvePanel" name="wh_alert">
		<div style="text-align: center" class="title">
			<a href="javascript:;" onclick="MU.hide(this)"></a>办件详情
		</div>
		<div class="popupWrapper2">
			<div style="padding:12px 20px; text-align:right;">
				<a href="javascript:;"  style="color: #107aee;" id="btnDownAcceptNotice">受理通知书</a>
				<a href="javascript:;"  style="color: #107aee;" id="btnDownFinishDoc">办结文书</a>
				<%--
				<a href="javascript:;"  style="color: #107aee;" id="btnPrintDetails">打印</a>
				--%>
			</div>
			<!-- 基本信息 -->
			<table class="table" style="width:98%">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tbody>
					<tr>
						<td colspan="4" class="tit">办件信息</td>
					</tr>
					<tr>
						<td class="tit">受理编号</td>
						<td id="showBusinessNo"></td>
						<td class="tit">提交时间</td>
						<td id="showCreateTime"></td>
					</tr>
					<tr>
						<td class="tit">申办事项</td>
						<td colspan="3" id="showGuidename"></td>
					</tr>
					<tr>
						<td class="tit">办理方式</td>
						<td>承诺件</td>
						<td class="tit">办理期限</td>
						<td id="showTimeLimit"></td>
					</tr>
					<tr>
						<td colspan="4" class="tit">申办单位（人）信息</td>
					</tr>
					<tr>
						<td class="tit">申办单位(人)</td>
						<td id="showBusinessDeptPerson"></td>
						<td class="tit">联系方式</td>
						<td id="showCellphone"></td>
					</tr>
					<tr>
						<td colspan="4" class="tit">受理信息</td>
					</tr>
					<tr>
						<td class="tit">受理部门</td>
						<td id="showDeptName"></td>
						<td class="tit">受理人</td>
						<td id="showAcceptUserName"></td>
					</tr>
					<tr>
						<td class="tit">受理时间</td>
						<td id="showAcceptTime"></td>
						<td class="tit">联系电话</td>
						<td id="showAcceptCellphone"></td>
					</tr>
					<tr>
						<td class="tit">办结时间</td>
						<td id="showFinishTime"></td>
						<td class="tit">当前状态</td>
						<td id="showStatusText"></td>
					</tr>
				</tbody>
			</table>
	
			<!-- 申请材料 -->
	        <h3 class="detail-title">申请业务材料<span class="showWithStatusOp">（</span><span class="statusOp"></span><span class="showWithStatusOp">）</span></h3>
	        <table class="column_tab_table" style=" text-align:center; width:98%;">
	            <thead>
		            <tr>
		                <td width="87px">操作</td>
		                <td width="">附件数</td>
		                <td>材料名称</td>
		                <td width="">材料说明</td>
		            </tr>
	            </thead>
	            <tbody id="attachmentsWrapper">
	            </tbody>
	        </table>
	        
	        <!-- 操作记录 -->
	        <div id="procListWrapper"></div>
	        
	        <!-- 通过/不通过 -->
	        <div class="approveWrapper">
	            <h3 class="detail-title"><span class="statusOp"></span>结果<a class="status">待<span class="statusOp"></span></a></h3>
	            <div style="margin: 20px 0 16px 0;">
	            	<label id="btnServiceApprove" class="checkbox radio">通过</label>
	            	<label id="btnServiceReject" class="checkbox radio">不通过</label>
	           	</div>
	           	<div class="upload-file finishDocUpload">
	           		<label class="tit" style="float:left; margin-top:10px;">上传办结文书：</label>
	           		<div id="finishDocUploadWrapper" style="float:left">
	           			<m:fileUpload inputIdForFileId="finishDocFileId" inputIdForFileName="finishDocFileName" />
	           		</div>
           		</div>
	            <table class="table" style="width:98%">
	                <tr>
	                    <td class="tit" style="vertical-align: middle;">反馈意见</td>
	                    <td colspan="3"><textarea placeholder="此处输入反馈意见内容，限字200" id="showRemark"></textarea></td>
	                </tr>
	            </table>
            </div>
			
			<!-- 抄送用户选择 -->
			<h3 class="detail-title ccUserSelect">抄送</h3>
			<ul class="form clearfix ccUserSelect">
				<li class="clearfix">
					<label class="tit w70">抄送部门：</label>
					<span class="select mr30 fl drop-up" style="width: 200px;" id="ccDeptSelector">
						<select></select>
					</span>
					<label class="tit w70">抄送人：</label>
					<span class="select fl drop-up" style="width: 200px;" id="ccUserSelector">
						<select></select>
					</span>
				</li>
			</ul>
        
        	<!-- 操作按钮 -->
			<div class="btn-box" style="margin: 20px 0px 20px; text-align: center;">
				<a id="btnDeliverPre" href="javascript:;" class="btn">转交下一步</a>
				<a id="btnApprove" href="javascript:;" class="btn">提交</a>
				<a id="btnReject" href="javascript:;" class="btn">提交</a>
				<a id="btnFinish" href="javascript:;" class="btn">确认</a>
				<a onclick="MU.hide(this)" href="javascript:;" class="btn btn-grey">取消</a>
			</div>
			
			<!-- 提交部门选择 -->
            <div class="receivebox userSelect" style="width:932px">
	            <h3 class="detail-title hide userSelect">提交<span class="statusNextOp"></span><a class="fr receivebox-close" onclick="$('.userSelect').hide()"></a></h3>
	            <div class="filter-box hide userSelect">
	                <label class="tit">接收部门：</label>
	                <span class="select mr30 drop-up" style="width:200px;" id="procDeptSelector">
	                    <select></select>
	                </span>
	                <label class="tit">接收人：</label>
	                <span class="select drop-up" style="width:200px;" id="procUserSelector">
	                    <select></select>
	                </span>
	            </div>
	        
	        	<!-- 操作按钮 -->
				<div class="btn-box userSelect" style="margin: 20px 0px 20px; text-align: center;">
					<a id="btnDeliver" href="javascript:;" class="btn">转交下一步</a>
				</div>
			</div>
			
			<div style="clear:both; height:18px"></div>
		</div>
	</div>


	<script>
		window.login = {
			userId: parseInt("${SPARK_LOGIN.userId}"),
			name: "${SPARK_LOGIN.name}",
			mobile: "${SPARK_LOGIN.mobile}",
			serviceRole: "${SPARK_LOGIN.serviceRole}"
		};
	</script>

	<script type="text/javascript" src="${contextPath}/static/js/spark/ApprovalBywindow_List.js?v=${buildTimestamp}"></script>
</body>
</html>
