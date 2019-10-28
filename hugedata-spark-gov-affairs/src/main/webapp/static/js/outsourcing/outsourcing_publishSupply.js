angular.module("outsourcingPublishSupplyModule", [])
    .controller("outsourcingPublishSupplyController", ["$scope", function ($scope) {

        $scope.PROVINCES = ["北京市","天津市","上海市","重庆市","河北省","山西省","内蒙古","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西","海南省","四川省","贵州省","云南省","西藏","陕西省","甘肃省","青海省","宁夏","新疆","香港","澳门","台湾省"];//全国省份
        $scope.SPEC_SIGN = 0;// 规格标识
        $scope.uploadImageData = [];// 照片数组
        $scope.specData = [];// 规格数组

        $scope.isLoading = false;//是否显示正在加载
        $scope.isModify = window.isModify;// 是否修改
        $scope.projectId = window.projectId;// 服务项目ID

        /**
         * 服务领域下拉菜单.
         */
        $("#serviceFieldSelect").siblings("ul").find("li").on("click", function() {
            var value = $(this).attr("data-value");
            $("#serviceField").val(value);
        });

        /**
         * 初始化省份下拉列表.
         */
        $scope.initProvinces = function () {
            for (var i = 0, size = $scope.PROVINCES.length; i < size; i++) {
                $("#addressProvinceSelect").append('<option value="' + $scope.PROVINCES[i] + '">' + $scope.PROVINCES[i] + '</option>');
            }
            $('#addressProvinceSpan').children('select').selectList();
            $("#addressProvinceSelect").siblings("ul").find("li").on("click", function () {
                var value = $(this).attr("data-value");
                $("#addressProvince").val(value);
            });
        };

        /**
         * 添加规格
         */
        $scope.addSpec = function () {
            $scope.SPEC_SIGN += 1;
            var newSpecAreaHtml = "";
            var newSpecSign = $scope.SPEC_SIGN - 1;
            newSpecAreaHtml += '<div id="specDiv_' + newSpecSign + '" data-value="' + newSpecSign + '" class="SPECDIV">';
            newSpecAreaHtml += '<li class="clearfix">';
            newSpecAreaHtml += '<label class="tit"><em>*</em>规格名称：</label>';
            newSpecAreaHtml += '<input id="specName_' + newSpecSign + '" type="text" class="text w866" placeholder="输入规格名称"/>';
            newSpecAreaHtml += '</li>';
            newSpecAreaHtml += '<li class="clearfix">';
            newSpecAreaHtml += '<label class="tit"><em>*</em>参考价格：</label>';
            newSpecAreaHtml += '<input id="referPrice_' + newSpecSign + '" type="text" class="text w140" placeholder="输入单价"/>';
            newSpecAreaHtml += '<em class="m10 fl">每</em><input id="measureUnit_' + newSpecSign + '" type="text" class="text w140" placeholder="输入计量单位"/>';
            newSpecAreaHtml += '<em class="m10 fl">价格单位：</em>';
            newSpecAreaHtml += '<input type="hidden" name="priceUnit" id="priceUnit_' + newSpecSign + '" value="PRICE_UNIT_YUAN" />';
            newSpecAreaHtml += '<span class="select w140 fl" id="priceUnitSpan_' + newSpecSign + '">';
            newSpecAreaHtml += '<select id="priceUnitSelect_' + newSpecSign + '">';
            newSpecAreaHtml += '<option value="PRICE_UNIT_YUAN">元</option>';
            newSpecAreaHtml += '<option value="PRICE_UNIT_TEN_THOUSAND_YUAN">万元</option>';
            newSpecAreaHtml += '<option value="PRICE_UNIT_MILLION_YUAN">百万元</option>';
            newSpecAreaHtml += '</select>';
            newSpecAreaHtml += '</span>';
            newSpecAreaHtml += '<label id="isNegotiablePrice_' + newSpecSign + '" class="checkbox checkbox-radio ml10 fl" style="margin-top: 8px;">价格面议</label>';
            newSpecAreaHtml += '<a class="del" href="javascript:;" id="deleteSpec_' + newSpecSign + '"></a>';
            newSpecAreaHtml += '</li>';
            newSpecAreaHtml += '</div>';
            $("#specArea").append(newSpecAreaHtml);

            // 刷新价格单位List
            $('#priceUnitSpan_' + newSpecSign).children('select').selectList();

            // 价格单位下拉菜单
            $("#priceUnitSelect_" + newSpecSign).siblings("ul").find("li").on("click", function () {
                var value = $(this).attr("data-value");
                $("#priceUnit_" + newSpecSign).val(value);
            });

            // 价格面议
            $("#isNegotiablePrice_" + newSpecSign).on("click", function () {
                $("#referPrice_" + newSpecSign).val('');
                $("#referPrice_" + newSpecSign).html('');
                $("#measureUnit_" + newSpecSign).val('');
                $("#measureUnit_" + newSpecSign).html('');
                if ($("#isNegotiablePrice_" + newSpecSign).hasClass("checked")) {
                    $("#referPrice_" + newSpecSign).attr('disabled', false);
                    $("#measureUnit_" + newSpecSign).attr('disabled', false);
                    $("#priceUnitSelect_" + newSpecSign).attr('disabled', false);
                } else {
                    $("#referPrice_" + newSpecSign).attr('disabled', true);
                    $("#measureUnit_" + newSpecSign).attr('disabled', true);
                    $("#priceUnitSelect_" + newSpecSign).attr('disabled', true);
                }
            });

            // 删除规格
            $("#deleteSpec_" + newSpecSign).on("click", function () {
                $(this).closest('div').remove();
            });
        };

        $scope.publish = function () {
            $scope.isLoading = true;
            var data = $scope.checkParams();
            if (data == null) {
                return;
            }

            $.ajax({
                url:contextPath + "/outsourcing/submitServiceProject",
                type:"post",
                dataType:"json",
                data: {data: JSON.stringify(data)},
                success:function (resp) {
                    $scope.isLoading = false;
                    if (resp && resp.result == 0) {
                        MU.msgTips("success", "提交成功", 1000);
                        location.href = contextPath + "/outsourcing/serviceProject";
                    } else {
                        MU.msgTips("error", resp.message);
                    }
                },
                error:function () {
                    $scope.isLoading = false;
                    MU.msgTips("error", "发布供应失败，请稍后重试");
                }
            });
        };

        $scope.modify = function () {
            $scope.isLoading = true;
            var data = $scope.checkParams();
            if (data == null) {
                return;
            }

            $.ajax({
                url:contextPath + "/outsourcing/modifyServiceProject",
                type:"post",
                dataType:"json",
                data: {data: JSON.stringify(data)},
                success:function (resp) {
                    $scope.isLoading = false;
                    if (resp && resp.result == 0) {
                        MU.msgTips("success", "修改成功", 1000);
                        location.href = contextPath + "/outsourcing/serviceProject";
                    } else {
                        MU.msgTips("error", resp.message);
                    }
                },
                error:function () {
                    $scope.isLoading = false;
                    MU.msgTips("error", "修改供应失败，请稍后重试");
                }
            });
        };

        $scope.checkParams = function () {
            // 服务领域
            var serviceField = $("#serviceField").val();
            if (serviceField == "") {
                MU.msgTips("warn", "请选择服务领域", 1200);
                return null;
            }

            // 服务类型
            var supplyType = "";
            $(".supplyType").each(function (pos, selector) {
                if($(selector).hasClass('checked')){
                    supplyType = $(selector).attr("value");
                }
            });
            if (supplyType == "") {
                supplyType = "SP_SUPPLY_TYPE_INSPECT_DETEC";//默认检验检测
            }

            // 供应主题名称
            var supplyTheme = $("#supplyTheme").val();
            if (supplyTheme == "") {
                MU.msgTips("warn", "请输入供应主题名称，限字50个", 1200);
                return null;
            } else if (supplyTheme.length > 100) {
                MU.msgTips("warn", "供应主题名称不得超过50个", 1200);
                return null;
            }

            // 供应简介
            var supplyBrief = $("#supplyBrief").val();
            if (supplyBrief == "") {
                MU.msgTips("warn", "请输入供应简介，限字100个", 1200);
                return null;
            } else if (supplyBrief.length > 100) {
                MU.msgTips("warn", "供应简介字数不得超过100个", 1200);
                return null;
            }

            // 规格
            $scope.specData = [];
            $(".SPECDIV").each(function () {
                var specSign = $(this).attr("data-value");
                var specItemData = $scope.checkSpecParams(specSign);
                if (specItemData != null) {
                    $scope.specData.push(specItemData);
                }else{
                	 $scope.specData = [];
                }
            });
            
            if($scope.specData.length == 0){
            	return null;
            }

            // 上传图片
            $scope.uploadImageData = window.uploadImageData;
            if ($scope.uploadImageData == null || $scope.uploadImageData.length == 0) {
                MU.msgTips("warn", "请至少上传一张照片", 1200);
                return null;
            }

            // 详细说明
            var detailDesc = $.trim($("#detailDesc").val());
            if (detailDesc == "") {
                MU.msgTips("warn", "请输入详细说明", 1200);
                return null;
            }
            
            if (detailDesc.length > 500) {
                MU.msgTips("error", "详细说明长度超过了500个字符", 1200);
                $("#detailDesc").focus();
                return null;
            }
            

            // 搜索关键词，用分号分隔
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                MU.msgTips("warn", "请输入搜索关键词", 1200);
                return null;
            }

            // 联系地址省份
            var addressProvince = $("#addressProvince").val();
            if (addressProvince == "") {
                MU.msgTips("warn", "请选择联系地址", 1200);
                return null;
            }

            // 联系地址-详细地址
            var addressDetail = $("#addressDetail").val();
            if (addressDetail == "") {
                MU.msgTips("warn", "请输入详细地址", 1200);
                return null;
            } else if (addressDetail.length > 50) {
                MU.msgTips("warn", "详细地址字数不得超过50个", 1200);
                return null;
            }

            // 联系人姓名
            var contact = $("#contact").val();
            if (contact == "") {
                MU.msgTips("warn", "请输入联系人姓名", 1200);
                return null;
            } else if (contact.length > 10) {
                MU.msgTips("warn", "联系人姓名字数不得超过10个", 1200);
                return null;
            }

            // 联系人电话
            var contactMobile = $("#contactMobile").val();
            if (contactMobile == "") {
                MU.msgTips("warn", "请输入联系人电话", 1200);
                return null;
            } else if (!/^\d{6,}$/.test(contactMobile)) {
                MU.msgTips("warn", "联系人电话不正确", 1200);
                return null;
            }

            // 电子邮箱
            var email = $("#email").val();
            if (email == "") {
                MU.msgTips("warn", "请输入联系电子邮箱", 1200);
                return null;
            }

            return {
                projectId: $scope.projectId,
                serviceField: serviceField,
                supplyType: supplyType,
                supplyTheme: supplyTheme,
                supplyBrief: supplyBrief,
                specData: $scope.specData,
                uploadImageData: JSON.stringify($scope.uploadImageData),
                detailDesc: detailDesc,
                searchKey: searchKey,
                addressProvince: addressProvince,
                addressDetail: addressDetail,
                contact: contact,
                contactMobile: contactMobile,
                email: email
            };
        };

        /**
         * 检查指定编号的规格信息
         * @param index     序列标识
         */
        $scope.checkSpecParams = function (index) {
            // 规格名称
            var specName = $("#specName_" + index).val();
            if (specName == "") {
                MU.msgTips("warn", "请输入规格名称", 1200);
                return null;
            }

            // 价格
            var isNegotiablePrice = 0; // 价格是否面议
            var referPrice = $("#referPrice_"+index).val();// 单价
            var measureUnit = $("#measureUnit_"+index).val();// 计量单位
            var priceUnit = $("#priceUnit_"+index).val();// 价格单位

            if ($("#isNegotiablePrice_" + index).hasClass("checked")) {
                isNegotiablePrice = 1;
            } else {
                isNegotiablePrice = 0;
                if (referPrice == "") {
                    MU.msgTips("warn", "请输入单价", 1200);
                    return null;
                }

                if (measureUnit == "") {
                    MU.msgTips("warn", "请输入计量单位", 1200);
                    return null;
                }

                if (priceUnit == "") {
                    MU.msgTips("warn", "请输入价格单位", 1200);
                    return null;
                }

            }

            return {
                specName: specName,
                isNegotiablePrice: isNegotiablePrice,
                referPrice: referPrice,
                measureUnit: measureUnit,
                priceUnit: priceUnit
            }

        }

        //==============================================================================
        // 上传图片处理
        //==============================================================================
        /*/!**
         * 图片上传后的回调处理
         *!/
        $scope.onImageUploaded = function(err, resp) {
            if (err) {
                MU.msgTips("error", err);
                return;
            }
            if (resp.fileId && resp.fileName) {
                $scope.uploadImageData.push({
                    fileId: resp.fileId,
                    fileName: resp.fileName
                });
                $scope.loadUploadedImages();
            }
        };

        $scope.loadUploadedImages = function () {
            $("#uploadedImages").empty();
            $.each($scope.uploadImageData, function(i, item) {
                var wrapper = $("<p>").appendTo($("#uploadedImages"));
                $("<span>").html(item.fileName).appendTo(wrapper);
                $("<span>").html("  ").appendTo(wrapper);
                $("<a>").attr("href", "javascript:;")
                    .html("删除")
                    .css("color", "#107aee")
                    .on("click", function() {
                        for (var i = 0; i < $scope.uploadImageData.length; i++) {
                            if ($scope.uploadImageData[i].fileId == item.fileId) {
                                $scope.uploadImageData.splice(i, 1);
                            }
                        }
                        $scope.loadUploadedImages();
                    })
                    .appendTo(wrapper);
            });

            if ($scope.uploadImageData.length >= $scope.MAX_IMAGES) {
                $("#uploadImageWrapper").hide();
            } else {
                $("#uploadImageWrapper").show();
            }
        };*/

        /**
         * 修改时初始化页面信息
         */
        $scope.initModifyInfo = function () {
            $scope.isLoading = true;
            $.ajax({
                url: contextPath + "/outsourcing/queryServiceProjectDetail",
                type: "post",
                dataType: "json",
                data: {
                    projectId: projectId
                },
                success: function (resp) {
                    $scope.isLoading = false;
                    if (resp && resp.result == 0) {
                        var serviceProject = resp.data.serviceProject;
                        var specList = resp.data.specList;
                        var imageList = resp.data.imageList;

                        // 服务领域下拉
                        $('#serviceFieldSelect').val(serviceProject.serviceField).triggerHandler("change");
                        $("#serviceField").val(serviceProject.serviceField);

                        // 供应类型
                        $(".supplyType").each(function (pos, selector) {
                            if($(selector).attr("value") == serviceProject.supplyType){
                                $(selector).siblings().removeClass("checked");
                                $(selector).addClass("checked");
                            }
                        });

                        // 供应主题名称
                        $("#supplyTheme").val(serviceProject.supplyTheme);

                        // 供应简介
                        $("#supplyBrief").val(serviceProject.supplyBrief);

                        // 交易信息规格
                        for (var i = 0, size = specList.length; i < size; i++) {
                            $scope.SPEC_SIGN = i + 1;
                            var newSpecAreaHtml = "";
                            var newSpecSign = $scope.SPEC_SIGN - 1;
                            newSpecAreaHtml += '<div id="specDiv_' + newSpecSign + '" data-value="' + newSpecSign + '" class="SPECDIV">';
                            newSpecAreaHtml += '<li class="clearfix">';
                            newSpecAreaHtml += '<label class="tit">规格名称：</label>';
                            newSpecAreaHtml += '<input id="specName_' + newSpecSign + '" type="text" class="text w866" placeholder="输入规格名称"/>';
                            newSpecAreaHtml += '</li>';
                            newSpecAreaHtml += '<li class="clearfix">';
                            newSpecAreaHtml += '<label class="tit"><em>*</em>参考价格：</label>';
                            newSpecAreaHtml += '<input id="referPrice_' + newSpecSign + '" type="text" class="text w140" placeholder="输入单价"/>';
                            newSpecAreaHtml += '<em class="m10 fl">每</em><input id="measureUnit_' + newSpecSign + '" type="text" class="text w140" placeholder="输入计量单位"/>';
                            newSpecAreaHtml += '<em class="m10 fl">价格单位：</em>';
                            newSpecAreaHtml += '<input type="hidden" name="priceUnit" id="priceUnit_' + newSpecSign + '" value="PRICE_UNIT_YUAN" />';
                            newSpecAreaHtml += '<span class="select w140 fl" id="priceUnitSpan_' + newSpecSign + '">';
                            newSpecAreaHtml += '<select id="priceUnitSelect_' + newSpecSign + '">';
                            newSpecAreaHtml += '<option value="PRICE_UNIT_YUAN">元</option>';
                            newSpecAreaHtml += '<option value="PRICE_UNIT_TEN_THOUSAND_YUAN">万元</option>';
                            newSpecAreaHtml += '<option value="PRICE_UNIT_MILLION_YUAN">百万元</option>';
                            newSpecAreaHtml += '</select>';
                            newSpecAreaHtml += '</span>';
                            newSpecAreaHtml += '<label id="isNegotiablePrice_' + newSpecSign + '" class="checkbox checkbox-radio ml10 fl" style="margin-top: 8px;">价格面议</label>';
                            newSpecAreaHtml += '<a class="del" href="javascript:;" id="deleteSpec_' + newSpecSign + '"></a>';
                            newSpecAreaHtml += '</li>';
                            newSpecAreaHtml += '</div>';
                            $("#specArea").append(newSpecAreaHtml);

                            // 刷新价格单位List
                            $('#priceUnitSpan_' + newSpecSign).children('select').selectList();

                            // 价格单位下拉菜单
                            $("#priceUnitSelect_" + newSpecSign).siblings("ul").find("li").on("click", function () {
                                var value = $(this).attr("data-value");
                                $("#priceUnit_" + newSpecSign).val(value);
                            });

                            // 价格面议
                            $("#isNegotiablePrice_" + newSpecSign).on("click", function () {
                                $("#referPrice_" + newSpecSign).val('');
                                $("#referPrice_" + newSpecSign).html('');
                                $("#measureUnit_" + newSpecSign).val('');
                                $("#measureUnit_" + newSpecSign).html('');
                                if ($("#isNegotiablePrice_" + newSpecSign).hasClass("checked")) {
                                    $("#referPrice_" + newSpecSign).attr('disabled', false);
                                    $("#measureUnit_" + newSpecSign).attr('disabled', false);
                                    $("#priceUnitSelect_" + newSpecSign).attr('disabled', false);
                                } else {
                                    $("#referPrice_" + newSpecSign).attr('disabled', true);
                                    $("#measureUnit_" + newSpecSign).attr('disabled', true);
                                    $("#priceUnitSelect_" + newSpecSign).attr('disabled', true);
                                }
                            });

                            // 删除规格
                            $("#deleteSpec_" + newSpecSign).on("click", function () {
                                $(this).closest('div').remove();
                            });

                            /******************************填充数据******************************/
                            // 规格名称
                            $("#specName_" + newSpecSign).val(specList[i].specName);
                            $("#referPrice_" + newSpecSign).val(specList[i].referPrice);
                            $("#measureUnit_" + newSpecSign).val(specList[i].measureUnit);

                            // 价格单位下拉
                            $("#priceUnitSelect_" + newSpecSign).val(specList[i].priceUnit).triggerHandler("change");
                            $("#priceUnit_" + newSpecSign).val(specList[i].priceUnit);

                            // 价格面议
                            if (specList[i].isNegotiablePrice) {
                                $("#isNegotiablePrice_" + newSpecSign).click();
                            }
                        }

                        // 图片
                        for (var i = 0, size = imageList.length; i < size; i++) {
                            window.uploadImageData.push({
                                fileId: imageList[i].fileId,
                                fileName: imageList[i].fileName
                            });
                        }
                        window.loadUploadedImages();

                        // 详细说明
                        $("#detailDesc").val(serviceProject.detailDesc);

                        // 搜索关键词
                        $("#searchKey").val(serviceProject.searchKey);

                        // 联系地址下拉
                        $("#addressProvinceSelect").val(serviceProject.addressProvince).triggerHandler("change");
                        $("#addressProvince").val(serviceProject.addressProvince);

                        // 联系人详细地址
                        $("#addressDetail").val(serviceProject.addressDetail);

                        // 联系人
                        $("#contact").val(serviceProject.contact);

                        // 联系人电话
                        $("#contactMobile").val(serviceProject.contactMobile);

                        // 电子邮箱
                        $("#email").val(serviceProject.email);

                    } else {
                        MU.msgTips("error", "加载失败");
                    }
                    $scope.$apply();
                },
                error: function () {
                    $scope.isLoading = false;
                    MU.msgTips("error", "加载失败");
                    $scope.$apply();
                }
            });
        };

        /**
         * 初始化操作
         */
        $scope.init = function () {
            if ($scope.isModify) {
                $scope.initProvinces();
                $scope.initModifyInfo();
            } else {
                $scope.addSpec();
                $scope.initProvinces();
            }
        };

        // init
        $scope.init();
    }]);
