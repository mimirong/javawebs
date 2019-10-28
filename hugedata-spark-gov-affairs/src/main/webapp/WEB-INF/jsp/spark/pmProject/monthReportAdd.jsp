<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目管理</title>
</head>
<body>

<div class="ng-cloak" ng-app="monthReportModule" ng-controller="monthReportController">   
   <!--banner-end-->
  <h1 class="column_tit">您当前位置：<a href="${contextPath}">首页</a> > <a href="list">项目管理</a> > <span>项目信息</span></h1>
  <!--content-start-->
  <div class="column_con clearfix" id="minH">
   <input type="hidden" id="reportId" value="${report.reportId }"/>
   <input type="hidden" id="month" value="${month}"/>
      <h2 class="title">项目信息<a href="detail?projectId=${projectId }" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?projectId=${projectId }">基本信息</a><a  href="doc1?projectId=${projectId }">手续文件</a><a  href="doc2?projectId=${projectId }">文档资料</a><a class="on"  href="monthReport?projectId=${projectId }">项目月报</a><a href="yearPlan?projectId=${projectId }">年度计划</a></div>
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
					 <td class="tit w180"><em></em>年计划投资（万元）</td><td width="160">暂无数据</td> 						 	 
				   </c:otherwise>
				</c:choose>
                  <td class="tit w180"><em></em>总投资规模</td><td id="investTotal">${project.investTotal }</td>
              </tr>
              <tr>
                  <td class="tit w180"><em>*</em>本月完成投资（万元）</td><td><input type="text" class="text" id="investedNum" value="${report.investedNum }"/></td>
                 
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
          <h4 class="title mt20">投资情况 <a href="javascript:;" class="btn op-add addMonthInvest" title="添加资金来源情况">添加</a></h4>
          <table class="column_tab_table mt20">
              <thead>
              <tr>
                  <td>序号</td>
                  <td>到位资金类型</td>
                  <td>主要来源</td>
                  <td>到位资金金额（万元）</td>
                  <td>操作</td>
              </tr>
              </thead>
              <tbody>
              
               <tr ng-repeat="item in dataList">
			      	 <td>{{$index+1}}</td>
			      	 <td>{{item.comeTypeName}}</td>
			         <td>{{item.comeFrom}}</td>
			         <td>{{item.amount.toFixed(2)}}</td>
			         <td><a data-value="{{item.investId}}" ng-click="deleteInvest(item.investId)" class="op op-del"></a></td>
		       </tr>
		       
              </tbody>
          </table>
          <table class="column_tab_table mt20">
              <thead>
              <tr>
                  <td>序号</td>
                  <td>资金用途类型</td>
                  <td width="335">资金用途金额（万元）</td>
              </tr>
              </thead>
              <tbody>
              <tr>
                  <td>1</td>
                  <td>咨询勘察设计</td>
                  <td><input type="text" class="text w315" id="useDesign" value="${report.useDesign }"/></td>
              </tr>
              <tr>
                  <td>2</td>
                  <td>监理</td>
                  <td><input type="text" class="text w315" id="useOversee" value="${report.useOversee }"/></td>
              </tr>
              <tr>
                  <td>3</td>
                  <td>工程投资</td>
                  <td><input type="text" class="text w315" id="useEngineer" value="${report.useEngineer}"/></td>
              </tr>
              <tr>
                  <td>4</td>
                  <td>设备材料采购</td>
                  <td><input type="text" class="text w315" id="useBuy" value="${report.useBuy }"/></td>
              </tr>
              <tr>
                  <td>5</td>
                  <td>征地拆迁</td>
                  <td><input type="text" class="text w315" id="useAsset" value="${report.useAsset }"/></td>
              </tr>
              <tr>
                  <td></td>
                  <td>总计</td>
                  <td><input type="text" class="text w315" id="useTotal" value="${report.useTotal }"/></td>
              </tr>
              </tbody>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em>*</em>当前工程建设形象进度</td>
                  <td colspan="3">
                      <textarea name="" id="current" cols="30" rows="10">${report.current}</textarea>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>征地拆迁情况</td>
                  <td colspan="3">
                      <textarea name="" id="asset" cols="30" rows="10">${report.asset }</textarea>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>存在困难及问题</td>
                  <td colspan="3">
                      <textarea name="" id="difficult" cols="30" rows="10">${report.difficult }</textarea>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>问题涉及单位</td>
                  <td colspan="3">
                      <textarea name="" id="difficultOrg" cols="30" rows="10">${report.difficultOrg}</textarea>
                  </td>
              </tr>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit w160" style="vertical-align: middle;"><em></em>备注及建议</td>
                  <td colspan="3">
                      <textarea name="" id="comment" cols="30" rows="10">${report.comment }</textarea>
                  </td>
              </tr>
          </table>
          <div style="margin: 20px 0;text-align: center;"><a ng-click="submitReport(${report.reportId })" class="btn">提交审批</a></div>
      </div>
      
      
      
  </div>
  <!--content-end-->
 </div>  
 <input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
 <div id="addMonthInvest"  style="display:none">
	 <div class="pop-content" style="padding: 30px 0;">
	    <ul class="form clearfix">
	        <li class="clearfix"><label class="tit"><em>*</em>资金来源类型：</label>
	            <span class="select comeType" style="width: 302px;">
	                <select>
	                    <option value="-1">--请选择--</option>
	                    <option value="1">中央资金</option>
	                    <option value="2">省级资金</option>
	                    <option value="3">银行贷款</option>
	                    <option value="4">利用外资</option>
	                    <option value="5">市州县自筹</option>
	                    <option value="6">企业自筹</option>
	                    <option value="7">国家预算内资金</option>
	                    <option value="8">国债专项资金</option>
	                    <option value="9">部专项资金</option>
	                    <option value="10">省预算内资金</option>
	                    <option value="11">省专项建设资金</option>
	                    <option value="12">地方配套</option>
	                    <option value="13">其他</option>
	                </select>
	            </span>
	        </li>
	        <li class="clearfix"><label class="tit"><em>*</em>资金来源主体：</label><input type="text" name="comeFrom" class="text w300" value=""/></li>
	        <li class="clearfix"><label class="tit"><em>*</em>投资金额（万元）：</label><input type="text" name="amount" class="text w300" value=""/></li>
	        <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn submitAddMonthInvest">提交</a><a href="javascript:;" onclick="MU.close(this)" class="btn btn-grey">取消</a></li>
	    </ul>
	</div>
</div>
 
  
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/pmProject/monthReport.js?v=${buildTimestamp}"></script>
<script type="text/javascript" >
$('#investTotal').text(parseFloat($('#investTotal').text()).toFixed(2));
$('#yearInvestedTotal').text(parseFloat($('#yearInvestedTotal').text()).toFixed(2));
if($('#amount').text() != '暂无数据'){
	$('#amount').text(parseFloat($('#amount').text()).toFixed(2));
	if($('#amount').text() != '0.00'){
		var yearInvestedTotal = parseFloat($('#yearInvestedTotal').text());
		var amount = parseFloat($('#amount').text());
		var percentNum = (yearInvestedTotal*100)/amount;
		$('#yearInvestedPercent').text(percentNum.toFixed(2) + '%');	
	}
}
</script>
</body>
</html>