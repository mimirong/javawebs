//var CommonsDepts = (function() {
//	var obj = {};
//
//	obj.ROOT_DEPT_NAME = "所有部门";
//	obj.ROOT_DEPT_ID = "-1";
//
//	// 加载部门树
//	//   {
//	//     createRootDept  默认false  是否创建根部门
//	//     selectedDeptId  默认null   选中的部门ID，如果创建根部门，默认为根部门
//	//     callback        必须.      加载完成回调，类型为function(errorMessage, rootDeptArray, deptList)
//	//   }
//	obj.loadDeptTree = function(params) {
//		if (!params.callback) {
//			return;
//		}
//		if (!params.selectedDeptId && params.createRootDept) {
//			params.selectedDeptId = obj.ROOT_DEPT_ID;
//		}
//
//		$.ajax({
//			url: contextPath + "/features/UcDeptInfo/queryAllDepartments?_t=" + new Date().getTime(),
//			type: "post",
//			dataType: "json",
//			success: function(resp) {
//				if (resp && resp.result == 0) {
//					createDeptTree(resp.data, params);
//				} else {
//					callback(resp.message);
//				}
//			},
//			error: function() {
//				callback("查询部门信息失败.");
//			}
//		});
//	};
//
//	// 将部门列表构造为树
//	function createDeptTree(depts, params) {
//		var root = { nodes:[] };
//		var deptById = {};
//
//		if (params.createRootDept) {
//			// 创建顶级部门
//			var createdRootDept = {
//					name: obj.ROOT_DEPT_NAME,
//					text: obj.ROOT_DEPT_NAME,
//					deptId: obj.ROOT_DEPT_ID,
//					nodes: []
//			};
//			deptById[obj.ROOT_DEPT_ID] = createdRootDept;
//			root.nodes.push(createdRootDept);
//
//			// 处理原有的顶级部门
//			for (var i = 0; i < depts.length; i++) {
//				var d = depts[i];
//				d.nodes = [];
//				d.text = d.name;
//				deptById[d.deptId] = d;
//				if (!d.parentDeptId) {
//					d.parentDeptId = obj.ROOT_DEPT_ID;
//				}
//			}
//
//			depts.push(createdRootDept);
//		} else {
//			// 处理顶级部门
//			for (var i = 0; i < depts.length; i++) {
//				var d = depts[i];
//				d.nodes = [];
//				d.text = d.name;
//				deptById[d.deptId] = d;
//				if (!d.parentDeptId) {
//					root.nodes.push(d);
//				}
//			}
//		}
//
//		// 处理子级部门
//		for (var i = 0; i < depts.length; i++) {
//			var d = depts[i];
//			if (d.parentDeptId) {
//				var parent = deptById[d.parentDeptId];
//				if (parent) {
//					parent.nodes.push(d);
//				}
//			}
//		}
//
//		// 处理选中部门
//		for (var i = 0; i < depts.length; i++) {
//			if (depts[i].deptId == params.selectedDeptId) {
//				depts[i].state = {
//					selected: true,
//					expanded: true
//				};
//
//				var d = depts[i];
//				while (d.parentDeptId) {
//					var p = deptById[d.parentDeptId];
//					if (p) {
//						p.state = { expanded: true };
//						d = p;
//					}
//				}
//			}
//		}
//
//		// 返回
//		params.callback(null, root.nodes, depts);
//	}
//
//	// 将部门结构树转为列表，其中部门名称将缩进显示
//	obj.toIdentedList = function(deptTree, indention) {
//		if (!indention) {
//			indention = 0;
//		}
//		var m = [];
//		for (var i = 0; i < deptTree.length; i++) {
//			var d = deptTree[i];
//			d.indentionText = createIndention(indention) + d.text;
//			m.push(d);
//
//			if (d.nodes && d.nodes.length > 0) {
//				var subdepts = obj.toIdentedList(d.nodes, indention + 1);
//				for (var j = 0; j < subdepts.length; j++) {
//					m.push(subdepts[j]);
//				}
//			}
//		}
//		return m;
//	};
//
//	// 创建n个空格的字符串
//	function createIndention(indention) {
//		var a = "";
//		for (var i = 0; i < indention; i++) {
//			a += "--";
//		}
//		return a;
//	}
//
//	return obj;
//})();