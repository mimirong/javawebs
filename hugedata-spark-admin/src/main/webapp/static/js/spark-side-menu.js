function initSideMenu(definition, current) {
	
	// 检查菜单是否没有正确加载
	if (!definition) {
		$("#sideMenuNoMenu").css("display", "block");
		$("#sideMenuList").css("display", "none");
		return;
	}
	$("#sideMenuNoMenu").css("display", "none");
	$("#sideMenuList").css("display", "block");
	
	// 获取当前菜单
	var current1, current2;
	if (current) {
		current1 = current.split("/")[0];
		current2 = current.split("/")[1];
	}
	

	// 构造菜单
	var m = [];
	$.each(definition, function(i, item1) {
		m.push('<li class="sidemenu-dropdown panel panel-default" id="sidemenu_' + i + '">');
		m.push('  <a data-toggle="collapse" href="#sidemenu_' + i + '_lvl1">');
		m.push('    <span class="fa fa-' + item1.iconName + '"></span> ');
		m.push(     item1.title);
		m.push('    <span class="caret"></span>');
		m.push('  </a>');
		
		m.push('<div id="sidemenu_' + i + '_lvl1" class="panel-collapse ' 
				+ (current1 == item1.id ? '' : 'collapse') + '">');
		m.push('  <div class="panel-body">');
		m.push('    <ul class="nav navbar-nav">');
		
		$.each(item1.menus, function(i, item2) {
			var active = (current1 == item1.id && current2 == item2.id);
			m.push('  <li class="' + (active ? 'active' : '') + '">');
			m.push('    <a href="' + contextPath + item2.url + '">' + item2.title + '</a>');
			m.push('  </li>');
		});
		
		m.push('    </ul>');
		m.push('  </div>');
		m.push('</div>');
		
		
		m.push('</li>');
	});
	
	$("#sideMenuList").html(m.join(""));
	
}