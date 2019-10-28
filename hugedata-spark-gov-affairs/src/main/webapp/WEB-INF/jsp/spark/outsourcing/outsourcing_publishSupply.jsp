<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="p" uri="http://spark.hugedata.com.cn/tags/portal-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div class="cen ng-cloak" id="minServiceH" ng-app="outsourcingPublishSupplyModule" ng-controller="outsourcingPublishSupplyController">
        <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/serviceProject">检验检测</a> &gt;
            <span ng-if="!isModify">发布供应</span>
            <span ng-if="isModify">修改供应</span>
        </h1>
        <h2 class="se-title se-title-noborder">
            <span ng-if="!isModify">发布供应</span>
            <span ng-if="isModify">修改供应</span>
        </h2>
        <div class="se-block">
            <dl class="release-info">
                <dt>供应信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>选择服务领域：</label>
                            <input type="hidden" name="serviceField" id="serviceField" value="" />
                            <span class="select w260 fl">
                                    <select id="serviceFieldSelect">
                                        <option value="">请选择</option>
                                        <option value="SP_SERVICE_FIELD_CHEMISTRY">化学化工</option>
                                        <option value="SP_SERVICE_FIELD_CONSTRUCTION">建筑建材</option>
                                        <option value="SP_SERVICE_FIELD_NDT">无损检测</option>
                                        <option value="SP_SERVICE_FIELD_CONSUMER_GOODS">消费品</option>
                                        <option value="SP_SERVICE_FIELD_ENVIRON_HEALTH">环境检测与职业卫生</option>
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
                            <label class="tit"><em>*</em>供应类型：</label>
                            <label class="supplyType checkbox radio checked" value="SP_SUPPLY_TYPE_INSPECT_DETEC">检验检测产品</label>
                            <label class="supplyType checkbox radio" value="SP_SUPPLY_TYPE_TECH_SERVICE">技术服务</label>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>供应主题名称：</label>
                            <input id="supplyTheme" type="text" class="text w866" placeholder="输入供应主题名称，限字50个"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>供应简介：</label>
                            <textarea id="supplyBrief" class="w866" placeholder="输入供应的简介描述，限字100个"></textarea>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class="release-info">
                <dt>交易信息</dt>
                <dd>
                    <ul id="specArea" class="clearfix">
                        <%--<li class="clearfix">
                            <label class="tit">规格名称：</label>
                            <input id="specName_0" type="text" class="text w866" placeholder="输入规格名称"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>参考价格：</label>
                            <input id="referPrice_0" type="text" class="text w140" placeholder="输入单价"/>
                            <em class="m10 fl">每</em><input id="measureUnit_0" type="text" class="text w140" placeholder="输入计量单位"/>
                            <em class="m10 fl">价格单位：</em>
                            <input type="hidden" name="priceUnit" id="priceUnit_0" value="PRICE_UNIT_YUAN" />
                            <span class="select w140 fl" id="priceUnitSpan_0">
                                <select id="priceUnitSelect_0">
                                    <option value="PRICE_UNIT_YUAN">元</option>
                                    <option value="PRICE_UNIT_TEN_THOUSAND_YUAN">万元</option>
                                    <option value="PRICE_UNIT_MILLION_YUAN">百万元</option>
                                </select>
                            </span>
                            <label id="isNegotiablePrice_0" class="checkbox checkbox-radio ml10 fl" style="margin-top: 8px;">价格面议</label>
                        </li>--%>
                    </ul>
                    <li class="clearfix"><label class="tit">&nbsp;</label><a class="add add-standard" id="addSpec" href="javascript:;" ng-click="addSpec();">添加</a></li>
                </dd>
            </dl>
            <dl class="release-info">
                <dt>详细信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit fl"><em>*</em>上传图片：</label>
                            <div style="padding-left:130px;padding-top: 4px;">
                                <div id="uploadedImages"></div>
                                <div id="uploadImageWrapper">
                                    <iframe src="showUploadImage?callback=onImageUploaded" style="width: 180px;height: 30px" scrolling="no"> </iframe>
                                <%--<p:fileUpload inputIdForFileId="uploadImageFileId" inputIdForFileName="uploadImgeFileName"
                                                  allowedExtensions="images" width="200px" buttonText="选择文件"/>--%>
                                </div>
                            </div>
                            <p class="tips">最多可上传5张不同的图片；建议上传图片格式为 jpg、jpeg 或 png ;高宽为1:1像素，单张大小不超过1M;图片最好为白色背景或无背景</p>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>详细说明：</label>
                            <textarea id="detailDesc" class="w866" placeholder="输入详细说明"></textarea>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>搜索关键词：</label>
                            <input id="searchKey" type="text" class="text w866" placeholder="可输入多个服务关键词，供搜索和匹配，用分号分隔，限字60个"/>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class="release-info">
                <dt>联系信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>联系地址：</label>
                            <input type="hidden" name="addressProvince" id="addressProvince" value="" />
                            <span class="select w260 fl" id="addressProvinceSpan">
                                    <select id="addressProvinceSelect">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            <label class="tit w172"><em>*</em>详细地址：</label>
                            <input type="text" class="text w428" id="addressDetail" placeholder="输入详细联系地址，限字50个"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>联系人：</label>
                            <input type="text" class="text w240" id="contact" placeholder="输入联系人姓名，限字10个"/>
                            <label class="tit w172"><em>*</em>联系人电话：</label>
                            <input type="text" class="text w428" id="contactMobile" placeholder="输入联系人电话或手机号码"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>电子邮箱：</label>
                            <input type="text" class="text w866" id="email" placeholder="输入联系电子邮箱"/>
                        </li>
                    </ul>
                </dd>
            </dl>
            <div class="btn-block">
                <a ng-if="isModify" href="javascript:;" class="btn" ng-click="modify();">确认修改</a>
                <a ng-if="!isModify" href="javascript:;" class="btn" ng-click="publish();">确认发布</a>
                <a href="#" class="btn btn-grey">取消</a>
            </div>
        </div>
</div>
    <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/outsourcing/outsourcing_publishSupply.js?v=${buildTimestamp}"></script>
    <script>
        window.isModify = ${isModify};
        window.projectId = ${projectId};
        //==============================================================================
        // 上传图片处理
        //==============================================================================
        window.uploadImageData = [];
        window.MAX_IMAGES = 5;
        /**
         * 图片上传后的回调处理
         */
        window.onImageUploaded = function(err, resp) {
            if (err) {
                MU.msgTips("error", err);
                return;
            }
            if (resp.fileId && resp.fileName) {
                uploadImageData.push({
                    fileId: resp.fileId,
                    fileName: resp.fileName
                });
                window.loadUploadedImages();
            }
        };

        window.loadUploadedImages = function () {
            $("#uploadedImages").empty();
            $.each(window.uploadImageData, function(i, item) {
                var wrapper = $("<p>").appendTo($("#uploadedImages"));
                $("<span>").html(item.fileName).appendTo(wrapper);
                $("<span>").html("  ").appendTo(wrapper);
                $("<a>").attr("href", "javascript:;")
                    .html("删除")
                    .css("color", "#107aee")
                    .on("click", function() {
                        for (var i = 0; i < window.uploadImageData.length; i++) {
                            if (window.uploadImageData[i].fileId == item.fileId) {
                                window.uploadImageData.splice(i, 1);
                            }
                        }
                        window.loadUploadedImages();
                    })
                    .appendTo(wrapper);
            });

            if (window.uploadImageData.length >= window.MAX_IMAGES) {
                $("#uploadImageWrapper").hide();
            } else {
                $("#uploadImageWrapper").show();
            }
        };
    </script>
</body>
</html>
