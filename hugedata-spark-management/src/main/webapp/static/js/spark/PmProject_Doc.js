angular.module("docModule", [ ]).controller("docController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
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
    
    $('a.addDoc').on('click',function(){
    	 MU.alert($("#addDoc").html(),650,'填加批复文件');
    	 $('span').children('select').selectList();
    	 $("input[type='file']").on('change',function(){
    	   	 
    	   	 $(this).addClass("addFiled");
    	   	 
    	   });
    	 
    		$('a.submitAddDoc').on('click',function(){
    			
    			if($("span.docType li.cur").attr('data-value') == '-1'){
    				MU.msgTips("error", "请选择文件类型", 1200);
    				return false;
    			}
    			
    			$("input[name='typeCode']").val($("span.docType li.cur").attr('data-value'));
    			$("input[name='typeName']").val($("span.docType li.cur").attr('title'));
    			var fileName = $(this).closest("form").find("input[name='fileName']").val();
    			
    			var approveCode = $(this).closest("form").find("input[name='approveCode']").val();
    			var fileContent = $(this).closest("form").find("input[name='fileContent']").hasClass('addFiled');
    			
    			if($.trim($('#typeCodeBegin').val()) != '200'){
    				if(  !approveCode ){
        				MU.msgTips("error", '请填写批复文号!');
        				return false;
        			}
    			}

    			if(!fileName || !fileContent){
    				MU.msgTips("error", '请将内容填写完整!');
    				return false;
    			}
    			
    			$(this).closest('form').submit();
    			return false;
    		});
    });
    

    // reload
    $scope.reload = function() {
    	var now = new Date();
        $http({
            url: "listDoc",
            method: "get",
            params: {
            	projectId:$('#projectId').val(),
            	typeCodeBegin:$.trim($('#typeCodeBegin').val()),
            	rd:now.getTime(),
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
    
    $scope.deleteDoc = function(docId){
    	
    	MU.conFirm('删除','您确定要删除吗？',function(){
    		 $http({
                 url:"deleteDoc",
                 method: "post",
                 params: {
                 	docId:docId,
                 }
             }).success(function(data, status, headers, config) {
            	  $scope.reload();
             }).error(function(data, status, headers, config) {
            	 MU.msgTips("error", '删除失败!');
             });
    	});
    	
    };
    
    $scope.chgDocStatus = function(docId){
    	
    	MU.conFirm('通过','您确定要通过审核吗？',function(){
    		 $http({
                 url:"chgDocStatus",
                 method: "post",
                 params: {
                 	docId:docId,
                 	status:'1'
                 }
             }).success(function(data, status, headers, config) {
            	  $scope.reload();
             }).error(function(data, status, headers, config) {
            	 MU.msgTips("error", '审核失败!');
             });
    	});
    	
    };
    
    $scope.chgDocStatus0 = function(docId){
    	
    	MU.conFirm('不通过','您确定不通过审核吗？',function(){
    		 $http({
                 url:"chgDocStatus",
                 method: "post",
                 params: {
                 	docId:docId,
                 	status:'0'
                 }
             }).success(function(data, status, headers, config) {
            	  $scope.reload();
             }).error(function(data, status, headers, config) {
            	 MU.msgTips("error", '审核失败!');
             });
    	});
    	
    };
    
}]);



