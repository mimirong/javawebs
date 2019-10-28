angular.module("outsourcingServiceProjectDetailModule", [])
    .controller("outsourcingServiceProjectDetailController", ["$scope", "$sce", function ($scope, $sce) {

        $scope.isLoading = false;//是否显示正在加载
        $scope.serviceProject = {};
        $scope.specList = [];
        $scope.imageList = [];

        $scope.init = function () {
            $scope.isLoading = true;
            var projectId = window.projectId;
            $.ajax({
                url: contextPath + "/outsourcing/queryServiceProjectDetail",
                type: "post",
                dataType: "json",
                data: {
                    projectId: projectId
                },
                success: function (resp) {
                    $scope.isLoading = false;
                    if (resp && resp.result == 0) {
                        $scope.serviceProject = resp.data.serviceProject;
                        $scope.serviceProject.detailDesc = $sce.trustAsHtml($scope.serviceProject.detailDesc);
                        $scope.specList = resp.data.specList;
                       // $scope.imageList = resp.data.imageList;

                        // 翻译价格单位
                        for (var i = 0, size = $scope.specList.length; i < size; i++) {
                            switch($scope.specList[i].priceUnit){
                                case "PRICE_UNIT_MILLION_YUAN":
                                    $scope.specList[i].priceUnitStr = "百万元";
                                    break;
                                case "PRICE_UNIT_TEN_THOUSAND_YUAN":
                                    $scope.specList[i].priceUnitStr = "万元";
                                    break;
                                case "PRICE_UNIT_YUAN":
                                default:
                                    $scope.specList[i].priceUnitStr = "元";
                                    break;
                            }
                        }

                        // 拼接图片路径
//                        for (var i = 0, size = $scope.imageList.length; i < size; i++) {
//                            var fileId = $scope.imageList[i].fileId;
//                            var fileName = $scope.imageList[i].fileName;
//                            var previewFileId = $scope.imageList[i].previewFileId;
//                            var previewFileName = $scope.imageList[i].previewFileName;
//                            if (fileId != null && fileName != null&&previewFileId != null && previewFileName != null) {
//                                var fileSrc = DownloadService.buildUrl(fileId);
//                                $scope.imageList[i].fileSrc = fileSrc;
//                                var previewFileSrc = DownloadService.buildUrl(previewFileId);
//                                $scope.imageList[i].previewFileSrc = previewFileSrc;
//                            }
//                        }
                    } else {
                        MU.msgTips("error", "加载失败");
                    }
                    $scope.$apply();
                },
                error: function () {
                    $scope.isLoading = false;
                    MU.msgTips("error", "加载失败");
                    $scope.$apply();
                }
            });
        };

        // init
        $scope.init();
    }]);
