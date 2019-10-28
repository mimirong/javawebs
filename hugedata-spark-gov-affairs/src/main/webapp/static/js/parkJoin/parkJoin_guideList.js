angular.module("parkJoinGuideListModule", [ "sparkGlobal" ])

//Controller
.controller("parkJoinGuideListController", ["$scope", function($scope) {
	$scope.isLoading = false;					//是否显示正在加载
	$scope.page = { start: 0, length: 20 };		//分页信息
	$scope.pageButtons = [];                    //显示的分页按钮

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
			url: contextPath + "/parkJoin/listGuideData",
			type: "post",
			dataType: "json",
			data: {
				start: $scope.page.start,
				length: $scope.page.length
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

                // 拼接预览图路径
                for (var i = 0, size = resp.data.length; i < size; i++) {
                    var coverFileId = resp.data[i].coverFileId;
                    var coverFileName = resp.data[i].coverFileName;
                    if (coverFileId != null && coverFileName != null) {
                        var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                        resp.data[i].imgSrc = imgSrc;
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