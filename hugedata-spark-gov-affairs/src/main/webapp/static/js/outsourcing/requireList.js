angular.module("requireModule", [ ]).controller("requireController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
    var PAGE_SIZE = 15;          //每页显示记录数
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
    	if($.trim($('#typeCodeBegin').val()) != '200'){
    		 MU.alert($("#addDoc").html(),650,'添加手续文件');
    	}else{
    		 MU.alert($("#addDoc").html(),650,'添加文档资料');
    	}
    	
    	 $('div.alert span').children('select').selectList();
    	 $("input[type='file']").on('change',function(){
    	   	 
    	   	 $(this).addClass("addFiled");
    	   	 
    	   });
    	 
    		$('a.submitAddDoc').on('click',function(){
    			
    			if($.trim($('#typeCodeBegin').val()) != '200'){
    				if($("span.docType li.cur").attr('data-value') == '-1'){
        				MU.msgTips("error", "请选择文件类型", 1200);
        				return false;
        			}
    	    	}else{
    	    		if($("span.docType li.cur").attr('data-value') == '-1'){
        				MU.msgTips("error", "请选择资料类型", 1200);
        				return false;
        			}
    	    	}
    			
    			
    			
    			$("input[name='typeCode']").val($("span.docType li.cur").attr('data-value'));
    			$("input[name='typeName']").val($("span.docType li.cur").attr('title'));
    			var fileName = $(this).closest("form").find("input[name='fileName']").val();
    			
    			var approveCode = $(this).closest("form").find("input[name='approveCode']").val();
    			var fileContent = $(this).closest("form").find("input[name='fileContent']").hasClass('addFiled');
    			
    			if($.trim($('#typeCodeBegin').val()) != '200'){
    				if(!fileName ){
        				MU.msgTips("error", '请填写文件名称!');
        				return false;
        			}
    				if(  !approveCode ){
        				MU.msgTips("error", '请填写批复文号!');
        				return false;
        			}
    			}else{
    				if(!fileName ){
        				MU.msgTips("error", '请填写资料名称!');
        				return false;
        			}
    			}

    			if( !fileContent){
    				MU.msgTips("error", '请选择上传文件!');
    				return false;
    			}
    			
    			$(this).closest('form').submit();
    			return false;
    		});
    });
    
    $(" div.se-sort  a , div.se-filter div.conbox a").on('click',function(){
    	 $scope.reload();
    });
    
    // reload
    $scope.reload = function() {
    	var now = new Date();
        $http({
            url: contextPath + "/outsourcing/listRequires",
            method: "get",
            params: {
            	searchWord:$.trim($("#searchWord").val()),
            	rd:now.getTime(),
            	orderType:$('div.se-sort  a.on').text(),
            	requireArea:$('div.se-filter div.conbox a.on').text(),
                start: PAGE_SIZE * ($scope.page - 1),
                length: PAGE_SIZE
            }
        }).success(function(data, status, headers, config) {
            $scope.dataList = data.data;
            $scope.page = data.page;
            $scope.pageTotal = data.pageCount;
            $scope.count = data.recordsTotal;
            $scope.hasNextPage = (data.page < data.pageCount);
            $scope.hasPrevPage = (data.page > 1);

            // 分页按钮处理
            $scope.pageButtons = [];
            for (var i = Math.max(1, $scope.page - 2); i <= Math.min(data.pageCount, $scope.page + 2); i++) {
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
                 url: contextPath + "/pmProject/deleteDoc",
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
    
 $scope.modifyDoc = function(doc){
	 
	if($.trim($('#typeCodeBegin').val()) != '200'){
		 MU.alert($("#modifyDoc").html(),650,'修改手续文件');
	}else{
		 MU.alert($("#modifyDoc").html(),650,'修改文档资料');
	}
	$("div.alert form").find("input[name='approveCode']").val(doc.approveCode);
	$("div.alert form").find("input[name='fileName']").val(doc.fileName);
	$("div.alert form").find("input[name='docId']").val(doc.docId);
	if(doc.fileId){
		$("div.alert form li.fileName").after('<li class="clearfix existFile"><label class="tit">已上传文件：</label><a href="'+doc.fileId+'" style="color: #107aee;">'+doc.fileName+'</a></li>')
	}
	
	$('div.alert span select option[value="'+doc.typeCode+'"]').attr("selected","selected") ;
	 $('div.alert span').children('select').selectList();
	 $("input[type='file']").on('change',function(){
	   	 
	   	 $(this).addClass("addFiled");
	   	 
	   });
	 
		$('a.submitModifyDoc').on('click',function(){
			
			if($.trim($('#typeCodeBegin').val()) != '200'){
				if($("span.docType li.cur").attr('data-value') == '-1'){
    				MU.msgTips("error", "请选择文件类型", 1200);
    				return false;
    			}
	    	}else{
	    		if($("span.docType li.cur").attr('data-value') == '-1'){
    				MU.msgTips("error", "请选择资料类型", 1200);
    				return false;
    			}
	    	}
			
			
			
			$("input[name='typeCode']").val($("span.docType li.cur").attr('data-value'));
			$("input[name='typeName']").val($("span.docType li.cur").attr('title'));
			var fileName = $(this).closest("form").find("input[name='fileName']").val();
			
			var approveCode = $(this).closest("form").find("input[name='approveCode']").val();
			var fileContent = $(this).closest("form").find("input[name='fileContent']").hasClass('addFiled');
			
			if($.trim($('#typeCodeBegin').val()) != '200'){
				if(!fileName ){
    				MU.msgTips("error", '请填写文件名称!');
    				return false;
    			}
				if(  !approveCode ){
    				MU.msgTips("error", '请填写批复文号!');
    				return false;
    			}
			}else{
				if(!fileName ){
    				MU.msgTips("error", '请填写资料名称!');
    				return false;
    			}
			}
		 
			var existFile = $("div.alert form li.existFile");
				if(existFile.length == 0 && !fileContent){
					MU.msgTips("error", '请选择上传文件!');
					return false;
				}
			 

			
			
			$(this).closest('form').submit();
			return false;
		});
		
    };

}]);



