function initSideMenu(definition, current) {
	
	// 定义系统名称
	var systemNames = {
		"BASIC": "基础管理",
		"GOVAFFAIRS": "政务服务",
		"OUTSOURCING": "服务外包",
		"DETECTION": "检验检测管理",
		"ECOMMERCE": "电子商务管理",
		"ACCOUNTS": "账号管理",
		"MP": "小程序管理",
		"APPROVAL": "行政审批处理",
		"PM": "项目管理",	
		"INFORMATION": "信息发布管理",
		"IT": "互动交流管理",
		"INTEGRATED": "综合服务"
	};
	var systemStyleClass = {
		"BASIC": "",
		"GOVAFFAIRS": "zwgl",
		"OUTSOURCING": "fwwb",
		"DETECTION": "jcsj",
		"ECOMMERCE": "dzsw",
		"ACCOUNTS": "zhgl",
		"MP": "xcx",
		"APPROVAL": "xzsp",
		"PM": "xmgl",
		"IT": "hdjl"
	};
	
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
	
	// 判断没有配置菜单
	if (definition.length == 0) {
		$("#sideMenuNoMenu").show();
		return;
	}
	
	// 构造菜单
	var m = [];
	m.push('<li class="menu_tit ' + systemStyleClass[masterSystemType] + '">' + systemNames[masterSystemType] + '</li>');
	$.each(definition, function(i, item1) {
		if (!currentSystem) {
			currentSystem = masterSystemType;
		}
		if (currentSystem && item1.systemType != currentSystem) {
			return;
		}

		if (item1.menus && item1.menus.length > 0) {
			m.push('<li class="'+ (current1 == item1.id ? 'active' : '') + '">');
			m.push('<a class="drop '+ (current1 == item1.id ? 'down' : '') + '">' + item1.title + '</a>');
			m.push('<dl style="' + (current1 == item1.id ? 'display:block' : 'display:none') + '">')

			$.each(item1.menus, function(i, item2) {
				var active = (current1 == item1.id && current1 + "/" + current2 == item2.id);
				m.push('<dd class="' + (active ? 'on' : '') + '"><a href="' + contextPath + item2.url + '">' + item2.title + '</a></dd>');
			});
			
			m.push('</dl>');
			m.push('</li>');
		} else {
			m.push('<li class="'+ (current1 == item1.id ? 'active' : '') + '">');
			m.push('<a href="' + contextPath + item1.url + '">' + item1.title + '</a>');
			m.push('</li>');	
		}
		
		m.push('</li>');
	});
	
	$("#sideMenuList").html(m.join(""));

    $(".leftsidebar a.drop").click(function(){  //  左侧菜单 二级菜单展开收缩
        if($(this).hasClass("down")){
            $(this).parent().removeClass('active');
            $(this).next().hide();
            $(this).removeClass('down');
        }else{
            //$(".leftsidebar li.nav").removeClass('active');
            //$(".leftsidebar dl").hide();
            //$(".leftsidebar .drop").removeClass('down');
            $(this).parent().addClass('active');
            $(this).next().show();
            $(this).addClass('down');
            return;
        }
    });	
}