<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
    <title>服务外包</title>
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/ui.css?v=${buildTimestamp}" />
	<link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
 	<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery-1.8.2.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/public.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/selectList.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/datepicker.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/datepicker-zh-CN.js?v=${buildTimestamp}"></script>
    <script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery-ui.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/dateFormat.min.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/date.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/js/commons.js?v=${buildTimestamp}"></script>
	<script type="text/javascript" src="${contextPath}/static/web2/public/js/moment.js?v=${buildTimestamp}"></script>
	<script>
		var contextPath = "${contextPath}";
		var downloadServiceUrl = "${downloadServiceUrl}";
		var umsUrl = "${umsUrl}";
		var managementUrl = "${managementUrl}";
		var govAffairsPortalUrl = "${govAffairsPortalUrl}";
		var oaWebUrl = "${oaWebUrl}";
	</script>
	
	<style>
		.ng-cloak { display:none; }
	</style>
	
	<decorator:head />
</head>
<body class="service-page">
    <div class="se-topbar">
        <div class="cen clearfix">
            <div class="fl">
                <c:if test="${SPARK_LOGIN == null}">
                    <a href="${umsUrl}/login" class="mr10 loginLink">登录</a>|<a href="${umsUrl}/signup/agreement" class="ml10">注册</a>
                </c:if>
                <c:if test="${SPARK_LOGIN != null}">
                    欢迎您， ${SPARK_LOGIN.companyName} &nbsp;|
                    <a href="${contextPath}/user/userCenter" class="ml10">个人中心</a>
                    <a href="javascript:;" class="ml10 logoutLink">退出</a>
                </c:if>
            </div>
            <div class="fr">
                <a href="${govAffairsPortalUrl}">园区首页</a><a href="${contextPath}/serviceGuide1/list">办事服务</a><a href="${contextPath}/pmProject/list">项目管理</a><a href="${contextPath}/integrated/index">综合服务</a><a href="${contextPath}/handyService/index">便民服务</a><a href="${contextPath}/interactive/list">互动交流</a>
            </div>
        </div>
    </div>
    <div class="se-header">
        <div class="cen clearfix">
            <div class="logo fl">
                <a href="${contextPath}/outsourcing/"><img src="${contextPath}/static/web2/public/images/service/logo_service.png" alt="岳麓科技产业园"/></a>
            </div>
            <div class="search-block fr">
                <h2 class="search_menu">
                    <a id="searchMenuId_001">服务项目</a>
                    <a id="searchMenuId_002">服务需求</a>
                    <a id="searchMenuId_003">专家名录</a>
                    <a id="searchMenuId_004">技术成果</a>
                    <a id="searchMenuId_005">会议培训</a>
                </h2>
                <div class="clearfix">
                    <div class="search-box fl">
                        <input id="searchWord" type="text" placeholder="请输入关键词"/><a href="javascript:;" id="btnSearch" class="search-btn"></a>
                    </div>
                    <a class="btn btn-grey fl" href="${contextPath}/outsourcing/publishSupply">发布供应</a><a class="btn btn-grey fl" href="${contextPath}/outsourcing/requireAdd">发布需求</a>
                </div>
                <p><!--  <a>化学化工</a><a>建筑材料</a><a>无损检测</a><a>消费品</a><a>环境检测与职业卫生</a>--></p>
            </div>
        </div>
    </div>

    <div id="sub_navigation_tab" class="se-nav">
        <div class="cen clearfix">
            <div class="fl menu_down">
                <a id="menuId_100" href="${contextPath}/outsourcing/">首页</a>
                <a id="menuId_200" href="${contextPath}/outsourcing/serviceProject">检验检测</a>
                <a id="menuId_300"  href="${contextPath}/outsourcing/requireList">需求大厅</a>
                <a id="menuId_400" href="${contextPath}/outsourcing/expertsIndex" class="on">成果转化</a>
                <a id="menuId_500" href="${contextPath}/outsourcing/meetingTrainingIndex">会议培训</a>
            </div>
        </div>
    </div>

    <script>
        $(".loginLink").attr("href", "${umsUrl}/login?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
        $(".logoutLink").attr("href", "${umsUrl}/logout?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));

        var searchMenuId = "${searchMenuId}";
        if (searchMenuId != "") {
            $("#searchMenuId_" + searchMenuId).addClass("on");
        }

        var menuId = "${menuId}";
        if (menuId != "") {
            $(".menu_down a").removeClass("on");
            $(".menu ul li").removeClass("on");
            if ($("#menuId_" + menuId).is("li")) {
                $("#menuId_" + menuId).addClass("on");
            } else {
                $("#menuId_" + menuId).addClass("on").parents("li").addClass("on");
            }
        }

        /**
         * 搜索
         */
        $("#btnSearch").on("click", function() {
            debugger;
            var currSearchMenuId = $(".search_menu a.on").attr("id");
            var searchWord = $("#searchWord").val().trim();
            if(currSearchMenuId != null && currSearchMenuId != "") {
                switch (currSearchMenuId){
                    case "searchMenuId_001":
                        window.location.href = '${contextPath}/outsourcing/serviceProjectSearch?searchWord='+searchWord;
                        break;
                     // 搜索专家名录
                    case "searchMenuId_002":
                        window.location.href = '${contextPath}/outsourcing/requireList?searchWord='+searchWord;
                        break;
                    // 搜索专家名录
                    case "searchMenuId_003":
                        window.location.href = '${contextPath}/outsourcing/expertsSearch?searchWord='+searchWord;
                        break;
                    // 搜索技术成果
                    case "searchMenuId_004":
                        window.location.href = '${contextPath}/outsourcing/techAchieveSearch?searchWord='+searchWord;
                        break;
                    // 会议培训的搜索结果页就是列表页，没有单独的搜索页
                    case "searchMenuId_005":
                        window.location.href = contextPath + '/outsourcing/meetingTrainingIndex?searchWord='+searchWord;
                        break;
                    default:
                        break;
                }

            }
        });
    </script>

    <!--content-start-->
    <decorator:body />
    <!--content-end-->

    <div class="login_footer">
        <div class="cen">
            <div class="login_footer_top">
                <ul class="left_float">
                    <li>
                        <h1>友情链接</h1>
                        <p><a href="http://www.hnxjxq.gov.cn/" target="_blank">湘江新区</a></p>
                        <p><a href="http://www.changsha.gov.cn/" target="_blank">中国长沙</a></p>
                        <p><a href="http://www.yuelu.gov.cn/" target="_blank">中国岳麓</a></p>
                        <p><a href="http://www.hunan.gov.cn/" target="_blank">湖南省政府网</a></p>
                    </li>
                </ul>
                <img src="${contextPath}/static/web2/public/images/code1.png" style="margin-right: 105px;">
                <%--
                <img src="${contextPath}/static/web2/public/images/code2.png">
                --%>
                <ul class="right_float">
                    <li>
                        <p>联系电话：0731-88532200</p>
                        <p>版权所有：长沙市岳麓科技产业园</p>
                        <p>备案号：湘ICP备10202297号-1</p>
                        <p>技术支持：湖南网数科技有限公司</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div id="goTopBtn" class="go-top-btn"></div>

    <script>
        <!--  回到顶部悬浮按钮 -->
        $(function() {
            $(window).scroll(function(){
                var sc=$(window).scrollTop();
                if(sc>0){
                    $("#goTopBtn").css("display","block");
                }else{
                    $("#goTopBtn").css("display","none");
                }
            })
        });

        $("#goTopBtn").click(function(){  //  回到顶部
            var sc=$(window).scrollTop();
            $('body,html').animate({scrollTop:0},500);
        });
    </script>
</body>
</html>