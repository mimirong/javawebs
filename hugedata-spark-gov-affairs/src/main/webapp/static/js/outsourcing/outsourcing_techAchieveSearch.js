angular.module("techAchieveSearchModule", [ "sparkGlobal" ])

//Controller
.controller("techAchieveSearchController", ["$scope", function($scope) {
	$scope.isLoading = false;					//是否显示正在加载
	$scope.page = { start: 0, length: 9 };		//分页信息，一页查9条(3 x 3)
	$scope.pageButtons = [];                    //显示的分页按钮
	$scope.isNeedContent = window.isNeedContent;//是否需要查询内容
    $scope.currProfessionFieldId = "";
    $scope.currAchieveTypeId = "";
    $scope.investmentValumeScope = "";
	
	// changeCategory
	$scope.changeCategory = function(categoryId, $event) {
		$scope.categoryId = categoryId;
		$scope.categoryName = window.categoryMap[categoryId];
		location.href = "?category=" + categoryId;
		//$scope.page.start = 0;
		//$scope.loadData();
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
			url: contextPath + "/outsourcing/listTechAchieve",
			type: "post",
			dataType: "json",
			data: {
				start: $scope.page.start,
				length: $scope.page.length,
                professionFieldId: $scope.currProfessionFieldId,
                achieveTypeId: $scope.currAchieveTypeId,
                investmentValumeScope: $scope.investmentValumeScope,
                searchWord: window.searchWord
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

                // 拼接预览图路径&&清除内容的HTML标签
                for (var i = 0, size = resp.data.length; i < size; i++) {
				 	// 拼接预览图片路径
                    var previewFileId = resp.data[i].fileId;
                    var fileName = resp.data[i].fileName;
                    var monetaryUnit = resp.data[i].monetaryUnit;
                    if (previewFileId != null && fileName != null) {
                        var imgSrc = DownloadService.buildUrl(previewFileId, fileName);
                        resp.data[i].imgSrc = imgSrc;
                        resp.data[i].monetaryUnit = GetMonetaryTextById(monetaryUnit);
                    }
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

    /**
     * 根据专业领域筛选
     * @param e     onClick点击事件
     * @param value 当前被选中的专业领域ID
     */
    $scope.professionFieldSelected = function (e, value) {
        $scope.currProfessionFieldId = value;
        $scope.currAchieveTypeId = ""; // 清空其他条件
        $scope.investmentValumeScope = ""; // 清空其他条件
        $scope.loadData();
    }

    /**
     * 根据成果形式筛选
     * @param e     onClick点击事件
     * @param value 当前被选中的成果类型ID
     */
    $scope.achieveTypeSelected = function (e, value) {
        $scope.currProfessionFieldId = ""; // 清空其他条件
        $scope.currAchieveTypeId = value;
        $scope.investmentValumeScope = ""; // 清空其他条件
        $scope.loadData();
    }

    /**
     * 根据投资金额筛选
     * @param e     onClick点击事件
     * @param value 当前被选中的投资金额区间段
     */
    $scope.investmentValumeScopeSelected = function (e, value) {
        $scope.currProfessionFieldId = ""; // 清空其他条件
        $scope.currAchieveTypeId = "";  // 清空其他条件
        $scope.investmentValumeScope = value;
        $scope.loadData();
    }


    // /**
    //  * 搜索专家名录
    //  */
    // $("#btnSearch").on("click", function() {
    //     window.searchWord = $("#searchWord").val().trim();
    //     if(searchWord != null && searchWord != "") {
    //
    //         // 打开搜索页面
    //         window.location.href = contextPath + '/outsourcing/techAchieveSearch?searchWord='+searchWord;
		// }
    // });

	// init
	$scope.loadData();
	
}]);