<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>园区费用</title>
	<style>
		.showText p { margin:6px 0!important; }
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/spark/left_menu.jsp">
		<jsp:param name="currentMenu" value="fee" />
	</jsp:include>
	
    <div class="column_list">
      <h1 class="column_tit">您当前位置：<span>政务服务</span> > <a href="${contextPath}/fees/bills">园区费用</a></h1>
       <div class="con_tab">
            <ul class="con_tab_ul clearfix">
                <li class="" onclick="location.href = '${contextPath}/fees/bills'">账单</li>
                <li class="active">收费标准 </li>
            </ul>
            <div class="con_tab_box clearfix" style="display:block;">
                <div class="column_tab_con">
                    <table class="column_tab_table">
                        <thead>
	                        <tr>
	                            <td>收费项目</td>
	                            <td style="width: 644px;">收费标准</td>
	                        </tr>
                        </thead>
                        <tbody>
	                        <tr class="showPower" style="display:none">
	                            <td>电费</td>
	                            <td class="showText"></td>
	                        </tr>
	                        <tr class="showWater" style="display:none">
	                            <td>水费</td>
	                            <td class="showText"></td>
	                        </tr>
	                        <tr class="showProperty" style="display:none">
	                            <td>物业费</td>
	                            <td class="showText"></td>
	                        </tr>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
    	var data = ${data};
    	$.each(data, function(i, item) {
    		switch (item.standardId) {
	    		case "POWER":
	    			$(".showPower").show().children(".showText").html(item.remark);
	    			break;
	    		case "WATER":
	    			$(".showWater").show().children(".showText").html(item.remark);
	    			break;
	    		case "PROPERTY":
	    			$(".showProperty").show().children(".showText").html(item.remark);
	    			break;
    		}
    	});
    </script>

</body>
</html>