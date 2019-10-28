angular.module("indexModule", [])
    .controller("indexController", ["$scope", function ($scope) {
        $scope.supplyList1 = [];//显示的分页按钮
        $scope.listSupply1 = function () {
            
            $.ajax({
                url: contextPath + "/outsourcing/listSupply1",
                type: "get",
                dataType: "json",
                data: {},
                success: function (resp) {
                	if (resp && resp.result == 0) {
                		 for (var i = 0, size = resp.data.length; i < size; i++) {
                             // 拼接预览图片路径
                             var coverFileId = resp.data[i].coverFileId;
                             var coverFileName = resp.data[i].coverFileName;
                             if (coverFileId != null && coverFileName != null) {
                                 var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                                 resp.data[i].imgSrc = imgSrc;
                             }
                         }
                		 $scope.supplyList1 = resp.data;
                		 $scope.$apply();
                	 }
                },
                error: function () {
                    MU.msgTips("error", "加载数据失败");
                    $scope.isLoading = false;
                    $scope.$apply();
                }
            });

        };

        // init
        $scope.listSupply1();
        
        
        $scope.supplyList2 = [];//显示的分页按钮
        $scope.listSupply2 = function () {
            
            $.ajax({
                url: contextPath + "/outsourcing/listSupply2",
                type: "get",
                dataType: "json",
                data: {},
                success: function (resp) {
                	if (resp && resp.result == 0) {
                		 for (var i = 0, size = resp.data.length; i < size; i++) {
                             // 拼接预览图片路径
                             var coverFileId = resp.data[i].coverFileId;
                             var coverFileName = resp.data[i].coverFileName;
                             if (coverFileId != null && coverFileName != null) {
                                 var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                                 resp.data[i].imgSrc = imgSrc;
                             }
                         }
                		 $scope.supplyList2 = resp.data;
                		 $scope.$apply();
                	 }
                },
                error: function () {
                    MU.msgTips("error", "加载数据失败");
                    $scope.isLoading = false;
                    $scope.$apply();
                }
            });

        };

        // init
        $scope.listSupply2();
        
        $scope.requireList = [];//显示的分页按钮
        $scope.listRequire = function () {
            
            $.ajax({
                url: contextPath + "/outsourcing/listRequire",
                type: "get",
                dataType: "json",
                data: {},
                success: function (resp) {
                	if (resp && resp.result == 0) {
                		 $scope.requireList = resp.data;
                		 $scope.$apply();
                	 }
                },
                error: function () {
                    MU.msgTips("error", "加载数据失败");
                    $scope.isLoading = false;
                    $scope.$apply();
                }
            });

        };

        // init
        $scope.listRequire();
        
        
        $scope.meetingList = [];//显示的分页按钮
        $scope.listMeeting = function () {
            
            $.ajax({
                url: contextPath + "/outsourcing/listMeetingTraining",
                type: "get",
                dataType: "json",
                data: {start:0,length:8},
                success: function (resp) {
                	 
                		 $scope.meetingList = resp.data;
                		 $scope.$apply();
                	 
                },
                error: function () {
                    MU.msgTips("error", "加载数据失败");
                    $scope.isLoading = false;
                    $scope.$apply();
                }
            });

        };

        // init
        $scope.listMeeting();
        
        $scope.techAchieveList = [];//显示的分页按钮
        $scope.listTechAchieve = function () {
            
            $.ajax({
                url: contextPath + "/outsourcing/listTechAchieve",
                type: "get",
                dataType: "json",
                data: {start:0,length:6},
                success: function (resp) {
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
                	 
                		 $scope.techAchieveList = resp.data;
                		 $scope.$apply();
                	 
                },
                error: function () {
                    MU.msgTips("error", "加载数据失败");
                    $scope.isLoading = false;
                    $scope.$apply();
                }
            });

        };

        // init
        $scope.listTechAchieve();
        
        
        
        

    }]);
