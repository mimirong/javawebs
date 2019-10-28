// 下载服务
var DownloadService = (function() {
	var ds = {};
	ds.buildUrl = function(fileId, filename) {
        var url = downloadServiceUrl;
        if (url.lastIndexOf("/") != url.length - 1) {
        	url += "/";
        }
        url += fileId.substring(0, 7) + "_" + fileId.substring(fileId.length - 1) + "/";
        url += fileId;
        if (filename) {
            url += "?attname=" + encodeURIComponent(filename);
        }
        return url;
	};
	return ds;
})();

$(function() {
	$(".fileDownload").each(function(i, item) {
		item = $(item);
		var fileId = item.attr("data-fileid");
		var filename = item.attr("data-filename");
		if (!fileId) {
			return;
		}
		if (item.is("a")) {
			item.attr("href", DownloadService.buildUrl(fileId, filename));
		}
	});
});

jQuery(function($){
	$.datepicker.regional['zh-CN'] = {
		closeText: '关闭',
		prevText: '&#x3c;上月',
		nextText: '下月&#x3e;',
		currentText: '今天',
		monthNames: ['一月','二月','三月','四月','五月','六月',
		'七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort: ['一','二','三','四','五','六',
		'七','八','九','十','十一','十二'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
		dayNamesMin: ['日','一','二','三','四','五','六'],
		dateFormat: 'yy-mm-dd', firstDay: 1,
		isRTL: false};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

// 根据专业领域ID获取对应的专业领域的文字描述
var GetProfessionFieldTextById = function getProfessionFieldTextById(professionFieldId) {
    switch (professionFieldId) {
        case "SP_SERVICE_FIELD_CHEMISTRY": return "化学化工";
        case "SP_SERVICE_FIELD_CONSTRUCTION": return "建筑建材";
        case "SP_SERVICE_FIELD_NDT": return "无损检测";
        case "SP_SERVICE_FIELD_CONSUMER_GOODS": return "消费品";
        case "SP_SERVICE_FIELD_ENVIRON_HEALTH": return "环境监测与职业卫生";
        case "SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT": return "生物医药及器械";
        case "SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL": return "电子电气及机械";
        case "SP_SERVICE_FIELD_EMC": return "电磁兼容（EMC）";
        case "SP_SERVICE_FIELD_SPIN_FIBER": return "纺织及纤维";
        case "SP_SERVICE_FIELD_SOFTWARE_INFO": return "软件信息";
        case "SP_SERVICE_FIELD_METAL_MATERIAL": return "金属材料";
        case "SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS": return "食品及农产品";
        case "SP_SERVICE_FIELD_OTHER": return "其他";
        default: return "";
    }
}

// 根据成果形式ID获取对应的成果形式文字描述
var GetAchieveTypeTextById = function getAchieveTypeTextById(achieveTypeId) {
    switch (achieveTypeId) {
        case "NEW_PRODUCT": return "新产品";
        case "NEW_TECH": return "新技术";
        case "NEW_CRAFT": return "新工艺";
        case "NEW_MATERIAL": return "新材料";
        case "INVENTION_PATENT": return "发明专利";
        case "UTILITY_MODEL": return "实用新型";
        case "APPEARANCE_DESIGN": return "外观设计";
        case "TECH_SECRET": return "技术秘密";
        default: return "";
    }
}

// 根据货币单位ID获取对应的货币单位文字描述
var GetMonetaryTextById = function getMonetaryTextById(monetaryUnitId) {
    switch (monetaryUnitId) {
        case "YUAN": return "元";
        case "TEN_THOUSND_YUAN": return "万元";
        case "MILLION_YUAN": return "百万元";
        default: return "";
    }
}

// 根据投资成熟度ID获取对应的投资成熟度文字描述
var GetMaturityStageTextById = function getMaturityStageTextById(maturityStageId) {
    switch (maturityStageId) {
        case "MATURE_1": return "一般";
        case "MATURE_2": return "成熟";
        case "MATURE_3": return "很成熟";
        case "MATURE_4": return "非常成熟";
        default: return "";
    }
}

// 根据专业技术ID获取对应的专业技术文字描述
var GetTrainingTypeTextById = function getTrainingTypeTextById(trainingType) {
    switch (trainingType) {
        case "SPECIALTY_TECH": return "专业技术";
        case "TECH_POLICY": return "科技政策";
        case "AUTHENTICATION": return "认证认定";
        case "OCCUPATIONAL_SKILLS": return "职业技能";
        case "OCCUPATIONAL_QUALIFICATIONS": return "职业资格";
        case "TECH_MANAGEMENT": return "科技管理";
        case "OTHERS": return "其他";
        default: return "";
    }
}