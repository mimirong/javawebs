angular.module("outsourcingMyReleaseModule", [])
    .controller("outsourcingMyReleaseController", ["$scope", function ($scope) {

        $scope.isLoading = false;//是否显示正在加载
        $scope.page = {start: 0, length: 20};//分页信息
        $scope.pageButtons = [];//显示的分页按钮

        /********************分页********************/
        // gotoPage
        $scope.gotoPage = function (p) {
            if (p > $scope.page.pageCount) {
                return;
            }
            $scope.page.page = p;
            $scope.page.start = $scope.page.length * (p - 1);
            $scope.reload();
        };

        // directGotoPage
        $scope.directGotoPage = function () {
            if ($scope.directGoto == "") {
                return;
            }
            var p = parseInt($scope.directGoto);
            if (!isNaN(p)) {
                $scope.gotoPage(p);
            }
        };

        // page input keydown
        $scope.onPageKeydown = function (e) {
            if (e.keyCode == 13) {
                $scope.directGotoPage();
            }
        };
        /********************分页********************/

        /**
         * 删除发布供应
         */
       $scope.deleteMyRelease = function (e) {
            var projectId = $(e.target).attr("data-value");
            MU.conFirm('删除','您确定要删除吗？<input id="itemId" type="hidden" value="'+projectId+'" />', function () {
                $.ajax({
                    url: contextPath + "/outsourcing/deleteMyRelease",
                    type: "post",
                    dataType: "json",
                    data: {
                        projectId: projectId
                    },
                    success: function (resp) {
                        if (resp && resp.result == 0) {
                            MU.msgTips("success", "删除成功");
                            $(e.target).closest('li').remove();
                        } else {
                            MU.msgTips("error", "删除失败");
                        }
                    },
                    error: function () {
                        MU.msgTips("error", "删除失败");
                    }
                });
            });
        };

        // reload
        $scope.reload = function () {
            $scope.isLoading = true;

            $scope.supplyType = $("#supplyTypeQuery").val();
            $scope.serviceField = $("#serviceFieldQuery").val();
            $scope.sortType = $("#sortTypeQuery").val();

            // console.log("SuppleType: " + $scope.supplyType +
            //     ", ServiceField: " + $scope.serviceField +
            //     ", SortType: " + $scope.sortType);

            $.ajax({
                url: contextPath + "/outsourcing/listMyRelease",
                type: "post",
                dataType: "json",
                data: {
                    start: $scope.page.start,
                    length: $scope.page.length
                },
                success: function (resp) {
                    $scope.isLoading = false;
                    resp.start = $scope.page.start;
                    resp.length = $scope.page.length;
                    $scope.page = resp;

                    // 分页按钮处理
                    $scope.pageButtons = [];
                    for (var i = Math.max(1, $scope.page.page - 2);
                         i <= Math.min($scope.page.pageCount, $scope.page.page + 2);
                         i++) {
                        $scope.pageButtons.push(i);
                    }

                    // 拼接预览图路径
                    for (var i = 0, size = resp.data.length; i < size; i++) {
                        // 拼接预览图片路径
                        var coverFileId = resp.data[i].coverFileId;
                        var coverFileName = resp.data[i].coverFileName;
                        if (coverFileId != null && coverFileName != null) {
                            var imgSrc = DownloadService.buildUrl(coverFileId, coverFileName);
                            resp.data[i].imgSrc = imgSrc;
                        }
                    }

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
        $scope.reload();

    }]);
