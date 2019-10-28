var SideMenuData = (function() {
	// A
	var menu_aaa = { id:"aaa", title:"菜单AAA", iconName:"user", menus:[] };
	menu_aaa.menus.push({ id:"aaa1", title:"AAA-1", url:"/features/Aaa/list.aspx" });
	
	// B
	var menu_bbb = { id:"bbb", title:"菜单BBB", iconName:"gear", menus:[] };
	menu_bbb.menus.push({ id:"bbb1", title:"bbb1", url:"/features/bbb1/list.aspx" });
	menu_bbb.menus.push({ id:"bbb2", title:"bbb2", url:"/features/bbb2/list.aspx" });

	// C
	var menu_ccc = { id:"ccc", title:"菜单CCC", iconName:"tasks", menus:[] };
	menu_ccc.menus.push({ id:"ccc1", title:"ccc1", url:"/features/ccc1/toAdd.aspx" });
	menu_ccc.menus.push({ id:"ccc2", title:"ccc2", url:"/features/ccc2/list.aspx" });
	menu_ccc.menus.push({ id:"ccc3", title:"ccc3", url:"/features/ccc3/list.aspx" });
	
	// ROOT
	var menu_root = [];
	menu_root.push(menu_aaa);
	menu_root.push(menu_bbb);
	menu_root.push(menu_ccc);
	
	return {
		data: function() {
			return menu_root;
		} 
	};
})();