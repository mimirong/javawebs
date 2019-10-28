var DEPT_NAMES = {
    "1": "经济发展局",
    "2": "国土规划局",
    "3": "工程建设局",
    "4": "社会事务局",
    "5": "招商合作局",
    "6": "财政分局",
    "7": "办公室",
    "8": "党群纪检绩效办"
};

// 重新提交时加载数据
if (window.fromServiceId && window.fromServiceId != "") {
	$.ajax({
		url: "serviceShowDeatil",
		type: "post",
		dataType: "json",
		data: { serviceId:window.fromServiceId },
		success: function(resp) {
			if (resp && resp.result == 0) {
				$("#businessDeptPerson").val(resp.data.serviceInfo.businessDeptPerson);
				$("#cellphone").val(resp.data.serviceInfo.cellphone);
				$.each(resp.data.attachments, function(i, a) {
					if (a.fileId != "NOFILE") {
						window.onAttUploaded(null, a);
					}
				});
			} else {
				MU.msgTips("error", "加载办件信息失败，请稍后重试");
			}
		},
		error: function() {
			MU.msgTips("error", "加载办件信息失败，请稍后重试");
		}
	});
}

// 提交
$("#btnSubmit").on("click", function() {
	// businessDeptPerson 
    var businessDeptPerson = $("#businessDeptPerson").val();
    if (businessDeptPerson == "") {
    	MU.msgTips("warn", "请输入申办单位（人）");
    	$("#businessDeptPerson").focus();
    	return;
    }
    
    // cellphone
    var cellphone = $("#cellphone").val();
    if (cellphone == "") {
    	MU.msgTips("warn", "请输入联系方式");
    	$("#cellphone").focus();
    	return;
    }
    if (!/[\d\-]{6}/.test(cellphone)) {
    	MU.msgTips("warn", "联系方式不正确");
    	return;
    }
    
    // 附件信息
	var atts = window.findAllUploadedAtts();
	if (!atts) {
		return;
	}
	
	// 提交
    $.ajax({
    	url: "submitOnLineBusiness",
    	type: "post",
    	dataType: "json",
    	data: {
    		guideId: window.data.guide.guideId,
    	    businessDeptPerson: businessDeptPerson,
    	    cellphone: cellphone,
    	    attachmentsJson: JSON.stringify(atts)
    	},
    	success: function(resp) {
			if (resp && resp.result == 0) {
				MU.msgTips("success", "提交成功", 1200);
				location.href = "list?dept=" + window.data.guide.deptCode;
			} else {
				MU.msgTips("error", resp.message);
			}
    	},
    	error: function() {
			MU.msgTips("error", "提交失败，请稍后重试");
    	}
    });
});

//==============================================================================
// 办理材料处理
//==============================================================================

$(function() {
	// 进入页面后加载办理材料列表
	function loadAttConfigList() {
		var m = [];
		$.each(window.data.attConfigs, function(i, ac) {
			var uploadPageUrl = 'showUploadAtt?callback=onAttUploaded&attConfigId=' + ac.attConfigId;
			m.push('<tr class="attItemWrapper" id="attItemWrapper_' + ac.attConfigId + '" data-id="' + ac.attConfigId + '" data-files="[]">');
			m.push('  <td>');
			m.push('    <div style="float:left; margin:0 0 0 16px;">');
			m.push('      <iframe src="' + uploadPageUrl + '" width="30px" height="18px" scrolling="no"> </iframe>');
			m.push('    </div>');
			m.push('    <div style="float:left; margin-left:8px;">');
			m.push('      <a class="blue btnDeleteAtt">删除</a>');
			m.push('    </div>');
			m.push('  </td>');
			m.push('  <td><span class="blue attCount" style="cursor:pointer"></span></td>');
			m.push('  <td class="configName">' + ac.attConfigName + '</td>');
			m.push('  <td><input type="text" class="attRemark" placeholder="(此处可输入说明内容)" style="width:272px; background:none;" maxlength="100" /></td>');
			m.push('</tr>');
		});
		$("#attListWrapper").html(m.join(""));
		bindDeleteAttEvents();
		bindShowAttEvents();
	}
	
	// 进入页面后加载文件信息
	function loadFileData() {
		$(".attItemWrapper").each(function() {
			var tr = $(this);
			var attConfigId = tr.attr("data-id");
			var files = JSON.parse(tr.attr("data-files"));
			tr.find(".attCount").html(files.length);
		});
	}
	
	// 删除附件
	function bindDeleteAttEvents() {
		$(".attItemWrapper .btnDeleteAtt").off("click").on("click", function() {
			var tr = $(this).parents(".attItemWrapper");
			tr.find(".attCount").html("0");
			tr.attr("data-files", "[]");
		});
	}
	
	// 显示附件
	function bindShowAttEvents() {
		$(".attItemWrapper .attCount").off("click").on("click", function() {
			var tr = $(this).parents(".attItemWrapper");
			var files = JSON.parse(tr.attr("data-files"));
			if (files.length == 0) {
				return;
			}
			
			var m = [];
			m.push('<div class="pop-content" style="padding: 30px 0;">');
			m.push('<ul class="list" style="min-height: 76px;">');
			$.each(files, function(i, f) {
				var url = DownloadService.buildUrl(f.fileId, f.fileName);
				m.push('<li><a href="' + url + '">' + f.fileName + '</a></li>');
			});
			m.push('</ul>');
			m.push('</div>');
			MU.alert(m.join(""), 600, "附件");
		});
	}
	
	// 办理材料上传成功		
	window.onAttUploaded = function(err, resp) {
		if (err) {
			MU.msgTips("error", err);
			return;
		}
		var attConfigId = resp.attConfigId;
		var tr = $("#attItemWrapper_" + attConfigId);
		
		if (resp.remark && resp.remark != "") {
			tr.find(".attRemark").val(resp.remark);
		}
		
		var files = JSON.parse(tr.attr("data-files"));
		files.push({
			fileId: resp.fileId,
			fileName: resp.fileName
		});
		tr.find(".attCount").html(files.length);
		tr.attr("data-files", JSON.stringify(files));
	};
	
	// 获取所有附件信息
	window.findAllUploadedAtts = function() {
		var arr = [];
		
		var items = $(".attItemWrapper");
		for (var i = 0; i < items.length; i++) {
			var tr = $(items[i]);
			var attConfigId = tr.attr("data-id");
			var attConfigName = tr.find(".configName").text();
			var files = JSON.parse(tr.attr("data-files"));
			var remark = tr.find(".attRemark").val();
			
			if (files.length > 0) {
				$.each(files, function(i, file) {
					arr.push({
						fileId: file.fileId,
						fileName: file.fileName,
						remark: remark,
						attConfigId: attConfigId
					});
				});
			} else {
//				arr.push({
//					fileId: "NOFILE",
//					fileName: "NOFILE",
//					remark: remark,
//					attConfigId: attConfigId
//				});
				MU.msgTips("warn", "请上传申请材料\"" + attConfigName + "\"");
				return null;
			}
		}
		
		return arr;
	};

	// init
	loadAttConfigList();
	loadFileData();
});