<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

    <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>便民服务</span></h1>

    <!--content-start-->
    <div class="column_con clearfix" id="minH" style="padding: 20px;">
        <div class="service-block">
            <ul class="clearfix">
                <li><a id="menuId_501" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_RESIDENCE"><img src="${contextPath}/static/web2/public/images/yqxq.png?v=${buildTimestamp}" alt=""/></a></li>
                <li><a id="menuId_502" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_SCHOOL"><img src="${contextPath}/static/web2/public/images/yqxx.png?v=${buildTimestamp}" alt=""/></a></li>
                <li><a id="menuId_503" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_HOSPITAL"><img src="${contextPath}/static/web2/public/images/yqyy.png?v=${buildTimestamp}" alt=""/></a></li>
                <li><a id="menuId_504" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_BUS"><img src="${contextPath}/static/web2/public/images/yqgj.png?v=${buildTimestamp}" alt=""/></a></li>
                <li><a id="menuId_505" href="${contextPath}/handyService/articleList?category=HANDY_SERVICE_BANK"><img src="${contextPath}/static/web2/public/images/yqyh.png?v=${buildTimestamp}" alt=""/></a></li>
            </ul>
            <a href="http://www.yuelu.gov.cn/yuelugov/bsfw/index.html" class="btn" target="_blank">更多便民办事服务 >></a>
        </div>
    </div>
    <!--content-end-->

</body>
</html>
