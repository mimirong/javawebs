var modifyingId = null;

// Columns
var columns =[
	{
		data: null,
		render: function(row) {
			var specs = JSON.parse(row.extraData);
			if (row.applyType == "STORAGE") {
				return specs.size;
			} else if (row.applyType == "COMPUTING") {
				return "CPU：" + specs.cpu + "，内存：" + specs.memory + "，硬盘：" + specs.disk + "，宽带：" + specs.bandwidth;
			} else {
				return "";
			}
		}
	}, {
		data: "serviceDuration",
		render: function(val) {
			return val + "月";
		}
	}, {
		data: "amount"
	}, {
		data: "price",
		render: function(val) {
			if (val > 0) {
				return val + "元";
			} else {
				return "";
			}
		}
	}, {
		data: "createTime",
		render: function(val) {
			return DateFormat.format.date(val, "yyyy-MM-dd");
		}
	}, {
		data: "applyType",
		render: function(val) {
			switch (val) {
				case "COMPUTING": return "云主机";
				case "STORAGE": return "云存储";
				default: return "";
			}
		}
	}, {
		data: "specName"
	}, {
		data: "status",
		render: function(val) {
			switch (val) {
				case "CREATED":  return '<span class="red-font">待审批</span>';
				case "APPROVED": return '<span class="gray">已审批</span>';
				case "REJECTED": return '<span class="gray">审批不通过</span>';
				default:         return val;
			}
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("techserv/service_apply/approve")) {
				if (row.status == "CREATED") {
					m.push('<a class="txt-op btnShowApprove" data-row-id="' + row.applyId + '" href="javascript:;">审批</a>');
				}
			}
			if (CommonsPrivileges.hasPrivilegeId("techserv/service_apply/delete")) {
				m.push('<a class="txt-op btnDelete" data-row-id="' + row.applyId + '" href="javascript:;">删除</a>');
			}
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

// 初始化日期选择
$(".dateInput").on("focusin", function(e) {
	$(this).prop('readonly', true);
}).on("focusout", function(e) {
	$(this).prop('readonly', false);
}).datepicker({
    showOtherMonths: true,
    selectOtherMonths: true,
    dateFormat: "yy-mm-dd"
});

// 搜索
$(".btnQuery").on("click", function() {
	dt.ajax.reload();
});
$("input.queryItem").on("keydown", function(e) {
	if (e.keyCode == 13) {
		dt.ajax.reload();	
	}
});

//每行事件
dt.on("draw", function(settings, data) {
	// 审批
	$(".btnShowApprove").off().on("click", function(e) {
		var applyId = $(e.target).attr("data-row-id");

		MU.mask();
		MU.center("#approvePanel");
		$("#approvePanel").show();
		
		$("#btnSetApprove").off().on("click", function() {
			$.ajax({
				url: "approve",
				type: "post",
				dataType: "json",
				data: {
					applyId: applyId
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						MU.msgTips("success", "审批成功");
						MU.hide($("#btnSetApprove"));
						dt.ajax.reload();
					} else {
						MU.msgTips("error", resp.message);
					}
				},
				error: function() {
					MU.msgTips("error", "审批失败，请稍后重试");
				}
			});
		});
	});
});
