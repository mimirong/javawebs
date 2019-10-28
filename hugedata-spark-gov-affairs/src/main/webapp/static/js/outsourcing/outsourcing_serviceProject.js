angular.module("outsourcingServiceProjectModule", [])
    .controller("outsourcingServiceProjectController", ["$scope", function ($scope) {

        $scope.isLoading = false;//是否显示正在加载
        $scope.page = {start: 0, length: 15};//分页信息
        $scope.pageButtons = [];//显示的分页按钮
        $scope.supplyType = window.supplyType;// 供应类型，默认为检测检验
        $scope.supplyTypeName = window.supplyTypeMap[$scope.supplyType];// 供应类型，默认为检测检验
        $scope.serviceField = "";// 服务领域，默认为全部
        $scope.sortType = "";// 排序方式，默认为按浏览量

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

        // reload
        $scope.reload = function () {
            $scope.isLoading = true;

            $scope.serviceField = $("#serviceFieldQuery").val();
            $scope.sortType = $("#sortTypeQuery").val();

            // console.log("SuppleType: " + $scope.supplyType +
            //     ", ServiceField: " + $scope.serviceField +
            //     ", SortType: " + $scope.sortType);

            $.ajax({
                url: contextPath + "/outsourcing/listServiceProject",
                type: "post",
                dataType: "json",
                data: {
                    start: $scope.page.start,
                    length: $scope.page.length,
                    supplyType: $scope.supplyType,
                    serviceField: $scope.serviceField,
                    sortType: $scope.sortType
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

        /**
         * 根据供应类型筛选：检验检测（SP_SUPPLY_TYPE_INSPECT_DETEC）、技术服务（SP_SUPPLY_TYPE_TECH_SERVICE）
         * @param e     onClick点击事件
         * @param value 当前被选中的类别值
         */
        $scope.supplyTypeSelected = function (e, value) {
           // $("#supplyTypeQuery").val(value);
            $scope.supplyType = value;
            switch (value){
                case "SP_SUPPLY_TYPE_TECH_SERVICE":
                    $scope.supplyTypeName = "技术服务";
                    $('#t1').removeClass('on');
                    $('#t2').addClass('on');
                    break;
                case "SP_SUPPLY_TYPE_INSPECT_DETEC":
                	 $('#t2').removeClass('on');
                     $('#t1').addClass('on');
                default:
                    $scope.supplyTypeName = "检验检测";
                    break;
            }
            $scope.reload();
        };
        
        /**
         * 根据服务领域筛选
         * @param e     onClick点击事件
         * @param value 当前被选中的类别值
         */
        $scope.serviceFieldSelected = function (e, value) {
            $("#serviceFieldQuery").val(value);
            $scope.reload();
        }

        /**
         * 切换排序方式：按浏览量（QUERY_SORT_VISITOR_COUNT）、按最新发布（QUERY_SORT_LATEST_PUBLISH）
         * @param e     onClick点击事件
         * @param value 当前被选中的类别值
         */
        $scope.sortTypeSelected = function (e, value) {
            $("#sortTypeQuery").val(value);
            $scope.reload();
        };

        // init
        $scope.reload();

    }]);
