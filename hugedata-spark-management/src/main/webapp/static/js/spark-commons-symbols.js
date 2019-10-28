//

var CommonsSymbols = (function() {
	var symbols = {};
	
	// 将数据加载到<select>
	// 参数:
	//   selector       String   指定<select>标签，如"#userType"
	//   data           Object   CommonsSymbols中的一组数据
	//   selectedValue  String   默认选中的值
	//   enableAll      Boolean  是否显示"全部"选项
	symbols.loadToSelect = function(selector, data, selectedValue, enableAll) {
		var select = $(selector);
		var m = [];
		$.each(data.keys, function(index, key) {
			if (!enableAll && key == '') {
				return;
			}
			var value = data[key];
			if (selectedValue && key == selectedValue) {
				m.push('<option value="', key, '" selected="selected">', value, '</option>');
			} else {
				m.push('<option value="', key, '">', value, '</option>');
			}
		});
		select.html(m.join(''));
	};

	// 数据格式：
	// {
	//   keys: [ "", "a", "b", "c" ],
	//   "": "全部",
	//   "a": "Item A",
	//   "b": "Item B",
	//   "c": "Item C"
	// }
	
	
	
	// 用于创建数据项目
	function Builder() {
		var self = this;
		var data = {
			keys: [ "" ],
			"": "全部"
		};
		
		this.append = 
		this.add = function(key, value) {
			data[key] = value;
			data.keys.push(key);
			return self;
		};
		this.build = function() {
			return data;
		};
	}
	
	// 操作日志 - 操作
	symbols.LogOperation_Operation = new Builder()
		.add("ADD"    , "新增")
		.add("MODIFY" , "修改")
		.add("DELETE" , "删除")
		.add("IMPORT" , "导入")
		.add("ACCEPT" , "接受")
		.add("REJECT" , "拒绝")
		.add("CONFIRM", "确认")
		.add("CANCEL" , "取消")
		.add("MERGE"  , "合并")
		.add("ROLE_UPDATE_RIGHTS", "配置权限")
		.add("USER_UPDATE_ROLES" , "配置角色")
		.build();
	
	// 操作日志 - 操作内容
	symbols.LogOperation_Target = new Builder()
		.add("NULL"           , "无")
		.add("PARK_USERS"     , "用户")
	    .add("PARK_DEPTS"     , "部门")
	    .add("PORTAL_CATEGORY", "门户栏目")
	    .add("PORTAL_ARTICLE" , "门户文章")
	    .add("ROLES"          , "角色")
	    .add("COMPANY_USERS"  , "企业用户")
	    .build();

	return symbols;
})();
