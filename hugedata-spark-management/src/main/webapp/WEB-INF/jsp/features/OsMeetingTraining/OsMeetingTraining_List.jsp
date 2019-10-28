<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>会议培训</title>
    <script type="text/javascript" src="${contextPath}/static/laydate/laydate.js"></script>
    <script>
        currentMenu = "meeting_training";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
    <h2 class="title">会议培训</h2>
    <div class="column_tab_con">
        <div class="search-box clearfix">
            <a class="btn op-btn right_float btnDeleteSelected" title="删除培训">删除培训</a>
            <a href="javascript:;" class="btn op-btn right_float op-conference btnAdd" title="发布培训">发布培训</a>
        </div>
        <table id="data" class="column_tab_table" width="100%">
            <thead>
            <tr>
                <td>
                    <label class="checkbox btnSelectAll"></label>
                </th>
                <td>ID</th>
                <td style="width:120px">图片</td>
                <td>名称</td>
                <td>报名时间<br/>是否过期</td>
                <td>是否可见</td>
                <td style="width:210px;">操作</td>
            </tr>
            </thead>
        </table>
    </div>

    <!--  Add or Modify  -->
    <div class="alert" style="display:none; width:680px;" id="addModifyDialog" name="wh_alert">
        <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addModifyDialogTitle"></span></div>
        <div class="pop-content1" style="">
            <div class="tab-div-btn">
                <label style="display: inline-block;margin-right: 8px;width: 97px;text-align: right;">所属栏目：</label>
                <a id="tab_jianjie" class="on">课程简介</a>
                <a id="tab_neirong">课程内容</a>
                <a id="tab_shixiang">注意事项</a>
                <a id="tab_women">联系我们</a></div>
            <ul class="form tab-form clearfix" style="display: block;">
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>培训名称：</label>
                    <input id="name" name="name" type="text" class="text w440" placeholder="输入培训名称，限字50个" maxlength="50"/></li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>课程类型：</label>
                    <span class="select" style="width:459px;">
                <select id="trainingType">
                    <option value="">--请选择--</option>
                    <option value="SPECIALTY_TECH">专业技术</option>
                    <option value="TECH_POLICY">科技政策</option>
                    <option value="AUTHENTICATION">认证认定</option>
                    <option value="OCCUPATIONAL_SKILLS">职业技能</option>
                    <option value="OCCUPATIONAL_QUALIFICATIONS">职业资格</option>
                    <option value="TECH_MANAGEMENT">科技管理</option>
                    <option value="OTHERS">其他</option>
                </select>
            </span>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>适用对象：</label>
                    <input id="adaptObject" name="adaptObject" type="text" class="text w440" placeholder="限字50个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>报名时间：</label>
                    <%--<input id="beginDate3" type="text" class="text date con-date" placeholder="选择报名开始时间"/>&nbsp;-&nbsp;<input id="beginDate4" type="text" class="text date con-date" placeholder="选择报名截止时间"/>--%>
                    <input id="registrationTime" type="text" class="hugedate-laydate-icon hugedata-date"  placeholder="选择报名开始时间" >&nbsp;-&nbsp;
                    <input id="registrationDeadline" type="text" class="hugedate-laydate-icon hugedata-date"  placeholder="选择报名截止时间">
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>培训时间：</label>
                    <%--<input id="beginDate5" type="text" class="text date con-date" placeholder="选择培训开始时间"/>&nbsp;-&nbsp;<input id="beginDate6" type="text" class="text date con-date" placeholder="选择培训结束时间"/>--%>
                    <input id="trainingStartTime" type="text" class="hugedate-laydate-icon hugedata-date"  placeholder="选择培训开始时间">&nbsp;-&nbsp;
                    <input id="trainingEndTime" type="text" class="hugedate-laydate-icon hugedata-date"  placeholder="选择培训结束时间">
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>培训地点：</label>
                    <input id="address" type="text" class="text w440" placeholder="培训详细地点，限字100个" maxlength="100"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>报名方式：</label>
                    <label id="registrationWay_offLine" class="radio checkbox checked fl" style="margin-top: 8px;">线下报名</label><label id="registrationWay_onLine" class="radio checkbox fl" style="margin-top: 8px;">在线报名</label>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>限制人数：</label>
                    <input id="maxPlayers" type="text" class="text w440" placeholder="输入人数"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>课程形式：</label>
                    <input id="trainingWay" type="text" class="text w440" placeholder="限字20个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>课程价格：</label>
                    <input id="price" type="text" class="text w234 fl" placeholder="输入价格"/>
                    <label class="tit" style="width: 50px;">元每人</label><label id="radioFree" class="radio checkbox fl" style="margin-top: 8px;">免费</label>
                </li>
                <li class="clearfix" id="imageFileWrapper" style="margin-bottom:0;">
                    <label class="tit w97"><em>*</em>上传图片：</label>
                    <div style="float:left;">
                        <div id="fileDiv">
                            <m:fileUpload inputIdForFileName="fileName" inputIdForFileId="fileId" allowedExtensions="images" maxFileSize="10M" width="420px" />
                            <span id="fileTip" style="color:#f00">建议图片尺寸1920x1080，大小不能超过10M，建议不超过1M</span>
                        </div>
                    </div>
                </li>
                <!--<li class="clearfix"><label class="tit w97">&nbsp;</label><a href="" class="btn">发布文章</a></li>-->
            </ul>
            <ul class="form tab-form clearfix">
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>课程内容：</label>
                    <textarea id="brief" class="w440" placeholder="输入课程内容描述，限字1000个"></textarea>
                </li>
                <li class="clearfix">
                    <label class="tit w97">附件：</label>
                    <div  style="float:left;">
                        <div id="uploadAttachmentWrapper_brief">
                            <m:fileUpload inputIdForFileId="briefFileId" inputIdForFileName="briefFileName" maxFileSize="20M" width="420px" />
                            <span id="fileTip" style="color:#f00">请上传text/pdf/word/xls格式文档，附件最大不能超过20MB</span>
                        </div>
                    </div>
                </li>
            </ul>
            <ul class="form tab-form clearfix">
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>注意事项：</label>
                    <textarea id="announcements" class="w440" placeholder="输入注意事项描述，限字1000个"></textarea>
                </li>
                <li class="clearfix">
                    <label class="tit w97">附件：</label>
                    <div style="float:left;">
                        <div id="uploadAttachmentWrapper_anno">
                            <m:fileUpload inputIdForFileId="annoFileId" inputIdForFileName="annoFileName" maxFileSize="20M" width="420px" />
                            <span id="fileTip" style="color:#f00">请上传text/pdf/word/xls格式文档，附件最大不能超过20MB</span>
                        </div>
                    </div>
            </ul>
            <ul class="form tab-form clearfix">
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>联系人：</label>
                    <input id="contact" type="text" class="text w440" placeholder="输入联系人姓名，限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>联系人电话：</label>
                    <input id="phone" type="text" class="text w440" placeholder="输入联系人座机电话号码"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97">联系人手机：</label>
                    <input id="mobile" type="text" class="text w440" placeholder="输入联系人手机号码"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>联系人邮箱：</label>
                    <input id="email" type="text" class="text w440" placeholder="输入联系人邮箱"/>
                </li>
            </ul>
            <div style="text-align: center;padding:10px 0;">
                <label class="tit w97">&nbsp;</label>
                <a class="btn btnDoAdd" href="javascript:;">确定添加</a>
                <a class="btn btnDoModify" href="javascript:;">确定修改</a>
                <a class="btn btn-gray" onclick="MU.hide(this)" href="javascript:;">取消</a>
            </div>
        </div>
    </div>

    <script>
        //执行一个laydate实例
        laydate.render({
            elem: '#registrationTime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#registrationDeadline'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#trainingStartTime'
            ,type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#trainingEndTime' //指定元素
            ,type: 'datetime'
        });
    </script>
    <script type="text/javascript" src="${contextPath}/static/js/spark/OsMeetingTraining_List.js?v=${buildTimestamp}"></script>
</body>
</html>
