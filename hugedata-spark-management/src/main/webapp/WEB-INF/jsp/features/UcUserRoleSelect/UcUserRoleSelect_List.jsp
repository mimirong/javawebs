<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>选择需要分配的角色</title>
	<meta name="decorator" content="simple"> 
</head>
<body>

	<div class="feature-content" style="width:98%">

		<%-- RESULT --%>
		<table id="data" class="table table-striped table-bordered" cellpadding="0" cellspacing="0" border="0" style="width:100%;">
			<thead>
				<tr>
					<td style="width:60px;" class="text-center">&nbsp;</td>
					<td style="width:180px;">角色ID</td>
					<td style="width:180px;">角色名称</td>
					<td>描述</td>
				</tr>
			</thead>
		</table>
	
		<!-- Operations -->
		<div class="text-center" style="clear:both;">
			<button type="button" class="btn btn-primary btn-md btnSave">保存</button>
		</div>	
			
	</div>
	
	
	<script type="text/javascript">
		var userId = ${param.userId};
	
		// Columns
		var columns =[
			{
				data: null,
				orderable: false,
				createdCell: function(td) {
					$(td).css('text-align', 'center');
				},
				render: function(role) {
					var checked = '';
					if (role.selected) {
						checked = 'checked="checked"';
					}
					return '<input type="checkbox" class="btnSelectRole" data-row-id="' + role.roleId + '"'
							+ checked + '/>';
				}
			}, {
				data: "roleId"
			}, {
				data: "name"
			}, {
				data: "description"
			}
		];
			
		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
  			ajax: {
  				url: "listData",
  				data: function(data) {
					data.searchParams = JSON.stringify({
						userId: userId
					});
				}
  			},
			lengthChange: false,
			serverSide : true,
			searching : false,
			processing: true,
			ordering: false,
			paging: false,
			info: false,
			columns : columns
		}));
		
		// 绑定事件
		ListFeaturePage.autobind(dt);

		// 保存
		$(".btnSave").on("click", function() {
			var selectedRoles = [];
			$.each($(".btnSelectRole:checked"), function(i, item) {
				var roleId = $(item).attr("data-row-id");
				selectedRoles.push(roleId);
			});
			
			$.ajax({
				url: "saveUserRoles",
				type: "post",
				dataType: "json",
				data: {
					roles: selectedRoles,
					userId: userId
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						window.parent.Dialog.hide("selectRolesDialog");
						window.parent.reloadList();
					} else {
						alert(resp.message);
					}
				},
				error: function() {
					alert("保存失败");
				}
			});
		});
	</script>

</body>