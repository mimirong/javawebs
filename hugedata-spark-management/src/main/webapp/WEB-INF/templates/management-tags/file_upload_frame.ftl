<div id="upload" style="padding:6px 0;">
	<form id="uploadForm" action="${action}" method="post" enctype="multipart/form-data">
		<input type="hidden" id="internalId" name="internalId" value="${internalId}" />
		<input type="hidden" id="maxFileSize" name="maxFileSize" value="" />
    	<input type="file" id="uploadFile" name="file" />
    	<a href="javascript:;" id="btnSubmit" class="btn btn-green" 
    			style="vertical-align:middle; margin-right:6px; line-height:22px; width:28px; height:22px; padding:0 22px;">上传</a>
    	<span class="prompt" style="color:#f00"></span>
	</form>
</div>
<div id="uploading" style="display:none; padding:10px 0;">
	<span style="font-size:12px;">正在上传...</span>
</div>

<script>
	$("body").css("background-color", "white");
	
	(function() {
		<#-- GET FILE-UPLOAD DEFINITION -->
		var def = window.parent.__fileUploadDialogData["${internalId}"];
		if (!def) {
			alert("无法获取文件上传组件信息.");
			return;
		}
		
		<#-- Set Values -->
		$(".prompt").html(def.prompt);
		$("#maxFileSize").val(def.maxFileSize);
		
		<#-- showError -->
		function showError(msg) {
			if (window.parent && window.parent.MU) {
				window.parent.MU.msgTips("warn", msg, 1200); 
			} else {
				alert(msg);
			}
			return;
		}
		
		<#-- checkExtension -->
		function checkExtension(filename) {
		    filename = filename.toLocaleLowerCase();
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
		
		<#-- FileSizeText -->
		function fileSizeText(size) {
			if (size >= 1024 * 1024 * 1024) {
				return (size / 1024 / 1024 / 1024).toFixed(1) + "GB"
			} else if (size >= 1024 * 1024) {
				return (size / 1024 / 1024).toFixed(1) + "MB"
			} else if (size >= 1024) {
				return (size / 1024).toFixed(1) + "KB"
			} else {
				return size + "B";
			}
		}
		
		<#-- Check File Size -->
		function checkFileSize() {
			var maxFileSize = parseInt(def.maxFileSize);
			if (isNaN(maxFileSize) || maxFileSize <= 0) {
				return true;
			}
			
			var files = document.getElementById("uploadFile").files;
			if (!files || !files[0]) {
				return true;
			}

			if (files[0].size > def.maxFileSize) {
				return false;
			}			
			
			return true;
		}
		
		<#-- SUBMIT -->
		$("#btnSubmit").on("click", function() {
			var filename = $("#uploadFile").val();
			if (filename == "") {
				showError("请选择需要上传的文件");
				return;
			}
			
			if (!checkExtension(filename)) {
				showError("不支持此格式的文件");
				return;
			}
			
			if (!checkFileSize()) {
				showError("文件大小不能超过" + fileSizeText(def.maxFileSize));
				return;
			}
			
			$("#upload").hide();
			$("#uploading").show();
			$("#uploadForm").submit();
		});
	})();
</script>