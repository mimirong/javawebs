<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>
<title>基本信息</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>

      <h2 class="title">项目信息<a href="javascript:;" 	onclick="window.history.go(-1);" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?serviceId=${project.projectId}" class="on">基本信息</a><a href="doc1?projectId=${ project.projectId}">手续文件</a><a href="doc2?projectId=${ project.projectId}">文档资料</a><a href="monthReport?projectId=${ project.projectId}">项目月报</a><a href="yearPlan?projectId=${ project.projectId}">年度计划</a></div>
      <div class="tab-content">
          <h3 class="title">基本信息 <a href="toModify?id=${project.projectId }" class="btn">更新信息</a></h3>
          <h4 class="title">${project.projectName }</h4>
          <table class="table">
              <tr>
                  <td class="tit"><em>*</em>项目名称</td><td colspan="3">${project.projectName }</td>
              </tr>
              <tr>
                  <td class="tit"><em></em>项目代码</td><td colspan="3">${project.projectCode }</td>
              </tr>
              <tr>
                  <td class="tit"><em></em>建设地点</td><td colspan="3">${project.projectAddr }</td>
              </tr>
              <tr>
                  <td class="tit"><em></em>所属领域</td><td colspan="3">${project.projectArea }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>建设性质</td>
                  <td colspan="3">
                      <p class="des">
                      ${project.projectType }
                      </p>
                  </td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>建设规模和内容</td>
                  <td colspan="3">
                      <p class="des">
                          ${project.projectContent }
                      </p>
                  </td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>实施主体</td><td colspan="3"> ${project.mainActor }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>责任单位</td><td colspan="3"> ${project.dutyOrg }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>责任人</td><td colspan="3"> ${project.dutyMan }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>联系领导</td><td colspan="3"> ${project.contactLead }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>统计入库项目名称</td><td colspan="3"> ${project.repoName }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>起止年限</td><td colspan="3"> ${project.beginEnd }</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>总投资规模（万元）</td><td colspan="3" id="investTotal"> ${project.investTotal}</td>
              </tr>
               <tr>
                  <td class="tit">企业对接人</td><td colspan="3" > ${project.infCompanyName} &nbsp;&nbsp; ${project.infUserName}</td>
              </tr>
              <tr>
                  <td class="tit"><em>*</em>实际完成投资（万元）<br/><i>截止上月底</i></td><td colspan="3" id="investedTotal">${investedTotal }</td>
              </tr>
          </table>
          <h4 class="title mt20">投资完成情况</h4>
          <table class="column_tab_table">
              <thead>
              <tr>
                  <td width="88">序号</td>
                  <td>到位资金类型</td>
                  <td>投资主体</td>
                  <td>到位资金金额（万元）</td>
                  <td>到位时间</td>
              </tr>
              </thead>
              <tbody>
              	 <c:choose>
					   <c:when test="${monthInvests != null &&   monthInvests.size() > 0 }">  
					      <c:forEach var="m" items="${monthInvests}"  varStatus="status">
								  <tr>
					                  <td>${status.index + 1 }</td>
					                  <td>${m.comeTypeName }</td>
					                  <td>${m.comeFrom }</td>
					                  <td name="amount">${m.amount}</td>
					                  <td>${m.investMonth }</td>
					              </tr>
						  </c:forEach>
					   </c:when>
					   <c:otherwise> 
					 	 
					   </c:otherwise>
				 </c:choose>
              </tbody>
          </table>
          <table class="table mt20" style="height: 96px;">
              <tr>
                  <td class="tit" style="vertical-align: middle;"><em></em>初审意见</td><td colspan="3">${project.accountComment}</td>
              </tr>
          </table>
      </div>
   <script type="text/javascript" >
    $('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
  </script>

<script type="text/javascript" >
$('#investTotal').text(parseFloat($('#investTotal').text()).toFixed(2));
$('#investedTotal').text(parseFloat($('#investedTotal').text()).toFixed(2));
$("td[name='amount']").each(function(){
	$(this).text(parseFloat($(this).text()).toFixed(2));
  });

</script>
	

</body>