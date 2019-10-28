var modifyingId = null;

// Columns
var columns = [
    {
        data: null,
        render: function(row) {
            return '<label class="checkbox btnSelect" data-row-id="' + row.imageId + '"></label>';
        }
    }, {
        "data": null, "targets": 0
    }, {
        data: "categoryId",
        render: function (val) {
            switch (val){
                // 知识产权服务-办事服务
                case "LINK_URL_INTELLECTUAL_SERVICE_GUIDE":
                    return "办事服务";
                // 知识产权服务-数据服务
                case "LINK_URL_INTELLECTUAL_DATA_SERVICE":
                    return "数据服务";
                // 诚信体系服务-企业信用查询
                case "LINK_URL_CREDIT_SYSTEM_COM_INFO_QUERY":
                    return "企业信用查询";
                // 诚信体系服务-企业信息填报
                case "LINK_URL_CREDIT_SYSTEM_COM_INFO_SUBMIT":
                    return "企业信息填报";
                default:
                    return "未知";
            }
        }
    }, {
        data: "name",
        render: function(val) {
            return val;
        }
    },{
        data: "linkUrl",
        render: function(val) {
            return val;
        }
    }, {
        data: null,
        orderable: false,
        render: function(row) {
            var m = [];
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
    columns : columns,
    "fnDrawCallback": function(){
        var api = this.api();
        var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
        api.column(1).nodes().each(function(cell, i) {
            cell.innerHTML = startIndex + i + 1;
        });
    }
}));

// 绑定事件
ListFeaturePage.autobind(dt);

// 显示新增
$(".btnAdd").on("click", function() {
    $("#name").val("");
    $("#categoryId").val("").triggerHandler("change");
    $("#linkUrl").val("");

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
                MU.msgTips("error", "获取链接信息失败，请稍后重试", 1200);
            }
        });
    });

    // 详情
    $(".btnDetails").off().on("click", function(e) {
        modifyingId = $(e.target).attr("data-row-id");
    });

});

// 检查表单
function checkAddModifyForm() {

    //name
    var name = $.trim($("#name").val());
    if (name == "") {
        MU.msgTips("warn", "请输入链接名称", 1200);
        $("#name").focus();
        return null;
    }

    // categoryId
    var categoryId = $("#categoryId").val();
    if (categoryId == "") {
        MU.msgTips("warn", "请选择栏目", 1200);
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

    return {
        name:name,
        categoryId:categoryId,
        linkUrl:linkUrl,
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