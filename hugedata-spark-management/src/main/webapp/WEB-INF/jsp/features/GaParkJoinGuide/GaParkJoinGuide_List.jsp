<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>入园指南</title>
	<script>
		currentMenu = "joinguide";
		currentSystem = "INTEGRATED";
	</script>
	<style>
		ul.form li label.tit { width:90px; }
	</style>
</head>
<body>

	<h2 class="title">入园指南</h2>
	<%--
    <div class="tab"><a href="${contextPath}/features/GaParkJoinApplication/list">申请审批</a><a href="${contextPath}/features/GaParkJoinGuide/list" class="on">入园指南</a></div>
    --%>
		
	<div class="column_tab_con">
        <div class="search-box clearfix">
            <a href="javascript:;" class="btn op-btn right_float btnAdd" title="添加指南">添加指南</a>
        </div>
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
                    <td>入园指南</td>
                    <td style="width:160px;">发布日期</td>
                    <td style="width:160px;">附件下载</td>
                    <td style="width:100px;">操作</td>
				</tr>
			</thead>
		</table>
	</div>
	
    <%-- 新增和修改操作 --%>
    <div class="alert" style="display:none; width:950px;" id="addPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
		<div class="pop-content" style="padding-top:8px;">
			<ul class="form clearfix" style="padding:10px 0; overflow:auto;">
				<li class="clearfix">
					<label class="tit"><em>*</em>名称：</label>
					<input type="text" class="text w460" value="" name="title" id="title" maxlength="100" />
				</li>
				<li class="clearfix">
					<label class="tit"><em>*</em>发布部门：</label>
					<input type="text" class="text w460" value="" name="creatorDept" id="creatorDept" maxlength="100" />
				</li>
				<li class="clearfix">
					<label class="tit"><em>*</em>发布人：</label>
					<input type="text" class="text w220" value="${SPARK_LOGIN.name}" name="creatorName" id="creatorName" maxlength="100" />
				</li>
				<li  class="clearfix">
					<label class="tit"><em>*</em>发布日期：</label>
					<input type="text" class="text w220 dateInput" id="publishTimeStr" name="publishTimeStr" />
				</li>
		        <li class="clearfix">
		        	<label class="tit w70"><em>*</em>内容：</label>
            		<div style="float:left; width:800px;">
                    	<textarea id="contentEditorModify" name="contentEditor" style="width:700px; height:200px;"></textarea>
                    </div>
		        </li>
		        <li class="clearfix">
		        	<label class="tit">附件：</label>
		        	<div style="padding-left:93px;">
			        	<div id="uploadedAttachments"></div>
			        	<div id="uploadAttachmentWrapper">
			        		<m:fileUpload inputIdForFileId="uploadAttachmentFileId" inputIdForFileName="uploadAttachmentFileName" 
			        				autoReset="true" callback="onAttachmentUploaded" prompt="附件最大不能超过20MB" maxFileSize="20M" />
			        	</div>
		        	</div>
	        	</li>
			</ul>
		    <div style="text-align: center;padding:10px 0;">
		    	<a href="javascript:;" class="btn" id="btnDoAdd">确定</a>
		    	<a href="javascript:;" class="btn" id="btnDoModify">确定</a>
		    	<a href="javascript:;" class="btn" onclick="MU.hide(this)">取消</a>
		    </div>
		</div>
	</div>
	
    <script type="text/javascript" src="${contextPath}/static/ckeditor/ckeditor.js?v=${buildTimestamp}"></script>
    <script type="text/javascript" src="${contextPath}/static/ckeditor/adapters/jquery.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/spark/GaParkJoinGuide_List.js?v=${buildTimestamp}"></script>

</body>