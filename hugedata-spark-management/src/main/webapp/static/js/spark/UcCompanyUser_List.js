var modifyingId = null;

function companyTypeText(type) {
	switch (type) {
	    case "1": return "行政单位";
	    case "2": return "事业单位";
	    case "3": return "企业单位";
	    case "4": return "军事单位";
	    case "5": return "其他单位";
	    case "6": return "个人";
	    default:  return type;
    }
}

// Columns
var columns =[
	{
		data: "loginName"
	}, {
		data: "companyName",
		render: function(val, type, row) {
			return '<a class="btnDetails blue-font" href="javascript:;" title="注册账号信息" data-row-id="' + row.userId + '">' + val + '</a>';
		}
	}, {
		data: "email"
	}, {
		data: "mobile"
	}, {
		data: "usable",
		render: function(val) {
			if (val) {
				return "可用";
			} else {
				return "不可用";
			}
		}
	}, {
		data: "createTime",
		render: function(val) {
			return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("company_account/setEnable")) {
				if (row.usable) {
					m.push('<a class="op-start txt-op op-isview1 btnSetDisable" data-row-id="' + row.userId + '" href="javascript:;">停用</a>');
				} else {
					m.push('<a class="op-start txt-op op-isview1 btnSetEnable" data-row-id="' + row.userId + '" href="javascript:;">启用</a>');	
				}
			}
			if (CommonsPrivileges.hasPrivilegeId("company_account/delete")) {
				m.push('<a class="op-del txt-op btnDelete" data-row-id="' + row.userId + '" href="javascript:;">删除</a>');
			}
			if (CommonsPrivileges.hasPrivilegeId("company_account/sendApproveMail")) {
				m.push('<a class="txt-op btnSendApproveMail" data-row-id="' + row.userId + '" href="#">');
				if (row.usable) {
					m.push("账号开通通知");
				} else {
					m.push("账号停用通知");
				}
				m.push('</a>');
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

// 每行事件
dt.on("draw", function(settings, data) {
	// 启用
	$(".btnSetEnable").off().on("click", function(e) {
		var userId = $(e.target).attr("data-row-id");
		$.ajax({
			url: "setEnable",
			type: "post",
			dataType: "json",
			data: {
				userId : userId,
				enable : true
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					dt.ajax.reload();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "启用失败，请稍后重试");
			}
		});
	});

	// 停用
	$(".btnSetDisable").off().on("click", function(e) {
		var userId = $(e.target).attr("data-row-id");
		$.ajax({
			url: "setEnable",
			type: "post",
			dataType: "json",
			data: {
				userId : userId,
				enable : false
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					dt.ajax.reload();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "停用失败，请稍后重试");
			}
		});
	});

	// 发送邮件
	$(".btnSendApproveMail").off().on("click", function(e) {
		var userId = $(e.target).attr("data-row-id");
		var emailName = $(e.target).html();
		MU.conFirm("发送邮件", "确认" + emailName + "?", function() {
			$.ajax({
				url: "sendApproveMail",
				type: "post",
				dataType: "json",
				data: {
					userId : userId
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						dt.ajax.reload();
					} else {
						MU.msgTips("error", resp.message);
					}
				},
				error: function() {
					MU.msgTips("error", "发送邮件失败，请稍后重试");
				}
			});
		});
	});

	// 详情
	$(".btnDetails").off().on("click", function(e) {
		var userId = $(e.target).attr("data-row-id");
		$.ajax({
			url: "toModify",
			type: "post",
			dataType: "json",
			data: { id:userId },
			success: function(resp) {
				if (resp && resp.result == 0) {
					if (!resp.data.companyInfoJson || resp.data.companyInfoJson == "") {
						MU.msgTips("error", "获取企业信息失败");
						return;
					}
					
					var companyInfo = JSON.parse(resp.data.companyInfoJson);
					$("#detailsPanel .showCompanyName").html(companyInfo.companyName);
					$("#detailsPanel .showCompanyType").html(companyTypeText(companyInfo.companyType));
					$("#detailsPanel .showCompanyAddress").html(companyInfo.companyAddress);
					$("#detailsPanel .showOrganizationCode").html(companyInfo.organizationCode);
					$("#detailsPanel .showLicenceCode").html(companyInfo.licenceCode);
					$("#detailsPanel .showRepresentative").html(companyInfo.representative);
					$("#detailsPanel .showRepresentativeId").html(companyInfo.representativeId);
					$("#detailsPanel .showContactName").html(companyInfo.contactName);
					$("#detailsPanel .showContactMobile").html(companyInfo.contactMobile);
					$("#detailsPanel .showContactEmail").html(companyInfo.contactEmail);
					
					MU.mask();
					MU.center("#detailsPanel");
					$("#detailsPanel").show();	
				} else {
					MU.msgTips("error", resp.message, 1200);
				}
			},
			error: function() {
				MU.msgTips("error", "获取用户信息失败，请稍后重试", 1200);
			}
		});
	});
});