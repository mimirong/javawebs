angular.module("expertDetailModule", [ "sparkGlobal" ])

//Controller
    .controller("expertDetailController", ["$scope", function($scope) {
        $scope.isLoading = false;					//是否显示正在加载

        // loadData
        $scope.loadData = function() {
            $scope.isLoading = true;
            $.ajax({
                url: contextPath + "/outsourcing/getExpertDetail",
                type: "post",
                dataType: "json",
                data: {
                    expertId: window.expertId,
                },
                success: function(resp) {
                    $scope.isLoading = false;
                    $scope.expertDetail = resp;

                    // 拼接预览图路径&&清除内容的HTML标签
					// 拼接预览图片路径
					var previewFileId = resp.fileId;
					var fileName = resp.fileName;
					if (previewFileId != null && fileName != null) {
						var imgSrc = DownloadService.buildUrl(previewFileId, fileName);
						resp.imgSrc = imgSrc;
						resp.professionField = GetProfessionFieldTextById(resp.professionFieldId);
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
        //  * 搜索专家名录
        //  */
        // $("#btnSearch").on("click", function() {
        //     var searchWord = $("#searchWord").val().trim();
        //     if(searchWord != null && searchWord != "") {
        //         // 打开搜索页面
        //         window.location.href = contextPath + '/outsourcing/expertsSearch?searchWord='+searchWord;
        //     }
        // });

        // init
        $scope.loadData();

    }]);