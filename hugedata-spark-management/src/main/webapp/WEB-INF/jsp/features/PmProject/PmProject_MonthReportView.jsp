<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>项目月报</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>
<div class="ng-cloak" ng-app="monthReportModule" ng-controller="monthReportController">   
    <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
    <input type="hidden" id="reportId" value="${report.reportId }"/>
   <input type="hidden" id="month" value="${month}"/>
      <h2 class="title">项目信息<a href="detail?serviceId=${projectId}" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?serviceId=${projectId}">基本信息</a><a  href="doc1?projectId=${projectId }">手续文件</a><a  href="doc2?projectId=${projectId }">文档资料</a><a class="on"  href="monthReport?projectId=${projectId }">项目月报</a><a href="yearPlan?projectId=${projectId }">年度计划</a></div>
       
       <div class="tab-content-left">
          <dl class="clearfix">
                <dt>
               		<span class="select yearList" style="width: 160px;">
               			<select>
               				<c:forEach var="y" items="${yearList}">
								 <c:choose>
								   <c:when test="${y == year }">  
								    <option value="${y}" selected="selected">${y}年</option>
								   </c:when>
								   <c:otherwise> 
								 	 <option value="${y}" >${y}年</option>
								   </c:otherwise>
								</c:choose>
							</c:forEach>
                  		</select>
               		</span>
               </dt>
              <c:forEach var="m"   items="${monthListInfo}">
						<c:choose>
						   <c:when test="${m.status == 1  &&   month != m.month }">  
						    <dd><a href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span><em class="icon checked"></em></a></dd>
						   </c:when>
						   <c:when test="${m.status == 1  &&   month == m.month }">  
						    <dd><a class="on" href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span><em class="icon checked"></em></a></dd>
						   </c:when>
						   <c:when test="${m.status == 0  &&   month != m.month }">  
						    <dd><a href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span><em class="icon check-wait"></em></a></dd>
						   </c:when>
						    <c:when test="${m.status == 0  &&   month == m.month }">  
						    <dd><a  class="on"  href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span><em class="icon check-wait"></em></a></dd>
						   </c:when>
						    <c:when test="${m.status == -1  &&   month != m.month }">  
						    <dd><a href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span></a></dd>
						   </c:when>
						    <c:when test="${m.status == -1  &&   month == m.month }">  
						    <dd><a  class="on"  href="monthReport?projectId=${projectId }&month=${m.month}&year=${m.year}"><span>${m.month }月</span></a></dd>
						   </c:when>
						   <c:otherwise> 
						 	 
						   </c:otherwise>
						</c:choose>
			  </c:forEach>
          </dl>
      </div>
      <div class="tab-content tab-content-right">
          <h4 class="title">${project.projectName }项目完成情况（${month}月）</h4>
          <table class="table">
              <tr>
       		    <c:choose>
				   <c:when test="${yearPlan != null   }"> 
				   <td class="tit w180"><em></em>年计划投资（万元）</td><td width="160" id="amount">${yearPlan.amount }</td> 
				   </c:when>
				   <c:otherwise> 
					 <td class="tit w180"><em></em>年计划投资（万元）</td><td width="160" id="amount">暂无数据</td> 						 	 
				   </c:otherwise>
				</c:choose>
                  <td class="tit w180"><em></em>总投资规模</td><td id="investTotal">${project.investTotal }</td>
              </tr>
              <tr>
                  <td class="tit w180"><em>*</em>本月完成投资（万元）</td><td id="investedNum">${report.investedNum }</td>
                  <c:choose>
				   <c:when test="${yearPlan != null   }"> 
				    <td class="tit w180"><em></em>占年计划百分比</td><td id="investedNumPercent"></td>
				   </c:when>
				   <c:otherwise> 
					<td class="tit w180"><em></em>占年计划百分比</td><td>暂无数据</td>					 	 
				   </c:otherwise>
				</c:choose>
              </tr>
              <tr>
                  <td class="tit w180"><em></em>本年实际完成投资（万元）</td><td id="yearInvestedTotal">${yearInvestedTotal}</td>
                  
                  <c:choose>
				   <c:when test="${yearPlan != null   }"> 
				   <td class="tit w180"><em></em>占年计划百分比</td><td id="yearInvestedPercent"></td>
				   </c:when>
				   <c:otherwise> 
					<td class="tit w180"><em></em>占年计划百分比</td><td>暂无数据</td>					 	 
				   </c:otherwise>
				</c:choose>
              </tr>
          </table>
            <h4 class="title mt20">投资情况</h4>
          <table class="column_tab_table mt20">
              <thead>
              <tr>
                  <td>序号</td>
                  <td>到位资金类型</td>
                  <td>主要来源</td>
                  <td>到位资金金额（万元）</td>
              </tr>
              </thead>
              <tbody>
              
               <tr ng-repeat="item in dataList">
			      	 <td>{{$index+1}}</td>
			      	 <td>{{item.comeTypeName}}</td>
			         <td>{{item.comeFrom}}</td>
			         <td>{{item.amount.toFixed(2)}}</td>
		       </tr>
		       
              </tbody>
          </table>
          <table class="column_tab_table mt20">
              <thead>
              <tr>
                  <td>步骤</td>
                  <td>资金用途类型</td>
                  <td>资金用途金额</td>
              </tr>
              </thead>
              <tbody>
              <tr>
                  <td>1</td>
                  <td>咨询勘察设计</td>
                  <td id="useDesign">${report.useDesign }</td>
              </tr>
              <tr>
                  <td>2</td>
                  <td>监理</td>
                  <td id="useOversee">${report.useOversee }</td>
              </tr>
              <tr>
                  <td>3</td>
                  <td>工程投资</td>
                  <td id="useEngineer">${report.useEngineer}</td>
              </tr>
              <tr>
                  <td>4</td>
                  <td>设备材料采购</td>
                  <td id="useBuy">${report.useBuy }</td>
              </tr>
              <tr>
                  <td>5</td>
                  <td>征地拆迁</td>
                  <td id="useAsset">${report.useAsset }</td>
              </tr>
              <tr>
                  <td></td>
                  <td>总计</td>
                  <td id="useTotal">${report.useTotal }</td>
              </tr>
              </tbody>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em>*</em>当前工程建设形象进度</td>
                  <td colspan="3">
                      <p class="des">
                         ${report.current}
                      </p>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>征地拆迁情况</td>
                  <td colspan="3">
                      <p class="des">
                         ${report.asset }
                      </p>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>存在困难及问题</td>
                  <td colspan="3">
                      <p class="des">
                       ${report.difficult }
                      </p>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>问题涉及单位</td>
                  <td colspan="3">
                      <p class="des">
                        ${report.difficultOrg}
                      </p>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>备注及建议</td>
                  <td colspan="3">
                      <p class="des">
                        ${report.comment }
                      </p>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>审批情况</td>
                  <td colspan="3">
                      <p class="des">
                        ${report.approveInfo }
                      </p>
                  </td>
              </tr>
          </table>
      </div> 
      
      
      
  </div>
 
  
  
<script type="text/javascript" src="${contextPath}/static/js/spark/PmProject_MonthReport.js"></script>
<script type="text/javascript" >
$('div.leftsidebar').remove();
$('div.column_list').removeClass('column_list');
$('div.column_con').attr('style','padding: 0 20px 35px;') ;

$('#investTotal').text(parseFloat($('#investTotal').text()).toFixed(2));
$('#yearInvestedTotal').text(parseFloat($('#yearInvestedTotal').text()).toFixed(2));
if($('#investedNum').text() != ''){
	$('#investedNum').text(parseFloat($('#investedNum').text()).toFixed(2));	
}

if($('#amount').text() != '暂无数据'){
	$('#amount').text(parseFloat($('#amount').text()).toFixed(2));
	if($('#amount').text() != '0.00'){
		var yearInvestedTotal = parseFloat($('#yearInvestedTotal').text());
		var amount = parseFloat($('#amount').text());
		var percentNum = (yearInvestedTotal*100)/amount;
		$('#yearInvestedPercent').text(percentNum.toFixed(2) + '%');
		if($('#investedNum').text() != ''){
			$('#investedNum').text(parseFloat($('#investedNum').text()).toFixed(2));
			var investedNum = parseFloat($('#investedNum').text());
			percentNum =  (investedNum*100)/amount;
			$('#investedNumPercent').text(percentNum.toFixed(2) + '%');	
			$('#useDesign').text(parseFloat($('#useDesign').text()).toFixed(2));
			$('#useOversee').text(parseFloat($('#useOversee').text()).toFixed(2));
			$('#useEngineer').text(parseFloat($('#useEngineer').text()).toFixed(2));
			$('#useBuy').text(parseFloat($('#useBuy').text()).toFixed(2));
			$('#useAsset').text(parseFloat($('#useAsset').text()).toFixed(2));
			$('#useTotal').text(parseFloat($('#useTotal').text()).toFixed(2));
		}
	}
}


</script>	
</body>