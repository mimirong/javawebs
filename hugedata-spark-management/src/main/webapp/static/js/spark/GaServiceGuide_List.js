var modifyingId = null;

var DEPT_NAMES = {
    "1": "经济发展局",
    "2": "国土规划局",
    "3": "工程建设局",
    "4": "社会事务局",
    "5": "招商合作局",
    "6": "财政分局",
    "7": "办公室",
    "8": "党群纪检绩效办"
};

// Columns
var columns =[
	{
		data: "guideId"
	}, {
		data: "deptName"
	}, {
		data: "guideName"
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("service_guide/delete")) {
				m.push('<a href="#" class="txt-op btnDelete" data-row-id="' + row.guideId + '">删除</a> ');
			}
			if (CommonsPrivileges.hasPrivilegeId("service_guide/modify")) {
				m.push('<a href="#" class="txt-op btnModify" data-row-id="' + row.guideId + '">修改</a> ');
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
}).on("change", function(e) {
	$(this).valid();
}).datepicker({
    showOtherMonths: true,
    selectOtherMonths: true,
    dateFormat: "yy-mm-dd"
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

// 新增
$(".btnAdd").on("click", function() {
	$("#deptCode").val("").triggerHandler("change");
	$("#guideName").val("");
	$("#guideType").val("");
	$("#serviceSubject").val("");
	$("#according").val("");
	$("#precondition").val("");
	$("#jointDept").val("");
    $("#legalTimeLimit").val("");
    $("#promisedTimeLimit").val("");
    $("#conditions").val("");
    //$("#material").val("");
    $("#process").val("");
	$("#isCharge").val("");
    $("#isCharge_false").removeClass("checked");
    $("#isCharge_true").removeClass("checked");
	$("#chargeAccording").val("");
	$("#chargeStandard").val("");
	$("#address").val("");
	$("#workTime").val("");
	$("#telephone").val("");
	$("#complaintTelephone").val("");
	$("#flowImageFileId").val("");
	$("#flowImageFileName").val("");
	FileUpload.reset("#flowImageWrapper");
	FileUpload.reset("#upAttachments");
    $("#curAttachments").html("");
    window.loadAllAttConfig([]);
    $(".switchTab1").triggerHandler("click");
	
	$(".addPanelTitle").html("添加");
	$("#btnDoAdd").show();
	$("#btnDoModify").hide();
	MU.mask();
	MU.center("#addPanel");
	$("#addPanel").show();
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
					$("#deptCode").val(resp.data.deptCode).triggerHandler("change");
					$("#guideName").val(resp.data.guideName);
					$("#guideType").val(resp.data.guideType);
					$("#serviceSubject").val(resp.data.serviceSubject);
					$("#according").val(resp.data.according);
					$("#precondition").val(resp.data.precondition);
					$("#jointDept").val(resp.data.jointDept);
					$("#legalTimeLimit").val(resp.data.legalTimeLimit);
					$("#promisedTimeLimit").val(resp.data.promisedTimeLimit);
					$("#conditions").val(resp.data.conditions);
					//$("#material").val(resp.data.material);
					$("#process").val(resp.data.process);
					$("#chargeAccording").val(resp.data.chargeAccording);
					$("#chargeStandard").val(resp.data.chargeStandard);
					$("#address").val(resp.data.address);
					$("#workTime").val(resp.data.workTime);
					$("#telephone").val(resp.data.telephone);
					$("#complaintTelephone").val(resp.data.complaintTelephone);
				    $(".switchTab1").triggerHandler("click");
					
					if (resp.data.attConfigJson && resp.data.attConfigJson != "") {
						window.loadAllAttConfig(JSON.parse(resp.data.attConfigJson));
					} else {
						window.loadAllAttConfig([]);
					}
					
					if (resp.data.flowImageFileId && resp.data.flowImageFileId != "") {
						FileUpload.setFile("#flowImageWrapper", resp.data.flowImageFileId, resp.data.flowImageFileName);
					} else {
						FileUpload.reset("#flowImageWrapper");
					}

                    if (resp.data.isCharge) {
    					$("#isCharge_true").addClass("checked");
    					$("#isCharge_false").removeClass("checked");  
                    } else {
    					$("#isCharge_true").removeClass("checked");
    					$("#isCharge_false").addClass("checked");
                    }                  
                    
                    // 附件文件列表
					FileUpload.reset("#upAttachments");
                    $("#curAttachments").html("");
                    $.each(JSON.parse(resp.data.attachmentData), function(i, item) {
                        var filename = $("<span>").html(item.fileName);
                        var deleteBtn = $("<a>").html("删除")
												.attr("href", "javascript:;")
												.addClass("btn")
												.css({ "vertical-align": "middle",
						    						   "line-height":    "22px",
						    						   "width":          "28px",
						    						   "height":         "22px",
						    						   "padding":        "0 22px",
						    						   "margin-left":    "8px"      });
                        console.log(deleteBtn);
                        var p = $("<p>")
                        	.addClass("attachment")
                    		.attr("data-file-id", item.fileId)
                    		.attr("data-file-name", item.fileName)
                        	.append(filename, " ", deleteBtn)
                        	.appendTo("#curAttachments");
                        
                        deleteBtn.on("click", function() {
                        	p.remove();
                        });
                    });

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
				MU.msgTips("error", "获取办事指南信息失败，请稍后重试", 1200);
			}
		});
	});
});

// 检查表单
function checkAddModifyForm() {
	// attConfigData
	var attConfigArr = window.findAllAttConfig();
	var attConfigJson = JSON.stringify(attConfigArr);
	
	//	deptCode
	var deptCode = $("#deptCode").val();
	if (deptCode == "") {
		MU.msgTips("warn", "请选择所属部门", 1200);
		return null;
	}

	//	guideName
	var guideName = $("#guideName").val();
	if (guideName == "") {
		MU.msgTips("warn", "请输入事项名称", 1200);
		$("#guideName").focus();
		return null;
	}
	
	//	guideType
	var guideType = $("#guideType").val();
	if (guideType == "") {
		MU.msgTips("warn", "请输入事项类型", 1200);
		$("#guideType").focus();
		return null;
	}
	
	//	serviceSubject
	var serviceSubject = $("#serviceSubject").val();
	
	//	according
	var according = $("#according").val();
	
	//	precondition
	var precondition = $("#precondition").val();
	
	//	jointDept
	var jointDept = $("#jointDept").val();
	
	//	legalTimeLimit
	var legalTimeLimit = $("#legalTimeLimit").val();
	
	//	promisedTimeLimit
	var promisedTimeLimit = $("#promisedTimeLimit").val();
	
	//	conditions
	var conditions = $("#conditions").val();
	
	//	material
	//var material = $("#material").val();
	
	//	process
	var process = $("#process").val();
	
	//	isCharge
	var ct = $("#isCharge_true").hasClass("checked");
	var cf = $("#isCharge_false").hasClass("checked");
	if (!ct && !cf) {
		MU.msgTips("warn", "请选择是否收费", 1200);
		return null;
	}
	var isCharge = "" + ct;
	
	//	chargeAccording
	var chargeAccording = $("#chargeAccording").val();
	
	//	chargeStandard
	var chargeStandard = $("#chargeStandard").val();
	
	//	address
	var address = $("#address").val();
	
	//	workTime
	var workTime = $("#workTime").val();
	
	//	telephone
	var telephone = $("#telephone").val();
	
	//	complaintTelephone
	var complaintTelephone = $("#complaintTelephone").val();
	
	//	flowImageFileId
	var flowImageFileId = $("#flowImageFileId").val();
	
	//	flowImageFileName
	var flowImageFileName = $("#flowImageFileName").val();
	
	// attachmentData
	var attachmentData = [];
	$.each($(".attachment"), function(i, item) {
		item = $(item);
		attachmentData.push({
			fileId: item.attr("data-file-id"),
			fileName: item.attr("data-file-name"),
			type: "ATTACHMENT"
		});
	});
	attachmentData = JSON.stringify(attachmentData);
	
	return {
		deptCode: deptCode,
		deptName: DEPT_NAMES[deptCode],
		guideName: guideName,
		guideType: guideType,
		serviceSubject: serviceSubject,
		according: according,
		precondition: precondition,
		jointDept: jointDept,
		legalTimeLimit: legalTimeLimit,
		promisedTimeLimit: promisedTimeLimit,
		conditions: conditions,
		material: "",
		process: process,
		isCharge: isCharge,
		chargeAccording: chargeAccording,
		chargeStandard: chargeStandard,
		address: address,
		workTime: workTime,
		telephone: telephone,
		complaintTelephone: complaintTelephone,
		flowImageFileId: flowImageFileId,
		flowImageFileName: flowImageFileName,
		attachmentData: attachmentData,
		attConfigJson: attConfigJson
	};
}

// 提交新增
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

// 提交修改
$("#btnDoModify").on("click", function() {
	var data = checkAddModifyForm();
	if (data == null) {
		return;
	}
	data.guideId = modifyingId;
	
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

// 是否收费单选处理
$(function() {
	$("#isCharge_true").off().on("click", function(e) {
		if (!$(this).hasClass("checked")) {
			$("#isCharge_false").removeClass("checked");
		}
	});
	$("#isCharge_false").off().on("click", function(e) {
		if (!$(this).hasClass("checked")) {
			$("#isCharge_true").removeClass("checked");
		}
	});
});

// 附件上传功能
function onAttachmentUploaded(err,resp) {
    if (err) {
        MU.msgTips("warn", err, 1200);
        return;
    }

    var filename = $("<span>").html(resp.fileName);
    var deleteBtn = $("<a>").html("删除")
    						.attr("href", "javascript:;")
    						.addClass("btn")
    						.css({ "vertical-align": "middle",
	    						   "line-height":    "22px",
	    						   "width":          "28px",
	    						   "height":         "22px",
	    						   "padding":        "0 22px",
	    						   "margin-left":    "8px"      });
    var p = $("<p>")
    	.addClass("attachment")
		.attr("data-file-id", resp.fileId)
		.attr("data-file-name", resp.fileName)
    	.append(filename, " ", deleteBtn)
    	.appendTo("#curAttachments");
    
    deleteBtn.on("click", function() {
    	p.remove();
    });
}

// 申请材料配置
$(function() {
	// 新增
	$("#btnAddAttConfig").on("click", function() {
		newItem("", "");
	});
	
	// 创建一个item
	function newItem(attConfigId, attConfigName) {
		var input = $("<input>")
				.attr("type", "text")
				.attr("maxlength", "100")
				.attr("placeholder", "输入材料名称")
				.attr("data-id", attConfigId)
				.addClass("attConfigItem")
				.val(attConfigName)
				.css({ "width": "400px" });
		var deleteBtn = $("<a>") 
				.css({ "color": "#107aee" })
				.html("删除");
		var div = $("<div>")
				.css({ "margin-bottom": "6px" })
				.append(input).append(" ")
				.append(deleteBtn).append(" ")
				.appendTo($("#attConfigWrapper"));
		
		deleteBtn.on("click", function() {
			div.remove();
		});
	}
	
	// 获取所有item信息
	function findAllAttConfig() {
		// 删除所有空配置
		$(".attConfigItem").each(function() {
			var name = $.trim($(this).val());
			$(this).val(name);
			if (name == "") {
				$(this).parent("div").remove();
			}
		});
		
		// 获取配置信息
		var arr = [];
		$(".attConfigItem").each(function() {
			var name = $(this).val();
			var id = $(this).attr("data-id");
			if (id == "") {
				id = null;
			}
			arr.push({ id:id, name:name });
		});
		return arr;
	}
	window.findAllAttConfig = findAllAttConfig;
	
	// 显示所有配置信息
	function loadAllAttConfig(arr) {
		$("#attConfigWrapper").empty();
		$.each(arr, function(i, item) {
			newItem(item.id, item.name);
		});
	}
	window.loadAllAttConfig = loadAllAttConfig;
});


// 弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$("ul.form").css("height", wh - 160 + "px");
});
$(window).trigger("resize");

// tab切换
$(".switchTab1").on("click", function() {
	$(".tab1").show();
	$(".tab2").hide();
	$(".switchTab1").addClass("on");
	$(".switchTab2").removeClass("on");
});
$(".switchTab2").on("click", function() {
	$(".tab1").hide();
	$(".tab2").show();
	$(".switchTab1").removeClass("on");
	$(".switchTab2").addClass("on");
});