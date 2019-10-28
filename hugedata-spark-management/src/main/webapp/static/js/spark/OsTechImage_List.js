var modifyingId = null;

$("#queryCategoryIdInput").val("HUMAN_RESOURCE");

// Columns
var columns = [
    {
        data: null,
        render: function(row) {
            return '<label class="checkbox btnSelect" data-row-id="' + row.imageId + '"></label>';
        }
    }, {
        data: "imageId"
    }, {
        data: "fileId",
        render:function(val,type,obj){
            if(val!=null && val!='' ){
                var url = DownloadService.buildUrl(val, "");
                return '<img src="' + url + '" style="width:90px; height:60px;" />'
            }
            return "";
        }
    }, {
        data: "categoryId",
        render: function (val) {
            switch (val){
                case "HUMAN_RESOURCE":
                    return "人力资源服务";
                case "COMPANIES_INFORMATION":
                    return "企业信息服务";
                case "PROJECT_OSINVEST":
                    return "项目招商服务";
                case "INTELLECTUAL":
                    return "知识产权服务";
                case "CREDIT_SYSTEM":
                    return "诚信体系服务";
                default:
                    return "未知";
            }
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
        data: "linkUrl",
        render: function(val) {
            if(val!=null && val!=''  && val.length > 20){
                return '<a href="javascript:;" title="'+val+'">'+val.substring(0,17)+"..."+'</a>';
            }
            return val;
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
                m.push('<a href="#" class="txt-op btnHideImage" data-row-id="' + row.imageId + '">隐藏</a> ');
            } else {
                m.push('<a href="javascript:void(0)" class="txt-op btnShowImage" data-row-id="' + row.imageId + '">显示</a> ');
            }
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveUp" data-row-id="' + row.imageId + '">上移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnMoveDown" data-row-id="' + row.imageId + '">下移</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnModify" data-row-id="' + row.imageId + '">修改</a> ');
            m.push('<a href="javascript:void(0)" class="txt-op btnDelete" data-row-id="' + row.imageId + '">删除</a> ');
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
    $("#categoryId").val($("#queryCategoryIdInput").val()).triggerHandler("change");
    $("#brief").val("");
    $("#title").val("");
    $("#linkUrl").val("");
    FileUpload.reset("#imageFileWrapper");

    modifyingId = null;

    $(".addModifyDialogTitle").html("添加");
    $(".btnDoAdd").show();
    $(".btnDoModify").hide();
    MU.mask();
    MU.center("#addModifyDialog");
    $("#addModifyDialog").show();

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
                    $("#categoryId").val(resp.data.categoryId).triggerHandler("change");
                    $("#linkUrl").val(resp.data.linkUrl);
                    $("#brief").val(resp.data.brief);
                    $("#title").val(resp.data.title);
                    FileUpload.setFile("#imageFileWrapper", resp.data.fileId, resp.data.fileName);
                    $(".addModifyDialogTitle").html("修改");
                    $(".btnDoAdd").hide();
                    $(".btnDoModify").show();
                    MU.mask();
                    MU.center("#addModifyDialog");
                    $("#addModifyDialog").show();

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
            url: "setTechImageVisible",
            type: "post",
            dataType: "json",
            data: {
                imageId: id,
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
    $(".btnShowImage").off().on("click", function(e) {
        var id = $(e.target).attr("data-row-id");
        $.ajax({
            url: "setTechImageVisible",
            type: "post",
            dataType: "json",
            data: {
                imageId: id,
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
        var idlist = findImageIdList();
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
        var idlist = findImageIdList();
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
        MU.msgTips("warn", "请输入图片名称", 1200);
        $("#name").focus();
        return null;
    }

    // categoryId
    var categoryId = $("#categoryId").val();
    if (categoryId == "") {
        MU.msgTips("warn", "请选择栏目", 1200);
        return null;
    }

    var title = $.trim($("#title").val());
    if (title == "") {
        MU.msgTips("warn", "请输入标题", 1200);
        $("#title").focus();
        return null;
    }

    //linkUrl
    var linkUrl = $.trim($("#linkUrl").val());

    if (linkUrl != "") {
        var flag = linkUrl.substr(0,7)=="http://" || linkUrl.substr(0,8)=="https://";
        if(!flag){
            MU.msgTips("warn", "请输入以https或http开头的链接");
            $("#linkUrl").focus();
            return null;
        }
    }

    var brief = $.trim($("#brief").val());
    if (brief == "") {
        MU.msgTips("warn", "请输入概要", 1200);
        $("#brief").focus();
        return null;
    }

    //fileName
    var fileName = $("#fileName").val();
    //fileId
    var fileId = $("#fileId").val();
    if (fileId == "") {
        MU.msgTips("warn", "请先上传图片", 1200);
        return null;
    }

    return {
        name:name,
        categoryId:categoryId,
        linkUrl:linkUrl,
        fileId:fileId,
        fileName:fileName,
        title:title,
        brief:brief
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
    data.imageId = modifyingId;
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
function findImageIdList() {
    var list = [];
    for (var i = 0; i < dt.data().length; i++) {
        var row = dt.data()[i];
        list.push(row.imageId);
    }
    return list;
}

// 将排序信息保存到服务器
function saveOrder(list) {
    var categoryId = $("#categoryIdQuery").val();
    if (categoryId == "") {
        MU.msgTips("warn", "请选择栏目", 1200);
        return null;
    } else {
        $.ajax({
            url: "setOrder",
            type: "post",
            dataType: "json",
            data: {
                idlist: list,
                categoryId: categoryId
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
}

// 页面顶部下拉筛选
$(function () {
    $("#categoryIdQuerySuper ul li").on("click", function () {
        var categoryId = $("#categoryIdQuery").val();
        if (categoryId == "") {
            MU.msgTips("warn", "请选择栏目", 1200);
            return null;
        } else {
            switch (categoryId){
                // 人力资源服务
                case "HUMAN_RESOURCE":
                    $("#queryCategoryIdInput").val("HUMAN_RESOURCE");
                    dt.ajax.reload();
                    break;
                // 企业信息服务
                case "COMPANIES_INFORMATION":
                    $("#queryCategoryIdInput").val("COMPANIES_INFORMATION");
                    dt.ajax.reload();
                    break;
                // 项目招商服务
                case "PROJECT_OSINVEST":
                    $("#queryCategoryIdInput").val("PROJECT_OSINVEST");
                    dt.ajax.reload();
                    break;
                // 知识产权服务
                case "INTELLECTUAL":
                    $("#queryCategoryIdInput").val("INTELLECTUAL");
                    dt.ajax.reload();
                    break;
                // 诚信体系服务
                case "CREDIT_SYSTEM":
                    $("#queryCategoryIdInput").val("CREDIT_SYSTEM");
                    dt.ajax.reload();
                    break;
                default:
                    $("#queryCategoryIdInput").val("HUMAN_RESOURCE");
                    dt.ajax.reload();
                    break;
            }
        }
    });
});

// 添加或修改
$(function () {
    $("#categoryIdSuper ul li").on("click", function () {
        var categoryId = $("#categoryId").val();
        if (categoryId == "") {
            MU.msgTips("warn", "请选择栏目", 1200);
            return null;
        } else {
            switch (categoryId){
                // 人力资源服务
                case "HUMAN_RESOURCE":
                    $("#fileTip").html("建议图片尺寸100x72,大小不能超过10M");
                    break;
                // 企业信息服务
                case "COMPANIES_INFORMATION":
                    $("#fileTip").html("建议图片尺寸100x72,大小不能超过10M");
                    break;
                // 项目招商服务
                case "PROJECT_OSINVEST":
                    $("#fileTip").html("建议图片尺寸100x72,大小不能超过10M");
                    break;
                // 知识产权服务
                case "INTELLECTUAL":
                    $("#fileTip").html("建议图片尺寸210x72,大小不能超过10M");
                    break;
                // 诚信体系服务
                case "CREDIT_SYSTEM":
                    $("#fileTip").html("建议图片尺寸430x72,大小不能超过10M");
                    break;
                default:
                    $("#fileTip").html("建议图片尺寸100x80,大小不能超过10M");
                    break;
            }
        }
    });
});