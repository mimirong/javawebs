<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>园区费用</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/spark/left_menu.jsp">
		<jsp:param name="currentMenu" value="fee" />
	</jsp:include>
	
    <div class="column_list ng-cloak" ng-app="billsModule" ng-controller="billsController">
      <h1 class="column_tit">您当前位置：<span>政务服务</span> > <a href="${contextPath}/fees/bills">园区费用</a></h1>
       <div class="con_tab">
            <ul class="con_tab_ul clearfix">
                <li class="active">账单</li>
                <li class="" onclick="location.href = '${contextPath}/fees/standard'">收费标准 </li>
            </ul>
            <div class="con_tab_box clearfix" style="display: block;">
                <div class="column_tab_con">
                    <table class="column_tab_table">
                        <thead>
	                        <tr>
	                            <td>收费项目</td>
	                            <td>月份</td>
	                            <td>应缴费用</td>
	                            <td style="width: 130px;">
	                            <span class="select fl" id="queryStatusSelect">
	                                <select style="display: none;">
	                                    <option value="" selected="selected">全部</option>
	                                    <option value="CREATED">待缴账单</option>
	                                    <option value="FINISHED">已缴账单</option>
	                                </select><div class="select-input" title="入园申请">待缴账单</div><ul class="select-list" style="display: none; z-index: 1; position: absolute; width: 138px; top: 34px;"><li data-value="undefined" title="全部" class="">全部</li><li data-value="undefined" title="待缴账单" class="cur">待缴账单</li><li data-value="undefined" title="已缴账单" class="">已缴账单</li></ul>
	                            </span>
	                            </td>
	                            <td>账单生成日期</td>
	                            <td>操作</td>
	                        </tr>
                        </thead>
                        <tbody ng-repeat="item in dataList">
	                        <tr>
	                            <td>{{item.billType | billTypeText}}</td>
	                            <td>{{item.month}}</td>
	                            <td>{{item.amount / 100}}</td>
	                            <td class="{{item.status == 'CREATED' ? 'red-font' : 'grey-font'}}">{{item.status | statusText}}</td>
	                            <td>{{item.createTime | date:'yyyy-MM-dd'}}</td>
	                            <td><a class="op-utilitybill" href="javascript:;" ng-click="showDetails(item)">详情</a></td>
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
            </div>
        </div>
        
        
	    <%-- 详情 --%>
	    <div class="alert" style="display:none; width:650px;" id="detailsPanel" name="wh_alert">
			<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span>园区费用详情</span></div>
	        <ul class="form detail-form clearfix">
				<li class="clearfix"><label class="tit w140">收费项目：</label><span>{{detailsBill.billType | billTypeText}}</span></li>
			    <li><label class="tit w140">月份：</label><span>{{detailsBill.month}}</span></li>
			    <li class="clearfix"><label class="tit w140">应缴费用：</label><span class="red-font bold">{{detailsBill.amount / 100}}元</span></li>
			    <li class="clearfix"><label class="tit w140">账单生成日期：</label><span>{{detailsBill.createTime | date:'yyyy-MM-dd'}}</span></li>
			    <li class="clearfix"><label class="tit w140">截止缴费日期：</label><span>{{detailsBill.expireTime | date:'yyyy-MM-dd'}}</span></li>
			    <li class="clearfix">
			    	<label class="tit w140">状态：</label>
			    	<span class="red-font" ng-if="detailsBill.status == 'CREATED'">待缴费</span>
			    	<span class="blue-font" ng-if="detailsBill.status == 'FINISHED'">已缴费</span>
		    	</li>
			    <li class="clearfix"><label class="tit w140">缴费日期：</label><span>{{detailsBill.finishTime | date:'yyyy-MM-dd'}}</span></li>
			</ul>
		</div>

    </div>
    
    

	<script type="text/javascript" src="${contextPath}/static/js/angular.min.js"></script>
 	<script type="text/javascript" src="${contextPath}/static/js/bills.js"></script>

</body>
</html>