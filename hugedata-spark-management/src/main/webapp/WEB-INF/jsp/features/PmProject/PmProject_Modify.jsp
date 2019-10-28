<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>更新信息</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>


      <h2 class="title">项目信息<a href="detail?serviceId=${projectId}" class="return-btn">返回 >></a></h2>
      <div class="tab w434"><a href="detail?serviceId=${projectId}" class="on">基本信息</a><a href="doc1?projectId=${projectId}">手续文件</a><a href="doc2?projectId=${ projectId}">文档资料</a><a href="monthReport?projectId=${ projectId}">项目月报</a><a href="yearPlan?projectId=${ projectId}">年度计划</a></div>
      <div class="tab-content">
          <h3 class="title">${projectName } <a href="javascript:history.go(-1);" class="return-btn">返回 >></a></h3>
          <ul class="form clearfix">
          	  <input id="projectId" type="hidden"  value="${projectId }"/>
              <li class="clearfix"><label class="tit"><em>*</em>项目名称：</label><input id="projectName" type="text" class="text" value="${projectName }"/></li>
              <li class="clearfix"><label class="tit">项目代码：</label><input id="projectCode" type="text" class="text" value="${projectCode }"/></li>
              <li class="clearfix"><label class="tit">建设地点：</label><input id="projectAddr" type="text" class="text" value="${projectAddr }"/></li>
              <li class="clearfix"><label class="tit">所属领域：</label><input id="projectArea" type="text" class="text" value="${projectArea }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>建设性质：</label>
                    <textarea name="" cols="30" rows="10" id="projectType">${projectType }</textarea>
              </li>
              <li class="clearfix"><label class="tit"><em>*</em>建设规模和内容：</label>
                    <textarea name="" cols="30" rows="10" id="projectContent">${projectContent}</textarea>
              </li>
              <li class="clearfix"><label class="tit"><em>*</em>实施主体：</label><input id="mainActor"  type="text" class="text" value="${mainActor }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>责任单位：</label><input id="dutyOrg"  type="text" class="text" value="${dutyOrg }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>责任人：</label><input id="dutyMan" type="text" class="text" value="${dutyMan }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>联系领导：</label><input id="contactLead" type="text" class="text" value="${contactLead }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>统计入库项目名称：</label><input id="repoName"  type="text" class="text" value="${repoName }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>起止年限：</label>
                 <span class="select mr5 startYear" style="width:90px;">
                    <select>
                        <option>2010</option><option>2011</option><option>2012</option><option>2013</option><option>2014</option><option>2015</option><option>2016</option><option selected="selected">2017</option><option>2018</option><option>2019</option>
                        <option>2020</option><option>2021</option><option>2022</option><option>2023</option><option>2024</option><option>2025</option><option>2026</option><option>2027</option><option>2028</option><option>2029</option>
                    </select>
                </span>&nbsp;&nbsp;至&nbsp;&nbsp;
                <span class="select mr5 endYear" style="width:90px;">
                    <select>
                        <option>2010</option><option>2011</option><option>2012</option><option>2013</option><option>2014</option><option>2015</option><option>2016</option><option>2017</option><option>2018</option><option>2019</option>
                        <option selected="selected">2020</option><option>2021</option><option>2022</option><option>2023</option><option>2024</option><option>2025</option><option>2026</option><option>2027</option><option>2028</option><option>2029</option>
                    </select>
                </span>
              </li>
              <li class="clearfix"><label class="tit"><em>*</em>总投资规模（万元）：</label><input id="investTotal"  type="text" class="text" value="${investTotal }"/></li>
              <li class="clearfix"><label class="tit"><em>*</em>企业对接人：</label>
                  <span class="select mr10 companies" style="width: 194px;"><select><option value="-1">选择企业</option></select></span>
                  <span class="select infUsers" style="width: 194px;"><select><option value="-1">选择对接人</option></select></span>
              </li>
              <li class="clearfix"><label class="tit">初审意见：</label>
                    <textarea name="" cols="30" rows="5" id="accountComment">${accountComment}</textarea>
              </li>
               <li class="clearfix"><label class="tit"><em>*</em>项目情况：</label>
                  <span class="select mr10 projectStatus" style="width: 194px;"><select><option value="1">正常</option><option value="0">异常</option></select></span>
              </li>
              <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn mr10 modifyProject">提交</a><a href="javascript:history.go(-1);" class="btn btn-grey">取消</a></li>
          </ul>
      </div>
  <!--content-end-->
<script type="text/javascript">
$('div.leftsidebar').remove();
$('div.column_list').removeClass('column_list');
$('div.column_con').attr('style','padding: 0 20px 35px;') ;
$('#investTotal').val(parseFloat($('#investTotal').val()).toFixed(2));
// 初始化年份 企业对接人  项目情况
var beginEnd = '${beginEnd}';
var begin = beginEnd.substring(0,4);
var end =  beginEnd.substring(5,9);
console.log(end);
var beginHtml = '';
for(var i=0;i < 30 ; i++){
	var year = 2010+i;
	if(year == begin){
		beginHtml = beginHtml + '<option selected="selected">'+year+'</option>';
	}else{
		beginHtml = beginHtml + '<option>'+year+'</option>';		
	}
}
$('span.startYear').children('select').html(beginHtml).selectList();

var endHtml = '';
for(var i=0;i < 30 ; i++){
	var year = 2010+i;
	if(year == end){
		endHtml = endHtml + '<option selected="selected">'+year+'</option>';
	}else{
		endHtml = endHtml + '<option>'+year+'</option>';		
	}
}
$('span.endYear').children('select').html(endHtml).selectList();

var infCompanyId = ${infCompanyId};
var infUserId = ${infUserId};

//获取企业列表
$.ajax({
        url: "getCompanies",
        type: "post",
		dataType: "json",
		data:  {},
		success: function(resp) {
		if (resp && resp.result == 0) {
			 if(resp.data){
		        	var ol = '';
		            for(var i=0;i < resp.data.length;i++ ){
		            	if(infCompanyId == resp.data[i].companyId){
		            		ol = ol + '<option selected="selected" value='+resp.data[i].companyId+'>'+resp.data[i].companyName+'</option>';
		            		$.ajax({
		            	        url: "getInfUsers",
		            	        type: "post",
		            			dataType: "json",
		            			data:  {companyId:infCompanyId},
		            			success: function(resp) {
		            			if (resp && resp.result == 0) {
		            				 if(resp.data){
		            			        	var ol = '<option value="-1">选择对接人</option>';
		            			            for(var i=0;i < resp.data.length;i++ ){
		            			            	if(resp.data[i].userId == infUserId){
		            			            		ol = ol + '<option selected="selected" value='+resp.data[i].userId+'>'+resp.data[i].name+'</option>';		            		
		            			            	}else{
		            			            		ol = ol + '<option value='+resp.data[i].userId+'>'+resp.data[i].name+'</option>';	
		            			            	}
		            			            }
		            			            $('span.infUsers').children('select').html(ol);
		            			            $('span.infUsers').children('select').selectList();
		            			        }
		            				
		            			} else {
		            				MU.msgTips("error", resp.message, 1200);
		            			}
		            		},
		            		error: function() {
		            			MU.msgTips("error", "获取企业用户数据失败，请稍后重试！", 1200);
		            		}
		            	});
		            	}else{
		            		ol = ol + '<option value='+resp.data[i].companyId+'>'+resp.data[i].companyName+'</option>';
		            	}
		            }
		            $('span.companies').children('select').append(ol);
		            $('span.companies').children('select').selectList();
		            
		            // 填加企业下拉员工显示事件
		            
		        }
			
		} else {
			MU.msgTips("error", resp.message, 1200);
		}
	},
	error: function() {
		MU.msgTips("error", "增加失败，请稍后重试", 1200);
	}
});

$("span.companies ").delegate("ul  li","click", function() {
	var companyId = $(this).attr('data-value');
	if(companyId != '-1'){
		$.ajax({
	        url: "getInfUsers",
	        type: "post",
			dataType: "json",
			data:  {companyId:companyId},
			success: function(resp) {
			if (resp && resp.result == 0) {
				 if(resp.data){
			        	var ol = '<option value="-1">选择对接人</option>';
			            for(var i=0;i < resp.data.length;i++ ){
			            	ol = ol + '<option value='+resp.data[i].userId+'>'+resp.data[i].name+'</option>';
			            }
			            $('span.infUsers').children('select').html(ol);
			            $('span.infUsers').children('select').selectList();
			        }
				
			} else {
				MU.msgTips("error", resp.message, 1200);
			}
		},
		error: function() {
			MU.msgTips("error", "获取企业用户数据失败，请稍后重试！", 1200);
		}
	});
	}else{
		var ol = '<option value="-1">选择对接人</option>';
        $('span.infUsers').children('select').html(ol);
        $('span.infUsers').children('select').selectList();
	}
});

 

 

var projectStatus = '${projectStatus}';
var projectStatusHtml = '';
 
	if(projectStatus == '0'){
		projectStatusHtml =  '<option value="1">正常</option><option selected="selected" value="0">异常</option>' ;
	}else{
		projectStatusHtml =  '<option value="1" selected="selected">正常</option><option value="0">异常</option>' ;		
	}
 
$('span.projectStatus').children('select').html(projectStatusHtml).selectList();



$('a.modifyProject').on('click',function(){
	
	if($.trim($('#projectName').val()).length == 0){
		MU.msgTips("error", "请填写项目名称", 1200);
		return false;
	}


	
	if($.trim($('#projectType').val()).length == 0){
		MU.msgTips("error", "请填写建设性质", 1200);
		return false;
	}
	
	if($.trim($('#projectContent').val()).length == 0){
		MU.msgTips("error", "请填写建设规模和内容", 1200);
		return false;
	}
	
	if($.trim($('#accountComment').val()).length > 200){
		MU.msgTips("error", "初审意见字符长度不能大于200", 1200);
		return false;
	}
	
	if($.trim($('#mainActor').val()).length == 0){
		MU.msgTips("error", "请填写实施主体", 1200);
		return false;
	}
	if($.trim($('#dutyOrg').val()).length == 0){
		MU.msgTips("error", "请填写责任单位", 1200);
		return false;
	}
	if($.trim($('#dutyMan').val()).length == 0){
		MU.msgTips("error", "请填写责任人", 1200);
		return false;
	}
	if($.trim($('#contactLead').val()).length == 0){
		MU.msgTips("error", "请填写联系领导", 1200);
		return false;
	}
	if($.trim($('#repoName').val()).length == 0){
		MU.msgTips("error", "请填写入库项目名称", 1200);
		return false;
	}
	
	if($.trim($('#investTotal').val()).length == 0){
		MU.msgTips("error", "请填写投资规模", 1200);
		return false;
	}
	
	var doubleValide = /^\d+(\.\d+)?$/;
	if(!doubleValide.test($.trim($('#investTotal').val()))){
  		 MU.msgTips("error", '请填写正确的投资规模数字!');
  		 return false;
	}
	
	var investTotal = parseFloat($('#investTotal').val());
	investTotal.toFixed(2);
	
	if($("span.companies li.cur").attr('data-value') == '-1' ||  $("span.infUsers li.cur").attr('data-value') == '-1'){
		MU.msgTips("error", "请填写企业对接人", 1200);
		return false;
	}
	
	
	
	$.ajax({
    	url: "modifyProject",
    	type: "post",
    	dataType: "json",
    	data: {projectId:$('#projectId').val(),projectName:$.trim($('#projectName').val()),
    		projectCode:$.trim($('#projectCode').val()),projectAddr:$.trim($('#projectAddr').val()),
    		projectArea:$.trim($('#projectArea').val()),projectType:$.trim($('#projectType').val()),
    		projectContent:$.trim($('#projectContent').val()),projectStatus:$("span.projectStatus li.cur").attr('data-value'),
    		accountComment:$.trim($('#accountComment').val()),
    		mainActor:$('#mainActor').val(),
				dutyOrg:$('#dutyOrg').val(),
				dutyMan:$('#dutyMan').val(),
				contactLead:$('#contactLead').val(),
				repoName:$('#repoName').val(),
				beginEnd:$('span.startYear li.cur').html() + '~' + $('span.endYear li.cur').html() ,
				investTotal:investTotal,
				infCompanyId:$("span.companies li.cur").attr('data-value'),
				infCompanyName:$("span.companies li.cur").attr('title'),
				infUserId:$("span.infUsers li.cur").attr('data-value'),
				infUserName:$("span.infUsers li.cur").attr('title')
    	
    	},
    	success: function(resp) {
    		if (resp && resp.result == 0) {
    			MU.msgTips("success", '修改成功!');
    			window.location.href = contextPath + '/features/PmProject/list';
    		} else {
    			MU.msgTips("error", resp.message);
    		}
    	},
    	error: function() {
    		MU.msgTips("error", "获取数据失败，请稍后重试");
    	}
    });
}) 
</script>
</body>