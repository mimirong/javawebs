<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://spark.hugedata.com.cn/tags/management-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>入园申请</title>
	<script>
		currentMenu = "joinapply";
		currentSystem = "INTEGRATED";
	</script>
</head>
<body>

	<h2 class="title">入园申请</h2>
	<%--
    <div class="tab"><a href="${contextPath}/features/GaParkJoinApplication/list" class="on">申请审批</a><a href="${contextPath}/features/GaParkJoinGuide/list">入园指南</a></div>
   --%>
		
	<div class="column_tab_con">
		<table id="data" class="column_tab_table" width="100%">
			<thead>
				<tr>
                    <td style="width:100px">申请ID</td>
                    <td>企业名称</td>
                    <td style="width:160px">申请日期</td>
                    <td style="width:80px">
                        <div class="selectContainer" id="queryStatusWrapper">
                        	<input type="hidden" class="queryItem" value="" name="status" />
                        	<span class="selectOption">当前状态</span><em class="icon"></em>
                            <ul class="selectMenu">
                                <li class="cur" value="">全部</li>
                                <li value="CREATED">待审批</li>
                                <li value="APPROVED">已审批</li>
                            </ul>
                        </div>
                    </td>
                    <td style="width:100px">操作</td>
				</tr>
			</thead>
		</table>
	</div>

    <%-- 查看详情 --%>
    <div class="alert" style="display:none; width:781px;" id="detailsPanel" name="wh_alert">
		<div class="title"><a href="javascript:;" onclick="MU.hide(this)"></a><span id="detailsTitle"></span></div>
		<div class="column_con panelSizeWrapper" style="border:none; overflow:auto; width:770px; min-width:0">
			<h3 class="tab-tit" style="text-align: center;">长沙市岳麓科技产业园企业入园备案登记表</h3>
			<div class="signup-cont apply-count" id="viewFormWrapper">
			    <div class="row">
			        <label class="title w154">企业名称：</label>
			        <input type="text" value="" class="txt w190" id="viewCompanyName" />
			        <label class="title w100">法人代表：</label>
			        <input type="text" value="" class="txt w190" id="viewRepresentative" />
			    </div>
			    <div class="row">
			        <label class="title w154">联系方式：</label>
			        <input type="text" value="" class="txt w190" id="viewContact" />
			        <label class="title w100">手机：</label>
			        <input type="text" value="" class="txt w190" id="viewContactMobile" />
			    </div>
			    <div class="row">
			        <label class="title w154">固定电话：</label>
			        <input type="text" value="" class="txt w190" id="viewTelephone" />
			    </div>
			    <div class="row">
			        <label class="title w154">注册资金：</label>
			        <div class="con-span">
				        <span>内资</span>
				        <input type="text" value="" class="txt w80 left_float" id="viewRegCapDomestic"  />
				        <span>（万元），</span>
			        </div>
			        <div class="con-span">
			        	<span>外资</span>
			        	<input type="text" value="" class="txt w80" id="viewRegCapForeign" />
			        	<span>（万元）</span>
		        	</div>
			    </div>
			    <div class="row">
			    	<label class="title w154">注册地址：</label>
			    	<input type="text" value="" class="txt w520" id="viewRegAddress" />
		    	</div>
			    <div class="row">
			    	<label class="title w154">经营范围：</label>
			    	<input type="text" value="" class="txt w520" id="viewBusinessScope" />
		    	</div>
			    <!-- 
			    <div class="row row-new">
			        <label class="title w154">创业平台：</label>
			                <span class="checkbox_area">
			                    <label class="checkbox">广发隆平创业服务中心</label>
			                </span>
			                <span class="checkbox_area">
			                    <label class="checkbox">金丹科技创业大厦</label>
			                </span>
			                <span class="checkbox_area">
			                    <label class="checkbox">豪丹生物科技创业园</label>
			                </span>
			
			                 <span class="checkbox_area">
			                    <label class="checkbox">美地思创业园</label>
			                </span>
			                 <span class="checkbox_area">
			                    <label class="checkbox">行知教学创业园</label>
			                </span>
			                <span class="checkbox_area">
			                    <label class="checkbox">其他</label>
			                </span>
			    </div>
			    -->
			    <div class="row">
			        <label class="title w154">入驻企业联系人：</label>
			        <input type="text" value="" class="txt w100" id="viewCompanyContactName" />
			        <label class="title w94">联系电话：</label>
			        <input type="text" value="" class="txt w100" id="viewCompanyContactTel" />
			        <label class="title w56">邮箱：</label>
			        <input type="text" value="" class="txt w100" id="viewCompanyContactEmail" />
			    </div>
			    <div class="row">
			        <label class="title w154">创业平台联系人：</label>
			        <input type="text" value="" class="txt w100" id="viewPlatformContactName" />
			        <label class="title w94">联系电话：</label>
			        <input type="text" value="" class="txt w100" id="viewPlatformContactTel" />
			        <label class="title w56">邮箱：</label>
			        <input type="text" value="" class="txt w100" id="viewPlatformContactEmail" />
			    </div>
			    <div class="row">
			        <label class="title w154">招商合作局项目联系人：</label>
			        <input type="text" value="" class="txt w100" id="viewInvestContactName" />
			        <label class="title w94">联系电话：</label>
			        <input type="text" value="" class="txt w100" id="viewInvestContactTel" />
			        <label class="title w56">邮箱：</label>
			        <input type="text" value="" class="txt w100" id="viewInvestContactEmail" />
			    </div>
			    <div class="row">
				    <label class="title w154">入驻方意见：</label>
				    <input type="text" value="" class="txt w520" id="viewCompanyRemark" />
			    </div>
			    <div class="row">
			    	<label class="title w154">招商合作局意见：</label>
			    	<input type="text" value="" class="txt w520" id="viewInvestRemark" />
		    	</div>
			    <div class="row" style="height:66px;">
			    	<label class="title w154">审批意见：</label>
			    	<textarea id="approveRemark" class="txt w520" style="height:80px"></textarea>
		    	</div>
			    <div class="btn-div">
			    	<a href="javascript:;" class="btn" id="btnSubmitApprove">提交</a>
			    	<a href="javascript:;" onclick="MU.hide(this)" class="btn btn-grey">关闭</a>
			    </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${contextPath}/static/js/spark/GaParkJoinApplication_List.js?v=${buildTimestamp}"></script>

</body>