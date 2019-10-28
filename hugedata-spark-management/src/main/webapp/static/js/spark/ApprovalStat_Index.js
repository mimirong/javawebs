// 加载数据
function loadData() {
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();

	if (endDate < beginDate) {
		MU.msgTips("warn", "结束时间不能早于开始时间");
		return;
	}
	
	$.ajax({
		url: "queryStatData",
		type: "post",
		dataType: "json",
		data: {
			beginDate: beginDate,
			endDate: endDate
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				$(".chartGroup").remove();
				$.each(resp.data, function(i, item) {
					//重新计算受理数
					item.acceptedCount = item.inProgressCount + item.finishedCount;
					
					showChartGroup(item);
				});
			} else {
				MU.msgTips("error", resp.message);
			}
		},
		error: function() {
			MU.msgTips("error", "加载数据失败，请稍后重试");
		}
	});
}

//显示一组图表 (左右两个)
function showChartGroup(data) {
	var totalCount = data.inProgressCount + data.finishedCount + data.rejectedCount;
	if (totalCount == 0) {

		var m = [];
		m.push('');
		m.push('<div class="echart-block nodata chartGroup" style="width:960px;">');
		m.push('  <div class="title">' + data.userName + '</div>');
		m.push('  <p>没有数据</p>');
		m.push('</div>');
		$("#chartWrapper").append(m.join(""));
		
	} else {
		
		var lchartid = window.nextChartId();
		var rchartid = window.nextChartId();

		var exportUrl = "exportDetails?beginDate=" + $("#beginDate").val() + "&endDate=" + $("#endDate").val()
				+ "&userId=" + data.userId;
		
		var m = [];
		m.push('');
		m.push('<div class="echart-block chartGroup">');
		m.push('  <div class="title">');
		m.push(     data.userName + ' - 累计经手办件：' + totalCount + ' 件');
		m.push('    <a href="' + exportUrl + '" target="_blank" class="btn btn-export" style="float:right"> 导出明细</a>');
		m.push('  </div>')
		m.push('  <div class="clearfix" style="width:900px;">');
		m.push('    <div class="fl" style="width:400px; height:320px;" id="' + lchartid + '"></div>');
		m.push('    <div class="fr" style="width:400px; height:320px;" id="' + rchartid + '"></div>');
		m.push('    <div class="text"><div class="a">办结率</div><div class="b"></div></div>')
		m.push('  </div>');
		m.push('</div>');
		$("#chartWrapper").append(m.join(""));
		
		showLeftChart(lchartid, data);
		showRightChart(rchartid, data);
	}
}

//显示左侧柱状图
function showLeftChart(divId, data) {
    var chart = echarts.init(document.getElementById(divId));
    var options = {
    	grid: { top: 15, left: 30, bottom: 30, right: 10 },
    	xAxis: {
            data: [ "受理", "办结", "办理中", "废弃" ],
            axisTick: { show: false },
            axisLabel: { color: "#222222" },
            axisLine: { 
            	lineStyle: { color: "#7E7E7E" } 
            }
        },
        yAxis: {
            axisTick: { show: false },
            axisLabel: { color: "#222222" },
            axisLine: {
            	lineStyle: { color: "#7E7E7E" }
            },
            splitLine: {
            	lineStyle: { color: "#E6E5E5" }
            }
        },
        series: [
            {
                type: 'bar',
                hoverAnimation: false,
                barWidth: "33px",
                itemStyle: {
                	normal: { color: "#107AEE" }
                },
                label: {
                    normal: { 
	                	show: true,
	                	position: "insideTop",
	                	formatter: "{c}",
	                	color: "#fff"
                    }
                },
                data:[
                    { name: "受理",		value: data.acceptedCount   },
                    { name: "办结",		value: data.finishedCount   },
                    { name: "办理中",	value: data.inProgressCount },
                    { name: "废弃",		value: data.rejectedCount   },
                ]
            }
        ]
    };
    chart.setOption(options);
}

//显示右侧饼图
function showRightChart(divId, data) {
	var total = data.inProgressCount + data.finishedCount;
	var done = data.finishedCount;
	
	var div = $("#" + divId);
	var textDiv = div.siblings(".text");
	textDiv.css({
		top: div.position().top,
		left: div.position().left,
		width: div.width()
	});
	textDiv.find(".b").html(parseInt(done / total * 100) + "%");
	
    var chart = echarts.init(document.getElementById(divId));
    var options = {
    	grid: { top: 15, left: 30, bottom: 30, right: 10 },
        series: [
            {
                type: 'pie',
                radius: [ "53%", "70%" ],
                hoverAnimation: false,
                label: {
                    normal: { 
                    	show: true,
                    	fontSize: 16,
                    	color: "#59676B",
                    	align: "center",
                    	formatter: "{b}\n{c}件"
                	},
                },
                data:[
                	{
                		name: "办结",
                		value: done,
                        itemStyle: { normal: { color: "#F6AB01" } }
            		}, {
            			name: "未办结",
            			value: total - done,
                        itemStyle: { normal: { color: "#107AEE" } }
        			}
                ]
            }
        ]
    };
    chart.setOption(options);
}

//生成唯一ID
(function() {
	var maxId = 100;
	window.nextChartId = function() {
		return "CHART_" + maxId++;
	};
})();

//初始化日期选择
$(".dateInput").on("focusin", function(e) {
	$(this).prop('readonly', true);
}).on("focusout", function(e) {
	$(this).prop('readonly', false);
}).datepicker({
	showOtherMonths: true,
	selectOtherMonths: true,
	dateFormat: "yy-mm-dd"
});

// 初始化默认日期
(function() {
	var begin = new Date().getFullYear() + "-01-01";
	$("#beginDate").val(begin);
	$("#endDate").val(DateFormat.format.date(new Date(), "yyyy-MM-dd"));
})();

// 初始化加载
loadData();

$(".btnReload").on("click", loadData);

