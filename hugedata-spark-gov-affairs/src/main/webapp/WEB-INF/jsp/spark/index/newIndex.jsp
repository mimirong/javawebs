<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="decorator" content="simple2">
  <title>首页</title>
  <link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/reset.css?v=${buildTimestamp}" />
  <link type="text/css" rel="stylesheet" href="${contextPath}/static/web2/public/css/style.css?v=${buildTimestamp}" />
</head>
<body>
<script>
$("body").css({ "overflow":"hidden" });
</script>

<div class="top-tips">建议使用IE8以上浏览器（不含IE8）浏览网站 <a class="close"></a></div>
<div class="logo-index">
    <img src="${contextPath}/static/web2/public/images/logo-index.PNG" alt=""/>
    <div class="logo-index-right">
    	<%--
        <a class="download-app"><em class="dlapp-icon"></em>下载APP</a>
        --%>
		<c:if test="${SPARK_LOGIN == null}">
        	<a class="download-app"><em class="dlapp-icon"></em>下载APP</a>
        	<a class="internal-office" href="${managementUrl}" target="_blank">内部办公系统</a>
	        <a class="" href="${umsUrl}/login">登录</a>
	        <a>&nbsp;|&nbsp;</a>
	        <a class="" href="${umsUrl}/signup/agreement">注册</a>
		</c:if>
		<c:if test="${SPARK_LOGIN != null}">
        	<a class="download-app"><em class="dlapp-icon"></em>下载APP</a> &nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/user/userCenter">欢迎您，${SPARK_LOGIN.companyName} &nbsp;|&nbsp;</a>
			<a href="javascript:;" class="logoutLink">退出</a>
		</c:if>
        <div class="download-con">
            <ul class="clearfix">
                <li>
                    <h2>IOS下载</h2>
                    <img src="${contextPath}/static/web2/public/images/IOS.png?v=${buildTimestamp}" style="width:93px; height:92px" alt=""/>
                </li>
                <li>
                    <h2>安卓下载</h2>
                    <img src="${contextPath}/static/web2/public/images/android.png?v=${buildTimestamp}" style="width:94px; height:94px" alt=""/>
                </li>
            </ul>
        </div>
		<script>
			$(".logoutLink").attr("href", "${umsUrl}/logout?redirect=" + encodeURIComponent("${govAffairsPortalUrl}"));
		</script>
    </div>
</div>

<div class="slideImg">
</div>

<div class="index-bottom" style="position:absolute;width:100%;bottom:0px;">
    <div class="indexNav">
        <ul>
            <li class="nav0"><a href="http://www.cshrss.gov.cn/xxgk/ztzl/csrcxz22/" target="_blank"><img src="${contextPath}/static/web2/public/images/nav0.PNG" /><span>人才政策</span></a></li>
            <li class="nav1"><a href="${contextPath}/serviceGuide1/list"><img src="${contextPath}/static/web2/public/images/nav1.PNG" /><span>办事服务</span></a></li>
            <li class="nav2"><a href="${contextPath}/outsourcing/index"><img src="${contextPath}/static/web2/public/images/nav2.PNG" />服务外包</a></li>
            <li class="nav3"><a href="${contextPath}/pmProject/list"><img src="${contextPath}/static/web2/public/images/nav3.PNG" />项目管理</a></li>
            <li class="nav7"><a href="${contextPath}/integrated/index"><img src="${contextPath}/static/web2/public/images/nav7.PNG" />综合服务</a></li>
            <li class="nav4"><a href="${contextPath}/notice/index"><img src="${contextPath}/static/web2/public/images/nav4.PNG" />通知公告</a></li>
            <li class="nav5"><a href="${contextPath}/handyService/index"><img src="${contextPath}/static/web2/public/images/nav5.PNG" />便民服务</a></li>
            <li class="nav6"><a href="${contextPath}/interactive/list"><img src="${contextPath}/static/web2/public/images/nav6.PNG" />互动交流</a></li>
        </ul>
    </div>
<!--footer-->
    <div class="index-footer">
        <div class="clearfix" style="min-width: 1220px;">
        <div class="footer-left">
            <h1 class="footer-tit">友情链接</h1>
            <ul class="clearfix">
                <li><a href="http://www.hnxjxq.gov.cn/" target="_blank" style="color: #cdcdcd;">湘江新区</a></li>
                <li><a href="http://www.changsha.gov.cn/" target="_blank" style="color: #cdcdcd;">中国长沙</a></li>
                <li><a href="http://www.yuelu.gov.cn/" target="_blank" style="color: #cdcdcd;">中国岳麓</a></li>
                <li><a href="http://www.hunan.gov.cn/" target="_blank" style="color: #cdcdcd;">湖南省政府网</a></li>
            </ul>
        </div>
        <div class="left_float" style="margin-top:10px;">
        	<img src="${contextPath}/static/web2/public/images/wechat.PNG?v=${buildTimestamp}" alt="" style="margin-right: 100px;"/>
        	<!--
        	<img src="${contextPath}/static/web2/public/images/weibo.PNG?v=${buildTimestamp}" alt=""/>
        	-->
       	</div>
        <div class="footer-right">
            <ul>
            	<li style="margin:30px 0 12px 0;">电话：0731-88532200&nbsp;&nbsp;&nbsp;&nbsp;版权所有：长沙市岳麓科技产业园&nbsp;&nbsp;&nbsp;&nbsp;备案号：湘ICP备10202297号-1</li>
                <li>建议使用IE8以上浏览器（不含IE8）浏览网站，技术支持：湖南网数科技有限公司</li>
            </ul>
        </div>
        </div>
    </div>
</div>
<!--footer结束-->
<script>
	$.ajax({
		url: "queryHomeImages",
		type: "post",
		dataType: "json",
		data: {},
		success: function(resp) {
			if (resp && resp.result == 0) {
				var images = resp.data;
				var m = [];
				$.each(images, function(i, image) {
					var onclick = "";
					var style = 'background-image:url(' + image.imageUrl + ');  ';
					style += 'background-size:cover; ';
					style += 'z-index:' + ((images.length-i)*100) + '; ';
					
					if (image.linkUrl && image.linkUrl != "") {
						onclick = 'window.open(\'' + image.linkUrl + '\')';
						style += 'cursor:pointer;';
					}
					
				    m.push('<div class="pageImg img1" onclick="' + onclick + '" style="' + style + '">');
		    		m.push('  <img src="' + image.imageUrl + '" style="display:block; visibility:hidden;" />');
    				//m.push('  <div class="txt"></div>');
 					m.push('</div>');
				});
				$('.slideImg').html(m.join(""));
				$(".slideImg .pageImg").css("height",$(window).height()-100);
				initImages(images.length);
			} else {
				try { console.error("Failed to load home images"); } catch(e) {}
			}
		},
		error: function() {
			try { console.error("Failed to load home images"); } catch(e) {}
		}
	});

    function initImages(count) {
        var pageImg = $('.pageImg');
        var slideImg = $('.slideImg');
        var op = 0.2;
        var num = 0;
        pageImg.eq(num).addClass("z1");
        setInterval(function(){
            num+=1;
            if(num>=count){
                num=0;
            }
            comeback(pageImg.eq(num));
        },5000)

        function comeback(obj){
            obj.css({
                'left'       : 0,
                'top'        : 0,
                'z-index'    : 1,
                'opacity'    : 1,
                'visibility' : 'visible'
            });
            pageImg.not(obj).each(function(){
                $(this).css({
                    'z-index' : parseInt($(this).css('z-index')) + 1
                });
                $(this).animate(
               		{ opacity: 0 }, 
               		'slow', 
               		'', 
               		function() {
               			$(this).css('visibility', 'hidden');
               		}
              	);
            })
        }
    }
</script>
</body>
</html>
