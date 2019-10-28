<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>首页图片</title>
    <script>
        currentMenu = "home_image";
        currentSystem = "INFORMATION";
    </script>
</head>
<body>
    <h2 class="title">首页图片</h2>

    <div class="column_tab_con">
        <div class="search-box clearfix">
            <a class="btn op-btn right_float btnDeleteSelected" title="删除图片">删除图片</a>
            <a href="javascript:;" class="btn op-btn right_float op-publish btnAdd" title="发布图片">发布图片</a>
        </div>
        <table id="data" class="column_tab_table" width="100%">
            <thead>
            <tr>
                <td style="width:30px">
                    <label class="checkbox btnSelectAll"></label>
                </th>
                <td style="width:80px">ID</th>
                <td style="width:100px">图片</td>
                <td>名称</td>
                <td>链接URL</td>
                <td style="width:60px">是否可见</td>
                <td style="width:200px;">操作</td>
            </tr>
            </thead>
        </table>
    </div>

    <!--  Add or Modify  -->
    <div class="alert" style="display:none; width:800px;" id="addModifyDialog" name="wh_alert">
        <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addModifyDialogTitle"></span></div>
        <div class="pop-content" style="">
            <ul class="form clearfix">
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>图片名称：</label>
                    <input id="name" name="name" type="text" class="text" style="width:478px" placeholder="输入图片名称，最多80个字" value="" maxlength="80" />
                </li>

                <li class="clearfix">
                    <label class="tit w84"><em>*</em>标题：</label>
                    <input id="title" name="title" type="text" class="text" style="width:478px" placeholder="输入标题，最多50个字" value="" maxlength="50" />
                </li>

                <li class="clearfix">
                    <label class="tit w84">链接Url：</label>
                    <input id="linkUrl" name="linkUrl" type="text" class="text" style="width:478px" placeholder="输入正确的链接URL，请以https或http开头" value="" maxlength="200"/>
                </li>

                <li class="clearfix">
                    <label class="tit w84"><em>*</em>摘要：</label>
                    <textarea name="brief" id="brief" style="width:478px;" cols ="20" rows="3" maxlength="200" placeholder="不能超过200个字"></textarea>
                </li>

                <li class="clearfix" id="imageFileWrapper">
                    <label class="tit w84"><em>*</em>上传图片：</label>
                    <div style="float:left">
                        <div id="fileDiv">
                            <m:fileUpload inputIdForFileName="fileName" inputIdForFileId="fileId" allowedExtensions="images" maxFileSize="10M" />
                            <span id="fileTip" style="color:#f00">建议图片尺寸1920x1080，大小不能超过10M，建议不超过1M</span>
                        </div>
                    </div>
                </li>
            </ul>
            <div style="text-align: center;padding:10px 0;">
                <label class="tit w84">&nbsp;</label>
                <a class="btn btnDoAdd" href="javascript:;">确定添加</a>
                <a class="btn btnDoModify" href="javascript:;">确定修改</a>
                <a class="btn btn-gray" onclick="MU.hide(this)" href="javascript:;">取消</a>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${contextPath}/static/js/spark/PtHomeImage_List.js?v=${buildTimestamp}"></script>
</body>
</html>