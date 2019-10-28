window.sessionId = null;
window.sessionKey = null;
window.email = null;

// Send Code
$("#btnSendCode").on("click", function() {
	if (!checkEmail()) {
		return;
	}
	
	$.ajax({
		url: "sendCode",
		type: "post",
		dataType: "json",
		data: {
			type: "email",
			email: $("#email").val()
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				$("#email").prop("disabled", true);
				$("#btnSendCode").hide();
				$("#btnSendCodeDone").show();
				window.email = email;
				window.sessionId = resp.data.sessionId;
				window.sessionKey = resp.data.sessionKey;
				MU.msgTips("success", "发送成功");
				$("#code").focus();
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "发送验证码失败，请稍后重试");
		}
	});
});


// submit
$("#btnSubmit").on("click", function() {
	if (!window.sessionId) {
		MU.msgTips("warn", "请先输入邮箱并点击发送验证码");
		$("#email").focus();
		return;
	}
	
	if (!checkCode() || !checkPassword() || !checkPassword2() || !checkImgCode()) {
		return;
	}
	
	$.ajax({
		url: "doResetPassword",
		type: "post",
		dataType: "json",
		data: {
			imgCode: $("#imgCode").val(),
			sessionId: window.sessionId,
			sessionKey: window.sessionKey,
			password: $("#password").val(),
			code: $("#code").val()
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				location.href = "success";
			} else {
				reloadImgCode();
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "重置密码失败，请稍后重试");
		}
	});
});

// checks
function checkEmail() {
	$("#emailError").html("");
	var email = $("#email").val();
	if (email == "") {
		$("#emailError").html("请输入联系邮箱");
		$("#email").focus();
		return false;
	}
	return true;
}

function checkCode() {
	$("#codeError").html("");
	var code = $("#code").val();
	if (code == "") {
		$("#codeError").html("请输入邮箱收到的验证码");
		$("#code").focus();
		return false;
	}
	return true;
}

function checkPassword() {
	$("#passwordError").html("");
	var password = $("#password").val();
	if (password == "") {
		$("#passwordError").html("请输入新密码");
		$("#password").focus();
		return false;
	}
	return true;
}

function checkPassword2() {
	$("#password2Error").html("");
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if (password2 == "") {
		$("#password2Error").html("请再次输入新密码");
		$("#password2").focus();
		return false;
	}
	if (password2 != password) {
		$("#password2Error").html("两次输入的密码不一致");
		$("#password2").focus();
		return false;
	}
	return true;
}

function checkImgCode() {
	$("#imgCodeError").html("");
	var imgCode = $("#imgCode").val();
	if (imgCode == "") {
		$("#imgCodeError").html("请输入图片验证码");
		$("#imgCode").focus();
		return false;
	}
	return true;
}

$("#email").on("blur", checkEmail);
$("#code").on("blur", checkCode);
$("#password").on("blur", checkPassword);
$("#password2").on("blur", checkPassword2);
$("#imgCode").on("blur", checkImgCode);

// refresh code
function reloadImgCode() {
	$("#codeImage").attr("src", "viewCode?_t=" + new Date().getTime());
}
$("#codeImage").on("click", reloadImgCode);

// init
$("#email").focus();
