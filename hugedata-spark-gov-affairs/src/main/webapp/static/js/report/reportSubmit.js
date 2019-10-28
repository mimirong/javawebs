angular.module("reportSubmitListModule", ["reportSubmitDialogModule"])
    .controller("reportSubmitListController",
        ["$scope", "$http", "$timeout", "$rootScope", function ($scope, $http, $timeout, $rootScope) {
        var PAGE_SIZE = 12;             //每页显示记录数
        $scope.dataList = [];           //分页数据
        $scope.page = 1;                //当前页
        $scope.pageTotal = 0;           //总页数
        $scope.hasNextPage = false;     //是否有下一页
        $scope.hasPrevPage = false;     //是否有上一页
        $scope.pageButtons = [];        //显示页码按钮
        $scope.directGoto = "";         //bind直接跳转到页码的文本框

        // gotoPage
        $scope.gotoPage = function(p) {
            if (p > $scope.pageTotal) {
                return;
            }
            $scope.page = p;
            $scope.reload();
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

        // reload
        $scope.reload = function () {
            $http({
                url: contextPath + "/reportSubmit/query",
                method: "get",
                params: {
                    pageStart: PAGE_SIZE * ($scope.page - 1),
                    pageSize: PAGE_SIZE
                }
            }).success(function (data, status, headers, config) {
                info = data.data;
                $scope.dataList = info.data;
                $scope.pageTotal = info.pageCount;
                $scope.hasNextPage = (info.page < info.pageCount);
                $scope.hasPrevPage = (info.page > 1);

                // 分页按钮处理
                $scope.pageButtons = [];
                for (var i = Math.max(1, $scope.page - 2); i <= Math.min(info.pageCount, $scope.page + 2); i++) {
                    $scope.pageButtons.push(i);
                }
            }).error(function(data, status, headers, config) {
                alert("查询失败");
            });

        };

        $scope.reportSubmit = function () {
            window.location.href = contextPath + "/reportSubmit/list";
        };
        $scope.reportTemplate = function () {
            // console.log("report template");
            window.location.href = contextPath + "/reportTemplate/list";
        };

        $scope.submitReport = function () {
            $rootScope.$broadcast("submitReport");
        };

        //显示提交记录详情弹框
         $scope.showReportDetail = function (report) {
             $scope.reportDetail = report;
             MU.mask();
             MU.center("#reportDetailPanel");
             $("#reportDetailPanel").show();
         };

        // 初始化
        $scope.reload();

    }]);

