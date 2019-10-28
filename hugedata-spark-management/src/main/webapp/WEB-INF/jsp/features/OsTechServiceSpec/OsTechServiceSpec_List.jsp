<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>云主机套餐</title>
	<script>
		currentMenu = "techserv/computing_specs";
		currentSystem = "INTEGRATED";
	</script>
</head>
<body>

	<h2 class="title">云主机套餐</h2>
		
	<div class="column_tab_con">
        <div class="search-box clearfix">
            <a href="javascript:;" class="btn op-btn right_float op-publish btnAdd">添加</a>
        </div>
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
					<td width="32">ID</th>
	                <td>套餐名称</td>
	                <td width="100">CPU</td>
	                <td width="100">内存</td>
	                <td width="100">硬盘</td>
	                <td width="100">带宽</td>
	                <td width="100">价格</td>
					<td width="155">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <%-- 新增和修改操作 --%>
    <div class="alert" style="display:none; width:600px;" id="addPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
		<div class="pop-content">
		    <ul class="form clearfix">
		        <li class="clearfix">
			        <label class="tit w164"><em>*</em>套餐名称：</label>
			        <input type="text" class="text w300" value="" id="specName" maxlength="20" />
		        </li>
		        <li class="clearfix">
		        	<label class="tit w164"><em>*</em>CPU：</label>
		        	<input type="text" id="specDataCpu" class="text w98" value="2" />核
		        </li>
		        <li class="clearfix">
		        	<label class="tit w164"><em>*</em>内存：</label>
		        	<input type="text" id="specDataMemory" class="text w98" value="1"/>GB
		        </li>
		        <li class="clearfix">
		        	<label class="tit w164"><em>*</em>硬盘：</label>
		        	<input type="text" id="specDataDisk" class="text w98" value="60"/>GB
		        </li>
		        <li class="clearfix">
		        	<label class="tit w164"><em>*</em>带宽：</label>
		        	<input type="text" id="specDataBandwidth" class="text w98" value="4"/>M
		        </li>
		        <li class="clearfix">
			        <label class="tit w164"><em>*</em>价格：</label>
			        <input type="text" class="text w98 mr10" value="" id="price" maxlength="8" />元/月
		        </li>
			    <div style="text-align: center;padding:10px 0;">
			    	<a href="javascript:;" class="btn" id="btnDoAdd">添加</a>
			    	<a href="javascript:;" class="btn" id="btnDoModify">修改</a>
			    </div>
		    </ul>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/OsTechServiceSpec_List.js?v=${buildTimestamp}"></script>

</body>