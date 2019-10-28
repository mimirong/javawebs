angular.module("billsModule", [])
	.filter("billTypeText", function() {
		return function(value) {
			var text = {
					"RENT": "租金",
					"EARNEST": "保证金",
					"WATER": "水费",
					"POWER": "电费",
					"PROPERTY": "物业费"
			}[value];
			if (!text || text == "") {
				return value;
			}
			return text;
		}
	})
	.filter("statusText", function() {
		return function(value) {
			var text = {
					"CREATED": "待缴费",
					"FINISHED": "已缴费"
			}[value];
			if (!text || text == "") {
				return value;
			}
			return text;
		};
	})
	.controller("billsController", [ "$scope", "$http", "$filter", function($scope, $http, $filter) {
		var PAGE_SIZE = 12;          //每页显示记录数
		$scope.dataList = [];        //分页数据
		$scope.page = 1;             //当前页
		$scope.pageTotal = 0;        //总页数
		$scope.hasNextPage = false;  //是否有下一页
		$scope.hasPrevPage = false;  //是否有上一页
		$scope.pageButtons = [];     //显示页码按钮
		$scope.directGoto = "";      //bind直接跳转到页码的文本框
		$scope.detailsBill = {};
		$scope.queryStatus = $("#queryStatusSelect select").val();
		
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
		
		// 切换messageType处理
		$("#messageTypeSelect li").on("click", function(e) {
			$scope.messageType = $(e.target).attr("data-value");
			$scope.page = 1;
			$scope.reload();
		});
		
		// 显示详情
		$scope.showDetails = function(bill) {
			$scope.detailsBill = bill;
			MU.mask();
			MU.center("#detailsPanel");
			$("#detailsPanel").show();
		};
		
		// 状态选择
		$(function() {
			$("#queryStatusSelect ul li").on("click", function(e) {
				var value = $(e.target).attr("data-value");
				$scope.queryStatus = value;
				$scope.reload();
			});
		});
		
		// reload
		$scope.reload = function() {
			$http({
				url: contextPath + "/fees/listBills",
				method: "get",
				params: {
					status: $scope.queryStatus,
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