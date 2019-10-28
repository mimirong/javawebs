angular.module("sparkGlobal", [])
	.filter('limit', function () {
	    return function (value, max) {
	        if (!value) {
	        	return '';
	        }
	        if (!max) {
	        	return value;
	        }
	        if (value.length <= max) {
	        	return value;
	        }
	        
	        value = value.substr(0, max);
	        var lastspace = value.lastIndexOf(' ');
	        if (lastspace != -1) {
	          if (value.charAt(lastspace-1) == '.' || value.charAt(lastspace-1) == ',') {
	            lastspace = lastspace - 1;
	          }
	          value = value.substr(0, lastspace);
	        }
	
	        return value + ' ...';
	    };
	});