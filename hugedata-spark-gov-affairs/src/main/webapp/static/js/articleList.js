angular.module("articleListModule", [ "sparkGlobal" ])

//Controller
.controller("articleListController", ["$scope", function($scope) {
	$scope.isLoading = false;					//是否显示正在加载
	$scope.page = { start: 0, length: 20 };		//分页信息
	$scope.pageButtons = [];                    //显示的分页按钮
	$scope.categoryId = window.categoryId; 		//栏目ID
	$scope.categoryName = window.categoryMap[$scope.categoryId]; //栏目名
	$scope.isNeedContent = window.isNeedContent;//是否需要查询内容
	
	// changeCategory
	$scope.changeCategory = function(categoryId, $event) {
		$scope.categoryId = categoryId;
		$scope.categoryName = window.categoryMap[categoryId];
		location.href = "?category=" + categoryId;
		//$scope.page.start = 0;
		//$scope.loadData();
	};

	// gotoPage
	$scope.gotoPage = function(p) {
		if (p > $scope.page.pageCount) {
			return;
		}
		$scope.page.start = $scope.page.length * (p - 1);
		$scope.loadData();
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
	
	// loadData
	$scope.loadData = function() {
		$scope.isLoading = true;
		$.ajax({
			url: contextPath + "/article/listArticles",
			type: "post",
			dataType: "json",
			data: {
				start: $scope.page.start,
				length: $scope.page.length,
				categoryId: $scope.categoryId,
                isNeedContent: $scope.isNeedContent
			},
			success: function(resp) {
				resp.start = $scope.page.start;
				resp.length = $scope.page.length;
				$scope.isLoading = false;
				$scope.page = resp;

				 // 分页按钮处理
				 $scope.pageButtons = [];
				 for (var i = Math.max(1, $scope.page.page - 2); 
				 		i <= Math.min($scope.page.pageCount, $scope.page.page + 2);
				 		i++) {
					 $scope.pageButtons.push(i);
				 }

                // 拼接预览图路径&&清除内容的HTML标签
                for (var i = 0, size = resp.data.length; i < size; i++) {
				 	// 拼接预览图片路径
                    var coverFileId = resp.data[i].coverFileId;
                    var coverFileName = resp.data[i].coverFileName;
                    if (coverFileId != null && coverFileName != null) {
                        var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                        resp.data[i].imgSrc = imgSrc;
                    }

                    if ($scope.isNeedContent) {
                        // 清除正文内容的HTML标签
                        resp.data[i].content = resp.data[i].content.replace(/<[^>]+>/g, "");
                        resp.data[i].content = resp.data[i].content.replace(/&nbsp;/gi, "");
                        resp.data[i].content = resp.data[i].content.replace(/[ ]/g, "");
                        resp.data[i].content = resp.data[i].content.replace(/[\r\n]/g, "");
                    }
                }
				 
				$scope.$apply();
			},
			error: function() {
				alert("加载数据失败");
				$scope.isLoading = false;
				$scope.$apply();
			}
		});
	}

	// init
	$scope.loadData();
	
}]);