angular.module("yearPlanModule", [ ]).controller("yearPlanController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
    var PAGE_SIZE = 10;          //每页显示记录数
    $scope.dataList = [];        //分页数据
    $scope.page = 1;             //当前页
    $scope.pageTotal = 0;        //总页数
    $scope.count = 0;  			 //总条数	
    $scope.hasNextPage = false;  //是否有下一页
    $scope.hasPrevPage = false;  //是否有上一页
    $scope.pageButtons = [];     //显示页码按钮
    $scope.directGoto = "";      //bind直接跳转到页码的文本框
    
    
    // gotoPage
    $scope.gotoPage = function(p) {
        if (p > $scope.pageTotal) {
            return;
        }
        $scope.page = p;
        $scope.reload();
    };

    // directGotoPage
    $scope.directGotoPage = function() {
        if ($scope.directGoto == "") {
            return;
        }
        var p = parseInt($scope.directGoto);
        if (!isNaN(p)) {
            $scope.gotoPage(p);
        }
    };

    // page input keydown
    $scope.onPageKeydown = function(e) {
        if (e.keyCode == 13) {
            $scope.directGotoPage();
        }
    };
    
    $('a.addPlan').on('click',function(){
    	 MU.alert($("#addPlan").html(),650,'填加年度计划');
    	 $('span.planYear').children('select').selectList();
    		$('a.submitAddPlan').on('click',function(){
    			var amount = $(this).closest("form").find("input[name='amount']").val();
    			var mainActor = $(this).closest("form").find("input[name='mainActor']").val();
    			var content = $(this).closest("form").find("textarea[name='content']").val();
    			$('input[name="content"]').val(content);
    			$("input[name='planYear']").val($("span.planYear li.cur").attr('data-value'));
    			if(!amount || !mainActor || !content){
    				MU.msgTips("error", '请将内容填写完整!');
    				return false;
    			}
    			
    			$(this).closest('form').submit();
    			return false;
    		});
    });
    

    // reload
    $scope.reload = function() {
        $http({
            url: "listPlan",
            method: "get",
            params: {
            	projectId:$('#projectId').val(),
                start: PAGE_SIZE * ($scope.page - 1),
                length: PAGE_SIZE
            }
        }).success(function(data, status, headers, config) {
            info = data.data;
            //构造附件下载url attachmentFileUrl
            for(var i = 0; i < info.data.length; i++) {
//                if(null != info.data[i].attachmentFileId && null != info.data[i].attachmentFileName) {
//                    info.data[i].attachmentFileUrl = DownloadService.buildUrl(info.data[i].attachmentFileId, info.data[i].attachmentFileName);
//                }
            }
            $scope.dataList = info.data;
            $scope.page = info.page;
            $scope.pageTotal = info.pageCount;
            $scope.count = info.recordsTotal;
            $scope.hasNextPage = (info.page < info.pageCount);
            $scope.hasPrevPage = (info.page > 1);

            // 分页按钮处理
            $scope.pageButtons = [];
            for (var i = Math.max(1, $scope.page - 2); i <= Math.min(info.pageCount, $scope.page + 2); i++) {
                $scope.pageButtons.push(i);
            }
         
            
            
        }).error(function(data, status, headers, config) {
            alert("查询失败");
        });
    };
    
    
    
    // 初始化
    $scope.reload();
    
    $scope.approvePlan = function(planId){
    	
    	MU.alert($("#approvePlan").html(),650,'审核结论');
   	 
   		$('a.approvePlan').on('click',function(){
   			
   		  
   			var result = $(this).closest("form").find("textarea[name='result']").val();
   			
   			$("input[name='approveResult']").val(result);
   			$("input[name='planId']").val(planId); 			 
   			$("input[name='approveStatus']").val("1");
   			if(!result || $.trim(result) == ''){
   				MU.msgTips("error", '请填写审核结论!');
   				return false;
   			}
   			
   			if( $.trim(result).length > 200){
   				MU.msgTips("error", '审核结论限200字!');
   				return false;
   			}
   			
   			
   			$(this).closest('form').submit();
   			return false;
   		});
   		
   		$('a.disApprovePlan').on('click',function(){
   			
     		  
   			var result = $(this).closest("form").find("textarea[name='result']").val();
   			
   			$("input[name='approveResult']").val(result);
   			$("input[name='planId']").val(planId); 			 
   			$("input[name='approveStatus']").val("2");
   			if(!result || $.trim(result) == ''){
   				MU.msgTips("error", '请填写审核结论!');
   				return false;
   			}
   			
   			if( $.trim(result).length > 200){
   				MU.msgTips("error", '审核结论限200字!');
   				return false;
   			}
   			
   			
   			$(this).closest('form').submit();
   			return false;
   		});
    	
    };
    
    
 $scope.deletePlan = function(planId){
		MU.conFirm('删除','您确定要删除吗？',function(){
			  $http({
		          url: "deletePlan",
		          method: "post",
		          params: {
		          	planId:planId
		          }
		      }).success(function(data, status, headers, config) {
		      	console.log(data);
		      	if(data.result == 1){
		      		MU.msgTips("error", data.message);
		        	$scope.reload();
		      	}else{
		      		MU.msgTips("success", "删除成功！");
		        	$scope.reload();
		      	}
		      	
		      }).error(function(data, status, headers, config) {
		      	MU.msgTips("error", '删除失败!');
		      });
   	});
		
		
	
    	
    };
    
    
    
	
	  
	  

}]);



