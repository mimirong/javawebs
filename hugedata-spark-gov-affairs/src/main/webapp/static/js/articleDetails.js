angular.module("articleDetailsModule", ['ngSanitize'])
	.controller("articleDetailsController", [ "$scope", "$http", "$sce", "$timeout", function($scope, $http, $sce, $timeout) {
		$scope.article = {};
		$scope.images = null;
		$scope.attachments = null;
		
		$scope.categoryId = null;
		$scope.categoryName = null;
		
		function init() {
			$.ajax({
				url: contextPath + "/article/details",
				type: "post",
				dataType: "json",
				data: {
					articleId: window.articleId
				},
				success: function(resp) {
					if (!resp || resp.result != 0) {
						alert(resp.message);
						return;
					}
					
					var article = resp.data;
					$scope.article = article;
					$scope.article.content = $sce.trustAsHtml(article.content);
					$scope.categoryId = article.categoryId;
					$scope.categoryName = window.categoryMap[article.categoryId];
					
					if (article.images && article.images.length > 0) {
						$scope.images = article.images;
					}
					
					if (article.attachments && article.attachments.length > 0) {
						$scope.attachments = article.attachments;
					}
					
					$scope.$apply();
				},
				error: function() {
					alert("加载文章信息失败");
				}
			});
		}
		
		init();
		
		// 我要申请 点击事件
        $(".btnAdd").on("click", function () {
            $.ajax({
                url: contextPath + "/outsourcing/applyCheck",
                type: "post",
                dataType: "json",
                data: {},
                success: function (resp) {
                    if (resp && resp.result == 0) {
                        $("#companyName").val("");
                        $("#contactName").val("");
                        $("#contact").val("");
                        $("#comment").val("");

                        $(".addPanelTitle").html("信息填写");
                        $("#btnDoAdd").show();
                        MU.mask();
                        MU.center("#addPanel");
                        $("#addPanel").show();
                    } else {
                        var redirect = govAffairsPortalUrl
                            + "/outsourcing/details?articleId=" + $scope.article.articleId + "&categoryId=" + $scope.categoryId;
                        window.location.href = umsUrl + "/login?redirect=" + encodeURIComponent(redirect);
                    }
                },
                error: function () {
                    MU.msgTips("error", "登录检查失败", 1200);
                }
			});

        });

        // 提交信息
		$("#btnDoAdd").on("click", function () {
			var data = checkAddForm();
            if (data == null) {
                return;
            }

            $.ajax({
                url: contextPath + "/outsourcing/submitApply",
                type: "post",
                dataType: "json",
                data: {
                    data: JSON.stringify(data)
                },
                success: function (resp) {
                    MU.hide($("#btnDoAdd"));
                    if (resp && resp.result == 0) {
                        MU.msgTips("success", "申请提交成功");
                    } else {
                        MU.msgTips("error", resp.message);
                    }
                },
                error: function () {
                    MU.hide($("#btnDoAdd"));
                    MU.msgTips("error", "申请提交失败，请稍后重试");
                }
            });

        });

        // 检查表单
        function checkAddForm() {
            // companyName
            var companyName = $("#companyName").val();
            if (companyName == "") {
                MU.msgTips("warn", "请输入公司名称", 1200);
                $("#companyName").focus();
                return null;
            }

            // contactName
            var contactName = $("#contactName").val();
            if (contactName == "") {
                MU.msgTips("warn", "请输入联系人", 1200);
                $("#contactName").focus();
                return null;
            }

            // contact
            var contact = $("#contact").val();
            if (!contact || contact == "") {
                MU.msgTips("warn", "请输入联系方式", 1200);
                $("#contact").focus();
                return null;
            }
            var TELEPHONE_REGEX = /^[\d\(\)\-\+]{6,30}$/;
            if (!TELEPHONE_REGEX.test(contact)) {
                MU.msgTips("warn", "请输入正确联系方式");
                $("#contact").focus();
                return;
            }

            // comment
            var comment = $("#comment").val();
            if (comment.length > 200) {
                MU.msgTips("warn", "说明不能超过200字", 1200);
                $("#comment").focus();
                return null;
            }

            return{
                companyName: companyName,
                contactName: contactName,
                contact: contact,
                comment: comment,
                articleId: window.articleId,
                articleName: $scope.article.title,
                categoryId: $scope.categoryId
			}

        }

	} ]);