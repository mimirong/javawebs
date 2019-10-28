
var columns =[ {
				data: null,
				render : function(row) {
					return '<span title="'+row.resultText+'" class="wh" style="width:500px">' + row.resultText+ '</span>';
				}
				}, {
					 data: "createTime",
	                  render: function(val) {
	                      return DateFormat.format.date(new Date(val), 'yyyy-MM-dd');
	                  }
				}];
//DataTables
var questionId = null;
var dt = $("#data").DataTable(dataTableCommonOptions({
	ajax: {
		url: "resultData",
		data :  function(data) {
			data.searchParams = JSON.stringify({questionId:questionId});
		}
	},
	lengthChange : false,
	serverSide : true,
	searching : false,
	ordering : false,
	columns : columns
}));


$.ajax({
	url: "summaryData",
	type: "post",
	dataType: "json",
	data: {
		surveyId: surveyId
	},
	success: function(resp) {
		var dlStr = '';
		if (resp && resp.result == 0) {
			for (var i = 0; i < resp.data.length; i++) {
				var q = resp.data[i];

				// configData
				if (!q.configData) {
					q.configData = "{}";
				}
				q.config = JSON.parse(q.configData);

				// summary
				if (q.questionType == "0") {
					dlStr = dlStr+'<div class="questionItem"><div class="questionText">'
					+(i+1)+'.'+q.questionText+'</div><div class="questionItemValues"><dl class="clearfix">'
					dlStr = processRadioSummary(q,dlStr);
				} else if (q.questionType == "1") {
					dlStr = dlStr+'<div style="margin-bottom:15px" class="questionItem"><div class="questionText">'
					+(i+1)+'.'+q.questionText+'</div><div class="questionItemValues"><a href="javascript:;" class="blue op-result" question-id='+q.questionId+' title="查看结果">查看结果</a></div></div>'
				} else if (q.questionType == "2") {
					dlStr = dlStr+'<div class="questionItem"><div class="questionText">'
					+(i+1)+'.'+q.questionText+'</div><div class="questionItemValues"><dl class="clearfix">'
					dlStr = processCheckSummary(q,dlStr);
				}
			}
//			console.log(dlStr);
			$(".column_tab_con").append(dlStr);
			$(".op-result").on("click",function(e){
				questionId = $(e.target).attr("question-id")*1;
				
				dt.ajax.reload();
				
				$("#data").css("width","840px");
				MU.mask();
				//确保数据加载完之后再居中
				setTimeout(function(){
					MU.center("#detailPanel");
					$("#detailPanel").show();
				},100)
				
			})
			
		}
	},
	error: function() {
		alert("查询失败");
	}
});



// processRadioSummary
function processRadioSummary(q,dlStr) {
	// 计算单选问题参与调查的总数（没有参与调查的次数不计算在内即调查后加的问题次数不计算）
	var totalCount = 0;
	$.each(q.summary, function(j, summaryItem) {
		totalCount += summaryItem.count;
	});
	q.totalCount = totalCount;
	//用来记录选择过答案的数量
	var answeredCount = 0;
	// 处理每一个选项
	$.each(q.itSurveyOptionList, function(i, op) {
		dlStr= dlStr+'<dt>'+op.optionText+'</dt>';
		op.count = 0;
		op.percent = "0.00";
		$.each(q.summary, function(j, summaryItem) {
			if (summaryItem.optionId == op.optionCode) {
				op.count = summaryItem.count;
				answeredCount = answeredCount +summaryItem.count;
				op.percent = (op.count * 100 / totalCount)
						.toFixed(2);
			}
		});
		
		dlStr = dlStr+'<dd><span class="progress-per"><span class="rate"style="width:'
		+op.percent+'%"></span></span></dd><dd class="last">'+op.percent+'%</dd>'
	});
	//判断总数和有答案的条数是否相等
	if(totalCount!= answeredCount){
		//未填写百分比
		var noAnswerPercent = ((totalCount - answeredCount) * 100 / totalCount).toFixed(2);
		dlStr = dlStr+'<dt>'+'未填写'+'</dt>'
				+'<dd><span class="progress-per"><span class="rate"style="width:'
				+noAnswerPercent+'%"></span></span></dd><dd class="last">'+noAnswerPercent+'%</dd>'
		;
	}
	
	dlStr = dlStr+'</dl></div></div>'
	return dlStr;
}

//processCheckSummary
function processCheckSummary(q,dlStr) {
	console.log(q);
	
	// 计算单选问题参与调查的总数（没有参与调查的次数不计算在内即调查后加的问题次数不计算）
	var totalCount = 0;
	$.each(q.summary, function(j, summaryItem) {
		totalCount += summaryItem.count;
	});
	q.totalCount = totalCount;
	
	// 处理每一个选项
	$.each(q.itSurveyOptionList, function(i, op) {
		dlStr= dlStr+'<dt>'+op.optionText+'</dt>';
		op.count = 0;
		op.percent = "0.00";
		$.each(q.summary, function(j, summaryItem) {
			if (summaryItem.optionId == op.optionCode) {
				op.count = summaryItem.count;
				op.percent = (op.count * 100 / totalCount) .toFixed(2);
			}
		});
		
		dlStr = dlStr+'<dd><span class="progress-per"><span class="rate"style="width:'
		+op.percent+'%"></span></span></dd><dd class="last">'+op.percent+'%</dd>'
	});
	
	dlStr = dlStr+'</dl></div></div>'
	return dlStr;
}
