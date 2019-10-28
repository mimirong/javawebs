var savedLoginName = $.cookie("savedLoginNameManagement");
if (savedLoginName && savedLoginName != "") {
	$("#username").val(savedLoginName);
    $("#chkSaveLoginName").addClass("checked");
}

// 普通用户/管理员 切换处理
window.loginUserType = "park";
$("#userTypePark").on("click", function() {
	window.loginUserType = "park";
	$("#username").focus();
});
$("#userTypeAdmin").on("click", function() {
	window.loginUserType = "system";
	$("#username").focus();
});

// 登录
function doLogin() {
	// username
	var username = $("#username").val();
	if (username == "") {
		MU.msgTips("warn", "请输入用户名");
		$("#username").focus();
		return;
	}
	
	// password
	var password = $("#password").val();
	if (password == "") {
		MU.msgTips("warn", "请输入密码");
		$("#password").focus();
		return;
	}
	
	// imgCode
	var imgCode = $("#imgCode").val();
	if (imgCode == "") {
		MU.msgTips("warn", "请输入验证码");
		$("#imgCode").focus();
		return;
	}
	
	$("#submitLoginInProg").show();
	$("#submitLogin").hide();
	
	$.ajax({
		url: contextPath + "/login/doLogin",
		type: "post",
		dataType: "json",
		data: {
			username: username,
			password: password,
			imgCode: imgCode,
			userType: window.loginUserType
		},
		success: function(data){
			if(data && data.result == 0){
				MU.msgTips('success','登录成功',1200);
				setTimeout(function(){
					var url = $("#redirectUrl").val();
					var userType = data.data.user.userType;
					
					if (userType == "SYSTEM" || userType == "PARK") {
						url = managementUrl;
					} else if (userType == "COMPANY") {
						if (url.indexOf(govAffairsPortalUrl) != 0) {
							url = govAffairsPortalUrl;
						}
					}
					
					if (url.indexOf("?") >= 0) {
						url += "&";
					} else {
						url += "?";
					}
					url += "login=" + data.data.token;
					
					var rememberUsername = $("label.checkbox").hasClass("checked");
					if (rememberUsername) {
						$.cookie("savedLoginNameManagement", username, { expires: 365 });
					} else {
						$.cookie("savedLoginNameManagement", "");
					}
					
					window.location.href = url;
				}, 1000);
			}else{
				$("#submitLoginInProg").hide();
				$("#submitLogin").show();
				reloadImagCode();
				MU.msgTips('error', data.message);
			}
		},
		error: function() {
			$("#submitLoginInProg").hide();
			$("#submitLogin").show();
			reloadImagCode();
			MU.msgTips("error", "登录失败，请稍后重试");
		}
	});
}

function reloadImagCode() {
	$("#imgCode").val("");
	$(".imgCodeUpdate img").attr("src", contextPath + "/login/viewLoginCode?t=" + new Date().getTime());
}

$("#username").on("keypress", function(e) {
	if (e.keyCode == 13) {
		$("#password").focus();
	}
});

$("#password").on("keypress", function(e) {
	if (e.keyCode == 13) {
		$("#imgCode").focus();
	}
});

$("#imgCode").on("keypress", function(e) {
	if (e.keyCode == 13) {
		doLogin();
	}
});

$("#submitLogin").on("click", doLogin);

$("#password").val("");
$("#imgCode").val("");

$(".imgCodeUpdate").on("click", reloadImagCode);

$("#username").focus();