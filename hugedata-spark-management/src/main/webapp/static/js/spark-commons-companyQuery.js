var CompanyQueryUtils = (function() {
	var $this = {};

	// 将input初始化为suggest查询
	$this.initSearchSuggest = function(selector) {
		var input = $(selector);
		var ul = $('<ul class="select-list"></ul>').appendTo(input.parent());
		var dataList = [];
		var selectIndex = -1;
		var keyword = "";
		
		// init
		input.attr("autocomplete", "off");

		// key up
		input.on("keyup", function(e) {
			if (keyword != input.val()) {
				queryCompanyNames(input.val(), function(list) {
					dataList = list;
					buildList(ul, input, list);
				});
				keyword = input.val();
			}
		});
		
		// key down
		input.on("keydown", function(e) {
			switch (e.keyCode) {
				case 38: //up
					if (selectIndex >= 0) {
						selectIndex--;
					}
					ul.children("li").removeClass("cur");
					ul.children("li:eq(" + selectIndex + ")").addClass("cur");
					e.preventDefault();
					break;
				case 40: //down
					if (selectIndex < dataList.length - 1) {
						selectIndex++;
					}
					ul.children("li").removeClass("cur");
					ul.children("li:eq(" + selectIndex + ")").addClass("cur");
					e.preventDefault();
					break;
				case 13: //enter
					keyword = dataList[selectIndex];
					input.val(keyword);
					ul.hide();
					e.preventDefault();
					break;
			}
		});

		// focus
		input.on("focus", function(e) {
			queryCompanyNames(input.val(), function(list) {
				dataList = list;
				buildList(ul, input, list);
			});
		});
		
		// blur
		input.on("blur", function(e) {
			setTimeout(function() {
				ul.hide();
			}, 200);
		});
	};
	
	// buildList
	function buildList(ul, input, list) {
		selectIndex = -1;
		ul.empty();
		
		$.each(list, function(i, item) {
			var li = $("<li>", { "data-value":item }).html(item);
			ul.append(li);
		});
		
		ul.css("left", input.position().left + "px")
			.css("top", input.position().top + input.height() + "px")
			.css("z-index", 10000)
			.show();

		ul.children("li").on("click", function(e) {
			keyword = $(e.target).html()
			input.val(keyword);
			ul.hide();
		});
	}
	
	// queryCompanyNames
	function queryCompanyNames(keyword, callback) {
		if (keyword == "") {
			return;
		}
		$.ajax({
			url: contextPath + "/global/queryCompanyNamesLike",
			type: "post",
			dataType: "json",
			data: { keyword:keyword },
			success: function(resp) {
				if (resp && resp.result == 0) {
					callback(resp.data);
				} else {
					error("Ajax error: " + resp.message);
				}
			},
			error: function() {
				error("Ajax error");
			}
		})
	};
	
	// log
	function error(a,b,c,d,e,f) {
		try {
			console.error(a,b,c,d,e,f)
		} catch(e) {
		}
	}
	
	return $this;
})();