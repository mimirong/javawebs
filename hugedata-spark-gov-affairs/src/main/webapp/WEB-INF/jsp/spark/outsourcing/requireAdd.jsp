<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
   
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/requireList">需求大厅</a> &gt; <span>发布需求</span></h1>

    <div class="cen" id="minServiceH">
        <h2 class="se-title se-title-noborder"><span>发布需求</span></h2>
        <div class="se-block">
            <dl class="release-info">
                <dt>需求信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>选择需求领域：</label>
                            <span class="select w260 fl requireArea">
                                <select>
                                    <option value="请选择">请选择</option>
                                    <option value="化学化工">化学化工</option>
                                    <option value="建筑建材">建筑建材</option>
                                    <option value="无损检测">无损检测</option>
                                    <option value="消费品">消费品</option>
                                    <option value="环境监测与职业卫生">环境监测与职业卫生</option>
                                    <option value="生物医药及器械">生物医药及器械</option>
                                    <option value="电子电气及机械">电子电气及机械</option>
                                    <option value="电磁兼容（EMC）">电磁兼容（EMC）</option>
                                    <option value="纺织及纤维">纺织及纤维</option>
                                    <option value="软件信息">软件信息</option>
                                    <option value="金属材料">金属材料</option>
                                    <option value="食品及农产品">食品及农产品</option>
                                    <option value="其他">其他</option>
                                </select>
                            </span>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>需求主题名称：</label>
                            
                            <input type="hidden"  id="requireId" value="${requireId}"/>
                            <input type="text" class="text w866" id="requireTitle" placeholder="输入需求主题名称，限字50个" maxlength="50"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>截止日期：</label>
                            <input type="text" class="text w240 date" id="deadDate" placeholder="选择报价截止日期"/>
                            <label class="tit" style="width: 217px;"><em>*</em>交付日期：</label>
                            <input type="text" class="text w240 date" id="offerDate" placeholder="选择交付日期"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>期望最高总价：</label>
                            <input type="text" class="text w240" id="hopePrice" placeholder="输入期望最高总价"/><label class="checkbox ml10 fl" id="isChat" style="margin-top: 8px;">面议</label>
                            <label class="tit"><em>*</em>价格单位：</label>
                            <span class="select w160 fl priceUnit"><select><option>元</option><option>万元</option><option>百万元</option></select></span><label id="isQuick" class="checkbox  ml10 fl" style="margin-top: 8px;">加急</label>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class="release-info">
                <dt>需求产品/服务信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>需求描述：</label>
                            <textarea class="w866" id="requireDesc" placeholder="输入需求的详细描述，限字500个"></textarea>
                        </li>
                        <li class="clearfix">
                            <label class="tit">添加附件：</label>
                            <div style="padding-left:130px;padding-top: 4px;">
                                <div id="uploadedRequireAtt"></div>
                                <div id="uploadRequireAttWrapper">
                                    <iframe src="showUploadRequireAtt?callback=onRequireAttUploaded" style="width: 180px;height: 30px" scrolling="no"> </iframe>
                                <%--<p:fileUpload inputIdForFileId="uploadImageFileId" inputIdForFileName="uploadImgeFileName"
                                                  allowedExtensions="images" width="200px" buttonText="选择文件"/>--%>
                                </div>
                            </div>
                            <p class="tips">请上传text/pdf/word/xls格式文档</p>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>搜索关键词：</label>
                            <input type="text" class="text w866" id="keyWord" placeholder="可输入多个服务关键词，供搜索和匹配，用分号分隔，限字60个" maxlength="500"/>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class="release-info">
                <dt>商务信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>付款方式：</label>
                            <span class="select w160 fl paymentMethod">
                                <select>
                                    <option value="请选择">请选择</option>
                                    <option value="现金">现金</option>
                                    <option value="转账">转账</option>
                                </select>
                            </span>
                            <label class="tit"><em>*</em>发票类型：</label>
                            <span class="select w160 fl invoiceType">
                                <select>
                                    <option value="请选择">请选择</option>
                                    <option value="无">无</option>
                                    <option value="增值发票">增值发票</option>
                                    <option value="普通发票">普通发票</option>
                                </select>
                            </span>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>运费承担方：</label>
                            <span class="select w160 fl freightPayer">
                                <select>
                                    <option value="请选择">请选择</option>
                                    <option value="供应方">供应方</option>
                                    <option value="采购方">采购方</option>
                                </select>
                            </span>
                            <label class="tit"><em>*</em>货到付款：</label>
                            <label id="noCod" class="checkbox radio checked">否</label>
                            <label id="isCod" class="checkbox radio">是</label>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>需求数量：</label>
                            <input type="text" id="requireNum" class="text w140" placeholder="输入数量" maxlength="10"/>
                            <label class="tit"><em>*</em>计量单位：</label>
                            <input type="text" id="numUnit" class="text w140" placeholder="如：个、台、次等" maxlength="10"/>
                        </li>
                    </ul>
                </dd>
            </dl>

            <dl class="release-info">
                <dt>联系信息</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="clearfix">
                            <label class="tit"><em>*</em>收货地址：</label>
                            <span class="select w260 fl contactArea">
                                <select>
                                    <option value="请选择">请选择</option>
                                    <option value="湖南省">湖南省</option>
                                    <option value="北京">北京</option>
                                    <option value="天津">天津</option>
                                    <option value="上海">上海</option>
                                    <option value="重庆">重庆</option>
                             		<option value="深圳">深圳</option>
                                    <option value="江苏省">江苏省</option>
                                    <option value="浙江省">浙江省</option>
                                    <option value="福建省">福建省</option>
                                    <option value="广东省">广东省</option>
                                    <option value="湖北省">湖北省</option>
                                     <option value="河北省">河北省</option>
                                    <option value="山西省">山西省</option>
                                    <option value="内蒙古">内蒙古</option>
                                    <option value="辽宁省">辽宁省</option>
                                    <option value="吉林省">吉林省</option>
                                    <option value="黑龙江省">黑龙江省</option>
                                    <option value="安徽省">安徽省</option>
                                    <option value="江西省">江西省</option>
                                     <option value="广西省">广西省</option>
                                    <option value="海南省">海南省</option>
                                    <option value="四川省">四川省</option>
                                    <option value="贵州省">贵州省</option>
                                    <option value="云南省">云南省</option>
                                    <option value="西藏">西藏</option>
                                    <option value="陕西省">陕西省</option>
                                    <option value="甘肃省">甘肃省</option>
                                     <option value="青海省">青海省</option>
                                    <option value="宁夏">宁夏</option>
                                    <option value="新疆">新疆</option>
                                    <option value="香港">香港</option>
                                    <option value="澳门">澳门</option>
                                    <option value="台湾省">台湾省</option>
                                </select>
                            </span>
                            <label class="tit w172"><em>*</em>详细地址：</label>
                            <input id="contactAddr" type="text" class="text w428" placeholder="输入详细联系地址，限字50个" maxlength="50"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>联系人：</label>
                            <input id="contacter" type="text" class="text w240" placeholder="输入联系人姓名，限字10个" maxlength="10"/>
                            <label class="tit w172"><em>*</em>联系人电话：</label>
                            <input id="contactPhone" type="text" class="text w428" placeholder="输入联系人电话或手机号码" maxlength="30"/>
                        </li>
                        <li class="clearfix">
                            <label class="tit"><em>*</em>电子邮箱：</label>
                            <input id="email" type="text" class="text w866" placeholder="输入联系电子邮箱" maxlength="40"/>
                        </li>
                    </ul>
                </dd>
            </dl>
            <div class="btn-block">
                <a id="btnDoAdd" href="javascript:;" class="btn">确认发布</a><a href="#" class="btn btn-grey">取消</a>
            </div>
        </div>
    </div>
    <script>
    $("input.date").datepicker();
</script>

 <script>
        //==============================================================================
        // 上传图片处理
        //==============================================================================
        window.uploadRequireAttData = [];
        window.MAX_IMAGES = 5;
        
        
        /**
         * 图片上传后的回调处理
         */
        window.onRequireAttUploaded = function(err, resp) {
            if (err) {
                MU.msgTips("error", err);
                return;
            }
            if (resp.fileId && resp.fileName) {
                uploadRequireAttData.push({
                    fileId: resp.fileId,
                    fileName: resp.fileName
                });
                window.loadUploadedAtts();
            }
        };

        window.loadUploadedAtts = function () {
            $("#uploadedRequireAtt").empty();
            $.each(window.uploadRequireAttData, function(i, item) {
                var wrapper = $("<p>").appendTo($("#uploadedRequireAtt"));
                $("<span>").html(item.fileName).appendTo(wrapper);
                $("<span>").html("  ").appendTo(wrapper);
                $("<a>").attr("href", "javascript:;")
                    .html("删除")
                    .css("color", "#107aee")
                    .on("click", function() {
                        for (var i = 0; i < window.uploadRequireAttData.length; i++) {
                            if (window.uploadRequireAttData[i].fileId == item.fileId) {
                                window.uploadRequireAttData.splice(i, 1);
                            }
                        }
                        window.loadUploadedAtts();
                    })
                    .appendTo(wrapper);
            });

            if (window.uploadRequireAttData.length >= window.MAX_IMAGES) {
                $("#uploadRequireAttWrapper").hide();
            } else {
                $("#uploadRequireAttWrapper").show();
            }
        };
    </script>
    <script src="${contextPath}/static/js/outsourcing/requireAdd.js?v=${buildTimestamp}"></script>
</body>
</html>
