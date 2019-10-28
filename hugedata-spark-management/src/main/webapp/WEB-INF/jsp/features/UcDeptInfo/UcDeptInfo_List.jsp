<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>部门管理</title>
	<script>
		currentMenu = "depts";
	</script>
	<script type="text/javascript" src="${contextPath}/static/js/bootstrap-treeview.min.js"></script>
	<style>
		.deptTreeIcon { font-size:10px; }
	</style>
</head>
<body>

	<div class="feature-content" class="ng-cloak" ng-app="">

		<jsp:include page="../header.jsp">
			<jsp:param name="title" value="部门管理" />
		</jsp:include>
		
		<m:left width="220px" columnSpacing="8px">
			<%-- LEFT --%>
			<div style="border:solid 1px #ddd; margin-top:6px; min-height:500px;">
				<div id="deptTree">
				</div>
			</div>
		</m:left>
		
		<m:right>
			
			<%-- RESULT --%>
			<table id="data" class="table table-striped table-bordered" cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<thead>
					<tr>
						<td style="width:60px;" class="text-center">
							<input type="checkbox" class="btnSelectAll" />
						</td>
						<td>名称</td>
						<td style="width:160px;">创建时间</td>
						<td style="width:100px;" class="text-center">操作</td>
					</tr>
				</thead>
			</table>
			
			<%-- OPERATIONS --%>
			<div class="operation-panel">
				<m:privilege rightId="org_structure/depts/add">
					<button type="button" class="btnAddDept btn btn-primary btn-md">创建部门</button>
				</m:privilege>
				<m:privilege rightId="org_structure/depts/delete">
					<button type="button" class="btnDeleteSelected btn btn-primary btn-md">删除所选部门</button>
				</m:privilege>
			</div>
			
		</m:right>

	</div>
	
	<m:dialog title="!创建部门" id="addDialog">
		<iframe width="560" height="230" frameborder="0"></iframe>
	</m:dialog>
	
	<m:dialog title="!修改部门" id="modifyDialog">
		<iframe width="560" height="230" frameborder="0"></iframe>
	</m:dialog>
	
	
	<script type="text/javascript">
		// 当前选择的部门的ID
		var selectedDept = null;
		
		// Columns
		var columns =[
			{
				data: null,
				orderable: false,
				createdCell: function(td) {
					$(td).css('text-align', 'center');
				},
				render: function(dept) {
					return '<input type="checkbox" class="btnSelect" data-row-id="' + dept.deptId + '" />';
				}
			}, {
				data: "name"
			}, {
				data: "createTime",
				render: function(val) {
					return DateFormat.format.date(new Date(val), 'yyyy/MM/dd HH:mm:ss');
				}
			}, {
				data: null,
				orderable: false,
				createdCell: function(td) {
					$(td).css('text-align', 'center');
				},
				render: function(dept) {
					var m = [];
					if (CommonsPrivileges.hasPrivilegeId("org_structure/depts/modify")) {
						m.push('<a href="#" class="btnModify" data-row-id="' + dept.deptId + '">修改</a> ');
					}
					if (CommonsPrivileges.hasPrivilegeId("org_structure/depts/delete")) {
						m.push('<a href="#" class="btnDelete" data-row-id="' + dept.deptId + '">删除</a> ');
					}
					return m.join('');
				}
			}
		];
			
		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
  			ajax: {
  				url: "listData",
  				data: function(data) {
					data.searchParams = JSON.stringify({
						parentDeptId: (selectedDept ? selectedDept.deptId : null)
					});
				}
  			},
			lengthChange: false,
			serverSide : true,
			searching : false,
			processing: true,
			order: [[2, "desc"]],
			columns : columns
		}));
		
		// 绑定事件
		ListFeaturePage.autobind(dt);
		
		// 修改按钮
		dt.on('draw', function(settings, data) {
			$(".btnModifyWithType").off().on("click", function(e) {
				var id = $(e.target).attr("data-row-id");
				for (var i = 0; i < dt.data().length; i++) {
					var row = dt.data()[i];
					if (row.id == id) {
						var url = "toModifyWithType." + ext + "?id=" + id + "&t=" + new Date().getTime();
						$("#modifyDialog iframe").attr("src", url);
						Dialog.show("modifyDialog");
					}
				}
			});
		});

		// 创建部门按钮
		(function() {
			var dialog = $("#addDialog");
			$("#addDialog iframe").attr("src", contextPath + "/empty");
			Dialog.bindOnHide("addDialog", function() {
				$("#addDialog iframe").attr("src", contextPath + "/empty");
			});
			$(".btnAddDept").click(function() {
				var parentDeptId = (selectedDept ? selectedDept.deptId : "");
				$("#addDialog iframe").attr("src", "toAdd?parentDeptId=" + parentDeptId + "&_t=" + new Date().getTime());
				Dialog.show("addDialog");
			});
		})();
		
		//----------------------------------------------------
		// LEFT
		//----------------------------------------------------
		
		// 加载左侧部门结构
		function reloadDeptTree() {
			CommonsDepts.loadDeptTree({
				createRootDept: true,
				selectedDeptId: (selectedDept ? selectedDept.deptId : null),
				callback: function(err, tree) {
					$("#deptTree").treeview({
						data: tree,
						showBorder: false,
						expandIcon: "fa fa-chevron-right deptTreeIcon",
						collapseIcon: "fa fa-chevron-down deptTreeIcon",
						onNodeSelected: function(e, data) {
							$('#deptTree').treeview(true).expandNode(data.nodeId);
							reloadDeptList(data);
						}
					});
				}
			});
		}

		// 重新加载右侧部门列表
		function reloadDeptList(dept) {
			if (dept.deptId == CommonsDepts.ROOT_DEPT_ID) {
				dept = null;
			}
			selectedDept = dept;
			dt.ajax.reload();
		}
	
		// 初始化时加载左侧部门树
		$(reloadDeptTree);
		
		// 新增、修改完成后刷新左侧部门树
		window.onReloadList = function() {
			reloadDeptTree();
		};
		
	</script>

</body>