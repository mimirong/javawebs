
// 修改
function doModify() {
    // oldPassword
    var oldPassword = $("#oldPassword").val();
    if(oldPassword == ""){
        MU.msgTips("warn", "请输入旧密码");
        $("#oldPassword").focus();
        return;
    }

    // password
    var password = $("#password").val();
    if (password == "") {
        MU.msgTips("warn", "请输入新密码");
        $("#password").focus();
        return;
    } else if (password.length < 6) {
        MU.msgTips("warn", "新密码不得低于6位");
        $("#password").focus();
        return;
    }

    // passwords
    var passwords = $("#passwords").val();
    if(passwords == ""){
        MU.msgTips("warn", "请再次输入新密码");
        $("#passwords").focus();
        return;
    }

    // compare password
    if(password != passwords){
        MU.msgTips("warn", "两次的新密码不一致，请重新输入！");
        $("#passwords").focus();
        return;
    }else if(password == oldPassword){
        MU.msgTips("warn", "新密码与旧密码一致！");
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

    $.ajax({
        url: contextPath + "/modifyPwd/doModify",
        type: "post",
        data: {
            oldPassword: oldPassword,
            password: password,
            imgCode: imgCode,
        },
        success: function (data) {
            if(data && data.result == 0){
                MU.msgTips('success','修改成功',1200);
                setTimeout(function () {
                    window.location.href = managementUrl;
                }, 1000);
            }else{
                reloadImagCode();
                MU.msgTips('error', data.message);
            }
        },
        error: function () {
            $("#oldPassword").val("");
            $("#password").val("");
            $("#passwords").val("");
            $("#imgCode").val("");
            reloadImagCode();
            MU.msgTips("error", "修改失败，请稍后重试");
        }
    });

}

function reloadImagCode() {
    $("#imgCode").val("");
    $(".imgCodeUpdate img").attr("src", contextPath + "/modifyPwd/viewLoginCode?t=" + new Date().getTime());
}

$("#oldPassword").on("keypress", function(e) {
    if (e.keyCode == 13) {
        $("#password").focus();
    }
});

$("#password").on("keypress", function(e) {
    if (e.keyCode == 13) {
        $("#passwords").focus();
    }
});

$("#passwords").on("keypress", function(e) {
    if (e.keyCode == 13) {
        $("#imgCode").focus();
    }
});

$("#imgCode").on("keypress", function(e) {
    if (e.keyCode == 13) {
        doModify();
    }
});

$("#submitModifyPassword").on("click", doModify);

$("#oldPassword").val("");
$("#password").val("");
$("#passwords").val("");
$("#imgCode").val("");

$(".imgCodeUpdate").on("click", reloadImagCode);

$("#oldPassword").focus();