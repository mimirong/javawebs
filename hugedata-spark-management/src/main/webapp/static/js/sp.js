/*
 * =================================================================================================
 *  调用接口
 * =================================================================================================
 *   SP.invoke(method, params, callback)
 *   参数: 
 *     method    String    请求method
 *     params    Object    请求参数
 *     callback  Function  成功和失败回调: function(err, resp)
 *                                          err  String 错误消息，成功时为null
 *                                          resp Object 服务器返回的结果
 * 示例：
 *   SP.invoke(
 *   	"user/login", 
 *      {
 *          loginName: "admin",
 *          password: "1"
 *      }, 
 *      function(err, resp) {
 *          if (err) {
 *              MU.msgTips("warning", err);
 *              return;
 *          }
 *          
 *          ......
 *      }
 *  ); 
 * 
 * =================================================================================================
 *  打印
 * =================================================================================================
 * SP.print(html, title, pagesize)
 * 参数:
 *     html      String  打印的HTML，如 $("#printWrapper").html()
 *     title     String  打印标题
 *     pagesize  String  打印纸张大小，默认为"A4 portrait"，其它支持的值"A4 landscape" "A3 portrait" "A3 landscape"
 * 
 * =================================================================================================
 *  导出
 * =================================================================================================
 * SP.exportTable(table, title, columnWidths)
 * 参数:
 *     table         String          导出的表格，通过JQUERY获取，如$("#exportTable")
 *     title         String          标题
 *     columnWidths  Array<Integer>  导出的Excel文档每一列的宽度，如果不指定，将自动设置宽度
 *                                   宽度可以设置为屏幕上px宽度
 * 
 */

var SP = (function() {

	// 调用接口
	function invoke(method, params, callback) {
		if (!callback) {
			callback = function() {};
		}
		
		$.ajax({
			url: connectUrl + "/connect?method=" + encodeURIComponent(method),
			type: "post",
			dataType: "json",
			data: {
				data: JSON.stringify(params),
				session: loginSession
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					callback(null, resp.data);
				} else if (resp.result == 9) {
					window.top.location.href = umsUrl + "/login?redirect=" + encodeURIComponent(portalUrl);
				} else {
					callback(resp.message);
				}
			},
			error: function() {
				callback("调用接口失败");
			}
		});
	};
	
	// 打印
	function print(html, title, pagesize) {
		MU.msgTips("loading", "正在准备打印...");
		
		if (!pagesize) {
			pagesize = "A4 portrait";
		}
		
		$.ajax({
			url: contextPath + "/print/prepare",
			type: "post",
			dataType: "json",
			async: false,
			data: {
				data: html,
				title: title
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var u = contextPath + "/print/print?key=" + encodeURIComponent(resp.data.key)
							+ "&pagesize=" + encodeURIComponent(pagesize);
					var f = "menubar=no, location=no, status=no";
					window.open(u, "printWindow" + new Date().getTime(), f);
					MU.removeTips();
				} else {
					MU.removeTips();
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.removeTips();
				MU.msgTips("error", "打印处理失败");
			}
		});
	}
	
	// 导出
	function exportTable(table, title, columnWidths) {
		MU.msgTips("loading", "正在准备导出...");
		var data = convertTableToJSON(table);
		data.columnWidths = columnWidths;
		
		$.ajax({
			url: contextPath + "/export/prepare",
			type: "post",
			dataType: "json",
			async: false,
			data: {
				data: JSON.stringify(data),
				title: title
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					var u = contextPath + "/export/export?key=" + encodeURIComponent(resp.data.key);
					window.open(u);
					MU.removeTips();
				} else {
					MU.removeTips();
					MU.msgTips("error", resp.message);
				}
			},
			error: function() {
				MU.removeTips();
				MU.msgTips("error", "打印处理失败");
			}
		});
	}
	
	// export 将table/thead/tbody转为json
	function convertTableToJSON(table) {
		table = $(table);
		var data = { thead:[], tbody:[] };
		
		// thead
		var headerRows = table.find("thead").children();
		$.each(headerRows, function(i, tr) {
			var cellsData = [];
			var cells = $(tr).children();
			$.each(cells, function(i, td) {
				td = $(td);
				cellsData.push({
					text: $.trim(td.text()),
					colspan: (td.attr("colspan") ? td.attr("colspan") : null),
					rowspan: (td.attr("rowspan") ? td.attr("rowspan") : null)
				});
			});
			data.thead.push(cellsData);
		});
		
		// tbody
		var bodyRows = table.find("tbody").children();
		$.each(bodyRows, function(i, tr) {
			var cellsData = [];
			var cells = $(tr).children();
			$.each(cells, function(i, td) {
				td = $(td);
				cellsData.push({
					text: $.trim(td.text()),
					colspan: (td.attr("colspan") ? td.attr("colspan") : null),
					rowspan: (td.attr("rowspan") ? td.attr("rowspan") : null)
				});
			});
			data.tbody.push(cellsData);
		});
		
		return data;
	}

	// END
	return {
		invoke: invoke,
		print: print,
		exportTable: exportTable
	};
	
})();



