angular.module("techStorageSpecsModule", [])
	.controller("techStorageSpecsController", [ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
		$scope.unitPrice = 0;                            //每GB每月单价，从服务器加载
		$scope.applyStorageSize = 20;                    //云存储大小
		$scope.applyServiceDuration = 1;                 //申请的时长
		$scope.applyAmount = 1;                          //申请的数量
		$scope.totalPrice = 0;
		$scope.isSubmitting = false;                     //是否正在提交
		
		// 选择时长
		$(function() {
			$("#serviceDuration").siblings("ul").find("li").on("click", function() {
				var value = $(this).attr("data-value");
				$scope.applyServiceDuration = parseInt(value);
				$scope.calcTotalPrice();
				$scope.$apply();
			});
		});
		
		// 选择数量
		$(function() {
			$("#amount").siblings("ul").find("li").on("click", function() {
				var amount = $(this).attr("data-value");
				$scope.applyAmount = parseInt(amount);
				$scope.calcTotalPrice();
				$scope.$apply();
			});
		});
		
		// 计算价格
		$scope.calcTotalPrice = function() {
			var size = parseInt($scope.applyStorageSize);
			if (isNaN(size)) {
				return;
			}
			var price = $scope.unitPrice * size * $scope.applyAmount * $scope.applyServiceDuration;
			$scope.totalPrice = parseInt(price);
		};
		
		// 提交申请
		$scope.submitApply = function() {
			// applyStorageSize
			if ($scope.applyStorageSize == "") {
				MU.msgTips("warn", "请输入存储大小", 1200);
				return;
			}
			if (isNaN(parseInt($scope.applyStorageSize)) || !/^\d+$/.test($scope.applyStorageSize)) {
				MU.msgTips("warn", "存储大小必须为整数", 1200);
				return;
			}
			if (parseInt($scope.applyStorageSize) < 20 || parseInt($scope.applyStorageSize) > 20000) {
				MU.msgTips("warn", "存储大小必须为20-20000GB", 1200);
				return;
			}

			$scope.calcTotalPrice();
			
			// 防重复提交处理
			if ($scope.isSubmitting) {
				return;
			}
			$scope.isSubmitting = true;
			
			// 提交
			$.ajax({
				url: "submitApply",
				type: "post",
				dataType: "json",
				data: {
					size            : $scope.applyStorageSize + "GB",
					serviceDuration : $scope.applyServiceDuration,
					amount          : $scope.applyAmount,
					totalPrice      : $scope.totalPrice
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						MU.msgTips("success", "申请成功", 1200);
						$timeout(function() {
							window.location.href = "applyList";
						}, 1200);
					} else {
						MU.msgTips("error", resp.message, 1200);
						$scope.isSubmitting = false;
						$scope.$apply();
					}
				},
				error: function() {
					MU.msgTips("error", "提交申请失败，请稍后重试", 1200);
					$scope.isSubmitting = false;
					$scope.$apply();
				}
			});
		};
		
		// 查询配置
		$.ajax({
			url: "queryConfig",
			type: "post",
			dataType: "json",
			data: {},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var p = resp.data.unitPricePerGBMonth;
					if (!p) {
						MU.msgTips("error", "查询申请配置失败，请稍后重试");
					}
					$scope.unitPrice = p / 100.0;
					$scope.calcTotalPrice();
					$scope.$apply();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "查询申请配置失败，请稍后重试");
			}
		});
		
	}]);

