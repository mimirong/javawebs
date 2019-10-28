var CommonsSelect = (function() {
	var instance = {};
	
	// 将<select>转为Bootstrap下拉列表
	instance.convert = function(id) {
		var select = $("#" + id);
		var parent = select.parent();
		
		select.hide();
		
		// 处理所有options
		var selectedOption = null;
		var s = [];
		s.push('<ul class="dropdown-menu" role="menu" aria-labelledby="' + id + '_select_button"">');
		$.each($("#" + id + " option"), function(i, op) {
			if (i == 0) {
				selectedOption = op;
			}
			//console.log(i + "    " + op.text + "     " + op.value + "     " + op.selected);
			s.push('<li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="' + op.value + '">');
			s.push(op.text);
			s.push('</a></li>');
			
		});
		s.push('</ul>');
		
		var m = [];
		m.push('<div class="dropdown" id="' + id + '_select_wrapper">');
		m.push('  <button class="btn btn-default dropdown-toggle" type="button" id="' + id + '_select_button" data-toggle="dropdown">');
		m.push(selectedOption.text);
		m.push('    <span class="caret"></span>');
		m.push('  </button>');
		m.push(s.join(''));
		m.push('</div>');
		parent.append($(m.join("")));
	};
	
	return instance;
})();