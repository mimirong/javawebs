<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合服务</title>
</head>
<body>

  <h1 class="column_tit">您当前位置：<a href="${contextPath}/">首页</a> > <span>综合服务</span></h1>
<!--content-start-->
  <div class="column_con clearfix" id="minH">
      <h2 class="title">综合服务 </h2>
      <div class="">
          <div class="block left_float mr18 w958">
              <h3 class="title">入退园服务</h3>
              <div class="technology">
                    <ul class="technology-ul clearfix">
                        <li>
                            <a href="${contextPath}/parkJoin/listGuide">
                            <em class="icon icon12"></em>
                            <p class="title">入园指南</p>
                            </a>
                        </li>
                        <li>
                            <a href="${contextPath}/parkJoin/apply">
                                <em class="icon icon13"></em>
                                <p class="title">入园申请</p>
                            </a>
                        </li>
                        <li>
                            <a href="${contextPath}/parkQuit/listGuide">
                                <em class="icon icon14"></em>
                                <p class="title">退园指南</p>
                            </a>
                        </li>
                        <li>
                            <a href="${contextPath}/parkQuit/apply">
                                <em class="icon icon15"></em>
                                <p class="title">退园申请</p>
                            </a>
                        </li>
                    </ul>
              </div>
          </div>
          <div class="block left_float mr18 w958" >
              <h3 class="title">场地及费用服务</h3>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=GA_SITE">
                              <em class="icon icon16"></em>
                              <p class="title">场地服务指南</p>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/outsourcing/articleList?category=GA_FEES">
                              <em class="icon icon17"></em>
                              <p class="title">费用服务指南</p>
                          </a>
                      </li>
                  </ul>
              </div>
          </div>
          <div class="block left_float mr18 w958" >
              <h3 class="title">IT基础设施服务</h3>
              <div class="technology">
                  <ul class="technology-ul clearfix">
                      <li>
                          <a href="${contextPath}/outsourcing/tech/computing/applyList">
                              <em class="icon icon18"></em>
                              <p class="title">云主机</p>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/outsourcing/tech/storage/applyList">
                              <em class="icon icon19"></em>
                              <p class="title">云存储</p>
                          </a>
                      </li>
                  </ul>
              </div>
          </div>
      </div>
  </div>
  <!--content-end-->


</body>
</html>
