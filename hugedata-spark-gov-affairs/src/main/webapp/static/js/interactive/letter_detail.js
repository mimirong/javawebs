angular.module("letterDetailModule", [])

// Controller
.controller("letterDetailController", [ "$scope", function($scope) {

	$.ajax({
		url : "letterDetailData",
		type : "post",
		dataType : "json",
		data : {messageId:messageId},
		success : function(resp) {
			$scope.messageDetail = resp.data;
			$scope.$apply();
		},
		error : function() {
			MU.msgTips("error", "查询明细失败", 1200);
		}
	});

} ]);