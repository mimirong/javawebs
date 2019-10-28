<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>调查征集</title>
<script>
	currentMenu = "interactive";
	currentSystem = "IT";
</script>
</head>
<body>

	<div class="content">
		<h2 class="title">调查征集配置-${title} <a href="javascript:history.go(-1)" class="return-btn">返回 >></a></h2>
        <div class="column_tab_con">
            <div class="search-box clearfix">
                <a href="javascript:;" class="btn op-btn right_float add-topic" title="新增题目">新增题目</a>
            </div>
            <table id="data"class="column_tab_table">
                <thead>
                <tr>
                    <td style="width:80px">序号</td>
                    <td style="width:60px">类型</td>
                    <td>题目内容</td>
                    <td style="width:80px">是否必填</td>
                    <td style="width:160px">操作</td>
                </tr>
                </thead>
            </table>
        </div>
		<input type="hidden" class="queryItem" id="surveyId" name="surveyId" value="${survey.surveyId}" />
	</div>
	<script type="text/javascript" src="${contextPath}/static/js/spark/SurveyQuestion_List.js?v=${buildTimestamp}"></script>
	
	<div class="alert" style="display: none; width: 800px;height:500px;" id="addPanel"
			name="wh_alert">
		<div class="title">
			<a href="javascript:;" onclick="MU.hide(this)"></a><span
				class="addPanelTitle"></span>
		</div>
		<div class="pop-content" style="height:350px;overflow-y:auto">
			<ul class="form clearfix">
	            <li class="clearfix">
	            	<label class="tit w104">题目类型：</label>
	                <span class="select" style="width: 402px;">
	                	<select id="typeSelect">
	                		<option value="0">单选</option>
	                		<option value="2">多选</option>
	                		<option value="1">文本输入</option>
                		</select>
	                </span>
	            </li>
	            <li class="clearfix">
	            	<label class="tit w104"><em>*</em>题目：</label>
	            	<textarea id="questionText" cols="30" rows="10" style="width: 384px;"maxlength="200"></textarea>
            	</li>
	            <li class="clearfix">
	            	<label class="tit w104">是否必填：</label>
	            	<label class="checkbox checked"></label>
            	</li>
	            <li id="optionLi" class="clearfix">
	            	<label class="tit w104">选项：</label>
	                <div class="item-list">
	                    <ul id="chooseUl" class="clearfix">
	                        <li id="btLi"><input id ="chooseText" type="text" class="text w300" value=""/>
	                        <a href="javascript:;" class="btn add-choose">新增选项</a></li>
	                    </ul>
	                </div>
	            </li>
	       </ul>
           <div style="text-align: center;padding:10px 0;"><a href="javascript:;" class="btn add-question"></a></div>
   	   </div>
    </div>
</div>	

</body>