var modifyingId = null;

// Columns
var columns = [
    {
        data: null,
        render: function(row) {
            return '<label class="checkbox btnSelect" data-row-id="' + row.expertId + '"></label>';
        }
    }, {
        data: "expertId"
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
        data: "dept",
        render: function(dept) {
        	if (!dept || dept == "") {
        		return "";
        	}
        	return dept;
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
                m.push('<a href="#" class="txt-op btnHideImage" data-row-id="' + row.expertId + '">隐藏</a> ');
            } else {
                m.push('<a href="javascript:void(0)" class="txt-op btnShowExpert" data-row-id="' + row.expertId + '">显示</a> ');
            }
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveUp" data-row-id="' + row.expertId + '">上移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveDown" data-row-id="' + row.expertId + '">下移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnModify" data-row-id="' + row.expertId + '">修改</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnDelete" data-row-id="' + row.expertId + '">删除</a> ');
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
    $("#job_title").val("");
    $("#dept").val("");
    $("#position").val("");
    $("#professionFieldId").val("").triggerHandler("change");
    $("#expert_field").val("");
    $("#expert_title").val("");
    $("#brief").val("");
    FileUpload.reset("#imageFileWrapper");

    modifyingId = null;

    $(".addModifyDialogTitle").html("添加专家信息");
    $(".btnDoAdd").show();
    $(".btnDoModify").hide();
    MU.mask();
    MU.center("#addModifyDialog");
    $("#addModifyDialog").show();

    setTimeout(function() {
    	$(".popupWrapper").scrollTop(0);
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
                    $("#job_title").val(resp.data.jobTitle);
                    $("#dept").val(resp.data.dept);
                    $("#position").val(resp.data.position);
                    $("#professionFieldId").val(resp.data.professionFieldId).triggerHandler("change");
                    $("#expert_field").val(resp.data.expertField);
                    $("#expert_title").val(resp.data.expertTitle);
                    $("#brief").val(resp.data.brief);
                    FileUpload.setFile("#imageFileWrapper", resp.data.fileId, resp.data.fileName);
                    $(".addModifyDialogTitle").html("修改专家信息");
                    $(".btnDoAdd").hide();
                    $(".btnDoModify").show();
                    MU.mask();
                    MU.center("#addModifyDialog");
                    $("#addModifyDialog").show();

                    setTimeout(function() {
                    	$(".popupWrapper").scrollTop(0);
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
    $(".btnHideImage").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setExpertVisible",
            type: "post",
            dataType: "json",
            data: {
                expertId: id,
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
    $(".btnShowExpert").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setExpertVisible",
            type: "post",
            dataType: "json",
            data: {
                expertId: id,
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
        var idlist = findExpertIdList();
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
        var idlist = findExpertIdList();
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
        MU.msgTips("warn", "请输入姓名", 1200);
        $("#name").focus();
        return null;
    }
    
    if (name.length >  10) {
        MU.msgTips("warn", "姓名长度超过10个字符", 1200);
        $("#name").focus();
        return null;
    }

    // job_title
    var job_title = $.trim($("#job_title").val());
    if (job_title == "") {
        MU.msgTips("warn", "请输入职称", 1200);
        $("#job_title").focus();
        return null;
    }
    
    if (job_title.length >  10) {
        MU.msgTips("warn", "职称长度超过10个字符", 1200);
        $("#job_title").focus();
        return null;
    }

    // dept
    var dept = $.trim($("#dept").val());
    if (dept == "") {
        MU.msgTips("warn", "请输入部门", 1200);
        $("#dept").focus();
        return null;
    }
    
    if (dept.length >  20) {
        MU.msgTips("warn", "部门长度超过20个字符", 1200);
        $("#dept").focus();
        return null;
    }

    // position
    var position = $.trim($("#position").val());
    if (position == "") {
        MU.msgTips("warn", "请输入职务", 1200);
        $("#position").focus();
        return null;
    }
    
    if (position.length >  20) {
        MU.msgTips("warn", "职务长度超过20个字符", 1200);
        $("#position").focus();
        return null;
    }

    //	professionFieldId
    var professionFieldId = $("#professionFieldId").val();
    if (professionFieldId == "") {
        MU.msgTips("warn", "请选择专业领域", 1200);
        return null;
    }

    // expert_field
    var expert_field = $.trim($("#expert_field").val());
    // if (expert_field == "") {
    //     MU.msgTips("warn", "请输入擅长领域", 1200);
    //     $("#expert_field").focus();
    //     return null;
    // }
    
    if (expert_field && expert_field.length >  30) {
        MU.msgTips("warn", "擅长领域长度超过30个字符", 1200);
        $("#expert_field").focus();
        return null;
    }

    // expert_title
    var expert_title = $.trim($("#expert_title").val());
    // if (expert_title == "") {
    //     MU.msgTips("warn", "请输入专家称号", 1200);
    //     $("#expert_title").focus();
    //     return null;
    // }
    
    if (expert_title && expert_title.length >  10) {
        MU.msgTips("warn", "专家称号长度超过10个字符", 1200);
        $("#expert_title").focus();
        return null;
    }

    // brief
    var brief = $.trim($("#brief").val());
    if (brief == "") {
        MU.msgTips("warn", "请输入个人简介", 1200);
        $("#brief").focus();
        return null;
    }
    
    if ( brief.length >  500) {
        MU.msgTips("warn", "个人简介长度超过500个字符", 1200);
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
        jobTitle:job_title,
        dept:dept,
        position:position,
        professionFieldId:professionFieldId,
        expertField:expert_field,
        expertTitle:expert_title,
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
    data.expertId = modifyingId;
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
function findExpertIdList() {
    var list = [];
    for (var i = 0; i < dt.data().length; i++) {
        var row = dt.data()[i];
        list.push(row.expertId);
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


//弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$(".popupWrapper").css({
		"max-height": wh - 100 + "px",
		"overflow": "auto"
	});
});
$(window).trigger("resize");