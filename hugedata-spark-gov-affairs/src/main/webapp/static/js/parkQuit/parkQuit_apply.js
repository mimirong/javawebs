var TELEPHONE_REGEX = /^[\d\(\)\-\+]{6,30}$/;

$("#quitDate").datepicker();
console.log($("#quitDate"));

// Reset
$("#btnReset").on("click", function() {
	$("#companyName").val("");
	$("#representative").val("");
	$("#contact").val("");
	$("#quitDate").val("");
});

// Submit
$("#btnSubmit").on("click", function() {
	var data = checkForm();
	if (data == null) {
		return;
	}
	
	$.ajax({
		url: "submitApply",
		type: "post",
		dataType: "json",
		data: {
			data: JSON.stringify(data)
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交退园申请成功");
				setTimeout(function() {
					location.href = contextPath + "/parkQuit/apply";
				}, 1000);
				
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "提交退园申请失败，请稍后重试");
		}
	});
});

// checkForm
function checkForm() {
	//companyName
	var companyName = $("#companyName").val();
	if (!companyName || companyName == "") {
		MU.msgTips("warn", "请输入企业名称");
		$("#companyName").focus();
		return;
	}
	
	//representative
	var representative = $("#representative").val();
	if (!representative || representative == "") {
		MU.msgTips("warn", "请输入法人代表");
		$("#representative").focus();
		return;
	}
	
	//contact
	var contact = $("#contact").val();
	if (!contact || contact == "") {
		MU.msgTips("warn", "请输入联系方式");
		$("#contact").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(contact)) {
		MU.msgTips("warn", "请输入正确联系方式");
		$("#contact").focus();
		return;
	}
	
	//quitDate
	var quitDate = $("#quitDate").val();
	if (!quitDate || quitDate == "") {
		MU.msgTips("warn", "请选择退园日期");
		$("#quitDate").focus();
		return;
	}
	
	return {
		companyName: companyName,
		representative: representative,
		contact: contact,
		quitDate: quitDate 
	};
}