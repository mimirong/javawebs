angular.module("reportSubmitDialogModule",[])
    .controller("reportSubmitDialogController",["$scope", "$http", "$timeout", function($scope, $http, $timeout){
        $scope.templateNameList = [];   //报表模板数据

        // 显示提交报表弹框
        $scope.$on("submitReport", function (e, args) {
            if($scope.templateNameList.length == 0){
                MU.msgTips("warn", "没有可用的报表模板，请联系管理员", 1200);
                return;
            }

            MU.mask();
            MU.center("#submitReportPanel");
            $("#submitReportPanel").show();
        });

        // 加载报表名称列表
        $scope.loadReportNameList = function () {
            // 请求弹框所需数据
            $http({
                url: contextPath + "/reportTemplate/query",
                method: "get",
                params: {
                    pageStart: 0,
                    pageSize: 10000
                }
            }).success(function (data, status, headers, config){
                info = data.data;
                //初始化赋值
                $scope.templateNameList = info.data;
                $scope.templateIdSelected = $scope.templateNameList[0].templateId;
                $scope.templateNameSelected = $scope.templateNameList[0].templateName;

                $timeout(function() {
                    $("#reportNameList select").selectList();
                }, 0);

            }).error(function(data, status, headers, config) {
                alert("查询失败");
            });
        };

        // 提交报表
        $scope.submitReportBtn = function () {
            var data = checkAddModifyForm();
            if (data == null) {
                return;
            }

            $http({
                url: contextPath + "/reportSubmit/doSubmitReport",
                method: "POST",
                data: $.param(data),
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
                }
            }).success(function(data, status, headers, config) {
                if (data && data.result == 0) {
                    window.MU.msgTips("success", "提交成功", 1200);
                    setTimeout(function(){
                        window.location.href = "list";
                    },1200);
                } else {
                    MU.msgTips("error", data.message);
                }
            }).error(function(data, status, headers, config) {
                MU.msgTips("error", "提交失败");
            });
        };

        // 检查表单
        function checkAddModifyForm() {

            // reportTemplateId
            var reportTemplateId = $("#reportTemplateId").val();
            var reportTemplateName = "";

            for (var i = 0, dataLength = $scope.templateNameList.length; i < dataLength; i++) {
                if($scope.templateNameList[i].templateId == reportTemplateId){
                    reportTemplateName = $scope.templateNameList[i].templateName;
                }
            }

            // reportFileId
            var reportFileId = $("#reportFileId").val();
            if (reportFileId == "") {
                MU.msgTips("warn", "请选择附件", 1200);
                return null;
            }

            //reportFileName
            var reportFileName = $("#reportFileName").val();
            if (reportFileName == "") {
                MU.msgTips("warn", "请选择附件", 1200);
                return null;
            }

            //companyName
            var companyName = $("#companyName").val();
            if (companyName == "" || companyName == null) {
                MU.msgTips("warn", "请输入公司名称", 1200);
                $("#companyName").focus();
                return null;
            } else if (companyName.length > 100) {
                MU.msgTips("warn", "公司名称过长，请重新输入", 1200);
                $("#companyName").focus();
                return null;
            }

            //contact
            var contact = $("#contact").val();
            if (contact == "" || contact == null) {
                MU.msgTips("warn", "请输入联系人", 1200);
                $("#contact").focus();
                return null;
            } else if (contact.length > 100) {
                MU.msgTips("warn", "联系人姓名过长，请重新输入", 1200);
                $("#contact").focus();
                return null;
            }

            //contactMobile
            var contactMobile = $("#contactMobile").val();
            if (!CheckService.telephone(contactMobile)) {
                MU.msgTips("warn", "请输入正确的联系电话", 1200);
                $("#contactMobile").focus();
                return null;
            }

            return {
                templateName:reportTemplateName,
                templateId:reportTemplateId,
                reportFileId:reportFileId,
                reportFileName:reportFileName,
                companyName:companyName,
                contact:contact,
                contactMobile:contactMobile
            };
        }

        // 初始化
        $scope.loadReportNameList();

    }]);