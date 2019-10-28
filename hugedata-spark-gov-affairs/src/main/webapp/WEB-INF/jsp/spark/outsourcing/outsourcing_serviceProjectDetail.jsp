<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<body class="service-page">

<h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/serviceProject">检验检测</a> &gt; <span>查看详情</span></h1>

<div class="cen ng-cloak" id="minServiceH" ng-app="outsourcingServiceProjectDetailModule" ng-controller="outsourcingServiceProjectDetailController">
    <h2 class="se-title se-title-noborder"><span>服务详情</span><a onclick="history.go(-1);" class="more">返回</a></h2>
    <div class="se-block se-article-block clearfix">
     <div class="new-div"><em class="icons {{ serviceProject.supplyType == 'SP_SUPPLY_TYPE_INSPECT_DETEC' ? ' ' : 'skill'}}"></em></div>
        <div id="DB_gallery">
            <div class="DB_imgSet">
                <div class="DB_imgWin">
                	<c:choose>
				   <c:when test="${images != null && images.size() > 0}">  
				      	<img src="${images[0].fileId }" alt=""/>
				   </c:when>
				   <c:otherwise> 
				   </c:otherwise>
				</c:choose>
                
                </div>
                <div class="DB_page"><span class="DB_current">0</span>-<span class="DB_total">0</span></div>
                <div class="DB_prevBtn"><img src="${contextPath}/static/web2/public/images/service/prev_off.png" alt=""/></div>
                <div class="DB_nextBtn"><img src="${contextPath}/static/web2/public/images/service/next_off.png" alt=""/></div>
            </div>
            <div class="DB_thumSet">
                <div class="DB_prevPageBtn"><img src="${contextPath}/static/web2/public/images/service/prev_page.png" alt="上一页"/></div>
                <ul class="DB_thumMove">
                
                <c:choose>
				   <c:when test="${images != null }">  
				      	<c:forEach var="a" items="${images}" varStatus="status">
							 <li><a href="${a.fileId }"><img src="${a.fileId }" alt=""/></a></li>					
						</c:forEach>
				   </c:when>
				   <c:otherwise> 
				   </c:otherwise>
				</c:choose>
                </ul>
                <div class="DB_thumLine"></div>
                <div class="DB_nextPageBtn"><img src="${contextPath}/static/web2/public/images/service/next_page.png" alt="下一页"/></div>
            </div>
        </div>
        <div class="con">
            <h2 class="tit"><span>{{serviceProject.supplyTheme}}</span>
                <%--<a class="collect-btn">关注收藏</a>--%>
            </h2>
            <div class="price clearfix">
                <span class="fl">价格：<em>{{serviceProject.price}}</em></span>
                <span class="fr"><em>{{serviceProject.visitorCount}}</em><br/>浏览量</span>
            </div>
            <dl class="clearfix">
                <dt>规格 <a class="more" data-id="0">更多规格</a></dt>
                <dd>
                    <ul class="clearfix">
                        <li ng-repeat="row in specList">
                            <label class="">{{row.specName}}</label>
                            <em ng-if="row.isNegotiablePrice == 0">￥ {{row.referPrice}}{{row.priceUnitStr}}/{{row.measureUnit}}</em>
                            <em ng-if="row.isNegotiablePrice == 1">价格面议</em>
                        </li>

                    </ul>
                </dd>
            </dl>
            <div class="contact">
                <h3><span>联系人：{{serviceProject.contact}}</span><span>电话：{{serviceProject.contactMobile}}</span></h3>
                <p>邮箱：{{serviceProject.email}}</p>
            </div>
        </div>
    </div>
    <div class="se-block se-intro-block">
        <h2 class="tit">服务详细说明</h2>
        <div class="con">
            <div id="content" ng-bind-html="serviceProject.detailDesc" style="line-height: normal; max-width:850px; word-wrap: break-word">
                <!-- content -->
            </div>
        </div>
    </div>
</div>
<div id="goTopBtn" class="go-top-btn"></div>

<script type="text/javascript" src="${contextPath}/static/web2/public/js/jquery.DB_gallery.js?v=${buildTimestamp}"></script>
<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular-sanitize.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_serviceProjectDetail.js?v=${buildTimestamp}"></script>
<script>
    window.projectId = ${projectId};
    $('#DB_gallery').DB_gallery({
        thumWidth:70,
        thumGap:8,
        thumMoveStep:1,
        moveSpeed:300,
        fadeSpeed:500
    });
    
    $(".more").click(function(){  //服务外包-详情页点击更多
        var num = $(this).attr("data-id");
        if( num == 1){
            $(this).attr('data-id','0');
            $(this).parent().next().removeClass('show');
        }else{
            $(this).attr('data-id','1');
            $(this).parent().next().addClass('show');
        }
    });
</script>

</body>
</html>
