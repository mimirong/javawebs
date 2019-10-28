var modifyingId = null;

var SERVICE_ROLE_TEXT = {
	"WINDOW"            : "窗口",
	"DEPUTY_DIRECTOR"   : "副局长",
	"DIRECTOR"          : "局长",
	"DEPUTY_CHIEF"      : "副主任",
	"CHIEF"             : "主任",
	"SECRETARY_GENERAL" : "书记"	
};

var PASSWORD_NOT_CHANGED = "#$dkj%$;ds#$"

// Columns
var columns =[
	{
		data: "userId"
	}, {
		data: "loginName"
	}, {
		data: "name"
	}, {
		data: "deptName"
	}, {
		data: "serviceRole",
		render: function(val) {
			var text = SERVICE_ROLE_TEXT[val];
			return (text ? text : val);
		}
	}, {
		data: "mobile"
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
//			if (CommonsPrivileges.hasPrivilegeId("park_account/setEnable")) {
//				if (row.usable) {
//					m.push('<a class="op op-start btnSetDisable" data-row-id="' + row.userId + '" href="javascript:;">停用</a>');
//				} else {
//					m.push('<a class="op op-start btnSetEnable" data-row-id="' + row.userId + '" href="javascript:;">启用</a>');	
//				}
//			}
			if (CommonsPrivileges.hasPrivilegeId("park_account/modify")) {
				m.push('<a class="txt-op op-modify btnModify" data-row-id="' + row.userId + '" href="javascript:;">修改</a>');
			}
			if (CommonsPrivileges.hasPrivilegeId("park_account/delete")) {
				m.push('<a class="op-del txt-op btnDelete" data-row-id="' + row.userId + '" href="javascript:;">删除</a>');
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
});

//新增
$(".btnAdd").on("click", function() {
	$("#loginName").val("");
	$("#password").val("");
	$("#name").val("");
	$("#serviceRole").val("").triggerHandler("change");
	$("#mobile").val("");
	window.setSelectedDeptIds([]);
	
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
					$("#loginName").val(resp.data.loginName);
					$("#password").val(PASSWORD_NOT_CHANGED);
					$("#name").val(resp.data.name);
					$("#serviceRole").val(resp.data.serviceRole).triggerHandler("change");
					$("#mobile").val(resp.data.mobile);
					if (resp.data.deptJson && resp.data.deptJson != "") {
						window.setSelectedDeptIds(JSON.parse(resp.data.deptJson));
					} else {
						window.setSelectedDeptIds([]);
					}
					
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
				MU.msgTips("error", "获取用户信息，请稍后重试", 1200);
			}
		});
	});
});

//检查表单
function checkAddModifyForm() {
	// loginName
	var loginName = $("#loginName").val();
	if (loginName == "") {
		MU.msgTips("warn", "请输入用户名", 1200);
		$("#loginName").focus();
		return null;
	}

	// password
	var password = $("#password").val();
	if (!isModifying() && password == "") {
		MU.msgTips("warn", "请输入密码", 1200);
		$("#password").focus();
		return null;
	}
	if (password == PASSWORD_NOT_CHANGED) {
		password = "";
	}
	
	// name
	var name = $("#name").val();
	if (name == "") {
		MU.msgTips("warn", "请输入真实姓名", 1200);
		$("#name").focus();
		return null;
	}
	
	// deptJson
	var deptIdArr = window.findSelectedDeptIds();
	if (deptIdArr.length == 0) {
		MU.msgTips("warn", "请选择所属部门", 1200);
		return null;
	}
	var deptJson = JSON.stringify(deptIdArr);
	
	// serviceRole
	var serviceRole = $("#serviceRole").val();
	if (serviceRole == "") {
		MU.msgTips("warn", "请选择职位", 1200);
		return null;
	}
	
	// mobile
	var mobile = $("#mobile").val();
	if (mobile == "") {
		MU.msgTips("warn", "请输入联系电话", 1200);
		$("#mobile").focus();
		return null;
	}
	if (!/^\d{6,}$/.test(mobile)) {
		MU.msgTips("warn", "请输入正确的联系电话", 1200);
		$("#mobile").focus();
		return null;
	}
	
	return {
		loginName: loginName,
		password: password,
		name: name,
		deptJson: deptJson,
		serviceRole: serviceRole,
		mobile: mobile
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
	data.userId = modifyingId;
	
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

// 部门多选
$(function() {
	var w = $("#deptSelectWrapper");
	var div = w.find("div.select-input");
	var ul = w.find("ul.select-list");
	
	// 加载部门信息
	ul.empty();
	$.each(GLOBAL_DEPT_CONFIG, function(i, dept) {
		var label = $("<label>").addClass("checkbox").append(dept.name);
		$("<li>").attr("data-value", dept.code).append(label).appendTo(ul);
	});
	
	// 点击div显示和隐藏
	div.on("click", function(e) {
		if (ul.is(":visible")) {
			ul.hide();
		} else {
			div.addClass("focus");
			$('.select-list').hide();
			ul.show().css({
					"z-index": "9",
					"position": "absolute",
					"width": w.width() - 2,
					"top": w.outerHeight() + 2,
					"max-height": "250px"
			});
			e.stopPropagation();
		}
	});
	
	// 去除原有的li点击事件
	ul.find("li").on("click", function(e) {
		e.stopPropagation();
	});
	
	// Checkbox点击
	ul.find(".checkbox").on("click", function(e) {
		var $this = $(this);
		if ($this.hasClass("checked")) {
			$this.removeClass("checked");
		} else {
			$this.addClass("checked");	
		}
		e.stopPropagation();
		
		updateSelectedDeptNames();
	});
	
	// 更新div显示的多个部门名称
	function updateSelectedDeptNames() {
		var text = [];
		$.each(findSelectedDeptIds(), function(i, deptId) {
			var dept = null;
			for (var i = 0; i < GLOBAL_DEPT_CONFIG.length; i++) {
				if (GLOBAL_DEPT_CONFIG[i].code == "" + deptId) {
					dept = GLOBAL_DEPT_CONFIG[i];
					break;
				}
			}
			text.push(dept.name);
		});
		if (text.length == 0) {
			div.html("--请选择--");
		} else {
			div.html(text.join(", "));
		}
	}
	
	// 获取所有选择的值  return Array
	function findSelectedDeptIds() {
		var arr = [];
		$.each(ul.find(".checkbox.checked"), function() {
			var deptId = $(this).parent().attr("data-value");
			arr.push(parseInt(deptId));
		});
		return arr;
	}
	window.findSelectedDeptIds = findSelectedDeptIds;
	
	// 设置选择的值
	function setSelectedDeptIds(arr) {
		$.each(ul.find("li"), function() {
			var deptId = $(this).attr("data-value");
			var checkbox = $(this).find(".checkbox");
			checkbox.removeClass("checked");
			for (var i = 0; i < arr.length; i++) {
				if (arr[i] == deptId) {
					checkbox.addClass("checked");
				}
			}
		});
		updateSelectedDeptNames();
	}
	window.setSelectedDeptIds = setSelectedDeptIds;
});

// 加载部门信息
$(function() {
	$("#deptQuerySelect").html('<option value="">--请选择--</option>');
	$.each(GLOBAL_DEPT_CONFIG, function(i, dept) {
		$("<option>").attr("value", dept.code).append(dept.name).appendTo("#deptQuerySelect");
	});
	$("#deptQuerySelect").selectList();
});

//弹框大小处理
//$(window).on("resize", function() {
//	var wh = $(window).height();
//	$("ul.form").css("height", wh - 160 + "px");
//});
//$(window).trigger("resize");



