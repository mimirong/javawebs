<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="decorator" content="simple"> 
	<title>修改部门</title>
</head>
<body>

	<div class="feature-content-internal">

		<jsp:include page="../header.jsp" />
		
		<%-- FORM --%>
		<div class="form-wrapper">
		
			<form class="form-horizontal" role="form" method="post" action="doModify" id="mainForm" onsubmit="return false;">
				
				<input type="hidden" name="deptId" id="deptId" />
			
				<div class="form-group">
					<label for="name" class="col-xs-3 control-label">部门名称 <span class="required">*</span>：</label>
					<div class="col-xs-9">
						<input type="text" class="form-control" id="name" name="name" placeholder="输入部门名称" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="name" class="col-xs-3 control-label">上级部门 <span class="required">*</span>：</label>
					<div class="col-xs-9">
						<select id="parentDeptId" name="parentDeptId" class="form-control">
							<option value="">顶级部门</option>
						</select>
					</div>
				</div>
			</form>

			<div class="text-center">
				<button type="button" class="btn btn-primary btn-md" onclick="$('#mainForm').submit();">修改部门</button>
			</div>			
		</div><!-- .form-wrapper -->
		
	</div>
	
	<script type="text/javascript">
		var entity = ${entityJson};
		var selectedParentDeptId = "" + entity.parentDeptId;
		FormFeaturePage.autoload(entity);
	
		$('#mainForm').validate({
			focusInvalid : true,
		    errorElement: 'div',
		    errorClass: 'help-block text-danger',
			rules: {
				name: { required:true }
			},
			messages: {
				name: { required:"请输入部门名称" }
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		
		// 加载部门列表
		CommonsDepts.loadDeptTree({
			callback: function(err, deptTree) {
				if (err) {
					alert(err);
					return;
				}
				var deptlist = CommonsDepts.toIdentedList(deptTree);
				
				$.each(deptlist, function(i, dept) {
					var op = new Option(dept.indentionText, dept.deptId);
					if (("" + dept.deptId) == selectedParentDeptId) {
						op.selected = true;
					}
					$("#parentDeptId").append($(op));
				});
			}
		});
	</script>

</body>