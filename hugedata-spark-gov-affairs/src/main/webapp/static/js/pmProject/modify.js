$('a.modifyProject').on('click',function(){
	
	if($.trim($('#projectName').val()).length == 0){
		MU.msgTips("error", "请填写项目名称", 1200);
		return false;
	}

	if($.trim($('#projectType').val()).length == 0){
		MU.msgTips("error", "请填写建设性质", 1200);
		return false;
	}
	
	if($.trim($('#projectContent').val()).length == 0){
		MU.msgTips("error", "请填写建设规模和内容", 1200);
		return false;
	}
	
	$.ajax({
    	url: "modify",
    	type: "get",
    	dataType: "json",
    	data: {projectId:$('#projectId').val(),projectName:$.trim($('#projectName').val()),projectCode:$.trim($('#projectCode').val()),projectAddr:$.trim($('#projectAddr').val()),projectArea:$.trim($('#projectArea').val()),projectType:$.trim($('#projectType').val()),projectContent:$.trim($('#projectContent').val())},
    	success: function(resp) {
    		if (resp && resp.result == 0) {
    			MU.msgTips("success", '修改成功!');
    			window.location.href = contextPath + '/pmProject/list';
    		} else {
    			MU.msgTips("error", resp.message);
    		}
    	},
    	error: function() {
    		MU.msgTips("error", "获取数据失败，请稍后重试");
    	}
    });
}) 

