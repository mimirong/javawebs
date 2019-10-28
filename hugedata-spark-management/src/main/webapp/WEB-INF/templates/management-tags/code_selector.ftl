
<!-- CODE SELECTOR START - ${id} -->

<input type="hidden" name="${inputNameForCode}" id="${inputIdForCode}" value="" />
<input type="hidden" name="${inputNameForName}" id="${inputIdForName}" value="" />
<#if inputIdForFullName??>
<input type="hidden" name="${inputIdForFullName}" id="${inputIdForFullName}" value="" />
</#if>
<#if inputIdForFullCode??>
<input type="hidden" name="${inputIdForFullCode}" id="${inputIdForFullCode}" value="" />
</#if>

<div class="codeSelector" id="codeSelector_${id}" style="${wrapperStyle}">
</div>

<script>
	$(function() {
		CodeSelector.initSelector({
			"id": "${id}", 
			"codeGroup": "${codeGroup}",
			"value": "${value!}",
			"inputIdForCode": "${inputIdForCode}", 
			"inputIdForName": "${inputIdForName}",
			"inputIdForFullName": "${inputIdForFullName!}",
			"inputIdForFullCode": "${inputIdForFullCode!}",
			"itemWidth": "${itemWidth}",
			"allowSelectingNonLeaf": ${allowSelectingNonLeaf?c},
			"promptText": "${promptText}",
			"onchange": "${onchange!}",
			"maxLevel": ${maxLevel},
			"readonly": ${readonly?c}
		});
	});
</script>

<!-- CODE SELECTOR END - ${id} -->
