<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>申请审批</title>
    <script>
        currentMenu = "outdetec/approve";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
<h2 class="title">申请审批</h2>
<!--content-start-->
<div class="column_tab_con">
    <table id="data" class="column_tab_table">
        <thead>
        <tr>
            <td width="50px">序号</td>
            <td>申请资源</td>
            <td width="140px">公司名称</td>
            <td width="60px">联系人</td>
            <td width="70px">手机号码</td>
            <td width="40px">说明</td>
            <td width="70px">
                <div class="selectContainer" id="queryStatusWrapper"><span class="selectOption">状态</span><em class="icon"></em>
                    <input type="hidden" class="queryItem" value="" name="status" />
                    <ul class="selectMenu">
                        <li class="cur" value="">全部</li>
                        <li value="CREATED">待审批</li>
                        <li value="FINISHED">已办结</li>
                    </ul>
                </div>
            </td>
            <td width="60px">审批人</td>
            <td width="40px">操作</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    </div>
</div>
<!--content-end-->

<!--  comment dialog  -->
<div class="alert"  style="display:none;width: 400px;" id="showComment" name="wh_alert">
    <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="showCommentTitle"></span></div>
    <ul class="form clearfix">
        <li class="clearfix">
            <a class="form-con" title="" id="dialogComment" name="dialogComment"></a>
        </li>
        <li class="clearfix"><a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey" style="margin-left:132px;">关闭</a></li>
    </ul>
</div>

<!--  approve dialog  -->
<div class="alert" style="display:none;width: 400px;" id="showApprove" name="wh_alert">
    <input type="hidden" id="approveDialogApplyId"/>
    <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="showApproveTitle"></span></div>
    <ul class="form clearfix" style="width: 360px;text-align: center;">
        <li class="clearfix"><label class="tit w84">审批意见：</label>
            <textarea id="approveComment"  name="approveComment"  type="text" class="text w300" value="" style=" width: 284px;"></textarea>
        </li>
        <li class="clearfix"><a href="javascript:;" class="btn btnDoApprove">确定</a><a href="javascript:;" onclick="MU.hide(this)"
                                                             class="btn btn-grey">取消</a></li>
    </ul>
</div>

<script type="text/javascript" src="${contextPath}/static/js/spark/OsDetecApprove_List.js?v=${buildTimestamp}"></script>
</body>
</html>
