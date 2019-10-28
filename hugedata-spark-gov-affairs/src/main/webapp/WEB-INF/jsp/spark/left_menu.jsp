<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="column_menu">
    <ul>
        <li class="menu_tit">政务服务</li>
        <li class="menu_messages"><a href="${contextPath}/messages/list">消息通知</a>
        <li class="menu_parkJoin"><a href="${contextPath}/parkApply/in">入园申请</a>
        <li class="menu_parkQuit"><a href="${contextPath}/parkApply/out">退园申请</a>
        <li class="menu_fee"><a href="${contextPath}/fees/bills">园区费用</a>
        <li class="menu_aptitude"><a href="${contextPath}/aptitude/list">资质认证</a>
        <li class="menu_financing"><a href="${contextPath}/financingApply/list">融资申请</a>
        <li class="menu_report"><a href="${contextPath}/reportSubmit/list">统计报表</a>
        <li class="menu_siteProof"><a href="${contextPath}/siteProof/list">场地证明</a>
        <li class="menu_rentInfo"><a href="${contextPath}/rentInfo/list">招商信息</a>
    </ul>
</div>
<script>
	$(".menu_${param.currentMenu}").addClass("active");
	setInterval(function() {
		$(".column_menu ul").css('height',$(".column_list").height()+50);
	}, 200, 0);
</script>
