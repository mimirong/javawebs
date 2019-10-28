$.ajax({
	url: contextPath + "/messages/queryUnreadCount",
	type: "post",
	dataType: "json",
	data: {
		
	},
	success: function(resp) {
		if (resp && resp.result == 0) {
			var count = parseInt(resp.data);
			if (isNaN(count)) {
				return;
			}
			if (count > 0) {
				$("#unreadMessageIndicator").show();
			} else {
				$("#unreadMessageIndicator").hide();
			}
		} else {
			MU.msgTips("warn", "查询未读消息数失败: " + resp.message);
		}
	},
	error: function() {
		MU.msgTips("warn", "查询未读消息数失败")
	}
});