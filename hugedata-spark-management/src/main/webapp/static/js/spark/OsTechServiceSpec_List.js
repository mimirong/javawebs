var modifyingId = null;

// Columns
var columns =[
	{
		data: "specId"
	}, {
		data: "specName"
	}, {
		data: null,
		render: function(row) {
			if (!row.specs) {
				row.specs = JSON.parse(row.specData);
			}
			return row.specs.cpu;
		}
	}, {
		data: null,
		render: function(row) {
			if (!row.specs) {
				row.specs = JSON.parse(row.specData);
			}
			return row.specs.memory;
		}
	}, {
		data: null,
		render: function(row) {
			if (!row.specs) {
				row.specs = JSON.parse(row.specData);
			}
			return row.specs.disk;
		}
	}, {
		data: null,
		render: function(row) {
			if (!row.specs) {
				row.specs = JSON.parse(row.specData);
			}
			return row.specs.bandwidth;
		}
	}, {
		data: "price",
		render: function(val) {
			return val + "元/月";
		}
	}, {
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("techserv/computing_specs/modify")) {
				m.push('<a class="txt-op btnModify" data-row-id="' + row.specId + '" href="javascript:;">修改</a>');
			}
			if (CommonsPrivileges.hasPrivilegeId("techserv/computing_specs/delete")) {
				m.push('<a class="txt-op btnDelete" data-row-id="' + row.specId + '" href="javascript:;">删除</a>');
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

//新增
$(".btnAdd").on("click", function() {
	if (dt.data().length >= 3) {
		MU.msgTips("error", "最多只允许添加3个云主机套餐");
		return;
	}
	
	$("#specName").val("");
	$("#specDataCpu").val("").triggerHandler("change");
	$("#specDataMemory").val("").triggerHandler("change");
	$("#specDataDisk").val("").triggerHandler("change");
	$("#specDataBandwidth").val("").triggerHandler("change");
	$("#price").val("");
	
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
					var specData = JSON.parse(resp.data.specData);
					specData.cpu = specData.cpu.replace(/核/g, "");
					specData.memory = specData.memory.replace(/[KMGBkmbg]/g, "");
					specData.disk = specData.disk.replace(/[KMGBkmbg]/g, "");
					specData.bandwidth = specData.bandwidth.replace(/[KMGBkmbg]/g, "");
					
					$("#specName").val(resp.data.specName);
					$("#specDataCpu").val(specData.cpu).triggerHandler("change");
					$("#specDataMemory").val(specData.memory).triggerHandler("change");
					$("#specDataDisk").val(specData.disk).triggerHandler("change");
					$("#specDataBandwidth").val(specData.bandwidth).triggerHandler("change");
					$("#price").val(resp.data.price);

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
	// specName
	var specName = $("#specName").val();
	if (specName == "") {
		MU.msgTips("warn", "请输入套餐名称", 1200);
		$("#specName").focus();
		return null;
	}

	// specDataCpu
	var specDataCpu = $("#specDataCpu").val();
	if (specDataCpu == "") {
		MU.msgTips("warn", "请输入套餐CPU", 1200);
		$("#specDataCpu").focus();
		return null;
	}
	if (isNaN(parseInt(specDataCpu))) {
		MU.msgTips("warn", "套餐CPU必须为数值", 1200);
		$("#specDataCpu").focus();
		return null;
	}

	// specDataMemory
	var specDataMemory = $("#specDataMemory").val();
	if (specDataMemory == "") {
		MU.msgTips("warn", "请输入套餐内存", 1200);
		$("#specDataMemory").focus();
		return null;
	}
	if (isNaN(parseInt(specDataMemory))) {
		MU.msgTips("warn", "套餐内存必须为数值", 1200);
		$("#specDataMemory").focus();
		return null;
	}

	// specDataDisk
	var specDataDisk = $("#specDataDisk").val();
	if (specDataDisk == "") {
		MU.msgTips("warn", "请输入套餐硬盘", 1200);
		$("#specDataDisk").focus();
		return null;
	}
	if (isNaN(parseInt(specDataDisk))) {
		MU.msgTips("warn", "套餐硬盘必须为数值", 1200);
		$("#specDataDisk").focus();
		return null;
	}

	// specDataBandwidth
	var specDataBandwidth = $("#specDataBandwidth").val();
	if (specDataBandwidth == "") {
		MU.msgTips("warn", "请输入套餐带宽", 1200);
		$("#specDataBandwidth").focus();
		return null;
	}
	if (isNaN(parseInt(specDataBandwidth))) {
		MU.msgTips("warn", "套餐带宽必须为数值", 1200);
		$("#specDataBandwidth").focus();
		return null;
	}

	// price
	var price = $("#price").val();
	if (price == "") {
		MU.msgTips("warn", "请输入价格", 1200);
		$("#price").focus();
		return null;
	}
	if (isNaN(parseInt(price))) {
		MU.msgTips("warn", "价格必须为数值", 1200);
		$("#price").focus();
		return null;
	}
	
	return {
		specName: specName,
		price: price,
		specData: JSON.stringify({
			cpu: specDataCpu + "核",
			memory: specDataMemory + "G",
			disk: specDataDisk + "G",
			bandwidth: specDataBandwidth + "M"
		}),
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
	data.specId = modifyingId;
	
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
//$(window).on("resize", function() {
//	var wh = $(window).height();
//	$("ul.form").css("height", wh - 190 + "px");
//	console.log(wh - 160);
//});
//$(window).trigger("resize");

