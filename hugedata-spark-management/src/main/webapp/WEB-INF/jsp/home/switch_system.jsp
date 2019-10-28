<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="webkit">
	<meta name="decorator" content="simple"> 
</head>
<body>
	<script>
		(function() {
			switch ("${param.systemType}") {
				case "OUTSOURCING":
					//location.href = "${contextPath}/features/OsDetecApprove/list"; // 2018-01-23改版
                    location.href = "${contextPath}/features/OsExpertsList/list";
					break;
				case "ACCOUNTS":
					location.href = "${contextPath}/features/UcCompanyUser/list";
					break;
				case "APPROVAL":
					location.href = "${contextPath}/features/ApprovalonLine/list";
					break;
				case "PM":
					location.href = "${contextPath}/features/PmProject/list";
					break;
				case "INFORMATION":
                    location.href = "${contextPath}/features/PtHomeImage/list";
				    break;
				case "IT":
                    location.href = "${contextPath}/features/Interactive/list";
				    break;
				case "INTEGRATED":
                    location.href = "${contextPath}/features/GaParkJoinApplication/list";
				    break;
			}
		})();
	</script>
</body>