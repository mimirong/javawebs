//获取企业列表
$.ajax({
        url: "getCompanies",
        type: "post",
		dataType: "json",
		data:  {},
		success: function(resp) {
		if (resp && resp.result == 0) {
			 if(resp.data){
		        	var ol = '';
		            for(var i=0;i < resp.data.length;i++ ){
		            	ol = ol + '"<option value='+resp.data[i].companyId+'>'+resp.data[i].companyName+'</option>"';
		            }
		            $('span.companies').children('select').append(ol);
		            $('span.companies').children('select').selectList();
		            
		            // 填加企业下拉员工显示事件
		            
		        }
			
		} else {
			MU.msgTips("error", resp.message, 1200);
		}
	},
	error: function() {
		MU.msgTips("error", "增加失败，请稍后重试", 1200);
	}
});

$("span.companies ").delegate("ul  li","click", function() {
	var companyId = $(this).attr('data-value');
	if(companyId != '-1'){
		$.ajax({
	        url: "getInfUsers",
	        type: "post",
			dataType: "json",
			data:  {companyId:companyId},
			success: function(resp) {
			if (resp && resp.result == 0) {
				 if(resp.data){
			        	var ol = '<option value="-1">选择对接人</option>';
			            for(var i=0;i < resp.data.length;i++ ){
			            	ol = ol + '"<option value='+resp.data[i].userId+'>'+resp.data[i].name+'</option>"';
			            }
			            $('span.infUsers').children('select').html(ol);
			            $('span.infUsers').children('select').selectList();
			        }
				
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "增加失败，请稍后重试", 1200);
		}
	});
	}else{
		var ol = '<option value="-1">选择对接人</option>';
        $('span.infUsers').children('select').html(ol);
        $('span.infUsers').children('select').selectList();
	}
});


$('a.doAdd').on('click',function(){
	
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
	if($.trim($('#mainActor').val()).length == 0){
		MU.msgTips("error", "请填写实施主体", 1200);
		return false;
	}
	if($.trim($('#dutyOrg').val()).length == 0){
		MU.msgTips("error", "请填写责任单位", 1200);
		return false;
	}
	if($.trim($('#dutyMan').val()).length == 0){
		MU.msgTips("error", "请填写责任人", 1200);
		return false;
	}
	if($.trim($('#contactLead').val()).length == 0){
		MU.msgTips("error", "请填写联系领导", 1200);
		return false;
	}
	if($.trim($('#repoName').val()).length == 0){
		MU.msgTips("error", "请填写入库项目名称", 1200);
		return false;
	}
	
	if($.trim($('#investTotal').val()).length == 0){
		MU.msgTips("error", "请填写投资规模", 1200);
		return false;
	}
	
	var doubleValide = /^\d+(\.\d+)?$/;
	if(!doubleValide.test($.trim($('#investTotal').val()))){
  		 MU.msgTips("error", '请填写正确的投资规模数字!');
  		 return false;
	}
	
	var investTotal = parseFloat($('#investTotal').val());
	investTotal.toFixed(2);
	
	if($("span.companies li.cur").attr('data-value') == '-1' ||  $("span.infUsers li.cur").attr('data-value') == '-1'){
		MU.msgTips("error", "请填写企业对接人", 1200);
		return false;
	}
	  			$.ajax({
	  				url: "doAdd",
	  				type: "post",
	  				dataType: "json",
	  				data: {projectName:$('#projectName').val(),
	  					projectCode:$('#projectCode').val(),
	  					projectAddr:$('#projectAddr').val(),
	  					projectArea:$('#projectArea').val(),
	  					projectType:$('#projectType').val(),
	  					projectContent:$('#projectContent').val(),
	  					mainActor:$('#mainActor').val(),
	  					dutyOrg:$('#dutyOrg').val(),
	  					dutyMan:$('#dutyMan').val(),
	  					contactLead:$('#contactLead').val(),
	  					repoName:$('#repoName').val(),
	  					beginEnd:$('span.startYear li.cur').html() + '~' + $('span.endYear li.cur').html() ,
	  					investTotal:investTotal,
	  					infCompanyId:$("span.companies li.cur").attr('data-value'),
	  					infCompanyName:$("span.companies li.cur").attr('title'),
	  					infUserId:$("span.infUsers li.cur").attr('data-value'),
	  					infUserName:$("span.infUsers li.cur").attr('title')
	  				},
	  				success: function(resp) {
	  					if (resp && resp.result == 0) {
	  						MU.msgTips("success", "增加成功", 1200);
	  						window.location.href = contextPath + '/features/PmProject/list';
	  					} else {
	  						MU.msgTips("error", resp.message, 1200);
	  					}
	  				},
	  				error: function() {
	  					MU.msgTips("error", "增加失败，请稍后重试", 1200);
	  				}
	  			});
});