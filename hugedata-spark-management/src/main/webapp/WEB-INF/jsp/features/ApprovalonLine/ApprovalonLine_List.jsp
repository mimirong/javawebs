<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<h2 class="title">在线办理</h2>
		<div class="column_tab_con">
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
									<li style="padding:0 12px;" data-value="11">待预审</li>
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
	</div>

	<%-- 审核操作 --%>
	<div class="alert" style="display:none; width:900px" id="approvePanel" name="wh_alert">
		<div style="text-align: center" class="title">
			<a href="javascript:;" onclick="MU.hide(this)"></a>办件处理
		</div>
		<div class="popupWrapper">
			<div style="padding:12px 20px; text-align:right;">
				<a href="javascript:;"  style="color: #107aee;" id="btnDownAcceptNotice">受理通知书</a>
				<a href="javascript:;"  style="color: #107aee;" id="btnDownFinishDoc">办结文书</a>
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
						<td id="businessNo"></td>
						<td class="tit">提交时间</td>
						<td id="createTime"></td>
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
						<td id="businessDeptPerson"></td>
						<td class="tit">联系方式</td>
						<td id="cellphone"></td>
					</tr>
					<tr>
						<td colspan="4" class="tit">受理信息</td>
					</tr>
					<tr>
						<td class="tit">受理部门</td>
						<td id="deptName"></td>
						<td class="tit">受理人</td>
						<td id="acceptUserName"></td>
					</tr>
					<tr>
						<td class="tit">受理时间</td>
						<td id="acceptTime"></td>
						<td class="tit">联系电话</td>
						<td id="acceptCellphone"></td>
					</tr>
					<tr>
						<td class="tit">办结时间</td>
						<td id="finishTime"></td>
						<td class="tit">当前状态</td>
						<td id="statusText"></td>
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
	                    <td colspan="3"><textarea placeholder="此处输入反馈意见内容，限字200" id="remark"></textarea></td>
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
            <div class="receivebox userSelect" style="width:832px">
	            <h3 class="detail-title hide userSelect">提交<span class="statusNextOp"></span><a class="fr receivebox-close" onclick="$('.userSelect').hide()"></a></h3>
	            <div class="filter-box hide userSelect">
	                <label class="tit">接收部门：</label>
	                <span class="select mr30 drop-up" style="width:200px;" id="deptSelector">
	                    <select></select>
	                </span>
	                <label class="tit">接收人：</label>
	                <span class="select drop-up" style="width:200px;" id="userSelector">
	                    <select>
	                        <option value="">--请选择接收人--</option>
	                    </select>
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
			userId: ${SPARK_LOGIN.userId},
			serviceRole: "${SPARK_LOGIN.serviceRole}"	
		};
	</script>

	<script type="text/javascript" src="${contextPath}/static/js/spark/ApprovalonLine_List.js?v=${buildTimestamp}"></script>

</body>