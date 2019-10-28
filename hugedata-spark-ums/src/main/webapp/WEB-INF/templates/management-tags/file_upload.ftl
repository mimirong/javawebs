<div>
	<iframe id="fileUpload_${internalId}" height="40" frameborder="0" src="" scrolling="no"></iframe>
</div>

<script>
	if (!window.__fileUploadDialogData) {
		window.__fileUploadDialogData = {};
	}
	
	if (!window.__setupFileUploadFrame) {
		window.__setupFileUploadFrame = function(internalId) {
			var def = window.__fileUploadDialogData[internalId];
			if (def == null) {
				alert("无法显示上传组件(J)");
				return;
			}
			var url = "${contextPath}/commons-web/file-upload/toFileUpload-simple.aspx";
			url += "?id=" + encodeURIComponent(def.id);
			url += "&name=" + encodeURIComponent(def.name);
			url += "&placeholder=" + encodeURIComponent(def.placeholder);
			url += "&action=" + encodeURIComponent(def.action);
			url += "&callback=" + encodeURIComponent(def.callback);
			url += "&internalId=" + encodeURIComponent(internalId);
			$("#fileUpload_${internalId}").attr("src", url); 
		};
	}

	window.__fileUploadDialogData["${internalId}"] = {
		id          : "${id}",
		name        : "${name}",
		placeholder : "${placeholder}",
		action      : "${action}",
		callback    : "${callback}"
	};

	window.__setupFileUploadFrame("${internalId}");
</script>