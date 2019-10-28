<div class="panel panel-default" style="display:none;" id="successDiv">
	<div class="panel-body" style="padding-top:2px; padding-bottom:2px;">
		<span>上传成功&nbsp;</span>
		<button id="btnReupload" class="btn btn-primary btn-sm">重新上传</button>
	</div>
</div>

<script>
	(function() {
		var err = "${err!!}";
		var internalId = "${internalId!!}";
		var resp = ${response};
		
		var def = window.parent.__fileUploadDialogData[internalId];
		if (!def) {
			alert("无法获取文件上传组件信息.");
			return;
		}
		
		var callbackFn = window.parent[def.callback];
		if (!callbackFn) {
			alert("无法获取回调信息.");
			return;
		}
		
		if (!err || err == "") {
			callbackFn(null, resp);
			$("#successDiv").show();
		} else {
			callbackFn(err, resp);
			window.parent.__setupFileUploadFrame(internalId);
		}

		$("#btnReupload").on("click", function() {
			window.parent.__setupFileUploadFrame(internalId);
		});
		
	})();
</script>
