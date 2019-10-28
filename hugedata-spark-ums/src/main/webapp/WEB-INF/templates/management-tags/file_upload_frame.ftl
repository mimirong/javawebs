<script>
	function onSubmitForm() {
		document.getElementById("upload").style.display = "none";
		document.getElementById("uploading").style.display = "block";
	}
</script>

<div id="upload">
	<form action="${action}" method="post" enctype="multipart/form-data" onsubmit="onSubmitForm()">
		<input type="hidden" name="internalId" value="${internalId}" />
		<input type="file" name="${name}" id="${id}" placeholder="${placeholder}" class="form-control" style="width:180px; float:left;" />
		<button type="submit" class="btn btn-primary btn-sm" style="float:left; margin-left:4px;">上传</button>
	</form>
</div>
<div id="uploading" style="display:none;">
	<img src="${contextPath}/images/loading_round.gif" style="vertical-align:middle;" />
	<span style="font-size:12px;">正在上传...</span>
</div>
