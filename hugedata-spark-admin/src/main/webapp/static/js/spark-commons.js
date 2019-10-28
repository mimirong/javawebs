// DataTable语言选项
function dataTableLang(){
	var langOptions = {
		"sProcessing" : '<img src="' + contextPath + '/static/images/loading-round.gif" alt="正在加载..." />',
		"sLengthMenu" : "每页显示 _MENU_ 条",
		"sZeroRecords" : "正在加载中......",
		"sEmptyTable" : "查询无数据！",
		"sInfo" : "当前显示<span style='color:#428bca'> _START_ </span>到<span style='color:#428bca'> _END_ </span>条，共<span style='color:#428bca'> _TOTAL_ </span>条",
		"sInfoEmpty" : "显示0到0条记录",
		"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
		"sSearch" : "当前数据搜索",
		"oPaginate" : {
			"sFirst" : "首页",
			"sPrevious" : "上一页",
			"sNext" : "下一页",
			"sLast" : "末页"
		}
	};
	return langOptions;					
}

// 定义DataTables每页显示的记录条数
function dataTablePageLength() {
	return 10;
}

// 定义DataTables的"sDom"字段不显示顶部栏
function dataTablesDomWithoutTopBar() {
	return  '<""<tr>>' + 
			'<"row"<"col-sm-6"i><"col-sm-6"p>>';
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

