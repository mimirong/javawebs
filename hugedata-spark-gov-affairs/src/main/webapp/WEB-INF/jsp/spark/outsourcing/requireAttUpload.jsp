<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="decorator" content="simple2" />
	<title>上传</title>
</head>
<body>
	<script>
		$("body").css({
			"margin": "0",
			"background-color": "white"
		});
	</script>

	<div style="height:26px; width:30px; overflow:hidden; background-color:white;">
		<div id="uploadPanel" style="padding:4px 0;">
			<form id="uploadForm" action="doRequireAttUpload" method="post" enctype="multipart/form-data">
				<input type="hidden" name="callback" value="${callback}" />
				<a href="javascript:;" id="uploadButton" style="color:#107aee; cursor:pointer;">
					<span>上传</span>
					<input type="file" id="uploadFile" name="file" style="position:absolute; right:0; top:0; opacity:0; cursor:pointer; font-size:0;" />
				</a>
			</form>
		</div>
		<div id="uploadingPanel" style="padding:3px 0; width:30px; text-align:center; display:none;">
			<img src="${contextPath}/static/web2/public/images/loading.gif" />
		</div>
		<div id="resultPanel" style="display:none;">
		</div>
	</div>
	
	<script>
		// 处理上传按钮
		var uploadButton = $("#uploadButton");
		$("#uploadFile").css({ 
			"left"   : uploadButton.position().left + "px",
			"top"    : uploadButton.position().top + "px",
			"width"  : uploadButton.width() + 20 + "px",
			"height" : uploadButton.height() + 2 + "px"
		});
		
		// 选择文件后触发上传
		$("#uploadFile").on("change", function() {
			var filename = $("#uploadFile").val();
			if (filename == "") {
				showError("请选择需要上传的文件");
				return;
			}
			$("#uploadPanel").hide();
			$("#uploadingPanel").show();
			$("#uploadForm").submit();
		});
		
		// 处理结果
		var isResult = ("${isResult}" === "true");
		var isError = ("${isError}" === "true");
		var response = ${response};
		var errorMessage = "${errorMessage}";
		var callback = "${callback}";

		if (isResult && window.parent) {
			var fn = window.parent[callback];
			if (fn && typeof fn === "function") {
				if (isError) {
					fn(errorMessage, null);					
				} else {
					fn(null, response);
				}
			}
		}
	</script>
</body>
</html>