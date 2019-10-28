// Columns
var columns =[
    {
        data: "typeId"
    }, {
        data: "typeName"
    },  {
        data: null,
        orderable: false,
        render: function(row) {
            var m = [];
                m.push('<a class="txt-op btnModify" data-row-id="' + row.typeId + '" href="javascript:;">修改</a>');
                m.push('<a class="txt-op btnDelete" data-row-id="' + row.typeId + '" href="javascript:;">删除</a>');
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



// 搜索
$(".btnQuery").on("click", function() {
    dt.ajax.reload();
});
$("input.queryItem").on("keydown", function(e) {
    if (e.keyCode == 13) {
        dt.ajax.reload();
    }
});


//新增
$(".btnAdd").on("click", function() {
    $("#typeName").val("");
    modifyingId = null;
    $(".addPanelTitle").html("添加");
    $("#btnDoAdd").show();
    $("#btnDoModify").hide();
    MU.mask();
    MU.center("#addPanel");
    $("#addPanel").show();
});

//每行事件
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
                	$("#typeName").val(resp.data.typeName);
                    $(".addPanelTitle").html("修改");
                    $("#btnDoAdd").hide();
                    $("#btnDoModify").show();
                    MU.mask();
                    MU.center("#addPanel");
                    $("#addPanel").show();
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "获取文章信息失败，请稍后重试", 1200);
            }
        });
    });
});

//检查表单
function checkAddModifyForm() {
    var typeName = $("#typeName").val();
    if (typeName == "") {
        MU.msgTips("warn", "文件类型名称不能为空", 1200);
        $("#typeName").focus();
        return null;
    }
    if (typeName.length > 20) {
        MU.msgTips("warn", "文件类型名称字符数不能大于20", 1200);
        $("#typeName").focus();
        return null;
    }
    
    return {
    	typeName: typeName,
    	docType:$('input[name="docType"]').val()
    };
}

//提交新增
$("#btnDoAdd").on("click", function() {
    var data = checkAddModifyForm();
    if (data == null) {
        return;
    }

    //提交
    $.ajax({
        url: "doAdd",
        type: "post",
        dataType: "json",
        data: data,
        success: function(resp) {
            if (resp && resp.result == 0) {
                MU.msgTips("success", "添加成功", 1200);
                MU.hide($("#btnDoAdd"))
                dt.ajax.reload();
            } else {
                MU.msgTips("error", resp.message, 1200);
            }
        },
        error: function() {
            MU.msgTips("error", "添加失败，请稍后重试", 1200);
        }
    });
});

//提交修改
$("#btnDoModify").on("click", function() {
    var data = checkAddModifyForm();
    if (data == null) {
        return;
    }
    data.typeId = modifyingId;

    //提交
    $.ajax({
        url: "doModify",
        type: "post",
        dataType: "json",
        data: data,
        success: function(resp) {
            if (resp && resp.result == 0) {
                MU.msgTips("success", "修改成功", 1200);
                MU.hide($("#btnDoModify"))
                dt.ajax.reload();
            } else {
                MU.msgTips("error", resp.message, 1200);
            }
        },
        error: function() {
            MU.msgTips("error", "修改失败，请稍后重试", 1200);
        }
    });
});

// 判断是否正在修改
function isModifying() {
    return modifyingId != null;
}

$("div.tab a").on("click", function() {
	$(this).addClass('on').siblings().removeClass('on');
	$('input[name="docType"]').val($(this).attr('data-type'));
    dt.ajax.reload();
});
 


