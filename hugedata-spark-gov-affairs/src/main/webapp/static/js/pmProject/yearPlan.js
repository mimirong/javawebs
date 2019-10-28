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
    
    var doubleValide = /^\d+(\.\d+)?$/;
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
    	 MU.alert($("#addPlan").html(),650,'添加年度计划');
    	 $('div.alert span.planYear').children('select').selectList();
    		$('a.submitAddPlan').on('click',function(){
    			
    			
    			
    			var amount = $(this).closest("form").find("input[name='amount']").val();
    			var mainActor = $(this).closest("form").find("input[name='mainActor']").val();
    			var content = $(this).closest("form").find("textarea[name='contentT']").val();
    			$('input[name="content"]').val(content);
    			$("input[name='planYear']").val($("span.planYear li.cur").attr('data-value'));
    			if(!amount ){
    				MU.msgTips("error", '请输入年计划投资!');
    				return false;
    			}
    			
    			if(!mainActor ){
    				MU.msgTips("error", '请输入计划投资主体!');
    				return false;
    			}
    			
    			if( !content){
    				MU.msgTips("error", '请输入年主要建设内容!');
    				return false;
    			}
    			
    			if(!doubleValide.test($.trim(amount))){
   	    		 MU.msgTips("error", '请填写正确的年计划投资数字!');
   	    		 return false;
    			}
    			
    			var that = this;
    			
  			  $http({
  		            url: "addPlan",
  		            method: "post",
  		            params: {
  		            	projectId:$('#projectId').val(),
  		            	planYear:$("input[name='planYear']").val(),
  		            	amount:amount,
  		            	mainActor:mainActor,
  		            	content:content
  		            }
  		        }).success(function(data, status, headers, config) {
  		        	console.log(data);
  		        	if(data.result == 1){
  		        		MU.msgTips("error", data.message);
  		        	}else{
  		        		MU.close(that);
  	  		        	$scope.reload();
  		        	}
  		        	
  		        }).error(function(data, status, headers, config) {
  		        	MU.msgTips("error", '增加失败!');
  		        });
    		});
    });
    
    $scope.modifyPlan = function(plan){
    	
    	
    	
    	 MU.alert($("#addPlan").html(),650,'修改年度计划');
    	 var date=new Date;
    	 var year=date.getFullYear();
    	 var mydate = year.toString();
    	 var yearOp = '0';
    	 if(plan.planYear == mydate){
    		 yearOp = '0';
    	 }else{
    		 yearOp = '1';
    	 }
    	 
    		$("div.alert form").find("input[name='amount']").val(plan.amount);
        	$("div.alert form").find("input[name='mainActor']").val(plan.mainActor);
        	$("div.alert form").find("textarea[name='contentT']").val(plan.content);
        	$("div.alert form li.planYear").before('<input type="hidden"  name="planId" value="'+plan.planId+'"/>');
    	 $('div.alert span select option[value="'+yearOp+'"]').attr("selected","selected") ;
       	 $('div.alert span.planYear').children('select').selectList();
       	 
       
       	 
       		$('a.submitAddPlan').on('click',function(){
       			
       			
       			
       			var amount = $(this).closest("form").find("input[name='amount']").val();
       			var mainActor = $(this).closest("form").find("input[name='mainActor']").val();
       			var content = $(this).closest("form").find("textarea[name='contentT']").val();
       			$('input[name="content"]').val(content);
       			$("input[name='planYear']").val($("span.planYear li.cur").attr('data-value'));
       			if(!amount ){
       				MU.msgTips("error", '请输入年计划投资!');
       				return false;
       			}
       			
       			if(!mainActor ){
       				MU.msgTips("error", '请输入计划投资主体!');
       				return false;
       			}
       			
       			if( !content){
       				MU.msgTips("error", '请输入年主要建设内容!');
       				return false;
       			}
       			
       			if(!doubleValide.test($.trim(amount))){
      	    		 MU.msgTips("error", '请填写正确的年计划投资数字!');
      	    		 return false;
       			}
       			
       			var that = this;
       			
     			  $http({
     		            url: "modifyPlan",
     		            method: "post",
     		            params: {
     		            	planId:$("input[name='planId']").val(),
     		            	projectId:$('#projectId').val(),
     		            	planYear:$("input[name='planYear']").val(),
     		            	amount:amount,
     		            	mainActor:mainActor,
     		            	content:content
     		            }
     		        }).success(function(data, status, headers, config) {
     		        	console.log(data);
     		        	if(data.result == 1){
     		        		MU.msgTips("error", data.message);
     		        	}else{
     		        		MU.close(that);
     	  		        	$scope.reload();
     		        	}
     		        	
     		        }).error(function(data, status, headers, config) {
     		        	MU.msgTips("error", '增加失败!');
     		        });
       		});
    		
        };
        
    

    // reload
    $scope.reload = function() {
        $http({
            url: contextPath + "/pmProject/listPlan",
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
    

}]);



