var modifyingId = null;

// Columns
var columns = [
    {
        data: null,
        render: function(row) {
            return '<label class="checkbox btnSelect" data-row-id="' + row.achieveId + '"></label>';
        }
    }, {
        data: "achieveId"
    }, {
        data: "fileId",
        render:function(val,type,obj){
            if(val!=null && val!='' ){
                var url = DownloadService.buildUrl(val, "");
                return '<img src="' + url + '" style="width:100px;" />'
            }
            return "";
        }
    }, {
        data: "name",
        render: function(val) {
            if(val!=null && val!=''  && val.length > 10){
                return '<a href="javascript:;" title="'+val+'">'+val.substring(0,7)+"..."+'</a>';
            }
            return val;
        }
    },{
        data: "professionFieldId",
        render: function(professionFieldId) {
        	if (!professionFieldId || professionFieldId == "") {
        		return "";
        	}
        	return getProfessionFieldTextById(professionFieldId);
        }
    },{
        data: "isVisible",
        render: function(isVisible) {
            if (isVisible) {
                return '<span class="green">是</span>';
            } else {
                return '<span class="red">否</span>';
            }
        }
    }, {
        data: null,
        orderable: false,
        render: function(row) {
            var m = [];
            if (row.isVisible) {
                m.push('<a href="#" class="txt-op btnHideAchieve" data-row-id="' + row.achieveId + '">隐藏</a> ');
            } else {
                m.push('<a href="javascript:void(0)" class="txt-op btnShowAchieve" data-row-id="' + row.achieveId + '">显示</a> ');
            }
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveUp" data-row-id="' + row.achieveId + '">上移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveDown" data-row-id="' + row.achieveId + '">下移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnModify" data-row-id="' + row.achieveId + '">修改</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnDelete" data-row-id="' + row.achieveId + '">删除</a> ');
            return m.join('');
        }
    }
];

// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
    ajax: {
        url: "listData",
        data: function(data) {
            ListFeaturePage.loadQueryValues(data, ".queryItem")
        }
    },
    lengthChange: false,
    serverSide : true,
    searching : false,
    ordering: false,
    columns : columns
}));

// 绑定事件
ListFeaturePage.autobind(dt);

// 显示新增
$(".btnAdd").on("click", function() {
    $("#name").val("");
    $("#professionFieldId").val("").triggerHandler("change");
    $("#achieveType").val("").triggerHandler("change");
    $("#investmentVolume").val("");
    $("#monetaryUnit").val("YUAN").triggerHandler("change");
    $("#maturityStage").val("").triggerHandler("change");
    $("#desiredEffect").val("");
    $("#adaptObject").val("");
    $("#cooperationWays").val("");
    $("#contact").val("");
    $("#phone").val("");
    $("#brief").val("");
    FileUpload.reset("#imageFileWrapper");

    modifyingId = null;

    $(".addModifyDialogTitle").html("添加技术成果");
    $(".btnDoAdd").show();
    $(".btnDoModify").hide();
    MU.mask();
    MU.center("#addModifyDialog");
    $("#addModifyDialog").show();

    setTimeout(function() {
    	$(".pop-content").scrollTop(0);
    }, 1);
});

// 每行事件
dt.on("draw", function(settings, data) {
    // 修改
    $(".btnModify").off().on("click", function(e) {

        modifyingId = $(e.target).attr("data-row-id");

        $.ajax({
            url: "toModify",
            type: "post",
            dataType: "json",
            data: { id:modifyingId },
            success: function(resp) {
                if (resp && resp.result == 0) {

                    $("#name").val(resp.data.name);
                    $("#professionFieldId").val(resp.data.professionFieldId).triggerHandler("change");
                    $("#achieveType").val(resp.data.achieveType).triggerHandler("change");
                    $("#investmentVolume").val(resp.data.investmentVolume);
                    $("#monetaryUnit").val(resp.data.monetaryUnit).triggerHandler("change");
                    $("#maturityStage").val(resp.data.maturityStage).triggerHandler("change");
                    $("#desiredEffect").val(resp.data.desiredEffect);
                    $("#adaptObject").val(resp.data.adaptObject);
                    $("#cooperationWays").val(resp.data.cooperationWays);
                    $("#contact").val(resp.data.contact);
                    $("#phone").val(resp.data.phone);
                    $("#brief").val(resp.data.brief);
                    FileUpload.setFile("#imageFileWrapper", resp.data.fileId, resp.data.fileName);
                    $(".addModifyDialogTitle").html("修改技术成果");
                    $(".btnDoAdd").hide();
                    $(".btnDoModify").show();
                    MU.mask();
                    MU.center("#addModifyDialog");
                    $("#addModifyDialog").show();

                    setTimeout(function() {
                    	$(".pop-content").scrollTop(0);
                    }, 1);

                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "获取图片信息失败，请稍后重试", 1200);
            }
        });
    });

    // 隐藏
    $(".btnHideAchieve").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setAchieveVisible",
            type: "post",
            dataType: "json",
            data: {
                achieveId: id,
                visible: false
            },
            success: function(resp) {
                if (resp && resp.result == 0) {
                    dt.ajax.reload();
                } else {
                    alert(resp.message);
                }
            },
            error: function() {
                alert("隐藏失败");
            }
        });
    });

    // 显示
    $(".btnShowAchieve").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setAchieveVisible",
            type: "post",
            dataType: "json",
            data: {
                achieveId: id,
                visible: true
            },
            success: function(resp) {
                if (resp && resp.result == 0) {
                    dt.ajax.reload();
                } else {
                    alert(resp.message);
                }
            },
            error: function() {
                alert("设置失败");
            }
        });
    });

    // 详情
    $(".btnDetails").off().on("click", function(e) {
        modifyingId = $(e.target).attr("data-row-id");
    });

    // 上移
    $(".btnMoveUp").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        var idlist = findAchieveIdList();
        if (idlist.length <= 1) {
            return;
        }

        for (var i = 1; i < idlist.length; i++) {
            if (idlist[i] == id) {
                var swap = idlist[i - 1];
                idlist[i - 1] = idlist[i];
                idlist[i] = swap;
                break;
            }
        }

        saveOrder(idlist);
    });

    // 下移
    $(".btnMoveDown").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        var idlist = findAchieveIdList();
        if (idlist.length <= 1) {
            return;
        }

        for (var i = 0; i < idlist.length - 1; i++) {
            if (idlist[i] == id) {
                var swap = idlist[i + 1];
                idlist[i + 1] = idlist[i];
                idlist[i] = swap;
                break;
            }
        }

        saveOrder(idlist);
    });

});

// 检查表单
function checkAddModifyForm() {

    //name
    var name = $.trim($("#name").val());
    if (name == "") {
        MU.msgTips("warn", "请输入项目名称", 1200);
        $("#name").focus();
        return null;
    }
    
    if (name.length >  10) {
        MU.msgTips("warn", "项目名称长度超过10个字符", 1200);
        $("#name").focus();
        return null;
    }

    //	professionFieldId
    var professionFieldId = $("#professionFieldId").val();
    if (professionFieldId == "") {
        MU.msgTips("warn", "请选择专业领域", 1200);
        return null;
    }

    //	achieveType
    var achieveType = $("#achieveType").val();
    if (achieveType == "") {
        MU.msgTips("warn", "请选择成果形式", 1200);
        return null;
    }

    // investmentVolume
    var investmentVolume = $.trim($("#investmentVolume").val());
    if (investmentVolume == "") {
        MU.msgTips("warn", "请输入金额", 1200);
        $("#investmentVolume").focus();
        return null;
    }

    //	monetaryUnit
    var monetaryUnit = $("#monetaryUnit").val();
    if (monetaryUnit == "") {
        MU.msgTips("warn", "请选择金额单位", 1200);
        return null;
    }

    //	maturityStage
    var maturityStage = $("#maturityStage").val();
    if (maturityStage == "") {
        MU.msgTips("warn", "请选择技术成熟度", 1200);
        return null;
    }

    // desiredEffect
    var desiredEffect = $.trim($("#desiredEffect").val());
    if (desiredEffect == "") {
        MU.msgTips("warn", "请输入预期效果", 1200);
        $("#desiredEffect").focus();
        return null;
    }
    
    if (desiredEffect.length >  100) {
        MU.msgTips("warn", "预期效果长度超过100个字符", 1200);
        $("#desiredEffect").focus();
        return null;
    }

    // adaptObject
    var adaptObject = $.trim($("#adaptObject").val());
    if (adaptObject == "") {
        MU.msgTips("warn", "请输入适应对象", 1200);
        $("#adaptObject").focus();
        return null;
    }
    
    if (adaptObject.length >  20) {
        MU.msgTips("warn", "适应对象长度超过20个字符", 1200);
        $("#adaptObject").focus();
        return null;
    }

    // cooperationWays
    var cooperationWays = $.trim($("#cooperationWays").val());
    if (cooperationWays == "") {
        MU.msgTips("warn", "请输入合作方式", 1200);
        $("#cooperationWays").focus();
        return null;
    }
    
    if (cooperationWays.length >  20) {
        MU.msgTips("warn", "合作方式长度超过20个字符", 1200);
        $("#cooperationWays").focus();
        return null;
    }

    // contact
    var contact = $.trim($("#contact").val());
    if (contact == "") {
        MU.msgTips("warn", "请输入联系人", 1200);
        $("#contact").focus();
        return null;
    }
    
    if (contact.length >  10) {
        MU.msgTips("warn", "联系人长度超过10个字符", 1200);
        $("#contact").focus();
        return null;
    }

    // phone
    var phone = $.trim($("#phone").val());
    if (phone == "") {
        MU.msgTips("warn", "请输入联系电话", 1200);
        $("#phone").focus();
        return null;
    }

    // brief
    var brief = $.trim($("#brief").val());
    if (brief == "") {
        MU.msgTips("warn", "请输入方案详述", 1200);
        $("#brief").focus();
        return null;
    }
    
    if (brief.length >  500) {
        MU.msgTips("warn", "方案详述长度超过500个字符", 1200);
        $("#brief").focus();
        return null;
    }


    //fileName
    var fileName = $("#fileName").val();
    //fileId
    var fileId = $("#fileId").val();
    if (fileId == "") {
        MU.msgTips("warn", "请上传照片", 1200);
        return null;
    }

    return {
        name:name,
        professionFieldId:professionFieldId,
        achieveType:achieveType,
        investmentVolume:investmentVolume,
        monetaryUnit:monetaryUnit,
        maturityStage:maturityStage,
        desiredEffect:desiredEffect,
        adaptObject:adaptObject,
        cooperationWays:cooperationWays,
        contact:contact,
        phone:phone,
        brief:brief,
        fileId:fileId,
        fileName:fileName
    };
}

// 提交新增
$(".btnDoAdd").on("click", function() {
    var data = checkAddModifyForm();
    if (data == null) {
        return;
    }

    $.ajax({
        url: "doAdd",
        type: "post",
        dataType: "json",
        data: data,
        success: function(resp) {
            if (resp && resp.result == 0) {
                MU.msgTips("success", "添加成功", 1200);
                MU.hide($(".btnDoAdd"));
                dt.ajax.reload();
            } else {
                MU.msgTips("error", resp.message);
            }
        },
        error: function() {
            MU.msgTips("error", "添加失败，请稍后重试");
        }
    });

});

// 确定修改
$(".btnDoModify").on("click", function() {
    var data = checkAddModifyForm();
    if (data == null) {
        return;
    }
    data.achieveId = modifyingId;
    $.ajax({
        url: "doModify",
        type: "post",
        dataType: "json",
        data: data,
        success: function(resp) {
            if (resp && resp.result == 0) {
                MU.msgTips("success", "修改成功", 1200);
                MU.hide($(".btnDoModify"));
                dt.ajax.reload();
            } else {
                MU.msgTips("error", resp.message);
            }
        },
        error: function() {
            MU.msgTips("error", "修改失败，请稍后重试");
        }
    });
});

// 获取所有ID
function findAchieveIdList() {
    var list = [];
    for (var i = 0; i < dt.data().length; i++) {
        var row = dt.data()[i];
        list.push(row.achieveId);
    }
    return list;
}

// 将排序信息保存到服务器
function saveOrder(list) {
    $.ajax({
        url: "setOrder",
        type: "post",
        dataType: "json",
        data: {
            idlist: list,
            categoryId: "DEFAULT"
        },
        success: function (resp) {
            if (resp && resp.result == 0) {
                dt.ajax.reload();
            } else {
                alert(resp.message);
            }
        },
        error: function () {
            alert("保存排序信息失败");
        }
    });
}

// 根据专业领域ID获取对应的专业领域的文字描述
function getProfessionFieldTextById(professionFieldId) {
    switch (professionFieldId) {
        case "SP_SERVICE_FIELD_CHEMISTRY": return "化学化工";
        case "SP_SERVICE_FIELD_CONSTRUCTION": return "建筑建材";
        case "SP_SERVICE_FIELD_NDT": return "无损检测";
        case "SP_SERVICE_FIELD_CONSUMER_GOODS": return "消费品";
        case "SP_SERVICE_FIELD_ENVIRON_HEALTH": return "环境监测与职业卫生";
        case "SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT": return "生物医药及器械";
        case "SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL": return "电子电气及机械";
        case "SP_SERVICE_FIELD_EMC": return "电磁兼容（EMC）";
        case "SP_SERVICE_FIELD_SPIN_FIBER": return "纺织及纤维";
        case "SP_SERVICE_FIELD_SOFTWARE_INFO": return "软件信息";
        case "SP_SERVICE_FIELD_METAL_MATERIAL": return "金属材料";
        case "SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS": return "食品及农产品";
        case "SP_SERVICE_FIELD_OTHER": return "其他";
        default: return "";
    }
}


//弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$(".pop-content").css({
		"max-height": wh - 120 + "px",
		"overflow": "auto"
	});
});
$(window).trigger("resize");
