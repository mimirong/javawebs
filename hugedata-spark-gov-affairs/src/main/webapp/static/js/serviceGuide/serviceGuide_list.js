angular.module("serviceGuideListModule", [ "sparkGlobal" ])

// Filter: deptName
.filter("deptName", function() {
	return function(val) {
		var DEPT_NAMES = {
		    "1": "经济发展局",
		    "2": "国土规划局",
		    "3": "工程建设局",
		    "4": "社会事务局",
		    "5": "招商合作局",
		    "6": "财政分局",
		    "7": "办公室",
		    "8": "党群纪检绩效办"
		};
		return DEPT_NAMES[val] ? DEPT_NAMES[val] : val;
	};
})

//Controller
.controller("serviceGuideListController", ["$scope", function($scope) {
	$scope.isLoading = false;					//是否显示正在加载
	$scope.page = { start: 0, length: 20 };		//分页信息
	$scope.pageButtons = [];                    //显示的分页按钮
	if(typeof(deptCode)!="undefined" && deptCode){
		$scope.deptCode = deptCode;  
	}
	else{
		$scope.deptCode = "7";                      //当前显示的部门
	}
	
	// changeDept
	$scope.changeDept = function(code,$event) {
		$scope.deptName = $($event.target).text()
		$scope.deptCode = code;
		$scope.page.start = 0;
		$scope.loadData();
	};

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
				dept: $scope.deptCode
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