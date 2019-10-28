<div class="pro-cont" id="upload">
	<div class="row">
		<form id="uploadForm" action="${action}" method="post" enctype="multipart/form-data">
			<input type="hidden" id="internalId" name="internalId" value="${internalId}" />
			<a href="javascript:;" class="upload" id="uploadButton">
				<span id="buttonText"></span>
	        	<input type="file" id="uploadFile" name="file" style="position:absolute; right:0; top:0; opacity:0;" />
	        </a>
	        <em id="prompt"></em>
		</form>
	</div>
</div>
<div id="uploading" style="display:none; padding:10px 0;">
	<span style="font-size:12px;">正在上传...</span>
</div>

<script>
	(function() {
		var def = window.parent.__fileUploadDialogData["${internalId}"];
		if (!def) {
			alert("无法获取文件上传组件信息.");
			return;
		}

		$("body").css("background-color", "white");		
		$("#prompt").html(def.prompt);	
		$("#buttonText").html(def.buttonText);
		
		var uploadButton = $("#uploadButton");
		$("#uploadFile").css("left", uploadButton.position().left + "px")
						.css("top", uploadButton.position().top + "px")
						.css("width", uploadButton.width() + 20 + "px")
						.css("height", uploadButton.height() + 2 + "px");
		
		function showError(msg) {
			if (window.parent && window.parent.MU) {
				window.parent.MU.msgTips("warn", msg, 1200); 
			} else {
				alert(msg);
			}
			return;
		}
		
		function checkExtension(filename) {
			if (!def.allowedExtensions || def.allowedExtensions == "") {
				return true;
			}
			
			var ext = "";
			if (filename.lastIndexOf(".") > 0) {
				ext = filename.substring(filename.lastIndexOf(".") + 1);
			}
			
			var exts = def.allowedExtensions.split(",");
			for (var i = 0; i < exts.length; i++) {
				if (exts[i] == ext) {
					return true;
				}
			}
			return false;
		}
		
		$("#uploadFile").on("change", function() {
			var filename = $("#uploadFile").val();
			if (filename == "") {
				showError("请选择需要上传的文件");
				return;
			}
			
			if (!checkExtension(filename)) {
				showError("不支持此格式的文件");
				return;
			}
			
			$("#upload").hide();
			$("#uploading").show();
			$("#uploadForm").submit();
		});
	})();
</script>