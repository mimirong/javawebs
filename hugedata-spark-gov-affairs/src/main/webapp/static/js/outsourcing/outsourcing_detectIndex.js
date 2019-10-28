angular.module("outsourcingDetectIndexModule", [])
    .controller("outsourcingDetectIndexController", ["$scope", function ($scope) {
        $scope.achieveExhibitList = [];
        $scope.resourcePlatformList = [];
        $scope.techTransferList = [];
        $scope.techTrainList = [];

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
        loadData("OS_DETEC_ACHIEVE_EXHIBIT", function (resp) {
            $scope.achieveExhibitList = resp.data;
            $scope.$apply();
        });
        loadData("OS_DETEC_RESOURCE_PLATFORM", function (resp) {
            $scope.resourcePlatformList = resp.data;
            $scope.$apply();
        });
        loadData("OS_DETEC_TECH_TRANSFER", function (resp) {
            $scope.techTransferList = resp.data;
            $scope.$apply();
        });
        loadData("OS_DETEC_TECH_TRAIN", function (resp) {
            $scope.techTrainList = resp.data;
            $scope.$apply();
        });

    }]);
