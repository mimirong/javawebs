var MAX_IMAGES = 1;
var MAX_ATTACHMENTS = 1;

var modifyingId = null;

var DOUBLE_REGEX = /^\d+(\.\d+)?$/;
var TELEPHONE_REGEX = /^\d{6,}$/;
var MOBILE_REGEX = /^[\d\(\)\-\+]{6,30}$/;
var EMAIL_REGEX = /.+@.+\.[a-zA-Z]{2,4}$/;

// Columns
var columns = [
    {
        data: null,
        render: function(row) {
            return '<label class="checkbox btnSelect" data-row-id="' + row.trainingId + '"></label>';
        }
    }, {
        data: "trainingId"
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
        data: "outOfDate"
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
                m.push('<a href="#" class="txt-op btnHideTraining" data-row-id="' + row.trainingId + '">隐藏</a> ');
            } else {
                m.push('<a href="javascript:void(0)" class="txt-op btnShowTraining" data-row-id="' + row.trainingId + '">显示</a> ');
            }
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveUp" data-row-id="' + row.trainingId + '">上移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveDown" data-row-id="' + row.trainingId + '">下移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnModify" data-row-id="' + row.trainingId + '">修改</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnDelete" data-row-id="' + row.trainingId + '">删除</a> ');
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
	$("#registrationWay_offLine").addClass("checked")
	$("#registrationWay_onLine").removeClass("checked");
    $("#name").val("");
    $("#trainingType").val("").triggerHandler("change");
    $("#adaptObject").val("");
    $("#registrationTime").val("");
    $("#registrationDeadline").val("");
    $("#trainingStartTime").val("");
    $("#trainingEndTime").val("");
    $("#address").val("");
    $("#registrationWay").val("");
    $("#maxPlayers").val("");
    $("#trainingWay").val("");
    $("#price").val("");
    FileUpload.reset("#imageFileWrapper");
    $("#brief").val("");
    FileUpload.reset("#uploadAttachmentWrapper_brief");

    $("#announcements").val("");
    FileUpload.reset("#uploadAttachmentWrapper_anno");

    $("#contact").val("");
    $("#phone").val("");
    $("#mobile").val("");
    $("#email").val("");

    modifyingId = null;
    $(".addModifyDialogTitle").html("添加会议培训");
    $(".btnDoAdd").show();
    $(".btnDoModify").hide();
    MU.mask();
    MU.center("#addModifyDialog");
    $("#addModifyDialog").show();

    // 重置Tab到第一个(课程简介)
    $("#tab_jianjie").trigger("click");

    setTimeout(function() {
        $("#addModifyDialog ul.form").scrollTop(0);
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
                    $("#trainingType").val(resp.data.trainingType).triggerHandler("change");
                    $("#adaptObject").val(resp.data.adaptObject);
                    $("#registrationTime").val(DateFormat.format.date(resp.data.registrationTime, "yyyy-MM-dd"));
                    $("#registrationDeadline").val(DateFormat.format.date(resp.data.registrationDeadline, "yyyy-MM-dd"));
                    $("#trainingStartTime").val(DateFormat.format.date(resp.data.trainingStartTime, "yyyy-MM-dd HH:mm:ss"));
                    $("#trainingEndTime").val(DateFormat.format.date(resp.data.trainingEndTime, "yyyy-MM-dd HH:mm:ss"));
                    $("#address").val(resp.data.address);
                   // $("#registrationWay").val(resp.data.registrationWay);
                    $("#maxPlayers").val(resp.data.maxPlayers);
                    
                    if(resp.data.registrationWay == '线下报名'){
                    	$("#registrationWay_offLine").addClass("checked")
                    	$("#registrationWay_onLine").removeClass("checked");
                    }else{
                    	$("#registrationWay_offLine").removeClass("checked");
                    	$("#registrationWay_onLine").addClass("checked");
                    }
                    
                    $("#price").val(resp.data.price);
                    if(resp.data.price=="" || resp.data.price<=0){
                        $("#radioFree").addClass("checked");
                    }
                    else {
                        $("#radioFree").removeClass("checked");
                    }
                    $("#trainingWay").val(resp.data.trainingWay);
                    FileUpload.setFile("#imageFileWrapper", resp.data.fileId, resp.data.fileName);
                    $("#brief").val(resp.data.brief);
                    FileUpload.setFile("#uploadAttachmentWrapper_brief", resp.data.briefFileId, resp.data.briefFileName);
                    $("#announcements").val(resp.data.announcements);
                    FileUpload.setFile("#uploadAttachmentWrapper_anno", resp.data.annoFileId, resp.data.annoFileName);
                    $("#contact").val(resp.data.contact);
                    $("#phone").val(resp.data.phone);
                    $("#mobile").val(resp.data.mobile);
                    $("#email").val(resp.data.email);
                    $(".addModifyDialogTitle").html("修改会议培训");
                    $(".btnDoAdd").hide();
                    $(".btnDoModify").show();

                    // 重置Tab到第一个(课程简介)
                    $("#tab_jianjie").trigger("click");

                    MU.mask();
                    MU.center("#addModifyDialog");
                    $("#addModifyDialog").show();
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "获取会议培训信息失败，请稍后重试", 1200);
            }
        });
    });

    // 隐藏
    $(".btnHideTraining").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setTrainingVisible",
            type: "post",
            dataType: "json",
            data: {
                trainingId: id,
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
    $(".btnShowTraining").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setTrainingVisible",
            type: "post",
            dataType: "json",
            data: {
                trainingId: id,
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
        var idlist = findTrainingIdList();
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
        var idlist = findTrainingIdList();
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
        MU.msgTips("warn", "请输入培训名称", 1200);
        $("#name").focus();
        return null;
    }
    
    if(name.length > 50){
    	 MU.msgTips("warn", "培训名称长度超过50个字符", 1200);
         $("#name").focus();
         return null;
    }

    //	trainingType
    var trainingType = $("#trainingType").val();
    if (trainingType == "") {
        MU.msgTips("warn", "请选择课程类型", 1200);
        return null;
    }

    // adaptObject
    var adaptObject = $.trim($("#adaptObject").val());
    if (adaptObject == "") {
        MU.msgTips("warn", "请输入适用对象", 1200);
        $("#adaptObject").focus();
        return null;
    }
    
    if(adaptObject.length > 50){
   	 MU.msgTips("warn", "适用对象长度超过50个字符", 1200);
        $("#adaptObject").focus();
        return null;
   }

    // registrationTime
    var registrationTime = $.trim($("#registrationTime").val());
    if (registrationTime == "") {
        MU.msgTips("warn", "请选择报名开始时间", 1200);
        $("#registrationTime").focus();
        return null;
    }

    // registrationDeadline
    var registrationDeadline = $.trim($("#registrationDeadline").val());
    if (registrationDeadline == "") {
        MU.msgTips("warn", "请选择报名截止时间", 1200);
        $("#registrationDeadline").focus();
        return null;
    }
    
    var rStartTime = moment(registrationTime, "YYYY-MM-DD");
    var rEndTime = moment(registrationDeadline, "YYYY-MM-DD");
    if(!rStartTime.isBefore(rEndTime)){
    	 MU.msgTips("warn", "报名截止时间需要在开始时间之后", 1200);
         return null;
    }
    
    // trainingStartTime
    var trainingStartTime = $.trim($("#trainingStartTime").val());
    if (trainingStartTime == "") {
        MU.msgTips("warn", "请选择培训开始时间", 1200);
        $("#trainingStartTime").focus();
        return null;
    }
    
  

    // trainingEndTime
    var trainingEndTime = $.trim($("#trainingEndTime").val());
    if (trainingEndTime == "") {
        MU.msgTips("warn", "请选择培训结束时间", 1200);
        $("#trainingEndTime").focus();
        return null;
    }
    
   
    
    var tStartTime = moment(trainingStartTime, "YYYY-MM-DD hh:mm:ss");
    var tEndTime = moment(trainingEndTime, "YYYY-MM-DD hh:mm:ss");
    console.log(tStartTime);
    console.log(tEndTime);
    
    if(!rStartTime.isBefore(tStartTime)){
      	 MU.msgTips("warn", "培训开始时间需要在报名开始时间之后", 1200);
           return null;
    }
    
    if(!tStartTime.isBefore(tEndTime)){
    	 MU.msgTips("warn", "培训结束时间需要在开始时间之后", 1200);
         return null;
    }

    // address
    var address = $.trim($("#address").val());
    if (address == "") {
        MU.msgTips("warn", "请输入培训地点", 1200);
        $("#address").focus();
        return null;
    }
    
    if(address.length > 100){
      	 MU.msgTips("warn", "培训地点长度超过100个字符", 1200);
           $("#address").focus();
           return null;
      }

    // registrationWay_offLine
    var registrationWay = "";
    if ($("#registrationWay_offLine").hasClass("checked")) {
        //MU.msgTips("warn", "请选择报名方式", 1200);
        //$("#registrationWay_offLine").focus();
        //return null;

        registrationWay = "线下报名"
    }
    else {
        registrationWay = "在线报名"
    }

    // maxPlayers
    var maxPlayers = $.trim($("#maxPlayers").val());
    if (!DOUBLE_REGEX.test(maxPlayers)) {
        MU.msgTips("warn", "请输入正确的限制人数", 1200);
        $("#maxPlayers").focus();
        return null;
    }

    //	trainingWay
    var trainingWay = $("#trainingWay").val();
    if (trainingWay == "") {
        MU.msgTips("warn", "请输入课程形式", 1200);
        return null;
    }
    
    if(trainingWay.length > 20){
     	 MU.msgTips("warn", "课程形式长度超过20个字符", 1200);
          $("#trainingWay").focus();
          return null;
     }

    //	price
    var price = $("#price").val();
    if (!DOUBLE_REGEX.test(price)) {
        MU.msgTips("warn", "请输入正确的课程价格", 1200);
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

    // brief
    var brief = $.trim($("#brief").val());
    if (brief == "") {
        MU.msgTips("warn", "请输入课程内容", 1200);
        $("#brief").focus();
        return null;
    }
    
    if(brief.length > 1000){
    	 MU.msgTips("warn", "课程内容长度超过1000个字符", 1200);
         $("#brief").focus();
         return null;
    }


    //briefFileName
    var briefFileName = $("#briefFileName").val();
    //briefFileId
    var briefFileId = $("#briefFileId").val();
    // if (briefFileId == "") {
    //     MU.msgTips("warn", "请上传照片", 1200);
    //     return null;
    // }

    // announcements
    var announcements = $.trim($("#announcements").val());
    if (announcements == "") {
        MU.msgTips("warn", "请输入注意事项", 1200);
        $("#announcements").focus();
        return null;
    }
    
    if(announcements.length > 1000){
   	 MU.msgTips("warn", "注意事项长度超过1000个字符", 1200);
        $("#announcements").focus();
        return null;
   }

    //annoFileName
    var annoFileName = $("#annoFileName").val();
    //annoFileId
    var annoFileId = $("#annoFileId").val();
    // if (annoFileId == "") {
    //     MU.msgTips("warn", "请上传照片", 1200);
    //     return null;
    // }

    // contact
    var contact = $.trim($("#contact").val());
    if (contact == "") {
        MU.msgTips("warn", "请输入联系人", 1200);
        $("#contact").focus();
        return null;
    }
    
    if(contact.length > 10){
      	 MU.msgTips("warn", "联系人长度超过10个字符", 1200);
           $("#contact").focus();
           return null;
    }

    // phone
    var phone = $.trim($("#phone").val());
    if (phone == "") {
        MU.msgTips("warn", "请输入联系人电话", 1200);
        $("#phone").focus();
        return null;
    }
    else if (!TELEPHONE_REGEX.test(phone)) {
        MU.msgTips("warn", "联系人电话格式不正确", 1200);
        $("#phone").focus();
        return null;
    }
    
    if(phone.length > 20){
     	 MU.msgTips("warn", "联系人电话长度超过20个字符", 1200);
          $("#phone").focus();
          return null;
   }

    // mobile
    var mobile = $.trim($("#mobile").val());
    if (mobile != "") {
        if (!MOBILE_REGEX.test(mobile)) {
            MU.msgTips("warn", "手机号码格式不正确", 1200);
            $("#mobile").focus();
            return null;
        }
        
        if(mobile.length > 20){
        	 MU.msgTips("warn", "手机号码长度超过20个字符", 1200);
             $("#mobile").focus();
             return null;
        }
    }

    // email
    var email = $.trim($("#email").val());
    if (email == "" ) {
        MU.msgTips("warn", "请输入联系人邮箱", 1200);
        $("#email").focus();
        return null;
    }
    else if(!EMAIL_REGEX.test(email)) {
        MU.msgTips("warn", "邮箱的格式不正确", 1200);
        $("#email").focus();
        return null;
    }
    
    if(email.length > 20){
   	 MU.msgTips("warn", "邮箱长度超过20个字符", 1200);
        $("#email").focus();
        return null;
   }

    return {
        name:name,
        trainingType:trainingType,
        adaptObject:adaptObject,
        registrationTimeStr:registrationTime,
        registrationDeadlineStr:registrationDeadline,
        trainingStartTimeStr:trainingStartTime,
        trainingEndTimeStr:trainingEndTime,
        address:address,
        registrationWay:registrationWay,
        maxPlayers:maxPlayers,
        trainingWay:trainingWay,
        price:price,
        fileId:fileId,
        fileName:fileName,
        brief:brief,
        briefFileId:briefFileId,
        briefFileName:briefFileName,
        announcements:announcements,
        annoFileId:annoFileId,
        annoFileName:annoFileName,
        contact:contact,
        phone:phone,
        mobile:mobile,
        email:email
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
    data.trainingId = modifyingId;
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

// 免费的radio按钮
$("#radioFree").on("click", function() {
    if(!$("#radioFree").hasClass("checked")) {
        $("#price").val("0");
        $("#radioFree").addClass("checked");
    }
});

//实时监听价格输入框的变化，如果价格>0，那么自动选中免费的radio按钮
$("#price").on("input propertychange",function(){
    if($(this).val()=="" || $(this).val()<=0){
        $("#radioFree").addClass("checked");
    }
    else {
        $("#radioFree").removeClass("checked");
    }
})

// 获取所有ID
function findTrainingIdList() {
    var list = [];
    for (var i = 0; i < dt.data().length; i++) {
        var row = dt.data()[i];
        list.push(row.trainingId);
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

$(".pop-content1 .tab-div-btn a").click(function(){ //会议培训添加修改培训弹窗tab切换
    $(this).addClass("on").siblings().removeClass("on");
    $(".pop-content1 .tab-form").hide().eq($('.pop-content1 .tab-div-btn a').index(this)).show();
    //MU.init();
});


//弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$(".pop-content1").css({
		"max-height": wh - 60 + "px",
		"overflow": "auto"
	});
});
$(window).trigger("resize");
