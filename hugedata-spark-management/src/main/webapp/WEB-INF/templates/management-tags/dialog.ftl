<!-- START DIALOG : ${id} -->
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-labelledby="${id}" aria-hidden="true">
	<div class="modal-dialog"<#if width??> style="width:${width}"</#if>>
		<div class="modal-content">

			<#-- TITLE BAR -->		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" style="font-size:28px;">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="${id}_title">${title}</h4>
			</div>
			
			<#-- DIALOG CONTENT -->
			<div class="modal-body"<#if height??> style="height:${height}"</#if>>
				${contentHtml}
			</div>
			
			<#-- BOTTOM BUTTONS -->
			<#if buttons??>
				<div class="modal-footer">
					<#list buttons as b>
						<#if b.type == 'close'>
							<button type="button" class="btn btn-default" data-dismiss="modal">${b.label}</button>
						<#else>
							<button type="button" class="btn btn-primary"<#t>
							<#if b.id??> id="${b.id}"</#if><#t>
							<#if b.onclick??> onclick="${b.onclick}"</#if><#t>
							<#if b.ngClick??> ng-click="${b.ngClick}"</#if><#t>
							>${b.label}</button>
						</#if>
					</#list>
				</div>
			</#if>
		</div>
	</div>
</div>
<!-- END DIALOG : ${id} -->