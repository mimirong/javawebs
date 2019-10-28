angular.module("monthReportModule", [ ]).controller("monthReportController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
	$scope.dataList = [];        //分页数据
	
	var doubleValide = /^\d+(\.\d+)?$/;
	
	$('a.addMonthInvest').on('click',function(){
   	 MU.alert($("#addMonthInvest").html(),650,'添加资金来源情况');
   	 $('div.alert span').children('select').selectList();
   		$('a.submitAddMonthInvest').on('click',function(){
   			if($("span.comeType li.cur").attr('data-value') == '-1'){
   				MU.msgTips("error", "请选择资金来源类型", 1200);
   				return false;
   			}
   			var comeTypeId = $(this).closest("ul").find("span.comeType li.cur").attr('data-value');
   			var comeTypeName = $(this).closest("ul").find("span.comeType li.cur").attr('title');
   			var comeFrom = $(this).closest("ul").find("input[name='comeFrom']").val();
   			var amount = $(this).closest("ul").find("input[name='amount']").val();
			if(!comeFrom || $.trim(comeFrom) == ''){
				MU.msgTips("error", '请填写资金来源主体!');
				return false;
			}
			
			if(!amount || $.trim(amount) == ''){
				MU.msgTips("error", '请填写投资金额!');
				return false;
			}
			
			if(!doubleValide.test($.trim(amount))){
	    		 MU.msgTips("error", '请填写正确的投资金额数字!');
	    		 return false;
	    	}
			
			if(parseFloat($.trim(amount)).toFixed(2) == 0.00){
				MU.msgTips("error", '投资金额不得为0.00!');
	    		 return false;
			}
			
			var that = this;
			
			  $http({
		            url: "addMonthInvest",
		            method: "post",
		            params: {
		            	projectId:$('#projectId').val(),
		            	reportId:$.trim($('#reportId').val()),
		            	comeTypeId:comeTypeId,
		            	comeTypeName:comeTypeName,
		            	comeFrom:comeFrom,
		            	amount:amount,
		            	month:$('#month').val()
		            }
		        }).success(function(data, status, headers, config) {
		        	MU.close(that);
		        	 $scope.reload();
		        }).error(function(data, status, headers, config) {
		        	MU.msgTips("error", '增加失败!');
		        });
   		});
   });
	
	
	 // reload
    $scope.reload = function() {
        $http({
            url: contextPath + "/pmProject/listMonthInvest",
            method: "get",
            params: {
            	projectId:$('#projectId').val(),
            	reportId:$.trim($('#reportId').val())
            }
        }).success(function(data, status, headers, config) {
            info = data.data;
            $scope.dataList = info;
            
        }).error(function(data, status, headers, config) {
            alert("查询失败");
        });
    };
    
    
    
    // 初始化
    $scope.reload();
    
	 $scope.deleteInvest = function(investId){
	    	
	    	MU.conFirm('删除','您确定要删除吗？',function(){
	    		 $http({
	                 url: contextPath + "/pmProject/deleteInvest",
	                 method: "post",
	                 params: {
	                 	investId:investId,
	                 }
	             }).success(function(data, status, headers, config) {
	            	  $scope.reload();
	             }).error(function(data, status, headers, config) {
	            	 MU.msgTips("error", '删除失败!');
	             });
	    	});
	    	
	    };
	    
	    $scope.submitReport = function(reportId){
	    	
	    	if(!$('#investedNum').val() ||  $.trim($('#investedNum').val()) == ''){
	    		MU.msgTips("error", '请填写本月完成投资!');
	    		 return false;
	    	}
	    	
	    	if(!doubleValide.test($.trim($('#investedNum').val())) 
	    			|| ($.trim($('#useDesign').val()).length != 0 && !doubleValide.test($.trim($('#useDesign').val())))
	    			|| ($.trim($('#useOversee').val()).length != 0 && !doubleValide.test($.trim($('#useOversee').val())))
	    			|| ($.trim($('#useBuy').val()).length != 0 && !doubleValide.test($.trim($('#useBuy').val())))
	    			|| ($.trim($('#useAsset').val()).length != 0 && !doubleValide.test($.trim($('#useAsset').val())))
	    			|| ($.trim($('#useTotal').val()).length != 0 && !doubleValide.test($.trim($('#useTotal').val())))
	    			|| ($.trim($('#useEngineer').val()).length != 0 && !doubleValide.test($.trim($('#useEngineer').val())))
	        ){
	    		
	    		 MU.msgTips("error", '请填写正确的数字!');
	    		 return false;
	    	}
	    	
	    	if($.trim($('#useDesign').val()).length == 0){
	    		$('#useDesign').val('0.00');
	    	} 
	    	
	    	if($.trim($('#useOversee').val()).length == 0){
	    		$('#useOversee').val('0.00');
	    	}
	    	
	    	if($.trim($('#useAsset').val()).length == 0){
	    		$('#useAsset').val('0.00');
	    	}
	    	
	    	if($.trim($('#useBuy').val()).length == 0){
	    		$('#useBuy').val('0.00');
	    	}
	    	
	    	if($.trim($('#useEngineer').val()).length == 0){
	    		$('#useEngineer').val('0.00');
	    	}
	    	
	    	if($.trim($('#useTotal').val()).length == 0){
	    		$('#useTotal').val('0.00');
	    	} 
	    	
	    	
	    	if($.trim($('#current').val()).length == 0){
	    		MU.msgTips("error", "请填写当前工程建设形象进度", 1200);
	    		return false;
	    	}
	    	
	    	
	    	$http({
                url: contextPath + "/pmProject/submitReport",
                method: "post",
                params: {
                	reportId:$('#reportId').val(),
                	investedNum:parseFloat($.trim($('#investedNum').val())).toFixed(2),
                	useDesign:parseFloat($.trim($('#useDesign').val())).toFixed(2),
                	useOversee:parseFloat($.trim($('#useOversee').val())).toFixed(2),
                	useEngineer:parseFloat($.trim($('#useEngineer').val())).toFixed(2),
                	useBuy:parseFloat($.trim($('#useBuy').val())).toFixed(2),
                	useAsset:parseFloat($.trim($('#useAsset').val())).toFixed(2),
                	useTotal:parseFloat($.trim($('#useTotal').val())).toFixed(2),
                	current:$('#current').val(),
                	asset:$('#asset').val(),
                	difficult:$('#difficult').val(),
                	difficultOrg:$('#difficultOrg').val(),
                	comment:$('#comment').val()
                }
            }).success(function(data, status, headers, config) {
            	 MU.msgTips("success", '提交成功!');
            	 window.location.href = "monthReport?projectId="+$('#projectId').val()+"&month="+$('#month').val()+"&year="+$("span.yearList li.cur").attr('data-value');
            }).error(function(data, status, headers, config) {
           	 MU.msgTips("error", '提交失败!');
            });
	    }
	    
	    $('#investedNum').blur(function() {
	    	if($('#amount').text() != '暂无数据'){
	    		if($('#amount').text() != '0.00'){
    		    	if(!doubleValide.test($.trim($('#investedNum').val())))
    		        {
    		    		 MU.msgTips("error", '请填写正确的本月完成投资数字!');
    		    		 return false;
    		    	}
	    			var investedNum = parseFloat($('#investedNum').val());
	    			var amount = parseFloat($('#amount').text());
	    			var percentNum = (investedNum*100)/amount;
	    			$('#investedNumPercent').text(percentNum.toFixed(2) + '%');	
	    		}
	    	}
    	}); 
	    
	    
	    $("span.yearList li").on('click',function(){
	    	 window.location.href = "monthReport?projectId="+$('#projectId').val()+"&month=1&year="+ $("span.yearList li.cur").attr('data-value');
	    });
	   
	
}]);



