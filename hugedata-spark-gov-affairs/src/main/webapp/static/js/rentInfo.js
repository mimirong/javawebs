angular.module("rentInfoModule", [])
	.controller("rentInfoController", [ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
		var PAGE_SIZE = 12;          //每页显示记录数
		$scope.dataList = [];        //分页数据
		$scope.page = 1;             //当前页
		$scope.pageTotal = 0;        //总页数
		$scope.hasNextPage = false;  //是否有下一页
		$scope.hasPrevPage = false;  //是否有上一页
		$scope.pageButtons = [];     //显示页码按钮
		$scope.directGoto = "";      //bind直接跳转到页码的文本框
		$scope.isRent = false;       //true:已租, false:招租
		
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
		
		// switch isRent
		$scope.switchIsRent = function(b) {
			$scope.isRent = b;
			$scope.reload();
		};
		
		// reload
		$scope.reload = function() {
			$http({
				url: contextPath + "/rentInfo/query",
				method: "get",
				params: {
					isRent: $scope.isRent,
					pageStart: PAGE_SIZE * ($scope.page - 1),
					pageSize: PAGE_SIZE
				},
			}).success(function(data, status, headers, config) {
				 info = data.data;
				 $scope.dataList = info.data;
				 $scope.page = info.page;
				 $scope.pageTotal = info.pageCount;
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