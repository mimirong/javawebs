<input type="hidden" name="${inputNameForFileId}" id="${inputIdForFileId}" />
<input type="hidden" name="${inputNameForFileName}" id="${inputIdForFileName}" />

<div class="commons-file-upload" data-internal-id="${internalId}" style="width:${width}; overflow:hidden;">
	<iframe id="fileUpload_${internalId}" height="40" width="${width}" 
			frameborder="0" src="" scrolling="no"></iframe>
</div>

<script>
	if (!window.__fileUploadDialogData) {
		window.__fileUploadDialogData = {};
	}
	
	if (!window.__setupFileUploadFrame) {
		window.__setupFileUploadFrame = function(internalId) {
			var def = window.__fileUploadDialogData[internalId];
			if (def == null) {
				alert("无法显示上传组件");
				return;
			}
			var url = "${contextPath}/commons-web/file-upload/toFileUpload-simple";
			url += "?action=" + encodeURIComponent(def.action);
			url += "&internalId=" + encodeURIComponent(internalId);
			$("#fileUpload_" + internalId).attr("src", url); 
		};
	}

	window.__fileUploadDialogData["${internalId}"] = {
		inputIdForFileId     : "${inputIdForFileId}",
		inputNameForFileId   : "${inputNameForFileId}",
		inputIdForFileName   : "${inputIdForFileName}",
		inputNameForFileName : "${inputNameForFileName}",
		action               : "${action}",
		allowedExtensions    : "${allowedExtensions}",
		width                : "${width}",
		prompt               : "${prompt}",
		buttonText           : "${buttonText}"
	};

	window.__setupFileUploadFrame("${internalId}");
	
	window["fileUpload_callback_${internalId}"] = function(err, resp) {
		if (err) {
			return;
		}
		$("#${inputIdForFileId}").val(resp.fileId);
		$("#${inputIdForFileName}").val(resp.fileName);
	}
</script>
