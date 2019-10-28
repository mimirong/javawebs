var CommonsPrivileges = (function() {
	var obj = {};
	
	obj.privilegeList = null;
	obj.rightList = null;
	
	// 判断是否包含一个权限URI
	obj.hasPrivilegeUri = function(uri) {
		if (!obj.privilegeList) {
			return false;
		}
		for (var i = 0; i < obj.privilegeList.length; i++) {
			if (obj.privilegeList[i] == uri) {
				return true;
			}
		}
		return false;
	};
	
	// 判断是否包含某一权限ID
	obj.hasPrivilegeId = function(id) {
		if (!obj.rightList) {
			return false;
		}
		for (var i = 0; i < obj.rightList.length; i++) {
			if (obj.rightList[i] == id) {
				return true;
			}
		}
		return false;
	};
	
	// 初始化权限信息
	obj.init = function(privilegeList, rightList) {
		obj.privilegeList = privilegeList;
		obj.rightList = rightList;
	};
	
	return obj;
	
})();