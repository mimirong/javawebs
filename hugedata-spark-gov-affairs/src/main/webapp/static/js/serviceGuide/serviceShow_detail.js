angular.module("serviceDetailModule", [ ])

.filter("businessTypeName", function() {
	return function(val) {
		var TYPE_NAMES = {
		    "onLine": "在线办理",
		    "byWindow": "窗口办理"
		};
		return TYPE_NAMES[val] ? TYPE_NAMES[val] : val;
	};
})

.filter("statusText", function() {
	return function(val) {
		switch (val) {
			case "110": 
				return "待预审";
			case "120":
			case "130":
			case "140":
			case "150":
			case "160":
			case "210":
			case "220":
				return "办理中";
			case "211":
				return "已办结";
			case "221":
			case "231":
				return "废弃件";
			default:
				return "未知:" + val;
		}
	};
})

.filter("serviceOpText", function() {
	return function(val) {
		var text = {
			"110": "预审",
			"120": "初审",
			"130": "复审",
			"140": "决定",
			"150": "主任决定",
			"160": "书记决定",
			"210": "最终确认",
			"220": "最终确认",
			"211": "",
			"221": "",
			"231": ""
		}[val + ""];
		return (text ? text : val);
	};
})

//Controller
.controller("serviceDetailController", ["$scope", function($scope) {
	$scope.businessDetail = null;
	$scope.attachments = null;
	$scope.isDetailed = false;
	$scope.procList = [];
	
	$scope.simpleStatus = {
		created: false,
		inProgress: false,
		finished: false,
		rejected: false
	};
	
	// loadData
	$scope.loadData = function() {
		$.ajax({
			url: "serviceShowDeatil",
			type: "post",
			dataType: "json",
			data: {
				serviceId: serviceId
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					$scope.businessDetail = resp.data.serviceInfo;
					$scope.attachments = processAttachments(resp.data.attConfigs, resp.data.attachments);
					$scope.isDetailed = (resp.data.serviceInfo.createUserId + "" == window.login.userId);

					if (resp.data.serviceInfo.finishDocFileId) {
						resp.data.serviceInfo.finishDocUrl = DownloadService.buildUrl(
								resp.data.serviceInfo.finishDocFileId, resp.data.serviceInfo.finishDocFileName);
					}
					if (resp.data.serviceInfo.acceptNoticeFileId) {
						resp.data.serviceInfo.acceptNoticeUrl = DownloadService.buildUrl(
								resp.data.serviceInfo.acceptNoticeFileId, resp.data.serviceInfo.acceptNoticeFileName);
					}
					
					$scope.procList = resp.data.procList;
					
					// 简化状态
					switch (resp.data.serviceInfo.status) {
						case "110": 
							$scope.simpleStatus = { created: true, inProgress: false, finished: false, rejected: false };
							break;
						case "120":
						case "130":
						case "140":
						case "150":
						case "160":
						case "210":
						case "220": 
							$scope.simpleStatus = { created: false, inProgress: true, finished: false, rejected: false };
							break;
						case "211":
							$scope.simpleStatus = { created: false, inProgress: false, finished: true, rejected: false };
							break;
						case "221":
						case "231":
							$scope.simpleStatus = { created: false, inProgress: false, finished: false, rejected: true };
							break;
					}
					
					$scope.$apply();
				} else {
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.msgTips("error", "加载数据失败");
				$scope.isLoading = false;
				$scope.$apply();
			}
		});
	}
	
	// 处理附件
	function processAttachments(acList, attList) {
		var acMap = {};
		$.each(acList, function(i, ac) {
			acMap[ac.attConfigId] = ac;
			ac.files = [];
			ac.fileCount = 0;
		});
		
		$.each(attList, function(i, item) {
			var ac = acMap[item.attConfigId];
			if (!ac) {
				ac = {
					attConfigId: item.attConfigId,
					attConfigName: item.attConfigName,
					remark: item.remark,
					files: [],
					fileCount: 0
				};
				acList.push(ac);
				acMap[ac.attConfigId] = ac;
			}

			ac.files.push({
				fileId: item.fileId,
				fileName: item.fileName,
				fileUrl: DownloadService.buildUrl(item.fileId, item.fileName)
			});
			ac.approveStatus = item.approveStatus;
			ac.remark = item.remark;

			if (item.fileId != "NOFILE") {
				ac.fileCount++;
			}
		});
		return acList;
	}
	
	// showAttFiles
	$scope.showAttFiles = function(e, attConfig) {
//		var content = $(e.target).siblings(".attachmentPopupContentWrapper").html();
//		MU.alert(content, 600, "附件");
	};
	
	// 重新提交
	$scope.recreate = function() {
		var url = "onlineTransact?guideId=" + $scope.businessDetail.guidecode
				+ "&fromServiceId=" + $scope.businessDetail.serviceId;
		location.href = url;
	};

	// 撤销办事
	$scope.cancelService = function() {
		MU.conFirm("撤销", "确认撤销办事？", function() {
			$.ajax({
				url: "cancelService",
				type: "post",
				dataType: "json",
				data: {
					serviceId: serviceId
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						MU.msgTips("success", "撤销成功");
						setTimeout(function() {
							location.reload();
						}, 900);
					} else {
						MU.msgTips("error", resp.message);
					}
				},
				error: function() {
					MU.msgTips("error", "撤销失败，请稍后重试");
				}
			});
		});
	};
	
	// init
	$scope.loadData();
	
}]);