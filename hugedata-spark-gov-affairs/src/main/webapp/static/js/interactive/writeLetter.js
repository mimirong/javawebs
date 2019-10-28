angular.module("writeLetterModule", [ ])


//Controller
.controller("writeLetterController", ["$scope", function($scope) {
	
	var checkLetter = function(){
		var title = $("#title").val();
		var content = $("#content").val();
		if(title==''){
			   MU.msgTips("warn", "请输入主题", 1200);
			return null;
		}
		else{
			
			return{
				messageCode:$("#messageCode").val(),
				title:title,
				messageType:$("#messageType").val(),
				content:content
			}
		}
	}
	
	$scope.submitLetter = function(){
		var checkedParam = checkLetter();
		if(checkedParam==null){
			return;
		}
		else{
			$.ajax({
				url: "writeLetterData",
				type: "post",
				dataType: "json",
				data: checkedParam,
				success: function(resp) {
					 MU.msgTips("success", "写信成功", 1200);
					 alert("请记住信件密码："+resp.data);
					 location.href="list";
				},
				error: function() {
					MU.msgTips("error", "写信失败", 1200);
				}
			});
			 
			
			
		}
		
	}
	
	
	
}]);