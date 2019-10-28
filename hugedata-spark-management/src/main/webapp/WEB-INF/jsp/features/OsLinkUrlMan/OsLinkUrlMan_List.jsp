<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>链接管理</title>
    <script>
        currentMenu = "outtech/link_url_man";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
    <h2 class="title">链接管理</h2>

    <div class="column_tab_con">
        <div class="search-box clearfix">

            <a class="btn op-btn right_float btnDeleteSelected" title="删除链接">删除链接</a>
            <a href="javascript:;" class="btn op-btn right_float op-publish btnAdd" title="发布链接">发布链接</a>
        </div>
        <table id="data" class="column_tab_table" width="100%">
            <thead>
            <tr>
                <td width="24" style="border-top-left-radius: 5px;">
                    <label class="checkbox btnSelectAll"></label>
                </th>
                <td width="50">序号</td>
                <td width="100">类别</td>
                <td>名称</td>
                <td style="min-width: 200px;">链接URL</td>
                <td style="border-top-right-radius: 5px;width:90px;">操作</td>
            </tr>
            </thead>
        </table>
    </div>

    <!--  Add or Modify  -->
    <div class="alert" style="display:none; width:450px;" id="addModifyDialog" name="wh_alert">
        <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addModifyDialogTitle"></span></div>
        <div class="pop-content" style="">
            <ul class="form clearfix">
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>链接名称：</label>
                    <input id="name" name="name" type="text" class="text" style="width:300px" placeholder="输入名称，最多80个字" value="" maxlength="80" />
                </li>

                <li class="clearfix"><label class="tit w84"><em>*</em>所属栏目：</label>
                    <span id="categoryIdSuper" class="select" style="width:300px;">
		                <select id="categoryId">
                            <option value="">--请选择--</option>
		                    <option value="LINK_URL_INTELLECTUAL_SERVICE_GUIDE">办事服务</option>
		                    <option value="LINK_URL_INTELLECTUAL_DATA_SERVICE">数据服务</option>
		                    <option value="LINK_URL_CREDIT_SYSTEM_COM_INFO_QUERY">企业信用查询</option>
		                    <option value="LINK_URL_CREDIT_SYSTEM_COM_INFO_SUBMIT">企业信息填报</option>
		                </select>
		            </span>
                </li>

                <li class="clearfix">
                    <label class="tit w84"><em>*</em>链接Url：</label>
                    <input id="linkUrl" name="linkUrl" type="text" class="text" style="width:300px" placeholder="输入正确的链接URL，请以https或http开头" value="" maxlength="200"/>
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
    <script type="text/javascript" src="${contextPath}/static/js/spark/OsLinkUrlMan_List.js?v=${buildTimestamp}"></script>
</body>
</html>
