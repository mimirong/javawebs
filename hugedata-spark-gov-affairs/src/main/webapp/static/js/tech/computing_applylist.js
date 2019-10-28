(function() {
    $(".datePicker").datepicker();
	$(".datePicker").datepicker("disable");
})();

angular.module("techComputingApplyListModule", [])
	.controller("techComputingApplyListController", [ "$scope", "$http", "$filter", function($scope, $http, $filter) {
		var PAGE_SIZE = 12;          //每页显示记录数
		$scope.dataList = [];        //分页数据
		$scope.page = 1;             //当前页
		$scope.pageTotal = 0;        //总页数
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
		
		// 显示详情弹框
		$scope.showDetails = function(apply) {
			$scope.viewApply = apply;

			MU.mask();
			MU.center("#viewApplyPanel");
			$("#viewApplyPanel").show();
		};
		
		// 删除申请
		$scope.deleteApply = function(apply) {
			MU.conFirm("删除", "确认删除申请?", function() {
				$http({
					url: "deleteApply",
					method: "post",
					params: {
						applyId: apply.applyId
					},
				}).success(function(data, status, headers, config) {
					console.log(data);
					if (data.result == 0) {
						$scope.reload();
						MU.msgTips("success", "删除成功", 1200);	
					} else {
						MU.msgTips("error", data.message, 1200);
					}
				}).error(function(data, status, headers, config) {
					MU.msgTips("error", "删除失败", 1200);
				});
			});
		};
		
		// reload
		$scope.reload = function() {
			$http({
				url: contextPath + "/outsourcing/tech/computing/queryApplyList",
				method: "post",
				params: {
					pageStart: PAGE_SIZE * ($scope.page - 1),
					pageSize: PAGE_SIZE,
					_t: new Date().getTime()
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
				 
				 // 处理spec
				 for (var i = 0; i < $scope.dataList.length; i++) {
					 $scope.dataList[i].specs = JSON.parse($scope.dataList[i].extraData);
				 }
				 
			}).error(function(data, status, headers, config) {
				alert("查询失败");
			});
		};
		
		// 初始化
		$scope.reload();
		
	}]);