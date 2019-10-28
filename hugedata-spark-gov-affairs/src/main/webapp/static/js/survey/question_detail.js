angular.module("questionDetailModule", [ ])


//Controller
.controller("questionDetailController", ["$scope","$http", function($scope,$http) {
	// loadData
	$scope.loadData = function() {
		$scope.isLoading = true;
		$.ajax({
			url: "questionDetail",
			type: "post",
			dataType: "json",
			data: {
				surveyId:surveyId
			},
			success: function(resp) {
				$scope.itSurvey = resp.data.itSurvey;
				$scope.questionList = resp.data.questionList;
				$(".top").html($scope.itSurvey.brief);
				$scope.$apply();
			},
			error: function(e) {
				alert("加载数据失败");
				$scope.isLoading = false;
				$scope.$apply();
			}
		});
	}
	// init
	$scope.loadData();
	
	// 根据questionId找到对应的问题信息
	function findQuestionInfo(questionId) {
		for (var i = 0; i < $scope.questionList.length; i++) {
			var question = $scope.questionList[i];
			if (question.questionId == questionId) {
				return question;
			}
		}
		return null;
	}
	
	// 清除错误消息
	$scope.clearError = function(questionId) {
		var question = findQuestionInfo(questionId);
		if (question) {
			question.errorMessage = null;
		}
	};
	
	// 检查和获取表单数据
	function loadAndCheckSubmitData() {
		var submitData = {};
		var questionItems = $(".questionItem");
		var hasErrors = false;
		
		for (var i = 0; i < questionItems.length; i++) {
			var item = $(questionItems[i]);
			var questionId = item.attr("data-id");
			var question = findQuestionInfo(questionId);
			if (question == null) {
				continue;
			}
			
			if (question.questionType == "0") {
				var options = item.find(".questionOption");
				
				// 获取选择的值
				var value = null;
				var optionText = null;
				for (var j = 0; j < options.length; j++) {
					var option = $(options[j]);
					if (option.hasClass("checked")) {
						value = option.attr("data-id");
						optionText = option.text();
						break;
					}
				}
				
				// 判断值为空
				if ((!value || value == "") && question.isRequired) {
					question.errorMessage= "此项为必选项，必须选择一项";
					hasErrors = true;
				}
				
				submitData["" + questionId] = value+"@@"+optionText;
				
			} else if (question.questionType == "1") {
				var input = item.find("textarea");
				var value = input.val();

				// 判断值为空
				if ((!value || value == "") && question.isRequired) {
					question.errorMessage= "此项为必选项，请填写";
					hasErrors = true;
				}
				//判断长度限制
				if (value && value.length > 1000) {
					question.errorMessage= "长度不能超过1000个字";
					hasErrors = true;
				}
				
				submitData["" + questionId] =  value;
			} else if (question.questionType == "2") {
				var options = item.find(".questionOption");
				
				// 获取选择的值
				var values = [];
				item.find(".questionOptionMulti.checked").each(function() {
					values.push({
						value: $(this).attr("data-id"),
						text: $(this).text()
					});
				});
				
				// 用户未选择并且未必选时报错
				if (values.length == 0 && question.isRequired) {
					question.errorMessage = "此项为必选项，必须至少选择一项";
					hasErrors = true;
				}
				
				// 便于统计，当用户没有选择任何值时，插入一个默认值
				if (values.length == 0) {
					values.push({
						value: "NO_CHOICE",
						text: "未选择"
					});
				}
				
				submitData["" + questionId] = JSON.stringify(values);
			}
		}
		
		return { hasErrors:hasErrors, submitData:submitData };
	}
	
	// 提交表单
	$scope.submit = function() {
		// 检查和获取表单数据
		var resp = loadAndCheckSubmitData();
		if (resp.hasErrors) {
			return;
		}
		var submitData = resp.submitData;
		
		// 提交
		$http({
			url: "resultSubmit",
			method: "POST",
			data: $.param({
				data: JSON.stringify(submitData),
				surveyId: $scope.itSurvey.surveyId
			}),
		    headers: {
		        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
	    	}
		}).success(function(data, status, headers, config) {
			if (data && data.result == 0) {
				MU.msgTips('success',"提交成功",1200);
				MU.delay(function(){
					window.location.href = "list";
				},1200);
			} else {
				MU.msgTips('error',data.message);
			}
		}).error(function(data, status, headers, config) {
			alert("提交失败");
		});
		
	};//submit
	
}]);