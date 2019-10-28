
var TELEPHONE_REGEX = /^[\d\(\)\-\+]{6,30}$/;
var EMAIL_REGEX = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

// Reset
$("#btnReset").on("click", function() {
	$("#companyName").val("");
	$("#representative").val("");
	$("#contact").val("");
	$("#contactMobile").val("");
	$("#telephone").val("");
	$("#regCapDomestic").val("");
	$("#regCapForeign").val("");
	$("#regAddress").val("");
	$("#businessScope").val("");
	$("#companyContactName").val("");
	$("#companyContactTel").val("");
	$("#companyContactEmail").val("");
	$("#platformContactName").val("");
	$("#platformContactTel").val("");
	$("#platformContactEmail").val("");
	$("#investContactName").val("");
	$("#investContactTel").val("");
	$("#investContactEmail").val("");
	$("#companyRemark").val("");
	$("#investRemark").val("");
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
				MU.msgTips("success", "提交入园申请成功");
				setTimeout(function() {
					location.href = contextPath + "/parkJoin/apply";
				}, 1000);
				
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "提交入园申请失败，请稍后重试");
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
	
	//contactMobile
	var contactMobile = $("#contactMobile").val();
	if (!contactMobile || contactMobile == "") {
		MU.msgTips("warn", "请输入手机");
		$("#contactMobile").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(contactMobile)) {
		MU.msgTips("warn", "请输入正确的手机");
		$("#contactMobile").focus();
		return;
	}
	
	//telephone
	var telephone = $("#telephone").val();
	if (!telephone || telephone == "") {
		MU.msgTips("warn", "请输入固定电话");
		$("#telephone").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(telephone)) {
		MU.msgTips("warn", "请输入正确的固定电话");
		$("#telephone").focus();
		return;
	}
	
	//regCapDomestic
	var regCapDomestic = $("#regCapDomestic").val();
	if (!regCapDomestic || regCapDomestic == "") {
		MU.msgTips("warn", "请输入注册资金内资");
		$("#regCapDomestic").focus();
		return;
	}
	if (isNaN(parseFloat(regCapDomestic))) {
		MU.msgTips("warn", "注册资金必须为数值");
		$("#regCapDomestic").focus();
		return;
	}
	
	//regCapForeign
	var regCapForeign = $("#regCapForeign").val();
	if (!regCapForeign || regCapForeign == "") {
		MU.msgTips("warn", "请输入注册资金外资");
		$("#regCapForeign").focus();
		return;
	}
	if (isNaN(parseFloat(regCapForeign))) {
		MU.msgTips("warn", "注册资金必须为数值");
		$("#regCapForeign").focus();
		return;
	}
	
	//regAddress
	var regAddress = $("#regAddress").val();
	if (!regAddress || regAddress == "") {
		MU.msgTips("warn", "请输入注册地址");
		$("#regAddress").focus();
		return;
	}
	
	//businessScope
	var businessScope = $("#businessScope").val();
	if (!businessScope || businessScope == "") {
		MU.msgTips("warn", "请输入经营范围");
		$("#businessScope").focus();
		return;
	}
	
	//companyContactName
	var companyContactName = $("#companyContactName").val();
	if (!companyContactName || companyContactName == "") {
		MU.msgTips("warn", "请输入入驻企业联系人");
		$("#companyContactName").focus();
		return;
	}
	
	//companyContactTel
	var companyContactTel = $("#companyContactTel").val();
	if (!companyContactTel || companyContactTel == "") {
		MU.msgTips("warn", "请输入入驻企业联系电话");
		$("#companyContactTel").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(companyContactTel)) {
		MU.msgTips("warn", "请输入正确的入驻企业联系电话");
		$("#companyContactTel").focus();
		return;
	}
	
	//companyContactEmail
	var companyContactEmail = $("#companyContactEmail").val();
	if (!companyContactEmail || companyContactEmail == "") {
		MU.msgTips("warn", "请输入入驻企业邮箱");
		$("#companyContactEmail").focus();
		return;
	}
	if (!EMAIL_REGEX.test(companyContactEmail)) {
		MU.msgTips("warn", "请输入正确的入驻企业邮箱");
		$("#companyContactEmail").focus();
		return;
	}

	//platformContactName
	var platformContactName = $("#platformContactName").val();
	if (!platformContactName || platformContactName == "") {
		MU.msgTips("warn", "请输入创业平台联系人");
		$("#platformContactName").focus();
		return;
	}
	
	//platformContactTel
	var platformContactTel = $("#platformContactTel").val();
	if (!platformContactTel || platformContactTel == "") {
		MU.msgTips("warn", "请输入创业平台联系电话");
		$("#platformContactTel").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(platformContactTel)) {
		MU.msgTips("warn", "请输入正确的创业平台联系电话");
		$("#platformContactTel").focus();
		return;
	}
	
	//platformContactEmail
	var platformContactEmail = $("#platformContactEmail").val();
	if (!platformContactEmail || platformContactEmail == "") {
		MU.msgTips("warn", "请输入创业平台邮箱");
		$("#platformContactEmail").focus();
		return;
	}
	if (!EMAIL_REGEX.test(platformContactEmail)) {
		MU.msgTips("warn", "请输入正确的创业平台邮箱");
		$("#platformContactEmail").focus();
		return;
	}
	
	//investContactName
	var investContactName = $("#investContactName").val();
	if (!investContactName || investContactName == "") {
		MU.msgTips("warn", "请输入招商合作局项目联系人");
		$("#investContactName").focus();
		return;
	}
	
	//investContactTel
	var investContactTel = $("#investContactTel").val();
	if (!investContactTel || investContactTel == "") {
		MU.msgTips("warn", "请输入招商合作局项目联系电话");
		$("#investContactTel").focus();
		return;
	}
	if (!TELEPHONE_REGEX.test(investContactTel)) {
		MU.msgTips("warn", "请输入正确的招商合作局项目联系电话");
		$("#investContactTel").focus();
		return;
	}
	
	//investContactEmail
	var investContactEmail = $("#investContactEmail").val();
	if (!investContactEmail || investContactEmail == "") {
		MU.msgTips("warn", "请输入招商合作局项目邮箱");
		$("#investContactEmail").focus();
		return;
	}
	if (!EMAIL_REGEX.test(investContactEmail)) {
		MU.msgTips("warn", "请输入正确的招商合作局项目邮箱");
		$("#investContactEmail").focus();
		return;
	}
	
	//companyRemark
	var companyRemark = $("#companyRemark").val();
	if (!companyRemark || companyRemark == "") {
		MU.msgTips("warn", "请输入入驻方意见");
		$("#companyRemark").focus();
		return;
	}
	
	//investRemark
	var investRemark = $("#investRemark").val();
	if (!investRemark || investRemark == "") {
		MU.msgTips("warn", "请输入招商合作局意见");
		$("#investRemark").focus();
		return;
	}
	
	return {
		companyName: companyName,
		representative: representative,
		contact: contact,
		contactMobile: contactMobile,
		telephone: telephone,
		regCapDomestic: regCapDomestic,
		regCapForeign: regCapForeign,
		regAddress: regAddress,
		businessScope: businessScope,
		companyContactName: companyContactName,
		companyContactTel: companyContactTel,
		companyContactEmail: companyContactEmail,
		platformContactName: platformContactName,
		platformContactTel: platformContactTel,
		platformContactEmail: platformContactEmail,
		investContactName: investContactName,
		investContactTel: investContactTel,
		investContactEmail: investContactEmail,
		companyRemark: companyRemark,
		investRemark: investRemark 
	};
}