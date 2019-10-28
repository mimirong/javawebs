angular.module("projectModule", [ ]).controller("projectController",[ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
    var PAGE_SIZE = 10;          //每页显示记录数
    $scope.dataList = [];        //分页数据
    $scope.page = 1;             //当前页
    $scope.pageTotal = 0;        //总页数
    $scope.count = 0;  			 //总条数	
    $scope.hasNextPage = false;  //是否有下一页
    $scope.hasPrevPage = false;  //是否有上一页
    $scope.pageButtons = [];     //显示页码按钮
    $scope.directGoto = "";      //bind直接跳转到页码的文本框
    
    var searchType = 0;
    var gtYearAmount = '';
    var ltYearAmount = '';
    var ltTotalAmount = '';
    var gtTotalAmount = '';
    var projectArea = '';
    var startYear = 0;
    var endYear = 0;
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
    
    $("#btnSearch").on("click", function() {
    	searchType = 0;
    	  $scope.reload();
	});
	$("input.queryItem").on("keydown", function(e) {
		if (e.keyCode == 13) {
		  searchType = 0;
		  $scope.reload();
		}
	});
	
	var doubleValide = /^\d+(\.\d+)?$/;
	
	
	
	 $('a.exportExcel').on('click',function() {
		var projects = '';
		$('tbody').find('label.checked').each(function() {  
			projects = projects + $(this).attr("data-row-id") + ',';  
        });  
		if(!projects || projects == ''){
			MU.msgTips("error", "请勾选要导出的项目", 1200);
			return false;
		}
		var url = contextPath + "/features/PmProject/exportExcel?projects=" +projects;
		window.open(url);
	});
    
    $('a.advanceSearch').on('click',function(){
    	 MU.alert($("#advanceSearch").html(),650,'按条件筛选');
    	 
    	 //恢复之前查询数据
    	 if(projectArea != ''){
    		 $('div.alert span.projectArea select option[value="'+projectArea+'"]').attr("selected","selected") ;
    		 $('div.alert span.startYear select option[selected="selected"]').removeAttr('selected') ;
    		 $('div.alert span.endYear select option[selected="selected"]').removeAttr('selected') ;
    		 $('div.alert span.startYear select option:contains('+startYear+')').attr("selected","selected") ;
    		 $('div.alert span.endYear select option:contains('+endYear+')').attr("selected","selected") ;
    		 $('div.alert input.gtYearAmount').val(gtYearAmount);
    		 $('div.alert input.ltYearAmount').val(ltYearAmount);
    		 $('div.alert input.gtTotalAmount').val(gtTotalAmount);
    		 $('div.alert input.ltTotalAmount').val(ltTotalAmount);
    	 }
    	 
    	 $('div.alert span.projectArea').children('select').selectList();
    	 $('div.alert span.startYear').children('select').selectList();
    	 $('div.alert span.endYear').children('select').selectList();
    		$('a.submitSearch').on('click',function(){
    			searchType = 1;
    			projectArea = $("span.projectArea li.cur").attr('data-value');
    			startYear =  $("div.alert span.startYear li.cur").html();
    			endYear =  $("div.alert span.endYear li.cur").html();
    			 
    		 
    			
    			if($.trim($('input.gtYearAmount').val())){
    				 
    				if(!doubleValide.test($.trim($('input.gtYearAmount').val()))) {
    					MU.msgTips("error", "请填写正确的投资额数字", 1200);
        				return false;
    				}
    				 
    				gtYearAmount = $.trim($('input.gtYearAmount').val());
    			}else{
    				gtYearAmount = '';
    			}
    			
    			
    			if($.trim($('input.ltYearAmount').val())){
    				if(!doubleValide.test($.trim($('input.ltYearAmount').val()))) {
    					MU.msgTips("error", "请填写正确的投资额数字", 1200);
        				return false;
    				}
    				ltYearAmount = $.trim($('input.ltYearAmount').val());
    			}else{
    				ltYearAmount = '';
    			}
    			
    			if($.trim($('input.gtTotalAmount').val())){
    				
    				if(!doubleValide.test($.trim($('input.gtTotalAmount').val()))) {
    					MU.msgTips("error", "请填写正确的投资额数字", 1200);
        				return false;
    				}
    			 
    				gtTotalAmount = $.trim($('input.gtTotalAmount').val());
    			}else{
    				gtTotalAmount = '';
    			}
    			
    			if($.trim($('input.ltTotalAmount').val())){
    				if(!doubleValide.test($.trim($('input.ltTotalAmount').val()))) {
    					MU.msgTips("error", "请填写正确的投资额数字", 1200);
        				return false;
    				}
    				ltTotalAmount = $.trim($('input.ltTotalAmount').val());
    			}else{
    				ltTotalAmount = '';
    			}
    			 $scope.reload();
    			 MU.close(this);
    		});
    });
    

    // reload
    $scope.reload = function() {
    	var now = new Date();
        $http({
            url: "listProject",
            method: "get",
            params: {
            	searchType : searchType,
            	rd:now.getTime(),
            	projectName: $('#projectName').val(),
                 gtYearAmount : gtYearAmount ,
                 gtTotalAmount : gtTotalAmount,
                 ltYearAmount : ltYearAmount ,
                 ltTotalAmount : ltTotalAmount,
                 projectArea : projectArea, 
                 startYear : startYear,
                 endYear : endYear,
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
         
    		setTimeout(function() {
    			var rows = $('.btnSelect');
        		var all = $('.btnSelectAll');
	    		all.on("click", function() {
	    			setTimeout(function() {
	    				if (all.hasClass("checked")) {
	    					rows.addClass("checked");
	    				} else {
	    					rows.removeClass("checked");
	    				}
	    			}, 10);
	    		});
	    		
	    		rows.on("click", function() {
	    			setTimeout(function() {
	    				if (rows.length == $(".btnSelect.checked").length) {
	    					all.addClass("checked");
	    				} else {
	    					all.removeClass("checked");
	    				}
	    			}, 10);
	    		});
    		
    		},100);
            
        }).error(function(data, status, headers, config) {
            alert("查询失败");
        });
    };
    
    
    
    // 初始化
    $scope.reload();
   
    $scope.deleteProject = function(projectId){
    	
    	MU.conFirm('删除','您确定要删除吗？',function(){
    		 $http({
                 url:"deleteProject",
                 method: "post",
                 params: {
                	 projectId:projectId,
                 }
             }).success(function(data, status, headers, config) {
            	  $scope.reload();
             }).error(function(data, status, headers, config) {
            	 MU.msgTips("error", '删除失败!');
             });
    	});
    	
    };
    
}]);



