(function() {
    $(".datePicker").datepicker();
    $(".datePicker").datepicker("disable");
})();

angular.module("siteProofListModule", ["sparkGlobal"])
    .controller("siteProofListController", [ "$scope", "$http", "$timeout", function($scope, $http, $timeout) {
        var PAGE_SIZE = 12;          //每页显示记录数
        $scope.dataList = [];        //分页数据
        $scope.page = 1;             //当前页
        $scope.pageTotal = 0;        //总页数
        $scope.hasNextPage = false;  //是否有下一页
        $scope.hasPrevPage = false;  //是否有上一页
        $scope.pageButtons = [];     //显示页码按钮
        $scope.directGoto = "";      //bind直接跳转到页码的文本框

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
        $scope.reload = function() {
            $http({
                url: contextPath + "/siteProof/query",
                method: "get",
                params: {
                    pageStart: PAGE_SIZE * ($scope.page - 1),
                    pageSize: PAGE_SIZE
                },
            }).success(function(data, status, headers, config) {
                info = data.data;
                //构造附件下载url attachmentFileUrl
                for(var i = 0; i < info.data.length; i++) {
                    if('' != info.data[i].attachmentFileId && null != info.data[i].attachmentFileId) {
                        info.data[i].attachmentFileUrl = DownloadService.buildUrl(info.data[i].attachmentFileId, info.data[i].attachmentFileName);
                    }
                }
                $scope.dataList = info.data;
                $scope.page = info.page;
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

        $scope.siteProof = function () {
            window.location.href=contextPath + "/siteProof/list";
        };

        $scope.financingApply = function () {
            window.location.href=contextPath + "/siteProof/list";
        };

        $scope.applyApplication = function () {
            MU.mask();
            MU.center("#applyFinancing");
            $("#applyFinancing").show();
        };

        // 初始化
        $scope.reload();

    }]);

// 下载服务
var DownloadService = (function() {
    var ds = {};
    ds.buildUrl = function(fileId, filename) {
        var url = downloadServiceUrl;
        if (url.lastIndexOf("/") != url.length - 1) {
            url += "/";
        }
        url += fileId.substring(0, 7) + "_" + fileId.substring(fileId.length - 1) + "/";
        url += fileId;
        if (filename) {
            //url += "?attname=" + encodeURIComponent(filename);  //不需要编码，否则下载下来的文件名是编码后的文件名
            url += "?attname=" + filename;
        }
        return url;
    };
    return ds;
})();