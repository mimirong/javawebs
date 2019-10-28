<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>类型配置</title>
    <script>
    currentMenu = "pm_project";
	currentSystem = "PM";
    </script>
</head>
<body>
<h2 class="title">类型配置 <a href="javascript:;"  class="btn fr btnAdd">添加</a></h2>
<div class="clearfix">
	<div class="tab fl"><a href="javascript:;" data-type="1" class="on">手续文件</a><a data-type="2" href="javascript:;">文档资料</a></div>
	<div class="filter-box fr" style="margin-right: 10px;">
         <input type="hidden" class="txt queryItem"  name="docType" value="1">
         <input type="text" class="txt queryItem"  name="like_typeName" placeholder="输入文件类型名称"><a href="javascript:;" id="btnSearch" class="btn btnQuery">查询</a>
    </div>
</div>

<div class="column_tab_con">
    <table id="data" class="column_tab_table" width="100%">
        <thead>
        <tr>
            <td width="20%">文件类型编码</th>
            <td width="40%">文件类型名称</td>
            <td >操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<%-- 新增和修改操作 --%>
<div class="alert" style="display:none; width:500px;" id="addPanel" name="wh_alert">
    <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
    <div class="pop-content" style="">
        <ul class="form clearfix" style="height:300px; overflow-y:auto">
            <li class="clearfix"><label class="tit w84"><em>*</em>类型名称：</label><input type="text" class="text w260" id="typeName" name="typeName" placeholder="输入文件类型名称" maxlength="100" /></li>
        </ul>
        <div style="text-align: center;padding:10px 0;">
            <a href="javascript:;" class="btn" id="btnDoAdd">增加</a>
            <a href="javascript:;" class="btn" id="btnDoModify">修改</a>
        </div>
    </div>
</div>
<script type="text/javascript">
	$('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
	</script>
<script type="text/javascript" src="${contextPath}/static/js/spark/PmDocType_List.js?v=${buildTimestamp}"></script>

</body>
</html>
