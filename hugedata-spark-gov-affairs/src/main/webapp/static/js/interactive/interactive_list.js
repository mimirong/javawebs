angular.module("interactiveListModule", [ ])


//Controller
.controller("interactiveListController", ["$scope", function($scope) {
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
	$scope.onPasswordKeydown=function(e){
		if (e.keyCode == 13) {
			$scope.loadData();
		}
	}
	
	// loadData
	$scope.loadData = function() {
		$scope.isLoading = true;
		$.ajax({
			url: "listData",
			type: "post",
			dataType: "json",
			data: {
				start: $scope.page.start,
				length: $scope.page.length,
				password:$scope.password
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