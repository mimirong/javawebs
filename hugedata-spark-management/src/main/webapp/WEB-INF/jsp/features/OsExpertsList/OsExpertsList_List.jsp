<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>专家名录</title>
    <script>
        currentMenu = "achieve_transfer/experts_list";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
    <h2 class="title">专家名录</h2>
    <div class="column_tab_con">
        <div class="search-box clearfix">
            <a class="btn op-btn right_float btnDeleteSelected" title="删除专家">删除专家</a>
            <a href="javascript:;" class="btn op-btn right_float op-export btnAdd" title="发布专家">发布专家</a>
        </div>
        <table id="data" class="column_tab_table" width="100%">
            <thead>
            <tr>
                <td>
                    <label class="checkbox btnSelectAll"></label>
                </th>
                <td>ID</th>
                <td style="width:120px">照片</td>
                <td>姓名</td>
                <td>工作单位</td>
                <td>是否可见</td>
                <td style="width:210px;">操作</td>
            </tr>
            </thead>
        </table>
    </div>

    <!--  Add or Modify  -->
    <div class="alert" style="display:none; width:650px;" id="addModifyDialog" name="wh_alert">
        <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addModifyDialogTitle"></span></div>
        <div class="pop-content popupWrapper">
            <ul class="form clearfix">
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>姓名：</label>
                    <input id="name" name="name" type="text" class="text w460" placeholder="限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>职称：</label>
                    <input id="job_title" name="job_title" type="text" class="text w460" placeholder="限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>部门：</label>
                    <input id="dept" name="dept" type="text" class="text w460" placeholder="限字20个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>职务：</label>
                    <input id="position" name="position" type="text" class="text w460" placeholder="限字20个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>专业领域：</label>
                    <span class="select" style="width:478px;">
                        <select id="professionFieldId">
                            <option value="">--请选择--</option>
                            <option value="SP_SERVICE_FIELD_CHEMISTRY">化学化工</option>
                            <option value="SP_SERVICE_FIELD_CONSTRUCTION">建筑建材</option>
                            <option value="SP_SERVICE_FIELD_NDT">无损检测</option>
                            <option value="SP_SERVICE_FIELD_CONSUMER_GOODS">消费品</option>
                            <option value="SP_SERVICE_FIELD_ENVIRON_HEALTH">环境监测与职业卫生</option>
                            <option value="SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT">生物医药及器械</option>
                            <option value="SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL">电子电气及机械</option>
                            <option value="SP_SERVICE_FIELD_EMC">电磁兼容（EMC）</option>
                            <option value="SP_SERVICE_FIELD_SPIN_FIBER">纺织及纤维</option>
                            <option value="SP_SERVICE_FIELD_SOFTWARE_INFO">软件信息</option>
                            <option value="SP_SERVICE_FIELD_METAL_MATERIAL">金属材料</option>
                            <option value="SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS">食品及农产品</option>
                            <option value="SP_SERVICE_FIELD_OTHER">其他</option>
                        </select>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="tit w84">擅长领域：</label>
                    <input id="expert_field" name="expert_field" type="text" class="text w460" placeholder="限字30个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84">专家称号：</label>
                    <input id="expert_title" name="expert_title" type="text" class="text w460" placeholder="限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w84"><em>*</em>个人简介：</label>
                    <textarea id="brief" name="brief" class="w300" placeholder="限字500个"></textarea>
                </li>

                <li class="clearfix" id="imageFileWrapper">
                    <label class="tit w84"><em>*</em>上传图片：</label>
                    <div style="float:left">
                        <div id="fileDiv">
                            <m:fileUpload inputIdForFileName="fileName" inputIdForFileId="fileId" allowedExtensions="images" maxFileSize="10M" />
                            <span id="fileTip" style="color:#f00">建议图片尺寸140*170，大小不能超过10M，建议不超过1M</span>
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
    <script type="text/javascript" src="${contextPath}/static/js/spark/OsExpertsList_List.js?v=${buildTimestamp}"></script>
</body>
</html>
