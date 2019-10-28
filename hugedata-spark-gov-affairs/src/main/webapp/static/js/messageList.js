(function() {
    $(".datePicker").datepicker();
	$(".datePicker").datepicker("disable");
})();

angular.module("messageListModule", [])
	.filter("messageTypeText", function() {
		return function(value) {
			switch (value) {
				case "":
					return "全部";
				case "PARK_JOIN_APPLY":
					return "入园申请";
				case "PARK_QUIT_APPLY":
					return "退园申请";
				case "FINANCING_APPLY":
					return "融资申请";
				case "AP_SERVICE":
					return "在线服务";
				case "PROJECT_MANAGE":
					return "项目管理";
				default:
					return value;
			}
		}
	})
	.controller("messageListController", [ "$scope", "$http", "$filter", function($scope, $http, $filter) {
		$scope.page = { start: 0, length: 12 };		//分页信息
		$scope.pageButtons = [];     //显示页码按钮
		$scope.directGoto = "";      //bind直接跳转到页码的文本框

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
		
		// reload
		$scope.loadData = function() {
			$scope.updateUnreadMark();
			
			$http({
				url: "query",
				method: "get",
				params: {
					messageType: $scope.messageType,
					dateStart: $scope.dateStart,
					dateEnd: $scope.dateEnd,
					pageStart: $scope.page.start,
					pageSize: $scope.page.length,
					random:new Date().getTime().toString(36)
				},
			}).success(function(resp, status, headers, config) {
				resp = resp.data;
				resp.start = $scope.page.start;
				resp.length = $scope.page.length;

				// 添加序号
                for (var i = 0, length = resp.data.length; i < length; i++) {
                    resp.data[i].serialNum = i + 1;
                }

				$scope.isLoading = false;
				$scope.page = resp;

				 // 分页按钮处理
				 $scope.pageButtons = [];
				 for (var i = Math.max(1, $scope.page.page - 2); 
				 		i <= Math.min($scope.page.pageCount, $scope.page.page + 2);
				 		i++) {
					 $scope.pageButtons.push(i);
				 }
			}).error(function(data, status, headers, config) {
				MU.msgTips("error", "查询失败");
			});
		};
		
		// 删除消息
		$scope.deleteMessage = function(messageId) {
			MU.conFirm("删除消息", "确认删除消息?", function() {
				$.ajax({
					url: "deleteMessage",
					type: "post",
					dataType: "json",
					data: {
						messageId: messageId
					},
					success: function(resp) {
						if (resp && resp.result == 0) {
							MU.msgTips("success", "删除成功");
							$scope.loadData();
						} else {
							MU.msgTips("error", resp.message);
						}
					},
					error: function() {
						MU.msgTips("error", "删除失败，请稍后重试");
					}
				});
			});
		};
		
		// viewMessage
		$scope.viewMessage = function(msg,event) {
			$.ajax({
				url: contextPath + "/messages/updateReadStatus",
				type: "post",
				dataType: "json",
				data: {
					messageId: msg.messageId
				},
				success: function(data, status, headers, config) {
					//防止详情页跳回不刷新
				  var mhtml =  $(event.target);
				  var rhtml = mhtml.parent().parent().next();
				  rhtml.html('<span class="grey" ng-if="row.isRead">已查看</span>');  
					switch (msg.messageType) {
						case "PROJECT_MANAGE":
							location.href = contextPath + "/pmProject/detail?projectId=" + msg.referenceId;
							break;
						case "AP_SERVICE":
							location.href = contextPath + "/serviceGuide1/serviceDetail?serviceId=" + msg.referenceId;
							break;
						case "PARK_JOIN_APPLY":
						case "PARK_QUIT_APPLY":
						default:
							// 不跳转页面
							$scope.loadData();
							break;  
					}
				},
				error: function() {
					MU.msgTips("error", "查看消息失败，请稍后重试");
				}
			});
		};
		
		// updateUnreadMark
		$scope.updateUnreadMark = function() {
			$.ajax({
				url: contextPath + "/messages/queryUnreadCount",
				type: "post",
				dataType: "json",
				data: {},
				success: function(resp) {
					if (resp && resp.result == 0) {
						var count = parseInt(resp.data);
						if (isNaN(count)) {
							return;
						}
						if (count > 0) {
							$("#unreadMessageIndicator").show();
						} else {
							$("#unreadMessageIndicator").hide();
						}
					} else {
						MU.msgTips("warn", "查询未读消息数失败: " + resp.message);
					}
				},
				error: function() {
					MU.msgTips("warn", "查询未读消息数失败")
				}
			});
		};
		
		// 初始化
		$scope.loadData();
		
	}]);