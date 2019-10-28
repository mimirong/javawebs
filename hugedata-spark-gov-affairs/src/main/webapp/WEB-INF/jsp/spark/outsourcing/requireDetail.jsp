<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
   
    
    <h1 class="column_tit">您当前的位置：<a href="${contextPath}/outsourcing/">首页</a> &gt; <a href="${contextPath}/outsourcing/requireList">需求大厅</a> &gt; <span>需求详情</span></h1>

    <div class="cen" id="minServiceH">
        <h2 class="se-title se-title-noborder"><span>需求详情</span><a onclick="history.go(-1);" class="more">返回</a></h2>
        <div class="se-block se-article-block se-require-block clearfix">
            <div class="con">
                <h2 class="tit"><span>${require.requireTitle }</span></h2>
                <p class="c-666 lh26">需求发布：${require.companyName}</p>
                <p class="c-666 lh26">需求领域：${require.requireArea}</p>
                <c:choose>
						   <c:when test="${require.isQuick eq '1'}">  
						   	  <p class="c-666 lh26">是否加急：加急</p>
						   </c:when>
						   <c:otherwise> 
						 	 
						   </c:otherwise>
						</c:choose>
                <div class="list">
                    <label>发布日期：</label><span><fmt:formatDate value="${require.publishTime}" pattern="yyyy-MM-dd"/></span><label>截止日期：</label><span><fmt:formatDate value="${require.deadDate}" pattern="yyyy-MM-dd"/></span><label>剩余时间：</label><span style="width: 426px;"><i>${restDay}</i>天<i>${restHour}</i>小时<i>${restMin}</i>分<i>${restSec}</i>秒</span><br/>
                   
                    <c:choose>
						   <c:when test="${require.isChat eq '1'}">  
						   	  <label>期望总价：</label><span class="red"><i>面议</i></span>
						   </c:when>
						   <c:otherwise> 
						 	  <label>期望总价：</label><span class="red"><i>${require.hopePrice }</i>${require.priceUnit}</span>
						   </c:otherwise>
						</c:choose>
                </div>
                <div class="contact">
                    <h3><span>联系人：${require.contacter }</span><span>电话：${require.contactPhone }</span></h3>
                    <p>邮箱：${require.email}</p>
                </div>
            </div>
        </div>
        <div class="se-block se-intro-block">
            <h2 class="tit">需求详情说明</h2>
            <div class="con">
                <dl>
                    <dt class="square"><em class="icon"></em>需求详情</dt>
                    <dd style="padding: 10px 20px;">
                        <table class="table">
                            <tr>
                                <td class="tit">需求主题名称</td>
                                <td>${require.requireTitle }	</td>
                                <td class="tit">计量单位</td>
                                <td>${require.numUnit }</td>
                                <td class="tit">数量</td>
                                <td>${require.requireNum }</td>
                            </tr>
                            <tr>
                                <td class="tit">交付日期</td>
                                <td><fmt:formatDate value="${require.offerDate}" pattern="yyyy-MM-dd"/></td>
                                <td class="tit">付款方式</td>
                                <td>${require.paymentMethod }</td>
                                <td class="tit">发票类型</td>
                                <td>${require.invoiceType}</td>
                            </tr>
                            <tr>
                                <td class="tit">要求货到付款</td>
                                <td>${require.isCod == "1" ? "是":"否"}</td>
                                <td class="tit">收货地址</td>
                                <td>${require.contactArea }</td>
                                <td class="tit">运费承担</td>
                                <td>${require.freightPayer }</td>
                            </tr>
                        </table>
                    </dd>
                    <dt class="square"><em class="icon"></em>详情描述
                    
                    	<c:choose>
						   <c:when test="${atts != null }">  
						   	 <span class="download">附件: 
						      	<c:forEach var="a" items="${atts}" varStatus="status">
									<a href="${ a.fileId}" class="blue">${status.count }</a>						
								</c:forEach>
							 </span>
						   </c:when>
						   <c:otherwise> 
						 	 
						   </c:otherwise>
						</c:choose>
                     </dt>
                    <dd>${require.requireDesc }</dd>
                   
                </dl>
            </div>
        </div>
    </div>

    
</body>
</html>