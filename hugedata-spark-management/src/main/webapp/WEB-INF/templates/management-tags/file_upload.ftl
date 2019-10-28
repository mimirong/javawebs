<input type="hidden" name="${inputNameForFileId}" id="${inputIdForFileId}" />
<input type="hidden" name="${inputNameForFileName}" id="${inputIdForFileName}" />

<div class="commons-file-upload" data-internal-id="${internalId}">
	<iframe id="fileUpload_${internalId}" height="40" width="${width}" frameborder="0" src="" scrolling="no"></iframe>
</div>

<script>
	<#-- window.__fileUploadDialogData -->
	if (!window.__fileUploadDialogData) {
		window.__fileUploadDialogData = {};
	}
	window.__fileUploadDialogData["${internalId}"] = {
		inputIdForFileId     : "${inputIdForFileId}",
		inputNameForFileId   : "${inputNameForFileId}",
		inputIdForFileName   : "${inputIdForFileName}",
		inputNameForFileName : "${inputNameForFileName}",
		action               : "${action}",
		allowedExtensions    : "${allowedExtensions}",
		prompt               : "${prompt}",
		maxFileSize          : "${maxFileSize}",
		callback             : "${callback}",
		autoReset            : ${autoReset?c}
	};
	
	<#-- window.__setupFileUploadFrame -->
	if (!window.__setupFileUploadFrame) {
		window.__setupFileUploadFrame = function(internalId) {
			var def = window.__fileUploadDialogData[internalId];
			if (def == null) {
				alert("无法显示上传组件");
				return;
			}
			
			<#-- Reload iframe -->
			var url = "${contextPath}/commons-web/file-upload/toFileUpload-simple";
			url += "?action=" + encodeURIComponent(def.action);
			url += "&internalId=" + encodeURIComponent(internalId);
			url += "&_t=" + new Date().getTime();
			$("#fileUpload_" + internalId).attr("src", url);
			
			<#-- Clear hidden input -->
			$("#" + def.inputIdForFileId).val("");
			$("#" + def.inputIdForFileName).val("");
		};
	}

	<#-- Initialize -->
	window.__setupFileUploadFrame("${internalId}");
	
	<#-- Callback -->
	window["fileUpload_callback_${internalId}"] = function(err, resp) {
		var callback = "${callback}";
		var callbackFn = null;
		if (callback != "") {
			callbackFn = window[callback];
			if (typeof callbackFn != "function") {
				callbackFn = null;
			}
		} 
		
		if (err) {
			if (MU && MU.msgTips) {
				MU.msgTips("error", err, 1200);
			} else {
				alert(err);
			}
			if (callbackFn) {
				callbackFn(err, null);
			}
		} else {
			$("#${inputIdForFileId}").val(resp.fileId);
			$("#${inputIdForFileName}").val(resp.fileName);
			if (callbackFn) {
				callbackFn(null, resp);
			}
		}
	}

	<#-- FileUpload Global Object -->
	if (!window.FileUpload) {
		var obj = {};
		window.FileUpload = obj;
		
		<#-- reset -->
		obj.reset = function(selector) {
			var internalId = $(selector + " .commons-file-upload").attr("data-internal-id");
			var def = window.__fileUploadDialogData[internalId];
			if (!def) {
				try { console.error("Invalid file upload component id: " + internalId); } catch(e) {}
				return;
			}
			
			if (internalId && internalId != "") {
				window.__setupFileUploadFrame(internalId);
			}
		};
		
		<#-- setFile -->
		obj.setFile = function(selector, fileId, fileName) {
			var internalId = $(selector + " .commons-file-upload").attr("data-internal-id");
			var def = window.__fileUploadDialogData[internalId];
			if (!def) {
				try { console.error("Invalid file upload component id: " + internalId); } catch(e) {}
				return;
			}
			
			if (!fileId || fileId == "" || !fileName || fileName == "") {
				obj.reset(selector);
				return;
			}
			
			$("#" + def.inputIdForFileId).val(fileId);
			$("#" + def.inputIdForFileName).val(fileName);
			
			var url = "${contextPath}/commons-web/file-upload/toFileUploadResult-simple";
			url += "?err=";
			url += "&internalId=" + internalId;
			url += "&response=" + encodeURIComponent(JSON.stringify({ fileId:fileId, fileName:fileName }));
			url += "&noCallback=true";
			$("#fileUpload_" + internalId).attr("src", url);
		};
	}
</script>
