var chooseCount;
var surveyId = $("#surveyId").val();
var questionType = '0';
// Columns
var columns =[
	{
		data: null,
		render: function(data, type, row, meta) {
			return meta.row + 1;
		}
	}, {
		data: "questionType",
		render: function(val) {
			switch (val) {
				case "0": return "单选";
				case "1": return "文本输入";
				case "2": return "多选";
				default: return val;
			}
		}
	}, {
		data: "configData",
		render: function(configData) {
				return '<span>'+configData+'</span>';
		}
	}, {
		data: "isRequired",
		render: function(isRequired) {
			if (isRequired) {
				return '<span class="green">是</span>';
			} else {
				return '<span class="red">否</span>';
			}
		}
	}, {
		data: null,
		orderable: false,
		createdCell: function(td) {
			$(td).css('text-align', 'center');
		},
		render: function(row) {
			var m = [];
			m.push('<a href="javascript:;" class="txt-op btnMoveUp" data-row-id="' + row.questionId + '">上移</a> ');
			m.push('<a href="javascript:;" class="txt-op btnMoveDown" data-row-id="' + row.questionId + '">下移</a> ');
			m.push('<a href="javascript:;" class="txt-op btnModify" data-row-id="' + row.questionId + '">修改</a> ');
			m.push('<a href="javascript:;" class="txt-op btnDelete" data-row-id="' + row.questionId + '">删除</a> ');
			return m.join('');
		}
	}
];
	
// DataTables
var dt = $("#data").DataTable(dataTableCommonOptions({
	pageLength: 100000,
	dom: dataTableDomNoPagination(),
	ajax: {
		url: "listData",
		data: {surveyId:surveyId}
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

//监听新增选项
var addChooseBind = function(){
	 $(".add-choose").off().on("click",function(){
    	chooseCount =  $("#chooseUl").children("li.chooseLi").length;
    	var chooseText = $("#chooseText").val();
    	if(!chooseText){
    		 MU.msgTips("warn", "请输入选项内容", 1200);
    		 return
    	}
    	$("#btLi").before('<li class="chooseLi"><a class="chooseDelte" href="javascript:;" style="margin-right:10px;color:#107aee" choose-id='+chooseCount+'>删除</a><span>'+chooseText+'</span></li>');
    	$("#chooseText").val(""); 
    	chooseCount++;
    	$(".chooseDelte").off().on("click",function(){
    		$(this).parent().nextUntil("#btLi").remove();
    		$(this).parent().remove();
    	});
    	$("#chooseText").focus();
	 });
}
//监听下拉框修改
var typeSelectBind = function(){
	$("#typeSelect").siblings("ul").find("li").off().on("click", function(){ 
		//初始化chooseCount和选项
		$("#btLi").siblings().remove(); 
		questionType = $(this).attr('data-value');
		$("#typeSelect").val(questionType).triggerHandler("change");
		
		// 单选和多选 显示下面选项列表配置
		if (questionType == '0' || questionType == '2') {
			$("#optionLi").show();
		} else {
			$("#optionLi").hide();
		}
	});
}	 
//获取题目参数
var getParams = function(questionType){
	if($("#questionText").val()==""){
		 MU.msgTips("warn", "请输入题目", 1200);
		 return null
	}
	var chooseLiList = [];
	if(questionType=='0' || questionType=='2'){
		var chooseLiListTag = $("#chooseUl").children("li.chooseLi");
		if(chooseLiListTag.length<2){
			 MU.msgTips("warn", "选项至少需要两个", 1200);
			 return null
		}
		$.each(chooseLiListTag, function(k, v) {
			var chooseLiObj = {}
			chooseLiObj.optionCode = $(v).children("a").attr("choose-id");
			chooseLiObj.optionText = $(v).children("span").text();
			chooseLiObj.surveyId = surveyId;
			chooseLiList.push(chooseLiObj);
		})
//		console.log(chooseLiList)
	}
	var isRequired =$('.checkbox').hasClass('checked');
	var questionText = $("#questionText").val()
	return {
		surveyId:surveyId,
		questionText:questionText,
		isRequired:isRequired,
		questionType:questionType,
		itSurveyOptionList:JSON.stringify(chooseLiList)
	}
}

// 点击新增按钮
$(".add-topic").off().on("click", function() {
	$(".add-question").text("新增题目");
	$("#optionLi").show();
	$('.checkbox').addClass('checked');
	$("#typeSelect").val('0').triggerHandler("change");
	questionType = '0';
	$("#questionText").val("");
	//初始化chooseCount和选项
	$("#btLi").siblings().remove(); 
    $("#btnDoAdd").show();
    MU.mask();
    MU.center("#addPanel");
    $("#addPanel").show();
    //绑定事件
    typeSelectBind();
	addChooseBind();
	//监听新增题目
	$(".add-question").off().on("click",function(){
		var data = getParams(questionType);
		if(data == null){
			return
		}
		$.ajax({
			url: "saveQuestion",
			type: "post",
			dataType: "json",
			data: data,
			success: function(resp) {
				if (resp && resp.result == 0) {
					MU.msgTips("success", "添加成功", 1200);
	                MU.hide($(".add-question"));
					dt.ajax.reload();
				} else {
					alert(resp.message);
				}
			},
			error: function() {
				alert("添加问题失败");
			}
		});
	});
})


// 绑定每行事件
dt.on('draw', function(settings, data) {
	// 上移
	$(".btnMoveUp").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		var idlist = findBannerIdList();
		if (idlist.length <= 1) {
			return;
		}
		for (var i = 1; i < idlist.length; i++) {
			if (idlist[i] == id) {
				var swap = idlist[i - 1];
				idlist[i - 1] = idlist[i];
				idlist[i] = swap;
				break;
			}
		}
		saveOrder(idlist);
	});
	
	// 下移
	$(".btnMoveDown").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		var idlist = findBannerIdList();
		if (idlist.length <= 1) {
			return;
		}
		
		for (var i = 0; i < idlist.length - 1; i++) {
			if (idlist[i] == id) {
				var swap = idlist[i + 1];
				idlist[i + 1] = idlist[i];
				idlist[i] = swap;
				break;
			}
		}
		saveOrder(idlist);
	});
	//修改
	$(".btnModify").off().on("click", function(e) {
		var id = $(e.target).attr("data-row-id");
		$(".add-question").text("修改题目");
		//查询原始数据
		$.ajax({
			url: "queryQuestion",
			type: "post",
			dataType: "json",
			data: {
				questionId: id
			},
			success: function(resp) {
				if (resp && resp.result == 0) {
					$("#typeSelect").val(resp.data.questionType).triggerHandler("change");
					questionType = resp.data.questionType;
					$("#questionText").val(resp.data.questionText);
					if(resp.data.isRequired){
						$('.checkbox').addClass('checked');
					}
					else{
						$('.checkbox').removeClass('checked');
					}
					if(resp.data.questionType=='0' || resp.data.questionType=='2'){
						$("#btLi").siblings().remove(); 
						$.each(resp.data.itSurveyOptionList, function(k, v) {
							$("#btLi").before('<li class="chooseLi"><a class="chooseDelte" href="javascript:;" style="margin-right:10px;color:#107aee" choose-id='+v.optionCode+'>删除</a><span>'+v.optionText+'</span></li>');
							$(".chooseDelte").off().on("click",function(){
					    		$(this).parent().nextUntil("#btLi").remove();
					    		$(this).parent().remove();
					    	})
						})
						$("#optionLi").show();
					}
					else{
						$("#optionLi").hide();
					}
					 //绑定事件
				    typeSelectBind();
					addChooseBind();
					$(".add-question").off().on("click", function() {
						var data = getParams(questionType);
						if(data == null){
							return
						}
						
						data.questionId = resp.data.questionId
						$.ajax({
							url: "updateQuestion",
							type: "post",
							dataType: "json",
							data: data,
							success: function(resp) {
								if (resp && resp.result == 0) {
									MU.msgTips("success", "修改成功", 1200);
					                MU.hide($(".add-question"));
									dt.ajax.reload();
								} else {
									alert(resp.message);
								}
							},
							error: function() {
								alert("修改信息失败");
							}
						});
						
					})
				} else {
					alert(resp.message);
				}
			},
			error: function() {
				alert("查询信息失败");
			}
		});
		MU.mask();
	    MU.center("#addPanel");
	    $("#addPanel").show();
		
	});
	
});
		
		
// 获取所有ID
function findBannerIdList() {
	var list = [];
	for (var i = 0; i < dt.data().length; i++) {
		var row = dt.data()[i];
		list.push(row.questionId);
	}
	return list;
}
		
// 将排序信息保存到服务器
function saveOrder(list) {
	$.ajax({
		url: "setOrder",
		type: "post",
		dataType: "json",
		data: {
			idlist: list
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				dt.ajax.reload();
			} else {
				alert(resp.message);
			}
		},
		error: function() {
			alert("保存排序信息失败");
		}
	});
}
