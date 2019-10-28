<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办事事项</title>
<style>
	.ng-cloak { display:none; }
</style>
</head>
<body>
<div class="ng-cloak" ng-app="serviceDetailModule" ng-controller="serviceDetailController">
    <h1 class="column_tit">您当前位置：
    	<a href="${contextPath}/">首页</a> > 
    	<a href="${contextPath}/serviceGuide1/list">办事服务</a> > 
    	<a href="${contextPath}/serviceGuide1/serviceShow">办事公示</a> 
   	</h1>
  <!--content-start-->
  <div class="column_con clearfix" style="padding: 0 20px 35px; width: 960px;">
       <h2 class="title">公示详情<a class="return-back" href="javascript:;" onclick="history.go(-1)">返回 >></a></h2>
     <div class="tab-content">
          <h3 class="title" style="border-bottom: none; margin-bottom: 0; ">
          	<span>{{businessDetail.guidename}}</span>
          	<a ng-if="simpleStatus.inProgress && businessDetail.acceptNoticeFileId && isDetailed" href="{{businessDetail.acceptNoticeUrl}}" class="btn w120">下载受理通知书</a>
          	<a ng-if="simpleStatus.finished && businessDetail.finishDocUrl && isDetailed" ng-href="{{businessDetail.finishDocUrl}}" class="btn w120">下载办结文书</a>
          	<a ng-if="simpleStatus.finished && !businessDetail.finishDocUrl && isDetailed" href="javascript:;" class="btn disabled w120">下载办结文书</a>
          	<a ng-if="(simpleStatus.inProgress || simpleStatus.created) && isDetailed" href="javascript:;" class="btn w120" ng-click="cancelService()">撤销办事</a>
          </h3>
          <table class="table">
              <tr>
                  <td colspan="4" class="tit">办件信息</td>
              </tr>
              <tr>
                  <td class="tit">受理编号</td>
                  <td width="340">{{businessDetail.businessNo}}</td>
                  <td class="tit">提交时间</td>
                  <td>{{businessDetail.createTime | date:"yyyy-MM-dd"}}</td>
              </tr>
              <tr>
                  <td class="tit">申办事项</td>
                  <td colspan="3">{{businessDetail.guidename}}</td>
              </tr>
              <tr>
                  <td class="tit">办理方式</td>
                  <td>{{businessDetail.businessType | businessTypeName}}</td>
                  <td class="tit">办理期限</td>
                  <td>{{businessDetail.timeLimit}}</td>
              </tr>
              <tr>
                  <td colspan="4" class="tit">申办单位（个人）信息</td>
              </tr>
              <tr>
                  <td class="tit">申办单位(人)</td>
                  <td>{{businessDetail.businessDeptPerson}}</td>
                  <td class="tit">联系方式</td>
                  <td>{{businessDetail.cellphone}}</td>
              </tr>
              <tr>
                  <td colspan="4" class="tit">受理信息</td>
              </tr>
              <tr>
                  <td class="tit">受理部门</td>
                  <td>{{simpleStatus.created ? "" : businessDetail.deptname}}</td>
                  <td class="tit">受理人</td>
                  <td>{{businessDetail.acceptUserName}}</td>
              </tr>
              <tr>
                  <td class="tit">受理时间</td>
                  <td>{{businessDetail.acceptTime | date : 'yyyy-MM-dd'}}</td>
                  <td class="tit">联系电话</td>
                  <td>{{businessDetail.acceptCellphone}}</td>
              </tr>
              <tr>
                  <td class="tit">办结时间</td>
                  <td>{{businessDetail.finishTime | date : 'yyyy-MM-dd'}}</td>
                  <td class="tit">当前状态</td>
                  <td>{{businessDetail.status | statusText}}</td>
              </tr>
          </table>
          
         <h3 class="detail-title">申请业务材料</h3>
         <table class="column_tab_table" style="text-align: center;">
             <thead>
             <tr>
             	 <td width="80px" ng-if="isDetailed && !simpleStatus.created">审核结果</td>
                 <td width="80px">附件数</td>
                 <td>材料名称</td>
                 <td width="300px">材料说明</td>
             </tr>
             </thead>
             <tbody>
	             <tr ng-repeat="ac in attachments">
	             	 <td ng-if="isDetailed && !simpleStatus.created">
	             	 	<span ng-if="ac.approveStatus == 'APPROVED'">通过</span>
	             	 	<span class="red" ng-if="ac.approveStatus == 'REJECTED'">不通过</span>
             	 	 </td>
	                 <td>
	                 	{{ac.fileCount}}
	                 	<%-- 
	                 	<span class="blue" style="cursor:pointer;" ng-click="showAttFiles($event, ac)">{{ac.fileCount}}</span>
	                 	--%>
	                 	<%-- BEGIN 文件列表弹框 --%>
	                 	<div class="attachmentPopupContentWrapper" style="display:none">
			                <div class="pop-content" style="padding: 30px 0;">
							    <ul class="list" style="min-height: 76px;">
							        <li ng-repeat="file in ac.files">
							        	<a ng-href="{{file.fileUrl}}">{{file.fileName}}</a>
							        </li>
							    </ul>
							</div>
						</div>
						<%-- END 文件列表弹框 --%>
	                 </td>
	                 <td>{{ac.attConfigName}}</td>
	                 <td>{{ac.remark && ac.remark != "" ? ac.remark : "无"}}</td>
	             </tr>
	         </tbody>
         </table>
          
		<%-- START 办理过程 --%>
		<div ng-repeat="proc in procList" ng-show="isDetailed">
	        <h3 class="detail-title">{{proc.serviceStatus | serviceOpText}}结果
	        	<a ng-if="proc.status == 'APPROVE'" class="status pass">通过</a>
	        	<a ng-if="proc.status == 'DELIVER'" class="status pass">通过</a>
	        	<a ng-if="proc.status == 'REJECT'" class="status nopass">不通过</a>
	        	<a ng-if="proc.status == 'FINISH' && proc.serviceStatus == STATUS_FINISH_CONFIRM" class="status pass">最终确认</a>
	        	<a ng-if="proc.status == 'FINISH' && proc.serviceStatus == STATUS_REJECT _CONFIRM" class="status nopass">最终确认</a>
	        	<a ng-if="proc.status == 'FINISH_APPROVED'" class="status pass">通过</a>
	        	<a ng-if="proc.status == 'FINISH_REJECTED'" class="status nopass">不通过</a>
	        	<a ng-if="proc.status == 'CANCEL'" class="status nopass">撤销</a>
	        	<a class="name">{{proc.userName}} {{proc.createTime | date:'yyyy-MM-dd'}}</a>
        	</h3>
	        <table class="table">
	            <tbody>
	            	<tr>
	                	<td class="tit">反馈意见</td>
	                	<td colspan="3">{{proc.remark}}</td>
	            	</tr>
	            </tbody>
	        </table>
        </div>
        <%-- END 办理过程 --%>
         
         
         <%--
         <table class="table mt20" style="height:96px;" ng-if="isDetailed && (simpleStatus.finished || simpleStatus.rejected)">
             <tbody>
             <tr>
                 <td class="tit" style="vertical-align: middle;"><em></em>反馈意见</td><td colspan="3">{{businessDetail.remark}}</td>
             </tr>
             </tbody>
         </table>
         --%>
         <div class="btn-div" style="margin:20px 0; text-align:center;" ng-if="isDetailed && simpleStatus.rejected">
         	<a href="javascript:;" class="btn" style="margin:0;" ng-click="recreate()">重新提交</a>
         </div>
         
      </div>
  </div>
  <!--content-end-->

</div>

<script>
	var serviceId = ${serviceId}
	window.login = {
		userId: "${SPARK_LOGIN.userId}"	
	};
</script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/serviceGuide/serviceShow_detail.js?v=${buildTimestamp}"></script>

</body>
</html>