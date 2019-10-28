var MAX_IMAGES = 2;
var MAX_ATTACHMENTS = 2;

var modifyingId = null;
var uploadAttachmentData = [];
var DOUBLE_REGEX = /^\d+(\.\d+)?$/;
var TELEPHONE_REGEX = /^\d{6,}$/;
var MOBILE_REGEX = /^[\d\(\)\-\+]{6,30}$/;
var EMAIL_REGEX = /.+@.+\.[a-zA-Z]{2,4}$/;
if($('#requireId').val()){
	//修改需求初始化
	$('h2.se-title span').text('修改需求');
	$('#btnDoAdd').text('修改发布');
	 $.ajax({
	        url: "getRequire",
	        type: "get",
	        dataType: "json",
	        data: {requireId:$('#requireId').val()},
	        success: function(resp) {
	            if (resp && resp.result == 0) {
	            	var r = resp.data.requireInfo;
	            	$('span.requireArea select option[value="'+r.requireArea+'"]').attr("selected","selected") ;
	           	 	$('span.requireArea').children('select').selectList();
	           	 	$('#requireTitle').val(r.requireTitle);
	           	 	$('#deadDate').datepicker('setDate',DateFormat.format.date(new Date(r.deadDate), 'yyyy-MM-dd'));
	           	 	$('#offerDate').datepicker('setDate',DateFormat.format.date(new Date(r.offerDate), 'yyyy-MM-dd'));
	           	 	$('#hopePrice').val(r.hopePrice);
	           	 	if(r.isChat == '1'){
	           	 		$('#isChat').addClass('checked');
	           	 	}
	           	 	$('span.priceUnit select option[value="'+r.priceUnit+'"]').attr("selected","selected") ;
	           	 	$('span.priceUnit').children('select').selectList();
	           	 	if(r.isQuick == '1'){
	           	 		$('#isQuick').addClass('checked');
	           	 	} 
	           	 	$('#requireDesc').val(r.requireDesc);
	           	 	$('#keyWord').val(r.keyWord);
	           	 	
	           	 	$('span.paymentMethod select option[value="'+r.paymentMethod+'"]').attr("selected","selected") ;
	           	 	$('span.paymentMethod').children('select').selectList();
	           	 	
	           	 	$('span.invoiceType select option[value="'+r.invoiceType+'"]').attr("selected","selected") ;
	           	 	$('span.invoiceType').children('select').selectList();
	           	 
	           	 	$('span.freightPayer select option[value="'+r.freightPayer+'"]').attr("selected","selected") ;
	           	 	$('span.freightPayer').children('select').selectList();
	           	 	
	           	 	if(r.isCod == '1'){
	           	 		$('#isCod').addClass('checked');
	           	 		$('#noCod').removeClass('checked');
	           	 	}
	           	 	
		           	 $('#requireNum').val(r.requireNum);
		           	 $('#numUnit').val(r.numUnit);
	           	 
		           	$('span.contactArea select option[value="'+r.contactArea+'"]').attr("selected","selected") ;
	           	 	$('span.contactArea').children('select').selectList();
		           	
	           	 $('#contactAddr').val(r.contactAddr);
	           	 $('#contacter').val(r.contacter);
	           	 	
	           	 $('#contactPhone').val(r.contactPhone);
	           	 $('#email').val(r.email);
	           	 
	           	$.each(resp.data.atts,function(i,n){
	           		uploadAttachmentData.push({
		                fileId: n.fileId,
		                fileName: n.fileName
		            });
		           	
	           	});
	           	
	           	window.uploadRequireAttData = uploadAttachmentData;
	           	window.loadUploadedAtts();
	            } else {
	                MU.msgTips("error", resp.message, 1200);
	            }
	        },
	        error: function() {
	            MU.msgTips("error", "获取需求信息失败，请稍后重试", 1200);
	        }
	    });
}  
 
 
//检查表单
function checkAddModifyForm() {
	
	if($("span.requireArea li.cur").attr('data-value') == '请选择'){
		MU.msgTips("error", "请选择需求领域", 1200);
		return false;
	}
	
	var requireArea = $("span.requireArea li.cur").attr('data-value');
    // title
    var requireTitle = $.trim($("#requireTitle").val());
    if (requireTitle == "") {
        MU.msgTips("error", "请输入需求主题名称", 1200);
        $("#requireTitle").focus();
        return null;
    }
    
    if (requireTitle.length > 50) {
        MU.msgTips("error", "需求主题名称长度超过了50个字符", 1200);
        $("#requireTitle").focus();
        return null;
    }
    
    var deadDate = $.trim($("#deadDate").val());
    if (deadDate == "") {
        MU.msgTips("error", "请输入截止日期", 1200);
        $("#deadDate").focus();
        return null;
    }
    
    var offerDate = $.trim($("#offerDate").val());
    if (offerDate == "") {
        MU.msgTips("error", "请输入交付日期", 1200);
        $("#offerDate").focus();
        return null;
    }
    
    var dTime = moment(deadDate, "YYYY-MM-DD");
    
    if(!moment().isBefore(dTime)){
   	 MU.msgTips("warn", "截止日期需在当前日期之后", 1200);
        return null;
   }
    var oTime = moment(offerDate, "YYYY-MM-DD");
    if(oTime.isBefore(dTime)){
    	 MU.msgTips("warn", "交付日期不能在截止日期之前", 1200);
         return null;
    }
    
    var hopePrice = $.trim($("#hopePrice").val());
    
    
    var isChat = '0';
    if($('#isChat').hasClass('checked')){
    	isChat = '1';
    }else{
    	if (hopePrice == "") {
            MU.msgTips("error", "请输入期望最高总价或选择面议", 1200);
            $("#hopePrice").focus();
            return null;
        }
    }
    
    var priceUnit = $("span.priceUnit li.cur").attr('data-value');
    
    var isQuick = '0';
    if($('#isQuick').hasClass('checked')){
    	isQuick = '1';
    }
    
    var requireDesc = $.trim($("#requireDesc").val());
    if (requireDesc == "") {
        MU.msgTips("error", "请输入需求描述", 1200);
        $("#requireDesc").focus();
        return null;
    }
    
    if (requireDesc.length > 500) {
        MU.msgTips("error", "需求描述不能超过500字，当前" + requireDesc.length + "字", 1200);
        $("#requireDesc").focus();
        return null;
    }
    
    
    var keyWord = $.trim($("#keyWord").val());
    if (keyWord == "") {
        MU.msgTips("error", "请输入关键词", 1200);
        $("#keyWord").focus();
        return null;
    }
    
    if (keyWord.length > 60) {
        MU.msgTips("error", "关键词长度超过了60个字符", 1200);
        $("#keyWord").focus();
        return null;
    }
    
    if($("span.paymentMethod li.cur").attr('data-value') == '请选择'){
		MU.msgTips("error", "请选择付款方式", 1200);
		return false;
	}
	var paymentMethod = $("span.paymentMethod li.cur").attr('data-value');
	
	 if($("span.invoiceType li.cur").attr('data-value') == '请选择'){
			MU.msgTips("error", "请选择发票类型", 1200);
			return false;
		}
	var invoiceType = $("span.invoiceType li.cur").attr('data-value');
	
	 if($("span.freightPayer li.cur").attr('data-value') == '请选择'){
			MU.msgTips("error", "请选择运费承担方", 1200);
			return false;
		}
	 
	var freightPayer = $("span.freightPayer li.cur").attr('data-value');
	
	 var isCod = '0';
	    if($('#isCod').hasClass('checked')){
	    	isCod = '1';
	    }
	    
	    
	    var requireNum = $.trim($("#requireNum").val());
	    if (requireNum == "") {
	        MU.msgTips("error", "请输入需求数量", 1200);
	        $("#requireNum").focus();
	        return null;
	    }
	    
	    var numUnit = $.trim($("#numUnit").val());
	    if (numUnit == "") {
	        MU.msgTips("error", "请输入计量单位", 1200);
	        $("#numUnit").focus();
	        return null;
	    }
	    
	    
	    if($("span.contactArea li.cur").attr('data-value') == '请选择'){
			MU.msgTips("error", "请选择联系地址", 1200);
			return false;
		}
	 
	    var contactArea = $("span.contactArea li.cur").attr('data-value');
	
	    var contactAddr = $.trim($("#contactAddr").val());
	    if (contactAddr == "") {
	        MU.msgTips("error", "请输入详细地址", 1200);
	        $("#contactAddr").focus();
	        return null;
	    }
	    
	    if (contactAddr.length > 50) {
	        MU.msgTips("error", "详细地址长度超过了50个字符", 1200);
	        $("#contactAddr").focus();
	        return null;
	    }
	    
	    var contacter = $.trim($("#contacter").val());
	    if (contacter == "") {
	        MU.msgTips("error", "请输入联系人", 1200);
	        $("#contacter").focus();
	        return null;
	    }
	    
	    if (contacter.length > 10) {
	        MU.msgTips("error", "联系人长度超过了10个字符", 1200);
	        $("#contacter").focus();
	        return null;
	    }
	    
	    
	    var contactPhone = $.trim($("#contactPhone").val());
	    if (contactPhone == "") {
	        MU.msgTips("error", "请输入联系电话", 1200);
	        $("#contactPhone").focus();
	        return null;
	    }
	    
	    if (contactPhone.length > 20) {
	        MU.msgTips("error", "联系电话长度超过了20个字符", 1200);
	        $("#contactPhone").focus();
	        return null;
	    }
	    
	    if (!TELEPHONE_REGEX.test(contactPhone)) {
	        MU.msgTips("warn", "联系人电话格式不正确", 1200);
	        $("#contactPhone").focus();
	        return null;
	    }
	    
	    
	    var email = $.trim($("#email").val());
	    if (email == "") {
	        MU.msgTips("error", "请输入电子邮箱", 1200);
	        $("#email").focus();
	        return null;
	    }
	    
	    if (email.length > 50) {
	        MU.msgTips("error", "邮箱长度超过了50个字符", 1200);
	        $("#email").focus();
	        return null;
	    }
	    
	    if(!EMAIL_REGEX.test(email)) {
	        MU.msgTips("warn", "邮箱的格式不正确", 1200);
	        $("#email").focus();
	        return null;
	    }
	    
    

    return {
    	requireArea: requireArea,
    	requireTitle: requireTitle,
    	deadDate: deadDate,
    	offerDate: offerDate,
    	hopePrice: hopePrice,
    	isChat: isChat,
    	priceUnit: priceUnit,
    	isQuick:isQuick,
    	requireDesc:requireDesc,
    	keyWord:keyWord,
    	paymentMethod:paymentMethod,
    	invoiceType:invoiceType,
    	freightPayer:freightPayer,
    	isCod:isCod,
    	requireNum:requireNum,
    	uploadRequireAttData: JSON.stringify(window.uploadRequireAttData),
    	numUnit:numUnit,
    	contactArea:contactArea,
    	contactAddr:contactAddr,
    	contacter:contacter,
    	contactPhone:contactPhone,
    	email:email
    };
}

//提交新增
$("#btnDoAdd").on("click", function() {
    var data = checkAddModifyForm();
    if (data == null || data == false) {
        return;
    }
    
    if($('#requireId').val()){
    	data.requireId = $('#requireId').val();
    	$.ajax({
            url: "doRequireModify",
            type: "post",
            dataType: "json",
            data: data,
            success: function(resp) {
                if (resp && resp.result == 0) {
                    MU.msgTips("success", "修改成功", 1200);
                    window.location.href = "requireMy";
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "修改失败，请稍后重试", 1200);
            }
        });
    }else{
    	$.ajax({
            url: "doRequireAdd",
            type: "post",
            dataType: "json",
            data: data,
            success: function(resp) {
                if (resp && resp.result == 0) {
                    MU.msgTips("success", "添加成功", 1200);
                    window.location.href = "requireMy";
                } else {
                    MU.msgTips("error", resp.message, 1200);
                }
            },
            error: function() {
                MU.msgTips("error", "添加失败，请稍后重试", 1200);
            }
        });
    }
});


