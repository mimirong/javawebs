<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>APP管理</title>
    <script>
        currentMenu = "app_manage_message";
        currentSystem = "INFORMATION";
    </script>
</head>
<body>
<h2 class="title">APP管理</h2>
<div class="column_tab_con">
     <div class="pop-content" style="">
     <input type="hidden" id="articleId" value="${articleId }"></input>
        <ul class="form clearfix" style="height:300px; overflow-y:auto">
            <li class="clearfix"><label class="tit w84"><em>*</em>标题：</label><input type="text" class="text w460" id="title" name="title" placeholder="输入文章标题" maxlength="100" /></li>
            <%--<li class="clearfix" ><label class="tit w84">摘要：</label><input type="text" class="text w460" id="brief" name="brief" placeholder="请输入摘要" maxlength="100" /></li>--%>
            <%--<li class="clearfix"><label class="tit w84">作者：</label><input type="text" class="text w460" id="author" name="author" placeholder="请输入作者" maxlength="100" /></li>--%>
            <%--<li class="clearfix"><label class="tit w84">来源：</label><input type="text" class="text w460" id="source" placeholder="请输入文章来源" maxlength="100" /></li>--%>
            <li class="clearfix">
            	<label class="tit w84"><em>*</em>正文：</label>
            	<div style="float:left; width:800px;">
                	<textarea id="contentEditorModify"  name="contentEditor" style="width:700px; height:200px;"></textarea>
                </div>
            </li>
            
            <li class="clearfix">
                <label class="tit w84">附件：</label>
                <div style="padding-left:93px;">
                    <div id="uploadedAttachments"></div>
                    <div id="uploadAttachmentWrapper">
                        <m:fileUpload inputIdForFileId="uploadAttachmentFileId" inputIdForFileName="uploadAttachmentFileName"
                                      autoReset="true" callback="onAttachmentUploaded" prompt="附件最大不能超过20MB" maxFileSize="20M" />
                    </div>
                </div>
            </li>
            <li>
            	 <div style="text-align: center;">
		            <a href="javascript:;" class="btn" id="btnDoModify">修改</a>
		        </div>
            </li>
        </ul>
       
    </div>
</div>

<script type="text/javascript" src="${contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${contextPath}/static/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/spark/AppManage_Message.js?v=${buildTimestamp}"></script>

</body>
</html>
