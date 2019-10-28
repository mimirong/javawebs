<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加项目</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>

      <h2 class="title">项目管理<a href="list" class="return-btn">返回 >></a></h2>
      <div class="tab-content">
          <h3 class="title">添加项目</h3>
          <ul class="form clearfix">
              <li class="clearfix"><label class="tit"><em>*</em>项目名称：</label><input type="text" id="projectName" class="text" value=""/></li>
              <li class="clearfix"><label class="tit">项目代码：</label><input id="projectCode"  type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit">建设地点：</label><input id="projectAddr"  type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit">所属领域：</label><input id="projectArea" type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>建设性质：</label><textarea id="projectType"  name="" cols="30" rows="10"></textarea></li>
              <li class="clearfix"><label class="tit"><em>*</em>建设规模和内容：</label><textarea id="projectContent"  name="" cols="30" rows="10"></textarea></li>
              <li class="clearfix"><label class="tit"><em>*</em>实施主体：</label><input id="mainActor"  type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>责任单位：</label><input id="dutyOrg"  type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>责任人：</label><input id="dutyMan" type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>联系领导：</label><input id="contactLead" type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>统计入库项目名称：</label><input id="repoName"  type="text" class="text" value=""/></li>
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
              <li class="clearfix"><label class="tit"><em>*</em>总投资规模（万元）：</label><input id="investTotal"  type="text" class="text" value=""/></li>
              <li class="clearfix"><label class="tit"><em>*</em>企业对接人：</label>
                  <span class="select mr10 companies" style="width: 194px;"><select><option value="-1">选择企业</option></select></span>
                  <span class="select infUsers" style="width: 194px;"><select><option value="-1">选择对接人</option></select></span>
              </li>
              <li class="clearfix"><label class="tit">&nbsp;</label><a href="javascript:;" class="btn mr10 doAdd">提交</a><a href="javascript:history.go(-1);" class="btn btn-grey">取消</a></li>
          </ul>
      </div>
  
   <script type="text/javascript" >
  $('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
  </script>

	<script type="text/javascript" src="${contextPath}/static/js/spark/PmProject_Add.js"></script>

</body>