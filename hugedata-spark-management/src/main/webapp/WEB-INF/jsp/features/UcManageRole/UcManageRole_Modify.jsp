<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="decorator" content="simple"> 
	<title>修改角色</title>
</head>
<body>

	<div class="feature-content-internal">

		<jsp:include page="../header.jsp" />
		
		<%-- FORM --%>
		<div class="form-wrapper">
		
			<form class="form-horizontal" role="form" method="post" action="doModify" id="mainForm" onsubmit="return false;">
				<input type="hidden" id="roleId" name="roleId" />

				<div class="form-group">
					<label for="roleId" class="col-xs-3 control-label">角色ID <span class="required">*</span>：</label>
					<div class="col-xs-9">
						<input type="text" class="form-control" id="roleIdDisplay" disabled="disabled" readonly="readonly" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="name" class="col-xs-3 control-label">角色名称 <span class="required">*</span>：</label>
					<div class="col-xs-9">
						<input type="text" class="form-control" id="name" name="name" maxlength="32" placeholder="输入角色名称" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="description" class="col-xs-3 control-label">描述 <span class="required">*</span>：</label>
					<div class="col-xs-9">
						<input type="text" class="form-control" id="description" name="description" maxlength="256" placeholder="输入角色描述" />
					</div>
				</div>
				
			</form>

			<div class="text-center">
				<button type="button" class="btn btn-primary btn-md" onclick="$('#mainForm').submit();">修改角色</button>
			</div>			
		</div><!-- .form-wrapper -->
		
	</div>
	
	<script type="text/javascript">
		var entity = ${entityJson};
		FormFeaturePage.autoload(entity);
		$("#roleIdDisplay").val(entity.roleId);
	
		$('#mainForm').validate({
			focusInvalid : true,
		    errorElement: 'div',
		    errorClass: 'help-block text-danger',
			rules: {
				roleId:      { required:true },
				name:        { required:true },
				description: { required:true }
			},
			messages: {
				roleId:      { required:"请输入角色ID" },
				name:        { required:"请输入角色名称" },
				description: { required:"请输入角色描述" }
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		
	</script>

</body>