/*
	CodeSelector

	function setValue(id, value)
	============================
	设置一个CodeSelector选择框的值
	参数:
	    id    ---- CodeSelector的ID，通过<m:codeSelector id="xx" />的id属性指定
	    value ---- 选项的值(code)

	
	function getItemName(codeGroup, code, callback)
	===============================================
	根据选项的code获得选项的文本
	参数:
	    codeGroup ---- 选项组的名称，通过CodeSelectorInitializer注册的名称
	    code      ---- 选项的值(code)
	    callback  ---- 用于返回选项文本的回掉函数: function("选项文本")
	
	
	function showItemName(codeGroup, code, element)
	===============================================
	通过一个选项组的code将文本设置到HTML
	参数:
	    codeGroup ---- 选项组的名称，通过CodeSelectorInitializer注册的名称
	    code      ---- 选项的值(code)
	    element   ---- HTML元素，支持span, input, select


	function initSelector(params)
	=============================
	初始化CodeSelector选择框，通过<m:codeSelector />调用
 */

(function() {

var globalCreated = {};            // Map[CodeSelectorId, Params]
var globalCachedCodeItemMap = {};  // Map[CodeGroup, Map[Code, Item]]
var globalCachedTreeRoots = {};    // Map[CodeGroup, Array[RootItems]]
	
// error
function error(msg) {
	MU.msgTips("error", msg);
}

// logError
function logError(msg) {
	try {
		console.error(msg);
	} catch (e) {
	}
}

// setValue
function setValue(id, value) {
	var params = globalCreated[id];
	if (!params) {
		logError("No code selector with id: " + id);
		return;
	}
	params.value = "" + value;
	initSelector(params);
}

// getValue
// eg. CodeSelector.getItemName("orgType", "631", function(name) {
//         alert(name);
//     });
function getItemName(codeGroup, code, callback) {
	loadCodeGroupData(codeGroup, function(codeItemMap, treeRoots) {
		var item = codeItemMap[code];
		if (item && callback) {
			callback(item.name, item);
		}
	});
}

// showValue
// eg. CodeSelector.showItemName("orgType", "631", $("#orgType"))
function showItemName(codeGroup, code, element) {
	element = $(element);
	loadCodeGroupData(codeGroup, function(codeItemMap, treeRoots) {
		var item = codeItemMap[code];
		if (item) {
			if (element.is("select")) {
				element.val(item.name).triggerHandler("change");
			} else if (element.is("textarea")) {
				element.html(item.name);
			} else if (element.is("span")) {
				element.html(item.name);
			} else {
				element.val(item.name);
			}
		}
	});
}

// initSelector
function initSelector(params) {
	globalCreated[params.id] = params;
	
	var id = params.id;
	var codeGroup = params.codeGroup;
	var value = params.value;
	var inputIdForCode = params.inputIdForCode;
	var inputIdForName = params.inputIdForName;
	var inputIdForFullName = params.inputIdForFullName;
	var inputIdForFullCode = params.inputIdForFullCode;
	var itemWidth = params.itemWidth;
	var allowSelectingNonLeaf = params.allowSelectingNonLeaf;
	var promptText = params.promptText;
	var onchange = params.onchange;
	var maxLevel = params.maxLevel;
	var readonly = params.readonly;
	
	var codeInput = $("#" + inputIdForCode);
	var nameInput = $("#" + inputIdForName);
	var codeItemMap = {};
	var treeRoots = [];
	
	codeInput.val("");
	nameInput.val("");
	
	// show html
	function makeHtml() {
		var showseq = []; //获取每一级选中的值
	
		// find select sequence
		if (value && value != "") {
			var item = codeItemMap[value];
			while (item) {
				showseq.push(item.code);
				item = codeItemMap[item.parentCode];
			}
		}
		showseq = showseq.reverse();
		
		// 构造HTML
		var m = [];            // HTML
		var level = 0;         // 当前级别
		var list = treeRoots;  // 当前级别的数据
		var finalSelection = null; // 最终选中的节点
		while (list) {
			var levelValue = showseq[level];  //当前级别选中的值
			var levelSelection = null;        //当前级别选中的项
			
			// 构造一个级别的HTML
			m.push('<div class="select" style="margin-right:4px; width:' + itemWidth + ';">')
			m.push('<select class="codeSelectorSelect" style="width:' + itemWidth 
					+ ';" id="codeSelectorSelect_' + id + "_" + level + '" data-level="' + level + '">');
			m.push('<option value="">', promptText, '</option>')
			$.each(list, function(i, item) {
				var selected = "";
				if (levelValue == item.code) {
					selected = ' selected="selected" ';
					levelSelection = item;
				}
				m.push('<option value="' + item.code + '"', selected ,'>');
				m.push(item.name);
				m.push('</option>');
			});
			m.push('</select>');
			m.push('</div>');
			
			// 处理选中
			if (levelSelection) {
				if (levelSelection.items && levelSelection.items.length > 0) {
					// 非叶子结点选中
					
					// 判断是否超过最大显示级别，获取下一级别的选项数据
					var maxLevelHit = (maxLevel != 0 && level == maxLevel - 1);
					if (maxLevelHit) {
						list = null;
					} else {
						list = levelSelection.items;
					}
					
					// 处理非叶子结点选中
					if (allowSelectingNonLeaf) {
						finalSelection = levelSelection;
					} else if (maxLevelHit) {
						finalSelection = levelSelection;
					} else {
						finalSelection = null;
					}
				} else {
					// 叶子结点
					list = null;
					finalSelection = levelSelection;
				}
			} else {
				// 未选择
				list = null;
			}
			
			level++;
		}
		processSelected(finalSelection);
		$("#codeSelector_" + id).html(m.join(""));
		
		// change to selectList
		for (var i = 0; i < level; i++) {
			var select = $("#codeSelectorSelect_" + id + "_" + i);
			select.selectList();
			
			if (readonly) {
				select.siblings("div").off("click");
			}
			
			var ul = select.siblings(".select-list");
			ul.children("li").on("click", function(e) {
				var val = $(this).attr("data-value");
				if (!val || val == "" || val == "undefined") {
					var select = $(this).parent().siblings("select");
					var level  = select.attr("data-level");
					if (parseInt(level) > 0) {
						val = $("#codeSelectorSelect_" + id + "_" + (level - 1)).val();
					}
				}
				value = val;
				makeHtml();
			});
		}
	}
	
	// 处理选中，触发选中事件
	function processSelected(item) {
		if (item) {
			// 获得完整name和code
			var fullNameArr = [], 
				fullCodeArr = [],
				i = item;
			while (i) {
				fullNameArr.push(i.name);
				fullCodeArr.push(i.code);
				if (i.parentCode) {
					i = codeItemMap[i.parentCode];
				} else {
					i = null;
				}
			}

			// 更新input，触发事件
			codeInput.val(item.code).triggerHandler("change");
			nameInput.val(item.name).triggerHandler("change");
			if (inputIdForFullName && inputIdForFullName != "") {
				$("#" + inputIdForFullName).val(fullNameArr.reverse().join(",")).triggerHandler("change");
			}
			if (inputIdForFullCode && inputIdForFullCode != "") {
				$("#" + inputIdForFullCode).val(fullCodeArr.reverse().join(",")).triggerHandler("change");
			}
			if (onchange && onchange != "" && window[onchange]) {
				window[onchange](item.code, item);
			}
		} else {
			codeInput.val("").triggerHandler("change");
			nameInput.val("").triggerHandler("change");
			if (inputIdForFullName && inputIdForFullName != "") {
				$("#" + inputIdForFullName).val("").triggerHandler("change");
			}
			if (inputIdForFullCode && inputIdForFullCode != "") {
				$("#" + inputIdForFullCode).val("").triggerHandler("change");
			}
			if (onchange && onchange != "" && window[onchange]) {
				window[onchange](null);
			}
		}
	}
	
	// load all options
	loadCodeGroupData(codeGroup, function(retCodeItemMap, retTreeRoots) {
		codeItemMap = retCodeItemMap;
		treeRoots = retTreeRoots;
		makeHtml();
	});
}

// Convert response data to tree structure
// @param array Array[Item] 
// @return      Array[Map[Code,Item], Array[RootItems]]
function buildToTree(array) {
	// to map[code, item]
	var map = {};
	$.each(array, function(i, item) {
		map[item.code] = item;
	});
	
	// roots
	var roots = [];
	
	// convert
	$.each(array, function(i, item) {
		if (item.parentCode && item.parentCode != "") {
			var parent = map[item.parentCode];
			if (!parent) {
				logError("Parent code not found: " + item.parentCode);
				return;
			}
			if (!parent.items) {
				parent.items = [];
			}
			item.isRoot = false;
			parent.items.push(item);
		} else {
			roots.push(item);
			item.isRoot = true;
		}
	});
	
	return [map, roots];
}

// Cached item loading
function loadCodeGroupData(codeGroup, callback) {
	if (globalCachedCodeItemMap[codeGroup] && globalCachedTreeRoots[codeGroup]) {
		if (callback) {
			callback(globalCachedCodeItemMap[codeGroup], globalCachedTreeRoots[codeGroup]);
		}
	}
	
	var url = contextPath + "/commons-web/code-selector/listAll?_t=" + new Date().getTime()
			+ "&_r=" + Math.random();
	$.ajax({
		url: url,
		type: "POST",
		dataType: "json",
		data: {
			codeGroup: codeGroup
		},
		success: function(resp) {
			if (resp && resp.result == 0) {
				var parsed = buildToTree(resp.data);
				globalCachedCodeItemMap[codeGroup] = parsed[0];
				globalCachedTreeRoots[codeGroup] = parsed[1];
				if (callback) {
					callback(parsed[0], parsed[1]);
				}
			} else {
				error(resp.message);
			}
		},
		error: function() {
			error("加载数据失败");
		}
	});
}

// window.codeSelector
window.CodeSelector = {
	initSelector: initSelector,
	setValue: setValue,
	getItemName: getItemName,
	showItemName: showItemName
};

})();