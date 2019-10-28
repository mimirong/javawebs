angular.module("meetingTrainingDetailModule", [ "sparkGlobal" ])

//Controller
        .controller("meetingTrainingDetailController", ["$scope", function($scope) {
        $scope.isLoading = false;					//是否显示正在加载

        // loadData
        $scope.loadData = function() {
            $scope.isLoading = true;
            $.ajax({
                url: contextPath + "/outsourcing/getMeetingTrainingDetail",
                type: "post",
                dataType: "json",
                data: {
                    trainingId: window.trainingId,
                },
                success: function(resp) {
                    $scope.isLoading = false;
                    $scope.meetingTrainingDetail = resp;

                    // 拼接预览图路径&&清除内容的HTML标签
					// 拼接预览图片路径
					var previewFileId = resp.fileId;
					var fileName = resp.fileName;
					if (previewFileId != null && fileName != null) {
						var imgSrc = DownloadService.buildUrl(previewFileId, fileName);
						resp.imgSrc = imgSrc;
						resp.trainingType = GetTrainingTypeTextById(resp.trainingType);
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
        //  * 搜索会议培训
        //  */
        // $("#btnSearch").on("click", function() {
        //     $scope.searchWord = $("#searchWord").val().trim();
        //     if($scope.searchWord != null && $scope.searchWord != "") {
        //         // 打开会议培训列表页
        //         window.location.href = contextPath + '/outsourcing/meetingTrainingIndex?searchWord='+$scope.searchWord;
        //
        //         $scope.loadData();
        //     }
        // });

        // init
        $scope.loadData();

    }]);