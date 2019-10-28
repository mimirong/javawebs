	var serviceId = null;
		var rowList = [];
		var columns = [
				{
					data : null,
					render : function(row) {
						var eventDesc = row.eventDesc.length > 40 ? row.eventDesc.substring(0,39)+'...' : row.eventDesc;
						return '<a  class=" blue-font " title="'+row.eventDesc+'" href="detail?serviceId='+row.postId+'">' + eventDesc+ '</a>';
					}
				
				},
				{
					data: null,
                    render : function(row) {
                    	if(row.postLevel && row.postLevel == '1'){
                    		return '<span class="red-font">紧急</span>';
                    	}else{
                    		return '<span>普通</span>';
                		}
					}
				},
				{
					data : "happenTime",
					render : function(val) {
						return DateFormat.format.date(new Date(val),'yyyy-MM-dd HH:mm:ss');
					}
				},
				{
					data : "postAddr"
				},
				{
					data : "posterName"
				},
				{
					data : "posterMobile"
				} ];

		// DataTables
		var dt = $("#data").DataTable(dataTableCommonOptions({
			ajax : {
				url : "listData",
				data : function(data) {
					ListFeaturePage.loadQueryValues(data, ".queryItem");
				}
			},
			lengthChange : false,
			serverSide : true,
			searching : false,
			ordering : false,
			columns : columns
		}));
		 
		// 搜索
		$("#btnSearch").on("click", function() {
			dt.ajax.reload();
		});
		$("input.queryItem").on("keydown", function(e) {
			if (e.keyCode == 13) {
				dt.ajax.reload();
			}
		});