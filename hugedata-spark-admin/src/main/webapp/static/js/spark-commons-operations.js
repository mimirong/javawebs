//---------------------------------------------------------------------------------------------------
// FORM PAGE EVENTS
//---------------------------------------------------------------------------------------------------

var FormFeaturePage = (function() {
	var obj = {};
	
	obj.autoload = function(entity) {
		$.each(entity, function(k, v) {
			var element = $("#" + k);
			if (element.length > 0) {
				if (element.is("select")) {
					element.val(v).trigger("change");
				} else if (element.is("textarea")) {
					element.html(v);
				} else if (element.is("input[type='checkbox']")) {
					if (typeof v === "string") {
						element.prop("checked", v === "true");
					} else if (typeof v === "boolean") {
						element.prop("checked", v);
					}
				} else {
					element.val(v);
				}
			}
		});
	};
	
	return obj;
})();


//---------------------------------------------------------------------------------------------------
// LIST PAGE EVENTS
//---------------------------------------------------------------------------------------------------

var ListFeaturePage = (function() {
	var obj = {};

	// 自动绑定查询页面所有事件
	obj.autobind = function(dt) {
		obj.bindQueryEvents(dt);
		obj.bindDeleteAllEvents(dt);
		obj.bindAddEvents(dt);
		obj.bindImportEvents(dt);
		dt.on('draw', function(settings, data) {
			obj.bindRowModifyEvents(dt);
			obj.bindRowDeleteEvents(dt);
			obj.bindSelectEvents(dt);
		});

		window.reloadList = function() {
			dt.ajax.reload(null, false);
		};
	};
	
	
	
	// Private (不常用方法) ===========================
	
	// 绑定查询事件
	obj.bindQueryEvents = function(dt) {
		$('.btnQuery').click(function() {
			dt.ajax.reload(null, false);
		});
	};
	
	// 绑定新增事件
	obj.bindAddEvents = function(dt) {
		var dialog = $("#addDialog");
		if (dialog.length > 0) {
			$("#addDialog iframe").attr("src", contextPath + "/empty");
			Dialog.bindOnHide("addDialog", function() {
				$("#addDialog iframe").attr("src", contextPath + "/empty");
			});
			$(".btnAdd").click(function() {
				$("#addDialog iframe").attr("src", "toAdd?_t=" + new Date().getTime());
				Dialog.show("addDialog");
			});
		} else {
			$(".btnAdd").click(function() {
				location.href = "toAdd";
			});	
		}
	};
	
	// 绑定导入事件
	obj.bindImportEvents = function(dt) {
		var dialog = $("#importDialog");
		if (dialog.length > 0) {
			$("#importDialog iframe").attr("src", contextPath + "/empty");
			Dialog.bindOnHide("importDialog", function() {
				$("#importDialog iframe").attr("src", contextPath + "/empty");
			});
			$(".btnImport").click(function() {
				$("#importDialog iframe").attr("src", "toImport?_t=" + new Date().getTime());
				Dialog.show("importDialog");
			});
		} else {
			$(".btnImport").click(function() {
				location.href = "toImport";
			});	
		}
	};
	
	// 绑定每行选择和全选事件
	obj.bindSelectEvents = function(dt) {
		var rows = $('.btnSelect');
		var all = $('.btnSelectAll');
		rows.change(function() {
			all.prop('checked', ($('.btnSelect:checked').length == rows.length));
		});
		all.change(function() {
			rows.prop('checked', all.prop('checked'));
		});
	};

	// 绑定每行的修按钮事件
	obj.bindRowModifyEvents = function(dt) {
		var dialog = $("#modifyDialog");
		if (dialog.length > 0) {
			$("#modifyDialog iframe").attr("src", contextPath + "/empty");
			Dialog.bindOnHide("modifyDialog", function() {
				$("#modifyDialog iframe").attr("src", contextPath + "/empty");
			});
			$(".btnModify").click(function(e) {
				var id = $(e.target).attr('data-row-id');
				$("#modifyDialog iframe").attr("src", "toModify?id=" + id + "&t=" + new Date().getTime());
				Dialog.show("modifyDialog");
			});
		} else {
			$("td .btnModify").click(function(e) {
				var id = $(e.target).attr("data-row-id");
				location.href = "toModify?id=" + id + "&t=" + new Date().getTime();
			});
		}
	};

	// 绑定每行的删除按钮事件
	obj.bindRowDeleteEvents = function(dt) {
		$('td .btnDelete').click(function(e) {
			if (!confirm('确认删除?')) {
				return;
			}
			var deleteId = $(e.target).attr('data-row-id');
			var ajax = $.ajax('delete', {
				method: 'post',
				dataType: 'json',
				data: {
					idlist: deleteId,
					t: new Date().getTime()
				}
			})
			ajax.done(function(resp) {
				if (resp && resp.result == 0) {
					showInfoMessage('删除成功');
					dt.ajax.reload(null, false);
				} else {
					showErrorMessage(resp.message);
				}
			});
			ajax.fail(function() {
				showErrorMessage('删除失败，请稍后重试')
			});
		});
	};
	
	// 绑定批量删除事件
	obj.bindDeleteAllEvents = function(dt) {
		$('.btnDeleteSelected').click(function() {
			var sels = $('.btnSelect:checked');
			if (sels.length == 0) {
				alert('请选择需要删除的记录')
				return;
			}
			var ids = [];
			for (var i = 0; i < sels.length; i++) {
				ids.push($(sels[i]).attr('data-row-id'));
			}
			if (!confirm('确认删除?')) {
				return;
			}
			var ajax = $.ajax('delete', {
				method: 'post',
				dataType: 'json',
				data: {
					idlist: ids.join(),
					t: new Date().getTime()
				}
			})
			ajax.done(function(resp) {
				if (resp && resp.result == 0) {
					showInfoMessage('删除成功');
					dt.ajax.reload(null, false);
				} else {
					showErrorMessage(resp.message);
				}
			});
			ajax.fail(function() {
				showErrorMessage('删除失败，请稍后重试')
			});
		});
	};
	
	return obj;
})();

