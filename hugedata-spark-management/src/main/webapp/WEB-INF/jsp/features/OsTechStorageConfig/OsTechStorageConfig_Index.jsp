<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>云存储</title>
	<script>
		currentMenu = "techserv/storage_config";
		currentSystem = "INTEGRATED";
	</script>
</head>
<body>

	<h2 class="title">云存储</h2>
		
    <div class="column_tab_con">
        <ul class="form clearfix">
            <li class="clearfix">
             <label class="tit"><em>*</em>定义单价：</label>
             <input class="text w210 mr10" type="text" id=unitPricePerGBMonth value="${config.unitPricePerGBMonth / 100}" />元/月/GB
            </li>
            <li class="clearfix">
            	<label class="tit">&nbsp;</label>
             <a href="javascript:;" class="btn mr10" id="btnUpdate">确认</a>
             <a href="javascript:;" class="btn btn-grey" id="btnCancel">取消</a>
            </li>
        </ul>
    </div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/OsTechStorageConfig_List.js?v=${buildTimestamp}"></script>

</body>