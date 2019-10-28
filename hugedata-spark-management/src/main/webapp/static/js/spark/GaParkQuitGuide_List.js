var MAX_ATTACHMENTS = 1;

var modifyingId = null;
var uploadAttachmentData = [];

$("#contentEditorModify").ckeditor();

// Columns
var columns =[
	{
		data: "title"
	}, {
		data: "createTime",
		render: function(val) {
			return DateFormat.format.date(val, "yyyy-MM-dd HH:mm:ss");
		}
	}, {
		data: null,
		render: function(row) {
			if (row.attachmentFileId && row.attachmentFileId != "") {
				var url = DownloadService.buildUrl(row.attachmentFileId, row.attachmentFileName);
				return '<a href="javascript:;" onclick="window.open(\'' + url + '\')" class="download">' + row.attachmentFileName + '</a>';
			} else {
				return "";
			}
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("quitguide/modify")) {
				m.push('<a class="txt-op btnModify" data-row-id="' + row.guideId + '" href="javascript:;">修改</a>');
			}
			if (CommonsPrivileges.hasPrivilegeId("quitguide/delete")) {
				m.push('<a class="txt-op btnDelete" data-row-id="' + row.guideId + '" href="javascript:;">删除</a>');
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
});

//新增
$(".btnAdd").on("click", function() {
	$("#title").val("");
	$("#creatorDept").val("");
	$("#creatorName").val("");
	$("#publishTimeStr").val(DateFormat.format.date(new Date(), "yyyy-MM-dd"));
    CKEDITOR.instances["contentEditorModify"].setData("");
    
    uploadAttachmentData = [];
    loadUploadedAttachments();
    FileUpload.reset("#uploadAttachmentWrapper");
	
	modifyingId = null;
	
	$(".addPanelTitle").html("添加");
	$("#btnDoAdd").show();
	$("#btnDoModify").hide();
	MU.mask();
	MU.center("#addPanel");
	$("#addPanel").show();
	
	setTimeout(function() {
		$("#addPanel ul.form").scrollTop(0);
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
					$("#title").val(resp.data.title);
					$("#creatorDept").val(resp.data.creatorDept);
					$("#creatorName").val(resp.data.creatorName);
					$("#publishTimeStr").val(DateFormat.format.date(resp.data.publishTime, "yyyy-MM-dd"));
                    CKEDITOR.instances["contentEditorModify"].setData(resp.data.content);

                    if (resp.data.attachmentFileId && resp.data.attachmentFileId != "") {
                        uploadAttachmentData = [{
                        	fileId: resp.data.attachmentFileId,
                        	fileName: resp.data.attachmentFileName
                        }];	
                    } else {
                    	uploadAttachmentData = [];
                    }
                    loadUploadedAttachments();
                    FileUpload.reset("#uploadAttachmentWrapper");
                    
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
				MU.msgTips("error", "获取退园指南信息失败，请稍后重试", 1200);
			}
		});
	});
});

//检查表单
function checkAddModifyForm() {
	// title
	var title = $("#title").val();
	if (title == "") {
		MU.msgTips("warn", "请输入退园指南名称", 1200);
		$("#title").focus();
		return null;
	}
	
	// creatorDept
	var creatorDept = $("#creatorDept").val();
	if (creatorDept == "") {
		MU.msgTips("warn", "请输入发布部门", 1200);
		$("#creatorDept").focus();
		return null;
	}

	// creatorName
	var creatorName = $("#creatorName").val();
	if (creatorName == "") {
		MU.msgTips("warn", "请输入发布人", 1200);
		$("#creatorName").focus();
		return null;
	}

	// publishTimeStr
	var publishTimeStr = $("#publishTimeStr").val();
	if (publishTimeStr == "") {
		MU.msgTips("warn", "请选择发布日期", 1200);
		$("#publishTimeStr").focus();
		return null;
	}

    // content
    var content = CKEDITOR.instances["contentEditorModify"].getData();
    $("#content").val(content);
    if (content == "") {
        MU.msgTips("warn", "请输入内容", 1200);
        $("#content").focus();
        return null;
    }
    
    // attachment
    var attachmentFileId = null, 
    	attachmentFileName = null;
    if (uploadAttachmentData && uploadAttachmentData.length > 0) {
    	attachmentFileId = uploadAttachmentData[0].fileId;
    	attachmentFileName = uploadAttachmentData[0].fileName;
    }
	
	return {
		title: title,
		creatorDept: creatorDept,
		creatorName: creatorName,
		publishTimeStr: publishTimeStr,
		content: content,
		attachmentFileId: attachmentFileId,
		attachmentFileName: attachmentFileName
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

// 判断是否正在修改
function isModifying() {
	return modifyingId != null;
}

// 弹框大小处理
$(window).on("resize", function() {
	var wh = $(window).height();
	$("ul.form").css("height", wh - 190 + "px");
});
$(window).trigger("resize");


//==============================================================================
//上传附件处理
//==============================================================================

window.onAttachmentUploaded = function(err, resp) {
	if (err) {
		MU.msgTips("error", err);
		return;
	} 
	if (resp.fileId && resp.fileName) {
		uploadAttachmentData.push({
			fileId: resp.fileId,
			fileName: resp.fileName
		});
		loadUploadedAttachments();
	}
};

function loadUploadedAttachments() {
	$("#uploadedAttachments").empty();
	$.each(uploadAttachmentData, function(i, item) {
		var wrapper = $("<p>").appendTo($("#uploadedAttachments"));
		$("<span>").html(item.fileName).appendTo(wrapper);
		$("<span>").html("  ").appendTo(wrapper);
		$("<a>").attr("href", "javascript:;")
				.html("删除")
				.css("color", "#107aee")
				.on("click", function() {
					for (var i = 0; i < uploadAttachmentData.length; i++) {
						if (uploadAttachmentData[i].fileId == item.fileId) {
							uploadAttachmentData.splice(i, 1);
						}
					}
					loadUploadedAttachments();
				})
				.appendTo(wrapper);
	});
	
	if (uploadAttachmentData.length >= MAX_ATTACHMENTS) {
		$("#uploadAttachmentWrapper").hide();
	} else {
		$("#uploadAttachmentWrapper").show();
	}
}


