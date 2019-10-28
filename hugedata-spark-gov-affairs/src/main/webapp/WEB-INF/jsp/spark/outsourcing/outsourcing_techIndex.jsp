<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div class="ng-cloak" ng-app="outsourcingTechIndexModule" ng-controller="outsourcingTechIndexController">
  <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <a href="${contextPath}/outsourcing/index">服务外包</a> >  <span>公共技术服务</span></h1>

  <!--content-start-->
  <div class="column_con clearfix" id="minH">
      <h2 class="title">公共技术服务 </h2>
      <div class="">
          <div class="block left_float mr18 w470">
              <a href="${contextPath}/outsourcing/articleList?category=HUMAN_RESOURCE_1"><h3 class="title"> 人力资源服务</h3></a>
              <div class="technology">
                    <ul class="technology-ul clearfix">
                        <li>
                            <a href="${contextPath}/outsourcing/articleList?category=HUMAN_RESOURCE_1">
                            <em class="icon"></em>
                            <p class="title">人才培训</p>
                            </a>
                        </li>
                        <li>
                            <a href="${contextPath}/outsourcing/articleList?category=HUMAN_RESOURCE_2">
                                <em class="icon icon1"></em>
                                <p class="title">技术职称招考</p>
                            </a>
                        </li>
                        <li>
                            <a href="${contextPath}/outsourcing/articleList?category=HUMAN_RESOURCE_3">
                                <em class="icon icon2"></em>
                                <p class="title">企业招聘</p>
                            </a>
                        </li>
                    </ul>
                  <h1 class="second-tit"><span class="line"/></span>人才招聘<span class="line"/></span></h1>
                  <div class="company_reports prize">
                      <div class="slidebox">
                          <ul class="clearfix slideList">
                              <li class="item" style="height:auto;" ng-repeat="row in humanResourceList">
                                  <a ng-if="row.linkUrl" target="_blank" ng-href="{{row.linkUrl}}"><img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" /></a>
                                  <img ng-if="!row.linkUrl" ng-src="{{row.imgSrc}}" alt=""/>
                                  <p class="slide-tit" style="width:102px">{{row.title}}</p>
                              </li>
                          </ul>
                      </div>
                  </div>
              </div>
          </div>
          <div class="block left_float w470">
              <a href="${contextPath}/outsourcing/articleList?category=INFORMATION_COMPANIES"><h3 class="title"> 企业信息服务 </h3></a>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=INFORMATION_COMPANIES">
                              <em class="icon icon3"></em>
                              <p class="title">企业信息库</p>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=INFORMATION_PRODUCTS">
                              <em class="icon icon4"></em>
                              <p class="title">产品信息库</p>
                          </a>
                      </li>
                  </ul>
                  <h1 class="second-tit"><span class="line"/></span>品牌展示<span class="line"/></span></h1>
                  <div class="company_reports prize">
                      <div class="slidebox">
                          <ul class="clearfix slideList">
                              <li class="item" style="height:auto;" ng-repeat="row in companiesInfoList">
                                  <a ng-if="row.linkUrl" target="_blank" ng-href="{{row.linkUrl}}"><img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" /></a>
                                  <img ng-if="!row.linkUrl" ng-src="{{row.imgSrc}}" alt=""/>
                                  <p class="slide-tit" style="width:102px">{{row.title}}</p>
                              </li>
                          </ul>
                      </div>
                  </div>
              </div>
          </div>
          <div class="block left_float mr18 w470" >
              <a href="${contextPath}/outsourcing/articleList?category=OSINVEST_1"><h3 class="title"> 项目招商服务 </h3></a>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=OSINVEST_1">
                              <em class="icon icon5"></em>
                              <p class="title">招商信息</p>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=OSINVEST_2">
                              <em class="icon icon6"></em>
                              <p class="title">资源展示</p>
                          </a>
                      </li>
                      <!--<li>
                          <a href="#">
                              <em class="icon icon7"></em>
                              <p class="title">在线申请</p>
                          </a>
                      </li>-->
                  </ul>
                  <h1 class="second-tit"><span class="line"/></span>园区影像<span class="line"/></span></h1>
                  <div class="company_reports prize">
                      <div class="slidebox">
                          <ul class="clearfix slideList">
                              <li class="w98" style="height:auto;" ng-repeat="row in projectOsinvestList">
		                          <a ng-if="row.linkUrl && row.linkUrl != ''" target="_blank" ng-href="{{row.linkUrl}}">
		                              <img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" />
		                          </a>
		                          <a ng-if="!row.linkUrl || row.linkUrl == ''" target="_blank" ng-href="techImage?imageId={{row.imageId}}">
		                              <img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" />
		                          </a>
                                  <p class="slide-tit" style="width:102px">{{row.title}}</p>
                              </li>
                          </ul>
                      </div>
                  </div>
              </div>
          </div>
          <div class="block left_float w470">
              <h3 class="title"> 知识产权服务 </h3>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a ng-if="intellectualServiceGuideUrl" ng-href="{{intellectualServiceGuideUrl}}" target="_blank">
                              <em class="icon icon8"></em>
                              <p class="title">办事服务</p>
                          </a>
                          <a ng-if="!intellectualServiceGuideUrl" ng-href="javascript:;">
                              <em class="icon icon8"></em>
                              <p class="title">办事服务</p>
                          </a>
                      </li>
                      <li>
                          <a ng-if="intellectualDataServiceUrl" ng-href="{{intellectualDataServiceUrl}}" target="_blank">
                              <em class="icon icon9"></em>
                              <p class="title">数据服务</p>
                          </a>
                          <a ng-if="!intellectualDataServiceUrl" ng-href="javascript:;">
                              <em class="icon icon9"></em>
                              <p class="title">数据服务</p>
                          </a>
                      </li>
                  </ul>
                  <h1 class="second-tit"><span class="line"/></span>相关网站<span class="line"/></span></h1>
                  <ul class="logo-ul clearfix">
                      <li class="" style="width:205px; height:auto;" ng-repeat="row in intellectualList">
                          <a ng-if="row.linkUrl" target="_blank" ng-href="{{row.linkUrl}}">
                              <img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" style="width:205px;" />
                          </a>
                          <img ng-if="!row.linkUrl" ng-src="{{row.imgSrc}}" alt=""/>
                          <p class="slide-tit" style="width:205px; height:20px; line-height:20px; color:#555;">{{row.title}}</p>
                      </li>
                  </ul>
              </div>
          </div>
          <div class="block left_float w470" style="clear:both;">
              <h3 class="title"> 诚信体系服务 </h3>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a ng-if="creditSystemComInfoQueryUrl" ng-href="{{creditSystemComInfoQueryUrl}}" target="_blank">
                              <em class="icon icon10"></em>
                              <p class="title">企业信用查询</p>
                          </a>
                          <a ng-if="!creditSystemComInfoQueryUrl" ng-href="javascript:;">
                              <em class="icon icon10"></em>
                              <p class="title">企业信用查询</p>
                          </a>
                      </li>
                      <li>
                          <a ng-if="creditSystemComInfoSubmitUrl" ng-href="{{creditSystemComInfoSubmitUrl}}" target="_blank">
                              <em class="icon icon11"></em>
                              <p class="title">企业信息填报</p>
                          </a>
                          <a ng-if="!creditSystemComInfoSubmitUrl" ng-href="javascript:;">
                              <em class="icon icon11"></em>
                              <p class="title">企业信息填报</p>
                          </a>
                      </li>
                  </ul>
                  <h1 class="second-tit"><span class="line"/></span>相关站点<span class="line"/></span></h1>
                  <ul class="logo-ul clearfix">
                      <li style="width:100%; height:auto;" ng-repeat="row in creditSystemList">
                          <a ng-if="row.linkUrl" target="_blank" ng-href="{{row.linkUrl}}"><img ng-src="{{row.imgSrc}}" alt="" class="c-pointer" /></a>
                          <img ng-if="!row.linkUrl" ng-src="{{row.imgSrc}}" alt=""/>
                          <p class="slide-tit" style="width:428px; height:20px; line-height:20px; color:#555;">{{row.title}}</p>
                      </li>
                  </ul>
              </div>
          </div>
      </div>
  </div>
    <!--content-end-->
</div>

<script src="${contextPath}/static/js/angular.min.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/spark-angular-global.js?v=${buildTimestamp}"></script>
<script src="${contextPath}/static/js/outsourcing/outsourcing_techIndex.js?v=${buildTimestamp}"></script>

</body>
</html>