<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>角色管理</title>
	<script>
		currentMenu = "depts";
	</script>
</head>
<body>

	<div class="feature-content" class="ng-cloak" ng-app="">

		<jsp:include page="../header.jsp">
			<jsp:param name="title" value="角色管理" />
		</jsp:include>
		
		<%-- RESULT --%>
		<table id="data" class="table table-striped table-bordered" cellpadding="0" cellspacing="0" border="0" style="width:100%;">
			<thead>
				<tr>
					<td style="width:60px;" class="text-center">
						<input type="checkbox" class="btnSelectAll" />
					</td>
					<td style="width:180px;">角色ID</td>
					<td style="width:180px;">角色名称</td>
					<td>描述</td>
					<td style="width:150px;" class="text-center">操作</td>
				</tr>
			</thead>
		</table>
			
		<%-- OPERATIONS --%>
		<div class="operation-panel">
			<m:privilege rightId="org_structure/roles/add">
				<button type="button" class="btnAdd btn btn-primary btn-md">创建角色</button>
			</m:privilege>
			<m:privilege rightId="org_structure/roles/delete">
				<button type="button" class="btnDeleteSelected btn btn-primary btn-md">删除所选角色</button>
			</m:privilege>
		</div>
			
	</div>
	
	<m:dialog title="!创建角色" id="addDialog">
		<iframe width="560" height="280" frameborder="0" src="${contextPath}/empty"></iframe>
	</m:dialog>
	
	<m:dialog title="!修改角色" id="modifyDialog">
		<iframe width="560" height="280" frameborder="0" src="${contextPath}/empty"></iframe>
	</m:dialog>
	
	<m:dialog title="!选择权限" id="selectRightsDialog" width="770px">
		<iframe width="750" height="500" frameborder="0" src="${contextPath}/empty"></iframe>
	</m:dialog>
	
	
	<script type="text/javascript">
		// Columns
		var columns =[
			{
				data: null,
				orderable: false,
				createdCell: function(td) {
					$(td).css('text-align', 'center');
				},
				render: function(role) {
					return '<input type="checkbox" class="btnSelect" data-row-id="' + role.roleId + '" />';
				}
			}, {
				data: "roleId"
			}, {
				data: "name"
			}, {
				data: "description"
			}, {
				data: null,
				orderable: false,
				createdCell: function(td) {
					$(td).css('text-align', 'center');
				},
				render: function(role) {
					var m = [];
					if (CommonsPrivileges.hasPrivilegeId("org_structure/roles/selectRights")) {
						m.push('<a href="#" class="btnSelectRights" data-row-id="' + role.roleId + '">选择权限</a> ');
					}
					if (CommonsPrivileges.hasPrivilegeId("org_structure/roles/modify")) {
						m.push('<a href="#" class="btnModify" data-row-id="' + role.roleId + '">修改</a> ');
					}
					if (CommonsPrivileges.hasPrivilegeId("org_structure/roles/delete")) {
						m.push('<a href="#" class="btnDelete" data-row-id="' + role.roleId + '">删除</a> ');
					}
					return m.join('');
				}
			}
		];
			
		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
  			ajax: {
  				url: "listData"
  			},
			lengthChange: false,
			serverSide : true,
			searching : false,
			processing: true,
			order: [[1, "desc"]],
			columns : columns
		}));
		
		// 绑定事件
		ListFeaturePage.autobind(dt);

		// 选择权限按钮
		dt.on('draw', function(settings, data) {
			$(".btnSelectRights").off().on("click", function(e) {
				var id = $(e.target).attr("data-row-id");
				
				Dialog.bindOnHide("selectRightsDialog", function() {
					$("#selectRightsDialog iframe").attr("src", contextPath + "/empty");
				});
				$("#selectRightsDialog iframe").attr("src", "toSelectRights?roleId=" + id + "&t=" + new Date().getTime());
				Dialog.show("selectRightsDialog");
			});
		});
	</script>

</body>