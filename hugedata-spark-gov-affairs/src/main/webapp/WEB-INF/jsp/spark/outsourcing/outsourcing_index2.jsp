<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div class="ng-cloak" ng-app="indexModule" ng-controller="indexController">
  <!--content-start-->
  <div class="se-banner">
      <div class="banner-block"></div>
      <div class="nav">
          <ul class="clearfix">
              <li><a href="${contextPath}/outsourcing/serviceProject"><em class="icon"></em>检验检测</a></li>
              <li><a href="${contextPath}/outsourcing/requireList"><em class="icon icon2"></em>需求大厅</a></li>
              <li><a href="${contextPath}/outsourcing/expertsIndex"><em class="icon icon3"></em>成果转化</a></li>
              <li><a href="${contextPath}/outsourcing/meetingTrainingIndex"><em class="icon icon4"></em>会议培训</a></li>
          </ul>
      </div>
      <div class="news">
          <div class="cen clearfix">
              <script>
                  var marqueeContent=new Array();   //滚动主题

                
                  <c:choose>
				   <c:when test="${  msgList != null }">  
				   	 
				      	<c:forEach var="a" items="${  msgList}" varStatus="status">
							 marqueeContent[${status.index }]='<a href="${contextPath}/${ a.url}" target="_blank">${ a.name}</a>';
						</c:forEach>
					 
				   </c:when>
				   <c:otherwise> 
				 	 
				   </c:otherwise>
				</c:choose>
				
                  
                  
               
                  var marqueeInterval=new Array();  //定义一些常用而且要经常用到的变量
                  var marqueeId=0;
                  var marqueeDelay=5000;
                  var marqueeHeight=40;
                  function initMarquee() {
                      var str=marqueeContent[0];
                      document.write('<div id=marqueeBox class="news_scrol" style="overflow:hidden;height:'+marqueeHeight+'px" onmouseover="clearInterval(marqueeInterval[0])" onmouseout="marqueeInterval[0]=setInterval(\'startMarquee()\',marqueeDelay)"><div>'+str+'</div></div>');
                      marqueeId++;
                      marqueeInterval[0]=setInterval("startMarquee()",marqueeDelay);
                  }
                  function startMarquee() {
                      var str=marqueeContent[marqueeId];
                      marqueeId++;
                      if(marqueeId>=marqueeContent.length) marqueeId=0;
                      if(marqueeBox.childNodes.length==1) {
                          var nextLine=document.createElement('DIV');
                          nextLine.innerHTML=str;
                          marqueeBox.appendChild(nextLine);
                      }
                      else {
                          marqueeBox.childNodes[0].innerHTML=str;
                          marqueeBox.appendChild(marqueeBox.childNodes[0]);
                          marqueeBox.scrollTop=0;
                      }
                      clearInterval(marqueeInterval[1]);
                      marqueeInterval[1]=setInterval("scrollMarquee()",20);
                  }
                  function scrollMarquee() {
                      marqueeBox.scrollTop++;
                      if(marqueeBox.scrollTop%marqueeHeight==marqueeHeight){
                          clearInterval(marqueeInterval[1]);
                      }
                  }
                  initMarquee();
              </script>
          </div>
      </div>
  </div>

  <div class="cen" >
      <div class="se-row clearfix">
          <div class="fl">
              <h2 class="se-title"><span>检验检测</span><a href="${contextPath}/outsourcing/serviceProject" class="more">更多</a></h2>
              <ul class="se-ul clearfix">
        	   <li ng-repeat="row in supplyList1">
        	  		<a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
	        	  		<img ng-if="!row.coverFileId" src="${contextPath}/static/web2/public/images/service/img01.png" alt=""/>
	        	  		<img ng-if="row.coverFileId" ng-src="{{row.imgSrc}}" alt=""/>
	        	  		<span class="wh" title="{{row.supplyTheme}}">{{row.supplyTheme}}</span>
        	  		</a>
     	  		</li>
              </ul>
          </div>
          <div class="fr">
              <h2 class="se-title"><span>技术服务</span><a href="${contextPath}/outsourcing/serviceProject?supplyType=SP_SUPPLY_TYPE_TECH_SERVICE" class="more">更多</a></h2>
              <ul class="se-ul clearfix">
              	 <li ng-repeat="row in supplyList2">
        	  		<a href="${contextPath}/outsourcing/serviceProjectDetail?projectId={{row.projectId}}">
	        	  		<img ng-if="!row.coverFileId" src="${contextPath}/static/web2/public/images/service/img01.png" alt=""/>
	        	  		<img ng-if="row.coverFileId" ng-src="{{row.imgSrc}}" alt=""/>
	        	  		<span class="wh" title="{{row.supplyTheme}}">{{row.supplyTheme}}</span>
        	  		</a>
     	  		</li>
              </ul>
          </div>
      </div>
  </div>

  <div class="se-row-bg">
      <div class="cen">
          <div class="se-row clearfix">
              <div class="fl">
                  <h2 class="se-title"><span>需求大厅</span><a href="${contextPath}/outsourcing/requireList" class="more">更多</a><a href="${contextPath}/outsourcing/requireAdd" class="release-btn">发布需求</a></h2>
                  <dl class="se-dl clearfix">
                      <dt><a href="${contextPath}/outsourcing/requireList"><img src="${contextPath}/static/web2/public/images/service/img05.png" alt=""/></a></dt>
                      <dd>
                          <ul>
	                              <li ng-repeat="row in requireList">
	                              	<span class="disc"></span>
	                              	<a href="${contextPath}/outsourcing/requireDetail?requireId={{row.requireId}}" title="{{row.requireTitle}}">{{row.requireTitle}}</a>
	                              	<span>{{row.publishTime | date:'yyyy/MM/dd'}}</span>
	                             </li>
                             
                          </ul>
                      </dd>
                  </dl>
              </div>
              <div class="fr">
                  <h2 class="se-title"><span>会议培训</span><a href="${contextPath}/outsourcing/meetingTrainingIndex" class="more">更多</a></h2>
                  <dl class="se-dl clearfix">
                      <dt><a href="${contextPath}/outsourcing/meetingTrainingIndex"><img src="${contextPath}/static/web2/public/images/service/img03.png" alt=""/></a></dt>
                      <dd>
                          <ul>
                          	 <li ng-repeat="row in meetingList">
	                              	<span class="disc"></span>
	                              	<a href="${contextPath}/outsourcing/meetingTrainingDetail?trainingId={{row.trainingId}}" title="{{row.name}}">{{row.name}}</a>
	                              	<span>{{row.updateTime | date:'yyyy/MM/dd'}}</span>
                             </li>
                          </ul>
                      </dd>
                  </dl>
              </div>
          </div>
      </div>
  </div>

  <div class="cen">
      <div class="se-row se-row-expert clearfix">
          <h2 class="se-title"><span>成果转化</span><a href="${contextPath}/outsourcing/techAchieveIndex" class="more">更多</a></h2>
          <ul class="se-ul clearfix">
              <li ng-repeat="row in techAchieveList">
              	  <a href="${contextPath}/outsourcing/techAchieveDetail?achieveId={{row.achieveId}}">
                  <img ng-src="{{row.imgSrc}}" alt=""/>
                  <span class="wh" title="{{row.name}}">{{row.name}}</span>
                  <p>投资金额：<em class="red">{{row.investmentVolume}}</em><i>{{row.monetaryUnit}}</i></p>
              </a></li>
               
          </ul>
      </div>
  </div>
  <!--content-end-->
</div>
  <!-- 首页没有导航Tab，将其隐藏 -->
  <script>
      $("#sub_navigation_tab").hide();
  </script>
      <script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
    <script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
  <script src="${contextPath}/static/js/outsourcing/index.js?v=${buildTimestamp}"></script>
</body>
</html>