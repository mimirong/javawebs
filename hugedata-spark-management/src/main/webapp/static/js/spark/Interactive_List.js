var messageId = null;
var columns =[
	{
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			m.push('<label onclick="ck(this)" class="checkbox messageCheck "data-message-id='+row.messageId+'></label>')
			return m.join('');
		}
	},{
		data: "title",
		render: function(val, type, row) {
			return '<a href="javascript:;" class="blue op-reply" data-row-id="' + row.messageId + '">' + val + '</a>';
		}
	},{
		data: "name"
	},{
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (row.messageType == "0") {
				m.push('<span">求助</span> ');
			} else if (row.messageType == "1") {
				m.push('<span">投诉</span> ');
			} else {
				m.push('<span">咨询</span> ');
			}
			return m.join('');
		}
	},{
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (row.status == "0") {
				m.push('<span">未回复</span> ');
			} else {
				m.push('<span">已回复</span> ');
			}
			return m.join('');
		}
	},{
		data: "submitTime",
		render: function(val) {
			if (val == null) {
				return null
			} else {
				return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
			}
		}
	},{
		data: "replyTime",
		render: function(val) {
			if (val == null) {
				return null
			} else {
				return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
			}
		}
	},{
		data: null,
		orderable: false,
		render: function(row) {
			var m = [];
			if (row.status == '0' && !row.isDeleted) {
				m.push('<a href="javascript:;" class="txt-op op-reply" data-row-id=' + row.messageId + '>回复</a>');
			} else if (row.isDeleted) {
				m.push('<span href="javascript:;" class="red">已忽略</span>');  
			} else {
				m.push('<span href="javascript:;" class="grey">已回复</span>');
			}
			if (!row.isDeleted) {
				m.push('<a style="float:right; " href="javascript:;" class="txt-op op-del" data-row-isDeleted='+row.isDeleted+' data-row-id=' + row.messageId + '>忽略</a>');
			} else {
				m.push('<a style="float:right; " href="javascript:;" class="txt-op op-del" data-row-isDeleted='+row.isDeleted+'  data-row-id=' + row.messageId + '>激活</a>');
			}
			return m.join('');
		}
	}
];

// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
    ajax: {
        url: "listData",
        data: {}
    },
    lengthChange: false,
    serverSide : true,
    searching : false,
    ordering: false,
    columns : columns
}));

// 绑定事件
ListFeaturePage.autobind(dt);

//批量忽略
$(".op-btn").on("click",function(){
	var checked = $(".messageCheck.checked");
	if(checked.length==0){
		 MU.msgTips("warn", "请勾选数据", 1200);
		 return
	}
	MU.conFirm("忽略","您确定要忽略吗？", function() {
		var checkedList = [];
		$.each(checked, function(){
			checkedList.push($(this).attr("data-message-id")*1);
		});
		 $.ajax({
             url: "fakeDel",
             type: 'POST',
             dataType: "json",
             data: {messageIdList:checkedList},
             success: function(resp) {
                 if (resp && resp.result == 0) {
                     MU.msgTips("success", "忽略成功", 1200);
                     dt.ajax.reload();
                 } else {
                     MU.msgTips("error", resp.message, 1200);
                 }
             },
             error: function() {
                 MU.msgTips("error", "忽略失败，请稍后重试", 1200);
             }
         });
	});
});

var ck = function(e) {
	var checkedLength = $('.messageCheck.checked').length;
	var hasChecked = $(e).hasClass("checked");
  
	if(hasChecked){
		checkedLength = checkedLength - 1;
	} else {
		checkedLength = checkedLength + 1;
	}
	
	if(dt.data().length ==checkedLength){
		$(".js-all-selected").addClass("checked");
	} else {
		$(".js-all-selected").removeClass("checked");
	}
}
		
dt.on("draw", function(settings, data) {
	//点击忽略激活
	$(".op-del").off().on("click", function(e) {
		var messageId= $(e.target).attr("data-row-id")*1;
		var isDeleted= $(e.target).attr("data-row-isDeleted");
		if(isDeleted=="false"){
			isDeleted = false
		}else{
			isDeleted = true
		}
		var msg = isDeleted?'激活':'忽略';

		MU.conFirm(msg,"您确定要"+msg+"吗？", function() {
			var deleteIdList = [];
			deleteIdList.push(messageId);
			$.ajax({
				url: "fakeDel",
				type: 'POST',
				dataType: "json",
				data: {
					messageIdList:deleteIdList,
					isDeleted:!isDeleted	 	
				},
				success: function(resp) {
					if (resp && resp.result == 0) {
						MU.msgTips("success", msg+"成功", 1200);
						dt.ajax.reload();
					} else {
						MU.msgTips("error", resp.message, 1200);
					}
				},
				error: function() {
					MU.msgTips("error", msg+"失败，请稍后重试", 1200);
				}
			});
		});
	});
		  
	//打开回复
	$(".op-reply").off().on("click", function(e) {
		messageId = $(e.target).attr("data-row-id");
		$("#replyContent").val("");
		$.ajax({
			url: "toReply",
			type: "post",
			dataType: "json",
			data: { messageId:messageId },
			success: function(resp) {
				if (resp && resp.result == 0) {
					if (resp.data.status == "0") {
						$(".replyPanelTitle").text("回复");
						$("#title").text(resp.data.title);
						$("#content").val(resp.data.content);
						$("#replierCompany").val(resp.data.replierCompany).prop("disabled", false);
						$("#replyContent").val(resp.data.replyContent).prop("disabled", false);
						$("#btnDoModify").show();
					} else {
						$(".replyPanelTitle").text("查看");
						$("#title").text(resp.data.title);
						$("#content").val(resp.data.content);
						$("#replierCompany").val(resp.data.replierCompany).prop("disabled", true);
						$("#replyContent").val(resp.data.replyContent).prop("disabled", true);
						$("#btnDoModify").hide();
					}

					MU.mask();
					MU.center("#replyPanel");
					$("#replyPanel").show();	
				} else {
					MU.msgTips("error", resp.message, 1200);
				}
			},
			error: function() {
				MU.msgTips("error", "获取用户信息，请稍后重试", 1200);
			}
		});
	});
});



// 提交修改
$("#btnDoModify").on("click", function() {
	  if ($("#replyContent").val() == ""){
		  MU.msgTips("warn", '请输入回复内容', 1200);
		  return; 
	  }
	  
	  if ($("#replierCompany").val() == "") {
		  MU.msgTips("warn", '请输入回复单位', 1200);
		  return; 
	  }
	  
	  $.ajax({
		  url: "doModify",
		  type: "post",
		  dataType: "json",
		  data: {
			  messageId: messageId,
			  replyContent: $("#replyContent").val(),
			  replierCompany: $("#replierCompany").val(),
			  status: '1'
		  },
		  success: function(resp) {
			  if (resp && resp.result == 0) {
				  MU.msgTips("success", "回复成功", 1200);
				  MU.hide("#btnDoModify");
				  dt.ajax.reload();
			  } else {
				  MU.msgTips("error", resp.message, 1200);
			  }
		  },
		  error: function() {
			  MU.msgTips("error", "获取用户信息，请稍后重试", 1200);
		  }
	  });
}); //end of 提交修改