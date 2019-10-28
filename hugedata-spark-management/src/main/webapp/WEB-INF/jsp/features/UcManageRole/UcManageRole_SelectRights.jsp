<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="decorator" content="simple"> 
	<title>选择角色权限</title>
	<style>
		.right-item { padding:6px; margin-right:4px; }
		.right-lv1 { width:100%; clear:both; font-size:16px; font-weight:bold; }
		.right-lv2-wrapper { clear:both; margin-left:12px; }
		.right-lv2 { float:left; clear:both; font-size:13px; font-weight:bold; }
		.right-lv3-wrapper { }
		.right-lv3 { float:left; font-size:13px; }
		.right-lv3 label { font-weight:normal; }
	</style>
</head>
<body>

	<div class="feature-content-internal">

		<jsp:include page="../header.jsp" />
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" style="width:100%;">
			<li role="presentation" class="active"><a href="#tabBasic" role="tab" data-toggle="tab">基本配置</a></li>
			<li role="presentation"><a href="#tabPortal" role="tab" data-toggle="tab">门户内容</a></li>
		</ul>
	
		<!-- Tab panes -->
		<div class="tab-content" style="width:100%;">
			<div role="tabpanel" class="tab-pane active" id="tabBasic">bb</div>
			<div role="tabpanel" class="tab-pane" id="tabPortal">aa</div>
		</div>
	
		<!-- Operations -->
		<div class="text-center" style="clear:both;">
			<button type="button" class="btn btn-primary btn-md btnSave">保存</button>
		</div>			
	
	</div>
		
	
	<script type="text/javascript">
		var allRights = ${allRightsJson};
		var selectedRights = ${selectedRightsJson};
		var role = ${roleJson};
		
		// 创建选择项
		function buildForm() {
			var systems = {};
			$.each(allRights, function(i, right) {
				// 计算级别
				right.level = right.rightId.split("/").length;
				
				// 根据系统分开
				if (!systems[right.systemType]) {
					systems[right.systemType] = [];
				}
				systems[right.systemType].push(right);
			});

			buildFormForSystem("tabBasic", systems.BASIC);
			buildFormForSystem("tabPortal", systems.PORTAL);
			
			// 选中数据
			$.each(selectedRights, function(i, right) {
				var id = right.rightId.replace(/\//g, "__");
				$("#CHECK__" + id).prop("checked", true);
			});
		}
		
		// 创建单个系统的选择项
		function buildFormForSystem(wrapper, rights) {
			var m = [];
			var lastLevel = 1;
			$.each(rights, function(i, right) {
				var id = right.rightId.replace(/\//g, "__");
				if (right.level == 1) {
					if (lastLevel == 2) {
						m.push('</div> ');
					} else if (lastLevel == 3) {
						m.push('</div> </div> ');
					}
					m.push('<div class="right-lv1 right-item" id="WRAPPER__', id ,'"> ');
					m.push('<label> ');
					m.push('<input type="checkbox" class="right-check" id="CHECK__', id ,'" data-right-id="', right.rightId , '" /> ');
					m.push(right.name);
					m.push('</label> ')
					m.push('</div> ');
				} else if (right.level == 2) {
					if (lastLevel == 1) {
						m.push('<div class="right-lv2-wrapper"> ');
					} else if (lastLevel == 3) {
						m.push('</div> ');
					}
					m.push('<div class="right-lv2 right-item" id="WRAPPER__', id ,'"> ');
					m.push('<label> ');
					m.push('<input type="checkbox" class="right-check" id="CHECK__', id ,'" data-right-id="', right.rightId , '" /> ');
					m.push(right.name);
					m.push('</label> ')
					m.push('</div> ');
				} else if (right.level == 3) {
					if (lastLevel == 2) {
						m.push('<div class="right-lv3-wrapper"> ');
					}
					m.push('<div class="right-lv3 right-item" id="WRAPPER__', id ,'"> ');
					m.push('<label> ');
					m.push('<input type="checkbox" class="right-check" id="CHECK__', id ,'" data-right-id="', right.rightId , '" /> ');
					m.push(right.name);
					m.push('</label> ')
					m.push('</div> ');
				}
				lastLevel = right.level;
			});
			$("#" + wrapper).html(m.join(""));
		}
		
		// 初始化
		buildForm();

		
		// 选中处理
		$(".right-check").on("change", function(e) {
			var check = $(e.target);
			if (check.prop("checked")) {
				// 选中所有上级
				var splitid = check.attr("id").split("__");
				while (splitid.length >= 2) {
					var parent = splitid.join("__");
					$("#" + parent).prop("checked", true);
					splitid = splitid.slice(0, splitid.length - 1);
				}
			} else {
				// 取消选中所有下级
				var items = $(".right-check");
				$.each(items, function(i, item) {
					item = $(item);
					var id = item.attr("id");
					if (id.indexOf(check.attr("id")) == 0) {
						item.prop("checked", false);
					}
				});
			}
		});
		
		
		// 保存
		$(".btnSave").on("click", function() {
			var data = [];
			$.each($(".right-check:checked"), function(i, check) {
				var rightId = $(check).attr("data-right-id");
				data.push(rightId);
			});
			
			$.ajax({
				url: "doSelectRights?_t=" + new Date().getTime(),
				type: "post",
				dataType: "json",
				data: {
					selectedRights: data,
					roleId: role.roleId
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						window.parent.Dialog.hide("selectRightsDialog");
						window.parent.reloadList();
					} else {
						alert(resp.message);
					}
				},
				error: function() {
					alert("保存失败.");
				}
			});
		});
		
	</script>

</body>