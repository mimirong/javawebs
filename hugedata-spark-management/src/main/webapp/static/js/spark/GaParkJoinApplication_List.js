var STATUS_TEXT = {
	"CREATED": "待审批",
	"APPROVED": "已审批",
};

$("#queryStatusWrapper input").val("");

var currentApplicationId = null;

// Columns
var columns =[
	{
		data: "applicationId"
	}, {
		data: "companyName"
	}, {
		data: "createTime",
		render: function(val) {
			return DateFormat.format.date(val, "yyyy-MM-dd HH:mm:ss");
		}
	}, {
		data: "status",
		render: function(val) {
			var text = STATUS_TEXT[val];
			return text ? text : val;
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			if (row.status == "CREATED") {
				return '<a class="txt-op btnStartApprove" href="javascript:;" data-row-id="' + row.applicationId + '">审批</a>';
			} else {
				return '<a class="txt-op btnViewDetails" href="javascript:;" data-row-id="' + row.applicationId + '">查看</a>';
			}
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

// 选择状态处理
$("#queryStatusWrapper ul li").on("click", function() {
	var value = $(this).attr("value");
	if (value == "") {
		$("#queryStatusWrapper span").html("当前状态");
	} else {
		$("#queryStatusWrapper span").html(STATUS_TEXT[value]);
	}
	$("#queryStatusWrapper input").val(value);
	dt.ajax.reload();
});

// 每行事件
dt.on("draw", function(settings, data) {
	// 显示审批
	$(".btnStartApprove").off().on("click", function(e) {
		currentApplicationId = $(e.target).attr("data-row-id");
		$.ajax({
			url: "toModify",
			type: "post",
			dataType: "json",
			data: { id:currentApplicationId },
			success: function(resp) {
				if (resp && resp.result == 0) {
					var data = JSON.parse(resp.data.content);
					
					$("#viewCompanyName").val(data.companyName);
					$("#viewRepresentative").val(data.representative);
					$("#viewContact").val(data.contact);
					$("#viewContactMobile").val(data.contactMobile);
					$("#viewTelephone").val(data.telephone);
					$("#viewRegCapDomestic").val(data.regCapDomestic);
					$("#viewRegCapForeign").val(data.regCapForeign);
					$("#viewRegAddress").val(data.regAddress);
					$("#viewBusinessScope").val(data.businessScope);
					$("#viewCompanyContactName").val(data.companyContactName);
					$("#viewCompanyContactTel").val(data.companyContactTel);
					$("#viewCompanyContactEmail").val(data.companyContactEmail);
					$("#viewPlatformContactName").val(data.platformContactName);
					$("#viewPlatformContactTel").val(data.platformContactTel);
					$("#viewPlatformContactEmail").val(data.platformContactEmail);
					$("#viewInvestContactName").val(data.investContactName);
					$("#viewInvestContactTel").val(data.investContactTel);
					$("#viewInvestContactEmail").val(data.investContactEmail);
					$("#viewCompanyRemark").val(data.companyRemark);
					$("#viewInvestRemark").val(data.investRemark);

					$("#viewFormWrapper input").prop("readonly", true);
					$("#approveRemark").val("").prop("readonly", false);

					$("#detailsTitle").html("审批");
					$("#btnSubmitApprove").show();
					MU.mask();
					MU.center("#detailsPanel");
					$("#detailsPanel").show();	
				} else {
					MU.msgTips("error", resp.message, 1200);
				}
			},
			error: function() {
				MU.msgTips("error", "获取申请信息失败，请稍后重试", 1200);
			}
		});
	});
	
	// 查看
	$(".btnViewDetails").off().on("click", function(e) {
		currentApplicationId = $(e.target).attr("data-row-id");
		$.ajax({
			url: "toModify",
			type: "post",
			dataType: "json",
			data: { id:currentApplicationId },
			success: function(resp) {
				if (resp && resp.result == 0) {
					var data = JSON.parse(resp.data.content);
					
					$("#viewCompanyName").val(data.companyName);
					$("#viewRepresentative").val(data.representative);
					$("#viewContact").val(data.contact);
					$("#viewContactMobile").val(data.contactMobile);
					$("#viewTelephone").val(data.telephone);
					$("#viewRegCapDomestic").val(data.regCapDomestic);
					$("#viewRegCapForeign").val(data.regCapForeign);
					$("#viewRegAddress").val(data.regAddress);
					$("#viewBusinessScope").val(data.businessScope);
					$("#viewCompanyContactName").val(data.companyContactName);
					$("#viewCompanyContactTel").val(data.companyContactTel);
					$("#viewCompanyContactEmail").val(data.companyContactEmail);
					$("#viewPlatformContactName").val(data.platformContactName);
					$("#viewPlatformContactTel").val(data.platformContactTel);
					$("#viewPlatformContactEmail").val(data.platformContactEmail);
					$("#viewInvestContactName").val(data.investContactName);
					$("#viewInvestContactTel").val(data.investContactTel);
					$("#viewInvestContactEmail").val(data.investContactEmail);
					$("#viewCompanyRemark").val(data.companyRemark);
					$("#viewInvestRemark").val(data.investRemark);
                    
					$("#viewFormWrapper input").prop("readonly", true);
					$("#approveRemark").val(resp.data.approveComment).prop("readonly", true);
					
					$("#detailsTitle").html("查看");
					$("#btnSubmitApprove").hide();
					MU.mask();
					MU.center("#detailsPanel");
					$("#detailsPanel").show();	
				} else {
					MU.msgTips("error", resp.message, 1200);
				}
			},
			error: function() {
				MU.msgTips("error", "获取申请信息失败，请稍后重试", 1200);
			}
		});
	});
});

// 审批
$("#btnSubmitApprove").on("click", function() {
	var approveRemark = $("#approveRemark").val();
	if (approveRemark == "") {
		MU.msgTips("warn", "请输入审批意见");
		return;
	}
	
	$.ajax({
		url: "submitApprove",
		type: "post",
		dataType: "json",
		data: {
			applicationId: currentApplicationId,
			approveComment: approveRemark
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "审批提交成功");
				dt.ajax.reload();
				MU.hide($("#btnSubmitApprove"));
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "提交审批失败，请稍后重试", 1200);
		}
	});
});

// 弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$("ul.form").css("height", wh - 190 + "px");
	$(".panelSizeWrapper").css("height", wh - 80 + "px");
});
$(window).trigger("resize");

