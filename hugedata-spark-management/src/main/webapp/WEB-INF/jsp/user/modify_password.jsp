<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
    <title>内部办公系统</title>
    <script>
        currentMenu = "";
        currentSystem = "";
    </script>
</head>

<body>

<!--content-start-->
    <h2 class="title">修改密码</h2>
    <div class="tab-content">
        <h3 class="title">修改密码</h3>
        <ul class="form clearfix" style="margin: 50px auto 30px;width: 810px;">
            <li class="clearfix">
	            <label class="tit"><em>*</em>旧密码：</label>
	            <input type="password" class="text" id="oldPassword" name="oldPassword" value="" placeholder="请输入旧密码" style="height:30px;" />
	            <em class="error"></em>
            </li>
            <li class="clearfix">
	            <label class="tit"><em>*</em>新密码：</label>
	            <input type="password" class="text" id="password" name="password" value="" placeholder="请输入新密码" style="height:30px;" />
	            <em class="error"></em>
            </li>
            <li class="clearfix">
	            <label class="tit"><em>*</em>确认新密码：</label>
	            <input type="password" class="text" id="passwords" name="passwords" value="" placeholder="请再次输入新密码" style="height:30px;" />
	            <em class="error"></em>
            </li>
            <li class="clearfix">
	            <label class="tit">&nbsp;</label>
	            <input type="text" value="" id="imgCode" name="imgCode" class="txt" placeholder="验证码" style="width: 146px;"/>
	            <a href="javascript:;" class=" code imgCodeUpdate">
	            <img src="${contextPath}/modifyPwd/viewLoginCode" alt="" style="vertical-align: top;border: 1px solid #b6b6b6;margin-left: 10px;padding: 2px;"/></a>
	            <em class="error"></em>
            </li>
            <li class="clearfix">
            	<label class="tit">&nbsp;</label><a class="btn mr10" id="submitModifyPassword">提交</a>
            </li>
        </ul>
    </div>
<!--content-end-->

<script>
    $(".column_menu").hide();
    $(".leftsidebar").hide();
    $(".column_list").css({margin:0});
    $("#currentLocationWrapper").html("您当前位置：<span>修改密码</span>");
</script>

<script type="text/javascript" src="${contextPath}/static/js/spark/modifyPwd.js?v=${buildTimestamp}"></script>
</body>
</html>