
$("#btnUpdate").on("click", function() {
	var unitPricePerGBMonth = $("#unitPricePerGBMonth").val();
	if (unitPricePerGBMonth == "") {
		MU.msgTips("warn", "请输入价格");
		$("#unitPricePerGBMonth").focus();
		return;
	}
	
	unitPricePerGBMonth = parseFloat(unitPricePerGBMonth);
	if (isNaN(unitPricePerGBMonth)) {
		MU.msgTips("warn", "价格必须为数值");
		$("#unitPricePerGBMonth").focus();
		return;
	}
	
	$.ajax({
		url: "update",
		type: "post",
		dataType: "json",
		data: {
			unitPricePerGBMonth: parseInt(unitPricePerGBMonth * 100)
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "更新成功");
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "更新失败，请稍后重试");
		}
	});
});

$("#btnCancel").on("click", function() {
	location.href = "?_t" + new Date().getTime();
});