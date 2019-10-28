var STATUS_TEXT = {
    "CREATED": "待审批",
    "FINISHED": "已办结",
};
$("#queryStatusWrapper input").val("");

// Columns
var columns = [
    {
        "data": null, "targets": 0
    }, {
        data: null,
        render: function (row) {
            return '<a class="txt-op" href="' + govAffairsPortalUrl + '/outsourcing/details?articleId=' + row.resourceId + '&categoryId=' + row.applyType
                + '"   target ="_black">' + row.resourceName + '</a>';
        }
    }, {
        data: "companyName"
    }, {
        data: null,
        render: function (row) {
            if (row.contactName) {
                return row.contactName;
            } else {
                return "无";
            }
        }
    }, {
        data: "contact"
    }, {
        data: null,
        render: function (row) {
            return '<a class="txt-op op-check btnComment" data_row_comment="' + row.comment + '"href="javascript:;" title="说明">查看</a>';
        }
    }, {
        data: null,
        render: function (row) {
            switch (row.status) {
                case "CREATED":
                    return "待审核";
                case "FINISHED":
                    return "已办结";
                default:
                    return "未知";
            }
        }
    }, {
        data: null,
        render: function (row) {
            if (row.approverName) {
                return row.approverName;
            } else {
                return "无";
            }
        }
    }, {
        data: null,
        render: function (row) {
            switch (row.status) {
                case "CREATED":
                    return '<a class="txt-op op-approval btnApprove" data-row-applyid="' + row.applyId + '" href="javascript:;" title="审批">审批</a>';
                case "FINISHED":
                    return '<span style="color: #999;">已审批</span>';
                default:
                    return '<a class="txt-op op-approval btn-grey" href="javascript:;" title="已审批">已审批</a>';
            }

        }
    }

];

// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
    ajax: {
        url: "listData",
        data: function (data) {
            ListFeaturePage.loadQueryValues(data, ".queryItem")
        }
    },
    lengthChange: false,
    serverSide: true,
    searching: false,
    ordering: false,
    columns: columns,
    "fnDrawCallback": function(){
        var api = this.api();
        var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
        api.column(0).nodes().each(function(cell, i) {
            cell.innerHTML = startIndex + i + 1;
        });
    }
}));

// 绑定事件
ListFeaturePage.autobind(dt);

// 每行事件
dt.on("draw", function (settings, data) {

    // 查看说明
    $(".btnComment").on("click", function () {
        var comment = $(this).attr("data_row_comment");
        if (!comment) {
            comment = "无";
        }
        $("#dialogComment").html(comment);
        $(".showCommentTitle").html("申请说明");
        MU.mask();
        MU.center("#showComment");
        $("#showComment").show();
    });

    // 审批
    $(".btnApprove").on("click", function () {
        var applyId = $(this).attr("data-row-applyid");
        $("#approveDialogApplyId").val(applyId);
        $("#approveComment").val("");
        $(".showApproveTitle").html("审批");
        MU.mask();
        MU.center("#showApprove");
        $("#showApprove").show();
    });

});

// 提交审批
$(".btnDoApprove").on("click", function () {
    var applyId = $("#approveDialogApplyId").val();
    var approveComment = $("#approveComment").val();
    $.ajax({
        url:"submitApprove",
        type:"post",
        dataType: "json",
        data:{applyId:applyId,approveComment:approveComment},
        success:function (resp) {
            if (resp && resp.result == 0) {
                MU.msgTips("success", "审批提交成功");
                dt.ajax.reload();
                MU.hide($(".btnDoApprove"));
            } else {
                MU.msgTips("error", resp.message, 1000);
            }
        },
        error: function () {
            MU.msgTips("error", "审批失败，请稍后重试", 1000);
        }
    });
});

// 选择状态处理
$("#queryStatusWrapper ul li").on("click", function() {
    var value = $(this).attr("value");
    if (value == "") {
        $("#queryStatusWrapper span").html("状态");
    } else {
        $("#queryStatusWrapper span").html(STATUS_TEXT[value]);
    }
    $("#queryStatusWrapper input").val(value);
    dt.ajax.reload();
});
