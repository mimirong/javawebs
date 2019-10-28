angular.module("serviceGuideDetailsModule", [ ])

// Filter: deptName
.filter("deptName", function() {
	return function(val) {
		var DEPT_NAMES = {
		    "1": "经济发展局",
		    "2": "国土规划局",
		    "3": "工程建设局",
		    "4": "社会事务局",
		    "5": "招商合作局",
		    "6": "财政分局",
		    "7": "办公室",
		    "8": "党群纪检绩效办"
		};
		return DEPT_NAMES[val] ? DEPT_NAMES[val] : val;
	};
})

// Filter: makeUrl
.filter("makeUrl", function() {
	return function(fileId, filename) {
		 var url = downloadServiceUrl;
		 if (url.lastIndexOf("/") != url.length - 1) {
			 url += "/";
		 }
		 url += fileId.substring(0, 7) + "_" + fileId.substring(fileId.length - 1) + "/";
		 url += fileId;
		 if (filename) {
			 url += "?attname=" + filename;
		 }
		 return url;
	};
})

//Controller
.controller("serviceGuideDetailsController", ["$scope", "$sce", function($scope, $sce) {
	$scope.guide = window.guideData.guide;
	$scope.attachments = window.guideData.attachments;

	$scope.guide.conditions = $sce.trustAsHtml($scope.guide.conditions.replace(/\n/g, "<br/>"));
	$scope.guide.material = $sce.trustAsHtml($scope.guide.material.replace(/\n/g, "<br/>"));
	$scope.guide.process = $sce.trustAsHtml($scope.guide.process.replace(/\n/g, "<br/>"));
}]);



