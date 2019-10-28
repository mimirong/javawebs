var savedLoginName = $.cookie("savedLoginName");
if (savedLoginName && savedLoginName != "") {
	$("#username").val(savedLoginName);
    $("#chkSaveLoginName").addClass("checked");
}

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
			imgCode: imgCode
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
						$.cookie("savedLoginName", username, { expires: 365 });
					} else {
						$.cookie("savedLoginName", "");
					}
					
					window.location.href = url;
				}, 1000);
			}else{
				$("#submitLoginInProg").hide();
				$("#submitLogin").show();
				MU.msgTips('error', data.message);
			}
		},
		error: function() {
			$("#submitLoginInProg").hide();
			$("#submitLogin").show();
			MU.msgTips("error", "登录失败，请稍后重试");
		}
	});
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

$(".imgCodeUpdate").on("click", function() {
	$(".imgCodeUpdate img").attr("src", contextPath + "/login/viewLoginCode?t=" + new Date().getTime());
});

$("#username").focus();