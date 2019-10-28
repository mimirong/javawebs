var MAX_IMAGES = 2;
var MAX_ATTACHMENTS = 2;

var modifyingId = null;
var uploadImageData = [];
var uploadAttachmentData = [];

$("#contentEditorModify").ckeditor();

// Columns
var columns =[
	{
		data: null,
		render: function(row) {
			return '<label class="checkbox btnSelect" data-row-id="' + row.articleId + '"></label>';
		}
	}, {
		data: "articleId"
	}, {
		data: "categoryId",
		render: function(val) {
			switch (val) {
				case "ECOMM_BIDDINGS": 	return "招标公告";
				default:				return val;
			}
		}
	}, {
		data: "title"
	}, {
		data: "isVisible",
		render: function(val) {
			if (val) {
				return '<span class="green">是</span>';
			} else {
				return '<span class="red">否</span>';
			}
		}
	}, {
		data: "createTime",
		render: function(val) {
			return DateFormat.format.date(val, "yyyy-MM-dd HH:mm:ss");
		}
	}, {
		data: "updateTime",
		render: function(val) {
			return DateFormat.format.date(val, "yyyy-MM-dd HH:mm:ss");
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("ecomm/biddings/setVisible")) {
				if (row.isVisible) {
					m.push('<a class="txt-op btnHideArticle" data-row-id="' + row.articleId + '" href="javascript:;">隐藏</a>');
				} else {
					m.push('<a class="txt-op btnShowArticle" data-row-id="' + row.articleId + '" href="javascript:;">显示</a>');
				}
			}
			if (CommonsPrivileges.hasPrivilegeId("ecomm/biddings/modify")) {
				m.push('<a class="txt-op btnModify" data-row-id="' + row.articleId + '" href="javascript:;">修改</a>');
			}
			if (CommonsPrivileges.hasPrivilegeId("ecomm/biddings/delete")) {
				m.push('<a class="txt-op btnDelete" data-row-id="' + row.articleId + '" href="javascript:;">删除</a>');
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
	// 设置可见
	$(".btnShowArticle").on("click", function() {
		var articleId = $(this).attr("data-row-id");
		$.ajax({
			url: "setArticleVisible",
			type: "post",
			dataType: "json",
			data: {
				articleId: articleId,
				visible: true
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					dt.ajax.reload();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "设置可见失败，请稍后重试");
			}
		});
	});
	
	// 设置隐藏
	$(".btnHideArticle").on("click", function() {
		var articleId = $(this).attr("data-row-id");
		$.ajax({
			url: "setArticleVisible",
			type: "post",
			dataType: "json",
			data: {
				articleId: articleId,
				visible: false
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					dt.ajax.reload();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "设置隐藏失败，请稍后重试");
			}
		});
	});
});

//新增
$(".btnAdd").on("click", function() {
	$("#title").val("");
	$("#brief").val("");
	$("#author").val("");
	$("#source").val("");
	$("#publishTimeStr").val(DateFormat.format.date(new Date(), "yyyy-MM-dd"));
    CKEDITOR.instances["contentEditorModify"].setData("");
    
    uploadImageData = [];
    loadUploadedImages();
    FileUpload.reset("#uploadImageWrapper");
    
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
					$("#title").val(resp.data.title);
					$("#brief").val(resp.data.brief);
					$("#author").val(resp.data.author);
					$("#source").val(resp.data.source);
					$("#publishTimeStr").val(DateFormat.format.date(resp.data.publishTime, "yyyy-MM-dd"));
                    CKEDITOR.instances["contentEditorModify"].setData(resp.data.content);

                    uploadImageData = JSON.parse(resp.data.uploadImageData);
                    loadUploadedImages();
                    FileUpload.reset("#uploadImageWrapper");

                    uploadAttachmentData = JSON.parse(resp.data.uploadAttachmentData);
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
				MU.msgTips("error", "获取文章信息失败，请稍后重试", 1200);
			}
		});
	});
});

//检查表单
function checkAddModifyForm() {
	// title
	var title = $("#title").val();
	if (title == "") {
		MU.msgTips("warn", "请输入文章标题", 1200);
		$("#title").focus();
		return null;
	}
	
	// categoryId
	console.log($("#categoryId"));
	var categoryId = $("#categoryId").val();
	if (categoryId == "") {
		MU.msgTips("warn", "请选择栏目", 1200);
		return null;
	}

	// brief
	var brief = $("#brief").val();
	
	// author
	var author = $("#author").val();

	// source
	var source = $("#source").val();

	// publishTimeStr
	var publishTimeStr = $("#publishTimeStr").val();
	if (publishTimeStr == "") {
		MU.msgTips("warn", "请选择发布日期", 1200);
		$("#publishTimeStr").focus();
		return null;
	}

    //content
    var content = CKEDITOR.instances["contentEditorModify"].getData();
    $("#content").val(content);
    if (content == "") {
        MU.msgTips("warn", "请输入内容", 1200);
        $("#content").focus();
        return null;
    }
	
	return {
		title: title,
		categoryId: categoryId,
		brief: brief,
		author: author,
		source: source,
		publishTimeStr: publishTimeStr,
		content: content,
		uploadImageData: JSON.stringify(uploadImageData),
		uploadAttachmentData: JSON.stringify(uploadAttachmentData)
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
	data.articleId = modifyingId;
	
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
	console.log(wh - 160);
});
$(window).trigger("resize");


//==============================================================================
// 上传图片处理
//==============================================================================

window.onImageUploaded = function(err, resp) {
	if (err) {
		MU.msgTips("error", err);
		return;
	} 
	if (resp.fileId && resp.fileName) {
		uploadImageData.push({
			fileId: resp.fileId,
			fileName: resp.fileName
		});
		loadUploadedImages();
	}
};

function loadUploadedImages() {
	$("#uploadedImages").empty();
	$.each(uploadImageData, function(i, item) {
		var wrapper = $("<p>").appendTo($("#uploadedImages"));
		$("<span>").html(item.fileName).appendTo(wrapper);
		$("<span>").html("  ").appendTo(wrapper);
		$("<a>").attr("href", "javascript:;")
				.html("删除")
				.css("color", "#107aee")
				.on("click", function() {
					for (var i = 0; i < uploadImageData.length; i++) {
						if (uploadImageData[i].fileId == item.fileId) {
							uploadImageData.splice(i, 1);
						}
					}
					loadUploadedImages();
				})
				.appendTo(wrapper);
	});
	
	if (uploadImageData.length >= MAX_IMAGES) {
		$("#uploadImageWrapper").hide();
	} else {
		$("#uploadImageWrapper").show();
	}
}

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


