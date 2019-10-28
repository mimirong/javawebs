// Columns
var surveyId = null;
var columns =[
	{
		data: null,
		render: function(row) {
			return '<label class="checkbox btnSelect" data-row-id="' + row.surveyId + '"></label>';
		}
	},{
		data: "surveyId"
	}, {
		 data: null,
         render : function(row) {
			return '<span title="'+row.title+'"class="wh" style="width:100px">' + row.title+ '</span>';
		}
	}, {
		data: "startTime",
		render: function(val) {
			return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
		}
	}, {
		data: "endTime",
		render: function(val) {
			return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
		}
	}, {
		data: "submitCount"
	}, {
		data: "isPublished",
		render: function(isPublished) {
			if (isPublished) {
				return '<span class="green">已发布</span>';
			} else {
				return '<span class="red">未发布</span>';
			}
		}
	}, 
	{
		data: null,
		orderable: false,
		createdCell: function(td) {
			$(td).css('text-align', 'center');
		},
		render: function(row) {
			var m = [];
			if (CommonsPrivileges.hasPrivilegeId("interactive/setPublished")) {
				if (row.isPublished) {
					m.push('<a href="javascript:;" class="txt-op btnCancelPublished" data-row-id="' + row.surveyId + '">取消发布</a> ');
				} else {
					m.push('<a href="javascript:;" class="txt-op btnSetPublished" data-row-id="' + row.surveyId + '">发布</a> ');
				}
			}
			if (CommonsPrivileges.hasPrivilegeId("interactive/config") && !row.isPublished) {
				m.push('<a href="javascript:;" class="txt-op btnConfig" data-row-id="' + row.surveyId + '" data-row-title="'+row.title+'">配置</a> ');
			}
			if (CommonsPrivileges.hasPrivilegeId("interactive/modify") && !row.isPublished) {
				m.push('<a href="javascript:;" class="txt-op btnModify" data-row-id="' + row.surveyId+ '" data-row-title="'+row.title+'">修改</a> ');
			}
			if (CommonsPrivileges.hasPrivilegeId("interactive/delete")) {
				m.push('<a href="javascript:;" class="txt-op btnDelete" data-row-id="' + row.surveyId + '">删除</a> ');
			}
			if (CommonsPrivileges.hasPrivilegeId("interactive/summary")) {
				m.push('<a href="javascript:;" class="txt-op btnSummary" data-row-id="' + row.surveyId + '" data-row-title="'+row.title+'">查看结果</a> ');
			}
			return m.join('');
		}
	}
];
// 初始化日期选择
$(".dateInput").on("focusin", function(e) {
    $(this).prop('readonly', true);
}).on("focusout", function(e) {
    $(this).prop('readonly', false);
}).datepicker({
    showOtherMonths: true,
    selectOtherMonths: true,
    dateFormat: "yy-mm-dd"
});
	
// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
	ajax: {
		url: "listData"
	},
	lengthChange: false,
	serverSide : true,
	searching : false,
	processing: true,
	ordering: false,
	columns : columns
}));


// 绑定事件
ListFeaturePage.autobind(dt);

// 点击新增按钮
$(".add-survey").on("click", function() {
	$("#btnDoAdd").text("新增调查")
    $("#btnDoAdd").show();
    MU.mask();
    MU.center("#addPanel");
    $("#addPanel").show();
})

// 绑定每行事件
dt.on('draw', function(settings, data) {
	//修改
	$(".btnModify").on("click", function(e	) {
		surveyId = $(e.target).attr("data-row-id");
		var title = $(e.target).attr("data-row-title");
		$("#btnDoAdd").text("修改调查");
		$("#btnDoAdd").show();
		MU.mask();
		MU.center("#addPanel");
		$("#addPanel").show();
		 $.ajax({
	            url: "toUpdate",
	            type: 'POST',
	            dataType: "json",
	            data: {surveyId:surveyId,
	            	   title:title},
	            success: function(resp) {
	                if (resp && resp.result == 0) {
	                	$("#title").val(resp.data.title);
	                	$("#brief").val(resp.data.brief);
	                	$("#source").val(resp.data.source);
	                	$("#startTime").val(DateFormat.format.date(resp.data.startTime, 'yyyy-MM-dd'));
	                	$("#endTime").val(DateFormat.format.date(resp.data.endTime, 'yyyy-MM-dd'));
	                	
	                } else {
	                    MU.msgTips("error", resp.message, 1200);
	                }
	            },
	            error: function() {
	                MU.msgTips("error", "查询失败，请稍后重试", 1200);
	            }
	        });
		
	})
	// 检查表单
    function checkAddModifyForm() {
    	 var title = $("#title").val();
    	 if (title == "") {
	         MU.msgTips("warn", "请填写标题", 1200);
	         $("#title").focus();
	         return null;
	     }
    	 var brief = $("#brief").val();
    	 var source = $("#source").val();
    	 if (source == "") {
	         MU.msgTips("warn", "请填写来源", 1200);
	         $("#source").focus();
	         return null;
	     }
    	 var startTime = $("#startTime").val();
	     if (startTime == "") {
	         MU.msgTips("warn", "请选择开始时间", 1200);
	         $("#startTime").focus();
	         return null;
	     }
    	 var endTime = $("#endTime").val();
    	 if (endTime == "") {
    	      MU.msgTips("warn", "请选择结束时间", 1200);
    	      $("#endTime").focus();
    	      return null;
    	  }
    	 if(new Date(endTime)< new Date(startTime)){
    		  MU.msgTips("warn", "结束时间不能小于开始时间", 1200);
    		  return null;
    	 }
    	 if((new Date(endTime).getTime()+24*60*60*1000)< new Date().getTime()){
   		  MU.msgTips("warn", "结束时间不能小于当前时间", 1200);
   		  return null;
   	 }
         
         return {
        	 title:title,
        	 brief:brief,
        	 source:source,
        	 startTime:new Date(startTime),
        	 endTime:new Date(endTime)
         }
    	
    }
    // 提交新增或提交修改
    $("#btnDoAdd").on("click", function() {
        var data = checkAddModifyForm(false);
        if (data == null) {
            return;
        }
        if($("#btnDoAdd").text()=='新增调查'){
	        $.ajax({
	            url: "doAdd",
	            type: 'POST',
	            dataType: "json",
	            data: data,
	            success: function(resp) {
	                if (resp && resp.result == 0) {
	                    MU.msgTips("success", "添加成功", 1200);
	                    MU.hide($("#btnDoAdd"));
	                    dt.ajax.reload();
	                } else {
	                    MU.msgTips("error", resp.message, 1200);
	                }
	            },
	            error: function() {
	                MU.msgTips("error", "添加失败，请稍后重试", 1200);
	            }
	        });
        }
        else{
        	 data.surveyId = surveyId;
        	 $.ajax({
		            url: "doModify",
		            type: 'POST',
		            dataType: "json",
		            data: data,
		            success: function(resp) { 
		                if (resp && resp.result == 0) {
		                    MU.msgTips("success", "修改成功", 1200);
		                    MU.hide($("#btnDoAdd"));
		                    dt.ajax.reload();
		                } else {
		                    MU.msgTips("error", resp.message, 1200);
		                }
		            },
		            error: function() {
		                MU.msgTips("error", "修改失败，请稍后重试", 1200);
		            }
		        });
        }
    });
	// 取消发布
	$(".btnCancelPublished").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		$.ajax({
			url: "setPublished",
			type: "post",
			dataType: "json",
			data: {
				surveyId: id,
				isPublished: false
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					dt.ajax.reload();
				} else {
					alert(resp.message);
				}
			},
			error: function() {
				alert("取消发布失败");
			}
		});
	});
	
	// 发布
	$(".btnSetPublished").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		$.ajax({
			url: "setPublished",
			type: "post",
			dataType: "json",
			data: {
				surveyId: id,
				isPublished: true
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					if(resp.data!=null){
						 MU.msgTips("warn", resp.data, 1200);
					}
					else{
						dt.ajax.reload();
					}
				} else {
					alert(resp.message);
				}
			},
			error: function() {
				alert("发布失败");
			}
		});
	});
	
	
	// 配置
	$(".btnConfig").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		var title = $(e.target).attr("data-row-title");
		location.href = contextPath + "/features/SurveyQuestion/list?surveyId=" + id
				+ "&title=" + encodeURIComponent(title);
	});
	
	// 查看结果
	$(".btnSummary").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		var title = $(e.target).attr("data-row-title");
		location.href = contextPath + "/features/Survey/summary?surveyId=" + id
				+ "&title=" + encodeURIComponent(title);
	});
	
});