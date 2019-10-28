var vm = new Vue({
	el:'#applyIn',
	data:{
		companyName:'',
		bpUrl:'',
		opUrl:'',
		dtpUrl:'',
		ctpUrl:'',
		contact:'',
		contactMobile:'',
		applyFileUrl:'',
		status:'CREATED',
		createTime:''
	},
	/*computed: {
		statusCreated: function () {
		  if(this.status == 'CREATED'){
			  return "cur";
		  }else{
			  return '';
		  }
	  },
	  statusApproved: function () {
		  if(this.status == 'APPROVED'){
			  return "cur";
		  }else{
			  return '';
		  }
	  },
	  statusRejected: function () {
		    return this.status == 'REJECTED' ? "cur":"";
	  },
	  statusDesc:function(){
		  switch (this.status) {
			case "CREATED": return "待审核";
			case "APPROVED": return "已审核";
			case "REJECTED": return "已拒绝";
			default:        return "待审核";
		}
	  }
	},*/
	mounted:function(){
		// Columns
		var columns =[
			{
				data: "companyName"
			}, {
				data: "createTime",
				render: function(val) {
					return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
				}
			}, {
				data: "status",
				render: function(val) {
					switch (val) {
						case "CREATED": return "待审核";
						case "APPROVED": return "已审核";
						case "REJECTED": return "已拒绝";
						default:        return val;
					}
				}
			}, {
				data: null,
				orderable: false,
				render: function(row) {
					var m = [];
				 	if (CommonsPrivileges.hasPrivilegeId("apply_in/modify")) {
						m.push('<a class="op op-check" href="#" data-row-id="' + row.applicationId + '">审批</a>');
					}
					return m.join('');
				}
			}
		];
			
		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
  			ajax: {
  				url: "listData"
  			},
			lengthChange: false,
			serverSide : true,
			searching : false,
			processing: true,
			order: [[1, "desc"]],
			columns : columns
		}));
		
		// 绑定事件
		ListFeaturePage.autobind(dt);
		var vm = this;
		// 修改
		dt.on("draw", function(settings, data) {
			$(".op-check").off().on("click", function(e) {
				modifyingId = $(e.target).attr("data-row-id");
				$.ajax({
					url: "toModifyApplyIn",
					type: "post",
					dataType: "json",
					data: { id:modifyingId },
					success: function(resp) {
						if (resp && resp.result == 0) {
							vm.companyName = resp.data.model.companyName;
							vm.contact = resp.data.model.contact;
							vm.contactMobile = resp.data.model.contactMobile;
							vm.createTime = DateFormat.format.date(resp.data.model.createTime, 'yyyy-MM-dd');
							vm.bpUrl = resp.data.fileInfo.bpUrl;
							vm.opUrl = resp.data.fileInfo.opUrl;
							vm.dtpUrl = resp.data.fileInfo.dtpUrl;
							vm.ctpUrl = resp.data.fileInfo.ctpUrl;
							vm.applyFileUrl = resp.data.fileInfo.applyFileUrl;
							vm.status =  resp.data.model.status;// 计算属性为什么没有用
							$("#status").val(resp.data.model.status);
							MU.mask();
							MU.center("#approvePanel");
							$("#approvePanel").show();	
						} else {
							MU.msgTips("error", resp.message, 1200);
						}
					},
					error: function() {
						MU.msgTips("error", "获取退租信息失败，请稍后重试", 1200);
					}
				});
			});
		});
	}
	
});