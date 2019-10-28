var serviceId = null;
var currentService = null;
$("#queryStatus").val("");

var SR_WINDOW            = "WINDOW";            //窗口
var SR_DEPUTY_DIRECTOR   = "DEPUTY_DIRECTOR";   //副局长
var SR_DIRECTOR          = "DIRECTOR";          //局长
var SR_DEPUTY_CHIEF      = "DEPUTY_CHIEF";      //副主任
var SR_CHIEF             = "CHIEF";             //主任
var SR_SECRETARY_GENERAL = "SECRETARY_GENERAL"; //书记

var STATUS_CREATED             = "110";  //待预审
var STATUS_TO_FIRST_CHECK      = "120";  //待初审
var STATUS_TO_REVIEW           = "130";  //待复审
var STATUS_TO_DECIDE           = "140";  //待决定
var STATUS_TO_DIRECTOR_DECIDE  = "150";  //待主任决定
var STATUS_TO_SECRETARY_DECIDE = "160";  //待书记决定
var STATUS_FINISH_CONFIRM      = "210";  //办结待确认
var STATUS_FINISHED            = "211";  //已办结
var STATUS_REJECT_CONFIRM      = "220";  //废弃待确认
var STATUS_REJECTED            = "221";  //已废弃
var STATUS_CANCELLED           = "231";  //已废弃(用户撤销)

var STATUS_OP = {
	//状态,    状态全名,          操作,			下一步操作, 			是否选择用户,    下一步用户角色,                       是否可以deliver       是否可以approve      是否可以reject
	"110": { TEXT:"待预审",		OP:"预审",		NEXT_OP:"初审", 		USER_SEL:true, 	NEXT_USER_ROLE:SR_DEPUTY_DIRECTOR,   ALLOW_DELIVER:true,  ALLOW_APPROVE:false, ALLOW_REJECT:true },
	"120": { TEXT:"待初审",		OP:"初审",		NEXT_OP:"复审", 		USER_SEL:true, 	NEXT_USER_ROLE:SR_DIRECTOR,          ALLOW_DELIVER:true,  ALLOW_APPROVE:false, ALLOW_REJECT:true },
	"130": { TEXT:"待复审",		OP:"复审",		NEXT_OP:"决定", 		USER_SEL:true, 	NEXT_USER_ROLE:SR_DEPUTY_CHIEF,      ALLOW_DELIVER:true,  ALLOW_APPROVE:true,  ALLOW_REJECT:true },
	"140": { TEXT:"待决定",		OP:"决定",		NEXT_OP:"主任决定", 	USER_SEL:true,  NEXT_USER_ROLE:SR_CHIEF,             ALLOW_DELIVER:true,  ALLOW_APPROVE:true,  ALLOW_REJECT:true },
	"150": { TEXT:"待主任决定",	OP:"主任决定",	NEXT_OP:"书记决定", 	USER_SEL:true,  NEXT_USER_ROLE:SR_SECRETARY_GENERAL, ALLOW_DELIVER:true,  ALLOW_APPROVE:true,  ALLOW_REJECT:true },
	"160": { TEXT:"待书记决定",	OP:"书记决定",	NEXT_OP:"最终确认", 	USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:true,  ALLOW_REJECT:true },
	"210": { TEXT:"待最终确认",	OP:"最终确认",	NEXT_OP:"", 		USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:false, ALLOW_REJECT:false },
	"220": { TEXT:"待最终确认",	OP:"最终确认",	NEXT_OP:"", 		USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:false, ALLOW_REJECT:false },
	"211": { TEXT:"已办结",		OP:"",			NEXT_OP:"", 		USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:false, ALLOW_REJECT:false },
	"221": { TEXT:"已废弃",		OP:"",			NEXT_OP:"", 		USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:false, ALLOW_REJECT:false },
	"231": { TEXT:"已废弃",		OP:"",			NEXT_OP:"", 		USER_SEL:false, NEXT_USER_ROLE:null,                 ALLOW_DELIVER:false, ALLOW_APPROVE:false, ALLOW_REJECT:false }
};

var STATUS_TEXT = {
	"110": "待预审",
	"120": "已受理",
	"130": "已受理",
	"140": "已受理",
	"150": "已受理",
	"160": "已受理",
	"210": "已受理",
	"220": "已受理",
	"211": "已办结",
	"221": "废弃件",
	"231": "废弃件"
};

var DEPT_CONFIG = GLOBAL_DEPT_CONFIG;

var columns = [
	{
		data : null,
		render : function(row) {
			return '<a href="javascript:;" class="btnModify blue-font" data-row-id="' + row.serviceId + '">' + row.businessNo + '</a> ';
		}
	}, {
		data: "guidename"
	}, {
		data : "createTime",
		render : function(val) {
			return DateFormat.format.date(new Date(val),'yyyy-MM-dd');
		}
	}, {
		data: "businessDeptPerson",
		createdCell: function(tr) {
			$(tr).css("text-align", "center");
		}
	}, {
		data : "cellphone"
	}, {
		data : null,
		orderable : false,
		render : function(row) {
			var statusText = (STATUS_TEXT[row.status] ? STATUS_TEXT[row.status] : row.status);
			if (checkAllowProcess(row)) {
				return '<a href="javascript:;" class="btnModify blue-font" data-row-id="' + row.serviceId + '">' + statusText + '</a> ';
			} else {
				return '<span class="grey">' + statusText + '</span> ';
			}
		}
	}
];

// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
	ajax : {
		url : "listData",
		data : function(data) {
			ListFeaturePage.loadQueryValues(data, ".queryItem");
		}
	},
	lengthChange : false,
	serverSide : true,
	searching : false,
	ordering : false,
	columns : columns
}));

// 状态选择
$("#statusSelect li").on("click", function(e) {
	var li = $(e.target);
	var value = li.attr("data-value");
	$("#statusSelect input").val(value);
	$("#statusSelect span").html(value == "" ? "状态" : li.html());
	dt.ajax.reload();
});

// 搜索
$("#btnSearch").on("click", function() {
	dt.ajax.reload();
});
$("input.queryItem").on("keydown", function(e) {
	if (e.keyCode == 13) {
		dt.ajax.reload();
	}
});

// 每行事件
dt.on("draw", function(settings, data) {
	// 处理
	$(".btnModify").off().on("click",function(e) {
		serviceId = $(e.target).attr("data-row-id");
		$.ajax({
			url : "toModify",
			type : 'POST',
			dataType : "json",
			data: {
				id: serviceId
			},
			success : function(resp) {
				if (resp && resp.result == 0) {
					var service = resp.data;
					var attachments = JSON.parse(resp.data.attachmentsJson);
					var attConfigs = JSON.parse(resp.data.attConfigsJson);
					var procList = JSON.parse(resp.data.procListJson);
					currentService = service;
					
					// 显示基本信息
					$("#businessNo").text(service.businessNo);
					$("#guidename").text(service.guidename);
					$("#timeLimit").text(service.timeLimit);
					$("#businessDeptPerson").text(service.businessDeptPerson);
					$("#cellphone").text(service.cellphone);
					$("#deptName").text(service.deptname);
					$("#acceptWindowName").html(service.acceptWindowName);
					$("#acceptUserName").html(service.acceptUserName);
					$("#acceptCellphone").html(service.acceptCellphone);
					$("#createTime").text(DateFormat.format.date(service.createTime, "yyyy-MM-dd"));
					if (service.acceptTime) {
						$("#acceptTime").html(DateFormat.format.date(service.acceptTime, "yyyy-MM-dd"));
					}
					if (service.finishTime) {
						$("#finishTime").html(DateFormat.format.date(service.finishTime, "yyyy-MM-dd"));
					}
					$("#statusText").html(STATUS_TEXT[service.status]);
					$("#remark").val("");
					
					// 显示附件信息
					showAttachmentsData(attConfigs, attachments);
					
					// 显示或隐藏审批界面 (通过/不通过按钮，审批意见)
					checkAllowProcess(service) ? $(".approveWrapper").show() : $(".approveWrapper").hide();
					
					// 隐藏操作按钮，隐藏用户选择 (点击"通过"时显示)
					$(".operationWrapper").hide();
					$("#btnDeliver").hide();
					$("#btnDeliverPre").hide();
					$("#btnApprove").hide();
					$("#btnReject").hide();
					$(".userSelect").hide();
					$(".ccUserSelect").hide();
					
					// 显示操作名称
					if (STATUS_OP[service.status].OP && STATUS_OP[service.status].OP != "") {
						$(".statusOp").html(STATUS_OP[service.status].OP);
						$(".showWithStatusOp").show();
					} else {
						$(".statusOp").html("");
						$(".showWithStatusOp").hide();
					}
					
					// 显示下一步操作名称
					if (STATUS_OP[service.status].NEXT_OP && STATUS_OP[service.status].NEXT_OP != "") {
						$(".statusNextOp").html(STATUS_OP[service.status].NEXT_OP);
						$(".showWithStatusNextOp").show();
					} else {
						$(".statusNextOp").html("");
						$(".showWithStatusNextOp").hide();
					}
					
					// 通过/不通过按钮默认选中
					if (service.status == STATUS_REJECT_CONFIRM && window.login.serviceRole == 'WINDOW') {
						$("#btnServiceApprove").removeClass("checked");
						$("#btnServiceReject").addClass("checked");
						$("#btnFinish").show();
					} else {
						$("#btnServiceApprove").removeClass("checked");
						$("#btnServiceReject").removeClass("checked");
						$("#btnFinish").hide();
					}
					
					// 办结文书上传
					if (service.status == STATUS_FINISH_CONFIRM) {
						$(".finishDocUpload").show();
					} else {
						$(".finishDocUpload").hide();
					}
					
					// 显示操作记录
					$("#procListWrapper").empty();
					$.each(procList, function(i, proc) {
						var h3a = $("<a>").addClass("status").attr("href", "javascript:;");
						switch (proc.status) {
							case "APPROVE":   		h3a.addClass("pass").append("通过");      break;
							case "DELIVER":   		h3a.addClass("pass").append("通过");      break;
							case "REJECT":   		h3a.addClass("nopass").append("不通过");  break;
							case "FINISH":    		h3a.addClass(proc.serviceStatus == STATUS_FINISH_CONFIRM ? "pass" : "nopass").append("最终确认"); break;
							case "FINISH_APPROVED": h3a.addClass("pass").append("最终确认通过");   		break;
							case "FINISH_REJECTED": h3a.addClass("nopass").append("最终确认不通过");  	break;
							case "CANCEL": 			h3a.addClass("nopass").append("撤销办事");  	break;
						};
						
						var h3b = $("<a>").addClass("name").attr("href", "javascript:;")
								.append(proc.userName).append(" ")
								.append(DateFormat.format.date(proc.createTime, "yyyy-MM-dd HH:mm:ss"));
						
						var h3 = $("<h3>").addClass("detail-title")
								.append(STATUS_OP[proc.serviceStatus].OP + "结果")
								.append(h3a).append(h3b);
						
						var table = $("<table>").addClass("table").css("width", "98%");
						var tr = $("<tr>").appendTo(table);
						$("<td>").addClass("tit").append("反馈意见").appendTo(tr);
						$("<td>").attr("colspan", "3").append(proc.remark).appendTo(tr);
						
						$("#procListWrapper").append(h3).append(table);
					});

					// 受理通知书按钮/办结文书按钮
					if ([STATUS_TO_FIRST_CHECK, STATUS_TO_REVIEW, STATUS_TO_DECIDE, STATUS_TO_DIRECTOR_DECIDE, STATUS_TO_SECRETARY_DECIDE, STATUS_FINISH_CONFIRM, STATUS_REJECT_CONFIRM].indexOf(service.status) >= 0
							&& service.acceptNoticeFileId && service.acceptNoticeFileId != "") {
						$("#btnDownAcceptNotice").show().off("click").on("click", function() {
							window.open(DownloadService.buildUrl(service.acceptNoticeFileId, service.acceptNoticeFileName));
						});
					} else {
						$("#btnDownAcceptNotice").hide();
					}
					
					if (STATUS_FINISHED == service.status && service.finishDocFileId && service.finishDocFileId != "") {
						$("#btnDownFinishDoc").show().off("click").on("click", function() {
							window.open(DownloadService.buildUrl(service.finishDocFileId, service.finishDocFileName));
						});	
					} else {
						$("#btnDownFinishDoc").hide();					
					}
					
					// 隐藏办结文书上传
					$(".finishDocUpload").hide();

					// 当前部门和处理人信息
					(function() {
						if (checkAllowProcess(service) || !service.currentUserId || service.currentUserId == 0) {
							return;
						}
						var h3a = $("<a>").addClass("status").attr("href", "javascript:;").append("待处理");
						var h3b = $("<a>").addClass("name").attr("href", "javascript:;")
								.append(service.currentDeptName).append(" ")
								.append(service.currentUserName).append(" ");
						var h3 = $("<h3>").addClass("detail-title")
								.append(STATUS_OP[service.status].OP + "结果")
								.append(h3a).append(h3b);
						$("#procListWrapper").append(h3);
					})();
					
					// 抄送部门选择
					$("#ccDeptSelector select").empty().append('<option value="">--选择抄送部门--</option>');
					$.each(DEPT_CONFIG, function(i, dept) {
						$("<option>").attr("value", dept.code).html(dept.name).appendTo("#ccDeptSelector select");
					});
					$("#ccDeptSelector select").selectList();
					bindCcDeptSelectEvents();
					
					// 抄送人选择
					$("#ccUserSelector select").empty().append('<option value="">--选择抄送人--</option>');
					$("#ccUserSelector select").selectList();
					
					// 显示弹框
					$("#btnDoModify").show();
					MU.mask();
					MU.center("#approvePanel");
					$("#approvePanel").show();
					setTimeout(function() {
						$(".popupWrapper").scrollTop(0);
					},100);
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error : function() {
				MU.msgTips("error", "办理失败，请稍后重试");
			}
		});
	});
});

// 显示申请材料信息
function showAttachmentsData(attConfigs, attachments) {
	var acMap = {};
	$.each(attConfigs, function(i, ac) {
		acMap[ac.attConfigId] = ac;
		ac.files = [];
		ac.fileCount = 0;
	});

	$.each(attachments, function(i, att) {
		ac = acMap[att.attConfigId];
		if (!ac) {
			return;
		}
		ac.files.push(att);
		ac.status = att.approveStatus;
		if (att.fileId != "NOFILE") {
			ac.fileCount++;
		}
	});

	// 显示
	$("#attachmentsWrapper").empty();
	$.each(attConfigs, function(i, ac) {
		var tr = $("<tr>")
				.addClass("attachmentItemWrapper")
				.attr("data-attConfigId", ac.attConfigId)
				.attr("data-attConfigName", ac.attConfigName)
				.appendTo($("#attachmentsWrapper"));
		// td 1
		var btnApprove = $("<a>").html("通过");
		var btnReject = $("<a>").html("不通过").addClass("ml10");
		if (ac.status == "APPROVED") {
			btnApprove.addClass("grey").removeClass("blue");
			btnReject.addClass("blue").removeClass("grey");
		} else if (ac.status == "REJECTED") {
			btnApprove.addClass("blue").removeClass("grey");
			btnReject.addClass("grey").removeClass("blue");
		} else {
			btnApprove.addClass("blue").removeClass("grey");
			btnReject.addClass("blue").removeClass("grey");
		}
		$("<td>").append(btnApprove).append(btnReject).appendTo(tr);
		tr.attr("data-status", ac.status);
		// td 2
		var btnShowAttDownload = $("<a>")
				.attr("href", "javascript:;")
				.attr("title", "附件下载")
				.addClass("blue")
				.html(ac.fileCount);
		$("<td>").append(btnShowAttDownload).appendTo(tr);
		// td 3
		$("<td>").html(ac.attConfigName).appendTo(tr);
		// td 4
		$("<td>").html(ac.files.length > 0 ? ac.files[0].remark : "").appendTo(tr);
		
		// 附件下载事件
		btnShowAttDownload.on("click", function() {
			if (ac.fileCount == 0) {
				MU.msgTips("warn", "没有附件", 800);
				return;
			}
			var div = $("<div>").addClass("pop-content").css("padding", "30px 0");
			var ul = $("<ul>").appendTo(div).addClass("list").css("min-height", "76px");
			$.each(ac.files, function(j, file) {
				if (file.fileId == "NOFILE") {
					return;
				}
				var url = DownloadService.buildUrl(file.fileId, file.fileName); 
				var a = $("<a>").attr("href", url).html(file.fileName);
				$("<li>").append(a).appendTo(ul);
			});
			MU.alert(div.prop("outerHTML"), 600, "附件下载");
		});
		
		// 审批附件通过/不通过
		btnApprove.on("click", function() {
			tr.attr("data-status", "APPROVED");
			btnApprove.addClass("grey").removeClass("blue");
			btnReject.addClass("blue").removeClass("grey");
		});
		btnReject.on("click", function() {
			tr.attr("data-status", "REJECTED");
			btnApprove.addClass("blue").removeClass("grey");
			btnReject.addClass("grey").removeClass("blue");
		});
		
	});
}

// 获取附件信息
function findAttachmentApproveData() {
	var arr = [];
	$(".attachmentItemWrapper").each(function() {
		var tr = $(this);
		arr.push({
			attConfigId: tr.attr("data-attConfigId"),
			attConfigName: tr.attr("data-attConfigName"),
			status: tr.attr("data-status")
		});
	});
	return arr;
}

// 点击"通过"复选框
$("#btnServiceApprove").off("click").on("click", function(e) {
	var status = currentService.status;
	
	if (status == STATUS_REJECT_CONFIRM) {
		MU.msgTips("warn", "办件已经审批不通过，无法审批通过");
		e.stopPropagation();
		
	} else if (status == STATUS_FINISH_CONFIRM) {
		$(".finishDocUpload").show();
		$("#btnFinish").show();
		$(".ccUserSelect").show();
		
	} else {
		$(".userSelect").hide();
		$("#btnReject").hide();
		if (checkAllowProcess(currentService)) {
			STATUS_OP[status].ALLOW_DELIVER ? $("#btnDeliverPre").show() : $("#btnDeliverPre").hide();
			STATUS_OP[status].ALLOW_APPROVE ? $("#btnApprove").show() : $("#btnApprove").hide();
		} else {
			$("#btnDeliver").hide();
			$("#btnApprove").hide();
		}
	}
});

//点击"不通过"复选框
$("#btnServiceReject").off("click").on("click", function(e) {
	var status = currentService.status;
	
	if (status == STATUS_REJECT_CONFIRM) {
		// 审批不通过确认时不能点击审批按钮
		e.stopPropagation();
		
	} else if (status == STATUS_FINISH_CONFIRM) {
		$(".finishDocUpload").hide();
		$("#btnFinish").show();
		$(".ccUserSelect").hide();
		
	} else {
		$("#btnDeliverPre").hide();
		$("#btnApprove").hide();
		$(".userSelect").hide();
		if (checkAllowProcess(currentService)) {
			STATUS_OP[status].ALLOW_REJECT ? $("#btnReject").show() : $("#btnReject").hide();
		} else {
			$("#btnReject").hide();
		}
	}
});

//抄送部门选择
function bindCcDeptSelectEvents() {
	$("#ccDeptSelector ul li").on("click", function() {
		var deptCode = $(this).attr("data-value");
		if (deptCode == "") {
			$("#ccUserSelector select").html('<option value="">--选择抄送人--</option>').selectList();
			return;
		}
		
		$.ajax({
			url: "queryDeptUsers",
			type: "post",
			dataType: "json",
			data: {
				deptCode: deptCode,
				serviceRole: null
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var m = [ '<option value="">--选择抄送人--</option>' ]
					$.each(resp.data, function(i, user) {
						m.push('<option value="' + user.userId + '">' + user.name + '</option>');
					});
					$("#ccUserSelector select").html(m.join("")).selectList();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "查询用户信息失败");
			}
		});
	});
}

//转交下一步 DeliverPre
$("#btnDeliverPre").on("click", function() {
	var status = currentService.status;
	if (STATUS_OP[status].USER_SEL) {
		$(".userSelect").show();
		$("#btnDeliver").show()
		var w = $(".popupWrapper");
		w.scrollTop(w.prop("scrollHeight"));
	} else {
		$(".userSelect").hide();
	}
});

//部门选择
$(function() {
	$("#deptSelector select").html('<option value="">--请选择接收部门--</option>');
	$.each(DEPT_CONFIG, function(i, dept) {
		$("<option>").attr("value", dept.code).html(dept.name).appendTo("#deptSelector select");
	});
	$("#deptSelector select").selectList();
	
	$("#deptSelector ul li").on("click", function() {
		var deptCode = $(this).attr("data-value");
		if (deptCode == "") {
			$("#userSelector select").html('<option value="">--请选择接收人--</option>').selectList();
			return;
		}
		
		$.ajax({
			url: "queryDeptUsers",
			type: "post",
			dataType: "json",
			data: {
				deptCode: deptCode,
				serviceRole: STATUS_OP[currentService.status].NEXT_USER_ROLE
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var m = [ '<option value="">--请选择接收人--</option>' ]
					$.each(resp.data, function(i, user) {
						m.push('<option value="' + user.userId + '">' + user.name + '</option>');
					});
					$("#userSelector select").html(m.join("")).selectList();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "查询用户信息失败");
			}
		});
	});
});

// 下一步 Deliver
$("#btnDeliver").on("click", function() {
	// 获取附件信息
	var attData = findAttachmentApproveData();
	for (var i = 0; i < attData.length; i++) {
		if (attData[i].status != "APPROVED" && attData[i].status != "REJECTED") {
			MU.msgTips("warn", '请选择附件"' + attData[i].attConfigName + '"的审批结果');
			return;
		}
	}
	
	// 反馈意见
	var remark = $("#remark").val();
	if (remark == "") {
		//MU.msgTips("warn", "请输入反馈意见");
		//$("#remark").focus();
		//return null;
		remark = "无";
	}
	if (remark.length > 200) {
		MU.msgTips("warn", "反馈意见不能超过200字，当前" + remark.length + "字");
		$("#remark").focus();
		return null;
	}
	
	// 获取下一步用户信息
	var nextUserId = null,
    	nextDeptId = null;
	if (STATUS_OP[currentService.status].USER_SEL) {
		nextUserId = $("#userSelector select").val();
		nextDeptId = $("#deptSelector select").val();
	}
	if (!nextDeptId || nextDeptId == "" || !nextUserId || nextUserId == "") {
		MU.msgTips("warn", "请选择接收人");
		return null;
	}
	
	$.ajax({
		url: "deliver",
		type: "POST",
		dataType: "json",
		data: {
            serviceId: currentService.serviceId,
            remark: remark,
            nextUserId: nextUserId,
            nextDeptId: nextDeptId,
            attachmentStatusJson: JSON.stringify(attData)
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交成功", 1200);
				MU.hide($("#btnDeliver"));
				dt.ajax.reload();
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "提交失败，请稍后重试", 1200);
		}
	});
});

// 审批不通过 Reject
$("#btnReject").on("click", function() {
	// 获取附件信息
	var attData = findAttachmentApproveData();
	for (var i = 0; i < attData.length; i++) {
		if (attData[i].status != "APPROVED" && attData[i].status != "REJECTED") {
			MU.msgTips("warn", '请选择附件"' + attData[i].attConfigName + '"的审批结果');
			return;
		}
	}
	
	// 反馈意见
	var remark = $("#remark").val();
	if (remark == "") {
		MU.msgTips("warn", "请输入反馈意见");
		$("#remark").focus();
		return null;
	}
	if (remark.length > 200) {
		MU.msgTips("warn", "反馈意见不能超过200字，当前" + remark.length + "字");
		$("#remark").focus();
		return null;
	}
	
	$.ajax({
		url: "reject",
		type: "POST",
		dataType: "json",
		data: {
         serviceId: currentService.serviceId,
         remark: remark,
         attachmentStatusJson: JSON.stringify(attData)
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交成功", 1200);
				MU.hide($("#btnDeliver"));
				dt.ajax.reload();
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "提交失败，请稍后重试", 1200);
		}
	});
});

//审批通过 Approve
$("#btnApprove").on("click", function() {
	// 获取附件信息
	var attData = findAttachmentApproveData();
	for (var i = 0; i < attData.length; i++) {
		if (attData[i].status != "APPROVED" && attData[i].status != "REJECTED") {
			MU.msgTips("warn", '请选择附件"' + attData[i].attConfigName + '"的审批结果');
			return;
		}
	}
	
	// 反馈意见
	var remark = $("#remark").val();
	if (remark == "") {
		//MU.msgTips("warn", "请输入反馈意见");
		//$("#remark").focus();
		//return null;
		remark = "无";
	}
	if (remark.length > 200) {
		MU.msgTips("warn", "反馈意见不能超过200字，当前" + remark.length + "字");
		$("#remark").focus();
		return null;
	}
	
	$.ajax({
		url: "approve",
		type: "POST",
		dataType: "json",
		data: {
			serviceId: currentService.serviceId,
			remark: remark,
			attachmentStatusJson: JSON.stringify(attData)
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交成功", 1200);
				MU.hide($("#btnDeliver"));
				dt.ajax.reload();
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "提交失败，请稍后重试", 1200);
		}
	});
});

// 确认办结/废弃 Finish
$("#btnFinish").on("click", function() {
	// 获取附件信息
	var attData = findAttachmentApproveData();
	for (var i = 0; i < attData.length; i++) {
		if (attData[i].status != "APPROVED" && attData[i].status != "REJECTED") {
			MU.msgTips("warn", '请选择附件"' + attData[i].attConfigName + '"的审批结果');
			return;
		}
	}
	
	// 是否办结通过
	var isApproved = $("#btnServiceApprove").hasClass("checked");
	
	// 办结文书
	var finishDocFileId = $("#finishDocFileId").val();
	var finishDocFileName = $("#finishDocFileName").val();
//	if (currentService.status == STATUS_FINISH_CONFIRM && finishDocFileId == "") {
//		MU.msgTips("warn", "请上传办结文书");
//		$("#remark").focus();
//		return;
//	}
	
	// 反馈意见
	var remark = $("#remark").val();
	if (remark == "") {
		if (currentService.status == STATUS_FINISH_CONFIRM) {
			remark = "无";
		} else {
			MU.msgTips("warn", "请输入反馈意见");
			$("#remark").focus();
			return;
		}
	}
	if (remark.length > 200) {
		MU.msgTips("warn", "反馈意见不能超过200字，当前" + remark.length + "字");
		$("#remark").focus();
		return;
	}
	
	// 抄送用户和部门
	var ccUserId = null,
	    ccDeptId = null;
	if (currentService.status == STATUS_FINISH_CONFIRM && isApproved) {
		ccUserId = $("#ccUserSelector select").val();
		ccDeptId = $("#ccDeptSelector select").val();
		if (!ccDeptId || ccDeptId == "" || !ccUserId || ccUserId == "") {
			MU.msgTips("warn", "请选择抄送人");
			return null;
		}
	}
	
	$.ajax({
		url: "finish",
		type: "POST",
		dataType: "json",
		data: {
		   serviceId: currentService.serviceId,
		   isApproved: isApproved,
		   remark: remark,
		   attachmentStatusJson: JSON.stringify(attData),
		   finishDocFileId: finishDocFileId,
		   finishDocFileName: finishDocFileName,
		   ccUserId: ccUserId,
		   ccDeptId: ccDeptId
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交成功", 1200);
				MU.hide($("#btnDeliver"));
				dt.ajax.reload();
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "提交失败，请稍后重试", 1200);
		}
	});
});

//判断是否允许办理
function checkAllowProcess(service) {
	var login = window.login
	switch (service.status) {
		case STATUS_CREATED:             //待预审 
			return login.serviceRole == SR_WINDOW;  
		case STATUS_TO_FIRST_CHECK:      //待初审
			return login.serviceRole == SR_DEPUTY_DIRECTOR && login.userId == service.currentUserId;
		case STATUS_TO_REVIEW:           //待复审
			return login.serviceRole == SR_DIRECTOR && login.userId == service.currentUserId;
		case STATUS_TO_DECIDE:           //待决定
			return login.serviceRole == SR_DEPUTY_CHIEF && login.userId == service.currentUserId;
		case STATUS_TO_DIRECTOR_DECIDE:  //待主任决定
			return login.serviceRole == SR_CHIEF && login.userId == service.currentUserId;
		case STATUS_TO_SECRETARY_DECIDE: //待书记决定
			return login.serviceRole == SR_SECRETARY_GENERAL && login.userId == service.currentUserId;
		case STATUS_FINISH_CONFIRM:      //办结待确认
		case STATUS_REJECT_CONFIRM:      //废弃待确认
			return login.serviceRole == SR_WINDOW;
		case STATUS_FINISHED:            //已办结
		case STATUS_REJECTED:            //已废弃
		case STATUS_CANCELLED:           //已撤销
		default:
			return false;
	}
}

//弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$(".popupWrapper").css({
		"height": wh - 60 + "px",
		"overflow": "auto"
	});
});
$(window).trigger("resize");