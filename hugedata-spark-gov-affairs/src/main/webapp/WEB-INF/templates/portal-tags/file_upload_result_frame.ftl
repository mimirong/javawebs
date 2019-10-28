<div style="display:none;" id="successDiv">
	<div style="padding:10px 0;">
		<span class="filename">&nbsp;</span>
		<a href="javascript:;" id="btnRemove" class="btn" style="vertical-align:middle; line-height:22px; width:28px; height:22px; padding:0 22px;">删除附件</a>
	</div>
</div>

<script>
	(function() {
		var err = "${err!!}";
		var internalId = "${internalId!!}";
		var resp = ${response};
		
		$("body").css("background-color", "white");	
		$(".filename").html(resp.fileName);
		
		var def = window.parent.__fileUploadDialogData[internalId];
		if (!def) {
			alert("无法获取文件上传组件信息.");
			return;
		}
		
		var callbackFn = window.parent["fileUpload_callback_${internalId}"];
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

		
		$("#btnRemove").on("click", function() {
			window.parent.__setupFileUploadFrame(internalId);
		});
	})();
</script>
