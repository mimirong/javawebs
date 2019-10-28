<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目管理</title>
</head>
<body>
    <!--banner-end-->
    <h1 class="column_tit">您当前位置：<a href="index.html">首页</a> > <a href="list">项目管理</a> > <span>项目信息</span></h1>
    <!--content-start-->
    <div class="column_con clearfix" id="minH">
        <h2 class="title">项目信息<a href="detail?projectId=${project.projectId }" class="return-btn">返回 >></a></h2>
       <div class="tab w434"><a href="detail?projectId=${project.projectId }" class="on">基本信息</a><a href="doc1?projectId=${project.projectId }">手续文件</a><a href="doc2?projectId=${project.projectId }">文档资料</a><a href="monthReport?projectId=${project.projectId }">项目月报</a><a href="yearPlan?projectId=${project.projectId }">年度计划</a></div>
        <div class="tab-content">
            <h3 class="title">${project.projectName}<a href="javascript:history.go(-1);" class="return-btn">返回 >></a></h3>
            <ul class="form clearfix">
            	<input type="hidden" id="projectId" value="${project.projectId }"/>
                <li class="clearfix"><label class="tit"><em>*</em>项目名称：</label><input type="text" class="text" id="projectName" value="${project.projectName }"/></li>
                <li class="clearfix"><label class="tit">项目代码：</label><input type="text" class="text" id="projectCode" value="${project.projectCode }"/></li>
                <li class="clearfix"><label class="tit">建设地点：</label><input type="text" class="text" id="projectAddr" value="${project.projectAddr }"/></li>
                <li class="clearfix"><label class="tit">所属领域：</label><input type="text" class="text" id="projectArea" value="${project.projectArea }"/></li>
                <li class="clearfix"><label class="tit"><em>*</em>建设性质：</label>
                    <textarea name="" id="projectType" cols="30" rows="10">${project.projectType }</textarea>
                </li>
                <li class="clearfix"><label class="tit"><em>*</em>建设规模和内容：</label>
                    <textarea name="" id="projectContent" cols="30" rows="10">${project.projectContent }</textarea>
                </li>
                <li class="clearfix"><label class="tit"><em>*</em>实施主体：</label>${project.mainActor }</li>
                <li class="clearfix"><label class="tit"><em>*</em>责任单位：</label>${project.dutyOrg}</li>
                <li class="clearfix"><label class="tit"><em>*</em>责任人：</label>${project.dutyMan }</li>
                <li class="clearfix"><label class="tit"><em>*</em>联系领导：</label>${project.contactLead }</li>
                <li class="clearfix"><label class="tit"><em>*</em>统计入库项目名称：</label>${project.repoName }</li>
                <li class="clearfix"><label class="tit"><em>*</em>起止年限：</label>${project.beginEnd}</li>
                <li class="clearfix" id="investTotal"><label class="tit"><em>*</em>总投资规模（万元）：</label>${project.investTotal}</li>
                <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn modifyProject">提交</a><a href="javascript:history.go(-1);" class="btn btn-grey">取消</a></li>
            </ul>
        </div>
    </div>
    <!--content-end-->
<script src="${contextPath}/static/js/pmProject/modify.js?v=${buildTimestamp}"></script>
<script type="text/javascript" >
$('#investTotal').html('<label class="tit"><em>*</em>总投资规模（万元）：</label>' + parseFloat('${project.investTotal}').toFixed(2));
</script>
</body>
</html>