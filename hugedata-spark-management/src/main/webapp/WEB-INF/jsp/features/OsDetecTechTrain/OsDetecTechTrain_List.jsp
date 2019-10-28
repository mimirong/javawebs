<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>技术培训</title>
    <script>
        currentMenu = "outdetec/tech_train";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
<h2 class="title">技术培训</h2>
<div class="column_tab_con">
    <div class="search-box clearfix">
        <a class="btn op-btn right_float btnDeleteSelected" title="删除文章">删除文章</a>
        <a href="javascript:;" class="btn op-btn right_float op-publish btnAdd" title="发布文章">发布文章</a>
    </div>
    <table id="data" class="column_tab_table" width="100%">
        <thead>
        <tr>
            <td width="30" style="border-top-left-radius: 5px;">
                <label class="checkbox btnSelectAll"></label>
            </th>
            <td width="70">ID</th>
            <td width="115">所属栏目</td>
            <td width="170">标题</td>
            <td width="80">是否显示</td>
            <td width="150">创建时间</td>
            <td width="150">更新时间</td>
            <td width="205" style="border-top-right-radius: 5px;">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<%-- 新增和修改操作 --%>
<div class="alert" style="display:none; width:1000px;" id="addPanel" name="wh_alert">
    <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addPanelTitle"></span></div>
    <div class="pop-content" style="">
        <ul class="form clearfix" style="height:300px; overflow-y:auto">
            <li class="clearfix"><label class="tit w84"><em>*</em>标题：</label><input type="text" class="text w460" id="title" name="title" placeholder="输入文章标题" maxlength="100" /></li>
            <li class="clearfix"><label class="tit w84"><em>*</em>所属栏目：</label>
                <span class="select" style="width:478px;">
		                <select id="categoryId">
                            <option value="">--请选择--</option>
		                    <option value="OS_DETEC_TECH_TRAIN">技术培训</option>
		                </select>
		            </span>
            </li>
            <li class="clearfix"><label class="tit w84">摘要：</label><input type="text" class="text w460" id="brief" name="brief" placeholder="请输入摘要" maxlength="100" /></li>
            <li class="clearfix"><label class="tit w84">作者：</label><input type="text" class="text w460" id="author" name="author" placeholder="请输入作者" maxlength="100" /></li>
            <li class="clearfix"><label class="tit w84">来源：</label><input type="text" class="text w460" id="source" placeholder="请输入文章来源" maxlength="100" /></li>
            <li class="clearfix"><label class="tit w84"><em>*</em>发布日期：</label><input type="text" class="text w460 dateInput" id="publishTimeStr" name="publishTimeStr" /></li>
            <li class="clearfix">
            	<label class="tit w84">正文：</label>
            	<div style="float:left; width:800px;">
                	<textarea id="contentEditorModify"  name="contentEditor" style="width:800px; height:200px;"></textarea>
                </div>
            </li>
            <li class="clearfix">
                <label class="tit w84">图片：</label>
                <div style="padding-left:93px;">
                    <div id="uploadedImages"></div>
                    <div id="uploadImageWrapper">
                        <m:fileUpload inputIdForFileId="uploadImageFileId" inputIdForFileName="uploadImgeFileName"
                                      allowedExtensions="images" autoReset="true" callback="onImageUploaded" />
                    </div>
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
        </ul>
        <div style="text-align: center;padding:10px 0;">
            <a href="javascript:;" class="btn" id="btnDoAdd">发布文章</a>
            <a href="javascript:;" class="btn" id="btnDoModify">修改文章</a>
        </div>
    </div>
</div>

<script type="text/javascript" src="${contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${contextPath}/static/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/spark/OsDetecTechTrain_List.js?v=${buildTimestamp}"></script>

</body>
</html>
