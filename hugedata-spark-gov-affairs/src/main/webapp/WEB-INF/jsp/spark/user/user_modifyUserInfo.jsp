<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
</head>
<body>

<h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>个人中心</span></h1>
<!--content-start-->
<div class="column_con clearfix" id="minH">
    <h2 class="title">个人中心</h2>
    <div class="tab"><a href="${contextPath}/user/userCenter" class="on">注册信息</a><a href="${contextPath}/messages/list">消息通知</a></div>
    <div class="signup-cont signup-cont1">
        <div class="row">
        	<label class="title"><i>*</i>单位名称：</label>
			<input type="text" value="${company.companyName}" id="companyName" class="txt" maxlength="100" />
			<em><i id="companyName_error"></i></em>
       	</div>
        <div class="row">
        	<label class="title"><i>*</i>单位类型：</label>
            <span class="select w262">
				<select id="companyType">
                    <option value=""  ${company.companyType==''  ? 'selected="selected"' : ''}>请选择</option>
                    <option value="1" ${company.companyType=='1' ? 'selected="selected"' : ''}>行政单位</option>
                    <option value="2" ${company.companyType=='2' ? 'selected="selected"' : ''}>事业单位</option>
                    <option value="3" ${company.companyType=='3' ? 'selected="selected"' : ''}>企业单位</option>
                    <option value="4" ${company.companyType=='4' ? 'selected="selected"' : ''}>军事单位</option>
                    <option value="5" ${company.companyType=='5' ? 'selected="selected"' : ''}>其他单位</option>
                    <option value="6" ${company.companyType=='6' ? 'selected="selected"' : ''}>个人</option>
                </select>
            </span>
			<em><i id="companyType_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>单位地址：</label>
			<input type="text" value="${company.companyAddress}" id="companyAddress" class="txt" maxlength="100" />
			<em><i id="companyAddress_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>组织机构代码：</label>
			<input type="text" value="${company.organizationCode}" id="organizationCode" class="txt" maxlength="100" />
			<em><i id="organizationCode_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>营业执照号码：</label>
			<input type="text" value="${company.licenceCode}" id="licenceCode" class="txt" maxlength="100" />
			<em><i id="licenceCode_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>法人代表：</label>
			<input type="text" value="${company.representative}" id="representative" class="txt" maxlength="100" />
			<em><i id="representative_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>法人身份证号码：</label>
			<input type="text" value="${company.representativeId}" id="representativeId" class="txt" maxlength="100" />
			<em><i id="representativeId_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>联系人：</label>
			<input type="text" value="${company.contactName}" id="contactName" class="txt" maxlength="100" />
			<em><i id="contactName_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>联系人电话：</label>
			<input type="text" value="${company.contactMobile}" id="contactMobile" class="txt" maxlength="100" />
			<em><i id="contactMobile_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>联系人邮箱：</label>
			<input type="text" value="${company.contactEmail}" id="contactEmail" class="txt" maxlength="200" />
			<em><i id="contactEmail_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>密码：</label>
			<input type="password" value="" id="password" class="txt" />
			<em><i id="password_error"></i></em>
        </div>
        <div class="row">
	        <label class="title"><i>*</i>确认密码：</label>
			<input type="password" value="" id="password2" class="txt" />
			<em><i id="password2_error"></i></em>
        </div>
        <div class="btn-div res-div">
        	<a class="btn" href="javascript:;" id="btnSubmit">确认</a>
        	<a class="btn btn-grey" href="${contextPath}/user/userCenter">取消</a>
       	</div>
    </div>
</div>
<!--content-end-->



<script src="${contextPath}/static/js/user/user_modifyUserInfo.js?v=${buildTimestamp}"></script>
</body>
</html>

