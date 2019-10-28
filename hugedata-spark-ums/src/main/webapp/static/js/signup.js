$(".btnSignUp").on("click", function() {
	var data = checkForm();
	if (data == null) {
		return;
	}

	$.ajax({
		url: "doSignUp",
		type: "post",
		dataType: "json",
		data: data,
		success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "注册成功");
				setTimeout(function() {
					location.href = contextPath + "/login";
				}, 1000);
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "注册失败，请稍后重试");
		}
	});
});

function checkForm() {
	var success = true;
	
	// companyName
	var companyName = checkCompanyName();
	if (companyName == null) {
		success = false;
	}
	
	// companyType
	var companyType = checkCompanyType();
	if (companyType == null) {
		success = false;
	}
	
	// companyAddress
	var companyAddress = checkCompanyAddress();
	if (companyAddress == null) {
		success = false;
	}
	
	// organizationCode
	var organizationCode = checkOrganizationCode();
	if (organizationCode == null) {
		success = false;
	}
	
	// licenceCode
	var licenceCode = checkLicenceCode();
	if (licenceCode == null) {
		success = false;
	}
	
	// representative
	var representative = checkRepresentative();
	if (representative == null) {
		success = false;
	}
	
	// representativeId
	var representativeId = checkRepresentativeId();
	if (representativeId == null) {
		success = false;
	}
	
	// contactName
	var contactName = checkContactName();
	if (contactName == null) {
		success = false;
	}
	
	// contactMobile
	var contactMobile = checkContactMobile();
	if (contactMobile == null) {
		success = false;
	}
	
	// contactEmail
	var contactEmail = checkContactEmail();
	if (contactEmail == null) {
		success = false;
	}

	// username
	var username = checkUsername();
	if (username == null) {
		success = false;
	}

	// password
	var password = checkPassword();
	if (password == null) {
		success = false;
	}
	
	// password2
	var password2 = checkPassword2();
	if (password2 == null) {
		success = false;
	}
	
	if (!success) {
		return null;
	}
	
	return {
		companyName: companyName,
		companyType: companyType,
		companyAddress: companyAddress,
		organizationCode: organizationCode,
		licenceCode: licenceCode,
		representative: representative,
		representativeId: representativeId,
		contactName: contactName,
		contactMobile: contactMobile,
		contactEmail: contactEmail,
		username: username,
		password: password
	};
}

// checkCompanyName
function checkCompanyName() {
	var companyName = $("#companyName").val();
	if (companyName == "") {
		$("#companyName_error").html("请输入单位名称");
		return null;
	}
	$("#companyName_error").html("");
	return companyName;
}

// checkCompanyType
function checkCompanyType() {
	var companyType = $("#companyType").val();
	if (companyType == "") {
		$("#companyType_error").html("请选择单位类型");
		return null;
	}
	$("#companyType_error").html("");
	return companyType;
}

// checkCompanyAddress
function checkCompanyAddress() {
	var companyAddress = $("#companyAddress").val();
	if (companyAddress == "") {
		$("#companyAddress_error").html("请输入单位地址");
		return null;
	}
	$("#companyAddress_error").html("");
	return companyAddress;
}

// checkOrganizationCode
function checkOrganizationCode() {
	var organizationCode = $("#organizationCode").val();
	if (organizationCode == "") {
		$("#organizationCode_error").html("请输入组织机构代码");
		return null;
	}
	$("#organizationCode_error").html("");
	return organizationCode;
}

// checkLicenceCode
function checkLicenceCode() {
	var licenceCode = $("#licenceCode").val();
	if (licenceCode == "") {
		$("#licenceCode_error").html("请输入营业执照号码");
		return null;
	}
	$("#licenceCode_error").html("");
	return licenceCode;
}

// checkRepresentative
function checkRepresentative() {
	var representative = $("#representative").val();
	if (representative == "") {
		$("#representative_error").html("请输入法人代表");
		return null;
	}
	$("#representative_error").html("");
	return representative;
}

// checkRepresentativeId
function checkRepresentativeId() {
	var representativeId = $("#representativeId").val();
	if (representativeId == "") {
		$("#representativeId_error").html("请输入法人代表身份证号码");
		return null;
	}
	if (!/^\d{18}$/.test(representativeId)) {
		$("#representativeId_error").html("法人代表身份证号码不正确");
		return null;
	}
	$("#representativeId_error").html("");
	return representativeId;
}

// checkContactName
function checkContactName() {
	var contactName = $("#contactName").val();
	if (contactName == "") {
		$("#contactName_error").html("请输入联系人");
		return null;
	}
	$("#contactName_error").html("");
	return contactName;
}

// checkContactMobile
function checkContactMobile() {
	var contactMobile = $("#contactMobile").val();
	if (contactMobile == "") {
		$("#contactMobile_error").html("请输入联系人电话");
		return null;
	}
	if (!/^\d{6,}$/.test(contactMobile)) {
		$("#contactMobile_error").html("联系人电话不正确");
		return null;
	}
	$("#contactMobile_error").html("");
	return contactMobile;
}

// checkContactEmail
function checkContactEmail() {
	var contactEmail = $("#contactEmail").val();
	if (contactEmail == "") {
		$("#contactEmail_error").html("请输入联系人邮箱");
		return null;
	}
	$("#contactEmail_error").html("");
	return contactEmail;
}

// checkPassword
function checkPassword() {
	var password = $("#password").val();
	if (password == "") {
		$("#password_error").html("请输入密码");
		return null;
	}
	if (password.length < 6) {
		$("#password_error").html("密码不能少于6位");
		return null;
	}
	$("#password_error").html("");
	return password;
}

// checkPassword2
function checkPassword2() {
	var password2 = $("#password2").val();
	if (password2 != $("#password").val()) {
		$("#password2_error").html("两次输入的密码不一致");
		return null;
	}
	$("#password2_error").html("");
	return password2;
}

// checkUsername
function checkUsername() {
	var username = $("#username").val();
	if (username == "") {
		$("#username_error").html("请输入用户名");
		return null;
	}
	$("#username_error").html("");
	return username;
}

$("#companyName").on("blur", checkCompanyName);
$("#companyAddress").on("blur", checkCompanyAddress);
$("#organizationCode").on("blur", checkOrganizationCode);
$("#licenceCode").on("blur", checkLicenceCode);
$("#representative").on("blur", checkRepresentative);
$("#representativeId").on("blur", checkRepresentativeId);
$("#contactName").on("blur", checkContactName);
$("#contactMobile").on("blur", checkContactMobile);
$("#contactEmail").on("blur", checkContactEmail);
$("#password").on("blur", checkPassword);
$("#password2").on("blur", checkPassword2);
$("#username").on("blur", checkUsername);

$(function () {
    $("#companyTypeSuper ul li").on("click", function () {
        checkCompanyType();
    });
});

