// DataTable选项
function dataTableCommonOptions(options) {
	if (!options.language) {
		options.language = dataTableLanguage();
	}
	if (!options.pageLength) {
		options.pageLength = 10;
	}
//	if (!options.dom) {
//		options.dom = '<""<tr>>' + 
//					  '<"row"<"col-sm-6"i><"col-sm-6"p>>';
//	}
	
	// 处理loading效果
	if (options.ajax) {
		var ajax = options.ajax;
		if (typeof ajax === "string") {
			ajax = { url: ajax };
		}
		ajax.beforeSend = function() {
			MU.msgTips("loading", "正在加载");
		};
		ajax.complete = function() {
			MU.removeTips();
		};
	}
	
	return options;
}

function dataTableDomNoPagination() {
	return 'tr';
}

function dataTableLanguage() {
	return {
	    "decimal":        "",
	    "emptyTable":     "没有数据",
	    "info":           '<span class="total">共<em>_PAGES_</em>页</span>',
	    "infoEmpty":      "",
	    "infoFiltered":   "(共为 _MAX_ 条记录)",
	    "infoPostFix":    "",
	    "thousands":      "",
	    "lengthMenu":     "每页显示 _MENU_ 条",
	    "loadingRecords": null,
	    "processing":     null,
	    "search":         "搜索:",
	    "zeroRecords":    "没有数据..",
	    "paginate": {
	        "first":      "首页",
	        "last":       "末页",
	        "next":       "下一页",
	        "previous":   "上一页"
	    },
	    "aria": {
	        "sortAscending":  ": activate to sort column ascending",
	        "sortDescending": ": activate to sort column descending"
	    }
	};
}

// 检查权限
function checkPrivilege(p) {
	for (var i = 0; i < privileges.length; i++) {
		if (privileges[i] == p) {
			return true;
		}
	}
	return false;
}

// 显示错误信息
function showErrorMessage(msg) {
	$('#errorMessage div').html(msg);
	$('#errorMessage').css('display', 'block');
	$('#infoMessage').css('display', 'none');
}

// 显示消息
function showInfoMessage(msg) {
	$('#infoMessage div').html(msg);
	$('#infoMessage').css('display', 'block');
	$('#errorMessage').css('display', 'none');
}

// 全局处理AJAX登录超时
$(document).ajaxSuccess(function(e, xhr, options, resp) {
	if (typeof resp == "object") {
		if (resp.result == 9) {
			window.location.href = managementUrl + "/login?redirect=" + encodeURIComponent(managementUrl);
		}
	}
});
//$(document).ajaxSend(function(e, xhr, options) {
//	console.log("send: ", options);
//});
//$(document).ajaxComplete(function(e, xhr, options) {
//	console.log("complete: ", options);
//});
(function() {
	var GlobalSubmitProcess = {};
	$.ajaxSetup({
		beforeSend: function(xhr, settings) {
			if (GlobalSubmitProcess[settings.url] == 1) {
				return false;
			}
			GlobalSubmitProcess[settings.url] = 1;
			return true;
		},
		complete: function(xhr, status) {
			var url = this.url;
			setTimeout(function() {
				GlobalSubmitProcess[url] = 0;
			}, 300);
		}
	});
})();

// 下载服务
var DownloadService = (function() {
	var ds = {};
	ds.buildUrl = function(fileId, filename) {
        var url = downloadServiceUrl;
        if (url.lastIndexOf("/") != url.length - 1) {
        	url += "/";
        }
        url += fileId.substring(0, 7) + "_" + fileId.substring(fileId.length - 1) + "/";
        url += fileId;
        if (filename) {
        	url += "?attname=" + encodeURIComponent(filename); 
        }
        return url;
	};
	return ds;
})();

$(function() {
	$(".fileDownload").each(function(i, item) {
		item = $(item);
		var fileId = item.attr("data-fileid");
		var filename = item.attr("data-filename");
		if (!fileId) {
			return;
		}
		if (item.is("a")) {
			item.attr("href", DownloadService.buildUrl(fileId, filename));
		}
	});
});

// 公共检查方法
var CheckService = (function() {
	var obj = {};
	obj.telephone = function(val) {
		return /[\d\-]{6,}/.test(val);
	};
	return obj;
})();


// Datepicker
jQuery(function($){
	$.datepicker.regional['zh-CN'] = {
		closeText: '关闭',
		prevText: '&#x3c;上月',
		nextText: '下月&#x3e;',
		currentText: '今天',
		monthNames: ['一月','二月','三月','四月','五月','六月',
		'七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort: ['一','二','三','四','五','六',
		'七','八','九','十','十一','十二'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
		dayNamesMin: ['日','一','二','三','四','五','六'],
		dateFormat: 'yy-mm-dd', firstDay: 1,
		isRTL: false};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});


//部门信息配置
var GLOBAL_DEPT_CONFIG = [
	{ code:"7", name:"办公室" },
	{ code:"8", name:"党群纪检绩效办" },
	{ code:"1", name:"经济发展局" },
	{ code:"5", name:"招商合作局" },
	{ code:"3", name:"工程建设局" },
	{ code:"4", name:"社会事务局" },
	{ code:"6", name:"财政分局" },
	{ code:"2", name:"国土规划局" },
	{ code:"99", name:"其它" }
];

var GLOBAL_DEPT_CONFIG_WITHOUT_OTHER = [
	{ code:"7", name:"办公室" },
	{ code:"8", name:"党群纪检绩效办" },
	{ code:"1", name:"经济发展局" },
	{ code:"5", name:"招商合作局" },
	{ code:"3", name:"工程建设局" },
	{ code:"4", name:"社会事务局" },
	{ code:"6", name:"财政分局" },
	{ code:"2", name:"国土规划局" }
];
