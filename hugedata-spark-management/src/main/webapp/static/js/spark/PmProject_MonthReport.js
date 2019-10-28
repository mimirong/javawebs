angular.module("monthReportModule", [ ]).controller("monthReportController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
	$scope.dataList = [];        //分页数据
	
	var doubleValide = /^\d+(\.\d+)?$/;
	
	 // reload
    $scope.reload = function() {
        $http({
            url: "listMonthInvest",
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
    
	 
	    
	    $scope.submitReport = function(reportId){
	    	if(!doubleValide.test($.trim($('#investedNum').val())) 
	    			|| !doubleValide.test($.trim($('#useDesign').val()))
	    			||!doubleValide.test($.trim($('#useOversee').val()))
	    			|| !doubleValide.test($.trim($('#useBuy').val()))
	    			||!doubleValide.test($.trim($('#useAsset').val()))
	    			||!doubleValide.test($.trim($('#useTotal').val()))
	    			||!doubleValide.test($.trim($('#useEngineer').val()))){
	    		
	    		 MU.msgTips("error", '请填写正确的数字!');
	    		 return false;
	    	}
	    	$http({
                url: "submitReport",
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
                	comment:$('#comment').val(),
                	approveInfo:$('#approveInfo').val()
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
	    
	    setTimeout(function(){
	    	$("span.yearList ul li").on('click',function(){
		    	 window.location.href = "monthReport?projectId="+$('#projectId').val()+"&month=1&year="+ $("span.yearList li.cur").attr('data-value');
		    });
        },1200);
	    
	
}]);
