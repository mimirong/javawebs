<div style="display:none;" id="successDiv">
	<div style="padding:6px 0;">
		<span class="filename" style="vertical-align:middle;">&nbsp;</span>
		<span>&nbsp;</span>
		<#--
    	<a href="javascript:;" id="btnReSubmit" class="btn" style="vertical-align:middle; margin-right:6px; line-height:22px; width:28px; height:22px; padding:0 22px;">重新上传</a>
    	-->
    	<a href="javascript:;" id="btnRemove" class="btn" style="vertical-align:middle; line-height:22px; width:28px; height:22px; padding:0 22px;">删除附件</a>
	</div>
</div>

<script>
	$("body").css("background-color", "white");
	
	(function() {
		var err = "${err!!}";
		var internalId = "${internalId!!}";
		var resp = ${response};
		
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
		
		if (def.autoReset) {
			$("#btnReSubmit").hide();
			$("#btnRemove").hide();
		}
		
		if (!err || err == "") {
			callbackFn(null, resp);
			if (def.autoReset) {
				window.parent.__setupFileUploadFrame(internalId);
			} else {
				$("#successDiv").show();
			}
		} else {
			callbackFn(err, resp);
			window.parent.__setupFileUploadFrame(internalId);
		}

		$("#btnReSubmit").on("click", function() {
			window.parent.__setupFileUploadFrame(internalId);
		});
		
		$("#btnRemove").on("click", function() {
			window.parent.__setupFileUploadFrame(internalId);
		});
	})();
</script>
