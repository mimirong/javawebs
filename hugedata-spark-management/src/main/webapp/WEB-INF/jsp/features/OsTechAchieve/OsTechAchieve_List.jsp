<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>技术成果</title>
    <script>
        currentMenu = "achieve_transfer/tech_achieve";
        currentSystem = "OUTSOURCING";
    </script>
</head>
<body>
    <h2 class="title">技术成果</h2>
    <div class="column_tab_con">
        <div class="search-box clearfix">
            <a class="btn op-btn right_float btnDeleteSelected" title="删除成果">删除成果</a>
            <a href="javascript:;" class="btn op-btn right_float op-export btnAdd" title="发布技术成果">发布成果</a>
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
                <td>专业领域</td>
                <td>是否可见</td>
                <td style="width:210px;">操作</td>
            </tr>
            </thead>
        </table>
    </div>

    <!--  Add or Modify  -->
    <div class="alert" style="display:none; width:620px;" id="addModifyDialog" name="wh_alert">
        <div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span class="addModifyDialogTitle"></span></div>
        <div class="pop-content">
            <ul class="form clearfix" style="">
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>项目名称：</label>
                    <input id="name" name="name" type="text" class="text w440" placeholder="限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>专业领域：</label>
                    <span class="select" style="width:459px;">
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
                    <label class="tit w97"><em>*</em>成果形式：</label>
                    <span class="select" style="width:459px;">
                        <select id="achieveType">
                            <option value="">--请选择--</option>
                            <option value="NEW_PRODUCT">新产品</option>
                            <option value="NEW_TECH">新技术</option>
                            <option value="NEW_CRAFT">新工艺</option>
                            <option value="NEW_MATERIAL">新材料</option>
                            <option value="INVENTION_PATENT">发明专利</option>
                            <option value="UTILITY_MODEL">实用新型</option>
                            <option value="APPEARANCE_DESIGN">外观设计</option>
                            <option value="TECH_SECRET">技术秘密</option>
                        </select>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>投资额：</label>
                    <input id="investmentVolume" type="text" class="text w234 fl" placeholder="输入金额"/>
                    <label class="tit w97"><em>*</em>金额单位：</label>
                    <span class="select" style="width:102px;">
                        <select id="monetaryUnit">
                            <option value="YUAN">元</option>
                            <option value="TEN_THOUSND_YUAN">万元</option>
                            <option value="MILLION_YUAN">百万元</option>
                        </select>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>技术成熟度：</label>
                    <span class="select" style="width:459px;">
                        <select id="maturityStage">
                            <option value="">--请选择--</option>
                            <option value="MATURE_1">一般</option>
                            <option value="MATURE_2">成熟</option>
                            <option value="MATURE_3">很成熟</option>
                            <option value="MATURE_4">非常成熟</option>
                        </select>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>预期效果：</label>
                    <input id="desiredEffect" name="desiredEffect" type="text" class="text w440" placeholder="预期效果描述，限字100个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>适应对象：</label>
                    <input id="adaptObject" name="adaptObject" type="text" class="text w440" placeholder="限字20个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>合作方式：</label>
                    <input id="cooperationWays" name="cooperationWays" type="text" class="text w440" placeholder="限字20个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>联系人：</label>
                    <input id="contact" name="contact" type="text" class="text w440" placeholder="限字10个"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>联系电话：</label>
                    <input id="phone" name="phone" type="text" class="text w440"/>
                </li>
                <li class="clearfix">
                    <label class="tit w97"><em>*</em>方案详述：</label>
                    <textarea id="brief" name="brief" class="w440" placeholder="限字500个"></textarea>
                </li>

                <li class="clearfix" id="imageFileWrapper">
                    <label class="tit w97"><em>*</em>上传图片：</label>
                    <div style="float:left; width:450px;">
                        <div id="fileDiv">
                            <m:fileUpload inputIdForFileName="fileName" inputIdForFileId="fileId" allowedExtensions="images"
                            		maxFileSize="10M" width="420px" />
                            <span id="fileTip" style="color:#f00">建议图片尺寸1920x1080，大小不能超过10M，建议不超过1M</span>
                        </div>
                    </div>
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
    <script type="text/javascript" src="${contextPath}/static/js/spark/OsTechAchieve_List.js?v=${buildTimestamp}"></script>
</body>
</html>
