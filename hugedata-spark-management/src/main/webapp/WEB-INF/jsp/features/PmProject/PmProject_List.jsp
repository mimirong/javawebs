<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m"
	uri="http://spark.hugedata.com.cn/tags/management-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>项目列表</title>
<script>
	currentMenu = "pm_project";
	currentSystem = "PM";
</script>
<%-- <script type="text/javascript" src="${contextPath}/static/js/spark-commons.js?v=${buildTimestamp}"></script> --%>
<script>
	
</script>
</head>
<body>

      <h2 class="title">项目管理&nbsp;&nbsp; <a href="${contextPath}/features/PmDocType/list" style="color: #107aee">配置</a> <a href="toAdd" class="btn fr">添加项目</a></h2>
      <div class="column_tab_con"  ng-app="projectModule" ng-controller="projectController" class="ng-cloak">
          <div class="filter-box">
              <input type="text" id="projectName" class="txt queryItem"  name="like_projectName" placeholder="输入项目名称关键词"><a href="javascript:;" id="btnSearch" class="btn">查询</a> <a href="javascript:;"  class="btn advanceSearch">按条件筛选</a><a href="javascript:;"  class="btn exportExcel">导出到EXCEL</a> 
          </div>
          <table class="column_tab_table" id="data" >
              <thead>
              <tr>
              <td width="30" style="border-top-left-radius: 5px;">
                <label class="checkbox btnSelectAll"></label>
            </th>
                  <td>序号</td>
                  <td>项目名称</td>
                  <td>项目情况</td>
                  <td>创建时间</td>
                  <td>操作</td>
              </tr>
               <tbody  ng-if="dataList.length == 0 ">
              <tr class="odd"><td valign="top" colspan="6" class="dataTables_empty">没有数据</td></tr>
              </tbody>
              <tbody ng-if="dataList.length > 0 ">
              <tr ng-repeat="item in dataList">
              		 <td><label class="checkbox btnSelect " data-row-id="{{item.projectId}}"></label></td>
			      	 <td>{{(page-1)*10+$index+1}}</td>
			      	 <td ng-if="item.frontUpdated != '1'"><a class="blue-font"  href="detail?serviceId={{item.projectId}}">{{item.projectName}}</a></td>
			      	 <td ng-if="item.frontUpdated == '1'"><span style="background: #ff0000;display: inline-block;width: 8px;height: 8px;border-radius: 10px;"></span><a class="blue-font"  href="detail?serviceId={{item.projectId}}">{{item.projectName}}</a></td>
			      	 <td class="green-font" ng-if="item.projectStatus == '1'">正常</td>
		             <td class="red-font" ng-if="item.projectStatus != '1'">异常</td> 
	                 <td>{{item.createTime | date:'yyyy-MM-dd HH:mm'}}</td>
		             <td><a class="blue-font"  href="javascript:;" ng-click="deleteProject(item.projectId)">删除</a></td>
		       </tr>
              </tbody>
          </table>
          
            <div class="page_v1" style="text-align: center;">
					<span class="total">共<em>{{pageTotal}}</em>页</span>
					<span class="ell" ng-if="pageButtons[0] != 1">...</span>
					<ng-repeat ng-repeat="p in pageButtons"><a target="_self" href="javascript:;" ng-if="p!=page" ng-click="gotoPage(p)">{{p}}</a><span class="cur" ng-if="p==page">{{p}}</span></ng-repeat>
					<span class="ell" ng-if="pageButtons[pageButtons.length - 1] != pageTotal">...</span>
					<a target="_self" href="javascript:;" ng-click="gotoPage(hasNextPage ? page+1 : page)">下一页</a><span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span><a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
		 	</div>
          
      </div>
      
 <div id="advanceSearch"  style="display:none">     
	  <div class="pop-content">
	    <ul class="form clearfix" style="height:182px">
	        <li class="clearfix"><label class="tit w112">所属领域：</label>
	            <span class="select projectArea" style="width:425px;">
	                <select>
	                    <option value="-1">--全部--</option>
	                    <c:forEach var="m" items="${projectAreas}">
						 <option value="${m}">${m}</option>
						</c:forEach>
	                </select>
	            </span>
	        </li>
	        
	        
	        
	        
	        <li class="clearfix">
	        <label class="tit w112">起止年限：</label>
		        <div class="clearfix">
		         <span class="select startYear  " style="width:200px;" >
	                    <select>
	                        <option>2010</option><option>2011</option><option>2012</option><option>2013</option><option>2014</option><option>2015</option><option>2016</option><option selected="selected">2017</option><option>2018</option><option>2019</option>
	                        <option>2020</option><option>2021</option><option>2022</option><option>2023</option><option>2024</option><option>2025</option><option>2026</option><option>2027</option><option>2028</option><option>2029</option>
	                    </select>
	                </span>&nbsp;至&nbsp;
	                <span class="select endYear" style="width:200px;">
	                    <select>
	                        <option>2010</option><option>2011</option><option>2012</option><option>2013</option><option>2014</option><option>2015</option><option>2016</option><option>2017</option><option>2018</option><option>2019</option>
	                        <option selected="selected">2020</option><option>2021</option><option>2022</option><option>2023</option><option>2024</option><option>2025</option><option>2026</option><option>2027</option><option>2028</option><option>2029</option>
	                    </select>
	                </span>
	               </div>  
	        </li>
	        <li class="clearfix yearAmount">
	            <label class="tit w112">年度计划投资额：</label>
	            <div class="clearfix">
	             <span class="fl">大于</span>   <input type="text" value="" class="txt  left_float w98 gtYearAmount"><span class="fl" style="margin-right: 30px;">万元</span>
	             <span class="fl">小于</span>   <input type="text" value="" class="txt left_float w98 ltYearAmount"><span class="fl">万元</span>
	            </div>
	        </li>
	        <li class="clearfix totalAmount">
	            <label class="tit w112">总投资额：</label>
	            <div class="clearfix">
	                <span class="fl">大于</span> <input type="text" value="" class="txt  left_float w98 gtTotalAmount"><span class="fl" style="margin-right: 30px;">万元</span>
	                <span class="fl">小于</span><input type="text" value="" class="txt left_float w98 ltTotalAmount"><span class="fl">万元</span>
	            </div>
	        </li>
	    </ul>
	    <div style="text-align: center;padding:10px 0;"><a href="javascript:;" class="btn submitSearch">提交查询</a><a href="javascript:;" class="btn btn-grey" onclick="MU.close(this)">关闭</a></div>
	</div>
  </div>



	<script type="text/javascript">
	$('div.leftsidebar').remove();
	$('div.column_list').removeClass('column_list');
	$('div.column_con').attr('style','padding: 0 20px 35px;') ;
	</script>
	<script type="text/javascript" src="${contextPath}/static/js/spark/PmProject_List.js"></script>
	
</body>