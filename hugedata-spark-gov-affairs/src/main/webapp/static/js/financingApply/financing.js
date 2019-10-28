(function() {
    doAdd();
})();

function doAdd() {

    $("#addApplyFinancing").on("click", function() {
        var data = checkAddModifyForm();
        if (data == null) {
            return;
        }

        //提交
        $.ajax({
            url: "doApplyFinancing",
            type: "post",
            dataType: "json",
            data: data,
            success: function(resp) {
                if (resp && resp.result == 0) {
                    window.MU.msgTips("success", "提交成功", 1200);
                    setTimeout(function(){
                        window.location.href = "list";
                    },1200);
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "添加失败，请稍后重试", 1200);
            }
        });
    });
}



// 检查表单
function checkAddModifyForm() {

    //companyName
    var companyName = $("#companyName").val();
    if (companyName == "") {
        MU.msgTips("warn", "请输入公司名称", 1200);
        $("#companyName").focus();
        return null;
    }

    //remark
    var remark = $("#remark").val();
    if (remark == "") {
        MU.msgTips("warn", "请输入融资说明", 1200);
        $("#remark").focus();
        return null;
    }

    //businessLicenceFile
    var businessLicenceFilename = $("#businessLicenceFilename").val();
    var businessLicence = $("#businessLicence").val();

    if(businessLicenceFilename == null || businessLicenceFilename == '')  {
        MU.msgTips("warn", "营业执照不能为空", 1200);
        return null;
    }

    if(businessLicenceFilename != null && businessLicenceFilename != '' && !/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(businessLicenceFilename))  {
        MU.msgTips("warn", "营业执照图片类型必须是.gif,jpeg,jpg,png中的一种", 1200);
        return null;
    }

    //staffSize
    var staffSize = $("#staffSize").val();
    if (staffSize == "") {
        MU.msgTips("warn", "请输入公司人员规模", 1200);
        $("#staffSize").focus();
        return null;
    }

    //businessInfo
    var businessInfo = $("#businessInfo").val();
    if (businessInfo == "") {
        MU.msgTips("warn", "请输入公司业务介绍", 1200);
        $("#businessInfo").focus();
        return null;
    }

    //contact
    var contact = $("#contact").val();
    if (contact == "") {
        MU.msgTips("warn", "请输入联系人", 1200);
        $("#contact").focus();
        return null;
    }

    //contactMobile
    var contactMobile = $("#contactMobile").val();
    if("" == contactMobile){
        MU.msgTips("warn", "请输入联系电话", 1200);
        $("#contactMobile").focus();
        return null;
    }

    if (!CheckService.telephone(contactMobile)) {
        MU.msgTips("warn", "请输入正确的联系电话", 1200);
        $("#contactMobile").focus();
        return null;
    }

    //applicationFormFilename
    var applicationFormFilename = $("#applicationFormFilename").val();

    //applicationForm
    var applicationForm = $("#applicationForm").val();

    return {
        companyName: companyName,
        remark: remark,
        businessLicenceFilename: businessLicenceFilename,
        businessLicence: businessLicence,
        staffSize: staffSize,
        businessInfo: businessInfo,
        contact: contact,
        contactMobile: contactMobile,
        applicationFormFilename: applicationFormFilename,
        applicationForm: applicationForm
    };

}