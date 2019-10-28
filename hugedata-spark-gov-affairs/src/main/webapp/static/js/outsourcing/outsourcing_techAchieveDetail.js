angular.module("techAchieveDetailModule", [ "sparkGlobal" ])

//Controller
        .controller("techAchieveDetailController", ["$scope", function($scope) {
        $scope.isLoading = false;					//是否显示正在加载

        // loadData
        $scope.loadData = function() {
            $scope.isLoading = true;
            $.ajax({
                url: contextPath + "/outsourcing/getTechAchieveDetail",
                type: "post",
                dataType: "json",
                data: {
                    achieveId: window.achieveId,
                },
                success: function(resp) {
                    $scope.isLoading = false;
                    $scope.achieveDetail = resp;

                    // 拼接预览图路径&&清除内容的HTML标签
					// 拼接预览图片路径
					var previewFileId = resp.fileId;
					var fileName = resp.fileName;
					if (previewFileId != null && fileName != null) {
						var imgSrc = DownloadService.buildUrl(previewFileId, fileName);
						resp.imgSrc = imgSrc;
						resp.professionField = GetProfessionFieldTextById(resp.professionFieldId);
                        resp.achieveType = GetAchieveTypeTextById(resp.achieveType);
						resp.maturityStage = GetMaturityStageTextById(resp.maturityStage);
                        resp.monetaryUnit = GetMonetaryTextById(resp.monetaryUnit);
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

        // /**
        //  * 搜索技术成果
        //  */
        // $("#btnSearch").on("click", function() {
        //     searchType = 0;
        //     var searchWord = $("#searchWord").val().trim();
        //     if(searchWord != null && searchWord != "") {
        //
        //         // 打开搜索页面
        //         window.location.href = contextPath + '/outsourcing/techAchieveSearch?searchWord='+searchWord;
        //     }
        // });

        // init
        $scope.loadData();

    }]);