
var Symbols = (function() {
	var symbols = {};
	
	// 加载到select
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
	
	// 政企通道表单状态
	symbols.AAAA = {
		keys : [ "", "AAA", "BBB", "CCC", "DDD", "EEE" ],
		""    : "全部",
		"AAA" : "正在填写(未提交)",
		"BBB" : "已提交",
	    "CCC" : "正在处理",
	    "DDD" : "已完成"
	};
	

	return symbols;
})();


