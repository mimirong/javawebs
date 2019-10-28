angular.module("outsourcingEcommIndexModule", [])
.controller("outsourcingEcommIndexController", ["$scope", function($scope) {
	$scope.biddingsList = [];
	$scope.projectsList = [];
	$scope.outsourcingList = [];
	$scope.financingList = [];
	
	// loadData
	function loadData(categoryId, successCallback) {
		$.ajax({
			url: contextPath + "/article/listArticles",
			type: "post",
			dataType: "json",
			data: {
				start: 0,
				length: 6,
				categoryId: categoryId
			},
			success: function(resp) {
				successCallback(resp);
			},
			error: function() {
				MU.msgTips("error", "加载数据失败");
			}
		});
	};

	// init
	loadData("ECOMM_BIDDINGS", function(resp) {
		$scope.biddingsList = resp.data;
		$scope.$apply();
	});
	loadData("ECOMM_PROJECTS", function(resp) {
		$scope.projectsList = resp.data;
		$scope.$apply();
	});
	loadData("ECOMM_OUTSOURCING", function(resp) {
		$scope.outsourcingList = resp.data;
		$scope.$apply();
	});
	loadData("ECOMM_FINANCING", function(resp) {
		$scope.financingList = resp.data;
		$scope.$apply();
	});
	
}]);