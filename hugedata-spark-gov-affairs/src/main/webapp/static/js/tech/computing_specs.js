angular.module("techComputingSpecsModule", [])
	.controller("techComputingSpecsController", [ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
		
		// 定义所有套餐
		$scope.specs = [];                      //从服务端加载所有套餐信息
		$scope.applyingSpec = {};               //正在申请的套餐信息
		$scope.applyServiceDuration = 1;        //申请的时长
		$scope.applyAmount = 1;                 //申请的数量
		$scope.isSubmitting = false;            //是否正在提交
		$scope.curTab = 1;                      //当前Tab页 (1:套餐 2:自定义)
		$scope.directApplyDuration = [1,1,1];   //从套餐页面申请时所选的时长
		
		// 查询套餐信息
		$.ajax({
			url: "querySpecs",
			type: "post",
			dataType: "json",
			data: {},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var specs = [];
					$.each(resp.data, function(i, spec) {
						var sd = JSON.parse(spec.specData);
						specs.push({
							specId: spec.specId,
							specName: spec.specName,
							name: spec.specName,
							price: spec.price,
							cpu: sd.cpu,
							memory: sd.memory,
							disk: sd.disk,
							bandwidth: sd.bandwidth
						});
					});
					$scope.specs = specs;
					$scope.$apply();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "查询云主机套餐信息失败，请稍后重试");
			}
		});
		
		// 套餐页时长选择
		$(function() {
			$(".directDurationSelect").siblings("ul").find("li").on("click", function() {
				var select = $(this).parent("ul").siblings("select");
				var index = parseInt(select.attr("data-index"));
				var value = $(this).attr("data-value");
				$scope.directApplyDuration[index] = value;
			});
		});
		
		// 显示套餐
		$scope.showSpecs = function() {
			$scope.curTab = 1;
		};
		
		// 显示申请弹框
		$scope.showApplyDialog = function(spec) {
			spec = { cpu:"", memory:"", disk:"", bandwidth:"" };
			$scope.applyingSpec = spec;
			$scope.applyServiceDuration = 1;
			$scope.applyAmount = 1;
			$scope.curTab = 2;
		};
		
		// 选择CPU
		function initCpuOptions(options) {
			var m = [ '<option value="">请选择</option>' ];
			$.each(options, function(i, op) {
				m.push('<option value="' + op + '">' + op + '</option>');
			});
			$("#specCpu").html(m.join("")).selectList();

			$("#specCpu").siblings("ul").find("li").on("click", function() {
				$scope.applyingSpec.cpu = $(this).attr("data-value");
				$scope.$apply();
			});
		}
		
		// 选择内存
		function initMemoryOptions(options) {
			var m = [ '<option value="">请选择</option>' ];
			$.each(options, function(i, op) {
				m.push('<option value="' + op + '">' + op + '</option>');
			});
			$("#specMemory").html(m.join("")).selectList();

			$("#specMemory").siblings("ul").find("li").on("click", function() {
				$scope.applyingSpec.memory = $(this).attr("data-value");
				$scope.$apply();
			});
		}
		
		// 选择硬盘
		function initDiskOptions(options) {
			var m = [ '<option value="">请选择</option>' ];
			$.each(options, function(i, op) {
				m.push('<option value="' + op + '">' + op + '</option>');
			});
			$("#specDisk").html(m.join("")).selectList();

			$("#specDisk").siblings("ul").find("li").on("click", function() {
				$scope.applyingSpec.disk = $(this).attr("data-value");
				$scope.$apply();
			});
		}
		
		// 选择带宽
		function initBandwidthOptions(options) {
			var m = [ '<option value="">请选择</option>' ];
			$.each(options, function(i, op) {
				m.push('<option value="' + op + '">' + op + '</option>');
			});
			$("#specBandwidth").html(m.join("")).selectList();

			$("#specBandwidth").siblings("ul").find("li").on("click", function() {
				$scope.applyingSpec.bandwidth = $(this).attr("data-value");
				$scope.$apply();
			});
		}

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
				$scope.$apply();
			});
		});
		
		// 提交申请
		$scope.submitApply = function() {
			//applyAmount
			if ($scope.applyAmount == "") {
				MU.msgTips("warn", "请输入数量", 1200);
				return;
			}

			// cpu
			if ($scope.applyingSpec.cpu == "") {
				MU.msgTips("warn", "请选择CPU配置", 1200);
				return;
			}
			// memory
			if ($scope.applyingSpec.memory == "") {
				MU.msgTips("warn", "请选择内存配置", 1200);
				return;
			}
			// disk
			if ($scope.applyingSpec.disk == "") {
				MU.msgTips("warn", "请选择硬盘配置", 1200);
				return;
			}
			// bandwidth
			if ($scope.applyingSpec.bandwidth == "") {
				MU.msgTips("warn", "请选择带宽配置", 1200);
				return;
			}
			
			// 防重复提交处理
			if ($scope.isSubmitting) {
				return;
			}
			$scope.isSubmitting = true;
			
			$scope.applyingSpec.name = "自定义";
			
			// 提交
			$.ajax({
				url: "submitApply",
				type: "post",
				dataType: "json",
				data: {
					spec: JSON.stringify($scope.applyingSpec),
					serviceDuration: $scope.applyServiceDuration,
					amount: $scope.applyAmount
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						MU.msgTips("success", "申请成功", 1200);
						$timeout(function() {
							window.location.href = "applyList";
						}, 1200);
					} else {
						MU.msgTips("warn", resp.message, 1200);
						$timeout(function() {
							$scope.isSubmitting = false;
						}, 1200);
					}
				},
				error: function() {
					MU.msgTips("warn", "提交申请失败，请稍后重试", 1200);
					$timeout(function() {
						$scope.isSubmitting = false;
					}, 1200);
				}
			});
		};
		
		// 立即申请套餐
		$scope.directApplySpec = function(spec, duration) {
			MU.conFirm("删除", "是否立即申请?", function() {
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
						spec: JSON.stringify(spec),
						serviceDuration: duration,
						amount: 1
					},
					success: function(resp) {
						if (resp && resp.result == 0) {
							MU.msgTips("success", "申请成功", 1200);
							$timeout(function() {
								window.location.href = "applyList";
							}, 1200);
						} else {
							MU.msgTips("warn", resp.message, 1200);
							$timeout(function() {
								$scope.isSubmitting = false;
							}, 1200);
						}
					},
					error: function() {
						MU.msgTips("warn", "提交申请失败，请稍后重试", 1200);
						$timeout(function() {
							$scope.isSubmitting = false;
						}, 1200);
					}
				});
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
					initCpuOptions(resp.data.cpuOptions);
					initMemoryOptions(resp.data.memoryOptions);
					initDiskOptions(resp.data.diskOptions);
					initBandwidthOptions(resp.data.bandwidthOptions);
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "查询申请配置失败，请稍后重试");
			}
		});
		
	}]);

