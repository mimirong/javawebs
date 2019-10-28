angular.module("outsourcingTechIndexModule", [])
    .controller("outsourcingTechIndexController", ["$scope", function($scope) {

        $scope.MAX_IMAGE_NUM = 100000;

        $scope.humanResourceList = [];
        $scope.companiesInfoList = [];
        $scope.projectOsinvestList = [];
        $scope.intellectualList = [];
        $scope.creditSystemList = [];

        $scope.intellectualServiceGuideUrl = "";
        $scope.intellectualDataServiceUrl = "";
        $scope.creditSystemComInfoQueryUrl = "";
        $scope.creditSystemComInfoSubmitUrl = "";

        // loadImage
        function loadImages(categoryId, count, successCallback) {
            $.ajax({
                url: contextPath + "/outsourcing/listTechImages",
                type: "post",
                dataType: "json",
                data: {
                    start: 0,
                    length: count,
                    categoryId: categoryId
                },
                success: function(resp) {
                    // 拼接预览图路径&&清除内容的HTML标签
                    for (var i = 0, size = resp.data.length; i < size; i++) {
                        // 拼接预览图片路径
                        var previewFileId = resp.data[i].previewFileId;
                        var previewFileName = resp.data[i].previewFileName;
                        if (previewFileId != null && previewFileName != null) {
                            var imgSrc = DownloadService.buildUrl(previewFileId, previewFileName);
                            resp.data[i].imgSrc = imgSrc;
                        }

                    }
                    successCallback(resp);
                },
                error: function() {
                    MU.msgTips("error", "加载数据失败");
                }
            });
        }

        // loadLinkUrl
        function loadLinkUrl(categoryId, successCallback) {
            $.ajax({
                url: contextPath + "/outsourcing/listTechImages",
                type: "post",
                dataType: "json",
                data: {
                    start: 0,
                    length: 1,
                    categoryId: categoryId
                },
                success: function(resp) {
                    successCallback(resp);
                },
                error: function() {
                    MU.msgTips("error", "加载数据失败");
                }
            });
        }

        /*图片轮播*/
        $scope.slide = function(){
            $(".slideList").stop(true,false).animate({'margin-left':-98},500,function(){
                $(this).removeAttr('style');
                $(this).find("li:first").appendTo($(this))
            });
        };
        var autoPlay = setInterval($scope.slide,2000);
        $(".prize").on({
            mouseenter:function(){
                clearInterval(autoPlay);
            },
            mouseleave:function(){
                autoPlay = setInterval($scope.slide,2000);
            }
        });

        // init image
        loadImages("HUMAN_RESOURCE", $scope.MAX_IMAGE_NUM, function(resp) {
            $scope.humanResourceList = resp.data;
            $scope.$apply();
            $scope.slide();
        });
        loadImages("COMPANIES_INFORMATION", $scope.MAX_IMAGE_NUM, function(resp) {
            $scope.companiesInfoList = resp.data;
            $scope.$apply();
            $scope.slide();
        });
        loadImages("PROJECT_OSINVEST", $scope.MAX_IMAGE_NUM, function(resp) {
            $scope.projectOsinvestList = resp.data;
            $scope.$apply();
            $scope.slide();
        });
        loadImages("INTELLECTUAL", 2, function(resp) {
            $scope.intellectualList = resp.data;
            $scope.$apply();
        });
        loadImages("CREDIT_SYSTEM", 1, function(resp) {
            $scope.creditSystemList = resp.data;
            $scope.$apply();
        });

        // init linkUrl
        loadLinkUrl("LINK_URL_INTELLECTUAL_SERVICE_GUIDE", function (resp) {
            if (resp.data.length > 0) {
                $scope.intellectualServiceGuideUrl = resp.data[0].linkUrl;
            }
            $scope.$apply();
        });

        loadLinkUrl("LINK_URL_INTELLECTUAL_DATA_SERVICE", function (resp) {
            if (resp.data.length > 0) {
                $scope.intellectualDataServiceUrl = resp.data[0].linkUrl;
            }
            $scope.$apply();
        });

        loadLinkUrl("LINK_URL_CREDIT_SYSTEM_COM_INFO_QUERY", function (resp) {
            if (resp.data.length > 0) {
                $scope.creditSystemComInfoQueryUrl = resp.data[0].linkUrl;
            }
            $scope.$apply();
        });

        loadLinkUrl("LINK_URL_CREDIT_SYSTEM_COM_INFO_SUBMIT", function (resp) {
            if (resp.data.length > 0) {
                $scope.creditSystemComInfoSubmitUrl = resp.data[0].linkUrl;
            }
            $scope.$apply();
        });

    }]);