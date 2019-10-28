<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<body class="service-page">

<div class="cen ng-cloak" id="minServiceH" ng-app="outsourcingServiceProjectSearchModule" ng-controller="outsourcingServiceProjectSearchController">
    <input id="queryItem" value="${searchWord}" type="hidden" name="queryItem">
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <span>搜索</span></h1>
    <div class="se-filter clearfix">
        <label class="tit">服务类型：</label>
        <div class="conbox">
            <input id="supplyTypeQuery" value="SP_SUPPLY_TYPE_INSPECT_DETEC" type="hidden" name="supplyType">
            <a class="on" ng-click="supplyTypeSelected($event, 'SP_SUPPLY_TYPE_INSPECT_DETEC');">检验检测</a>
            <a ng-click="supplyTypeSelected($event, 'SP_SUPPLY_TYPE_TECH_SERVICE');">技术服务</a>
        </div>
    </div>
    <div class="se-filter clearfix">
        <label class="tit">服务领域：</label>
        <div class="conbox">
            <input id="serviceFieldQuery" value="" type="hidden" name="serviceField">
            <a class="on" href="javascript:"  ng-click="serviceFieldSelected($event, '')">全部</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_CHEMISTRY')">化学化工</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_CONSTRUCTION')">建筑建材</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_NDT')">无损检测</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_CONSUMER_GOODS')">消费品</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_ENVIRON_HEALTH')">环境检测与职业卫生</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_BIOLOGY_EQUIPMENT')">生物医药及器械</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_ELECTRONICS_MECHANICAL')">电子电气及机械</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_EMC')">电磁兼容（EMC）</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_SPIN_FIBER')">纺织及纤维</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_SOFTWARE_INFO')">软件信息</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_METAL_MATERIAL')">金属材料</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_FOOD_FARM_PRODUCTS')">食品及农产品</a>
            <a href="javascript:" ng-click="serviceFieldSelected($event, 'SP_SERVICE_FIELD_OTHER')">其他</a>
        </div>
        <a class="show_more" data-id="0">更多</a>
    </div>
    <div class="se-sort">
        <label>排序：</label>
        <input id="sortTypeQuery" value="QUERY_SORT_VISITOR_COUNT" type="hidden" name="sortType">
        <a class="on" href="javascript:" ng-click="sortTypeSelected($event, 'QUERY_SORT_VISITOR_COUNT')">按浏览量</a>
        <a href="javascript:" ng-click="sortTypeSelected($event, 'QUERY_SORT_LATEST_PUBLISH')">按最新发布</a>
        <span class="fr"><b class="blue">{{page.page}}</b> / <b class="fb">{{page.pageCount}}</b> 页</span>
    </div>
    <ul class="se-list">
        <li ng-repeat="row in page.data">
            <table class="se-table">
                <tr>
                    <td width="140" ng-if="!row.coverFileId"><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
                        <img ng-src="${contextPath}/static/web2/public/images/service/img01.png" alt=""/></a></td>
                    <td width="140" ng-if="row.coverFileId"><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
                        <img ng-src="{{row.imgSrc}}" alt=""/></a></td>
                    <td width="" valign="top">
                        <div class="info">
                             <h2><a class="wh" href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}" title="{{row.supplyTheme}}" style="width: 380px;">{{row.supplyTheme}}</a></h2>
                            <p class="wh" title="{{row.supplyBrief}}" style="width: 380px;">{{row.supplyBrief}}</p>
                            <p>{{row.companyName}}</p>
                            <em class="icons"></em>
                        </div>
                    </td>
                    <td width="130" align="center">
                        <div class="price">
                            价格<span class="red">{{row.price}}</span>
                        </div>
                    </td>
                    <td width="270" align="center">{{row.visitorCount}}人浏览</td>
                    <td width="130"><a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}" class="btn btn-grey">查看详情</a></td>
                </tr>
            </table>
        </li>
    </ul>
    <div class="page_v1" style="text-align: center;">
        <span class="total">共<em>{{page.pageCount}}</em>页</span>
        <span class="ell" ng-if="pageButtons[0] != 1">...</span>
        <ng-repeat ng-repeat="p in pageButtons">
            <a target="_self" href="javascript:;" ng-if="p!=page.page" ng-click="gotoPage(p)">{{p}}</a>
            <span class="cur" ng-if="p==page.page">{{p}}</span>
        </ng-repeat>
        <span class="ell" ng-if="pageButtons[pageButtons.length - 1] != page.pageCount">...</span>
        <a target="_self" href="javascript:;" ng-click="gotoPage(page.hasNext ? page.page+1 : page.page)">下一页</a><span>到<input type="text" name="jump_url" ng-model="directGoto" ng-keydown="onPageKeydown($event)"/>页</span><a href="javascript:;" class="pages-goto" ng-click="directGotoPage()">跳转</a>
    </div>
</div>

<div id="goTopBtn" class="go-top-btn"></div>
 <script>
      <!-- 保持从上个页面带过来的搜索关键字 -->
      var searchWord = "${searchWord}";
      $("#searchWord").val("${searchWord}");
  </script>
  
<script>
    window.supplyTypeMap = {
        "SP_SUPPLY_TYPE_INSPECT_DETEC": "检验检测",
        "SP_SUPPLY_TYPE_TECH_SERVICE": "技术服务"
    };
    window.supplyType = "SP_SUPPLY_TYPE_INSPECT_DETEC";
    //服务外包-行业类别点击更多
    $(".show_more").click(function(){
        var num = $(this).attr("data-id");
        if( num == 1){
            $(this).attr('data-id','0');
            $(".se-filter .conbox").css("height","24px");
        }else{
            $(this).attr('data-id','1');
            $(".se-filter .conbox").css("height","auto");
        }
    });
</script>
<script type="text/javascript" src="${contextPath}/static/web2/public/js/scripts.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_serviceProjectSearch.js?v=${buildTimestamp}"></script>
</body>
</html>